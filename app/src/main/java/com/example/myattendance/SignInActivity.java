package com.example.myattendance;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtSignInOne,txtSignInTwo;
    private Button btnSignInOne;

    private DatabaseReference referenceIN;
    private FirebaseAuth authIN;
    private FirebaseUser firebaseUser;
    String uid=null;
    String userRole=null;
    String emailIN=null,passwordIN=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();

        referenceIN = FirebaseDatabase.getInstance().getReference().child("user");
        authIN = FirebaseAuth.getInstance();

        txtSignInOne = findViewById(R.id.txtSignInOne);
        txtSignInTwo = findViewById(R.id.txtSignInTwo);
        btnSignInOne = findViewById(R.id.btnSignInOne);

        btnSignInOne.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnSignInOne:
                userLogin();
                break;
        }
    }
    private void userLogin() {

        emailIN = txtSignInOne.getText().toString().trim();
        passwordIN = txtSignInTwo.getText().toString().trim();

        if(emailIN.isEmpty()){
            txtSignInOne.setError("Please,enter email address");
            txtSignInOne.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailIN).matches()){
            txtSignInOne.setError("Please,enter valid email address");
            txtSignInOne.requestFocus();
            return;
        }

        if(passwordIN.isEmpty()){
            txtSignInTwo.setError("Please,enter password");
            txtSignInTwo.requestFocus();
            return;
        }
        if(passwordIN.length()<5){
            txtSignInTwo.setError("Minimum length of password shuold be 5");
            txtSignInTwo.requestFocus();
            return;
        }

        referenceIN.orderByChild("email").equalTo(emailIN).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot tempData : dataSnapshot.getChildren()){
                            userRole = tempData.child("role").getValue().toString();

                            if(userRole.equals("Student")){
                                authIN.signInWithEmailAndPassword(emailIN,passwordIN)
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if(task.isSuccessful()){
                                                    Intent intent = new Intent(getApplicationContext(),ProfileStudentActivity.class);
                                                    startActivity(intent);
                                                    Toast.makeText(SignInActivity.this, "Sign_In Successfully", Toast.LENGTH_SHORT).show();
                                                }else {
                                                    Toast.makeText(SignInActivity.this, "SignIn Failed", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        if(userRole.equals("Faculty")){
                            authIN.signInWithEmailAndPassword(emailIN,passwordIN)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                Intent intent = new Intent(getApplicationContext(),ProfileFacultyActivity.class);
                                                startActivity(intent);
                                                Toast.makeText(SignInActivity.this, "Sign_In Successfully", Toast.LENGTH_SHORT).show();
                                            }else {
                                                Toast.makeText(SignInActivity.this, "SignIn Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                        if(userRole.equals("Admin")){
                            authIN.signInWithEmailAndPassword(emailIN,passwordIN)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                                                startActivity(intent);
                                                Toast.makeText(SignInActivity.this, "Sign_In Successfully", Toast.LENGTH_SHORT).show();
                                            }else {
                                                Toast.makeText(SignInActivity.this, "SignIn Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
