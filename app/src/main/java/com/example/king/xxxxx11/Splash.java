package com.example.king.xxxxx11;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 10000;

    TextView inside, outside;

    String message = "Tidak ada pesan";
    String kuliah = "Tidak ada kuliah";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
        inside = (TextView) findViewById(R.id.txtMessage);
        outside = (TextView) findViewById(R.id.txtKuliah);

        if(getIntent().getExtras() !=null) {
            message = getIntent().getExtras().getString("message");
            kuliah = getIntent().getExtras().getString("kuliah");
            if (message==null) {
                message = "No Now SMS";
            }
            if (kuliah ==null){
                kuliah = "tidak ada kuliah";
            }
        }
        inside.setText("Message  :" +message);
        outside.setText("kuliah  :" +kuliah );
    }
}