package com.me.harris.droidx.cronet;

import android.util.Log;
import org.chromium.net.CronetException;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;

/**
 * Use this class for create a request and receive a callback once the request is finished.
 */
class SimpleUrlRequestCallback extends UrlRequest.Callback {

    private ByteArrayOutputStream bytesReceived = new ByteArrayOutputStream();
    private WritableByteChannel receiveChannel = Channels.newChannel(bytesReceived);
    public long start;
    private long stop;

    private static final String TAG = "UrlRequestCallback";

//    SimpleUrlRequestCallback(ImageView imageView, Context context) {
//        this.imageView = imageView;
//        this.mainActivity = (Activity) context;
//    }

    @Override
    public void onRedirectReceived(
            UrlRequest request, UrlResponseInfo info, String newLocationUrl) {
        android.util.Log.i(TAG, "****** onRedirectReceived ******");
        request.followRedirect();
    }

    @Override
    public void onResponseStarted(UrlRequest request, UrlResponseInfo info) {
        android.util.Log.i(TAG, "****** Response Started ******");
        android.util.Log.i(TAG, "*** Headers Are *** " + info.getAllHeaders());

        request.read(ByteBuffer.allocateDirect(32 * 1024));
    }

    @Override
    public void onReadCompleted(
            UrlRequest request, UrlResponseInfo info, ByteBuffer byteBuffer) {
        android.util.Log.i(TAG, "****** onReadCompleted ******" + byteBuffer);
        byteBuffer.flip();
        try {
            receiveChannel.write(byteBuffer);
        } catch (IOException e) {
            android.util.Log.e(TAG, "IOException during ByteBuffer read. Details: ", e);
        }
        byteBuffer.clear();
        request.read(byteBuffer);
    }

    @Override
    public void onSucceeded(UrlRequest request, UrlResponseInfo info) {

        stop = System.nanoTime();

        android.util.Log.i(TAG,
                "****** Cronet Request Completed, the latency is " + (stop - start));

        android.util.Log.i(TAG,
                "****** Cronet Request Completed, status code is " + info.getHttpStatusCode()
                        + ", total received bytes is " + info.getReceivedByteCount());
        // Set the latency
//        ((MainActivity) context).addCronetLatency(stop - start);

        byte[] byteArray = bytesReceived.toByteArray();
        String res = new String(byteArray,Charset.forName("utf-8"));
        Log.e(TAG, "onSucceeded: "+res );
//        final Bitmap bimage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//        mainActivity.runOnUiThread(new Runnable() {
//            public void run() {
//                imageView.setImageBitmap(bimage);
//                imageView.getLayoutParams().height = bimage.getHeight();
//                imageView.getLayoutParams().width = bimage.getWidth();
//            }
//        });
    }

    @Override
    public void onFailed(UrlRequest var1, UrlResponseInfo var2, CronetException var3) {
        android.util.Log.e(TAG, "****** onFailed, error is: " + var3.getMessage());
    }
}
