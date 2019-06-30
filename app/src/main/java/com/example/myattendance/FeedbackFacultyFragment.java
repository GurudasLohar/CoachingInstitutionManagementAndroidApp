package com.example.myattendance;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

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
public class FeedbackFacultyFragment extends Fragment {

    private DatabaseReference databaseReference,databaseReferenceNew;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private String uid=null,uEmail=null;

    ListView listView_feedback;
    List<feedbackDetails> courseFeedbackList;
    String facultyUserName=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback_faculty, container, false);

        databaseReferenceNew = FirebaseDatabase.getInstance().getReference().child("user");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("feedback");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseAuth.getCurrentUser().getUid();
        uEmail = firebaseAuth.getCurrentUser().getEmail();

        listView_feedback = view.findViewById(R.id.listView_feedback);
        courseFeedbackList = new ArrayList<>();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseReferenceNew.orderByChild("email").equalTo(uEmail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                   facultyUserName = dataSnapshot1.child("username").getValue().toString();

                    databaseReference.orderByChild("facultyEmail").equalTo(facultyUserName).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            courseFeedbackList.clear();
                            for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                                feedbackDetails feedbackDetails = dataSnapshot1.getValue(com.example.myattendance.feedbackDetails.class);
                                courseFeedbackList.add(feedbackDetails);
                            }
                            faculty_feedback_list adapter = new faculty_feedback_list(getActivity(),courseFeedbackList);
                            listView_feedback.setAdapter(adapter);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
