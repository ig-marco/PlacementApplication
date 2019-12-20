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

public class student_logni extends AppCompatActivity {
    private EditText semail,spass;
    private Button slogin;

   private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_logni);
        getSupportActionBar().setTitle("Student Login");

        semail=findViewById(R.id.s_email);
        spass=findViewById(R.id.s_pass);
        slogin=findViewById(R.id.btn_s_log);

        auth= FirebaseAuth.getInstance();


        slogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = semail.getText().toString();
                String txt_password = spass.getText().toString();

                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password))
                {
                    Toast.makeText(student_logni.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else  {
                    auth.signInWithEmailAndPassword(txt_email,txt_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(student_logni.this, "Authentication Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(student_logni.this,NoticePage.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(student_logni.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
