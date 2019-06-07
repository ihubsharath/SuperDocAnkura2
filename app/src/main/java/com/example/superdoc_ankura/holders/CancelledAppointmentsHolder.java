package com.example.superdoc_ankura.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.utils.BaseActivity;


public class CancelledAppointmentsHolder extends RecyclerView.ViewHolder {
    public TextView time,patientName,status;
    public CancelledAppointmentsHolder(@NonNull View itemView) {
        super(itemView);
        time = itemView.findViewById(R.id.tv_time);
        patientName = itemView.findViewById(R.id.tv_patient_name);
        status = itemView.findViewById(R.id.tv_status);

        time.setTypeface(BaseActivity.getInstance().faceLight);
        patientName.setTypeface(BaseActivity.getInstance().faceRegular);
        status.setTypeface(BaseActivity.getInstance().faceBold);
    }
}
