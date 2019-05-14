package com.example.superdoc_ankura.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.activities.AllAppointmentsActivity;
import com.example.superdoc_ankura.holders.DoctorSessionHolder;
import com.example.superdoc_ankura.pojos.response.DoctorSessionResponse;

import java.util.List;

public class DoctorSessionAdapter extends RecyclerView.Adapter<DoctorSessionHolder> {
    FragmentActivity doctorSessionActivity;
    List<DoctorSessionResponse> doctorSessionResponses;

    public DoctorSessionAdapter(FragmentActivity doctorSessionActivity, List<DoctorSessionResponse> doctorSessionResponses) {
        this.doctorSessionActivity = doctorSessionActivity;
        this.doctorSessionResponses = doctorSessionResponses;
    }

    @NonNull
    @Override
    public DoctorSessionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(doctorSessionActivity);
        View view = inflater.inflate(R.layout.doctor_session_itemview, viewGroup, false);
        DoctorSessionHolder holder = new DoctorSessionHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final DoctorSessionHolder holder, final int i) {
        holder.tvDate.setText(String.valueOf(doctorSessionResponses.get(i).getTodayAppointmentsCount()));
        holder.tvHospitalName.setText(doctorSessionResponses.get(i).getOrganizationName());
        holder.tvFromToTime.setText(doctorSessionResponses.get(i).getSessionTime());

        ViewCompat.setTransitionName(holder.cardView, doctorSessionResponses.get(i).getOrganizationName());

        holder.cardview_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(doctorSessionActivity, AllAppointmentsActivity.class);
//                doctorSessionActivity.startActivity(i);


                Intent intent = new Intent(doctorSessionActivity, AllAppointmentsActivity.class);
                intent.putExtra("AppointmentsCount", String.valueOf(doctorSessionResponses.get(i).getTodayAppointmentsCount()));
                intent.putExtra("OrganizationName", doctorSessionResponses.get(i).getOrganizationName());
                intent.putExtra("SessionTime", doctorSessionResponses.get(i).getSessionTime());

                intent.putExtra("name", ViewCompat.getTransitionName(holder.cardView));

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        doctorSessionActivity,
                        holder.cardView,
                        ViewCompat.getTransitionName(holder.cardView));

                ActivityCompat.startActivity(doctorSessionActivity, intent, options.toBundle());




            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorSessionResponses.size();
    }

//    public interface redirector{
//        public void sendData(int a, CardView cardView, int count, String orgName, String time);
//    }
}
