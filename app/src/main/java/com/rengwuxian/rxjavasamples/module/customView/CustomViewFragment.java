package com.rengwuxian.rxjavasamples.module.customView;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rengwuxian.rxjavasamples.R;
import com.rengwuxian.rxjavasamples.module.customView.view.Controller1;
import com.rengwuxian.rxjavasamples.module.customView.view.MySearchView;

/**
 * @author DaiJiCheng
 * @time 2017/4/28  17:03
 * @desc ${TODD}
 */
public class CustomViewFragment extends Fragment {
    MySearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_view, container, false);
        searchView = (MySearchView)view.findViewById(R.id.sv);
        searchView.setController(new Controller1());
        return view;
    }

}