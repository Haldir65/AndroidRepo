package com.me.harris.androidanimations._40_ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.me.harris.androidanimations.BaseAppCompatActivity;
import com.me.harris.androidanimations.model.MessageModel;
import com.me.harris.androidanimations.MessageReceiver;
import com.me.harris.androidanimations.MessageSender;
import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityIpcBinding;
import com.me.harris.androidanimations.utils.LogUtil;

/**
 * Created by Harris on 2017/6/18.
 */

public class IPCMainActivity extends BaseAppCompatActivity implements View.OnClickListener {

    ActivityIpcBinding binding;
    public static final String TAG = IPCMainActivity.class.getSimpleName();

    private MessageSender messageSender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ipc);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.button.setOnClickListener(this);
        binding.button1.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                setupService();
                break;
            case R.id.button1:
                Intent intent = new Intent(this, SocketIPCActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    /**
     * 1.unregisterListener
     * 2.unbindService
     */
    @Override
    protected void onDestroy() {
        //解除消息监听接口
        if (messageSender != null && messageSender.asBinder().isBinderAlive()) {
            try {
                messageSender.unregisterReceiveListener(messageReceiver);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if (registerd) {
            unbindService(serviceConnection);
        }
        super.onDestroy();
    }

    /**
     * bindService & startService：
     * 使用bindService方式，多个Client可以同时bind一个Service，但是当所有Client unbind后，Service会退出
     * 通常情况下，如果希望和Service交互，一般使用bindService方法，获取到onServiceConnected中的IBinder对象，和Service进行交互，
     * 不需要和Service交互的情况下，使用startService方法即可，Service主线程执行完成后会自动关闭；
     * unbind后Service仍保持运行，可以同时调用bindService和startService（比如像聊天软件，退出UI进程，Service仍能接收消息）
     */
    private void setupService() {
        Intent intent = new Intent(this, MessageService.class);
       registerd =  bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        int pid = android.os.Process.myPid();
        LogUtil.p("---------------------currentProcess id is "+pid);
       startService(intent);
    }

    boolean registerd;

    /**
     * Binder可能会意外死忙（比如Service Crash），Client监听到Binder死忙后可以进行重连服务等操作
     */
    IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.d(TAG, "binderDied");
            if (messageSender != null) {
                messageSender.asBinder().unlinkToDeath(this, 0);
                messageSender = null;
            }
            //// TODO: 2017/2/28 重连服务或其他操作
            setupService();
        }
    };

    //消息监听回调接口
    private MessageReceiver messageReceiver = new MessageReceiver.Stub() {

        public  final String TAG = IPCMainActivity.class.getSimpleName();

        @Override
        public void onMessageReceived(MessageModel receivedMessage) throws RemoteException {
            Log.d(TAG, "onMessageReceived: " + receivedMessage.toString());
            binding.tvPs.setText(receivedMessage.toString());
        }
    };

    ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //使用asInterface方法取得AIDL对应的操作接口
            messageSender = MessageSender.Stub.asInterface(service);

            //生成消息实体对象
            MessageModel messageModel = new MessageModel();
            messageModel.setId(1273783L);
            messageModel.setContent("this is my content");
            messageModel.setExtra("This is My extra");

            try {
                //设置Binder死亡监听
                messageSender.asBinder().linkToDeath(deathRecipient, 0);
                //把接收消息的回调接口注册到服务端
                messageSender.registerReceiveListener(messageReceiver);
                //调用远程Service的sendMessage方法，并传递消息实体对象
                messageSender.sendMessage(messageModel);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

}
