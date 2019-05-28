package com.example.superdoc_ankura.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.activities.PatientDetailsActivity;

public class ContactsFragment extends android.support.v4.app.Fragment {
    LinearLayout layoutSingleContact;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_fragment,container,false);
layoutSingleContact = view.findViewById(R.id.layoutSingleContact);
layoutSingleContact.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(getActivity(), PatientDetailsActivity.class);
        startActivity(i);
    }
});
        return view;
    }
}
