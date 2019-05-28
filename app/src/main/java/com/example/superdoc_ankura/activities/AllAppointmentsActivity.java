package com.example.superdoc_ankura.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.adapters.AllAppointmentsAdapter;
import com.example.superdoc_ankura.adapters.CancelledAppointmentsAdapter;
import com.example.superdoc_ankura.adapters.ConfirmedAppointmentsAdapter;
import com.example.superdoc_ankura.adapters.NoShowAppointmentsAdapter;
import com.example.superdoc_ankura.fragments.GraphFragment;
import com.example.superdoc_ankura.pojos.response.AllAppointmentsResponse;
import com.example.superdoc_ankura.pojos.response.ConfirmedAppointmentsResponse;
import com.example.superdoc_ankura.pojos.response.GetListOfCancelledAppointmentsResponse;
import com.example.superdoc_ankura.pojos.response.GetListOfNoShowAppointmentsResponse;
import com.example.superdoc_ankura.utils.BaseActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAppointmentsActivity extends BaseActivity {
    static AllAppointmentsActivity allAppointmentsActivity;
    int hour2;
    private int hour3;
    LinearLayout llStart, llDelay, llAdd;
    TextView tvStart, tvDelay, tvAdd;
    ImageView ivStart, ivDelay, ivAdd;


    TextView noDataFound;
    LinearLayout layoutSession;
    public RecyclerView rview;
    public LinearLayoutManager linearLayoutManager;
    TextView tvAppointmentsCount, tvHospitalName, tvSessionTime;
    LinearLayout allAppointments, checkinAppointments, noshowAppointments, cancelAppointments;
    TextView allAppointmentsSize, checkinAppointmentsSize, noshowAppointmentsSize, cancelAppointmentsSize;
    public AllAppointmentsAdapter allAppointmentsAdapter;
    public ConfirmedAppointmentsAdapter confirmedAppointmentsAdapter;
    public NoShowAppointmentsAdapter noShowAppointmentsAdapter;
    public CancelledAppointmentsAdapter cancelledAppointmentsAdapter;

    public  String sessionId, AppointmentsCount, OrganizationName, SessionTime;
    int int_allAppointmentsSize, int_noshowAppointmentsSize, int_checkinAppointmentsSize, int_cancelAppointmentsSize;
    public boolean start;
    public boolean stop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_appointments);
        allAppointmentsActivity = this;
        layoutSession = findViewById(R.id.layout_session);
        noDataFound = findViewById(R.id.noDataFound);

        layoutSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }

        });



        llStart = findViewById(R.id.ll_start);
        llDelay = findViewById(R.id.ll_delay);
        llAdd = findViewById(R.id.ll_add);

        ivStart = findViewById(R.id.iv_start);
        ivDelay = findViewById(R.id.iv_delay);
        ivAdd = findViewById(R.id.iv_add);

        tvStart = findViewById(R.id.tv_start);
        tvDelay = findViewById(R.id.tv_delay);
        tvAdd = findViewById(R.id.tv_add);


        llStart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                start = true;

                if (tvStart.getText().toString().equals("START")) {
                    ivStart.setColorFilter(ContextCompat.getColor(AllAppointmentsActivity.this, R.color.pink), android.graphics.PorterDuff.Mode.SRC_IN);
                    tvStart.setText("STOP");
                    tvStart.setTextColor(getResources().getColor(R.color.pink));
                    Snackbar snackbar = Snackbar.make(v, Html.fromHtml("<b>SESSION STARTED</b>"), Snackbar.LENGTH_LONG)
                            .setAction("Action", null)
                            .setActionTextColor(getResources().getColor(R.color.white));
                    View view = snackbar.getView();
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(ContextCompat.getColor(AllAppointmentsActivity.this, R.color.white));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    view.setBackgroundColor(ContextCompat.getColor(AllAppointmentsActivity.this, R.color.colorPrimaryDark));

                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                    params.height = 170;
                    view.setLayoutParams(params);

                    snackbar.show();
                } else if (tvStart.getText().toString().equals("STOP")) {
                    stop = true;
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//


//                    Intent i = new Intent(AllAppointmentsActivity.this, GraphFragment.class);
//                    startActivity(i);
                }
            }
        });
        llDelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog;
                LayoutInflater inflater = (LayoutInflater) getLayoutInflater();
                View customView = inflater.inflate(R.layout.delay_dailog_popup, null);
                dialog = new Dialog(AllAppointmentsActivity.this, R.style.CustomDialogTheme);
                dialog.setContentView(customView);


                dialog.getWindow().setLayout(((getWidth(AllAppointmentsActivity.this) / 100) * 90), ((getHeight(AllAppointmentsActivity.this) / 100) * 80));
                dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.show();
                final TimePicker startTime = dialog.findViewById(R.id.start_time);
                TimePicker endTime = dialog.findViewById(R.id.end_time);


                final TextView tv_start_time = dialog.findViewById(R.id.tv_start_time);
                final TextView tv_end_time = dialog.findViewById(R.id.tv_end_time);
                TextView tv_start = dialog.findViewById(R.id.tv_start);
                TextView tv_end = dialog.findViewById(R.id.tv_end);
                TextView tv_markdelay = dialog.findViewById(R.id.tv_markdelay);
                TextView tvMinuts = dialog.findViewById(R.id.tv_minuts);
                EditText etMinuts = dialog.findViewById(R.id.et_minuts);

//                tvMinuts.setTypeface(faceMedium);
//                etMinuts.setTypeface(faceMedium);
//
//                tv_markdelay.setTypeface(faceMedium);
//
//                tv_start_time.setTypeface(faceBold);
//                tv_end_time.setTypeface(faceBold);
//                tv_start.setTypeface(faceLight);
//                tv_end.setTypeface(faceLight);


                // startTime.setIs24HourView(true);
                int hour, minute;
                String am_pm;
                if (Build.VERSION.SDK_INT >= 23) {
                    hour = startTime.getHour();
                    minute = startTime.getMinute();
                } else {
                    hour = startTime.getCurrentHour();
                    minute = startTime.getCurrentMinute();
                }
//                if(hour > 12) {
//                    am_pm = "PM";
//                    hour = hour - 12;
//                }
//                else
//                {
//                    am_pm="AM";
//                }
                tv_start_time.setText(hour + ":" + minute);
                startTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//                        String am_pm;
//
//                        if(hourOfDay > 12) {
//                            am_pm = "PM";
//                            hour2 = hourOfDay - 12;
//                        }
//                        else
//                        {
//                            am_pm="AM";
//                        }
                        tv_start_time.setText(hourOfDay + ":" + minute);
                    }
                });


                // startTime.setIs24HourView(true);
                int hour2, minute2;
                String am_pm2;
                if (Build.VERSION.SDK_INT >= 23) {
                    hour2 = startTime.getHour();
                    minute2 = startTime.getMinute();
                } else {
                    hour2 = startTime.getCurrentHour();
                    minute2 = startTime.getCurrentMinute();
                }
//                if(hour > 12) {
//                    am_pm = "PM";
//                    hour = hour - 12;
//                }
//                else
//                {
//                    am_pm="AM";
//                }
                tv_end_time.setText(hour2 + ":" + minute2);
                endTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//                        String am_pm;
//
//                        if(hourOfDay > 12) {
//                            am_pm = "PM";
//                            hour2 = hourOfDay - 12;
//                        }
//                        else
//                        {
//                            am_pm="AM";
//                        }
                        tv_end_time.setText(hourOfDay + ":" + minute);
                    }
                });


            }
        });
        llAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog customDialog;

                LayoutInflater inflater = (LayoutInflater) getLayoutInflater();
                View customView = inflater.inflate(R.layout.add_dailog_popup, null);

                customDialog = new Dialog(AllAppointmentsActivity.this, R.style.CustomDialogTheme);
                customDialog.setContentView(customView);

                customDialog.getWindow().setLayout(((getWidth(AllAppointmentsActivity.this) / 100) * 90), ((getHeight(AllAppointmentsActivity.this) / 100) * 80));
                customDialog.getWindow().setGravity(Gravity.CENTER);
                customDialog.show();
//                ivAdd.setColorFilter(ContextCompat.getColor(AllAppointmentsActivity.this, R.color.pink), android.graphics.PorterDuff.Mode.SRC_IN);
//                tvAdd.setTextColor(getResources().getColor(R.color.pink));
            }
        });
        sessionId = getIntent().getExtras().getString("sessionid");

        AppointmentsCount = getIntent().getExtras().getString("AppointmentsCount");
        OrganizationName = getIntent().getExtras().getString("OrganizationName");
        SessionTime = getIntent().getExtras().getString("SessionTime");

        allAppointments = findViewById(R.id.allAppointments);
        allAppointmentsSize = findViewById(R.id.allAppointmentsSize);
        checkinAppointments = findViewById(R.id.checkinAppointments);
        checkinAppointmentsSize = findViewById(R.id.checkinAppointmentsSize);
        noshowAppointments = findViewById(R.id.noshowAppointments);
        noshowAppointmentsSize = findViewById(R.id.noshowAppointmentsSize);
        cancelAppointments = findViewById(R.id.cancelAppointments);
        cancelAppointmentsSize = findViewById(R.id.cancelAppointmentsSize);


        tvAppointmentsCount = findViewById(R.id.tv_appointments_count);
        tvHospitalName = findViewById(R.id.tv_hospital_name);
        tvSessionTime = findViewById(R.id.tv_session_time);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String imageTransitionName = getIntent().getExtras().getString("name");
            layoutSession.setTransitionName(imageTransitionName);
            tvAppointmentsCount.setText(AppointmentsCount);
            tvHospitalName.setText(OrganizationName);
            tvSessionTime.setText(SessionTime);
            supportStartPostponedEnterTransition();

        }


        rview = findViewById(R.id.rview);
        linearLayoutManager = new LinearLayoutManager(AllAppointmentsActivity.this);
        rview.setLayoutManager(linearLayoutManager);
        rview.setHasFixedSize(true);

        getAllAppointmentsSize();
        getlistOfConfirmedAppointmentsSize();
        getlistOfNoShowAppointmentsSize();
        getlistOfCancelledAppointmentsSize();

        getlistOfConfirmedAppointments();

        checkinAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                getlistOfConfirmedAppointments();

                getlistOfCancelledAppointmentsSize();
                getlistOfNoShowAppointmentsSize();
                getlistOfConfirmedAppointmentsSize();
                getAllAppointmentsSize();
            }
        });
        noshowAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                getlistOfNoShowAppointments();

                getlistOfCancelledAppointmentsSize();
                getlistOfNoShowAppointmentsSize();
                getlistOfConfirmedAppointmentsSize();
                getAllAppointmentsSize();
            }
        });
        cancelAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                getlistOfCancelledAppointments();

                getlistOfCancelledAppointmentsSize();
                getlistOfNoShowAppointmentsSize();
                getlistOfConfirmedAppointmentsSize();
                getAllAppointmentsSize();
            }
        });

        allAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                getAllAppointments();

                getlistOfCancelledAppointmentsSize();
                getlistOfNoShowAppointmentsSize();
                getlistOfConfirmedAppointmentsSize();
                getAllAppointmentsSize();
            }
        });


    }

    public static int getWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    private void getlistOfCancelledAppointments() {
        Call<List<GetListOfCancelledAppointmentsResponse>> call = serviceCalls.getListOfCancelledAppointments(sessionManager.getDOCTORID(), sessionId);
        showDialog();
        call.enqueue(new Callback<List<GetListOfCancelledAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<GetListOfCancelledAppointmentsResponse>> call, Response<List<GetListOfCancelledAppointmentsResponse>> response) {
                closeDialog();
                if (response.code() == 200) {
                    List<GetListOfCancelledAppointmentsResponse> cancelledAppointmentsResponseList = response.body();
                    if (cancelledAppointmentsResponseList.size() == 0) {
                        rview.setAdapter(null);
//                        showAlertDialog("No Cancelled Appointments Found");
                        noDataFound.setVisibility(View.VISIBLE);
                        rview.setVisibility(View.GONE);
                    } else {
                        noDataFound.setVisibility(View.GONE);
                        rview.setVisibility(View.VISIBLE);
                        cancelledAppointmentsAdapter = new CancelledAppointmentsAdapter(AllAppointmentsActivity.this, cancelledAppointmentsResponseList);
                        rview.setAdapter(cancelledAppointmentsAdapter);
                        cancelledAppointmentsAdapter.notifyDataSetChanged();
                    }
                } else {
                    showAlertDialog("Error :" + response.code() + "cancel");
                }
            }

            @Override
            public void onFailure(Call<List<GetListOfCancelledAppointmentsResponse>> call, Throwable t) {
                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }

    private void getlistOfNoShowAppointments() {
        Call<List<GetListOfNoShowAppointmentsResponse>> call = serviceCalls.getListOfNoShowAppointments(sessionManager.getDOCTORID(), sessionId);
        showDialog();
        call.enqueue(new Callback<List<GetListOfNoShowAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<GetListOfNoShowAppointmentsResponse>> call, Response<List<GetListOfNoShowAppointmentsResponse>> response) {
                closeDialog();
                if (response.code() == 200) {
                    List<GetListOfNoShowAppointmentsResponse> noShowAppointmentsResponseList = response.body();
                    if (noShowAppointmentsResponseList.size() == 0) {
                        rview.setAdapter(null);
//                        showAlertDialog("No no-show Appointments Found");
                        noDataFound.setVisibility(View.VISIBLE);
                        rview.setVisibility(View.GONE);
                    } else {
                        noDataFound.setVisibility(View.GONE);
                        rview.setVisibility(View.VISIBLE);
                        noShowAppointmentsAdapter = new NoShowAppointmentsAdapter(AllAppointmentsActivity.this, noShowAppointmentsResponseList);
                        rview.setAdapter(noShowAppointmentsAdapter);
                        noShowAppointmentsAdapter.notifyDataSetChanged();
                    }
                } else {
                    showAlertDialog("Error :" + response.code() + "no-show");
                }
            }

            @Override
            public void onFailure(Call<List<GetListOfNoShowAppointmentsResponse>> call, Throwable t) {
                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }

    public void getAllAppointments() {
        Call<List<AllAppointmentsResponse>> call = serviceCalls.getAllAppointments(sessionManager.getDOCTORID(), sessionId);

        showDialog();
        call.enqueue(new Callback<List<AllAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<AllAppointmentsResponse>> call, Response<List<AllAppointmentsResponse>> response) {
                closeDialog();
                if (response.code() == 200) {
                    List<AllAppointmentsResponse> allAppointmentsResponses = response.body();
                    if (allAppointmentsResponses.size() == 0) {
                        rview.setAdapter(null);
//                        showAlertDialog("No Appointments Found");
                        noDataFound.setVisibility(View.VISIBLE);
                        rview.setVisibility(View.GONE);
                    } else {
                        noDataFound.setVisibility(View.GONE);
                        rview.setVisibility(View.VISIBLE);
                        allAppointmentsAdapter = new AllAppointmentsAdapter(AllAppointmentsActivity.this, allAppointmentsResponses);
                        rview.setAdapter(allAppointmentsAdapter);
                        allAppointmentsAdapter.notifyDataSetChanged();


                    }
                } else {
                    showAlertDialog("Error :" + response.code() + "appointments");
                }
            }

            @Override
            public void onFailure(Call<List<AllAppointmentsResponse>> call, Throwable t) {
                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }

    private void getlistOfConfirmedAppointments() {
        Call<List<ConfirmedAppointmentsResponse>> call = serviceCalls.getListOfConfirmedAppointments(sessionManager.getDOCTORID(), sessionId);
//        showDialog();
        call.enqueue(new Callback<List<ConfirmedAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<ConfirmedAppointmentsResponse>> call, Response<List<ConfirmedAppointmentsResponse>> response) {
//                closeDialog();
                if (response.code() == 200) {
                    List<ConfirmedAppointmentsResponse> confirmedAppointmentsResponseList = response.body();
                    if (confirmedAppointmentsResponseList.size() == 0) {
                        rview.setAdapter(null);
                        //showAlertDialog("No Confirmed Appointments Found");
                        noDataFound.setVisibility(View.VISIBLE);
                        rview.setVisibility(View.GONE);
                    } else {
                        noDataFound.setVisibility(View.GONE);
                        rview.setVisibility(View.VISIBLE);
                        confirmedAppointmentsAdapter = new ConfirmedAppointmentsAdapter(AllAppointmentsActivity.this, confirmedAppointmentsResponseList);
                        rview.setAdapter(confirmedAppointmentsAdapter);
                        confirmedAppointmentsAdapter.notifyDataSetChanged();

//                        LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
//                        linearSnapHelper.attachToRecyclerView(rview);

                    }
                } else {
                    showAlertDialog("Error :" + response.code() + "confirmed");
                }
            }

            @Override
            public void onFailure(Call<List<ConfirmedAppointmentsResponse>> call, Throwable t) {
//                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }

    public void getlistOfCancelledAppointmentsSize() {
        Call<List<GetListOfCancelledAppointmentsResponse>> call = serviceCalls.getListOfCancelledAppointments(sessionManager.getDOCTORID(), sessionId);
        call.enqueue(new Callback<List<GetListOfCancelledAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<GetListOfCancelledAppointmentsResponse>> call, Response<List<GetListOfCancelledAppointmentsResponse>> response) {
                if (response.code() == 200) {
                    List<GetListOfCancelledAppointmentsResponse> cancelledAppointmentsResponseList = response.body();
                    int_cancelAppointmentsSize = cancelledAppointmentsResponseList.size();
                    cancelAppointmentsSize.setText(String.valueOf(int_cancelAppointmentsSize));
                }
//                else {
//                    showAlertDialog("Error :" + response.code() + "1");
//                }
            }

            @Override
            public void onFailure(Call<List<GetListOfCancelledAppointmentsResponse>> call, Throwable t) {
                showAlertDialog(t.getMessage());
            }
        });
    }

    public void getlistOfNoShowAppointmentsSize() {
        Call<List<GetListOfNoShowAppointmentsResponse>> call = serviceCalls.getListOfNoShowAppointments(sessionManager.getDOCTORID(), sessionId);
        call.enqueue(new Callback<List<GetListOfNoShowAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<GetListOfNoShowAppointmentsResponse>> call, Response<List<GetListOfNoShowAppointmentsResponse>> response) {
                if (response.code() == 200) {
                    List<GetListOfNoShowAppointmentsResponse> getListOfNoShowAppointmentsResponseList = response.body();
                    int_noshowAppointmentsSize = getListOfNoShowAppointmentsResponseList.size();
                    noshowAppointmentsSize.setText(String.valueOf(int_noshowAppointmentsSize));
                }
//                else {
//                    showAlertDialog("Error :" + response.code() + "no_show_size");
//                }
            }

            @Override
            public void onFailure(Call<List<GetListOfNoShowAppointmentsResponse>> call, Throwable t) {
                showAlertDialog(t.getMessage());
            }
        });
    }

    public void getlistOfConfirmedAppointmentsSize() {
        Call<List<ConfirmedAppointmentsResponse>> call = serviceCalls.getListOfConfirmedAppointments(sessionManager.getDOCTORID(), sessionId);
//        showDialog();
        call.enqueue(new Callback<List<ConfirmedAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<ConfirmedAppointmentsResponse>> call, Response<List<ConfirmedAppointmentsResponse>> response) {
//                closeDialog();
                if (response.code() == 200) {
                    List<ConfirmedAppointmentsResponse> confirmedAppointmentsResponseList = response.body();
                    int_checkinAppointmentsSize = confirmedAppointmentsResponseList.size();
                    checkinAppointmentsSize.setText(String.valueOf(int_checkinAppointmentsSize));
                }
//                else {
//                    showAlertDialog("Error :" + response.code() + "5");
//                }
            }

            @Override
            public void onFailure(Call<List<ConfirmedAppointmentsResponse>> call, Throwable t) {
//                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }

    public void getAllAppointmentsSize() {
        Call<List<AllAppointmentsResponse>> call = serviceCalls.getAllAppointments(sessionManager.getDOCTORID(), sessionId);
//        showDialog();
        call.enqueue(new Callback<List<AllAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<AllAppointmentsResponse>> call, Response<List<AllAppointmentsResponse>> response) {
//                closeDialog();
                if (response.code() == 200) {
                    List<AllAppointmentsResponse> allAppointmentsResponses = response.body();
                    int_allAppointmentsSize = allAppointmentsResponses.size();
                    Log.d("sizeeeee", String.valueOf(int_allAppointmentsSize));
                    allAppointmentsSize.setText(String.valueOf(int_allAppointmentsSize));

                }
//                else {
//                    showAlertDialog("Error :" + response.code() + "7");
//                }
            }

            @Override
            public void onFailure(Call<List<AllAppointmentsResponse>> call, Throwable t) {
//                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }

    public static AllAppointmentsActivity getInstance() {
        return allAppointmentsActivity;
    }

}
