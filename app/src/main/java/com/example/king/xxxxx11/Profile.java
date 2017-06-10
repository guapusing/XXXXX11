package com.example.king.xxxxx11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    //firebase auth object
    private FirebaseAuth firebaseAuth;
    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private ImageButton btnKuliah, btnUjian, btnUpoadnilai, btnInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
//initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, MainActivity.class));
        }
        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        //initializing views
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        btnKuliah = (ImageButton) findViewById(R.id.imgBtnAll);
        btnInfo = (ImageButton)findViewById(R.id.imgBtnInfo);
        btnUpoadnilai = (ImageButton) findViewById(R.id.imgBtnAgenda);
        btnUjian = (ImageButton) findViewById(R.id.imgBtnUjian);

        //displaying logged in user name
        textViewUserEmail.setText("Welcome "+user.getEmail()+ "Anda sudah terdaftar sebagai member. selamat menikmati!!");

        //adding listener to button
        buttonLogout.setOnClickListener(this);
        btnKuliah.setOnClickListener(this);
        btnInfo.setOnClickListener(this);
        btnUjian.setOnClickListener(this);
        btnUpoadnilai.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        //if logout is pressed
        if(view == buttonLogout){
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, MainActivity.class));
        }
        if (view == btnKuliah){
            startActivity(new Intent(this, Allagenda.class));
        }
        if (view == btnInfo){
            startActivity(new Intent(this, Info.class));
        }
        if (view == btnUjian){
            startActivity(new Intent(this, Ujian.class));
        }
        if (view == btnUpoadnilai){
            startActivity(new Intent(this, UploadNilai.class));
        }
    }
}
