package com.teasting.microfinance;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class Profile extends AppCompatActivity {

    TextView email;
    TextView username;

    Dialog mdialog;
    @Override
    public void onBackPressed() {
        // Leave this method empty or add custom behavior
        // to handle the back button press event.
        // This will disable the default back button functionality.
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }

        email = findViewById(R.id.p_email);
        username = findViewById(R.id.p_username);

        String global = GlobalName.name;

        User user1 = UserDatabase.getUserByUsername(global);

        email.setText(user1.getEmail());
        username.setText(user1.getUsername().toUpperCase(Locale.ROOT));




    }

    public void onclick_toHome(View v) {
        startActivity(new Intent(Profile.this, Home.class));
    }

    public void onclick_toMyProfile(View v) {
        startActivity(new Intent(Profile.this, MyProfile.class));
    }

    public void onClick_toLogin(View v){

        mdialog = new Dialog(this);

        mdialog.setContentView(R.layout.popup);


        mdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mdialog.show();
        Button logout = mdialog.findViewById(R.id.logout_btn);
        Button cancel = mdialog.findViewById(R.id.cancel_btn);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdialog.dismiss();
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdialog.dismiss();
                startActivity(new Intent(Profile.this, Registration.class));
            }
        });


    }

    public void onClick_toAccount(View v) {
        startActivity(new Intent(Profile.this, Account.class));

    }

    public void onClick_toVerification(View view) {
        startActivity(new Intent(Profile.this, Verification.class));
    }

    public void onClick_toMessage(View view) {
        startActivity(new Intent(Profile.this, Message.class));
    }


    public void onClick_toCoupon(View view) {
        startActivity(new Intent(Profile.this, Coupon.class));
    }

    public void onclick_toWithdrawal(View v) {
        startActivity(new Intent(Profile.this, Withdrawal.class));
    }

    public void  onClick_toRepayment(View v) {
        startActivity(new Intent(Profile.this, Repayment.class));
    }

    public void  onClick_toHelpCenter(View v) {
        startActivity(new Intent(Profile.this, HelpCenter.class));
    }

    public void  onClick_toLoyal(View v) {
        startActivity(new Intent(Profile.this, LoyaltyDetails.class));
    }
}