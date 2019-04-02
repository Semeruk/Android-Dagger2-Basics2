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
import com.semeruk.android_dagger2.model.Repository;
import com.semeruk.android_dagger2.module.GitHubModule;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    // Variables should not be private
    @Inject
    SharedPreferences mSharedPreferences;

    @Inject
    Retrofit mRetrofit;

    @Inject
    GitHubModule.GitHubApiInterface mGitHubApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvResponse = findViewById(R.id.tv_response);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
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

        });

        // Cast to our extended application class to get the component
        ((CustomApplication) getApplication()).getUserComponent().inject(this);
    }
}