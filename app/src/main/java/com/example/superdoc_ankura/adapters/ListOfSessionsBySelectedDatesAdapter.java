package com.example.superdoc_ankura.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.holders.ListOfSessionsBySelectedDatesHolder;
import com.example.superdoc_ankura.pojos.response.ListOfSessionsBySelectedDatesResponse;
import com.example.superdoc_ankura.utils.BaseActivity;

import java.util.ArrayList;

public class ListOfSessionsBySelectedDatesAdapter extends RecyclerView.Adapter<ListOfSessionsBySelectedDatesHolder> {
    Context context;
    static ListOfSessionsBySelectedDatesAdapter listOfSessionsBySelectedDatesAdapter;
    public ArrayList<Integer> sessionIdsList = new ArrayList<>();
    ArrayList<ListOfSessionsBySelectedDatesResponse> listOfSessionsBySelectedDatesResponseArrayList;


    public ListOfSessionsBySelectedDatesAdapter(Context context, ArrayList<ListOfSessionsBySelectedDatesResponse> listOfSessionsBySelectedDatesResponseArrayList) {
        this.context = context;
        this.listOfSessionsBySelectedDatesResponseArrayList = listOfSessionsBySelectedDatesResponseArrayList;
    }



    @NonNull
    @Override
    public ListOfSessionsBySelectedDatesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        listOfSessionsBySelectedDatesAdapter = this;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_of_sessions_by_selected_dates, viewGroup, false);
        ListOfSessionsBySelectedDatesHolder holder = new ListOfSessionsBySelectedDatesHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListOfSessionsBySelectedDatesHolder holder, int i) {
        holder.tv_session_id.setText(String.valueOf(listOfSessionsBySelectedDatesResponseArrayList.get(i).getDoctorSessionId()));
        holder.tvHospitalName.setText(listOfSessionsBySelectedDatesResponseArrayList.get(i).getOrganizationName());
        holder.tvFromToTime.setText(listOfSessionsBySelectedDatesResponseArrayList.get(i).getSessionTime());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    BaseActivity.getInstance().showToast(String.valueOf(listOfSessionsBySelectedDatesResponseArrayList.get(i).getDoctorSessionId()));

                    sessionIdsList.add(listOfSessionsBySelectedDatesResponseArrayList.get(i).getDoctorSessionId());

                }
                Log.d("sessionsidlist", sessionIdsList.toString());
            }
        });
    }



    @Override
    public int getItemCount() {
        return listOfSessionsBySelectedDatesResponseArrayList.size();
    }

    public static ListOfSessionsBySelectedDatesAdapter getInstance() {
        return listOfSessionsBySelectedDatesAdapter;
    }
}
