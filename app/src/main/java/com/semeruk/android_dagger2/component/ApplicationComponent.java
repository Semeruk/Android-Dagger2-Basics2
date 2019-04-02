package com.semeruk.android_dagger2.component;

import android.content.SharedPreferences;

import com.semeruk.android_dagger2.activity.MainActivity;
import com.semeruk.android_dagger2.module.ApplicationModule;
import com.semeruk.android_dagger2.module.DataModule;
import com.semeruk.android_dagger2.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Parent component
 */
@Singleton
@Component(modules = {
        ApplicationModule.class, DataModule.class, NetworkModule.class
})
public interface ApplicationComponent {

    // Remove injection methods if downstream modules will perform injection
    //void inject(MainActivity activity);

    // Downstream components need these exposed with the return type.
    // Method name does not really matter
    Retrofit retrofit();
    OkHttpClient okHttpClient();
    SharedPreferences sharedPreferences();
}