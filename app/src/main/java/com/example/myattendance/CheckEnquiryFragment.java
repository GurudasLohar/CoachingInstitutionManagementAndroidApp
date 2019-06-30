package com.example.myattendance;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
public class CheckEnquiryFragment extends Fragment {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    String datePick=null,uid=null;

    private ImageView dateImageEC;
    TextView txtEnquiryDateCheck;
    Button btnCheckEnquiry;


    ListView listView_enquiry;
    List<enquiryDetails> enquiryList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_enquiry, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("enquiry");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseAuth.getCurrentUser().getUid();

        listView_enquiry =view.findViewById(R.id.listView_enquiry);
        dateImageEC = view.findViewById(R.id.dateImageEC);
        txtEnquiryDateCheck = view.findViewById(R.id.txtEnquiryDateCheck);
        btnCheckEnquiry = view.findViewById(R.id.btnCheckEnquiry);

        enquiryList = new ArrayList<>();


        dateImageEC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment1 = new DatePickerFragmentEnquiryCheck();
                fragment1.show(getFragmentManager(),"date_Enquiry");
            }
        });

        btnCheckEnquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePick = txtEnquiryDateCheck.getText().toString();

                databaseReference.orderByChild("enquiryDate").equalTo(datePick).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        enquiryList.clear();
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                            enquiryDetails enquiryDetails = dataSnapshot1.getValue(com.example.myattendance.enquiryDetails.class);
                            enquiryList.add(enquiryDetails);
                        }
                        check_enquiry_list adapter = new check_enquiry_list(getActivity(),enquiryList);
                        listView_enquiry.setAdapter(adapter);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });




        return view;
    }
}
