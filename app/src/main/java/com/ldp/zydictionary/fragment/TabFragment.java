package com.ldp.zydictionary.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by chenqiang on 15/12/15.
 */
public class TabFragment extends Fragment {
    private String mTilte = "Default";

    public static final String TITLE ="title";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(getArguments()!=null){
            mTilte = getArguments().getString(TITLE);

        }

        TextView tv = new TextView(getActivity());
        tv.setTextSize(20);
        tv.setBackgroundColor(Color.parseColor("#ffffffff"));
        tv.setText(mTilte);
        tv.setGravity(Gravity.CENTER);
        return  tv;
    }
}
