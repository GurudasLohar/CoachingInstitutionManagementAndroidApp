package com.example.myattendance;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnquiryFragment extends Fragment {
    
    private EditText enquiryName,enquiryPhone,enquiryBy;
    private Spinner spnEnquiryCourse;
    private RadioGroup enquiryRadioGroup;
    private RadioButton enquiryRadioButton;
    private ImageView enquiryDateImage;
    TextView enquiryDate;
    private Button btnEnquirySubmit;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String uid=null,uemail=null,enquiryCourseName=null;
    int roleId;

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_enquiry, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("enquiry");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseAuth.getCurrentUser().getUid();
        uemail = firebaseAuth.getCurrentUser().getEmail();
        
        enquiryName = view.findViewById(R.id.enquiryName);
        enquiryPhone = view.findViewById(R.id.enquiryPhone);
        enquiryBy = view.findViewById(R.id.enquiryBy);
        spnEnquiryCourse = view.findViewById(R.id.spnEnquiryCourse);
        enquiryRadioGroup = view.findViewById(R.id.enquiryRadioGroup);
        enquiryDate = view.findViewById(R.id.enquiryDate);
        enquiryDateImage = view.findViewById(R.id.enquiryDateImage);
        btnEnquirySubmit = view.findViewById(R.id.btnEnquirySubmit);

        enquiryBy.setText(uemail);

        final String[] studentCourses = getResources().getStringArray(R.array.student_courses);
        ArrayAdapter studentAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,studentCourses);

        final String[] facultyCourses = getResources().getStringArray(R.array.faculty_courses);
        ArrayAdapter facultyAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,facultyCourses);

        enquiryRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                roleId = enquiryRadioGroup.indexOfChild(view.findViewById(checkedId));
                switch (roleId){
                    case 0 :
                        final String[] studentCourses = getResources().getStringArray(R.array.student_courses);
                        ArrayAdapter studentAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,studentCourses);
                        spnEnquiryCourse.setAdapter(studentAdapter);
                        spnEnquiryCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                enquiryCourseName = studentCourses[position];
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                        break;
                    case 1:
                        final String[] facultyCourses = getResources().getStringArray(R.array.faculty_courses);
                        ArrayAdapter facultyAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,facultyCourses);
                        spnEnquiryCourse.setAdapter(facultyAdapter);
                        spnEnquiryCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                enquiryCourseName = facultyCourses[position];
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                        break;
                }
            }
        });
        enquiryDateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment enDialogFragment = new DatePickerFragmentEnquiry();
                enDialogFragment.show(getFragmentManager(),"Date");
            }
        });
        btnEnquirySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitEnquiry();
            }
        });
        return view;
    }

    private void submitEnquiry() {

        String name = enquiryName.getText().toString();
        String phone = enquiryPhone.getText().toString();
        String email = enquiryBy.getText().toString();
        int selectID = enquiryRadioGroup.getCheckedRadioButtonId();
        enquiryRadioButton = view.findViewById(selectID);
        String role = enquiryRadioButton.getText().toString();
        String date = enquiryDate.getText().toString();
        String id = databaseReference.push().getKey();

        enquiryDetails enquiryDetails = new enquiryDetails(name,phone,role,enquiryCourseName,date,email);
        databaseReference.child(id).setValue(enquiryDetails);
        Toast.makeText(getActivity(), "Enquiry Submitted", Toast.LENGTH_SHORT).show();
    }
}
