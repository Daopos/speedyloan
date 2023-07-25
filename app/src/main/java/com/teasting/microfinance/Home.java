package com.teasting.microfinance;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Home extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        // Leave this method empty or add custom behavior
        // to handle the back button press event.
        // This will disable the default back button functionality.
    }
    Button borrow_now;
    String global = GlobalName.name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();


        String global = GlobalName.name;
        User user1 = UserDatabase.getUserByUsername(global);

        boolean iftrue = user1.signup;

        if(iftrue == true) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId = "my_channel_id";
                CharSequence channelName = "My Channel";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "my_channel_id")
                    .setSmallIcon(R.drawable.logo_peso)
                    .setContentTitle("SIGNED IN!")
                    .setContentText("Congratulations! You have successfully created your account. Welcome aboard!")
                    .setPriority(NotificationCompat.PRIORITY_HIGH);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(0, builder.build());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.bill:
                        startActivity(new Intent(getApplicationContext(),Bill.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });


        user1.signup = false;
    }

    public void onclick_toLoanMoney(View v) {

        User user = UserDatabase.getUserByUsername(global);

        if(user.getVerification()) {
            if(user.getLoanPending()) {
                startActivity(new Intent(Home.this, ApplicationAssessment.class));
            }
            else {
                startActivity(new Intent(Home.this, LoanMoney.class));
            }
        }else {
            startActivity(new Intent(Home.this, Verification.class));
        }
    }


}