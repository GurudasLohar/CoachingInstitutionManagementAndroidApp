package com.example.myattendance;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class faculty_notification_list extends ArrayAdapter<meetingDetails> {

    private Activity context;
    private List<meetingDetails> notificationList;

    public faculty_notification_list(Activity context,List<meetingDetails> notificationList){
        super(context,R.layout.faculty_notification_layout,notificationList);
        this.context = context;
        this.notificationList = notificationList;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.faculty_notification_layout,null,true);

        TextView txtMeetAgenda = listViewItem.findViewById(R.id.txtMeetAgenda);
        TextView txtMeetDate = listViewItem.findViewById(R.id.txtMeetDate);
        TextView txtMeetTime = listViewItem.findViewById(R.id.txtMeetTime);

        meetingDetails meetingDetails = notificationList.get(position);

        txtMeetAgenda.setText(meetingDetails.getMeetingAgenda());
        txtMeetDate.setText(meetingDetails.getMeetingDate());
        txtMeetTime.setText(meetingDetails.getMeetingTime());

        return listViewItem;
    }
}
