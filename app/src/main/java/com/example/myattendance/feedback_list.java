package com.example.myattendance;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class feedback_list extends ArrayAdapter<feedbackDetails> {

    private Activity context;
    private List<feedbackDetails> feedbackDetailsList;

    public feedback_list(Activity context,List<feedbackDetails> feedbackDetailsList){
        super(context,R.layout.feedback_layout,feedbackDetailsList);
        this.context = context;
        this.feedbackDetailsList = feedbackDetailsList;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View viewList = inflater.inflate(R.layout.feedback_layout,null,true);

        TextView txtAdminFeedbackGivenBy = viewList.findViewById(R.id.txtAdminFeedbackGivenBy);
        TextView txtAdminFeedbackCourse = viewList.findViewById(R.id.txtAdminFeedbackCourse);
        TextView txtAdminFeedbackRate = viewList.findViewById(R.id.txtAdminFeedbackRate);
        TextView txtAdminFeedbackDate = viewList.findViewById(R.id.txtAdminFeedbackDate);
        TextView txtAdminFeedbackTime = viewList.findViewById(R.id.txtAdminFeedbackTime);

        feedbackDetails feedbackDetails = feedbackDetailsList.get(position);

        txtAdminFeedbackGivenBy.setText(feedbackDetails.getUserEmail());
        txtAdminFeedbackCourse.setText(feedbackDetails.getCourseName());
        txtAdminFeedbackRate.setText(feedbackDetails.getRating());
        txtAdminFeedbackDate.setText(feedbackDetails.getFeedbackDate());
        txtAdminFeedbackTime.setText(feedbackDetails.getFeedbackTime());

        return viewList;
    }
}
