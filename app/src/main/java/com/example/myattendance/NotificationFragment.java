package com.example.myattendance;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.Base64Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    private ImageView dateImageM,timeImageM;
    TextView txtMeetingAgenda,txtMeetingDate,txtMeetingTime;
    Button btnNotify;

    String uid=null;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("meeting");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseAuth.getCurrentUser().getUid();

        txtMeetingAgenda = view.findViewById(R.id.txtMeetingAgenda);
        txtMeetingDate = view.findViewById(R.id.txtMeetingDate);
        txtMeetingTime = view.findViewById(R.id.txtMeetingTime);
        dateImageM = view.findViewById(R.id.dateImageM);
        timeImageM = view.findViewById(R.id.timeImageM);
        btnNotify = view.findViewById(R.id.btnNotify);

        dateImageM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragmentMeeting();
                dialogFragment.show(getFragmentManager(),"Date_Meeting");
            }
        });

        timeImageM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new TimePickerFragmentMeeting();
                fragment.show(getFragmentManager(),"Time_Meeting");
            }
        });

        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyMeeting();
            }
        });

        return view;
    }

    private void notifyMeeting() {

        String mAgenda = txtMeetingAgenda.getText().toString();
        String mDate = txtMeetingDate.getText().toString();
        String mTime = txtMeetingTime.getText().toString();

        String id = databaseReference.push().getKey();

        meetingDetails  meetingDetails = new meetingDetails(mAgenda,mDate,mTime);
        databaseReference.child(id).setValue(meetingDetails);
        Toast.makeText(getActivity(), "Meeting Details Saved", Toast.LENGTH_SHORT).show();
    }

}
