package com.example.superdoc_ankura.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.utils.BaseActivity;

public class NotificationActivity extends BaseActivity {
    ImageView ivBackButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        ivBackButton = findViewById(R.id.iv_back_button);
        ivBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
