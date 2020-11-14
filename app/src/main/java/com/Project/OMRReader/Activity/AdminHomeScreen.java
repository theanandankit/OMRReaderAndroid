package com.Project.OMRReader.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.Project.OMRReader.Fragments.HomeAdminFragment;
import com.Project.OMRReader.Fragments.ProfileAdminFragment;
import com.Project.OMRReader.Fragments.QuizsAdminFragment;
import com.Project.OMRReader.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminHomeScreen extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_screen);
        init();
        setBottomNavigationView();
    }

    private void init(){
        bottomNavigationView = findViewById(R.id.bottom);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new HomeAdminFragment()).commit();
    }
    private void setBottomNavigationView(){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectFragment =null;

                switch (item.getItemId()){
                    case R.id.home:
                        selectFragment = new HomeAdminFragment();
                        break;
                    case R.id.quiz:
                        selectFragment = new QuizsAdminFragment();
                        break;
                    case R.id.profile:
                        selectFragment = new ProfileAdminFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,selectFragment).commit();
                return true;
            }
        });
    }
}