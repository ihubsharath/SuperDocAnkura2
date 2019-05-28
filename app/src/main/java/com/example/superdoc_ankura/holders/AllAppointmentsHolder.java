package com.example.superdoc_ankura.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.example.superdoc_ankura.R;

public class AllAppointmentsHolder extends RecyclerView.ViewHolder {
    //testing




    public SwipeLayout swipeLayout, swipeLayout2;
    public TextView checkout, markFollowUp;
    public TextView tvTime,tvPatientName,tvApptStatus,tvTime2,tvPatientName2,tvApptStatus2;
    public LinearLayout left_to_right_undo,right_to_left, left;;
    public TextView cancel,noshow;
public LinearLayout startConsult;
    public TextView tvStart,tvConsult,tvUndoText;
    public ImageView ivUndoImage;
    public LinearLayout undo;
//public Button checkout;
    public LinearLayout layout_timer;
    public LinearLayout layout_undo;

    public Chronometer chronometer;
    public long pauseOffSet;
    public boolean running;
    public AllAppointmentsHolder(@NonNull View itemView) {
        super(itemView);
        swipeLayout = itemView.findViewById(R.id.swipe);
        swipeLayout2 = itemView.findViewById(R.id.swipe2);

        left_to_right_undo = itemView.findViewById(R.id.left_to_right_undo);
        right_to_left = itemView.findViewById(R.id.right_to_left);
        left = itemView.findViewById(R.id.left);

        tvTime = itemView.findViewById(R.id.tv_time);
        tvPatientName = itemView.findViewById(R.id.tv_patient_name);
        tvApptStatus = itemView.findViewById(R.id.tv_status);

        tvTime2 = itemView.findViewById(R.id.tv_time2);
        tvPatientName2 = itemView.findViewById(R.id.tv_patient_name2);
        tvApptStatus2 = itemView.findViewById(R.id.tv_status2);

        cancel = itemView.findViewById(R.id.cancel);
        noshow = itemView.findViewById(R.id.noshow);
        startConsult = itemView.findViewById(R.id.tvStartConsult);
//        tvStart = itemView.findViewById(R.id.tvStart);
//        tvConsult = itemView.findViewById(R.id.tvConsult);
//        tvUndoText = itemView.findViewById(R.id.tvUndoText);
//        ivUndoImage  = itemView.findViewById(R.id.ivUndoImage);
        undo = itemView.findViewById(R.id.undo);
        checkout = itemView.findViewById(R.id.checkout);
        markFollowUp = itemView.findViewById(R.id.markFollowUp);

        layout_timer = itemView.findViewById(R.id.layout_timer);
        chronometer = itemView.findViewById(R.id.chronometer);
        layout_undo = itemView.findViewById(R.id.layout_undo);



    //testing

    }
}
