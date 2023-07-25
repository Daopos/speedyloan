package com.teasting.microfinance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class HelpCenter extends AppCompatActivity {

    TabLayout tabLayout2;
    ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);

        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }

        tabLayout2 = findViewById(R.id.tabLayout2);
        viewPager2 = findViewById(R.id.viewPager2);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout2.getTabAt(position).select();
            }
        });

    }

    public void onclick_toProfile(View view) {
        startActivity(new Intent(HelpCenter.this, Profile.class));
    }

    private void toggleTextViewVisibility(TextView textView, ImageView imageView) {
        if(textView.getVisibility() == View.GONE) {
            textView.setVisibility(View.VISIBLE);
            imageView.setImageResource(R.drawable.arrow_up);
        } else {
            textView.setVisibility(View.GONE);
            imageView.setImageResource(R.drawable.arrow_down);
        }
    }
    public void onClick_showapp1(View v) {
        TextView text = findViewById(R.id.text1);
        ImageView button = findViewById(R.id.button1);

        toggleTextViewVisibility(text,button);
    }

    public void onClick_showapp2(View v) {
        TextView text = findViewById(R.id.text2);
        ImageView button = findViewById(R.id.button2);

        toggleTextViewVisibility(text,button);
    }

    public void onClick_showapp3(View v) {
        TextView text = findViewById(R.id.text3);
        ImageView button = findViewById(R.id.button3);

        toggleTextViewVisibility(text,button);
    }

    public void onClick_showapp4(View v) {
        TextView text = findViewById(R.id.text4);
        ImageView button = findViewById(R.id.button4);

        toggleTextViewVisibility(text,button);
    }

    public void onclick_toCustomerService(View v) {
        startActivity(new Intent(HelpCenter.this, CustomerService.class));
    }
    @Override
    public void onBackPressed() {
        // Leave this method empty or add custom behavior
        // to handle the back button press event.
        // This will disable the default back button functionality.
    }
}