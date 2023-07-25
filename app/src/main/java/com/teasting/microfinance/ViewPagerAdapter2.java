package com.teasting.microfinance;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter2 extends FragmentStateAdapter {
    public ViewPagerAdapter2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            case 0: return new ClassicFragment();
            case 1: return new SilverFragment();
            case 2: return new GoldFragment();
            default: return new ClassicFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
