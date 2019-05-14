package com.example.superdoc_ankura.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.utils.BaseActivity;

public class GraphActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_activity);
    }
}
