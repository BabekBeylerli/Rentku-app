package com.example.rentalcarmobile.screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rentalcarmobile.MainActivity;
import com.example.rentalcarmobile.R;
import com.example.rentalcarmobile.errorhandling.ErrorHandler;

public class SplashActivity extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    private ErrorHandler errorHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        errorHandler = ErrorHandler.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(mainIntent);
                    finish();
                } catch (Exception e) {
                    errorHandler.handleException(SplashActivity.this, e);
                }
            }
        }, SPLASH_TIME_OUT);
    }
}
