package com.example.shuaiz.flight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class SearchResultActivity extends AppCompatActivity {

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
        RecyclerView recyclerView = findViewById(R.id.search_result_list);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        Flight[] data = new Flight[]{new Flight("jfk", "sfo"), new Flight("sfo", "jfk"), new Flight("med", "app")};
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }
}
