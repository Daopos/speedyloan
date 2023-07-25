package com.teasting.microfinance;

import android.content.Intent;
import android.os.Build;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText r_email;

    EditText r_password;
    @Override
    public void onBackPressed() {
        // Leave this method empty or add custom behavior
        // to handle the back button press event.
        // This will disable the default back button functionality.
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }

        r_email = (EditText) findViewById(R.id.r_email);
        r_password = (EditText) findViewById(R.id.r_password);
    }


    public void onClick_toSignup(View v){
        startActivity(new Intent(Registration.this, Signup.class));
    }

    public void onClick_Login(View v) {
        String r_emails = r_email.getText().toString();
        String r_passwords = r_password.getText().toString();



        User user = UserDatabase.getUserByEmail(r_emails);


        if (user != null && user.getPassword().equals(r_passwords) && user.getEmail().equals(r_emails)) {
            GlobalName.name = user.getUsername();

            startActivity(new Intent(Registration.this, Home.class));

        } else {

            r_email.requestFocus();
            r_email.setError("Wrong Email Credential!");


            r_password.requestFocus();
            r_password.setError("Wrong Password Credential");
        }

    }
}