package com.teasting.microfinance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class Repayment extends AppCompatActivity {

    private static final int QR_CODE_SCAN_REQUEST_CODE = 1;
    private ImageView qrCodeImageView;
    @Override
    public void onBackPressed() {
        // Leave this method empty or add custom behavior
        // to handle the back button press event.
        // This will disable the default back button functionality.
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repayment);

        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }

        qrCodeImageView = findViewById(R.id.imageView43);
        qrCodeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQRCodeScanner();
            }
        });
    }

    private void startQRCodeScanner() {
        Intent intent = new Intent(this, QRCodeScannerActivity.class);
        startActivityForResult(intent, QR_CODE_SCAN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == QR_CODE_SCAN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String qrCodeResult = data.getStringExtra("qr_code_result");
                // Process the scanned QR code result as needed
            }
        }
    }

    public void onclick_toProfile(View view) {
        startActivity(new Intent(Repayment.this, Profile.class));
    }

}