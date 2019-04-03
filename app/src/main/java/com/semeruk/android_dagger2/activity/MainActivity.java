package com.semeruk.android_dagger2.activity;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.semeruk.android_dagger2.CustomApplication;
import com.semeruk.android_dagger2.R;
import com.semeruk.android_dagger2.component.DaggerUserComponent;
import com.semeruk.android_dagger2.component.UserComponent;
import com.semeruk.android_dagger2.model.Repository;
import com.semeruk.android_dagger2.module.GitHubModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    // Variables should not be private
    // Inject via Dagger
    @Inject
    SharedPreferences mSharedPreferences;
    @Inject
    Retrofit mRetrofit;
    @Inject
    GitHubModule.GitHubApiInterface mGitHubApiInterface;

    // Bind views
    @BindView(R.id.tv_response)
    TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // Pass ApplicationComponent into the UserComponent Dagger Builder
        UserComponent mUserComponent = DaggerUserComponent.builder()
                .applicationComponent(((CustomApplication) getApplication()).getApplicationComponent())
                .build();

        // Cast to our extended application class to get the component
        mUserComponent.inject(this);
    }

    @OnClick(R.id.fab)
    public void execRequest(View view) {
        Call<List<Repository>> call = mGitHubApiInterface.getRepository("codepath");
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {

                if (response.isSuccessful()) {

                    List<Repository> repositoryList = response.body();

                    StringBuilder stringBuilder = new StringBuilder();

                    for (Repository repository : repositoryList) {
                        stringBuilder.append(repository.toString() + "\n\n");
                    }

                    tvResponse.setText(stringBuilder.toString());

                    //Log.i("DEBUG", response.body().toString());
                    Snackbar.make(view,"Data retrieved", Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                } else {
                    Log.e("ERROR", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
            }
        });
    }
}