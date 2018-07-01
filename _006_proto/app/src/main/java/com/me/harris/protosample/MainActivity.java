package com.me.harris.protosample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.me.harris.gen.UserProfileRequest;
import com.me.harris.gen.UserProfileResponse;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        });

        findViewById(R.id.btn_get)
                .setOnClickListener(view ->{
                    sendGetRequest();

                });

        findViewById(R.id.btn_post)
                .setOnClickListener(view -> {
                    sendPostRequest();
                });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static final String BASE_URL = "http://server_ip_address:port";

    private void sendPostRequest() {

        UserProfileRequest user = UserProfileRequest.newBuilder().setName("罗永浩")
                .setLevel(89).build();

        OkHttpClient client = new OkHttpClient.Builder().build();
        RequestBody body = new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return MediaType.parse("application/x-protobuf");
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.write(user.toByteArray());
            }
        };
        Request request = new Request.Builder().url(BASE_URL+"/pbtest/upload")
                .post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    try{
                        Log.e(TAG,"response start");

                        UserProfileResponse data = UserProfileResponse.parseFrom(response.body().bytes());
                        String name = data.getName();
                        Log.e(TAG,name);
                        for (int i = 0; i < data.getSkillsCount(); i++) {
                            String skill = data.getSkills(i);
                            Log.e(TAG,skill);
                        }
                    }catch (Exception e){

                    }


                }
            }
        });
    }

    private void sendGetRequest() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(BASE_URL+"/pbtest/download")
                .get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    try{
                        Log.e(TAG,"response start");

                        UserProfileResponse data = UserProfileResponse.parseFrom(response.body().bytes());
                        String name = data.getName();
                        Log.e(TAG,name);
                        for (int i = 0; i < data.getSkillsCount(); i++) {
                            String skill = data.getSkills(i);
                            Log.e(TAG,skill);
                        }
                    }catch (Exception e){

                    }


                }
            }
        });
    }
}
