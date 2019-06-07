package com.example.superdoc_ankura.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.activities.AllAppointmentsActivity;
import com.example.superdoc_ankura.holders.NoShowAppointmentsHolder;
import com.example.superdoc_ankura.pojos.response.GetListOfNoShowAppointmentsResponse;

import java.util.List;

public class NoShowAppointmentsAdapter extends RecyclerView.Adapter<NoShowAppointmentsHolder> {
    AllAppointmentsActivity allAppointmentsActivity;
    List<GetListOfNoShowAppointmentsResponse> noShowAppointmentsResponseList;

    public NoShowAppointmentsAdapter(AllAppointmentsActivity allAppointmentsActivity, List<GetListOfNoShowAppointmentsResponse> noShowAppointmentsResponseList) {
        this.allAppointmentsActivity = allAppointmentsActivity;
        this.noShowAppointmentsResponseList = noShowAppointmentsResponseList;
    }

    @NonNull
    @Override
    public NoShowAppointmentsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(allAppointmentsActivity);
        View view = inflater.inflate(R.layout.noshow_appointments_itemview, viewGroup, false);
        NoShowAppointmentsHolder holder = new NoShowAppointmentsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoShowAppointmentsHolder holder, int i) {
        holder.time.setText(noShowAppointmentsResponseList.get(i).getApptTime());
        holder.patientName.setText(noShowAppointmentsResponseList.get(i).getPatientName());
        holder.status.setText(noShowAppointmentsResponseList.get(i).getApptStatus());
    }

    @Override
    public int getItemCount() {
        return noShowAppointmentsResponseList.size();
    }
}
