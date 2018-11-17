package com.me.harris.jniscratch;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView1);
        findViewById(R.id.textView3).setOnClickListener(v -> {
            String abs = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+Environment.DIRECTORY_MOVIES+File.separator+"sometext.txt";
            Log.e(TAG,abs);
            String result =  new CreatingFileUsingJni().createFileUsingJni(abs,"this is just some file content");
           Log.e(TAG,result);
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        String signature = Validator.getSignature(this);

        fab.setOnClickListener(view -> {
            String keyFromjni = PackageValidate.getPublicKey(MainActivity.this);
            textView.setText(keyFromjni);
            Log.e(TAG,keyFromjni);

            Log.e(TAG,textView.getText().toString());

            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
        textView.setText(signature.substring(10,20));

        TextView textView1 = findViewById(R.id.textView2);
        TextView textView2 = findViewById(R.id.textView);

        Crypto crypto = new Crypto();

        String crypted = crypto.encrypt("textView1");
        textView1.setText(crypted);

        String decoded = crypto.decrypt(crypted);
        textView2.setText(decoded);



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
}
