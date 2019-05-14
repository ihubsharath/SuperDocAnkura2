package com.example.superdoc_ankura.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.superdoc_ankura.R;


public class ConfirmedAppointmentsHolder extends RecyclerView.ViewHolder {
    public TextView tvTime,tvPatientName,tvApptStatus;
    public ConfirmedAppointmentsHolder(@NonNull View itemView) {
        super(itemView);
        tvTime = itemView.findViewById(R.id.tv_time);
        tvPatientName = itemView.findViewById(R.id.tv_patient_name);
        tvApptStatus = itemView.findViewById(R.id.tv_status);
    }
}
