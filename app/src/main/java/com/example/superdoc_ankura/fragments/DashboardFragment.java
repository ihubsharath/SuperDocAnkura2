package com.example.superdoc_ankura.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.adapters.DoctorSessionAdapter;
import com.example.superdoc_ankura.pojos.response.DoctorSessionResponse;
import com.example.superdoc_ankura.utils.BaseActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment  {
    RecyclerView rview;
    DoctorSessionAdapter doctorSessionAdapter;
int doctorSessionResponsesSize;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment,container,false);
        rview = view.findViewById(R.id.rview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        rview.setLayoutManager(linearLayoutManager);
        rview.setHasFixedSize(true);
        getDoctorSessions();
        return view;
    }

    private void getDoctorSessions() {
        Call<List<DoctorSessionResponse>> call = ((BaseActivity)getActivity()).serviceCalls.getDoctorSessions("EMPS0001");
        ((BaseActivity)getActivity()).showDialog();
        call.enqueue(new Callback<List<DoctorSessionResponse>>() {
            @Override
            public void onResponse(Call<List<DoctorSessionResponse>> call, Response<List<DoctorSessionResponse>> response) {
                ((BaseActivity)getActivity()).closeDialog();
                if (response.code() == 200) {
                    List<DoctorSessionResponse> doctorSessionResponses = response.body();
                    //doctorSessionResponsesSize = doctorSessionResponsesSize.si
                    if (doctorSessionResponses.size() == 0) {
                        ((BaseActivity)getActivity()).showAlertDialog("No Sessions Found");
                    } else {

                        doctorSessionAdapter = new DoctorSessionAdapter(getActivity(), doctorSessionResponses);
                        rview.setAdapter(doctorSessionAdapter);
                        doctorSessionAdapter.notifyDataSetChanged();
                    }
                } else {
                    ((BaseActivity)getActivity()).showAlertDialog("Error :" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<DoctorSessionResponse>> call, Throwable t) {
                ((BaseActivity)getActivity()).closeDialog();
                ((BaseActivity)getActivity()).showAlertDialog(t.getMessage());
            }
        });
    }
}
