package com.semeruk.android_dagger2.component;

import com.semeruk.android_dagger2.activity.MainActivity;
import com.semeruk.android_dagger2.module.GitHubModule;
import com.semeruk.android_dagger2.scope.UserScope;

import dagger.Component;

// Two dependent components cannot share the same scope.
// For instance, two components cannot both be scoped as @Singleton.
// Dependent components need to define their own scope.
@UserScope // Using the previously defined scope,
// note that @Singleton will not work
@Component(dependencies = ApplicationComponent.class, modules = GitHubModule.class)
public interface UserComponent {

    void inject(MainActivity activity);
}