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
public class HomeStudentFragment extends Fragment {

    CardView attendanceCardview_std,enquiryCardview_std,feedbackCardview_std,aboutCardview_std;

    EnquiryFragment enquiryFragment_std;
    FeedbackStudentFragment feedbackStudentFragment;
    AttendanceStudentFragment attendanceStudentFragment;
    AboutUsFragment aboutUsFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_student, container, false);

        attendanceCardview_std = view.findViewById(R.id.attendanceCardview_std);
        enquiryCardview_std = view.findViewById(R.id.enquiryCardview_std);
        feedbackCardview_std = view.findViewById(R.id.feedbackCardview_std);
        aboutCardview_std = view.findViewById(R.id.aboutCardview_std);

        enquiryFragment_std = new EnquiryFragment();
        feedbackStudentFragment = new FeedbackStudentFragment();
        attendanceStudentFragment = new AttendanceStudentFragment();
        aboutUsFragment = new AboutUsFragment();

        attendanceCardview_std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(attendanceStudentFragment);
            }
        });

        enquiryCardview_std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(enquiryFragment_std);
            }
        });

        feedbackCardview_std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(feedbackStudentFragment);
            }
        });
        aboutCardview_std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(aboutUsFragment);
            }
        });
        return view;
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutProfile_std,fragment);
        fragmentTransaction.commit();
    }
}
