package com.semeruk.android_dagger2.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.semeruk.android_dagger2.CustomApplication;
import com.semeruk.android_dagger2.R;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    // Variables should not be private
    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    OkHttpClient okHttpClientCached;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cast to our extended application class to get the component
        ((CustomApplication) getApplication()).getApplicationComponent().inject(this);
    }
}