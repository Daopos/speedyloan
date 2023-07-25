package com.teasting.microfinance;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Calendar;

public class Verification extends AppCompatActivity {

    ImageView imgSelected;
    Button btnChooseImage;

    EditText v_pl, v_el, v_ra, v_nc, v_p, v_mi;

    EditText v_cname1, v_cphone1, v_relation1, v_cname2, v_cphone_2, v_relation2;

    EditText v_fname, v_mname, v_lname, v_gender, v_dateb, v_phone, v_suffix;

    String global = GlobalName.name;
    Boolean iden = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        if (iden) {

            setContentView(R.layout.fragment_verfication2);
        }

        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));


        }


        if(iden) {
            Spinner genderSpinner = findViewById(R.id.v_gender);
            ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(
                    this, R.array.gender_array, android.R.layout.simple_spinner_item);
            genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            genderSpinner.setAdapter(genderAdapter);

            String selectedGender = genderSpinner.getSelectedItem().toString();
        }

    }

    public void selectImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            ImageView imageView = findViewById(R.id.imgSelected);
            imageView.setImageURI(selectedImage);
        }
    }


    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }

    private Calendar calendar;
    private EditText editTextDate;
    public void onclick_date(View v) {

        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        editTextDate = findViewById(R.id.v_dateb);
        calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(Verification.this,R.style.DatePickerDialogTheme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Do something with the selected date
                        editTextDate.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                    }
                }, year, month, dayOfMonth);

        datePickerDialog.show();
    }



    public void onclick_toContact(View v) {
        v_pl = findViewById(R.id.v_pl);
        v_el = findViewById(R.id.v_pl);
        v_ra = findViewById(R.id.v_ra);
        v_nc = findViewById(R.id.v_nc);
        v_p = findViewById(R.id.v_p);
        v_mi = findViewById(R.id.v_mi);

        String global = GlobalName.name;



        if(v_pl.length() <= 0 || v_el.length() <= 0 || v_ra.length() <= 0 || v_nc.length() <= 0 || v_p.length() <= 0 || v_mi.length() <= 0) {
            Toast.makeText(Verification.this, "Please input some characters!",
                    Toast.LENGTH_LONG).show();
        }
        else {
            UserDatabase.updateBasic(global, v_ra.getText().toString());
            replaceFragment(new VerificationFragment1());
        }

    }

    public void onclick_toIdentification(View v) {

        v_cname1 = findViewById(R.id.v_cname1);
        v_cphone1 = findViewById(R.id.v_cphone1);
        v_relation1 = findViewById(R.id.v_relation1);
        v_cname2 = findViewById(R.id.v_cname2);
        v_cphone_2 = findViewById(R.id.v_cphone2);
        v_relation2 = findViewById(R.id.v_relation2);

        if (v_cname1.length() <= 0 || v_cphone1.length() <= 0 || v_relation1.length() <= 0 || v_cname2.length() <= 0 || v_cphone_2.length() <= 0 || v_relation2.length() <= 0) {
            Toast.makeText(Verification.this, "Please input some characters!",
                    Toast.LENGTH_LONG).show();
        }
        else{
            replaceFragment(new VerificationFragment2());
        }

        iden = true;
    }

    public void onclick_toProfile(View view) {

        v_fname = findViewById(R.id.v_fname);
        v_mname = findViewById(R.id.v_mname);
        v_lname = findViewById(R.id.v_lname);
        v_phone = findViewById(R.id.v_phone);
        v_dateb = findViewById(R.id.v_dateb);
        imgSelected = findViewById(R.id.imgSelected);
        v_suffix = findViewById(R.id.v_suffix);


        if (imgSelected.getDrawable() == null) {
            Toast.makeText(Verification.this, "Please input an ID!", Toast.LENGTH_LONG).show();
        }
        else if(v_fname.length() <= 0 || v_lname.length() <= 0  || v_dateb.length() <= 0 || v_phone.length() <= 0) {
            Toast.makeText(Verification.this, "Please input some characters!",
                    Toast.LENGTH_LONG).show();
        }

        else {



            String name = v_fname.getText().toString() + " " + v_mname.getText().toString()   + " " + v_lname.getText().toString() + " " + v_suffix.getText().toString() ;

            String birthdate = v_dateb.getText().toString();
            String phone = v_phone.getText().toString();

            UserDatabase.updateIdentification(global, name, birthdate, phone);
            UserDatabase.updateVerification(global,true);
            startActivity(new Intent(Verification.this, SplashCheck.class));

        }

        }

    public void onclick_btoProfile(View view) {




            startActivity(new Intent(Verification.this, Profile.class));


    }

        public void onclick_toBasic (View v){


            startActivity(new Intent(Verification.this, Verification.class));
        }
    @Override
    public void onBackPressed() {
        // Leave this method empty or add custom behavior
        // to handle the back button press event.
        // This will disable the default back button functionality.
    }

}