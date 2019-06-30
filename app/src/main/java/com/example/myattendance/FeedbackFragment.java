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
public class FeedbackFragment extends Fragment {


    private DatabaseReference databaseReference;
    private DatabaseReference referenceIN;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private String uid=null,uEmail=null;

    Spinner spnFacultyFeedbackList;
    ListView listViewFeedback_admin;
    List<feedbackDetails> feedbackList;

    String facultyName=null;
    private ArrayList<String> listFacultyName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        referenceIN = FirebaseDatabase.getInstance().getReference().child("user");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("feedback");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseAuth.getCurrentUser().getUid();
        uEmail = firebaseAuth.getCurrentUser().getEmail();

        spnFacultyFeedbackList = view.findViewById(R.id.spnFacultyFeedbackList);
        listViewFeedback_admin = view.findViewById(R.id.listViewFeedback_admin);
        feedbackList = new ArrayList<>();

        listFacultyName = new ArrayList<>();
        final ArrayAdapter facultyListAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,listFacultyName);


        referenceIN.orderByChild("role").equalTo("Faculty").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot tempData : dataSnapshot.getChildren()){
                    String tempStudent = tempData.child("username").getValue().toString();
                    listFacultyName.add(tempStudent);
                }
                spnFacultyFeedbackList.setAdapter(facultyListAdapter);
                spnFacultyFeedbackList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        facultyName = listFacultyName.get(position);

                        databaseReference.orderByChild("facultyEmail").equalTo(facultyName).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                feedbackList.clear();
                                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    feedbackDetails details = dataSnapshot1.getValue(feedbackDetails.class);
                                    feedbackList.add(details);
                                }
                                feedback_list adapter = new feedback_list(getActivity(),feedbackList);
                                listViewFeedback_admin.setAdapter(adapter);
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



        return  view;
    }
}
