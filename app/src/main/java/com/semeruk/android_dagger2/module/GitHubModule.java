package com.semeruk.android_dagger2.module;

import com.semeruk.android_dagger2.model.Repository;
import com.semeruk.android_dagger2.scope.UserScope;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

@Module
public class GitHubModule {

    public interface GitHubApiInterface {
        @GET("/users/{user}/repos")
        Call<List<Repository>> getRepository(@Path("user") String userName);
    }

    @Provides
    @UserScope // Needs to be consistent with the component scope
    public GitHubApiInterface provideGitHubInterface(Retrofit retrofit) {
        return retrofit.create(GitHubApiInterface.class);
    }
}