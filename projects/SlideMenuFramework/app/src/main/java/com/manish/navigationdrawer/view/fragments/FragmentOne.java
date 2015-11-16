package com.manish.navigationdrawer.view.fragments;

import com.example.slidemenuframework.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * The Class FragmentOne.
 */
@SuppressLint("InflateParams")
public class FragmentOne extends Fragment {

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_one,
				null);
		return root;
	}

}