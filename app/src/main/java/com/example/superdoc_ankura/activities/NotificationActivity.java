package com.example.superdoc_ankura.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.adapters.NotificationsAdapter;
import com.example.superdoc_ankura.pojos.response.GetNotificationsResponse;
import com.example.superdoc_ankura.utils.BaseActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends BaseActivity {
    ImageView ivBackButton;
    RecyclerView rview;
    TextView textNotifications,textClearAll;
    TextView noDataFound;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        rview = findViewById(R.id.rview);
        noDataFound = findViewById(R.id.noDataFound);
        noDataFound.setTypeface(faceProximaRegular);
        textNotifications = findViewById(R.id.textNotification);
        textClearAll = findViewById(R.id.textClearAll);
        textNotifications.setTypeface(faceLight);
        textClearAll.setTypeface(faceLight);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rview.setLayoutManager(linearLayoutManager);
        rview.setHasFixedSize(true);

        Call<ArrayList<GetNotificationsResponse>> call = serviceCalls.getNotifications(sessionManager.getDOCTORID());
        call.enqueue(new Callback<ArrayList<GetNotificationsResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<GetNotificationsResponse>> call, Response<ArrayList<GetNotificationsResponse>> response) {
                if (response.code()==200){
                    ArrayList<GetNotificationsResponse> notificationsResponses = response.body();
                    if (notificationsResponses.size()==0){
                        noDataFound.setVisibility(View.VISIBLE);
                        rview.setVisibility(View.GONE);
                    }else {
                        rview.setVisibility(View.VISIBLE);
                        noDataFound.setVisibility(View.GONE);
                        NotificationsAdapter notificationsAdapter = new NotificationsAdapter(NotificationActivity.this,notificationsResponses);
                        rview.setAdapter(notificationsAdapter);
                        notificationsAdapter.notifyDataSetChanged();
                    }
                }else if (response.code()==204){
                    showToast("OPPS something went wrong...");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetNotificationsResponse>> call, Throwable t) {

            }
        });
        ivBackButton = findViewById(R.id.iv_back_button);
        ivBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
