package com.example.vidyasagarnaidum.rkv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Locale;



import static android.app.Activity.RESULT_OK;
import static android.os.Environment.getExternalStorageDirectory;
import static android.widget.Toast.LENGTH_SHORT;

public class tab3_fragment extends Fragment  {
    private static final int PICK_IMAGE_REQUEST=234;
    public String filePath=null;
    TextView textView5;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment3, container,false);
        Button button = (Button) view.findViewById(R.id.button6);
        Button button2 = (Button) view.findViewById(R.id.button7);
        Button button3 = (Button) view.findViewById(R.id.button8);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        textView5=(TextView)  view.findViewById(R.id.textView5);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showFileChooser();
            }
        });
        button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getContext().getApplicationContext(),User_Details.class);
                startActivity(i);
            }
        });
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Toast.makeText(getContext(),"file exist "+filePath,Toast.LENGTH_LONG).show();
                if(filePath==null)
                    Toast.makeText(getContext(),"select the  file first ..!!",Toast.LENGTH_LONG).show();
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
                            xx=xx+"\n"+line;
                        }
                        textView5.setText(xx);
                    } catch (Exception e) {

                        Toast.makeText(getContext(), "file not accesible " + e, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return view;


    }
    public void display(String value) {

        textView5.setText(value);
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
                Toast.makeText(getContext(),"file has selected and path is "+path,Toast.LENGTH_LONG).show();
                filePath=path;


            }
        }

    }

}


