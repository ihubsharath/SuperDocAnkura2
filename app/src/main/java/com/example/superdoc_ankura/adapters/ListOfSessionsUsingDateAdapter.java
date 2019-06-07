package com.example.superdoc_ankura.adapters;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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
import com.example.superdoc_ankura.holders.ListOfSessionsUsingDateHolder;
import com.example.superdoc_ankura.pojos.response.ListOfSessionsUsingDateResponse;

import java.util.ArrayList;

public class ListOfSessionsUsingDateAdapter extends RecyclerView.Adapter<ListOfSessionsUsingDateHolder> {
    FragmentActivity activity;
    ArrayList<ListOfSessionsUsingDateResponse> listOfSessionsUsingDateResponses;

    public ListOfSessionsUsingDateAdapter(FragmentActivity activity, ArrayList<ListOfSessionsUsingDateResponse> listOfSessionsUsingDateResponses) {
        this.activity = activity;
        this.listOfSessionsUsingDateResponses = listOfSessionsUsingDateResponses;
    }

    @NonNull
    @Override
    public ListOfSessionsUsingDateHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_of_sessions_using_date_item, viewGroup, false);
        ListOfSessionsUsingDateHolder holder = new ListOfSessionsUsingDateHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListOfSessionsUsingDateHolder holder, int i) {
        holder.tv_session_id.setText(String.valueOf(listOfSessionsUsingDateResponses.get(i).getDoctorSessionId()));
        holder.tvHospitalName.setText(listOfSessionsUsingDateResponses.get(i).getOrganizationName());
        holder.tvFromToTime.setText(listOfSessionsUsingDateResponses.get(i).getSessionTime());

        holder.cardview_item.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(doctorSessionActivity, AllAppointmentsActivity.class);
//                doctorSessionActivity.startActivity(i);


                Intent intent = new Intent(activity, AllAppointmentsActivity.class);

                intent.putExtra("sessionid", String.valueOf(listOfSessionsUsingDateResponses.get(i).getDoctorSessionId()));
                intent.putExtra("AppointmentsCount", String.valueOf(listOfSessionsUsingDateResponses.get(i).getTodayAppointmentsCount()));
                intent.putExtra("OrganizationName", listOfSessionsUsingDateResponses.get(i).getOrganizationName());
                intent.putExtra("SessionTime", listOfSessionsUsingDateResponses.get(i).getSessionTime());
                activity.startActivity(intent);
//                Pair[] pairs = new Pair[4];
//                pairs[0] = new Pair<View,String>(holder.tvDate,"count");
//                pairs[1] = new Pair<View,String>(holder.tvHospitalName,"hospital");
//                pairs[2] = new Pair<View,String>(holder.tvFromToTime,"time");
//                pairs[3] = new Pair<View,String>(holder.cardView,"sessionTransition");

//                intent.putExtra("name", ViewCompat.getTransitionName(holder.cardView));
//
//                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        activity,
//                        holder.cardView,
//                        ViewCompat.getTransitionName(holder.cardView));
//
////                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(doctorSessionActivity, pairs);
//                ActivityCompat.startActivity(activity, intent, options.toBundle());


            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfSessionsUsingDateResponses.size();
    }
}
