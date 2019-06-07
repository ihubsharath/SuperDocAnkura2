package com.example.superdoc_ankura.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.activities.AllAppointmentsActivity;
import com.example.superdoc_ankura.activities.DrawableUtils;
import com.example.superdoc_ankura.activities.NotificationActivity;
import com.example.superdoc_ankura.adapters.ListOfSessionsBySelectedDatesAdapter;
import com.example.superdoc_ankura.adapters.ListOfSessionsUsingDateAdapter;
import com.example.superdoc_ankura.pojos.request.ListOfSessionsBySelectedDatesRequest;
import com.example.superdoc_ankura.pojos.request.MarkLeaveRequest;
import com.example.superdoc_ankura.pojos.response.ListOfSessionsBySelectedDatesResponse;
import com.example.superdoc_ankura.pojos.response.ListOfSessionsUsingDateResponse;
import com.example.superdoc_ankura.pojos.response.ListOfTotalCountsWithDatesResponse;
import com.example.superdoc_ankura.pojos.response.ListOfTotalCountsWithDatesResponse2;
import com.example.superdoc_ankura.pojos.response.MarkLeaveResponse;
import com.example.superdoc_ankura.utils.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarFragment extends android.support.v4.app.Fragment {
    CalendarView calendarView;
    ImageView notification;
    ImageView markLeave;
    RecyclerView rview;
    Dialog dialog1, dialog2, dialog3;
    List<String> appointmentCount;
    String formatted;
    List<EventDay> events;
    ArrayList<String> listOfDates = new ArrayList<>();
    public LinearLayout btnSessions;
    public ArrayList<Integer> sessionIdsList = new ArrayList<>();
    Calendar cal;
    TextView doctorName, doctorSpeciality, doctorStudies;
    List<Calendar> selectedDates;
    TextInputEditText text_reason;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_fragment, container, false);

        notification = view.findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NotificationActivity.class);
                startActivity(i);
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
        rview = view.findViewById(R.id.rview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rview.setLayoutManager(linearLayoutManager);
        rview.setHasFixedSize(true);
        calendarView = view.findViewById(R.id.calendarView);


        cal = Calendar.getInstance();
        calendarView.setMinimumDate(cal);

        markLeave = view.findViewById(R.id.imageMarkLeave);
        events = new ArrayList<>();
        appointmentCount = new ArrayList<>();
        Call<ArrayList<ListOfTotalCountsWithDatesResponse2>> call = BaseActivity.getInstance().serviceCalls.getListOfTotalCountsWithDates(BaseActivity.getInstance().sessionManager.getDOCTORID());
        call.enqueue(new Callback<ArrayList<ListOfTotalCountsWithDatesResponse2>>() {
            @Override
            public void onResponse(Call<ArrayList<ListOfTotalCountsWithDatesResponse2>> call, Response<ArrayList<ListOfTotalCountsWithDatesResponse2>> response) {
                if (response.code() == 200) {
                    ArrayList<ListOfTotalCountsWithDatesResponse2> listOfTotalCountsWithDatesResponses = response.body();
                    for (int i = 0; i < listOfTotalCountsWithDatesResponses.size(); i++) {

                        String dateStr = listOfTotalCountsWithDatesResponses.get(i).getDate();

                        String[] dateParts = dateStr.split("-");
                        String year = dateParts[0];
                        String month = dateParts[1];
                        String day = dateParts[2];

                        Log.d("currentDate", year + month + day);

                        int count = listOfTotalCountsWithDatesResponses.get(i).getAppointmentsCount();

                        cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
                        // cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
                        events.add(new EventDay(cal, DrawableUtils.getCircleDrawableWithText(getActivity(), String.valueOf(count))));
                        calendarView.setEvents(events);

                    }
                } else if (response.code() == 204) {
                    BaseActivity.getInstance().showToast("No Counts Found");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ListOfTotalCountsWithDatesResponse2>> call, Throwable t) {
                BaseActivity.getInstance().showAlertDialog(t.getMessage());
            }
        });

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar calendarDate = eventDay.getCalendar();
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                String selectedDate = format1.format(calendarDate.getTime());
                Log.d("selectedDate", selectedDate);

                Call<ArrayList<ListOfSessionsUsingDateResponse>> call2 = BaseActivity.getInstance().serviceCalls.getListOfSessionsByDate(
                        BaseActivity.getInstance().sessionManager.getDOCTORID(), selectedDate);
                Log.d("requestdata", BaseActivity.getInstance().sessionManager.getDOCTORID() + "\n" + selectedDate);
                call2.enqueue(new Callback<ArrayList<ListOfSessionsUsingDateResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ListOfSessionsUsingDateResponse>> call, Response<ArrayList<ListOfSessionsUsingDateResponse>> response) {
                        if (response.code() == 200) {
                            ArrayList<ListOfSessionsUsingDateResponse> listOfSessionsUsingDateResponses = response.body();
                            if (listOfSessionsUsingDateResponses.size() == 0) {
                                rview.setAdapter(null);
                            } else {
                                ListOfSessionsUsingDateAdapter listOfSessionsUsingDateAdapter = new ListOfSessionsUsingDateAdapter(getActivity(), listOfSessionsUsingDateResponses);
                                rview.setAdapter(listOfSessionsUsingDateAdapter);
                                listOfSessionsUsingDateAdapter.notifyDataSetChanged();
                            }
                        } else if (response.code() == 204) {
                            rview.setAdapter(null);
                            BaseActivity.getInstance().showToast("No Sessions Found" + "\n" + "Error:" + response.code());

                        }


                    }

                    @Override
                    public void onFailure(Call<ArrayList<ListOfSessionsUsingDateResponse>> call, Throwable t) {

                    }
                });

            }
        });

        markLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listOfDates.clear();

                LayoutInflater inflater = (LayoutInflater) getLayoutInflater();
                View customView = inflater.inflate(R.layout.mark_leave_screen, null);
                dialog1 = new Dialog(getActivity(), R.style.MarkLeaveDialogTheme);
                dialog1.setContentView(customView);
                dialog1.setCancelable(true);
                TextView tvLeaveDays, tvSessions, tvReason;
                TextView textSelect = dialog1.findViewById(R.id.textSelect);
                tvLeaveDays = dialog1.findViewById(R.id.tv_leave_days);
                tvSessions = dialog1.findViewById(R.id.tv_sessions);
                tvReason = dialog1.findViewById(R.id.tv_reason);

                textSelect.setTypeface(BaseActivity.getInstance().faceRegular);
                tvLeaveDays.setTypeface(BaseActivity.getInstance().faceRegular);
                tvSessions.setTypeface(BaseActivity.getInstance().faceRegular);
                tvReason.setTypeface(BaseActivity.getInstance().faceRegular);
                TextView textReasonForLeave = dialog1.findViewById(R.id.textReasonForLeave);
                textReasonForLeave.setTypeface(BaseActivity.getInstance().faceRegular);

                Button btnCancel, btnUpdate;
                btnCancel = dialog1.findViewById(R.id.btnCancel);
                btnUpdate = dialog1.findViewById(R.id.btnUpdate);
                btnCancel.setTypeface(BaseActivity.getInstance().faceBold);
                btnUpdate.setTypeface(BaseActivity.getInstance().faceBold);


                text_reason = dialog1.findViewById(R.id.text_reason);
                text_reason.setTypeface(BaseActivity.getInstance().faceRegular);
                LinearLayout layoutLeaveDays, layoutSessions, layoutReason;
                layoutLeaveDays = dialog1.findViewById(R.id.layoutLeaveDays);
                layoutSessions = dialog1.findViewById(R.id.layoutSessions);
                layoutReason = dialog1.findViewById(R.id.layoutReason);
                LinearLayout btnLeaveDays = dialog1.findViewById(R.id.btn_leavedays);
                btnSessions = dialog1.findViewById(R.id.btn_sessions);
                LinearLayout btnReason = dialog1.findViewById(R.id.btn_reason);
                CalendarView calendarViewLeaveDays = dialog1.findViewById(R.id.calendarViewLeaveDays);
                calendarViewLeaveDays.setMinimumDate(cal);
                dialog1.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, ((getHeight(getActivity()) / 100) * 80));
                dialog1.getWindow().setGravity(Gravity.CENTER);

                ColorDrawable back = new ColorDrawable(Color.WHITE);
                InsetDrawable inset = new InsetDrawable(back, 0, 165, 0, 0);
                dialog1.getWindow().setBackgroundDrawable(inset);

                dialog1.show();

                btnLeaveDays.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        selectedDates = calendarViewLeaveDays.getSelectedDates();
                        for (int i = 0; i < selectedDates.size(); i++) {
                            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                            formatted = format1.format(selectedDates.get(i).getTime());
                            listOfDates.add(formatted);

                        }
                        Log.d("listofdates", listOfDates.toString());
                        if (listOfDates.size() == 0) {
                            BaseActivity.getInstance().showToast("please select leavedays");
                        } else {
                            layoutSessions.setVisibility(View.VISIBLE);
                            layoutLeaveDays.setVisibility(View.GONE);
                            layoutReason.setVisibility(View.GONE);
                            tvLeaveDays.setBackgroundResource(R.drawable.tv_bottom_line_light);
                            tvSessions.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                            //layoutReason.setVisibility(View.GONE);

                            RecyclerView rview = dialog1.findViewById(R.id.rview);
                            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(dialog1.getContext());
                            rview.setLayoutManager(linearLayoutManager1);
                            rview.setHasFixedSize(true);
                            ListOfSessionsBySelectedDatesRequest listOfSessionsBySelectedDatesRequest =
                                    new ListOfSessionsBySelectedDatesRequest(BaseActivity.getInstance().sessionManager.getDOCTORID(), listOfDates);
                            Call<ArrayList<ListOfSessionsBySelectedDatesResponse>> call = BaseActivity.getInstance().
                                    serviceCalls.getListOfSessionsBySelectedDates(listOfSessionsBySelectedDatesRequest);
                            call.enqueue(new Callback<ArrayList<ListOfSessionsBySelectedDatesResponse>>() {
                                @Override
                                public void onResponse(Call<ArrayList<ListOfSessionsBySelectedDatesResponse>> call, Response<ArrayList<ListOfSessionsBySelectedDatesResponse>> response) {
                                    if (response.code() == 200) {
                                        ArrayList<ListOfSessionsBySelectedDatesResponse> listOfSessionsBySelectedDatesResponseArrayList = response.body();
                                        if (listOfSessionsBySelectedDatesResponseArrayList.size() == 0) {
                                            BaseActivity.getInstance().showToast("Error:" + response.code());
                                            rview.setAdapter(null);
                                        } else {
                                            ListOfSessionsBySelectedDatesAdapter listOfSessionsBySelectedDatesAdapter =
                                                    new ListOfSessionsBySelectedDatesAdapter(dialog1.getContext(), listOfSessionsBySelectedDatesResponseArrayList);
                                            rview.setAdapter(listOfSessionsBySelectedDatesAdapter);
                                            listOfSessionsBySelectedDatesAdapter.notifyDataSetChanged();


                                        }

                                    } else if (response.code() == 204) {
                                        rview.setAdapter(null);
                                        BaseActivity.getInstance().showToast("No Sessions Found" + "\n" + "Error:" + response.code());
                                    }
                                }

                                @Override
                                public void onFailure(Call<ArrayList<ListOfSessionsBySelectedDatesResponse>> call, Throwable t) {
                                    BaseActivity.getInstance().showAlertDialog(t.getMessage());
                                }
                            });
                        }


                    }
                });

                btnSessions.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            if (ListOfSessionsBySelectedDatesAdapter.getInstance().sessionIdsList.size() == 0) {
                                BaseActivity.getInstance().showToast("please select sessions");
                            } else {
                                layoutSessions.setVisibility(View.GONE);
                                layoutReason.setVisibility(View.VISIBLE);
                                layoutLeaveDays.setVisibility(View.GONE);
                                tvLeaveDays.setBackgroundResource(R.drawable.tv_bottom_line_light);
                                tvSessions.setBackgroundResource(R.drawable.tv_bottom_line_light);
                                tvReason.setBackgroundResource(R.drawable.tv_bottom_line_dark);

                                //sessionIdsList.addAll(ListOfSessionsBySelectedDatesAdapter.getInstance().sessionIdsList);
                                sessionIdsList = ListOfSessionsBySelectedDatesAdapter.getInstance().sessionIdsList;
                                Log.d("sessionids", sessionIdsList.toString());

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                });

                btnReason.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doMarkLeave();
                    }
                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doMarkLeave();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });

                tvLeaveDays.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvLeaveDays.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                        tvSessions.setBackgroundResource(R.drawable.tv_bottom_line_light);
                        tvReason.setBackgroundResource(R.drawable.tv_bottom_line_light);
                        layoutLeaveDays.setVisibility(View.VISIBLE);
                        layoutSessions.setVisibility(View.GONE);
                        layoutReason.setVisibility(View.GONE);
                    }
                });
                tvSessions.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedDates = calendarViewLeaveDays.getSelectedDates();
                        for (int i = 0; i < selectedDates.size(); i++) {
                            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                            formatted = format1.format(selectedDates.get(i).getTime());
                            listOfDates.add(formatted);

                        }
                        Log.d("listofdates", listOfDates.toString());
                        if (listOfDates.size() == 0) {
                            BaseActivity.getInstance().showToast("please select leavedays");
                        } else {
                            layoutSessions.setVisibility(View.VISIBLE);
                            layoutLeaveDays.setVisibility(View.GONE);
                            layoutReason.setVisibility(View.GONE);
                            tvLeaveDays.setBackgroundResource(R.drawable.tv_bottom_line_light);
                            tvSessions.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                            tvReason.setBackgroundResource(R.drawable.tv_bottom_line_light);
                            //layoutReason.setVisibility(View.GONE);

                            RecyclerView rview = dialog1.findViewById(R.id.rview);
                            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(dialog1.getContext());
                            rview.setLayoutManager(linearLayoutManager1);
                            rview.setHasFixedSize(true);
                            ListOfSessionsBySelectedDatesRequest listOfSessionsBySelectedDatesRequest =
                                    new ListOfSessionsBySelectedDatesRequest(BaseActivity.getInstance().sessionManager.getDOCTORID(), listOfDates);
                            Call<ArrayList<ListOfSessionsBySelectedDatesResponse>> call = BaseActivity.getInstance().
                                    serviceCalls.getListOfSessionsBySelectedDates(listOfSessionsBySelectedDatesRequest);
                            call.enqueue(new Callback<ArrayList<ListOfSessionsBySelectedDatesResponse>>() {
                                @Override
                                public void onResponse(Call<ArrayList<ListOfSessionsBySelectedDatesResponse>> call, Response<ArrayList<ListOfSessionsBySelectedDatesResponse>> response) {
                                    if (response.code() == 200) {
                                        ArrayList<ListOfSessionsBySelectedDatesResponse> listOfSessionsBySelectedDatesResponseArrayList = response.body();
                                        if (listOfSessionsBySelectedDatesResponseArrayList.size() == 0) {
                                            BaseActivity.getInstance().showAlertDialog("Error:" + response.code());
                                        } else {
                                            ListOfSessionsBySelectedDatesAdapter listOfSessionsBySelectedDatesAdapter =
                                                    new ListOfSessionsBySelectedDatesAdapter(dialog1.getContext(), listOfSessionsBySelectedDatesResponseArrayList);
                                            rview.setAdapter(listOfSessionsBySelectedDatesAdapter);
                                            listOfSessionsBySelectedDatesAdapter.notifyDataSetChanged();


                                        }

                                    } else if (response.code() == 204) {
                                        BaseActivity.getInstance().showToast("failed");
                                    }
                                }

                                @Override
                                public void onFailure(Call<ArrayList<ListOfSessionsBySelectedDatesResponse>> call, Throwable t) {
                                    BaseActivity.getInstance().showAlertDialog(t.getMessage());
                                }
                            });
                        }


                    }
                });
                tvReason.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (ListOfSessionsBySelectedDatesAdapter.getInstance().sessionIdsList.size() == 0) {
                            BaseActivity.getInstance().showToast("please select sessions");
                        } else {
                            layoutSessions.setVisibility(View.GONE);
                            layoutReason.setVisibility(View.VISIBLE);
                            layoutLeaveDays.setVisibility(View.GONE);
                            tvLeaveDays.setBackgroundResource(R.drawable.tv_bottom_line_light);
                            tvSessions.setBackgroundResource(R.drawable.tv_bottom_line_light);
                            tvReason.setBackgroundResource(R.drawable.tv_bottom_line_dark);

                            //sessionIdsList.addAll(ListOfSessionsBySelectedDatesAdapter.getInstance().sessionIdsList);
                            sessionIdsList = ListOfSessionsBySelectedDatesAdapter.getInstance().sessionIdsList;
                            Log.d("sessionids", sessionIdsList.toString());

                        }
                    }
                });
            }

            private void doMarkLeave() {

                MarkLeaveRequest markLeaveRequest = new MarkLeaveRequest(BaseActivity.getInstance().sessionManager.getDOCTORID(),
                        text_reason.getText().toString(), sessionIdsList, listOfDates);
                Log.d("markleaverequest", markLeaveRequest.toString());
                Call<MarkLeaveResponse> call = BaseActivity.getInstance().serviceCalls.doMarkLeave(markLeaveRequest);
                call.enqueue(new Callback<MarkLeaveResponse>() {
                    @Override
                    public void onResponse(Call<MarkLeaveResponse> call, Response<MarkLeaveResponse> response) {
                        if (response.code() == 200) {
                            dialog1.dismiss();
                            BaseActivity.getInstance().showAlertDialog("Approved Successfully");
                        } else if (response.code() == 204) {
                            BaseActivity.getInstance().showToast("failed");
                        }

                    }

                    @Override
                    public void onFailure(Call<MarkLeaveResponse> call, Throwable t) {
                        BaseActivity.getInstance().showAlertDialog(t.getMessage());
                    }
                });
            }
        });


        return view;
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


}
