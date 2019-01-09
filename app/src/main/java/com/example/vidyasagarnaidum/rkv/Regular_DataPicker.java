package com.example.vidyasagarnaidum.rkv;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

public class Regular_DataPicker extends AppCompatActivity implements View.OnClickListener {

    TextView t1;
    public  Button b1,b2,b3,b4,b5;
    String MODE="";
    public EditText CSE,ECE,ME,MME,CE,CHE;
     String a[]=new String[2];
    private int mYear, mMonth, mDay, mHour, mMinute;
    private static final int PICK_IMAGE_REQUEST=234;
    public String filePath=null;
    List<String> list=new ArrayList<String>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular__data_picker);
        final Calendar c = Calendar.getInstance();
        a[0]="";a[1]="";
        b1=findViewById(R.id.button11);
        b2=findViewById(R.id.button12);
        b3=findViewById(R.id.button29);
        b4=findViewById(R.id.button9);
        b5=findViewById(R.id.button10);

        Intent i=getIntent();
        MODE=i.getStringExtra("REMEDIAL");
        Toast.makeText(getApplicationContext(), " mode : "+MODE, Toast.LENGTH_LONG).show();
        if(MODE.equals("REMEDIAL")) {
            b4.setVisibility(View.VISIBLE);
            b5.setVisibility(View.VISIBLE);
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showFileChooser();
                }
            });
            b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {




                    String line="";
                    FileInputStream fileInputStream = null;
                    try {
                        fileInputStream = new FileInputStream(new File(filePath));

                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                        while ( (line = bufferedReader.readLine()) != null )
                        {
                            //Student_Details stu=new Student_Details(line);
                            //Toast.makeText(Regular_DataPicker.this, ""+stu.getId(), Toast.LENGTH_SHORT).show();
                           list.add(line);
                            }
                        }
                 catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }


                });
        }
        CSE =((EditText) findViewById(R.id.edittextcse));
        ECE = ((EditText) findViewById(R.id.edittextece));
        ME = ((EditText)findViewById(R.id.edittextme));
        CHE = ((EditText)findViewById(R.id.edittextche));
        CE = ((EditText)findViewById(R.id.edittextce));
        MME = ((EditText)findViewById(R.id.edittextmme));

        b1.setOnClickListener( this);
        b2.setOnClickListener( this);
        b3.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)

            {
               // Toast.makeText(getApplicationContext(), " the ECE : " + ECE.getText().toString()+" CSE :  "+CSE.getText().toString(), Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),Regular_Data_Picker2.class);

                Bundle extras = new Bundle();
                extras.putString("date", a[0]);
               extras.putString("time", a[1]);
                extras.putSerializable("list", (Serializable)list);
                extras.putString("CSE",CSE.getText().toString());
                extras.putString("ECE",ECE.getText().toString());
                extras.putString("ME",ME.getText().toString());
                extras.putString("MME",MME.getText().toString());
                extras.putString("CE",CE.getText().toString());
                extras.putString("CHE",CHE.getText().toString());
                extras.putString("MODE",MODE);
                i.putExtras(extras);
                startActivity(i);
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
    public void onClick(View v) {

        if (v == b1) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            a[0]=""+dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == b2) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                            a[1]=""+(i + ":" + i1);
                            Toast.makeText(getApplicationContext(), " the date : " + a[0]+" time :  "+a[1], Toast.LENGTH_LONG).show();
                        }


                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

}
