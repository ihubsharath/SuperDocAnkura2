package com.example.superdoc_ankura.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.activities.NotificationActivity;
import com.example.superdoc_ankura.adapters.DoctorSessionAdapter;
import com.example.superdoc_ankura.pojos.response.DoctorSessionResponse;
import com.example.superdoc_ankura.utils.BaseActivity;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {
    RecyclerView rview;
    TextView noDataFound;
    ImageView notification;
    ImageView editProfile;
    DoctorSessionAdapter doctorSessionAdapter;
    int doctorSessionResponsesSize;
    TextView doctorName, doctorSpeciality, doctorStudies;
    ShimmerFrameLayout mShimmerViewContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);
        rview = view.findViewById(R.id.rview);
        mShimmerViewContainer = (ShimmerFrameLayout) view.findViewById(R.id.shimmer_view_container);

        noDataFound = view.findViewById(R.id.noDataFound);
        noDataFound.setTypeface(BaseActivity.getInstance().faceProximaRegular);
        notification = view.findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.getInstance().sessionManager.logout();
            }
        });
        doctorName = view.findViewById(R.id.doctor_name);
        doctorSpeciality = view.findViewById(R.id.doctor_specialities);
        doctorStudies = view.findViewById(R.id.doctor_studies);
        doctorName.setTypeface(BaseActivity.getInstance().faceMedium);
        doctorSpeciality.setTypeface(BaseActivity.getInstance().faceMedium);
        doctorStudies.setTypeface(BaseActivity.getInstance().faceMedium);

        doctorName.setText(BaseActivity.getInstance().sessionManager.getDOCTORNAME());
        doctorSpeciality.setText(BaseActivity.getInstance().sessionManager.getDOCTORSPECIALITIES());
        doctorStudies.setText(BaseActivity.getInstance().sessionManager.getDOCTORSTUDIES());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        rview.setLayoutManager(linearLayoutManager);
        rview.setHasFixedSize(true);
        getDoctorSessions();


        editProfile = view.findViewById(R.id.editProfile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog;
                LayoutInflater inflater = (LayoutInflater) getLayoutInflater();
                View customView = inflater.inflate(R.layout.activity_profile_top_to_bottom_animation, null);

                dialog = new Dialog(getActivity(),R.style.AppTheme_NoActionBar_FullScreen2);
                dialog.setContentView(customView);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationProfile;

                dialog.show();
            }
        });
        return view;
    }

    private void getDoctorSessions() {
        mShimmerViewContainer.startShimmerAnimation();
        Call<List<DoctorSessionResponse>> call = ((BaseActivity) getActivity()).serviceCalls.getDoctorSessions(((BaseActivity) getActivity()).sessionManager.getDOCTORID());
        call.enqueue(new Callback<List<DoctorSessionResponse>>() {
            @Override
            public void onResponse(Call<List<DoctorSessionResponse>> call, Response<List<DoctorSessionResponse>> response) {

                if (response.code() == 200) {
                    List<DoctorSessionResponse> doctorSessionResponses = response.body();
                    Log.d("sessionid", doctorSessionResponses.toString());
                    //doctorSessionResponsesSize = doctorSessionResponsesSize.si
                    if (doctorSessionResponses.size() == 0) {
                        rview.setAdapter(null);
                        noDataFound.setVisibility(View.VISIBLE);
                        rview.setVisibility(View.GONE);
                    } else {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mShimmerViewContainer.stopShimmerAnimation();
                            }
                        }, 500);
                        noDataFound.setVisibility(View.GONE);
                        rview.setVisibility(View.VISIBLE);
                        doctorSessionAdapter = new DoctorSessionAdapter(getActivity(), doctorSessionResponses);
                        rview.setAdapter(doctorSessionAdapter);
                        doctorSessionAdapter.notifyDataSetChanged();
                    }
                } else if (response.code() == 204) {
                    try {
                        ((BaseActivity) getActivity()).showToast("No Sessions Found");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<DoctorSessionResponse>> call, Throwable t) {
                mShimmerViewContainer.stopShimmerAnimation();
                ((BaseActivity) getActivity()).showAlertDialog(t.getMessage());
            }
        });
    }
}
