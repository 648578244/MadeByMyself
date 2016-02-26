package com.ldp.zydictionary.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ldp.zydictionary.R;

/**
 * Created by l on 2016/2/25.
 */
public class YCListFragment extends Fragment {
    public static final String POSITION ="position";
    public YCListFragment(String blogType) {
        Bundle bundle = new Bundle();
        bundle.putString(POSITION, blogType);
        setArguments(bundle);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_yc_list,null);
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = (TextView)getView().findViewById(R.id.textContent);
        textView.setText(getArguments().getString(POSITION));

    }
}
