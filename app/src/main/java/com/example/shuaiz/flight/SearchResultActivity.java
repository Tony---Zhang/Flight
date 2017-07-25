package com.example.shuaiz.flight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SearchResultAdapter adapter = new SearchResultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        findViewById(R.id.fire_btn).setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        initViews();
        initData();
    }

    private void initViews() {
        progressBar = findViewById(R.id.progressbar);
        recyclerView = findViewById(R.id.search_result_list);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        recyclerView.setVisibility(View.GONE);
        Flowable.just(R.raw.flight)
                .map(getResources()::openRawResource)
                .map(InputStreamReader::new)
                .map(reader -> new Gson().fromJson(reader, new TypeToken<Flight[]>() {
                }.getType()))
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    adapter.setData((Flight[]) data);
                    adapter.notifyDataSetChanged();
                });
    }
}
