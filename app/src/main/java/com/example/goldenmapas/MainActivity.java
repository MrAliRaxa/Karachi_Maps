package com.example.goldenmapas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.goldenmapas.Fragments.AboutUs;
import com.example.goldenmapas.Fragments.Menu;
import com.example.goldenmapas.databinding.ActivityMainBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mDataBinding;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.d(TAG, "onInitializationComplete: ");

                AdView adView = mDataBinding.adView;
                AdRequest adRequest = new AdRequest.Builder().build();
                adView.loadAd(adRequest);
            }
        });
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentCountainer,new Menu())
                .commit();

        mDataBinding.aboutPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragmentCountainer,new AboutUs())
                        .commit();

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.maps_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_aboutUs){
            getActivity().getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentCountainer,new AboutUs())
                    .commit();
        }
        return super.onOptionsItemSelected(item);
    }

    private AppCompatActivity getActivity(){

        return MainActivity.this;
    }

    private Context getContext(){
        return MainActivity.this;
    }


    @Override
    protected void onStart() {
        super.onStart();
    }
}