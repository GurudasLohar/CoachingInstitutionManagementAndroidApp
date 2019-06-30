package com.example.myattendance;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceStudentFragment extends Fragment {


    private DatabaseReference databaseReference,databaseReferenceAttendance;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    String uid=null,uEmail=null,userName=null;

    ListView listView_student;
    List<attendanceDetails> detailsList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attendance_student, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        databaseReferenceAttendance = FirebaseDatabase.getInstance().getReference().child("attendance");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseAuth.getCurrentUser().getUid();
        uEmail = firebaseAuth.getCurrentUser().getEmail();

        listView_student = view.findViewById(R.id.listView_student);
        detailsList = new ArrayList<>();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseReference.orderByChild("email").equalTo(uEmail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot tempData : dataSnapshot.getChildren()) {
                    userName = tempData.child("username").getValue().toString();
                }
                databaseReferenceAttendance.orderByChild("studentName").equalTo(userName).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        detailsList.clear();
                            for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                attendanceDetails temp = dataSnapshot1.getValue(com.example.myattendance.attendanceDetails.class);
                                detailsList.add(temp);
                        }
                        student_attendance_list adapter = new student_attendance_list(getActivity(),detailsList);
                        listView_student.setAdapter(adapter);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
