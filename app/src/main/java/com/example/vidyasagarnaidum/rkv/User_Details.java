package com.example.vidyasagarnaidum.rkv;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class User_Details extends AppCompatActivity {
    FirebaseDatabase firebasedatabase;
    DatabaseReference databaseReference;
    private static final int PICK_IMAGE_REQUEST=234;
    public String filePath=null;
    List<Student_Details> list=new ArrayList<Student_Details>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__details);
        firebasedatabase = FirebaseDatabase.getInstance();
        databaseReference=firebasedatabase.getReference();
        Button button = (Button) findViewById(R.id.button4);
        Button button2 = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showFileChooser();
            }
        });
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Toast.makeText(getContext(),"file exist "+filePath,Toast.LENGTH_LONG).show();
                if(filePath==null)
                    Toast.makeText(getApplicationContext(),"select the xls file first ..!!",Toast.LENGTH_LONG).show();
                else {
                    try {

                        //String sdcard=String.valueOf(filePath).replaceAll("sdcard","sdcard/");
                        String a="";
                        int j;
                        for(j=filePath.length()-1;filePath.charAt(j)!='/';j--)
                        {
                            a=filePath.charAt(j)+a;
                        }
                        //Toast.makeText(getContext(), "file name "+ a, Toast.LENGTH_LONG).show();
                        String line="",xx="";
                        FileInputStream fileInputStream = new FileInputStream (new File(filePath));
                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                        while ( (line = bufferedReader.readLine()) != null )
                        {
                            StringTokenizer st=new StringTokenizer(line);
                            String []ar=new String[3];
                            int i=0;
                            String n="";
                            //Toast.makeText(getApplicationContext(), "file details " + line, Toast.LENGTH_LONG).show();
                            while(st.hasMoreTokens()){

                               if(i<3) {
                                   ar[i++] = st.nextToken();
                               }
                               else
                               {
                                   n+=st.nextToken()+" ";
                               }
                            }

                              //  else n+=st.nextToken();n+=" ";
                            //}
                            Student_Details s=new Student_Details(ar[0],ar[1],ar[2],"","","","","",n);//,n,ar[1],"ALL","");
                            list.add(s);
                        }
                        Insert_into_Freebase();
                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(), "file not accesible " + e, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }
    public void Insert_into_Freebase()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator it=list.iterator();
                Student_Details student_details=null;
                Toast.makeText(getApplicationContext(),"Adding",Toast.LENGTH_SHORT).show();

                while(it.hasNext())
                {
                  student_details  = (Student_Details) it.next();
                databaseReference.child("Student_Details").child(student_details.getId()).setValue(student_details);

             /*   startActivity(new Intent(SignUpActivity.this, Login.class));
                Toast x=Toast.makeText(SignUpActivity.this,"Account Created Sucessfull",Toast.LENGTH_SHORT);
                x.setGravity(Gravity.TOP,Gravity.CENTER,5);
                x.show();*/
                }
                Toast.makeText(getApplicationContext(),"Added  Sucessfully ",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast x=Toast.makeText(User_Details.this,"Failed to Create Account"+databaseError,Toast.LENGTH_SHORT);
                x.setGravity(Gravity.TOP,Gravity.CENTER,5);
                x.show();
            }
        });
    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("text/plain");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);

    }

    //handling the image chooser activity result

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // filePath = String.valueOf(data.getData());

            Uri uri = data.getData();
            String type = data.getType();
            if (uri != null)
            {
                String path = uri.toString();
                if (path.toLowerCase().startsWith("file://"))
                {
                    // Selected file/directory path is below
                    path = (new File(URI.create(path))).getAbsolutePath();

                }
                Toast.makeText(getApplicationContext(),"file has selected and path is "+path,Toast.LENGTH_LONG).show();
                filePath=path;


            }
        }

    }
}

