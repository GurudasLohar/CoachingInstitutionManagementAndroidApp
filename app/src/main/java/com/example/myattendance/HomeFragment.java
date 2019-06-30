package com.example.myattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String uid=null;

    CardView userCardview,attendanceCardview,enquiryCardview,feedbackCardview;

    EnquiryFragment enquiryFragment;
    AttendanceFragment attendanceFragment;
    FeedbackFragment feedbackFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseAuth.getCurrentUser().getUid();

        enquiryFragment = new EnquiryFragment();
        attendanceFragment = new AttendanceFragment();
        feedbackFragment = new FeedbackFragment();

        userCardview = view.findViewById(R.id.userCardview);
        attendanceCardview = view.findViewById(R.id.attendanceCardview);
        enquiryCardview = view.findViewById(R.id.enquiryCardview);
        feedbackCardview = view.findViewById(R.id.feedbackCardview);

        userCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateUser.class);
                startActivity(intent);
            }
        });

        attendanceCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(attendanceFragment);
            }
        });

        enquiryCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(enquiryFragment);
            }
        });
        feedbackCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(feedbackFragment);
            }
        });
        return view;
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutProfile,fragment);
        fragmentTransaction.commit();
    }
}