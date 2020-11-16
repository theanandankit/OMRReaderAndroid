package com.Project.OMRReader.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.Project.OMRReader.Adapters.QuizDetailsTabLayoutAdapter;
import com.Project.OMRReader.R;
import com.google.android.material.tabs.TabLayout;

public class QuizDetailsScreen extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    QuizDetailsTabLayoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_details_screen);
        init();
        setAdapter();

    }

    private void init(){
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);
        adapter = new QuizDetailsTabLayoutAdapter(getSupportFragmentManager());
    }
    private void setAdapter(){
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}