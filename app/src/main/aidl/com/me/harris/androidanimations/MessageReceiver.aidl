// MessageReceiver.aidl
package com.me.harris.androidanimations;

import com.me.harris.androidanimations.MessageModel;

// Declare any non-default types here with import statements

interface MessageReceiver {

        void onMessageReceived(in MessageModel receivedMessage);
}
