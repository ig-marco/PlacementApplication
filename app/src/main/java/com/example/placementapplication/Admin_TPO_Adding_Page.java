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

public class Admin_TPO_Adding_Page extends AppCompatActivity {
    private EditText tponame,tpoemail,tponewpass,tpoconfpass;
    private Button addtpo;

    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__tpo__adding__page);
        getSupportActionBar().setTitle("Add a TPO");

        tponame=findViewById(R.id.tpo_name);
        tpoemail=findViewById(R.id.tpo_email);
        tponewpass=findViewById(R.id.tpo_new_pass);
        tpoconfpass=findViewById(R.id.tpo_conf_pass);
        addtpo=findViewById(R.id.tpo_add_signup);


        auth=FirebaseAuth.getInstance();

        addtpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = tpoemail.getText().toString();
                String txt_password = tponewpass.getText().toString();
                String txt_name = tponame.getText().toString();
                String txt_conpass = tpoconfpass.getText().toString();



                if(TextUtils.isEmpty(txt_name) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)|| TextUtils.isEmpty(txt_conpass))
                {
                    Toast.makeText(Admin_TPO_Adding_Page.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }else if(txt_password.length()<6 || !txt_password.equals(txt_conpass))
                {
                    Toast.makeText(Admin_TPO_Adding_Page.this, "password must be atleast 6 characters and confirm password must be same", Toast.LENGTH_SHORT).show();
                }else {
                    signup(txt_name,txt_email,txt_password);
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
                            reference = FirebaseDatabase.getInstance().getReference("TPO").child(userid);

                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("username",username);
                            hashMap.put("email",email);



                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(Admin_TPO_Adding_Page.this,Admin_adding_page.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        Toast.makeText(Admin_TPO_Adding_Page.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            });
                        } else {

                            Toast.makeText(Admin_TPO_Adding_Page.this, "You can not register with this email or password",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }
}
