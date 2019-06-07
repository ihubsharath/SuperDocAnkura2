package com.example.superdoc_ankura.adapters;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.activities.PatientDetailsActivity;
import com.example.superdoc_ankura.pojos.response.ListOfDoctorContactsResponse;
import com.example.superdoc_ankura.utils.BaseActivity;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ContactAdapter extends BaseAdapter {
    FragmentActivity activity;
    ArrayList<ListOfDoctorContactsResponse> listOfDoctorContactsResponses;

    public ContactAdapter(FragmentActivity activity, ArrayList<ListOfDoctorContactsResponse> listOfDoctorContactsResponses) {
        this.activity = activity;
        this.listOfDoctorContactsResponses = listOfDoctorContactsResponses;
    }

    @Override
    public int getCount() {
        return listOfDoctorContactsResponses.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfDoctorContactsResponses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        convertView = inflater.inflate(R.layout.contact_list_item, parent, false);

        LinearLayout singleContact = convertView.findViewById(R.id.layoutSingleContact);
        TextView contactName = convertView.findViewById(R.id.contactName);
        contactName.setTypeface(BaseActivity.getInstance().faceRegular);
        try {
            contactName.setText(listOfDoctorContactsResponses.get(position).getPatientName());
            singleContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(activity, PatientDetailsActivity.class);
                    i.putExtra("patientid",listOfDoctorContactsResponses.get(position).getPatientId());
                    Log.d("patientid2", listOfDoctorContactsResponses.get(position).getPatientId());
                    activity.startActivity(i);
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }




        return convertView;
    }


}
