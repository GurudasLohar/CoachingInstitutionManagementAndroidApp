package com.example.myattendance;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.security.PrivateKey;
import java.util.List;

public class faculty_timetable_list extends ArrayAdapter<facultyTimeTable> {

    private Activity context;
    private List<facultyTimeTable> timeTableList;

    public faculty_timetable_list(Activity context,List<facultyTimeTable> timeTableList){
        super(context,R.layout.faculty_timetable_layout,timeTableList);
        this.context = context;
        this.timeTableList = timeTableList;
    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listviewItem = inflater.inflate(R.layout.faculty_timetable_layout,null,true);

        TextView txtCourseName = listviewItem.findViewById(R.id.txtCourseName);
        TextView txtCourseTime = listviewItem.findViewById(R.id.txtCourseTime);

        facultyTimeTable facultyTimeTable = timeTableList.get(position);
        txtCourseName.setText(facultyTimeTable.getFacultyAllottedCourse());
        txtCourseTime.setText(facultyTimeTable.getCourseTiming());
        return listviewItem;
    }
}
