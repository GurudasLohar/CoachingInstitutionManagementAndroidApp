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

public class ProfileStudentActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation_std;
    FrameLayout frameLayoutProfile_std;

    HomeStudentFragment homeStudentFragment;
    CalenderFragment calenderFragment_std;
    ProfileFragment profileFragment_std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student);

        bottomNavigation_std = findViewById(R.id.bottomNavigation_std);
        frameLayoutProfile_std = findViewById(R.id.frameLayoutProfile_std);

        homeStudentFragment = new HomeStudentFragment();
        calenderFragment_std = new CalenderFragment();
        profileFragment_std = new ProfileFragment();

        setFragment(homeStudentFragment);

        bottomNavigation_std.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.bottom_home:
                        setFragment(homeStudentFragment);
                        return true;
                    case R.id.bottom_calender:
                        setFragment(calenderFragment_std);
                        return true;
                    case R.id.bottom_profile:
                        setFragment(profileFragment_std);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutProfile_std,fragment);
        fragmentTransaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menubar,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu1_id:
                setFragment(homeStudentFragment);
                return  true;

            case R.id.menu2_id:
                Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(intent);
                return true;

                default: return super.onOptionsItemSelected(item);
        }
    }
}
