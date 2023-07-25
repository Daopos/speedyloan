package com.teasting.microfinance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MyProfile extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        // Leave this method empty or add custom behavior
        // to handle the back button press event.
        // This will disable the default back button functionality.
    }
     Button togglet;
    private boolean buttonState = false;

    private boolean passwordState = false;

    EditText m_name, m_birthdate, m_contact, m_facebook, m_address, m_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);


        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }

        togglet = findViewById(R.id.togglet);

        m_name = findViewById(R.id.mp_name);
        m_birthdate = findViewById(R.id.mp_birthdate);
        m_contact = findViewById((R.id.mp_contact));
        m_facebook = findViewById(R.id.mp_facebook);
        m_address = findViewById(R.id.mp_address);
        m_password = findViewById(R.id.mp_password);
        String global = GlobalName.name;
        User user1 = UserDatabase.getUserByUsername(global);

        m_password.setText(user1.getPassword());

        if(m_name.length() <= 0) {
            m_name.setHint("Name");
        }
        if(m_birthdate.length() <= 0) {
            m_birthdate.setHint("Birthdate");
        }
        if(m_contact.length() <= 0) {
            m_contact.setHint("Contact no.");
        }
        if(m_password.length() <= 0) {
            m_password.setHint("Password");
        }
        if(m_facebook.length() <= 0) {
            m_facebook.setHint("Facebook account(optional)");
        }
        if(m_address.length() <= 0) {
            m_address.setHint("Address");
        }





        m_name.setText(user1.getName());
        m_birthdate.setText(user1.getBirthdate());
        m_contact.setText(user1.getContact());
        m_facebook.setText(user1.getFbaccount());
        m_address.setText(user1.getAddress());
        m_password.setText(user1.getPassword());
    }

    public void onClick_toggle(View v) {

        String global = GlobalName.name;


        buttonState = !buttonState;
        if (buttonState) {
            m_name.setEnabled(true);
            m_birthdate.setEnabled(true);
            m_contact.setEnabled(true);
            m_facebook.setEnabled(true);
            m_address.setEnabled(true);
            m_password.setEnabled(true);

        } else {
            m_name.setEnabled(false);
            m_birthdate.setEnabled(false);
            m_contact.setEnabled(false);
            m_facebook.setEnabled(false);
            m_address.setEnabled(false);
            m_password.setEnabled(false);

            String name = m_name.getText().toString();
            String birthdate = m_birthdate.getText().toString();
            String contact = m_contact.getText().toString() ;
            String facebook = m_facebook.getText().toString();
            String address = m_address.getText().toString() ;
            String password =m_password.getText().toString();

            UserDatabase.updateMyProfile(global, name, birthdate, contact, facebook, address,password);

        }
    }

    public void onclick_toProfile(View view) {
        startActivity(new Intent(MyProfile.this, Profile.class));
    }

    public void toggle_pass(View v) {

        ImageView imageView = findViewById(R.id.imageView29);



        passwordState = !passwordState;
        if (passwordState) {
            m_password.setInputType(InputType.TYPE_CLASS_TEXT);
            imageView.setRotation(90);

        } else {
            m_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            imageView.setRotation(0);
        }

    }
}