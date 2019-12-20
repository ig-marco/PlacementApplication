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

public class Admin_Student_Adding_Page extends AppCompatActivity {

    private EditText Sname,Semail,Spass,Sconpass,Sbranch,Sperc;
    private Button Ssignup;

    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__student__adding__page);
        getSupportActionBar().setTitle("Add a Student");

        Sname=findViewById(R.id.stu_name);
        Semail=findViewById(R.id.stu_email);
        Spass=findViewById(R.id.stu_pass);
        Sconpass=findViewById(R.id.stu_conf_pass);
        Sbranch=findViewById(R.id.stu_branch);
        Sperc=findViewById(R.id.stu_perc);
        Ssignup=findViewById(R.id.stu_add_signup);

        auth=FirebaseAuth.getInstance();

        Ssignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = Semail.getText().toString();
                String txt_password = Spass.getText().toString();
                String txt_name = Sname.getText().toString();
                String txt_conpass = Sconpass.getText().toString();
                String txt_branch = Sbranch.getText().toString();
                String txt_perc = Sperc.getText().toString();


                if(TextUtils.isEmpty(txt_name) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)|| TextUtils.isEmpty(txt_conpass)|| TextUtils.isEmpty(txt_branch)|| TextUtils.isEmpty(txt_perc))
                {
                    Toast.makeText(Admin_Student_Adding_Page.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }else if(txt_password.length()<6 || !txt_password.equals(txt_conpass))
                {
                    Toast.makeText(Admin_Student_Adding_Page.this, "password must be atleast 6 characters and confirm password must be same", Toast.LENGTH_SHORT).show();
                }else {
                    signup(txt_name,txt_email,txt_password,txt_branch,txt_perc);
                }
            }
        });
    }

    private void signup(final String username, final String email,final String password,final String branch,final  String percentile)
    {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();
                            reference = FirebaseDatabase.getInstance().getReference("Student").child(userid);

                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("username",username);
                            hashMap.put("email",email);
                            hashMap.put("branch",branch);
                            hashMap.put("percentile",percentile);


                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(Admin_Student_Adding_Page.this,Admin_adding_page.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        Toast.makeText(Admin_Student_Adding_Page.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            });
                        } else {

                            Toast.makeText(Admin_Student_Adding_Page.this, "You can not register with this email or password",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

}

