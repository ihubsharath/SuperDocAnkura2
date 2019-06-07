package com.example.superdoc_ankura.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.adapters.ListOfSessionsBySelectedDatesAdapter;
import com.example.superdoc_ankura.utils.BaseActivity;

public class ListOfSessionsBySelectedDatesHolder extends RecyclerView.ViewHolder {
    public CheckBox checkBox;
    public TextView tv_session_id,tvHospitalName,tvFromToTime;
    public TextView appointmentsCount;
    public LinearLayout cardview_item, transation_layout;
    public CardView cardView;

    public ListOfSessionsBySelectedDatesHolder(@NonNull View itemView) {
        super(itemView);
        tv_session_id = itemView.findViewById(R.id.tv_session_id);
        tvHospitalName = itemView.findViewById(R.id.tv_item_hospital_name);
        tvFromToTime = itemView.findViewById(R.id.tv_item_fromtime_totime);

        tv_session_id.setTypeface(BaseActivity.getInstance().faceLight);
        tvHospitalName.setTypeface(BaseActivity.getInstance().faceMedium);
        tvFromToTime.setTypeface(BaseActivity.getInstance().faceBold);

        cardview_item = itemView.findViewById(R.id.cardview_item);
        transation_layout = itemView.findViewById(R.id.id_layout);
        cardView = itemView.findViewById(R.id.id_card_item1);
        checkBox = itemView.findViewById(R.id.checkbox);



//        appointmentsCount = itemView.findViewById(R.id.appointmentsCount);
    }


}
