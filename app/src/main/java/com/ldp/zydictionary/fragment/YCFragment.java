package com.ldp.zydictionary.fragment;



import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idea.l.viewpagerlibrary.PageIndicator;
import com.ldp.zydictionary.R;
import com.ldp.zydictionary.adapter.TabAdapter;

/**
 * Created by l on 2016/2/25.
 */
public class YCFragment extends Fragment {
    private String mTilte = "Default";

    public static final String TITLE ="title";
    //view
    private ViewPager mPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        if(getArguments()!=null){
//            mTilte = getArguments().getString(TITLE);
//        }
//        TextView tv = new TextView(getActivity());
//        tv.setTextSize(20);
//        tv.setBackgroundColor(Color.parseColor("#ffffffff"));
//        tv.setText(mTilte);
//        tv.setGravity(Gravity.CENTER);
       return inflater.inflate(R.layout.fragment_yc,null);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentPagerAdapter adapter = new TabAdapter(
                this.getChildFragmentManager());
        mPager = (ViewPager)getView().findViewById(R.id.pager);
        mPager.setOffscreenPageLimit(1);
        mPager.setAdapter(adapter);

        PageIndicator indicator = (PageIndicator)getView().findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
    }
}
