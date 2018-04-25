package com.me.harris.socketio;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPClient {

    public static final String TAG = TCPClient.class.getSimpleName();

    public static final String HOST = "192.168.123.227";
    private static final int PORT = 10086;
    private PrintWriter printWriter;
    private BufferedReader in;
    private DataInputStream dataInputStream;
    private ExecutorService mExecutorService = null;
    InputStream inputStream;
    private String receiveMsg;


    public void init() {
            mExecutorService = Executors.newCachedThreadPool();
            mExecutorService.execute(new ConnectService());
    }





    ////  connect
    public void connect() {
        try {
            Socket socket = new Socket(HOST, PORT);
//            socket.setSoTimeout(60000);
            printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream(), "UTF-8")), true);
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
//            dataInputStream = new DataInputStream(socket.getInputStream());
            inputStream = socket.getInputStream();
            receiveMsg();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ConnectService implements Runnable {


        @Override
        public void run() {
            connect();
        }
    }

    public static final int BUFFER_SIZE = 1024<<3;

// receive msg
    private void receiveMsg() {
        try {
            byte[] buf = new byte[BUFFER_SIZE];
            while (true) {

//                if ((receiveMsg = in.readLine()) != null) {
//                    Log.d(TAG, "receiveMsg:" + receiveMsg);
////
////                    runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////                            mTextView.setText(receiveMsg + "\n\n" + mTextView.getText());
////                        }
////                    });
//                }
//                int msgFromServer = dataInputStream.readInt();

//                Log.d(TAG, "receiveMsg: " + msgFromServer);

//                dataInputStream.readInt();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
               while (true){
                    int nread = inputStream.read(buf, 0, buf.length);
                    Log.d(TAG, "=============读取了====================="+nread);
                    baos.write(buf, 0, nread);
                    if (nread < buf.length) {
                        break;
                    }
                }
                baos.close();
                String data = new String(baos.toByteArray(), Charset.defaultCharset());
                Log.d(TAG, "因为utf-8中一个汉字可能是2-4个byte，所以得把整个byteArray搞出来才能去组装这个String+\n"+data);
            }
        } catch (IOException e) {
            Log.e(TAG, "receiveMsg: ");
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        mExecutorService.execute(new sendMessageService(msg));
    }


    class sendMessageService implements Runnable {


        String sendMsg;


        public sendMessageService(String sendMsg) {
            this.sendMsg = sendMsg;

        }

        @Override
        public void run() {
            Log.d(TAG, "发送数据给服务器");
            printWriter.println(this.sendMsg);
        }
    }
}
