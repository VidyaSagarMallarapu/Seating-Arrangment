package com.example.vidyasagarnaidum.rkv;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    DatabaseReference databaseReference;
    public static Student_Details users;
    public static List<Student_Details> mylist=new ArrayList<Student_Details>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast toast1 = Toast.makeText(getApplicationContext(),"on create invoked ", Toast.LENGTH_SHORT);
        toast1.show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.userid);
        e2 = (EditText) findViewById(R.id.pwd);
        databaseReference= FirebaseDatabase.getInstance().getReference("Student_Details");
    }

    public void LoginCheck(View view) {
        String user, pwd;
        user = e1.getText().toString();
        pwd = e2.getText().toString();
        if (user.equals("") || pwd.equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Enter  User id or Password", Toast.LENGTH_LONG);
            toast.show();
        }
        if (user.equals("a") && pwd.equals("a")) {
            Intent i = new Intent(getApplicationContext(), Admin2.class);
            startActivity(i);
        }
        else {
            String user_id = user;
            int f=0;
            Iterator it = mylist.iterator();
            Student_Details userDetails = null;
            while (it.hasNext()) {
                userDetails = (Student_Details) it.next();

                if (((userDetails.getId()).equals(user_id)) && ((userDetails.getPassword()).equals(pwd))) {
                    f=1;
                    Intent i = new Intent(getApplicationContext(), Student.class);
                    i.putExtra("Object", (Serializable) userDetails);
                    startActivity(i);

                }
            }
            if(f==0){
            Toast toast = Toast.makeText(getApplicationContext(), "Enter Correct User id and Password", Toast.LENGTH_LONG);
            toast.show();}

        }
    }
    protected void onStart() {
        super.onStart();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                   Student_Details userDetails= userSnapshot.getValue(Student_Details.class);
                    mylist.add(userDetails);
                }
                Toast toast1 = Toast.makeText(getApplicationContext(),"Internet connected .", Toast.LENGTH_SHORT);
                toast1.show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }

        });
    }


    public void Forgot(View view) {

    }
}
