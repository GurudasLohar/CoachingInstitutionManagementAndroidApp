package com.example.myattendance;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class faculty_feedback_list extends ArrayAdapter<feedbackDetails> {

    private Activity context;
    private List<feedbackDetails> feedbackList;

    public faculty_feedback_list(Activity context,List<feedbackDetails> feedbackList){
        super(context,R.layout.faculty_feedback_layout,feedbackList);
     this.context = context;
     this.feedbackList = feedbackList;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.faculty_feedback_layout,null,true);

        TextView txtFeedbackGivenBy = listViewItem.findViewById(R.id.txtFeedbackGivenBy);
        TextView txtFeedbackCourse = listViewItem.findViewById(R.id.txtFeedbackCourse);
        TextView txtFeedbackRate = listViewItem.findViewById(R.id.txtFeedbackRate);
        TextView txtFeedbackDate = listViewItem.findViewById(R.id.txtFeedbackDate);
        TextView txtFeedbackTime = listViewItem.findViewById(R.id.txtFeedbackTime);

        feedbackDetails feedbackDetails = feedbackList.get(position);

        txtFeedbackGivenBy.setText(feedbackDetails.getUserEmail());
        txtFeedbackCourse.setText(feedbackDetails.getCourseName());
        txtFeedbackRate.setText(feedbackDetails.getRating());
        txtFeedbackDate.setText(feedbackDetails.getFeedbackDate());
        txtFeedbackTime.setText(feedbackDetails.getFeedbackTime());

        return listViewItem;
    }
}
