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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class admin_signup extends AppCompatActivity {

   private EditText adminname,adminmail,adminpass,adminconfpass;
  private   Button add_signup;

   private FirebaseAuth auth;
   private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);
        getSupportActionBar().setTitle("Admin Signup");

        adminname=findViewById(R.id.ad_name);
        adminmail=findViewById(R.id.ad_email);
        adminpass=findViewById(R.id.ad_pass);
        adminconfpass=findViewById(R.id.ad_conf_pass);
        add_signup=findViewById(R.id.ad_signup);

        auth = FirebaseAuth.getInstance();


        add_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String txt_username = adminname.getText().toString();
                String txt_email = adminmail.getText().toString();
                String txt_password = adminpass.getText().toString();
                String txt_co_password=adminpass.getText().toString();

                if(TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)|| TextUtils.isEmpty(txt_co_password))
                {
                    Toast.makeText(admin_signup.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }else if(txt_password.length()<6 || !txt_password.equals(txt_co_password))
                {
                    Toast.makeText(admin_signup.this, "password must be atleast 6 characters and confirm password must be same", Toast.LENGTH_SHORT).show();
                }else {
                    signup(txt_username,txt_email,txt_password);
                }
            }
        });
    }

    private void signup(final String username, final String email,final String password)
    {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();
                            reference = FirebaseDatabase.getInstance().getReference("Admin").child(userid);

                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username",username);
                            hashMap.put("email",email);


                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(admin_signup.this,Admin_adding_page.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            });
                        } else {

                            Toast.makeText(admin_signup.this, "You can not register with this email or password",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

    }

