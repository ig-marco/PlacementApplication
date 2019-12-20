package com.example.placementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Tpo_notice_addingpage extends AppCompatActivity {

    Button company;
    Button papers;
    Button students;
    TextView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpo_notice_addingpage);
        getSupportActionBar().setTitle("What you want to do");

        company=findViewById(R.id.addcompany);
        papers=findViewById(R.id.addpapers);
        students=findViewById(R.id.selectstudents);
        home=findViewById(R.id.homedir);
        company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Tpo_notice_addingpage.this,Image_Upload.class));
            }
        });

        papers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Tpo_notice_addingpage.this, PdfUpload.class));
            }
        });
        students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Tpo_notice_addingpage.this,Upload_selected_students.class));
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Tpo_notice_addingpage.this,NoticePage.class));
            }
        });

    }
    public void direct(){

    }


}
