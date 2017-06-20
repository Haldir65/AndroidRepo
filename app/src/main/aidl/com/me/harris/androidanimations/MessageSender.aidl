// MessageSender.aidl
package com.me.harris.androidanimations;
import com.me.harris.androidanimations.MessageReceiver;
import com.me.harris.androidanimations.model.MessageModel;

// Declare any non-default types here with import statements

interface MessageSender {
    void sendMessage(in MessageModel messageModel);

       void registerReceiveListener(MessageReceiver messageReceiver);

       void unregisterReceiveListener(MessageReceiver messageReceiver);
}
