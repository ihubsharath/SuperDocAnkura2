package com.example.superdoc_ankura.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.superdoc_ankura.R;

public class DoctorSessionHolder extends RecyclerView.ViewHolder {
    public TextView tvDate,tvHospitalName,tvFromToTime;
    public LinearLayout cardview_item, transation_layout;
    public CardView cardView;
    public DoctorSessionHolder(@NonNull View itemView) {
        super(itemView);
        tvDate = itemView.findViewById(R.id.tv_item_date);
        tvHospitalName = itemView.findViewById(R.id.tv_item_hospital_name);
        tvFromToTime = itemView.findViewById(R.id.tv_item_fromtime_totime);
        cardview_item = itemView.findViewById(R.id.cardview_item);
        transation_layout = itemView.findViewById(R.id.id_layout);
        cardView = itemView.findViewById(R.id.id_card_item1);
    }
}
