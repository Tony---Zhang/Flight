package com.example.shuaiz.flight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        findViewById(R.id.fire_btn).setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });
    }
}
