package com.me.harris.androidanimations._41_cute_animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.me.harris.androidanimations.R;

public class WeatherMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main);
        final WeatherView weatherView = (WeatherView) this.findViewById(R.id.weather_view);
        Button button= (Button) this.findViewById(R.id.btn_start);
        weatherView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherView.startAnim();
            }
        });
    }
}
