package com.example.superdoc_ankura.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.superdoc_ankura.R;

public class PatientDetailsActivity extends AppCompatActivity {
TextView tvAppointment,tvPersonal;
LinearLayout layoutAppointment,layoutPersonal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        tvAppointment = findViewById(R.id.tv_appointment);
        tvPersonal = findViewById(R.id.tv_personal);
        layoutAppointment = findViewById(R.id.layout_appointment);
        layoutPersonal = findViewById(R.id.layout_personal);

        tvAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvPersonal.setBackgroundResource(R.drawable.tv_bottom_line_light);
                tvAppointment.setBackgroundResource(R.drawable.tv_bottom_line_dark);

                if (layoutAppointment.getVisibility() == View.GONE){
                    layoutAppointment.setVisibility(View.VISIBLE);
                    layoutPersonal.setVisibility(View.GONE);
                }
            }
        });

        tvPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvPersonal.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                tvAppointment.setBackgroundResource(R.drawable.tv_bottom_line_light);

                if (layoutPersonal.getVisibility() == View.GONE){
                    layoutAppointment.setVisibility(View.GONE);
                    layoutPersonal.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
