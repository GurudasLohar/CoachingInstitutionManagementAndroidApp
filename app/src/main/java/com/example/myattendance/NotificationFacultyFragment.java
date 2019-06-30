package com.example.myattendance;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class NotificationFacultyFragment extends Fragment {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private String uid=null,uEmail=null;

    ListView listView_notification;
    List<meetingDetails> meetingDetailsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_faculty, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("meeting");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseAuth.getCurrentUser().getUid();
        uEmail = firebaseAuth.getCurrentUser().getEmail();

        listView_notification = view.findViewById(R.id.listView_notification);
        meetingDetailsList = new ArrayList<>();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                meetingDetailsList.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    meetingDetails meetingDetails = dataSnapshot1.getValue(com.example.myattendance.meetingDetails.class);
                    meetingDetailsList.add(meetingDetails);
                }
                faculty_notification_list adapter = new faculty_notification_list(getActivity(),meetingDetailsList);
                listView_notification.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
