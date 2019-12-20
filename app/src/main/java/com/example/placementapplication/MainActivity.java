package com.example.placementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button student,admin,tpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("HOME PAGE");

        student=findViewById(R.id.btn_student);
        admin=findViewById(R.id.btn_admin);
        tpo=findViewById(R.id.btn_tpo);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,student_logni.class);
                startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Admin_login.class);
                startActivity(intent);
            }
        });

        tpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,tpo_login.class);
                startActivity(intent);
            }
        });
    }


}
