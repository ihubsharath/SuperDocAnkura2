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
import com.example.superdoc_ankura.utils.BaseActivity;

public class AllAppointmentsHolder extends RecyclerView.ViewHolder {
    //testing

    public TextView tvStatus, tvStatus2;
    public ImageView isFirst, isWalkin;
    public SwipeLayout swipeLayout, swipeLayout2;
    public TextView checkout, markFollowUp;
    public TextView tvTime, tvPatientName, tvApptProcedure, tvTime2, tvPatientName2, tvApptProcedure2;
    public LinearLayout left_to_right_undo, right_to_left, left;
    ;
    public TextView cancel, noshow;
    public LinearLayout startConsult;
    public TextView tvStart, tvConsult, tvUndoText;
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
        tvApptProcedure = itemView.findViewById(R.id.tv_procedure);
        tvTime.setTypeface(BaseActivity.getInstance().faceLight);
        tvPatientName.setTypeface(BaseActivity.getInstance().faceMedium);
        tvApptProcedure.setTypeface(BaseActivity.getInstance().faceBold);

        tvTime2 = itemView.findViewById(R.id.tv_time2);
        tvPatientName2 = itemView.findViewById(R.id.tv_patient_name2);
        tvApptProcedure2 = itemView.findViewById(R.id.tv_procedure2);
        tvTime2.setTypeface(BaseActivity.getInstance().faceLight);
        tvPatientName2.setTypeface(BaseActivity.getInstance().faceMedium);
        tvApptProcedure2.setTypeface(BaseActivity.getInstance().faceBold);

        cancel = itemView.findViewById(R.id.cancel);
        noshow = itemView.findViewById(R.id.noshow);
        startConsult = itemView.findViewById(R.id.tvStartConsult);
        cancel.setTypeface(BaseActivity.getInstance().faceRegular);
        noshow.setTypeface(BaseActivity.getInstance().faceRegular);


        tvStart = itemView.findViewById(R.id.tvStart);
        tvConsult = itemView.findViewById(R.id.tvConsult);
        tvStart.setTypeface(BaseActivity.getInstance().faceRegular);
        tvConsult.setTypeface(BaseActivity.getInstance().faceRegular);
        tvUndoText = itemView.findViewById(R.id.tvUndoText);
        tvUndoText.setTypeface(BaseActivity.getInstance().faceRegular);
//        ivUndoImage  = itemView.findViewById(R.id.ivUndoImage);
        undo = itemView.findViewById(R.id.undo);
        checkout = itemView.findViewById(R.id.checkout);
        markFollowUp = itemView.findViewById(R.id.markFollowUp);
        checkout.setTypeface(BaseActivity.getInstance().faceRegular);
        markFollowUp.setTypeface(BaseActivity.getInstance().faceRegular);


        layout_timer = itemView.findViewById(R.id.layout_timer);
        chronometer = itemView.findViewById(R.id.chronometer);
        chronometer.setTypeface(BaseActivity.getInstance().faceBold);
        layout_undo = itemView.findViewById(R.id.layout_undo);

        isFirst = itemView.findViewById(R.id.isFirst);
        isWalkin = itemView.findViewById(R.id.isWalkin);
        tvStatus = itemView.findViewById(R.id.tv_status);
        tvStatus.setTypeface(BaseActivity.getInstance().faceRegular);
        tvStatus2 = itemView.findViewById(R.id.tv_status_2);
        //testing

    }
}
