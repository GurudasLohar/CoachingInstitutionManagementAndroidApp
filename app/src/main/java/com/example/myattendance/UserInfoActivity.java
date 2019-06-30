package com.example.myattendance;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserInfoActivity extends AppCompatActivity {

    private EditText userEmail,userName,userPhone,userPassword;
    private ImageView dateImage,timeImage;
    TextView userDate,userTime;
    private Button btnSubmit;
    private Spinner spnCourse;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    int roleId;
    String courseName=null,uid=null;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseAuth.getCurrentUser().getUid();

        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);
        userName = findViewById(R.id.userName);
        userPhone = findViewById(R.id.userPhone);
        radioGroup = findViewById(R.id.radioGroup);
        dateImage = findViewById(R.id.dateImage);
        userDate = findViewById(R.id.userDate);
        timeImage = findViewById(R.id.timeImage);
        userTime = findViewById(R.id.userTime);

        btnSubmit = findViewById(R.id.btnSubmit);
        spnCourse = findViewById(R.id.spnCourse);

        final String[] studentCourses = getResources().getStringArray(R.array.student_courses);
        ArrayAdapter studentAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,studentCourses);

        final String[] facultyCourses = getResources().getStringArray(R.array.faculty_courses);
        ArrayAdapter facultyAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,facultyCourses);

        Intent in = getIntent();
        String Email = in.getStringExtra("Email");
        String Password = in.getStringExtra("Password");
        userEmail.setText(Email);
        userPassword.setText(Password);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               roleId = radioGroup.indexOfChild(findViewById(checkedId));
               switch (roleId){
                   case 0:
                       final String[] studentCourses = getResources().getStringArray(R.array.student_courses);
                       ArrayAdapter studentAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,studentCourses);
                       spnCourse.setAdapter(studentAdapter);
                       spnCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                           @Override
                           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                               courseName = studentCourses[position];
                           }
                           @Override
                           public void onNothingSelected(AdapterView<?> parent) {
                           }
                       });
                       break;
                   case 1:
                       final String[] facultyCourses = getResources().getStringArray(R.array.faculty_courses);
                       ArrayAdapter facultyAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,facultyCourses);
                       spnCourse.setAdapter(facultyAdapter);
                       spnCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                           @Override
                           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                               courseName = facultyCourses[position];
                           }
                           @Override
                           public void onNothingSelected(AdapterView<?> parent) {
                           }
                       });
                       break;
                   case 2:
                       final String[] admin = getResources().getStringArray(R.array.admin);
                       ArrayAdapter adminAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,admin);
                       spnCourse.setAdapter(adminAdapter);
                       spnCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                           @Override
                           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                               courseName = admin[position];
                           }
                           @Override
                           public void onNothingSelected(AdapterView<?> parent) {
                           }
                       });
                       break;
               }
            }
        });
        dateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dateFragment = new DatePickerFragment();
                dateFragment.show(getSupportFragmentManager(),"DatePicker");
            }
        });
        timeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timeFragment = new TimePickerFragment();
                timeFragment.show(getSupportFragmentManager(),"TimePicker");
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInformation();
            }
        });
    }

    private void userInformation() {

        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();
        String name = userName.getText().toString();
        String phone = userPhone.getText().toString();
        int selectID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(selectID);
        String role = radioButton.getText().toString();
        String joinDate = userDate.getText().toString();
        String batchTime = userTime.getText().toString();

        userDetails userdetails = new userDetails(email,password,name,phone,role,courseName,joinDate,batchTime);
        databaseReference.child(uid).setValue(userdetails);
        Toast.makeText(getApplicationContext(), "Information Submitted Successfully", Toast.LENGTH_SHORT).show();
    }
}