package com.teasting.microfinance;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    EditText s_email;

    EditText s_password;

    EditText s_username;
    EditText s_confirmpass;
    @Override
    public void onBackPressed() {
        // Leave this method empty or add custom behavior
        // to handle the back button press event.
        // This will disable the default back button functionality.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }

        s_email = (EditText) findViewById(R.id.s_email);
        s_password = (EditText) findViewById(R.id.s_password);
        s_username = (EditText) findViewById(R.id.s_username);
        s_confirmpass = (EditText) findViewById(R.id.s_confirmpass);

        Dialog dialog = new Dialog(Signup.this);
        dialog.setContentView(R.layout.confirm_popup);
        dialog.setCancelable(false);

        dialog.show();

        dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.findViewById(R.id.button15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this, Registration.class));
            }
        });


    }

    public void onClick_toLogin(View v){
        startActivity(new Intent(Signup.this, Registration.class));
    }

    public void Onclick_Signup(View v) {
        String s_emails = s_email.getText().toString();
        String s_passwords = s_password.getText().toString();
        String s_usernames = s_username.getText().toString();
        String s_confirmpasss = s_confirmpass.getText().toString();


        Pattern pattern = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");

        Matcher matcher = pattern.matcher(s_emails);


         if(s_usernames.length() <= 0) {
            s_username.requestFocus();
            s_username.setError("Please enter a username.");
        }
        else if (UserDatabase.checkUserExists(s_usernames)) {
            Toast.makeText(Signup.this, "Username already taken!", Toast.LENGTH_SHORT).show();
        }

        else if(s_emails.length() <= 0) {
            s_email.requestFocus();
            s_email.setError("Please provide a valid email address.");
        }
         else if(UserDatabase.checkUserEmailExists(s_emails)) {
             Toast.makeText(Signup.this, "Email already taken!", Toast.LENGTH_SHORT).show();
         }
        else if (!matcher.matches()) {
            s_email.requestFocus();
            s_email.setError("Please provide a valid email address.");
        }
        else if (s_passwords.length() <= 5) {
            s_password.requestFocus();
            s_password.setError("Please choose a password with at least 8 characters.");
        }
        else if (s_emails.contains(" ")) {
             s_email.requestFocus();
             s_email.setError("Please avoid using spaces in your input.");
        }
         else if ( s_passwords.contains(" ")) {
             s_password.requestFocus();
             s_password.setError("Please avoid using spaces in your input.");
         }
         else if (s_usernames.contains(" ")) {
             s_username.requestFocus();
             s_username.setError("Please avoid using spaces in your input.");
         }
         else if (!s_passwords.equals(s_confirmpasss)) {
             s_password.requestFocus();
             s_confirmpass.requestFocus();
             s_password.setError("Passwords Do Not Match!");
             s_confirmpass.setError("Passwords Do Not Match!");
        }

        else if(s_passwords.equals(s_confirmpasss)){

            GlobalName.name = s_usernames;

            User newUser = new User(s_usernames, s_emails, s_passwords, true);
            UserDatabase.addUser(newUser);
            startActivity(new Intent(Signup.this, Home.class));
        }

    }


}