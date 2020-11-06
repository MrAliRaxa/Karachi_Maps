package com.example.karachimaps.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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

                startMap(MapCommand.KARACHI,"Karachi Map");
            }
        });
        mDataBinding.pakistanMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startMap(MapCommand.PAKISTAN,"Pakistan Map");

            }
        });
        mDataBinding.busMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImageView(ImageCommand.BUS_MAP,"Karachi Bus Map");
            }
        });
        mDataBinding.metroMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImageView(ImageCommand.METRO_MAP,"Karachi Metro Map");
            }
        });
        mDataBinding.areaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImageView(ImageCommand.AREA_MAP,"Karachi Area Map");
            }
        });
        mDataBinding.historicMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImageView(ImageCommand.HISTORIC_MAP,"Karachi Historic Map");
            }
        });

        return mDataBinding.getRoot();
    }
    private void startMap(int mapCommand,String title){
        MapsFragment mapsFragment=new MapsFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("MapCommand", mapCommand);
        bundle.putString("title",title);
        mapsFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .replace(R.id.fragmentCountainer,mapsFragment).commit();
    }
    private void startImageView(int imageCommand,String title){
        ImageViewerFragment imageViewerFragment=new ImageViewerFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("ImageCommand", imageCommand);
        bundle.putString("title",title);
        imageViewerFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .replace(R.id.fragmentCountainer,imageViewerFragment).commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.app_name));

    }
}