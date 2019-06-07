package com.example.superdoc_ankura.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.activities.PatientDetailsActivity;
import com.example.superdoc_ankura.pojos.response.GetPatientContactDetailsResponse;
import com.example.superdoc_ankura.utils.BaseActivity;

import java.util.List;

public class PatientDetailsProceduresAdapter extends BaseAdapter {
    PatientDetailsActivity patientDetailsActivity;
    List<GetPatientContactDetailsResponse.AppointmentsInLastNinetyDaysBean> appointmentsInLastNinetyDaysBeans;
    public PatientDetailsProceduresAdapter(PatientDetailsActivity patientDetailsActivity, List<GetPatientContactDetailsResponse.AppointmentsInLastNinetyDaysBean> appointmentsInLastNinetyDaysBeans) {
        this.patientDetailsActivity = patientDetailsActivity;
        this.appointmentsInLastNinetyDaysBeans = appointmentsInLastNinetyDaysBeans;
    }

    @Override
    public int getCount() {
        return appointmentsInLastNinetyDaysBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return appointmentsInLastNinetyDaysBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        convertView = inflater.inflate(R.layout.contacts_procedures_item,parent,false);
        TextView textProcedure,procedureName;
        textProcedure = convertView.findViewById(R.id.textProcedure);
        procedureName = convertView.findViewById(R.id.procedureName);
        textProcedure.setTypeface(patientDetailsActivity.faceLight);
        procedureName.setTypeface(patientDetailsActivity.faceLight);
        textProcedure.setText(appointmentsInLastNinetyDaysBeans.get(position).getProcedure());
        procedureName.setText(appointmentsInLastNinetyDaysBeans.get(position).getDate());
        return convertView;
    }
}
