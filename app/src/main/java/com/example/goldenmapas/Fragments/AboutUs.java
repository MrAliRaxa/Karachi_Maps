package com.example.goldenmapas.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.goldenmapas.R;
import com.example.goldenmapas.databinding.FragmentAboutUsBinding;


public class AboutUs extends Fragment {


    private FragmentAboutUsBinding mDataBinding;
    public AboutUs() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mDataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_about_us,container,false);

        mDataBinding.privacyPolicyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/goldenmapas/privacy"));
                startActivity(browserIntent);
            }
        });
        return mDataBinding.getRoot();
    }
}