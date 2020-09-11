package com.mikami.aad2020leaderboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Toolbar mToolbar;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    PageAdapter mPageAdapter;
    TabItem learningTab;
    TabItem skillIQTab;
    TextView mTvSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(mToolbar);

        mToolbar = findViewById(R.id.toolbar);

        mTabLayout = findViewById(R.id.tabLayout);
        learningTab = findViewById(R.id.tabLearners);
        skillIQTab = findViewById(R.id.tabSkillIQ);
        mViewPager = findViewById(R.id.viewPager);
        mTvSubmit = findViewById(R.id.tvSubmit);

        mPageAdapter = new PageAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mPageAdapter);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTvSubmit.setClickable(true);
        mTvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubmitActivity.class);
                startActivity(intent);
            }
        });

    }

}
