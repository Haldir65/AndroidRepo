
#ifndef CMAKETEMPLATE_LOGGER_H
#define CMAKETEMPLATE_LOGGER_H
#include <android/log.h>

#define LOG_TAG "=A="

#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO,  LOG_TAG, __VA_ARGS__))
#define LOGD(...) ((void)__android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__))
#define LOGW(...) ((void)__android_log_print(ANDROID_LOG_WARN,  LOG_TAG, __VA_ARGS__))
#define LOGE(...) ((void)__android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__))


#endif //CMAKETEMPLATE_LOGGER_H
