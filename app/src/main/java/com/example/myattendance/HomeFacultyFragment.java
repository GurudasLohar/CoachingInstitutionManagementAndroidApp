package com.example.myattendance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFacultyFragment extends Fragment {

    CardView attendanceCardview_flt,enquiryCardview_flt,feedbackCardview_flt,timetableCardview_flt;

    EnquiryFragment enquiryFragment_flt;
    AttendanceFacultyFragment attendanceFacultyFragment;
    TimetableFacultyFragment timetableFacultyFragment;
    FeedbackFacultyFragment feedbackFacultyFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_faculty, container, false);

        attendanceCardview_flt = view.findViewById(R.id.attendanceCardview_flt);
        enquiryCardview_flt = view.findViewById(R.id.enquiryCardview_flt);
        feedbackCardview_flt = view.findViewById(R.id.feedbackCardview_flt);
        timetableCardview_flt = view.findViewById(R.id.timetableCardview_flt);

        enquiryFragment_flt = new EnquiryFragment();
        attendanceFacultyFragment = new AttendanceFacultyFragment();
        timetableFacultyFragment = new TimetableFacultyFragment();
        feedbackFacultyFragment = new FeedbackFacultyFragment();

        enquiryCardview_flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(enquiryFragment_flt);
            }
        });
        attendanceCardview_flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(attendanceFacultyFragment);
            }
        });
        timetableCardview_flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(timetableFacultyFragment);
            }
        });
        feedbackCardview_flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(feedbackFacultyFragment);
            }
        });
        return view;
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutProfile_flt,fragment);
        fragmentTransaction.commit();
    }
}
