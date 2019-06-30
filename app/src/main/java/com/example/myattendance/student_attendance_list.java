package com.example.myattendance;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class student_attendance_list extends ArrayAdapter<attendanceDetails> {

    private Activity context;
    private List<attendanceDetails> attendStdList;

    public student_attendance_list(Activity context, List<attendanceDetails> attendStdList){
        super(context,R.layout.student_attendance_layout,attendStdList);
        this.context = context;
        this.attendStdList = attendStdList;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listItem = inflater.inflate(R.layout.student_attendance_layout,null,true);

        TextView txtAttendCourse = listItem.findViewById(R.id.txtAttendCourse);
        TextView txtAttendDate = listItem.findViewById(R.id.txtAttendDate);
        TextView txtAttendTime = listItem.findViewById(R.id.txtAttendTime);

        attendanceDetails attendanceDetails = attendStdList.get(position);

        txtAttendCourse.setText(attendanceDetails.getBatchCourse());
        txtAttendDate.setText(attendanceDetails.getBatchAttendDate());
        txtAttendTime.setText(attendanceDetails.getBatchAttendTime());

        return listItem;
    }
}
