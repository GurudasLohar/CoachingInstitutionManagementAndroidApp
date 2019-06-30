package com.example.myattendance;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class check_enquiry_list extends ArrayAdapter<enquiryDetails> {

    private Activity context;
    private List<enquiryDetails> enquiryDetailsList;

    public check_enquiry_list(Activity context,List<enquiryDetails> enquiryDetailsList){
        super(context,R.layout.check_enquiry_layout,enquiryDetailsList);
        this.context = context;
        this.enquiryDetailsList = enquiryDetailsList;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listItem = inflater.inflate(R.layout.check_enquiry_layout,null,true);

        TextView txtName = listItem.findViewById(R.id.txtName);
        TextView txtContact = listItem.findViewById(R.id.txtContact);
        TextView txtCourse = listItem.findViewById(R.id.txtCourse);
        TextView txtEnquiryBy = listItem.findViewById(R.id.txtEnquiryBy);

        enquiryDetails enquiryDetails = enquiryDetailsList.get(position);

        txtName.setText(enquiryDetails.getName());
        txtContact.setText(enquiryDetails.getPhoneNo());
        txtCourse.setText(enquiryDetails.getCourse());
        txtEnquiryBy.setText(enquiryDetails.getEnquiryBy());

        return listItem;
    }
}
