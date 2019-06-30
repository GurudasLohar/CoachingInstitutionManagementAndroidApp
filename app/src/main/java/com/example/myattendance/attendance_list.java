package com.example.myattendance;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class attendance_list extends ArrayAdapter<attendanceDetails> {

    private Activity context;
    private List<attendanceDetails> stdList;

    public attendance_list(Activity context,List<attendanceDetails> stdList){
        super(context,R.layout.attendance_layout,stdList);
        this.context = context;
        this.stdList = stdList;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View lstView = inflater.inflate(R.layout.attendance_layout,null,true);

        TextView txtAttendCourseAdmin = lstView.findViewById(R.id.txtAttendCourseAdmin);
        TextView txtAttendDateAdmin = lstView.findViewById(R.id.txtAttendDateAdmin);
        TextView txtAttendTimeAdmin = lstView.findViewById(R.id.txtAttendTimeAdmin);

        attendanceDetails attendanceDetails = stdList.get(position);

        txtAttendCourseAdmin.setText(attendanceDetails.getBatchCourse());
        txtAttendDateAdmin.setText(attendanceDetails.getBatchAttendDate());
        txtAttendTimeAdmin.setText(attendanceDetails.getBatchAttendTime());

        return lstView;
    }
}
