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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateTimetableFragment extends Fragment {

    private DatabaseReference databaseReference,datebaseRef;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    String courseName=null,uid=null;

    private Spinner spnFacultyName,spnTimeCourse;
    private ImageView timeSelectImage;
    TextView facultyTime;
    private Button btnTimetable;

    private ArrayList<String> facultyNameList;

    String facultyEmail=null,facultyCourseName=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_timetable, container, false);

        datebaseRef = FirebaseDatabase.getInstance().getReference().child("facultyTimetable");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseAuth.getCurrentUser().getUid();

        facultyNameList = new ArrayList<>();
        final ArrayAdapter facultyAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,facultyNameList);

        spnFacultyName = view.findViewById(R.id.spnFacultyName);
        spnTimeCourse = view.findViewById(R.id.spnTimeCourse);
        timeSelectImage = view.findViewById(R.id.timeSelectImage);
        facultyTime = view.findViewById(R.id.facultyTime);
        btnTimetable = view.findViewById(R.id.btnTimetable);

        databaseReference.orderByChild("role").equalTo("Faculty").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot tempData : dataSnapshot.getChildren()){
                    String tempFaculty = tempData.child("email").getValue().toString();
                    facultyNameList.add(tempFaculty);
                }
                spnFacultyName.setAdapter(facultyAdapter);
                spnFacultyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        facultyEmail = facultyNameList.get(position);
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

        final String[] studentCourses = getResources().getStringArray(R.array.student_courses);
        ArrayAdapter studentAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,studentCourses);
        spnTimeCourse.setAdapter(studentAdapter);
        spnTimeCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                facultyCourseName = studentCourses[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        timeSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timetableFragment = new TimePickerTimetableFragment();
                timetableFragment.show(getFragmentManager(),"Time_Picker");
            }
        });

        btnTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTimetable();
            }
        });
        return view;
    }

    private void saveTimetable() {

        String time = facultyTime.getText().toString();

        String id = datebaseRef.push().getKey();

        facultyTimeTable facultyTimeTable = new facultyTimeTable(facultyEmail,facultyCourseName,time);
        datebaseRef.child(id).setValue(facultyTimeTable);
        Toast.makeText(getActivity(), "Timetable Saved", Toast.LENGTH_SHORT).show();
    }
}
