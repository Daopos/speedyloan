package com.teasting.microfinance;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Window;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        // Leave this method empty or add custom behavior
        // to handle the back button press event.
        // This will disable the default back button functionality.
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();




        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3100);
                    startActivity(new Intent(MainActivity.this, Registration.class));
                    finish();
                }catch (Exception e) {

                }
            }
        }; thread.start();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }
        User newUser = new User("ivan", "ivan", "ivan");
        UserDatabase.addUser(newUser);

    }
}