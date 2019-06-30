package com.example.myattendance;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateUser extends AppCompatActivity implements View.OnClickListener{

    private EditText txtSignUpOne,txtSignUpTwo;
    private Button btnSignUpOne;

    private DatabaseReference referenceUP;
    private FirebaseAuth authUP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        referenceUP = FirebaseDatabase.getInstance().getReference();
        authUP = FirebaseAuth.getInstance();

        txtSignUpOne = findViewById(R.id.txtSignUpOne);
        txtSignUpTwo = findViewById(R.id.txtSignUpTwo);
        btnSignUpOne = findViewById(R.id.btnSignUpOne);
        btnSignUpOne.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnSignUpOne:
                userRegister();
                break;
        }
    }

    private void userRegister() {

        final String email = txtSignUpOne.getText().toString().trim();
        final String password = txtSignUpTwo.getText().toString().trim();

        if(email.isEmpty()){
            txtSignUpOne.setError("Please,enter email address");
            txtSignUpOne.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtSignUpOne.setError("Please,enter valid email address");
            txtSignUpOne.requestFocus();
            return;
        }

        if(password.isEmpty()){
            txtSignUpTwo.setError("Please,enter password");
            txtSignUpTwo.requestFocus();
            return;
        }
        if(password.length()<5){
            txtSignUpTwo.setError("Minimum length of password shuold be 5");
            txtSignUpTwo.requestFocus();
            return;
        }

        authUP.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CreateUser.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),UserInfoActivity.class);
                            intent.putExtra("Email",email);
                            intent.putExtra("Password",password);
                            startActivity(intent);
                        }else {
                            Toast.makeText(CreateUser.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
