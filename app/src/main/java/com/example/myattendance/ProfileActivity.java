package com.example.myattendance;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    DrawerLayout drawerLayout;
    FrameLayout frameLayoutProfile;
    NavigationView drawerNavigation;
    ActionBarDrawerToggle drawerToggle;

    HomeFragment homeFragment;
    CalenderFragment calenderFragment;
    ProfileFragment profileFragment;
    CreateTimetableFragment createTimetableFragment;
    NotificationFragment notificationFragment;
    CheckEnquiryFragment checkEnquiryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        drawerLayout = findViewById(R.id.drawerLayout);
        drawerNavigation = findViewById(R.id.drawerNavigation);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        frameLayoutProfile = findViewById(R.id.frameLayoutProfile);
        drawerToggle = new ActionBarDrawerToggle(ProfileActivity.this,drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        homeFragment = new HomeFragment();
        calenderFragment = new CalenderFragment();
        profileFragment = new ProfileFragment();
        createTimetableFragment = new CreateTimetableFragment();
        notificationFragment = new NotificationFragment();
        checkEnquiryFragment = new CheckEnquiryFragment();
        setFragment(homeFragment);

        drawerNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if(menuItem.getItemId() == R.id.menu1){
                    setFragment(profileFragment);
                }
                if(menuItem.getItemId() == R.id.menu2){
                    setFragment(notificationFragment);
                }
                if(menuItem.getItemId() == R.id.menu3){
                    setFragment(checkEnquiryFragment);
                }
                if(menuItem.getItemId() == R.id.menu4){
                    setFragment(createTimetableFragment);
                }
                if(menuItem.getItemId() == R.id.menu5){
                    Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.bottom_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.bottom_calender:
                        setFragment(calenderFragment);
                        return true;
                    case R.id.bottom_profile:
                        setFragment(profileFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutProfile,fragment);
        fragmentTransaction.commit();
    }
}
