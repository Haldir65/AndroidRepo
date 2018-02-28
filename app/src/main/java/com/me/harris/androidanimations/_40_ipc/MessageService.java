package com.me.harris.androidanimations._40_ipc;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.me.harris.androidanimations.MessageReceiver;
import com.me.harris.androidanimations.MessageSender;
import com.me.harris.androidanimations.model.MessageModel;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Harris on 2017/6/18.
 */

public class MessageService extends Service {
    public static final String TAG = MessageService.class.getSimpleName();

    private AtomicBoolean serviceStop = new AtomicBoolean(false);

    private RemoteCallbackList<MessageReceiver> listenerList = new RemoteCallbackList<>();

    IBinder messageSender = new MessageSender.Stub() {

        @Override
        public void sendMessage(MessageModel messageModel) throws RemoteException {
            Log.e(TAG, "messageModel: " + messageModel.toString());
            MessageService.this.duStuff();
        }

        @Override
        public void registerReceiveListener(MessageReceiver messageReceiver) throws RemoteException {
            listenerList.register(messageReceiver);

        }

        @Override
        public void unregisterReceiveListener(MessageReceiver messageReceiver) throws RemoteException {
            listenerList.unregister(messageReceiver);
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            /**
             * 包名验证方式
             */
            String packageName = null;
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }
            if (packageName == null || !packageName.startsWith("com.me.harris.androidanimations")) {
                Log.d("onTransact", "拒绝调用：" + packageName);
                return false;
            }

            return super.onTransact(code, data, reply, flags);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        //自定义permission方式检查权限
        if (checkCallingOrSelfPermission("com.me.harris.androidanimations.permission.REMOTE_SERVICE_PERMISSION") == PackageManager.PERMISSION_DENIED) {
            return null;
        }
        return messageSender;
    }

    @Override
    public void onDestroy() {
        serviceStop.set(true);
        super.onDestroy();
    }

    //模拟长连接，通知客户端有新消息到达
    private class FakeTCPTask implements Runnable {
        @Override
        public void run() {
            MessageService.this.duStuff();
        }
    }

   public void duStuff() {
        while (!serviceStop.get()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MessageModel messageModel = new MessageModel();
            messageModel.setId(System.currentTimeMillis());
            messageModel.setExtra("Client YOU GET MY EXTRAS ");
            messageModel.setContent(String.valueOf(System.currentTimeMillis()));
            /**
             * RemoteCallbackList的遍历方式
             * beginBroadcast和finishBroadcast一定要配对使用
             */
            final int listenerCount = listenerList.beginBroadcast();
            Log.d(TAG, "listenerCount == " + listenerCount+"=========process id is"+android.os.Process.myPid());

            for (int i = 0; i < listenerCount; i++) {
                MessageReceiver messageReceiver = listenerList.getBroadcastItem(i);
                if (messageReceiver != null) {
                    try {
                        messageReceiver.onMessageReceived(messageModel);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
            listenerList.finishBroadcast();
        }
    }

}
