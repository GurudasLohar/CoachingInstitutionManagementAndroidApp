package com.example.myattendance;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
public class TimetableFacultyFragment extends Fragment {

    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    ListView listView_timetable;
    List<facultyTimeTable> timeTableList;

    String uid=null,uEmail=null;

    @Override
    public void onStart() {
        super.onStart();

        databaseReference.orderByChild("facultyName").equalTo(uEmail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                timeTableList.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    facultyTimeTable facultyTimeTable = dataSnapshot1.getValue(com.example.myattendance.facultyTimeTable.class);
                    timeTableList.add(facultyTimeTable);
                }
                faculty_timetable_list adapter = new faculty_timetable_list(getActivity(),timeTableList);
                listView_timetable.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timetable_faculty, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("facultyTimetable");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseAuth.getCurrentUser().getUid();
        uEmail = firebaseAuth.getCurrentUser().getEmail();

        listView_timetable = view.findViewById(R.id.listView_timetable);
        timeTableList = new ArrayList<>();

        return view;
    }

}
