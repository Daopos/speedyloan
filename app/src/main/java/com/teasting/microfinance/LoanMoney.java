package com.teasting.microfinance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LoanMoney extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        // Leave this method empty or add custom behavior
        // to handle the back button press event.
        // This will disable the default back button functionality.
    }
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText loan_amount;
    String months = "";

    String global = GlobalName.name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_money);

        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }

        radioGroup = findViewById(R.id.radio_group);

    }
    public void onclick_toHome(View v) {
        startActivity(new Intent(LoanMoney.this, Home.class));
    }

    public void months4(View v) {
        months = "4months";

        Log.d("MyApp",months);
    }
    public void months8(View v) {
        months = "8months";

        Log.d("MyApp",months);
    }
    public void months12(View v) {
        months = "12months";

        Log.d("MyApp",months);
    }

    public void onclick_toApplicationAsses(View v) {
        double amount = 0;

        loan_amount = findViewById(R.id.loan_amount);
        String loan = loan_amount.getText().toString();
        if (!TextUtils.isEmpty(loan)) {
            amount =  Double.parseDouble(loan);
        }

        if(amount < 1000 || amount > 3000) {
            Toast.makeText(LoanMoney.this, "Enter amount between 1,000 to 3,000 only", Toast.LENGTH_SHORT).show();
        }
        else if(months.length() > 0 && (amount >= 1000 && amount <=3000)) {
            UserDatabase.updateloanPending(global,true);
            startActivity(new Intent(LoanMoney.this, ApplicationAssessment.class));

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
                    .setContentTitle("Loan Details")
                    .setContentText("Your Loan is now submitted.Wait for 24 to 48 hours for the verification of your loan.")
                    .setPriority(NotificationCompat.PRIORITY_HIGH);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(0, builder.build());

        }


    }

}