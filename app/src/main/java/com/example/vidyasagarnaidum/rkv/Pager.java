package com.example.vidyasagarnaidum.rkv;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class Pager extends FragmentStatePagerAdapter {

    int tabCount;

    public Pager(FragmentManager fm, int tabCount){
        super(fm);
        this.tabCount=tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0 :
                Regular tab1Fragment=new Regular();
                return tab1Fragment;
            case 1 :
                tab2Fragment tab2=new tab2Fragment();
                return tab2;
            case 2 :
                tab3_fragment tab3=new tab3_fragment();
                return tab3;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}