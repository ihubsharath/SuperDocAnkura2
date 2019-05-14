package com.example.superdoc_ankura.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.example.superdoc_ankura.R;

public class NoShowAppointmentsHolder extends RecyclerView.ViewHolder {
    public TextView time,patientName,status;
    public NoShowAppointmentsHolder(@NonNull View itemView) {
        super(itemView);
        time = itemView.findViewById(R.id.tv_time);
        patientName = itemView.findViewById(R.id.tv_patient_name);
        status = itemView.findViewById(R.id.tv_status);
    }
}
