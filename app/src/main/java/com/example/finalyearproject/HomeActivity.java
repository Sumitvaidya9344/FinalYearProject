package com.example.finalyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.homeBottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.homeBottomNavigationMenuHome);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.homeMenuMyOffers)
        {

        } else if (item.getItemId() == R.id.homeMenuMyCart) {

        } else if (item.getItemId() == R.id.homeMenuMyProfile) {
            Intent intent = new Intent(HomeActivity.this, MyProfileActivity.class);
            startActivity(intent);
        }

        return true;
    }

    HomeFragment homeFragment = new HomeFragment();
    PersonalnfoFragment personalnfoFragment = new PersonalnfoFragment();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.homeBottomNavigationMenuHome)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,homeFragment).commit();
        } else if (item.getItemId() == R.id.homeBottomNavigationMenuPersonalInfo)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,personalnfoFragment).commit();
        }
        return true;
    }
}