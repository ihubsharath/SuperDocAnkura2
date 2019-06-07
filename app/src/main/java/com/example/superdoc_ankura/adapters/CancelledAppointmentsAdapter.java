package com.example.superdoc_ankura.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.activities.AllAppointmentsActivity;
import com.example.superdoc_ankura.holders.CancelledAppointmentsHolder;
import com.example.superdoc_ankura.pojos.response.GetListOfCancelledAppointmentsResponse;

import java.util.List;

public class CancelledAppointmentsAdapter extends RecyclerView.Adapter<CancelledAppointmentsHolder> {
    AllAppointmentsActivity allAppointmentsActivity;
    List<GetListOfCancelledAppointmentsResponse> cancelledAppointmentsResponseList;

    public CancelledAppointmentsAdapter(AllAppointmentsActivity allAppointmentsActivity, List<GetListOfCancelledAppointmentsResponse> cancelledAppointmentsResponseList) {
    this.allAppointmentsActivity = allAppointmentsActivity;
    this.cancelledAppointmentsResponseList = cancelledAppointmentsResponseList;
    }

    @NonNull
    @Override
    public CancelledAppointmentsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(allAppointmentsActivity);
        View view = inflater.inflate(R.layout.cancelled_appointment_itemview,viewGroup,false);
        CancelledAppointmentsHolder holder = new CancelledAppointmentsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CancelledAppointmentsHolder holder, int i) {

       holder.time.setText(cancelledAppointmentsResponseList.get(i).getApptTime());
       holder.patientName.setText(cancelledAppointmentsResponseList.get(i).getPatientName());
        holder.status.setText(cancelledAppointmentsResponseList.get(i).getApptStatus());

    }

    @Override
    public int getItemCount() {
        return cancelledAppointmentsResponseList.size();
    }
}
