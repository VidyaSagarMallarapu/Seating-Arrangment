package com.example.vidyasagarnaidum.rkv;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.io.InputStream;


public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }


    /*public void ReadFromSheet(View view) {
        try {

            AssetManager am = getAssets();// If this line gives you ERROR then try AssetManager am=getActivity().getAssets();
            InputStream is = am.open("data.xls");
            Workbook wb = Workbook.getWorkbook(is);
            Sheet s = wb.getSheet(0);
            int row = s.getRows();
            int col = s.getColumns();
            String xx = "";
            for (int i = 0; i < row; i++) {
                for (int c = 0; c < col; c++) {
                    Cell z = s.getCell(c, i);
                    xx = xx + z.getContents();
                    xx+="  ";
                }
                Log.d("SS",xx);
                xx = xx + "\n";
            }

            display(xx);
        } catch (Exception e) {

        }

    }

    public void display(String value) {
        TextView x = (TextView) findViewById(R.id.textView);
        x.setText(value);
    }*/
}

