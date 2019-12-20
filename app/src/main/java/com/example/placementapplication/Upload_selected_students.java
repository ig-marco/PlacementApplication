package com.example.placementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Upload_selected_students extends AppCompatActivity {

    private EditText selectedname,companyname,id;
    private Button upload,show;

    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_selected_students);
        getSupportActionBar().setTitle("Select Students");


        selectedname=findViewById(R.id.selected_name);
        companyname=findViewById(R.id.company);
        show=findViewById(R.id.show);
        upload=findViewById(R.id.upload);
        id=findViewById(R.id.studentid);

        reference= FirebaseDatabase.getInstance().getReference("Selected_Students");


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_id=id.getText().toString();
                String txt_sname=selectedname.getText().toString();
                String txt_company=companyname.getText().toString();

                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("student_name",txt_sname);
                hashMap.put("company",txt_company);

                reference.child(txt_id).setValue(hashMap);
                Toast.makeText(Upload_selected_students.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();

            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Upload_selected_students.this,Show_selected_students.class);
                startActivity(intent);
            }
        });

    }
}
