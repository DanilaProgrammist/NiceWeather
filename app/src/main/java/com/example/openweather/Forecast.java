package com.example.openweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.RelativeLayout;
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

public class Forecast extends AppCompatActivity {


    boolean england;
    RelativeLayout relativeLayout;
    TextView firstAnswer;
    TextView firstFor;
    TextView secondAnswer;
    TextView secondFor;
    TextView thirdAnswer;
    TextView thirdFor;
    DBhelper dBhelper;
    
    private static String DB_NAME = "HISTORY";
    private static Context context;
    private static String DB_PATH = context.getFilesDir().getPath() + DB_NAME;
    private static String TABLE = "history";
    String firstcity = "SELECT city FROM hisory WHERE id = 1;";
    String secondcity = "SELECT city FROM hisory WHERE id = 2;";
    String thirdcity = "SELECT city FROM hisory WHERE id = 3;";

    String firsttemp = "SELECT temp FROM hisory WHERE id = 1;";
    String secondtemp = "SELECT temp FROM hisory WHERE id = 2;";
    String thirdtemp = "SELECT temp FROM hisory WHERE id = 3;";

    Data data = new Data();

    public void changBcg(boolean val) {
        if (val) {

            //relativeLayout.setBackgroundColor(Color.argb(255, 13, 12, 12));
            //0D0C0D
        } else {

            //relativeLayout.setBackgroundColor(0xFF018786);
            //FF018786
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //String firstA = getIntent().getStringExtra("firstAnswer");
        //String firstF = getIntent().getStringExtra("firstFor");
        //String SecondA = getIntent().getStringExtra("secondAnswer");
        //String SecondF = getIntent().getStringExtra("secondFor");
        //String ThirdA = getIntent().getStringExtra("thirdAnswer");
        //String ThirdF = getIntent().getStringExtra("thirdFor");
        String city = getIntent().getStringExtra("city");
        Toast.makeText(Forecast.this, city, Toast.LENGTH_SHORT).show();
        boolean england = getIntent().getBooleanExtra("lang", false);
        super.onCreate(savedInstanceState);
        relativeLayout = findViewById(R.id.mainlayout);
        setContentView(R.layout.activity_forecast);
        data.theme = getIntent().getBooleanExtra("theme", data.theme);
        changBcg(data.theme);
        firstAnswer = findViewById(R.id.firstAnswer);
        firstFor = findViewById(R.id.firstFor);
        secondAnswer = findViewById(R.id.secondAnswer);
        secondFor = findViewById(R.id.secondFor);
        thirdAnswer = findViewById(R.id.thirdAnswer);
        thirdFor = findViewById(R.id.thirdAnswer);

        //firstAnswer.setText(firstA);
        //firstFor.setText(firstF);
        //secondAnswer.setText(SecondA);
        //secondFor.setText(SecondF);
        //thirdAnswer.setText(ThirdA);
        //thirdFor.setText(ThirdF);

    }

}


