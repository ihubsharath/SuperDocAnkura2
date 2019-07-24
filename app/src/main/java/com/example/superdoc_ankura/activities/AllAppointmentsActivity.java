package com.example.superdoc_ankura.activities;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.adapters.AllAppointmentsAdapter;
import com.example.superdoc_ankura.adapters.CancelledAppointmentsAdapter;
import com.example.superdoc_ankura.adapters.ConfirmedAppointmentsAdapter;
import com.example.superdoc_ankura.adapters.GridViewProceduresAdapter;
import com.example.superdoc_ankura.adapters.GridViewSlotsAdapter;
import com.example.superdoc_ankura.adapters.NoShowAppointmentsAdapter;
import com.example.superdoc_ankura.pojos.request.BookDoctorAppointmentRequest;
import com.example.superdoc_ankura.pojos.request.DoctorDelayRequest;
import com.example.superdoc_ankura.pojos.response.AllAppointmentsResponse;
import com.example.superdoc_ankura.pojos.response.AllCountsResponse;
import com.example.superdoc_ankura.pojos.response.BookDoctorAppointmentResponse;
import com.example.superdoc_ankura.pojos.response.ConfirmedAppointmentsResponse;
import com.example.superdoc_ankura.pojos.response.DoctorDelayResponse;
import com.example.superdoc_ankura.pojos.response.DoctorProcedures;
import com.example.superdoc_ankura.pojos.response.DoctorTimeSlotsResponse;
import com.example.superdoc_ankura.pojos.response.GetListOfCancelledAppointmentsResponse;
import com.example.superdoc_ankura.pojos.response.GetListOfNoShowAppointmentsResponse;
import com.example.superdoc_ankura.utils.BaseActivity;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAppointmentsActivity extends BaseActivity {
    //implements SearchView.OnQueryTextListener
    static AllAppointmentsActivity allAppointmentsActivity;
    int hour2;
    private int hour3;
    LinearLayout llStart, llDelay, llAdd;
    TextView tvStart, tvDelay, tvAdd;
    ImageView ivStart, ivDelay, ivAdd;

    List<AllAppointmentsResponse> allAppointmentsResponses;

    public GridView gridViewSlots, gridViewProcedure;
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

    public String sessionId, AppointmentsCount, OrganizationName, SessionTime;
    int int_allAppointmentsSize, int_noshowAppointmentsSize, int_checkinAppointmentsSize, int_cancelAppointmentsSize;
    public boolean start;
    public boolean stop;
    private int difference;
    private String str_difference;
    private String currentTime1, currentTime2;
    public int slotID;
    public int procedureID;
    public TextView todayDate, pickslot, procedure;
    private String formattedDate;
    ImageView searchIcon;
    CardView cardView;
    SearchView searchView;
    LinearLayout layoutSearchIcon, layoutSearchView;
    TextView cancelSearch;
    ShimmerFrameLayout mShimmerViewContainer;
    private TextView noSlotsFound;
    LinearLayout no_checkin_appointments_view, no_noshow_appointments_view, no_cancel_appointments_view, no_all_appointments_view;
    ImageView circle, circle2, circle3, circle4;
    Animation blinkzoomin, blinkzoomin2, blinkzoomin3, blinkzoomin4;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_appointments);
        allAppointmentsActivity = this;

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        handler = new Handler();
        no_checkin_appointments_view = findViewById(R.id.no_checkin_appointments_view);
        no_noshow_appointments_view = findViewById(R.id.no_noshow_appointments_view);
        no_cancel_appointments_view = findViewById(R.id.no_cancel_appointments_view);
        no_all_appointments_view = findViewById(R.id.no_all_appointments_view);
        layoutSession = findViewById(R.id.layout_session);
        noDataFound = findViewById(R.id.noDataFound);
        noDataFound.setTypeface(faceProximaRegular);
        layoutSearchIcon = findViewById(R.id.layoutSearchIcon);
        layoutSearchView = findViewById(R.id.layoutSearchView);
        cancelSearch = findViewById(R.id.cancelSearch);
        searchIcon = findViewById(R.id.ivSearchIcon);
        cardView = findViewById(R.id.cardView);
        searchView = findViewById(R.id.searchView);
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutSearchIcon.setVisibility(View.GONE);
                layoutSearchView.setVisibility(View.VISIBLE);
            }
        });
        cancelSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutSearchIcon.setVisibility(View.VISIBLE);
                layoutSearchView.setVisibility(View.GONE);
                searchView.setQuery("", false);
            }
        });


        layoutSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }

        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab = findViewById(R.id.fab);
//        circle = findViewById(R.id.circle);
//        circle2 = findViewById(R.id.circle2);
//        circle3 = findViewById(R.id.circle3);
//        circle4 = findViewById(R.id.circle4);

//        blinkzoomin = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.blinkzoomin);
//        // blinkzoomin.setInterpolator(this, android.R.anim.accelerate_interpolator);
//        blinkzoomin2 = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.blinkzoomin2);
//        //blinkzoomin2.setInterpolator(this, android.R.anim.accelerate_interpolator);
//        blinkzoomin3 = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.blinkzoomin3);
//        //blinkzoomin3.setInterpolator(this, android.R.anim.accelerate_interpolator);
//        blinkzoomin4 = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.blinkzoomin4);
        //blinkzoomin4.setInterpolator(this, android.R.anim.accelerate_interpolator);

//        circle.setAnimation(blinkzoomin);
//
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("sharath","sharath");
//                circle2.setAnimation(blinkzoomin2);
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d("sharath","sharath");
//                        circle3.setAnimation(blinkzoomin3);
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                Log.d("sharath","sharath");
//                                circle4.setAnimation(blinkzoomin4);
//
//                            }
//                        },600);
//
//                    }
//                },600);
//            }
//        },600);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
        tvStart.setTypeface(faceRegular);
        tvDelay.setTypeface(faceRegular);
        tvAdd.setTypeface(faceRegular);


        sessionId = getIntent().getExtras().getString("sessionid");
        AppointmentsCount = getIntent().getExtras().getString("AppointmentsCount");
        OrganizationName = getIntent().getExtras().getString("OrganizationName");
        SessionTime = getIntent().getExtras().getString("SessionTime");

        allAppointments = findViewById(R.id.allAppointments);
        allAppointmentsSize = findViewById(R.id.allAppointmentsSize);
        allAppointmentsSize.setTypeface(faceMedium);
        checkinAppointments = findViewById(R.id.checkinAppointments);
        checkinAppointmentsSize = findViewById(R.id.checkinAppointmentsSize);
        checkinAppointmentsSize.setTypeface(faceMedium);
        noshowAppointments = findViewById(R.id.noshowAppointments);
        noshowAppointmentsSize = findViewById(R.id.noshowAppointmentsSize);
        noshowAppointmentsSize.setTypeface(faceMedium);
        cancelAppointments = findViewById(R.id.cancelAppointments);
        cancelAppointmentsSize = findViewById(R.id.cancelAppointmentsSize);
        cancelAppointmentsSize.setTypeface(faceMedium);
        TextView textCheckIn, textNoShow, textCancel, textAll;
        textCheckIn = findViewById(R.id.textCheckIn);
        textNoShow = findViewById(R.id.textNoShow);
        textCancel = findViewById(R.id.textCancel);
        textAll = findViewById(R.id.textAll);
        textCheckIn.setTypeface(faceMedium);
        textNoShow.setTypeface(faceMedium);
        textCancel.setTypeface(faceMedium);
        textAll.setTypeface(faceMedium);


        tvAppointmentsCount = findViewById(R.id.tv_appointments_count);
        tvHospitalName = findViewById(R.id.tv_hospital_name);
        tvSessionTime = findViewById(R.id.tv_session_time);

        tvAppointmentsCount.setTypeface(faceLight);
        tvHospitalName.setTypeface(faceMedium);
        tvSessionTime.setTypeface(faceBold);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String imageTransitionName = getIntent().getExtras().getString("name");
            layoutSession.setTransitionName(imageTransitionName);
            tvAppointmentsCount.setText(sessionId);
            tvHospitalName.setText(OrganizationName);
            tvSessionTime.setText(SessionTime);
            supportStartPostponedEnterTransition();

        }


        llStart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                start = true;
                if (tvStart.getText().toString().equals("START")) {
                    ivStart.setColorFilter(ContextCompat.getColor(AllAppointmentsActivity.this, R.color.pink), android.graphics.PorterDuff.Mode.SRC_IN);
                    tvStart.setText("STOP");
                    tvStart.setTextColor(getResources().getColor(R.color.pink));
                    Snackbar snackbar = Snackbar.make(v, Html.fromHtml("SESSION STARTED"), Snackbar.LENGTH_LONG)
                            .setAction("Action", null)
                            .setActionTextColor(getResources().getColor(R.color.white));
                    View view = snackbar.getView();
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTypeface(faceBold);
                    tv.setTextColor(ContextCompat.getColor(AllAppointmentsActivity.this, R.color.white));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    view.setBackgroundColor(ContextCompat.getColor(AllAppointmentsActivity.this, R.color.colorPrimaryDark));

                    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
                    params.height = 170;
                    view.setLayoutParams(params);

                    snackbar.show();
                } else if (tvStart.getText().toString().equals("STOP")) {
                    stop = true;
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
                View customView = inflater.inflate(R.layout.delay_dialog_popup_2, null);


                dialog = new Dialog(AllAppointmentsActivity.this,R.style.CustomDialogTheme);
                dialog.setContentView(customView);
                LinearLayout custom_dialog_root_layout = dialog.findViewById(R.id.custom_dialog);
//custom_dialog_root_layout.setAnimation(animation);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
                dialog.getWindow().setLayout(((getWidth(AllAppointmentsActivity.this) / 100) * 90), ((getHeight(AllAppointmentsActivity.this) / 100) * 80));
                dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.show();
//                Animation animation = AnimationUtils.loadAnimation(dialog.getContext(),
//                        R.anim.slide_in_bottom_login);
//                customView.setAnimation(animation);

                final TimePicker startTime = dialog.findViewById(R.id.start_time);
                TimePicker endTime = dialog.findViewById(R.id.end_time);
                startTime.setIs24HourView(true);
                endTime.setIs24HourView(true);

                TextView textMarkDelay, textMinuts, textProceed, textStart, textEnd;
                textMarkDelay = dialog.findViewById(R.id.text_markdelay);
                textMinuts = dialog.findViewById(R.id.text_minuts);
                textProceed = dialog.findViewById(R.id.text_proceed);
                textStart = dialog.findViewById(R.id.text_start);
                textEnd = dialog.findViewById(R.id.text_end);
                EditText etMinuts = dialog.findViewById(R.id.et_minuts);
//                ImageView ivEdit = dialog.findViewById(R.id.iv_edit);
                final TextView tv_start_time = dialog.findViewById(R.id.tv_start_time);
                final TextView tv_end_time = dialog.findViewById(R.id.tv_end_time);
                TextView tv_end_at_usual_time = dialog.findViewById(R.id.tv_end_at_usual_time);
                LinearLayout layout_end_at_usual_time = dialog.findViewById(R.id.layout_end_at_usual_time);


                textMarkDelay.setTypeface(faceProximaRegular);
                textMinuts.setTypeface(faceProximaRegular);
                etMinuts.setTypeface(faceProximaRegular);
                textStart.setTypeface(faceProximaRegular);
                textEnd.setTypeface(faceProximaRegular);
                tv_start_time.setTypeface(faceProximaBold);
                tv_end_time.setTypeface(faceProximaBold);
                tv_end_at_usual_time.setTypeface(faceProximaRegular);
                textProceed.setTypeface(faceProximaRegular);

                LinearLayout proceed = dialog.findViewById(R.id.proceed);

                layout_end_at_usual_time.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        Date date = null;
                        try {
                            date = sdf.parse(tv_end_time.getText().toString());
                        } catch (ParseException e) {
                        }
                        Calendar c = Calendar.getInstance();
                        c.setTime(date);


                        endTime.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
                        endTime.setCurrentMinute(c.get(Calendar.MINUTE));

                    }
                });
//                ivEdit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        etMinuts.setEnabled(true);
//                    }
//                });

                String[] session = SessionTime.split("-");
                String str_start_time = session[0];
                String str_end_time = session[1];

                tv_start_time.setText(str_start_time);
                tv_end_time.setText(str_end_time);
                Log.d("timeee", SessionTime.toString() + "\n" + str_start_time.toString() + "\n" + str_end_time.toString());
                String actualTime = str_start_time;

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
                Log.d("timeeeee", hour + ":" + minute);
                String curretnTime = hour + ":" + minute;


                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                try {
                    Date date1 = format.parse(curretnTime);
                    Date date2 = format.parse(actualTime);
                    long mills = date1.getTime() - date2.getTime();
                    Log.d("currentactual", actualTime + "\n" + curretnTime);
                    //New Lines
                    long differenceee = (date1.getTime() - date2.getTime()) / 1000;


                    Log.v("Data1", "" + date1.getTime());
                    Log.v("Data2", "" + date2.getTime());
                    int hours = (int) (mills / (1000 * 60 * 60));
                    int mins = (int) (mills / (1000 * 60)) % 60;

                    difference = (hours * 60) + mins; // updated value every1 second
                    str_difference = hours + ":" + mins; // updated value every1 second
                    Log.d("formatminutsNew", String.valueOf(difference));
                    Log.d("formatminutsOld", String.valueOf(difference));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (difference > 0) {
                    tv_start_time.setBackgroundResource(R.drawable.underline);
                    tv_end_time.setBackgroundResource(R.drawable.underline);

                    SimpleDateFormat formattt = new SimpleDateFormat("HH:mm");
                    try {
                        Date date1 = formattt.parse(curretnTime);
                        Date date2 = formattt.parse(actualTime);
                        long mills = date1.getTime() - date2.getTime();
                        Log.v("Data1", "" + date1.getTime());
                        Log.v("Data2", "" + date2.getTime());
                        int hours = (int) (mills / (1000 * 60 * 60));
                        int mins = (int) (mills / (1000 * 60)) % 60;

                        String diff = hours + ":" + mins; // updated value every1 second
                        int difference2 = (hours * 60) + mins; // updated value every1 second
                        etMinuts.setText(String.valueOf(difference2));


                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

                        Date date3 = timeFormat.parse(curretnTime);
                        Date date4 = timeFormat.parse(diff);

                        long sum = date3.getTime() + date4.getTime();

                        String date5 = timeFormat.format(new Date(sum));
                        Log.d("timeeeeeee", date5);

                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        Date date = null;
                        try {
                            date = sdf.parse(date5);
                        } catch (ParseException e) {
                        }
                        Calendar c = Calendar.getInstance();
                        c.setTime(date);


                        endTime.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
                        endTime.setCurrentMinute(c.get(Calendar.MINUTE));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                } else {
                    etMinuts.setText("0");
                }

//                Timer updateTimer = new Timer();
//                updateTimer.schedule(new TimerTask() {
//                    public void run() {
//                        try {
//                            Date date1 = format.parse(curretnTime);
//                            Date date2 = format.parse(actualTime);
//                            long mills = date1.getTime() - date2.getTime();
//                            int hours = (int) (mills/(1000 * 60 * 60));
//                            int mins = (int) ((mills/(1000*60)) % 60);
//
//                            String diff = hours + ":" + mins; // updated value every1 second
//                            etMinuts.setText(diff);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                }, 0, 1000); // here 1000 means 1000 mills i.e. 1 second

//                if(hour > 12) {
//                    am_pm = "PM";
//                    hour = hour - 12;
//                }
//                else
//                {
//                    am_pm="AM";
//                }
                // tv_start_time.setText(hour + ":" + minute);
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
                        // tv_start_time.setText(hourOfDay + ":" + minute);
                        currentTime1 = hourOfDay + ":" + minute;
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                        try {
                            String curretnTime2 = hourOfDay + ":" + minute;
                            Date date1 = format.parse(curretnTime2);
                            Date date2 = format.parse(actualTime);
                            long mills = date1.getTime() - date2.getTime();
                            Log.v("Data1", "" + date1.getTime());
                            Log.v("Data2", "" + date2.getTime());
                            int hours = (int) (mills / (1000 * 60 * 60));
                            int mins = (int) (mills / (1000 * 60)) % 60;

                            String diff = hours + ":" + mins; // updated value every1 second
                            int difference3 = (hours * 60) + mins;
                            etMinuts.setText(String.valueOf(difference3));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


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
                // tv_end_time.setText(hour2 + ":" + minute2);
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
                        //    tv_end_time.setText(hourOfDay + ":" + minute);
                        currentTime2 = hourOfDay + ":" + minute;
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                        try {
                            Date date1 = format.parse(currentTime2);
                            Date date2 = format.parse(actualTime);
                            long mills = date1.getTime() - date2.getTime();
                            Log.v("Data1", "" + date1.getTime());
                            Log.v("Data2", "" + date2.getTime());
                            int hours = (int) (mills / (1000 * 60 * 60));
                            int mins = (int) (mills / (1000 * 60)) % 60;

                            String diff = hours + ":" + mins; // updated value every1 second
                            // etMinuts.setText(diff);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DoctorDelayRequest doctorDelayRequest = new DoctorDelayRequest(sessionManager.getDOCTORID(),
                                Integer.parseInt(sessionId),
                                currentTime1,
                                currentTime2,
                                String.valueOf(difference)
                        );
                        Call<DoctorDelayResponse> call = serviceCalls.doDoctorDelay(doctorDelayRequest);
                        call.enqueue(new Callback<DoctorDelayResponse>() {
                            @Override
                            public void onResponse(Call<DoctorDelayResponse> call, Response<DoctorDelayResponse> response) {
                                if (response.code() == 200) {
                                    dialog.dismiss();
                                    showAlertDialog(response.body().getMsg());
                                } else if (response.code() == 204) {
                                    showToast("failed");
                                }
                            }

                            @Override
                            public void onFailure(Call<DoctorDelayResponse> call, Throwable t) {
                                showAlertDialog(t.getMessage());
                            }
                        });
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
                customDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
                customDialog.getWindow().setLayout(((getWidth(AllAppointmentsActivity.this) / 100) * 90), ((getHeight(AllAppointmentsActivity.this) / 100) * 80));
                customDialog.getWindow().setGravity(Gravity.CENTER);
                customDialog.show();

//                ImageView ivCloseDialog = customDialog.findViewById(R.id.ivCloseDialog);
                noSlotsFound = customDialog.findViewById(R.id.noDataFound);
                noSlotsFound.setTypeface(faceProximaRegular);
                TextView location, sessionTime, textLocation, textSessionTime;
                location = customDialog.findViewById(R.id.location);
                sessionTime = customDialog.findViewById(R.id.session);
                textLocation = customDialog.findViewById(R.id.tv_location);
                textSessionTime = customDialog.findViewById(R.id.tv_session);
                location.setTypeface(faceBold);
                sessionTime.setTypeface(faceBold);
                textLocation.setTypeface(faceRegular);
                textSessionTime.setTypeface(faceRegular);
                location.setText(OrganizationName);
                sessionTime.setText(SessionTime);
                ImageView ivPreviousDate, ivNextDate;
                ivPreviousDate = customDialog.findViewById(R.id.iv_previous_date);

                ivNextDate = customDialog.findViewById(R.id.iv_next_date);
                todayDate = customDialog.findViewById(R.id.tv_date);
                todayDate.setTypeface(faceRegular);
                pickslot = customDialog.findViewById(R.id.pickslot);
                TextView textPickslot = customDialog.findViewById(R.id.tv_pickslot);
                pickslot.setTypeface(faceProximaRegular);
                textPickslot.setTypeface(faceRegular);
                procedure = customDialog.findViewById(R.id.procedure);
                TextView textProcedure = customDialog.findViewById(R.id.text_procedure);
                procedure.setTypeface(faceProximaRegular);
                textProcedure.setTypeface(faceRegular);
                TextView textPatientDetails, textMobile, textPatientName, textEmailid;
                textPatientDetails = customDialog.findViewById(R.id.text_patient_details);
                textMobile = customDialog.findViewById(R.id.textMobileNumber);
                textPatientName = customDialog.findViewById(R.id.textPatientName);
                textEmailid = customDialog.findViewById(R.id.textEmailId);
                textPatientDetails.setTypeface(faceProximaRegular);
                textMobile.setTypeface(faceProximaRegular);
                textPatientName.setTypeface(faceProximaRegular);
                textEmailid.setTypeface(faceProximaRegular);
                EditText etMobile, etPatientName, etEmailID;


                etMobile = customDialog.findViewById(R.id.et_mobile);
                etPatientName = customDialog.findViewById(R.id.et_patient_name);
                etEmailID = customDialog.findViewById(R.id.et_mailid);

                etMobile.setTypeface(faceProximaRegular);
                etPatientName.setTypeface(faceProximaRegular);
                etEmailID.setTypeface(faceProximaRegular);

                TextView confirmAppointment = customDialog.findViewById(R.id.tv_confirm_appointment);
                confirmAppointment.setTypeface(faceProximaRegular);
                gridViewSlots = customView.findViewById(R.id.gview_slots);
                gridViewProcedure = customView.findViewById(R.id.gview_procedures);
//horizontal calendar view
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                formattedDate = df.format(c.getTime());
                todayDate.setText(formattedDate);

                if (todayDate.getText().toString().equals(formattedDate)) {
                    DrawableCompat.setTint(ivPreviousDate.getDrawable(), ContextCompat.getColor(customDialog.getContext(), R.color.grey));
                    ivPreviousDate.setEnabled(false);
                }

                ivPreviousDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (todayDate.getText().toString().equals(formattedDate)) {
                            DrawableCompat.setTint(ivPreviousDate.getDrawable(), ContextCompat.getColor(customDialog.getContext(), R.color.grey));
                            ivPreviousDate.setEnabled(false);
                        } else {
                            c.add(Calendar.DATE, -1);
                            String previousDate = df.format(c.getTime());
                            todayDate.setText(previousDate);
                            doGetTimeSlots(customDialog);
                            doGetProcedurs(customDialog);
                        }
                    }
                });
                ivNextDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DrawableCompat.setTint(ivPreviousDate.getDrawable(), ContextCompat.getColor(customDialog.getContext(), R.color.colorPrimaryDark));
                        ivPreviousDate.setEnabled(true);

                        c.add(Calendar.DATE, 1);
                        String nextDate = df.format(c.getTime());

                        todayDate.setText(nextDate);
                        doGetTimeSlots(customDialog);
                        doGetProcedurs(customDialog);
                    }
                });
                doGetTimeSlots(customDialog);
                doGetProcedurs(customDialog);

//                ivCloseDialog.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        customDialog.dismiss();
//                    }
//                });
                confirmAppointment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /**
                         * slotId : 7132
                         * procedureId : 1
                         * mobileNumber : 9059139016
                         * patientName : Mani
                         * emailId : mani.reddy727@gmail.com
                         * bookedType : Doctor
                         */

                        BookDoctorAppointmentRequest bookDoctorAppointmentRequest = new BookDoctorAppointmentRequest(
                                slotID,
                                procedureID,
                                etMobile.getText().toString(),
                                etPatientName.getText().toString(),
                                etEmailID.getText().toString(),
                                "Doctor");
                        Log.d("bookdoctorapptrequest", bookDoctorAppointmentRequest.toString());
                        if (slotID == 0) {
                            showToast("please select slot");
                        } else if (procedureID == 0) {
                            showToast("please select procedure");
                        } else if (etMobile.getText().toString().isEmpty()) {
                            etMobile.setError(getResources().getString(R.string.required_field));
                        } else if (etPatientName.getText().toString().isEmpty()) {
                            etPatientName.setError(getResources().getString(R.string.required_field));
                        } else if (etEmailID.getText().toString().isEmpty()) {
                            etEmailID.setError(getResources().getString(R.string.required_field));
                        } else {
                            Call<BookDoctorAppointmentResponse> call1 = serviceCalls.doBookDoctorAppointment(bookDoctorAppointmentRequest);
                            showDialog();
                            call1.enqueue(new Callback<BookDoctorAppointmentResponse>() {
                                @Override
                                public void onResponse(Call<BookDoctorAppointmentResponse> call, Response<BookDoctorAppointmentResponse> response) {
                                    closeDialog();
                                    if (response.code() == 200) {
                                        customDialog.dismiss();
                                        showAlertDialog(response.body().getMsg());
                                        // allAppointmentsAdapter.notifyDataSetChanged();
                                    } else if (response.code() == 204) {
                                        showAlertDialog("Booking failed...please try again");
                                    } else if (response.code() == 800) {
                                        showAlertDialog("Doctor Consultation has failed to book");
                                    }
                                }

                                @Override
                                public void onFailure(Call<BookDoctorAppointmentResponse> call, Throwable t) {
                                    closeDialog();
                                    showAlertDialog(t.getMessage());
                                }
                            });
                        }

                    }
                });
            }

            private void doGetProcedurs(Dialog customDialog) {
                Call<ArrayList<DoctorProcedures>> calll = serviceCalls.getDoctorProcedures();
                calll.enqueue(new Callback<ArrayList<DoctorProcedures>>() {
                    @Override
                    public void onResponse(Call<ArrayList<DoctorProcedures>> call, Response<ArrayList<DoctorProcedures>> response) {
                        if (response.code() == 200) {
                            ArrayList<DoctorProcedures> doctorProcedures = response.body();
                            GridViewProceduresAdapter gridViewProceduresAdapter = new GridViewProceduresAdapter(customDialog.getContext(), doctorProcedures);
                            gridViewProcedure.setAdapter(gridViewProceduresAdapter);
                            gridViewProcedure.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String selectedProcedure = doctorProcedures.get(position).getProcedureName();
                                    procedureID = doctorProcedures.get(position).getId();
                                    procedure.setText(selectedProcedure);
                                    gridViewSlots.setVisibility(View.GONE);
                                    gridViewProcedure.setVisibility(View.GONE);
                                }
                            });
                        } else if (response.code() == 204) {
                            showAlertDialog("No Procedures Found");
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<DoctorProcedures>> call, Throwable t) {
                        showAlertDialog(t.getMessage());
                    }
                });
            }

            private void doGetTimeSlots(Dialog customDialog) {
                Call<ArrayList<DoctorTimeSlotsResponse>> call = serviceCalls.getDoctorTimeSlots(sessionManager.getDOCTORID(), todayDate.getText().toString());
                Log.d("todaydate", todayDate.getText().toString());
                call.enqueue(new Callback<ArrayList<DoctorTimeSlotsResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<DoctorTimeSlotsResponse>> call, Response<ArrayList<DoctorTimeSlotsResponse>> response) {
                        if (response.code() == 200) {
                            noSlotsFound.setVisibility(View.GONE);
                            gridViewSlots.setVisibility(View.VISIBLE);
                            ArrayList<DoctorTimeSlotsResponse> doctorTimeSlotsResponses = response.body();
                            GridViewSlotsAdapter gridViewSlotsAdapter = new GridViewSlotsAdapter(customDialog.getContext(), doctorTimeSlotsResponses);
                            gridViewSlots.setAdapter(gridViewSlotsAdapter);

                        } else if (response.code() == 600) {
                            showToast("OPPS Something went wrong....");
                            noSlotsFound.setVisibility(View.VISIBLE);
                            gridViewSlots.setVisibility(View.GONE);
                        } else if (response.code() == 204) {
                            showToast("No Slots Found");
                            noSlotsFound.setVisibility(View.VISIBLE);
                            gridViewSlots.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<DoctorTimeSlotsResponse>> call, Throwable t) {
                        showAlertDialog(t.getMessage());
                    }
                });
            }
        });


        rview = findViewById(R.id.rview);
        linearLayoutManager = new LinearLayoutManager(AllAppointmentsActivity.this);
        rview.setLayoutManager(linearLayoutManager);
        rview.setHasFixedSize(true);

        getAppointmentsCount();


        getlistOfConfirmedAppointments();

        checkinAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation noshow_animation = AnimationUtils.loadAnimation(AllAppointmentsActivity.this,R.anim.slide_up);
                checkinAppointments.setAnimation(noshow_animation);
                allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                getlistOfConfirmedAppointments();

                getAppointmentsCount();
            }
        });
        noshowAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation noshow_animation = AnimationUtils.loadAnimation(AllAppointmentsActivity.this,R.anim.slide_up);
                noshowAppointments.setAnimation(noshow_animation);
                allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                getlistOfNoShowAppointments();

                getAppointmentsCount();
            }
        });
        cancelAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation noshow_animation = AnimationUtils.loadAnimation(AllAppointmentsActivity.this,R.anim.slide_up);
                cancelAppointments.setAnimation(noshow_animation);
                allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                getlistOfCancelledAppointments();

                getAppointmentsCount();
            }
        });

        allAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation noshow_animation = AnimationUtils.loadAnimation(AllAppointmentsActivity.this,R.anim.slide_up);
                allAppointments.setAnimation(noshow_animation);
                allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                getAllAppointments();

                getAppointmentsCount();
            }
        });


    }


    public void getAppointmentsCount() {
        Call<AllCountsResponse> call = BaseActivity.getInstance().serviceCalls.getAllCounts(sessionManager.getDOCTORID(),
                AllAppointmentsActivity.getInstance().sessionId);
        Log.d("appointmentscount", sessionManager.getDOCTORID() + "\n" +
                AllAppointmentsActivity.getInstance().sessionId);
        call.enqueue(new Callback<AllCountsResponse>() {
            @Override
            public void onResponse(Call<AllCountsResponse> call, Response<AllCountsResponse> response) {
                if (response.code() == 200) {
                    AllCountsResponse allCountsResponse = response.body();
                    Log.d("appointmentscounts",
                            AllAppointmentsActivity.getInstance().sessionId + "\n" + allCountsResponse.toString());
                    allAppointmentsSize.setText(String.valueOf(allCountsResponse.getAllCount()));
                    checkinAppointmentsSize.setText(String.valueOf(allCountsResponse.getCheckinCount()));
                    cancelAppointmentsSize.setText(String.valueOf(allCountsResponse.getCancelCount()));
                    noshowAppointmentsSize.setText(String.valueOf(allCountsResponse.getNoShowCount()));
                } else if (response.code() == 600) {
                    showToast("OPPS Something went wrong....");
                } else if (response.code() == 204) {
                    showToast("No counts found");
                }
            }

            @Override
            public void onFailure(Call<AllCountsResponse> call, Throwable t) {
                showAlertDialog(t.getMessage());
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
//        showDialog();
        mShimmerViewContainer.startShimmerAnimation();
        call.enqueue(new Callback<List<GetListOfCancelledAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<GetListOfCancelledAppointmentsResponse>> call, Response<List<GetListOfCancelledAppointmentsResponse>> response) {
//                closeDialog();
                mShimmerViewContainer.stopShimmerAnimation();
                if (response.code() == 200) {
                    List<GetListOfCancelledAppointmentsResponse> cancelledAppointmentsResponseList = response.body();
                    if (cancelledAppointmentsResponseList.size() == 0) {
                        rview.setAdapter(null);
//                        showAlertDialog("No Cancelled Appointments Found");
                        no_cancel_appointments_view.setVisibility(View.VISIBLE);
                        no_checkin_appointments_view.setVisibility(View.GONE);
                        no_noshow_appointments_view.setVisibility(View.GONE);
                        no_all_appointments_view.setVisibility(View.GONE);
                        rview.setVisibility(View.GONE);
                    } else {
                        no_cancel_appointments_view.setVisibility(View.GONE);
                        no_checkin_appointments_view.setVisibility(View.GONE);
                        no_noshow_appointments_view.setVisibility(View.GONE);
                        no_all_appointments_view.setVisibility(View.GONE);
                        rview.setVisibility(View.VISIBLE);
                        cancelledAppointmentsAdapter = new CancelledAppointmentsAdapter(AllAppointmentsActivity.this, cancelledAppointmentsResponseList);
                        rview.setAdapter(cancelledAppointmentsAdapter);
                        cancelledAppointmentsAdapter.notifyDataSetChanged();
                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                try {
                                    if (cancelledAppointmentsResponseList.toString().toLowerCase().contains(newText.toLowerCase())) {
                                        cancelledAppointmentsAdapter.getFilter().filter(newText.toLowerCase());
                                    } else {
                                        allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                                        checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                                        noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                                        cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                                        getAllAppointments();
                                        getAppointmentsCount();
//                                        Toast.makeText(AllAppointmentsActivity.this, "No Match Found", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                return true;
                            }
                        });
                    }
                } else if (response.code() == 600) {
                    no_cancel_appointments_view.setVisibility(View.VISIBLE);
                    no_checkin_appointments_view.setVisibility(View.GONE);
                    no_noshow_appointments_view.setVisibility(View.GONE);
                    no_all_appointments_view.setVisibility(View.GONE);
                    rview.setVisibility(View.GONE);
                    showToast("OPPS Something went wrong....");
                } else if (response.code() == 204) {
                    no_cancel_appointments_view.setVisibility(View.VISIBLE);
                    no_checkin_appointments_view.setVisibility(View.GONE);
                    no_noshow_appointments_view.setVisibility(View.GONE);
                    no_all_appointments_view.setVisibility(View.GONE);
                    rview.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<GetListOfCancelledAppointmentsResponse>> call, Throwable t) {
                mShimmerViewContainer.stopShimmerAnimation();
                showAlertDialog(t.getMessage());
            }
        });
    }

    private void getlistOfNoShowAppointments() {
        Call<List<GetListOfNoShowAppointmentsResponse>> call = serviceCalls.getListOfNoShowAppointments(sessionManager.getDOCTORID(), sessionId);
//        showDialog();
        mShimmerViewContainer.startShimmerAnimation();
        call.enqueue(new Callback<List<GetListOfNoShowAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<GetListOfNoShowAppointmentsResponse>> call, Response<List<GetListOfNoShowAppointmentsResponse>> response) {
//                closeDialog();
                mShimmerViewContainer.stopShimmerAnimation();
                if (response.code() == 200) {
                    List<GetListOfNoShowAppointmentsResponse> noShowAppointmentsResponseList = response.body();
                    if (noShowAppointmentsResponseList.size() == 0) {
                        rview.setAdapter(null);
//                        showAlertDialog("No no-show Appointments Found");
                        no_noshow_appointments_view.setVisibility(View.VISIBLE);
                        no_cancel_appointments_view.setVisibility(View.GONE);
                        no_checkin_appointments_view.setVisibility(View.GONE);
                        no_all_appointments_view.setVisibility(View.GONE);
                        rview.setVisibility(View.GONE);
                    } else {
                        no_cancel_appointments_view.setVisibility(View.GONE);
                        no_checkin_appointments_view.setVisibility(View.GONE);
                        no_noshow_appointments_view.setVisibility(View.GONE);
                        no_all_appointments_view.setVisibility(View.GONE);
                        rview.setVisibility(View.VISIBLE);
                        noShowAppointmentsAdapter = new NoShowAppointmentsAdapter(AllAppointmentsActivity.this, noShowAppointmentsResponseList);
                        rview.setAdapter(noShowAppointmentsAdapter);
                        noShowAppointmentsAdapter.notifyDataSetChanged();

                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                try {
                                    if (noShowAppointmentsResponseList.toString().toLowerCase().contains(newText.toLowerCase())) {
                                        noShowAppointmentsAdapter.getFilter().filter(newText.toLowerCase());

                                    } else {
                                        allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                                        checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                                        noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                                        cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                                        getAllAppointments();
                                        getAppointmentsCount();
//                                        Toast.makeText(AllAppointmentsActivity.this, "No Match Found", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                return true;
                            }
                        });

                    }
                } else if (response.code() == 600) {
                    no_noshow_appointments_view.setVisibility(View.VISIBLE);
                    no_cancel_appointments_view.setVisibility(View.GONE);
                    no_checkin_appointments_view.setVisibility(View.GONE);
                    no_all_appointments_view.setVisibility(View.GONE);
                    rview.setVisibility(View.GONE);
                    showToast("OPPS Something went wrong....");
                } else if (response.code() == 204) {
                    no_noshow_appointments_view.setVisibility(View.VISIBLE);
                    no_cancel_appointments_view.setVisibility(View.GONE);
                    no_checkin_appointments_view.setVisibility(View.GONE);
                    no_all_appointments_view.setVisibility(View.GONE);
                    rview.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<GetListOfNoShowAppointmentsResponse>> call, Throwable t) {
                mShimmerViewContainer.stopShimmerAnimation();
                showAlertDialog(t.getMessage());
            }
        });
    }

    public void getAllAppointments() {
        Call<List<AllAppointmentsResponse>> call = serviceCalls.getAllAppointments(sessionManager.getDOCTORID(), sessionId);

        mShimmerViewContainer.startShimmerAnimation();
        call.enqueue(new Callback<List<AllAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<AllAppointmentsResponse>> call, Response<List<AllAppointmentsResponse>> response) {
                mShimmerViewContainer.stopShimmerAnimation();
                if (response.code() == 200) {
                    allAppointmentsResponses = response.body();
                    if (allAppointmentsResponses.size() == 0) {
                        rview.setAdapter(null);
//                        showAlertDialog("No Appointments Found");
                        no_all_appointments_view.setVisibility(View.VISIBLE);
                        no_cancel_appointments_view.setVisibility(View.GONE);
                        no_checkin_appointments_view.setVisibility(View.GONE);
                        no_noshow_appointments_view.setVisibility(View.GONE);
                        rview.setVisibility(View.GONE);
                    } else {
                        no_cancel_appointments_view.setVisibility(View.GONE);
                        no_checkin_appointments_view.setVisibility(View.GONE);
                        no_noshow_appointments_view.setVisibility(View.GONE);
                        no_all_appointments_view.setVisibility(View.GONE);
                        rview.setVisibility(View.VISIBLE);
                        allAppointmentsAdapter = new AllAppointmentsAdapter(AllAppointmentsActivity.this, allAppointmentsResponses);
                        rview.setAdapter(allAppointmentsAdapter);
                        allAppointmentsAdapter.notifyDataSetChanged();
                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                try {
                                    if (allAppointmentsResponses.toString().toLowerCase().contains(newText.toLowerCase())) {
                                        allAppointmentsAdapter.getFilter().filter(newText.toLowerCase());
                                    } else {
//                                        allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
//                                        checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
//                                        noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
//                                        cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
//                                        getAllAppointments();
//                                        getAppointmentsCount();
                                        Toast.makeText(AllAppointmentsActivity.this, "No Match Found", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                return true;
                            }
                        });
                    }
                } else if (response.code() == 600) {
                    no_all_appointments_view.setVisibility(View.VISIBLE);
                    no_cancel_appointments_view.setVisibility(View.GONE);
                    no_checkin_appointments_view.setVisibility(View.GONE);
                    no_noshow_appointments_view.setVisibility(View.GONE);
                    rview.setVisibility(View.GONE);
                    showToast("OPPS Something went wrong....");
                } else if (response.code() == 204) {
                    no_all_appointments_view.setVisibility(View.VISIBLE);
                    no_cancel_appointments_view.setVisibility(View.GONE);
                    no_checkin_appointments_view.setVisibility(View.GONE);
                    no_noshow_appointments_view.setVisibility(View.GONE);
                    rview.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<AllAppointmentsResponse>> call, Throwable t) {
                mShimmerViewContainer.stopShimmerAnimation();
                showAlertDialog(t.getMessage());
            }
        });
    }

    private void getlistOfConfirmedAppointments() {
        Call<List<ConfirmedAppointmentsResponse>> call = serviceCalls.getListOfConfirmedAppointments(sessionManager.getDOCTORID(), sessionId);
//        showDialog();
        mShimmerViewContainer.startShimmerAnimation();
        call.enqueue(new Callback<List<ConfirmedAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<ConfirmedAppointmentsResponse>> call, Response<List<ConfirmedAppointmentsResponse>> response) {
//                closeDialog();
                mShimmerViewContainer.stopShimmerAnimation();
                if (response.code() == 200) {
                    List<ConfirmedAppointmentsResponse> confirmedAppointmentsResponseList = response.body();
                    if (confirmedAppointmentsResponseList.size() == 0) {
                        rview.setAdapter(null);
                        //showAlertDialog("No Confirmed Appointments Found");
                        no_checkin_appointments_view.setVisibility(View.VISIBLE);
                        no_cancel_appointments_view.setVisibility(View.GONE);
                        no_noshow_appointments_view.setVisibility(View.GONE);
                        no_all_appointments_view.setVisibility(View.GONE);
                        rview.setVisibility(View.GONE);
                    } else {
                        no_cancel_appointments_view.setVisibility(View.GONE);
                        no_checkin_appointments_view.setVisibility(View.GONE);
                        no_noshow_appointments_view.setVisibility(View.GONE);
                        no_all_appointments_view.setVisibility(View.GONE);
                        rview.setVisibility(View.VISIBLE);
                        confirmedAppointmentsAdapter = new ConfirmedAppointmentsAdapter(AllAppointmentsActivity.this, confirmedAppointmentsResponseList);
                        rview.setAdapter(confirmedAppointmentsAdapter);
                        confirmedAppointmentsAdapter.notifyDataSetChanged();

                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                try {
                                    if (confirmedAppointmentsResponseList.toString().toLowerCase().contains(newText.toLowerCase())) {
                                        confirmedAppointmentsAdapter.getFilter().filter(newText.toLowerCase());
                                    } else {
                                        allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                                        checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                                        noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                                        cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                                        getAllAppointments();
                                        getAppointmentsCount();
//                                        Toast.makeText(AllAppointmentsActivity.this, "No Match Found", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                return true;
                            }
                        });
                    }
                } else if (response.code() == 600) {
                    no_checkin_appointments_view.setVisibility(View.VISIBLE);
                    no_cancel_appointments_view.setVisibility(View.GONE);
                    no_noshow_appointments_view.setVisibility(View.GONE);
                    no_all_appointments_view.setVisibility(View.GONE);
                    rview.setVisibility(View.GONE);
                    showToast("OPPS Something went wrong....");
                } else if (response.code() == 204) {
                    no_checkin_appointments_view.setVisibility(View.VISIBLE);
                    no_cancel_appointments_view.setVisibility(View.GONE);
                    no_noshow_appointments_view.setVisibility(View.GONE);
                    no_all_appointments_view.setVisibility(View.GONE);
                    rview.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<List<ConfirmedAppointmentsResponse>> call, Throwable t) {
                mShimmerViewContainer.stopShimmerAnimation();
                showAlertDialog(t.getMessage());
            }
        });
    }


    public static AllAppointmentsActivity getInstance() {
        return allAppointmentsActivity;
    }


}
