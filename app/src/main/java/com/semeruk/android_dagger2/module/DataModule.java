package com.semeruk.android_dagger2.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    // Application reference must come from ApplicationModule.class
    SharedPreferences provideSharedPrefs(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
