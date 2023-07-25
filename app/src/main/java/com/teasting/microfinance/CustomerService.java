package com.teasting.microfinance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CustomerService extends AppCompatActivity {

    private EditText editTextMessage;
    private Button buttonSend;
    private LinearLayout chatLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service);
        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }

        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
        chatLayout = findViewById(R.id.chatLayout);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMessage = editTextMessage.getText().toString().trim();
                if (!userMessage.isEmpty()) {
                    addMessageToChat("User", userMessage);
                    // Process user input and generate chatbot response
                    String chatbotResponse = "Thank you for reaching out to our customer service team. We appreciate your interest in our products/services and we are here to assist you. Your satisfaction is our top priority, and we are committed to resolving any issues or answering any questions you may have.";
                    addMessageToChat("Chatbot", chatbotResponse);
                    editTextMessage.setText("");
                }
            }
        });
    }

    private void addMessageToChat(String sender, String message) {
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, 8, 0, 25);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textView.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            textView.setBackground(ContextCompat.getDrawable(this, R.drawable.round_menu));
        } else {
            // For API level below 16
            textView.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.round_menu));
        }

        textView.setPadding(40, 40, 40, 40);
        textView.setText(message);

        // Set gravity to right for sender's messages
        if (sender.equalsIgnoreCase("User")) {
            layoutParams.gravity = Gravity.END;
        } else {
            layoutParams.gravity = Gravity.START;
        }

        textView.setLayoutParams(layoutParams);
        chatLayout.setVisibility(View.VISIBLE);
        chatLayout.addView(textView);
        scrollToBottom();
    }
    private void scrollToBottom() {
        chatLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                int bottom = chatLayout.getBottom();
                ScrollView scrollView = findViewById(R.id.scrollView);
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                scrollView.smoothScrollTo(0, bottom);
            }
        }, 100);
    }

    public void onclick_toBack(View view) {
        startActivity(new Intent(CustomerService.this, HelpCenter.class));
    }
    @Override
    public void onBackPressed() {
        // Leave this method empty or add custom behavior
        // to handle the back button press event.
        // This will disable the default back button functionality.
    }
}