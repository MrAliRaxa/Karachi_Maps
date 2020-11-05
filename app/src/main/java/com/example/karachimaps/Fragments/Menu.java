package com.example.karachimaps.Fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.karachimaps.Constants.ImageCommand;
import com.example.karachimaps.Constants.MapCommand;
import com.example.karachimaps.R;
import com.example.karachimaps.databinding.FragmentMenuBinding;

public class Menu extends Fragment {


    private FragmentMenuBinding mDataBinding;

    public Menu() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_menu,container,false);

        mDataBinding.karachiMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startMap(MapCommand.KARACHI);
            }
        });
        mDataBinding.pakistanMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startMap(MapCommand.PAKISTAN);

            }
        });
        mDataBinding.busMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImageView(ImageCommand.BUS_MAP);
            }
        });
        mDataBinding.metroMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImageView(ImageCommand.METRO_MAP);
            }
        });
        mDataBinding.areaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImageView(ImageCommand.AREA_MAP);
            }
        });
        mDataBinding.historicMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImageView(ImageCommand.HISTORIC_MAP);
            }
        });

        return mDataBinding.getRoot();
    }
    private void startMap(int mapCommand){
        MapsFragment mapsFragment=new MapsFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("MapCommand", mapCommand);
        mapsFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .replace(R.id.fragmentCountainer,mapsFragment).commit();
    }
    private void startImageView(int imageCommand){
        ImageViewerFragment imageViewerFragment=new ImageViewerFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("ImageCommand", imageCommand);
        imageViewerFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .replace(R.id.fragmentCountainer,imageViewerFragment).commit();
    }
}