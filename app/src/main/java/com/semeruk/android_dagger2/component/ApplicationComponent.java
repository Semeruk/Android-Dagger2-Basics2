package com.semeruk.android_dagger2.component;

import com.semeruk.android_dagger2.activity.MainActivity;
import com.semeruk.android_dagger2.module.ApplicationModule;
import com.semeruk.android_dagger2.module.DataModule;
import com.semeruk.android_dagger2.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class, DataModule.class, NetworkModule.class
})
public interface ApplicationComponent {

    void inject(MainActivity activity);
}