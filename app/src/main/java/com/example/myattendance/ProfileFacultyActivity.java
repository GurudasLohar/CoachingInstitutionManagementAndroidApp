package com.example.myattendance;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class ProfileFacultyActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation_flt;
    FrameLayout frameLayoutProfile_flt;

    HomeFacultyFragment homeFacultyFragment;
    CalenderFragment calenderFragment_flt;
    ProfileFragment profileFragment_flt;
    NotificationFacultyFragment notificationFacultyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_faculty);

        bottomNavigation_flt = findViewById(R.id.bottomNavigation_flt);
        frameLayoutProfile_flt = findViewById(R.id.frameLayoutProfile_flt);

        homeFacultyFragment = new HomeFacultyFragment();
        calenderFragment_flt = new CalenderFragment();
        profileFragment_flt = new ProfileFragment();
        notificationFacultyFragment = new NotificationFacultyFragment();

        setFragment(homeFacultyFragment);

        bottomNavigation_flt.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.bottom_home:
                        setFragment(homeFacultyFragment);
                        return true;
                    case R.id.bottom_calender:
                        setFragment(calenderFragment_flt);
                        return true;

                    case R.id.bottom_profile:
                        setFragment(profileFragment_flt);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutProfile_flt,fragment);
        fragmentTransaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menubar_faculty,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu1_flt:
                setFragment(homeFacultyFragment);
                return  true;

            case R.id.menu2_flt:
                setFragment(notificationFacultyFragment);
                return  true;

            case R.id.menu3_flt:
                Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(intent);
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }
}
