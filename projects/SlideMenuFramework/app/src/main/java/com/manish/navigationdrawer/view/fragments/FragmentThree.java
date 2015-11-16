package com.manish.navigationdrawer.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slidemenuframework.R;
 

/**
 * The Class FragmentThree.
 */
@SuppressLint("InflateParams") public class FragmentThree extends Fragment {
 

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_three, null);
        return root;
    }
 
}