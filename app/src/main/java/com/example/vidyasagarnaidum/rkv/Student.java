package com.example.vidyasagarnaidum.rkv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class Student extends AppCompatActivity {

    TextView textView10;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        textView10=(TextView)findViewById(R.id.textView10);
        Intent i=getIntent();
        Student_Details done = (Student_Details) i.getSerializableExtra("Object");
        String s="";
        s+="\n\n\n\n  Id             :  "+done.getId()+"\n  Name           :  "+done.getName()+"\n  Subjects       :  "+done.getExams();
        s+="\n  Seating        :  "+done.getSeating()+"\n  Date           :  "+done.getDate()+"\n  Time           :  "+done.getTime()+"\n  Branch         :  "+done.getBranch()+"\n  Mode           :  "+done.getMode();
        textView10.setText(s);
    }


}
