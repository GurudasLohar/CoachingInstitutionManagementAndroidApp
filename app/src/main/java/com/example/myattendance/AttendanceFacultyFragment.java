package com.example.myattendance;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
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
import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceFacultyFragment extends Fragment {

    private DatabaseReference databaseReference,databaseReferenceNew,databaseReferenceAttendance;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    Spinner spnCourseBatch;
    ListView listViewStudent;
    List<userDetails> studentDetailList;
    FloatingActionButton btnFloatingAction;

    String uid=null,uEmail=null;

    private ArrayList<String> courseList;

    String batchCourse=null;

    faculty_attendance_list adapter;
    ArrayList<String> presentStd;

    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_attendance_faculty, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("facultyTimetable");
        databaseReferenceNew = FirebaseDatabase.getInstance().getReference().child("user");
        databaseReferenceAttendance = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseAuth.getCurrentUser().getUid();
        uEmail = firebaseAuth.getCurrentUser().getEmail();

        courseList = new ArrayList<>();
        final ArrayAdapter courseAdapter1 = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,courseList);

        spnCourseBatch = view.findViewById(R.id.spnCourseBatch);
        listViewStudent=view.findViewById(R.id.listViewStudent);
        btnFloatingAction = view.findViewById(R.id.btnFloatingAction);

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        studentDetailList = new ArrayList<>();
        presentStd = new ArrayList<>();

        databaseReference.orderByChild("facultyName").equalTo(uEmail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                courseList.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    String temp = dataSnapshot1.child("facultyAllottedCourse").getValue().toString();
                    courseList.add(temp);
                }
                spnCourseBatch.setAdapter(courseAdapter1);
                spnCourseBatch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, final View view, int position, long id) {
                        batchCourse = courseList.get(position);

                        databaseReferenceNew.orderByChild("course").equalTo(batchCourse).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                studentDetailList.clear();
                                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                                    userDetails userDetails = dataSnapshot1.getValue(com.example.myattendance.userDetails.class);
                                    studentDetailList.add(userDetails);
                                }
                                adapter = new faculty_attendance_list(getActivity(),studentDetailList);
                                listViewStudent.setAdapter(adapter);
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

        btnFloatingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presentStd = adapter.attendanceStdList;

                for(String temp:presentStd){
                    String bDate = DateFormat.getDateInstance().format(calendar.getTime());
                    String bTime = simpleDateFormat.format(calendar.getTime());
                    String id = databaseReferenceAttendance.push().getKey();
                    String status = "present";
                    attendanceDetails attendanceDetails = new attendanceDetails(temp,batchCourse,bDate,bTime,status);
                    databaseReferenceAttendance.child("attendance").child(id).setValue(attendanceDetails);
                }

                Toast.makeText(getActivity(), "Present Students "+presentStd, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

