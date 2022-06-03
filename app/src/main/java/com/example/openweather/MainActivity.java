package com.example.openweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    String token = "9b65aaadc20dece694ffb1fd5c81acf1";
    RelativeLayout relativeLayout;
    ImageView imageView;

    private static String DB_NAME = "HISTORY";
    private static String TABLE = "history";

    private Button forecast;
    private EditText yourCity;
    private Button getWeather;
    private Button settings;
    private TextView temprature_city;
    private TextView wind_city;
    private TextView humidity_city;
    private TextView cloudy;
    private TextView feels;
    private TextView country;
    private TextView rain;
    DBhelper dBhelper;
    String firstAnswer;
    String firstFor;
    String secondAnswer;
    String secondFor;
    String thirdAnswer;
    String thirdFor;
    Data data = new Data();
    String city;
    public void changeActivity() {
        Intent intent = new Intent(this, Settings.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void openForecast() {
        Intent intent = new Intent(this, Forecast.class);
        intent.putExtra("city", city);
        intent.putExtra("lang", data.england);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);


        intent.putExtra("firstAnswer", firstAnswer);
        intent.putExtra("firstFor", firstFor);
        intent.putExtra("secondAnswer", secondAnswer);
        intent.putExtra("secondFor", secondFor);
        intent.putExtra("thirdAnswer", thirdAnswer);
        intent.putExtra("thirdFor", thirdFor);

        startActivity(intent);
    }

    public void changeIcon(double temp, double cloud) {
        if (cloud < 30) {
            imageView.setImageResource(R.drawable.sun);
        } else if (cloud >= 30 && cloud <= 60) {
            imageView.setImageResource(R.drawable.cloud_sun);
        } else {
            imageView.setImageResource(R.drawable.cloud);
        }
    }

    public void changBcg(boolean val) {
        if (val) {
            relativeLayout.setBackgroundColor(Color.argb(255, 13, 12, 12));
        } else {
            relativeLayout.setBackgroundColor(0xFF018786);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        relativeLayout = findViewById(R.id.mainlayout);
        yourCity = findViewById(R.id.yourCity);
        getWeather = findViewById(R.id.getWeather);
        temprature_city = findViewById(R.id.temprature_city);
        wind_city = findViewById(R.id.wind_city);
        humidity_city = findViewById(R.id.humidity_city);
        settings = findViewById(R.id.settings);
        cloudy = findViewById(R.id.cloudy_city);
        feels = findViewById(R.id.feelsLike_city);
        country = findViewById(R.id.country_city);
        imageView = findViewById(R.id.imageView);

        data.theme = getIntent().getBooleanExtra("theme", data.theme);
        data.wind_speed = getIntent().getBooleanExtra("wind_speed_data", data.wind_speed);
        data.humidity = getIntent().getBooleanExtra("humidity_data", data.humidity);
        data.cloud = getIntent().getBooleanExtra("cloud_data", data.cloud);
        data.feels_like = getIntent().getBooleanExtra("feels_like_data", data.feels_like);
        data.country = getIntent().getBooleanExtra("country_data", data.country);
        data.england = getIntent().getBooleanExtra("england_data", data.england);
        data.theme = getIntent().getBooleanExtra("theme", data.theme);
        imageView.setImageDrawable(null);
        changBcg(data.theme);
        getWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (yourCity.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity.this, "Введите город", Toast.LENGTH_SHORT).show();
                } else {
                    city = yourCity.getText().toString();

                    if (data.theme) {
                        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + token + "&units=metric";
                        new GetWeatherData().execute(url);
                    } else {
                        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + token + "&units=metric&lang=ru";


                        new GetWeatherData().execute(url);
                    }

                }

            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                changeActivity();
            }
        });
    }

    private class GetWeatherData extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            temprature_city.setText("Ожидайте...");
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                    return buffer.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        protected void onPostExecute(String res) {
            super.onPostExecute(res);
            try {
                JSONObject jsonObject = new JSONObject(res);
                if (data.england) {
                    temprature_city.setText("Temperature: " + jsonObject.getJSONObject("main").getDouble("temp") + "°");
                    if (data.wind_speed) {
                        wind_city.setText("Wind speed: " + jsonObject.getJSONObject("wind").getDouble("speed") + "м/с");
                    }
                    if (data.humidity) {
                        humidity_city.setText("Humidity: " + jsonObject.getJSONObject("main").getDouble("humidity") + "%");
                    }
                    if (data.cloud) {
                        cloudy.setText("Clouds: " + jsonObject.getJSONObject("clouds").getDouble("all") + "%");
                    }
                    if (data.feels_like) {
                        feels.setText("Feels like: " + jsonObject.getJSONObject("main").getDouble("feels_like") + "°");
                    }
                    if (data.country) {
                        country.setText("Country: " + jsonObject.getJSONObject("sys").getString("country"));
                    }
                    changeIcon(jsonObject.getJSONObject("main").getDouble("temp"), jsonObject.getJSONObject("clouds").getDouble("all"));
                } else {
                    temprature_city.setText("Температура: " + jsonObject.getJSONObject("main").getDouble("temp") + "°");
                    if (data.wind_speed) {
                        wind_city.setText("Скорость ветра: " + jsonObject.getJSONObject("wind").getDouble("speed") + "м/с");
                    } else {
                        wind_city.setText("");
                    }
                    if (data.humidity) {
                        humidity_city.setText("Влажность: " + jsonObject.getJSONObject("main").getDouble("humidity") + "%");
                    } else {
                        humidity_city.setText("");
                    }
                    if (data.cloud) {
                        cloudy.setText("Облачность: " + jsonObject.getJSONObject("clouds").getDouble("all") + "%");
                    } else {
                        cloudy.setText("");
                    }
                    if (data.feels_like) {
                        feels.setText("Ощущается как: " + jsonObject.getJSONObject("main").getDouble("feels_like") + "°");
                    } else {
                        feels.setText("");
                    }
                    if (data.country) {
                        country.setText("Страна: " + jsonObject.getJSONObject("sys").getString("country"));
                    } else {
                        country.setText("");
                    }


                    changeIcon(jsonObject.getJSONObject("main").getDouble("temp"), jsonObject.getJSONObject("clouds").getDouble("all"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            yourCity.setText("");

        }
    }
}