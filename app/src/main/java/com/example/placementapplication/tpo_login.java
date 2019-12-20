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

public class tpo_login extends AppCompatActivity {
    private EditText temail,tpass;
    private Button tadd;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpo_login);
        getSupportActionBar().setTitle("TPO Login");

        temail=findViewById(R.id.t_email);
        tpass=findViewById(R.id.t_pass);
        tadd=findViewById(R.id.btn_t_log);

        auth=FirebaseAuth.getInstance();

        tadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = temail.getText().toString();
                String txt_password = tpass.getText().toString();

                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password))
                {
                    Toast.makeText(tpo_login.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else  {
                    auth.signInWithEmailAndPassword(txt_email,txt_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(tpo_login.this, "Authentication Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(tpo_login.this,Tpo_notice_addingpage.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(tpo_login.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
