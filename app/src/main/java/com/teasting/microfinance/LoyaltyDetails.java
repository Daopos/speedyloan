package com.teasting.microfinance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class LoyaltyDetails extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        // Leave this method empty or add custom behavior
        // to handle the back button press event.
        // This will disable the default back button functionality.
    }
    TabLayout tabLayout3;
    ViewPager2 viewPager3;
    ViewPagerAdapter2 viewPagerAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loyalty_details);
        getSupportActionBar().hide();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }

            tabLayout3 = findViewById(R.id.tabLayout3);
            viewPager3 = findViewById(R.id.viewPager3);
            viewPagerAdapter2 = new ViewPagerAdapter2(this);
            viewPager3.setAdapter(viewPagerAdapter2);
            tabLayout3.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager3.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            viewPager3.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    tabLayout3.getTabAt(position).select();
                }
            });

        }

        public void onclick_toProfile(View view) {
            startActivity(new Intent(LoyaltyDetails.this, Profile.class));
        }






    }
