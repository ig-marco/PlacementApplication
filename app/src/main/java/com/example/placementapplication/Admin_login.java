package com.example.placementapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Admin_login extends AppCompatActivity {
   private EditText Aemail,Apass;
   private Button Alogin,Asignup;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        getSupportActionBar().setTitle("Admin Login");

        Aemail=findViewById(R.id.admin_email);
        Apass=findViewById(R.id.admin_pass);
        Alogin=findViewById(R.id.btn_admin_log);
        Asignup=findViewById(R.id.btn_admin_signup);
        auth=FirebaseAuth.getInstance();

        Alogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = Aemail.getText().toString();
                String txt_password = Apass.getText().toString();

                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password))
                {
                    Toast.makeText(Admin_login.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else  {
                    auth.signInWithEmailAndPassword(txt_email,txt_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Admin_login.this, "Authentication Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Admin_login.this,Admin_adding_page.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(Admin_login.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        Asignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_login.this,admin_signup.class);
                startActivity(intent);
            }
        });


    }

}
