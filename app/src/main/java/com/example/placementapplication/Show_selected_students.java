package com.example.placementapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Show_selected_students extends AppCompatActivity {


    DatabaseReference reference;
    ListView listView;

    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;


    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_selected_students);
        getSupportActionBar().setTitle("Selected Students List");

        listView=findViewById(R.id.listview);
        arrayList=new ArrayList<>();
        arrayAdapter=new ArrayAdapter<String>(this,R.layout.listview_student,R.id.name,arrayList);
        listView.setAdapter(arrayAdapter);


        reference= FirebaseDatabase.getInstance().getReference("Selected_Students");


        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                for (DataSnapshot ds:dataSnapshot.getChildren())
                {

                    String value=dataSnapshot.getValue(Student.class).toString();
                    arrayList.add(value);
                    arrayAdapter.notifyDataSetInvalidated();

                    break;
                }


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }
}
