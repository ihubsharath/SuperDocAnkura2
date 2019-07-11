package com.example.superdoc_ankura.adapters;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
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
        //holder.cardview_item.setVisibility(View.VISIBLE);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final DoctorSessionHolder holder, final int i) {


        holder.tv_session_id.setText(String.valueOf(doctorSessionResponses.get(i).getDoctorSessionId()));
        holder.tvHospitalName.setText(doctorSessionResponses.get(i).getOrganizationName());
        holder.tvFromToTime.setText(doctorSessionResponses.get(i).getSessionTime());
        holder.cardview_appointmentcount.setVisibility(View.VISIBLE);
        holder.appointmentsCount.setText(String.valueOf(doctorSessionResponses.get(i).getTodayAppointmentsCount()));

        ViewCompat.setTransitionName(holder.cardView, doctorSessionResponses.get(i).getOrganizationName());

        holder.cardview_item.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(doctorSessionActivity, AllAppointmentsActivity.class);
//                doctorSessionActivity.startActivity(i);
               holder.cardview_appointmentcount.setVisibility(View.GONE);

//                final Handler handler = new Handler();
//               handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        holder.cardview_item.setVisibility(View.GONE);
//                    }
//                }, 500);
                Intent intent = new Intent(doctorSessionActivity, AllAppointmentsActivity.class);

                intent.putExtra("sessionid",String.valueOf(doctorSessionResponses.get(i).getDoctorSessionId()));
                intent.putExtra("AppointmentsCount", String.valueOf(doctorSessionResponses.get(i).getTodayAppointmentsCount()));
                intent.putExtra("OrganizationName", doctorSessionResponses.get(i).getOrganizationName());
                intent.putExtra("SessionTime", doctorSessionResponses.get(i).getSessionTime());

//                Pair[] pairs = new Pair[4];
//                pairs[0] = new Pair<View,String>(holder.tvDate,"count");
//                pairs[1] = new Pair<View,String>(holder.tvHospitalName,"hospital");
//                pairs[2] = new Pair<View,String>(holder.tvFromToTime,"time");
//                pairs[3] = new Pair<View,String>(holder.cardView,"sessionTransition");

                intent.putExtra("name", ViewCompat.getTransitionName(holder.cardView));

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        doctorSessionActivity,
                        holder.cardView,
                        ViewCompat.getTransitionName(holder.cardView));

//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(doctorSessionActivity, pairs);
                ActivityCompat.startActivity(doctorSessionActivity, intent, options.toBundle());
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        holder.cardview_appointmentcount.setVisibility(View.VISIBLE);
                    }
                }, 500);





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
