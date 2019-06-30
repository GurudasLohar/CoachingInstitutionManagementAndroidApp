package com.example.myattendance;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackStudentFragment extends Fragment {

    private DatabaseReference databaseReference;
    private DatabaseReference referenceIN,referenceNew;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private String uid=null,uemail=null;

    Spinner spnFacultyNameList;
    TextView feedbackCourseName;
    RatingBar feedbackRatingBar;
    Button btnFeedback;

    String course=null,emailFaculty=null;
    private ArrayList<String> listFacultyName;

    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback_student, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("feedback");
        referenceIN = FirebaseDatabase.getInstance().getReference().child("user");
        referenceNew = FirebaseDatabase.getInstance().getReference().child("user");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseAuth.getCurrentUser().getUid();
        uemail = firebaseAuth.getCurrentUser().getEmail();

        feedbackCourseName = view.findViewById(R.id.feedbackCourseName);
        feedbackRatingBar = view.findViewById(R.id.feedbackRatingBar);
        spnFacultyNameList = view.findViewById(R.id.spnFacultyNameList);
        btnFeedback = view.findViewById(R.id.btnFeedback);

        listFacultyName = new ArrayList<>();
        final ArrayAdapter facultyListAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,listFacultyName);

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFeedback();
            }
        });

        referenceIN.orderByChild("email").equalTo(uemail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot tempData : dataSnapshot.getChildren()) {
                    course = tempData.child("course").getValue().toString();
                    feedbackCourseName.setText(course);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        referenceNew.orderByChild("role").equalTo("Faculty").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot tempData : dataSnapshot.getChildren()){
                    String tempFaculty = tempData.child("username").getValue().toString();
                    listFacultyName.add(tempFaculty);
                }
                spnFacultyNameList.setAdapter(facultyListAdapter);
                spnFacultyNameList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            emailFaculty = listFacultyName.get(position);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        return view;
    }

    private void submitFeedback() {

        String rate = String.valueOf(feedbackRatingBar.getRating());
        String date = DateFormat.getDateInstance().format(calendar.getTime());
        String time = simpleDateFormat.format(calendar.getTime());
        String id = databaseReference.push().getKey();

        feedbackDetails feedbackDetails = new feedbackDetails(uemail,course,date,time,rate,emailFaculty);
        databaseReference.child(id).setValue(feedbackDetails);
        Toast.makeText(getActivity(), "Thank you for your FEEDBACK", Toast.LENGTH_SHORT).show();
    }
}
