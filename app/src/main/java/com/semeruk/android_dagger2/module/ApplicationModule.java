package com.semeruk.android_dagger2.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    // It is the method in charge of providing the instance of the Application class
    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }
}