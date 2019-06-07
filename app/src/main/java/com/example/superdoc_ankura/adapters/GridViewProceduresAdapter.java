package com.example.superdoc_ankura.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.pojos.response.DoctorProcedures;
import com.example.superdoc_ankura.utils.BaseActivity;

import java.util.ArrayList;

public class GridViewProceduresAdapter extends BaseAdapter {
    Context context;
    ArrayList<DoctorProcedures> doctorProcedures;
    public GridViewProceduresAdapter(Context context, ArrayList<DoctorProcedures> doctorProcedures) {
    this.context = context;
    this.doctorProcedures = doctorProcedures;
    }

    @Override
    public int getCount() {
        return doctorProcedures.size();
    }

    @Override
    public Object getItem(int position) {
        return doctorProcedures.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        convertView = inflater.inflate(R.layout.procedures_screen,parent,false);
        TextView tvProcedure = convertView.findViewById(R.id.tv_procedures);
        tvProcedure.setTypeface(BaseActivity.getInstance().faceProximaRegular);
        tvProcedure.setText(doctorProcedures.get(position).getProcedureName());
        return convertView;
    }
}
