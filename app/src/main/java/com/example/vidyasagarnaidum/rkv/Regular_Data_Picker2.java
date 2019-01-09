package com.example.vidyasagarnaidum.rkv;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Regular_Data_Picker2 extends AppCompatActivity {


    Button b1;
    String date,time;
    int lcse,lece,lche,lmme,lme,lce;
    FirebaseDatabase firebasedatabase;
    DatabaseReference databaseReference;
    public static Student_Details users;
    public String CSE;
    public String ECE;
    public String ME;
    public String MME;
    public String CE;
    public String CHE,MODE;
    public static List<Student_Details> R14=new ArrayList<Student_Details>();
    List<String> list=null,list2=null;
    int flag=0,flag1=0,flag2=0,flag3=0,flag4=0,flag5=0;
    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular__data__picker2);
        firebasedatabase = FirebaseDatabase.getInstance();
        Intent i=getIntent();
        Bundle extras = getIntent().getExtras();
        date=extras.getString("date");
        time=extras.getString("time");
        CSE=extras.getString("CSE");
        ECE=extras.getString("ECE");
        ME=extras.getString("ME");
        MME=extras.getString("MME");
        CE=extras.getString("CE");
        CHE=extras.getString("CHE");
        MODE=extras.getString("MODE");
        if(MODE.equals("REMEDIAL"))
        {
             list= (List<String>) extras.getSerializable("list");
             list2=list;
        }

        databaseReference = firebasedatabase.getReference();

        databaseReference = FirebaseDatabase.getInstance().getReference("Student_Details");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            public  List<Student_Details> cse=new ArrayList<Student_Details>();
            public  List<Student_Details> ece=new ArrayList<Student_Details>();
            public  List<Student_Details> me=new ArrayList<Student_Details>();
            public  List<Student_Details> mme=new ArrayList<Student_Details>();
            public  List<Student_Details> che=new ArrayList<Student_Details>();
            public  List<Student_Details> ce=new ArrayList<Student_Details>();

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (@NonNull DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    Student_Details userDetails = userSnapshot.getValue(Student_Details.class);
                    if (MODE.equals("REGULAR")&&userDetails.getBranch() != null) {
                        if (userDetails.getBranch().equals("CSE")) {
                            cse.add(userDetails);
                        }
                        if (userDetails.getBranch().equals("ECE")) {
                            ece.add(userDetails);
                        }
                        if (userDetails.getBranch().equals("MME")) {
                            mme.add(userDetails);
                        }
                        if (userDetails.getBranch().equals("ME")) {
                            me.add(userDetails);
                        }
                        if (userDetails.getBranch().equals("CE")) {
                            ce.add(userDetails);
                        }
                        if (userDetails.getBranch().equals("CH")) {
                            che.add(userDetails);
                        }

                    }
                    else
                    {

                        if (list.contains(userDetails.getId())&&userDetails.getBranch().equals("CSE")) {
                            cse.add(userDetails);
                        }
                        if (list.contains(userDetails.getId())&&userDetails.getBranch().equals("ECE")) {
                            ece.add(userDetails);
                        }
                        if (list.contains(userDetails.getId())&&userDetails.getBranch().equals("MME")) {
                            mme.add(userDetails);
                        }
                        if (list.contains(userDetails.getId())&&userDetails.getBranch().equals("ME")) {
                            me.add(userDetails);
                        }
                        if (list.contains(userDetails.getId())&&userDetails.getBranch().equals("CE")) {
                            ce.add(userDetails);
                        }
                        if (list.contains(userDetails.getId())&&userDetails.getBranch().equals("CH")) {
                            che.add(userDetails);
                        }
                    }
                }

                Pass(cse,ece,ce,me,mme,che);


                // Toast.makeText(Regular_Data_Picker2.this,"ce1 "+" "+cse1.size(),Toast.LENGTH_SHORT).show();
                // cse=cse1;

            }

            public void onCancelled(DatabaseError databaseError) {

            }

        });
        // cse=cse1;
        //Toast.makeText(Regular_Data_Picker2.this, "ce1 " + " " + cse.size(), Toast.LENGTH_SHORT).show();




    }
    public void Pass(List<Student_Details> cse,List<Student_Details> ece,List<Student_Details> ce,List<Student_Details> me,List<Student_Details> mme,List<Student_Details> che) {
        cse = randomize(cse, cse.size());
        ce = randomize(ce, ce.size());
        che = randomize(che, che.size());
        ece = randomize(ece, ece.size());
        me = randomize(me, me.size());
        mme = randomize(mme, mme.size());
       /* List<Pair<List<Student_Details>, Integer>> evin= new ArrayList<Pair<List<Student_Details>, Integer>>();
        evin.add(new Pair<List<Student_Details>, Integer>(cse,new Integer(cse.size())));
        evin.add(new Pair<List<Student_Details>, Integer>(ece,new Integer(ece.size())));
        evin.add(new Pair<List<Student_Details>, Integer>(mme,new Integer(mme.size())));
        evin.add(new Pair<List<Student_Details>, Integer>(ce,new Integer(ce.size())));
        evin.add(new Pair<List<Student_Details>, Integer>(che,new Integer(che.size())));
        evin.add(new Pair<List<Student_Details>, Integer>(me,new Integer(me.size())));
        Collections.sort(evin,new Comparator<Pair<List<Student_Details>, Integer>>() {
            @Override
            public int compare(Pair<List<Student_Details>, Integer> o1, Pair<List<Student_Details>, Integer> o2) {
                if (o1.g > o2.getValue()) {
                    return -1;
                } else if (o1.getValue().equals(o2.getValue())) {
                    return 0; // You can change this to make it then look at the
                    //words alphabetical order
                } else {
                    return 1;
                }
            }
        });*/
        String clas[] = {"G16", "G17", "G18", "F3", "F4", "F5", "F6", "F14", "F15", "F16", "F17", "F18", "S3", "S4", "S5", "S6", "S7", "S8", "S15", "S19", "S17", "S18"};
        lcse = cse.size();
        lece = ece.size();
        lche = che.size();
        lmme = mme.size();
        lme = me.size();
        lce = ce.size();
       Toast.makeText(Regular_Data_Picker2.this, "cse:" + lcse+" ece:"+lece+" ce:"+lce+" me:"+lme+" "+" mme:"+lmme+" che:"+lche+" ", Toast.LENGTH_SHORT).show();
        int csec = lcse, ecec = lece, cec = lce, mmec = lmme, mec = lme, chc  = lche, i = 0;
        //Toast.makeText(Regular_Data_Picker2.this, "hi:"+MME, Toast.LENGTH_SHORT).show();
        for (i = 0; i < clas.length; i++) {
            for (int j = 0; j <6 ; j++) {//j<6
                for (int k = 0; k < 10; k++) {
                    if ((k & 1) == 0) {
                        if (mmec != 0) {

                            Student_Details m = mme.get(lmme - mmec);
                            Student_Details s = new Student_Details(m.getId(), m.getPassword(), m.getBranch(), clas[i] + " " + (char) (j + 65)+k, MME, date,time, MODE, m.getName());
                            mmec--;
                            Insert_into_Freebase(s, "MME");
                        } else if (cec != 0) {
                            Student_Details m = ce.get(lce - cec);
                            Student_Details s = new Student_Details(m.getId(), m.getPassword(), m.getBranch(), clas[i] + " " + (char) (j + 65)+k, CE, date,time,MODE, m.getName());
                            cec--;
                            Insert_into_Freebase(s, "CE");
                        } else if (csec != 0) {
                            Student_Details m = cse.get(lcse - csec);
                            Student_Details s = new Student_Details(m.getId(), m.getPassword(), m.getBranch(), clas[i] + " " + (char) (j + 65)+k, CSE,date,time, MODE, m.getName());
                            csec--;
                            Insert_into_Freebase(s, "CSE");
                        }
                    } else {
                        if (chc != 0) {
                            Student_Details m = che.get(lche - chc);
                            Student_Details s = new Student_Details(m.getId(), m.getPassword(), m.getBranch(), clas[i] + " " + (char) (j + 65)+k,CHE, date,time, MODE, m.getName());
                            chc--;
                            Insert_into_Freebase(s, "CH");
                        } else if (mec != 0) {
                            Student_Details m = me.get(lme - mec);
                            Student_Details s = new Student_Details(m.getId(), m.getPassword(), m.getBranch(), clas[i] + " " + (char) (j + 65)+k, ME, date,time,MODE, m.getName());
                            mec--;
                            Insert_into_Freebase(s, "MME");
                        } else if (ecec != 0) {
                            Student_Details m = ece.get(lece - ecec);
                            Student_Details s = new Student_Details(m.getId(), m.getPassword(), m.getBranch(), clas[i] + " " + (char) (j + 65)+k, ECE,date,time, MODE, m.getName());
                            ecec--;
                            Insert_into_Freebase(s, "ECE");
                        }
                    }
                }
            }


        }
        Toast.makeText(Regular_Data_Picker2.this,"Jumbling generated . ",Toast.LENGTH_SHORT).show();
    }
    /*
    public void readData(Firebase ref, final OnGetDataListener listener) {
        listener.onStart();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                listener.onFailure();
            }
        });

    }*/
    public static  List<Student_Details> randomize(List<Student_Details> branch,int n) {

        Random r = new Random();
        for (int i = n - 1; i > 0; i--) {

            int j = r.nextInt(i + 1);
            swapCells(branch,i,j);
        }
        return branch;
    }
    public static  void swapCells
            (List<Student_Details> objectList, int left, int right) {

        if(left!=right){
       Student_Details temp= objectList.get(left);
        objectList.set(left, objectList.get(right));
        objectList.set(right, temp);}

    }
    public void Insert_into_Freebase(final Student_Details student_details,String str)
    {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Toast.makeText(Regular_Data_Picker2.this,student_details.getSeating(),Toast.LENGTH_SHORT).show();
                    databaseReference.child(student_details.getId()).setValue(student_details);
            }


            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast x=Toast.makeText(Regular_Data_Picker2.this,"Failed to Create Account",Toast.LENGTH_SHORT);
                x.setGravity(Gravity.TOP,Gravity.CENTER,5);
                x.show();
            }
        });
    }



}
