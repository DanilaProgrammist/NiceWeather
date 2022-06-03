package com.example.openweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Switch;

public class Settings extends AppCompatActivity {
    RelativeLayout relativeLayout;
    private Switch themes;
    private Switch wind_speed;
    private Switch humidity;
    private Switch cloud;
    private Switch feels_like;
    private Switch country;
    private Switch england;
    private Button button;
    boolean theme = false;
    boolean wind_speed_data = false;
    boolean humidity_data = false;
    boolean cloud_data = false;
    boolean feels_like_data = false;
    boolean country_data = false;
    boolean england_data = false;

    public  void changeActivity(boolean theme){
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("theme", theme);
        //intent.putExtra("wind_speed_data", wind_speed_data);
        //intent.putExtra("humidity_data", humidity_data);
        //intent.putExtra("cloud_data", cloud_data);
        //intent.putExtra("feels_like_data", feels_like_data);
        //intent.putExtra("country_data", country_data);
        //intent.putExtra("england_data", england_data);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        Data data = new Data();

        setContentView(R.layout.activity_settings);
        themes = findViewById(R.id.switch1);
        wind_speed = findViewById(R.id.wind_speed);
        humidity = findViewById(R.id.humidity);
        cloud = findViewById(R.id.cloud);
        feels_like = findViewById(R.id.feels_like);
        country = findViewById(R.id.country);
        england = findViewById(R.id.england);
        button = findViewById(R.id.back);
        relativeLayout = findViewById(R.id.mainlayout);
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        england.setChecked(sharedPreferences.getBoolean("enValue", true));
        wind_speed.setChecked(sharedPreferences.getBoolean("wiValue",true ));
        humidity.setChecked(sharedPreferences.getBoolean("huValue",true ));
        cloud.setChecked(sharedPreferences.getBoolean("cloValue",true ));
        feels_like.setChecked(sharedPreferences.getBoolean("feelValue",true ));
        country.setChecked(sharedPreferences.getBoolean("counValue",true ));
        themes.setChecked(sharedPreferences.getBoolean("themeValue",true ));
        themes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = ((Switch) v).isChecked();
                if (check) {
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("themeValue", true);
                    editor.apply();
                    relativeLayout.setBackgroundColor(Color.argb(255,13, 12, 12));
                    themes.setChecked(true);
                    theme = true;

                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("themeValue", false);
                    editor.apply();
                    themes.setChecked(false);
                    relativeLayout.setBackgroundColor(0xFF018786);
                    theme = false;
                }
            }
        });
        wind_speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = ((Switch) v).isChecked();
                if (check) {
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("wiValue", true);
                    editor.apply();
                    wind_speed.setChecked(true);
                    wind_speed_data = true;
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("wiValue", false);
                    editor.apply();
                    wind_speed.setChecked(false);
                    wind_speed_data = false;
                }
            }
        });
        humidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = ((Switch) v).isChecked();
                if (check) {
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("huValue", true);
                    editor.apply();
                    humidity.setChecked(true);
                    humidity_data = true;
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("huValue", false);
                    editor.apply();
                    humidity.setChecked(false);
                    humidity_data = false;
                }
            }
        });
        cloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = ((Switch) v).isChecked();
                if (check) {
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("cloValue", true);
                    editor.apply();
                    cloud.setChecked(true);
                    cloud_data = true;
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("cloValue", false);
                    editor.apply();
                    cloud.setChecked(false);
                    cloud_data = false;
                }
            }
        });
        feels_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = ((Switch) v).isChecked();
                if (check) {
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("feelValue", true);
                    editor.apply();
                    feels_like.setChecked(true);
                    feels_like_data = true;
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("feelValue", false);
                    editor.apply();
                    feels_like.setChecked(false);
                    feels_like_data = false;
                }
            }
        });
        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = ((Switch) v).isChecked();
                if (check) {
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("counValue", true);
                    editor.apply();
                    country.setChecked(true);
                    country_data = true;
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("counValue", false);
                    editor.apply();
                    country.setChecked(false);
                    country_data = false;
                }
            }
        });
        england.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (england.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("enValue", true);
                    editor.apply();
                    england.setChecked(true);
                    england_data = true;
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("enValue", false);
                    editor.apply();
                    england.setChecked(false);
                    england_data = false;
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, MainActivity.class);
                intent.putExtra("england_data", england_data);
                intent.putExtra("wind_speed_data", wind_speed_data);
                intent.putExtra("humidity_data", humidity_data);
                intent.putExtra("cloud_data", cloud_data);
                intent.putExtra("feels_like_data", feels_like_data);
                intent.putExtra("country_data", country_data);
                intent.putExtra("theme", theme);
                Intent intent1 = new Intent(Settings.this, Forecast.class);
                intent1.putExtra("theme", theme);
                startActivity(intent);
            }
        });

    }
}