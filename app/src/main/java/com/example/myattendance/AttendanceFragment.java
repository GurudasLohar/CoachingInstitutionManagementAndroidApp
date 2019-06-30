package com.example.myattendance;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

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
public class AttendanceFragment extends Fragment {

    private DatabaseReference databaseReference;
    private DatabaseReference referenceIN;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private String uid=null,uEmail=null;

    Spinner spnStudentNameList;
    ListView listView_admin;
    List<attendanceDetails> stdList;

    String usernameStd=null;
    private ArrayList<String> listStudentName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);

        referenceIN = FirebaseDatabase.getInstance().getReference().child("user");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("attendance");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseAuth.getCurrentUser().getUid();
        uEmail = firebaseAuth.getCurrentUser().getEmail();


        spnStudentNameList = view.findViewById(R.id.spnStudentNameList);
        listView_admin = view.findViewById(R.id.listView_admin);
        stdList = new ArrayList<>();

        listStudentName = new ArrayList<>();
        final ArrayAdapter studentListAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,listStudentName);


        referenceIN.orderByChild("role").equalTo("Student").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot tempData : dataSnapshot.getChildren()){
                    String tempStudent = tempData.child("username").getValue().toString();
                    listStudentName.add(tempStudent);
                }
                spnStudentNameList.setAdapter(studentListAdapter);
                spnStudentNameList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        usernameStd = listStudentName.get(position);

                        databaseReference.orderByChild("studentName").equalTo(usernameStd).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                stdList.clear();
                                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    attendanceDetails temp = dataSnapshot1.getValue(com.example.myattendance.attendanceDetails.class);
                                    stdList.add(temp);
                                }
                                student_attendance_list adapter = new student_attendance_list(getActivity(),stdList);
                                listView_admin.setAdapter(adapter);
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
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
}
