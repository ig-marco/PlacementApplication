package com.example.placementapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Admin_adding_page extends AppCompatActivity {
    Button addtpo,addstudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_adding_page);

        addtpo=findViewById(R.id.btn_add_tpo);
        addstudent=findViewById(R.id.btn_add_stud);

        addtpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_adding_page.this,Admin_TPO_Adding_Page.class);
                startActivity(intent);
            }
        });

        addstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin_adding_page.this,Admin_Student_Adding_Page.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return  true;
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Admin_adding_page.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;

            case R.id.viewpdfs:
                startActivity(new Intent(Admin_adding_page.this,ViewPdf.class));
                return true;


        }

        return false;
    }
}
