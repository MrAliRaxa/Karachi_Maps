package com.example.karachimaps.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.karachimaps.Constants.MapCommand;
import com.example.karachimaps.Constants.MapsLatLng;
import com.example.karachimaps.R;
import com.example.karachimaps.databinding.FragmentMapsBinding;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    private int mapCommand=0;
    private InterstitialAd mInterstitialAd;
    private FragmentMapsBinding mDataBinding;
    private String actionBarTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){

            mapCommand=(getArguments().getInt("MapCommand",0));
            actionBarTitle=(getArguments().getString("title",""));
        }
        integrateAdmob();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mDataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_maps,container,false);

        if(((AppCompatActivity)getActivity()).getSupportActionBar().isShowing()){
            ActionBar actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
            actionBar.setTitle(actionBarTitle);
        }

        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }


    }


    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng latLng =null;
            int zoomLevel=0;

            if(mapCommand== MapCommand.KARACHI){
                latLng= MapsLatLng.getKarachiLatLng();
                zoomLevel=12;
            }else if(mapCommand==MapCommand.PAKISTAN){
                latLng= MapsLatLng.getKarachiLatLng();
                zoomLevel=5;
            }


            mDataBinding.normalMapBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            });

            mDataBinding.satelliteMapBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            });

            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoomLevel));
            googleMap.addMarker(new MarkerOptions().position(latLng).title("Karachi"));

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }
    private void integrateAdmob(){
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }
}