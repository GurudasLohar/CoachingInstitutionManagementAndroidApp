package com.example.myattendance;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String uid=null,uEmail=null;

    TextView currentUserName,currentUserEmail;
    Button btnCurrentUser;
    TextView currentUserPhone,currentUserPassword,currentUserCourse,currentUserDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseAuth.getCurrentUser().getUid();
        uEmail = firebaseAuth.getCurrentUser().getEmail();

        currentUserName = view.findViewById(R.id.currentUserName);
        currentUserEmail = view.findViewById(R.id.currentUserEmail);
        btnCurrentUser = view.findViewById(R.id.btnCurrentUser);
        currentUserPhone = view.findViewById(R.id.currentUserPhone);
        currentUserPassword = view.findViewById(R.id.currentUserPassword);
        currentUserCourse = view.findViewById(R.id.currentUserCourse);
        currentUserDate = view.findViewById(R.id.currentUserDate);

        databaseReference.orderByChild("email").equalTo(uEmail)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                            String name = dataSnapshot1.child("username").getValue().toString();
                            String email = dataSnapshot1.child("email").getValue().toString();
                            String phone = dataSnapshot1.child("phoneNo").getValue().toString();
                            String password = dataSnapshot1.child("password").getValue().toString();
                            String course = dataSnapshot1.child("course").getValue().toString();
                            String date = dataSnapshot1.child("joinDate").getValue().toString();

                            currentUserName.setText(name);
                            currentUserEmail.setText(email);
                            currentUserPhone.setText(phone);
                            //currentUserPhone.setEnabled(false);
                            currentUserPassword.setText(password);
                            //currentUserPassword.setEnabled(false);
                            currentUserCourse.setText(course);
                            //currentUserCourse.setEnabled(false);
                            currentUserDate.setText(date);
                            //currentUserDate.setEnabled(false);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

        btnCurrentUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SignInActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
