package com.example.superdoc_ankura.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.activities.AllAppointmentsActivity;
import com.example.superdoc_ankura.pojos.response.DoctorTimeSlotsResponse;
import com.example.superdoc_ankura.utils.BaseActivity;

import java.util.ArrayList;

public class GridViewSlotsAdapter extends BaseAdapter {
    static GridViewSlotsAdapter gridViewSlotsAdapter;
    Context context;
    ArrayList<DoctorTimeSlotsResponse> doctorTimeSlotsResponses;

    public GridViewSlotsAdapter(Context context, ArrayList<DoctorTimeSlotsResponse> doctorTimeSlotsResponses) {
        this.context = context;
        this.doctorTimeSlotsResponses = doctorTimeSlotsResponses;
    }

    @Override
    public int getCount() {
        return doctorTimeSlotsResponses.size();
    }

    @Override
    public Object getItem(int position) {
        return doctorTimeSlotsResponses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        gridViewSlotsAdapter = this;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        convertView = inflater.inflate(R.layout.time_slots_screen, parent, false);
        TextView tvSlot = convertView.findViewById(R.id.tv_slot);
        tvSlot.setTypeface(BaseActivity.getInstance().faceProximaRegular);
        LinearLayout layoutSlot = convertView.findViewById(R.id.layout_slot);
        tvSlot.setText(doctorTimeSlotsResponses.get(position).getTimeSlot());

        if (doctorTimeSlotsResponses.get(position).isBooked() == true) {
            tvSlot.setTextColor(Color.parseColor("#14BBD3"));
            tvSlot.setEnabled(true);
            tvSlot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String selectedSlot = doctorTimeSlotsResponses.get(position).getTimeSlot();
                    AllAppointmentsActivity.getInstance().slotID = doctorTimeSlotsResponses.get(position).getSlotId();
                    AllAppointmentsActivity.getInstance().pickslot.setText(selectedSlot);
                    AllAppointmentsActivity.getInstance().gridViewSlots.setVisibility(View.GONE);
                    AllAppointmentsActivity.getInstance().gridViewProcedure.setVisibility(View.VISIBLE);
                }
            });
        } else if (doctorTimeSlotsResponses.get(position).isBooked() == false) {
            tvSlot.setTextColor(Color.parseColor("#CBC7C7"));
            tvSlot.setEnabled(false);
        }

        return convertView;
    }

    public static GridViewSlotsAdapter getInstance() {
        return gridViewSlotsAdapter;
    }
}
