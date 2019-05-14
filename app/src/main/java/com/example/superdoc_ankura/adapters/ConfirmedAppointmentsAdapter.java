package com.example.superdoc_ankura.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.activities.AllAppointmentsActivity;
import com.example.superdoc_ankura.holders.ConfirmedAppointmentsHolder;
import com.example.superdoc_ankura.pojos.response.ConfirmedAppointmentsResponse;

import java.util.List;

public class ConfirmedAppointmentsAdapter extends RecyclerView.Adapter<ConfirmedAppointmentsHolder> {
    AllAppointmentsActivity allAppointmentsActivity;
    List<ConfirmedAppointmentsResponse> confirmedAppointmentsResponseList;

    public ConfirmedAppointmentsAdapter(AllAppointmentsActivity allAppointmentsActivity, List<ConfirmedAppointmentsResponse> confirmedAppointmentsResponseList) {
        this.allAppointmentsActivity = allAppointmentsActivity;
        this.confirmedAppointmentsResponseList = confirmedAppointmentsResponseList;
    }

    @NonNull
    @Override
    public ConfirmedAppointmentsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(allAppointmentsActivity);
        View view = inflater.inflate(R.layout.confirmed_appointments_itemview, viewGroup, false);
        ConfirmedAppointmentsHolder holder = new ConfirmedAppointmentsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmedAppointmentsHolder holder, int i) {
        holder.tvTime.setText(confirmedAppointmentsResponseList.get(i).getApptTime());
        holder.tvPatientName.setText(confirmedAppointmentsResponseList.get(i).getPatientName()+ "/" + confirmedAppointmentsResponseList.get(i).getApptID());
        holder.tvApptStatus.setText(confirmedAppointmentsResponseList.get(i).getApptStatus());
    }

    @Override
    public int getItemCount() {
        return confirmedAppointmentsResponseList.size();
    }
}
