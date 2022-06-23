#include <jni.h>
#include <strings.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <sys/time.h>
#include <android/native_window_jni.h>

#include <libavfilter/avfilter.h>
//封装格式
#include "libavformat/avformat.h"
//解码
#include "libavcodec/avcodec.h"
//缩放
#include "libswscale/swscale.h"
//重采样
#include "libswresample/swresample.h"

#include "logger.h"

#define MAX_AUDIO_FRME_SIZE 48000 * 4


//JNIEXPORT jstring JNICALL
//Java_com_me_harris_ffmpegintegration_MainActivity_stringFromJNI(
//        JNIEnv *env,
//        jobject /* this */) {
//    std::string hello = "Hello from C++";
//    return env->NewStringUTF(hello.c_str());
//}
// https://xiaozhuanlan.com/topic/3418209675
static volatile int stop = 0 ;

JNIEXPORT void JNICALL
Java_com_me_harris_ffmpegintegration_FFSurfaceView_render(JNIEnv *env, jobject instance, jstring url_,
                                                          jobject surface) {
    stop = 0;
    const char *url = (*env)->GetStringUTFChars(env, url_, 0);
    // 注册。
    av_register_all();
    // 打开地址并且获取里面的内容  avFormatContext是内容的一个上下文
    AVFormatContext *avFormatContext = avformat_alloc_context();
    avformat_open_input(&avFormatContext, url, NULL, NULL);
    avformat_find_stream_info(avFormatContext, NULL);

    // 找出视频流
    int video_index = -1;
    int audio_stream_idx = -1;
    for (int i = 0; i < avFormatContext->nb_streams; ++i) {
        if (avFormatContext->streams[i]->codec->codec_type == AVMEDIA_TYPE_VIDEO) {
            LOGE("video code %s at index %d","AVMEDIA_TYPE_VIDEO",i);
            video_index = i;
        } else if(avFormatContext->streams[i]->codec->codec_type == AVMEDIA_TYPE_AUDIO){
            LOGE("%s at index %d","AVMEDIA_TYPE_AUDIO",i);
            audio_stream_idx = i;
        }
    }
    // 解码  转换  绘制
    // 获取解码器上下文
    AVCodecContext *avCodecContext = avFormatContext->streams[video_index]->codec;
    // 获取解码器
    AVCodec *avCodec = avcodec_find_decoder(avCodecContext->codec_id);
    // 打开解码器
    if (avcodec_open2(avCodecContext, avCodec, NULL) < 0) {
        // 打开失败。
        LOGE("%s failed","avcodec_open2");
        return;
    }
    // 申请AVPacket和AVFrame，
    // 其中AVPacket的作用是：保存解码之前的数据和一些附加信息，如显示时间戳（pts）、解码时间戳（dts）、数据时长，所在媒体流的索引等；
    // AVFrame的作用是：存放解码过后的数据。
    AVPacket *avPacket = (AVPacket *) av_malloc(sizeof(AVPacket));
    av_init_packet(avPacket);
    // 分配一个AVFrame结构体,AVFrame结构体一般用于存储原始数据，指向解码后的原始帧
    AVFrame *avFrame = av_frame_alloc();
    //分配一个AVFrame结构体，指向存放转换成rgb后的帧
    AVFrame *rgb_frame = av_frame_alloc();
    // rgb_frame是一个缓存区域，所以需要设置。
    // 缓存区

    uint8_t *out_buffer = (uint8_t *) av_malloc(
            avpicture_get_size(AV_PIX_FMT_RGBA, avCodecContext->width, avCodecContext->height));
    // 与缓存区相关联，设置rgb_frame缓存区
    avpicture_fill((AVPicture *) rgb_frame, out_buffer, AV_PIX_FMT_RGBA, avCodecContext->width,
                   avCodecContext->height);
    // 原生绘制，需要ANativeWindow
    ANativeWindow *pANativeWindow = ANativeWindow_fromSurface(env, surface);
    if (pANativeWindow == 0) {
        // 获取native window 失败
        return;
    }
    struct SwsContext *swsContext = sws_getContext(
            avCodecContext->width,
            avCodecContext->height,
            avCodecContext->pix_fmt,
            avCodecContext->width,
            avCodecContext->height,
            AV_PIX_FMT_RGBA,
            SWS_BICUBIC,
            NULL,
            NULL,
            NULL);
    // 视频缓冲区
    ANativeWindow_Buffer native_outBuffer;
    // 开始解码了。
    int frameCount;

    struct timeval start, end;

    const double FRAME_RATE = (1000/24.0)* 1000; //纳秒 24fps 41ms更新一次

    while (stop!=1&&av_read_frame(avFormatContext, avPacket) >= 0) {
        gettimeofday( &start, NULL );
        if (avPacket->stream_index == video_index) {
            avcodec_decode_video2(avCodecContext, avFrame, &frameCount, avPacket);
            // 当解码一帧成功过后，我们转换成rgb格式并且绘制。
            LOGD("%s %d","frameCount = ",frameCount);
            if (frameCount) {
                ANativeWindow_setBuffersGeometry(pANativeWindow, avCodecContext->width,
                                                 avCodecContext->height, WINDOW_FORMAT_RGBA_8888);
                // 上锁
                ANativeWindow_lock(pANativeWindow, &native_outBuffer, NULL);
                // 转换为rgb格式
                sws_scale(swsContext, (const uint8_t *const *) avFrame->data, avFrame->linesize, 0,
                          avFrame->height, rgb_frame->data, rgb_frame->linesize);
                uint8_t *dst = (uint8_t *) native_outBuffer.bits;
                int destStride = native_outBuffer.stride * 4;
                uint8_t *src = rgb_frame->data[0];
                int srcStride = rgb_frame->linesize[0];
                for (int i = 0; i < avCodecContext->height; ++i) {
                    memcpy(dst + i * destStride, src + i * srcStride, srcStride);
                }
                ANativeWindow_unlockAndPost(pANativeWindow);
            }
            gettimeofday( &end, NULL );
            long timeuse = 1000000 * ( end.tv_sec - start.tv_sec ) + end.tv_usec - start.tv_usec;
            LOGE("timeuse %ld FRAME_RATE %f",timeuse,FRAME_RATE);
            if(timeuse<FRAME_RATE){
                LOGE("slept %f",FRAME_RATE-timeuse);
                usleep(FRAME_RATE-timeuse); // 单位是毫秒 一百万分之一秒
            }

//            LOGD("time: %d us\n",timeuse);
             // tv_sec是时间戳 单位秒  272424是微秒
        }if(avPacket->stream_index == audio_stream_idx){

        }
        av_free_packet(avPacket);

    }

    ANativeWindow_release(pANativeWindow);
    av_frame_free(&avFrame);
    av_frame_free(&rgb_frame);
    avcodec_close(avCodecContext);
    avformat_free_context(avFormatContext);
//    env->ReleaseStringUTFChars(url_, url);
}



JNIEXPORT jstring JNICALL
Java_com_me_harris_ffmpegintegration_MainActivity_urlprotocolinfo(JNIEnv *env, jobject instance) {
    char info[40000] = {0};
    av_register_all();
    struct URLProtocol *pup = NULL;
    struct URLProtocol **p_temp = &pup;
    avio_enum_protocols((void **) p_temp, 0);
    while ((*p_temp) != NULL) {
        sprintf(info, "%sInput: %s\n", info, avio_enum_protocols((void **) p_temp, 0));
    }
    pup = NULL;
    avio_enum_protocols((void **) p_temp, 1);
    while ((*p_temp) != NULL) {
        sprintf(info, "%sInput: %s\n", info, avio_enum_protocols((void **) p_temp, 1));
    }

    return (*env)->NewStringUTF(env, info);
}

JNIEXPORT jstring JNICALL
Java_com_me_harris_ffmpegintegration_MainActivity_avformatinfo(JNIEnv *env, jobject instance) {

    char info[40000] = {0};

    av_register_all();

    AVInputFormat *if_temp = av_iformat_next(NULL);
    AVOutputFormat *of_temp = av_oformat_next(NULL);
    while (if_temp != NULL) {
        sprintf(info, "%sInput: %s\n", info, if_temp->name);
        if_temp = if_temp->next;
    }
    while (of_temp != NULL) {
        sprintf(info, "%sOutput: %s\n", info, of_temp->name);
        of_temp = of_temp->next;
    }
    return (*env)->NewStringUTF(env, info);
}

JNIEXPORT jstring JNICALL
Java_com_me_harris_ffmpegintegration_MainActivity_avcodecinfo(JNIEnv *env, jobject instance) {
    char info[40000] = {0};

    av_register_all();

    AVCodec *c_temp = av_codec_next(NULL);

    while (c_temp != NULL) {
        if (c_temp->decode != NULL) {
            sprintf(info, "%sdecode:", info);
        } else {
            sprintf(info, "%sencode:", info);
        }
        switch (c_temp->type) {
            case AVMEDIA_TYPE_VIDEO:
                sprintf(info, "%s(video):", info);
                break;
            case AVMEDIA_TYPE_AUDIO:
                sprintf(info, "%s(audio):", info);
                break;
            default:
                sprintf(info, "%s(other):", info);
                break;
        }
        sprintf(info, "%s[%10s]\n", info, c_temp->name);
        c_temp = c_temp->next;
    }
    return (*env)->NewStringUTF(env, info);
}

JNIEXPORT jstring JNICALL
Java_com_me_harris_ffmpegintegration_MainActivity_avfilterinfo(JNIEnv *env, jobject instance) {
    char info[40000] = {0};
    avfilter_register_all();

    AVFilter *f_temp = (AVFilter *) avfilter_next(NULL);
    while (f_temp != NULL) {
        sprintf(info, "%s%s\n", info, f_temp->name);
        f_temp = f_temp->next;
    }
    return (*env)->NewStringUTF(env, info);
}


JNIEXPORT jstring JNICALL
Java_com_me_harris_ffmpegintegration_MainActivity_stopPlay(JNIEnv *env, jobject instance) {
    stop = 1;
    LOGE("%s","stopPlay!!!");
    return (*env)->NewStringUTF(env, "info");
}
