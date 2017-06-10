package com.example.king.xxxxx11;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText edtEmail, edtPass;
    Button btnSignin, btnNextregister;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() !=null){
            //prifile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), Profile.class));
        }

        edtEmail = (EditText) findViewById(R.id.editEmailLogin);
        edtPass = (EditText) findViewById(R.id.editPasswordLogin);
        btnSignin = (Button) findViewById(R.id.buttonSignin);
        btnNextregister = (Button) findViewById(R.id.btnNextRegister);


        //attaching click listener
        btnSignin.setOnClickListener(this);
        btnNextregister.setOnClickListener(this);

    }
    //method for user login
    public void userLogin(){
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();
        if (TextUtils.isEmpty(email)){
            //email is empety
            Toast.makeText(this, "please enter email..", Toast.LENGTH_SHORT).show();
            //stoping a function excecution further
            return;
        }
        if (TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "please enter password..", Toast.LENGTH_SHORT).show();
            //stoping a function excecution further
            return;
        }
        progressDialog.setMessage("Register User..");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), Profile.class));

                        }

                    }
                });
    }
    @Override
    public void onClick(View view) {
        if (view == btnSignin){
            userLogin();
        }
        if (view == btnNextregister){
            finish();
            startActivity(new Intent(this, MainActivity.class));
            //will open login activity
        }
    }
}
