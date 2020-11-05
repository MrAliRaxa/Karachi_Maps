package com.example.karachimaps.Fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.karachimaps.Constants.ImageCommand;
import com.example.karachimaps.R;
import com.example.karachimaps.databinding.FragmentImageViewerBinding;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class ImageViewerFragment extends Fragment {


    private int imageCommand=0;
    private FragmentImageViewerBinding mDataBinding;
    private static final String TAG = "ImageViewerFragment";
    private InterstitialAd mInterstitialAd;
    public ImageViewerFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            imageCommand=getArguments().getInt("ImageCommand");
        }

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_image_viewer,container,false);

        if(imageCommand==ImageCommand.BUS_MAP){
            mDataBinding.imageViewerImage.setImageResource(R.drawable.transport_network_map);
            Log.d(TAG, "onCreateView: ");
        }else if(imageCommand==ImageCommand.METRO_MAP){
            mDataBinding.imageViewerImage.setImageResource(R.drawable.metromap);
            Log.d(TAG, "onCreateView: ");
        }else if(imageCommand==ImageCommand.AREA_MAP){
            mDataBinding.imageViewerImage.setImageResource(R.drawable.area_map);
            Log.d(TAG, "onCreateView: ");
        }else if(imageCommand==ImageCommand.HISTORIC_MAP){
            mDataBinding.imageViewerImage.setImageResource(R.drawable.historic_map);
            Log.d(TAG, "onCreateView: ");
        }

        return mDataBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }
}