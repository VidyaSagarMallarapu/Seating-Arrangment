package com.example.vidyasagarnaidum.rkv;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class tab2Fragment extends Fragment{

    Button REMIDAL;
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.fragment2, container,false);
        REMIDAL = (Button) view.findViewById(R.id.REMIDAL);
        REMIDAL.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getContext(),Regular_DataPicker.class);
                i.putExtra("REMEDIAL","REMEDIAL");
                startActivity(i);
            }
        });
            return view;

        }


}
