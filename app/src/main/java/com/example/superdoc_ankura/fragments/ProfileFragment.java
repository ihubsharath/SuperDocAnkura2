package com.example.superdoc_ankura.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.activities.NotificationActivity;
import com.example.superdoc_ankura.utils.BaseActivity;

public class ProfileFragment extends android.support.v4.app.Fragment {
    ImageView notification;
    TextView doctorName, doctorSpeciality, doctorStudies;
    TextView textNotificationSettilngs,textChangePassword,textMarkFollowUp,textReports,textAddClinic,textInvite,textContactSupport;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment,container,false);
        notification = view.findViewById(R.id.notification);
        doctorName = view.findViewById(R.id.doctor_name);
        doctorSpeciality = view.findViewById(R.id.doctor_specialities);
        doctorStudies = view.findViewById(R.id.doctor_studies);
        doctorName.setTypeface(BaseActivity.getInstance().faceMedium);
        doctorSpeciality.setTypeface(BaseActivity.getInstance().faceMedium);
        doctorStudies.setTypeface(BaseActivity.getInstance().faceMedium);

        textNotificationSettilngs = view.findViewById(R.id.textNotificationSettings);
        textChangePassword = view.findViewById(R.id.textChangePassword);
        textMarkFollowUp = view.findViewById(R.id.textMarkFollowUp);
        textReports = view.findViewById(R.id.textReports);
        textAddClinic = view.findViewById(R.id.textAddClinic);
        textInvite = view.findViewById(R.id.textInvite);
        textContactSupport = view.findViewById(R.id.textContactSupport);

        textNotificationSettilngs.setTypeface(BaseActivity.getInstance().faceLight);
        textChangePassword.setTypeface(BaseActivity.getInstance().faceLight);
        textMarkFollowUp.setTypeface(BaseActivity.getInstance().faceLight);
        textReports.setTypeface(BaseActivity.getInstance().faceLight);
        textAddClinic.setTypeface(BaseActivity.getInstance().faceLight);
        textInvite.setTypeface(BaseActivity.getInstance().faceLight);
        textContactSupport.setTypeface(BaseActivity.getInstance().faceLight);

        doctorName.setText(BaseActivity.getInstance().sessionManager.getDOCTORNAME());
        doctorSpeciality.setText(BaseActivity.getInstance().sessionManager.getDOCTORSPECIALITIES());
        doctorStudies.setText(BaseActivity.getInstance().sessionManager.getDOCTORSTUDIES());
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NotificationActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}
