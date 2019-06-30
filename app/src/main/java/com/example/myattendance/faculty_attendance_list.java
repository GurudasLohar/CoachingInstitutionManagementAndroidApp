
package com.example.myattendance;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class faculty_attendance_list extends ArrayAdapter<userDetails> {

    private Activity context;
    private List<userDetails> studentList;
    ArrayList<String> attendanceStdList = new ArrayList<>();

    public faculty_attendance_list(Activity context,List<userDetails> studentList){
        super(context,R.layout.faculty_attendance_layout,studentList);
        this.context = context;
        this.studentList = studentList;
    }

    private  class viewHolder{
        TextView tempName;
        TextView tempTime;
        CheckBox tempStatus;
    }


    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        final TextView txtStudentName,txtStudentTime;
        CheckBox studentStatus;

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.faculty_attendance_layout,null,true);

        txtStudentName = listViewItem.findViewById(R.id.txtStudentName);
        txtStudentTime = listViewItem.findViewById(R.id.txtStudentTime);
        studentStatus = listViewItem.findViewById(R.id.studentStatus);

        userDetails userDetails = studentList.get(position);

        txtStudentName.setText(userDetails.getUsername());
        txtStudentTime.setText(userDetails.getBatchTime());

        studentStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(buttonView.isChecked()){
                    attendanceStdList.add(txtStudentName.getText().toString());
                }else {
                    attendanceStdList.remove(txtStudentName.getText().toString());
                }
            }
        });

        return listViewItem;
    }
}