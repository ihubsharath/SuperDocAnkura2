package com.example.superdoc_ankura.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.activities.DrawableUtils;
import com.example.superdoc_ankura.pojos.response.ListOfTotalCountsWithDatesResponse;
import com.example.superdoc_ankura.utils.BaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarFragment extends android.support.v4.app.Fragment {
    ImageView markLeave;
    Dialog dialog1, dialog2, dialog3;
    List<String> appointmentCount;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_fragment, container, false);


        CalendarView calendarView = view.findViewById(R.id.calendarView);
        markLeave = view.findViewById(R.id.imageMarkLeave);
        List<EventDay> events = new ArrayList<>();
        appointmentCount = new ArrayList<>();
//        Call<ArrayList<ListOfTotalCountsWithDatesResponse>> call =
//                BaseActivity.getInstance().serviceCalls.getListOfTotalCountsWithDates(BaseActivity.getInstance().sessionManager.getDOCTORID());
//        call.enqueue(new Callback<ArrayList<ListOfTotalCountsWithDatesResponse>>() {
//            @Override
//            public void onResponse(Call<ArrayList<ListOfTotalCountsWithDatesResponse>> call, Response<ArrayList<ListOfTotalCountsWithDatesResponse>> response) {
//                ArrayList<ListOfTotalCountsWithDatesResponse> listOfTotalCountsWithDatesResponses = response.body();
//                Log.d("listoftotal",listOfTotalCountsWithDatesResponses.toString());
//                appointmentCount = new ArrayList<>();
//                for (ListOfTotalCountsWithDatesResponse c : listOfTotalCountsWithDatesResponses) {
//                    appointmentCount.add(String.valueOf(c.getAppointmentsCount()));
//                }
//
//                //  MyEventDay myEventDay = …
//
////                Log.d("madhu", listOfTotalCountsWithDatesResponses.toString());
////                String dateStr = null;
////                for (int i = 1; i <= listOfTotalCountsWithDatesResponses.size(); i++) {
////                     dateStr = listOfTotalCountsWithDatesResponses.get(1).getDate();
////                }
////                    String [] dateParts = dateStr.split("-");
////                    String day = dateParts[0];
////                    String month = dateParts[1];
////                    String year = dateParts[2];
////
////                    Calendar cal = Calendar.getInstance();
////                    cal.set(Integer.parseInt(year), Integer.parseInt(month)-1,Integer.parseInt(day));
////                    events.add(new EventDay(cal, DrawableUtils.getCircleDrawableWithText(getActivity(),"9")));
////                    Log.d("dateslist",cal.toString());
////                    Log.d("dates",cal.toString());
//
//                calendarView.setEvents(events);
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<ListOfTotalCountsWithDatesResponse>> call, Throwable t) {
//
//            }
//        });


        appointmentCount.add("10");
        appointmentCount.add("45");
        appointmentCount.add("19");
        appointmentCount.add("21");
        appointmentCount.add("25");

        appointmentCount.add("22");
        appointmentCount.add("30");
        appointmentCount.add("12");
        appointmentCount.add("14");
        appointmentCount.add("15");

        appointmentCount.add("20");
        appointmentCount.add("10");
        appointmentCount.add("45");
        appointmentCount.add("19");
        appointmentCount.add("21");

        appointmentCount.add("25");
        appointmentCount.add("22");
        appointmentCount.add("30");
        appointmentCount.add("12");
        appointmentCount.add("14");

        appointmentCount.add("15");
        appointmentCount.add("20");
        appointmentCount.add("10");
        appointmentCount.add("45");
        appointmentCount.add("19");


        appointmentCount.add("21");
        appointmentCount.add("25");
        appointmentCount.add("22");
        appointmentCount.add("30");
        appointmentCount.add("12");
        appointmentCount.add("14");
        for (int i = 1; i <= 31; i++) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, i);
            //cal.set(Integer.parseInt(year), Integer.parseInt(month)-1,Integer.parseInt(day));
            events.add(new EventDay(cal, DrawableUtils.getCircleDrawableWithText(getActivity(), appointmentCount.get(i - 1))));
            Log.d("dateslist", cal.toString());


        }

        calendarView.setEvents(events);

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar selectedDate = eventDay.getCalendar();
                Log.d("selectedDate", selectedDate.toString());
            }
        });

        markLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Dialog dialog;
//                LayoutInflater inflater = (LayoutInflater) getLayoutInflater();
//                View customView = inflater.inflate(R.layout.mark_leave_dailog_popup, null);
//                dialog = new Dialog(getActivity(), R.style.MarkLeaveDialogTheme);
//                dialog.setContentView(customView);
//                dialog.setCancelable(true);
//
//   dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, ((getHeight(getActivity()) / 100) * 80));
//                dialog.getWindow().setGravity(Gravity.CENTER);
//
//                ColorDrawable back = new ColorDrawable(Color.WHITE);
//                InsetDrawable inset = new InsetDrawable(back, 0, 165, 0, 0);
//                dialog.getWindow().setBackgroundDrawable(inset);
//
//                dialog.show();


                LayoutInflater inflater = (LayoutInflater) getLayoutInflater();
                View customView = inflater.inflate(R.layout.mark_leave_dailog_popup, null);
                dialog1 = new Dialog(getActivity(), R.style.MarkLeaveDialogTheme);
                dialog1.setContentView(customView);
                LinearLayout btnLeaveDays = dialog1.findViewById(R.id.btn_leavedays);
                dialog1.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, ((getHeight(getActivity()) / 100) * 80));
                dialog1.getWindow().setGravity(Gravity.CENTER);

                ColorDrawable back = new ColorDrawable(Color.WHITE);
                InsetDrawable inset = new InsetDrawable(back, 0, 165, 0, 0);
                dialog1.getWindow().setBackgroundDrawable(inset);

                dialog1.show();
                btnLeaveDays.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        dialog1.dismiss();
                        LayoutInflater inflater = (LayoutInflater) getLayoutInflater();
                        View customView = inflater.inflate(R.layout.mark_leave_dailog_popup2, null);
                        dialog2 = new Dialog(getActivity(), R.style.MarkLeaveDialogTheme);
                        dialog2.setContentView(customView);
                        LinearLayout btnSessions = dialog2.findViewById(R.id.btn_sessions);
                        dialog2.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, ((getHeight(getActivity()) / 100) * 80));
                        dialog2.getWindow().setGravity(Gravity.CENTER);

                        ColorDrawable back = new ColorDrawable(Color.WHITE);
                        InsetDrawable inset = new InsetDrawable(back, 0, 165, 0, 0);
                        dialog2.getWindow().setBackgroundDrawable(inset);

                        dialog2.show();
                        btnSessions.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog2.dismiss();
                                LayoutInflater inflater = (LayoutInflater) getLayoutInflater();
                                View customView = inflater.inflate(R.layout.mark_leave_dailog_popup3, null);
                                dialog3 = new Dialog(getActivity(), R.style.MarkLeaveDialogTheme);
                                dialog3.setContentView(customView);
                                LinearLayout btnReason = dialog3.findViewById(R.id.btn_reason);
                                dialog3.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, ((getHeight(getActivity()) / 100) * 80));
                                dialog3.getWindow().setGravity(Gravity.CENTER);

                                ColorDrawable back = new ColorDrawable(Color.WHITE);
                                InsetDrawable inset = new InsetDrawable(back, 0, 165, 0, 0);
                                dialog3.getWindow().setBackgroundDrawable(inset);

                                dialog3.show();
                                btnReason.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog3.dismiss();
                                    }
                                });
                            }
                        });
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
