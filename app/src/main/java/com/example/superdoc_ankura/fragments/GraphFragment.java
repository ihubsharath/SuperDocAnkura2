package com.example.superdoc_ankura.fragments;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.utils.BaseActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphFragment extends Fragment {
    private float[] yData = {12, 4 };
    private String[] xData = {"New", "Old"};

    TextView tvSummary, tvHospitalName, tvTime;

    TextView tvAvgWaitTime, tvConsultationDuration;
    TextView one, two, three, four;
    TextView tvAppts, tvWalkins, tvCancel, tvNoShow;
    TextView valueOne, valueTwo, valueThree, valueFour;
    LinearLayout layoutOne, layoutTwo, layoutThree, layoutFour;
    Animation animation;
    PieChart pieChart;
    ArrayList<Integer> colors;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.graph_fragment, container, false);


        tvSummary = view.findViewById(R.id.tv_summary);
        tvHospitalName = view.findViewById(R.id.tv_hospital_name);
        tvTime = view.findViewById(R.id.tv_from_to_time);

        tvSummary.setTypeface(((BaseActivity) getActivity()).faceMedium);
        tvHospitalName.setTypeface(((BaseActivity) getActivity()).faceLight);
        tvTime.setTypeface(((BaseActivity) getActivity()).faceLight);

        tvAvgWaitTime = view.findViewById(R.id.tv_avg_wait_time_value);
        tvConsultationDuration = view.findViewById(R.id.tv_consultaion_duration_value);

        tvAvgWaitTime.setTypeface(((BaseActivity) getActivity()).faceBold);
        tvConsultationDuration.setTypeface(((BaseActivity) getActivity()).faceBold);


        animation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_in_bottom_graph);

        one = view.findViewById(R.id.one);
        two = view.findViewById(R.id.two);
        three = view.findViewById(R.id.three);
        four = view.findViewById(R.id.four);

        tvAppts = view.findViewById(R.id.tvAppts);
        tvWalkins = view.findViewById(R.id.tvWalkins);
        tvCancel = view.findViewById(R.id.tvCancel);
        tvNoShow = view.findViewById(R.id.tvNoshow);
        valueOne = view.findViewById(R.id.value_one);
        valueTwo = view.findViewById(R.id.value_two);
        valueThree = view.findViewById(R.id.value_three);
        valueFour = view.findViewById(R.id.value_four);
        layoutOne = view.findViewById(R.id.layoutOne);
        layoutTwo = view.findViewById(R.id.layoutTwo);
        layoutThree = view.findViewById(R.id.layoutThree);
        layoutFour = view.findViewById(R.id.layoutFour);


        //apptmts = 10,
        //walkins = 6,
        //cancel = 2,
        //noshow = 4

        int count = 50;
        int appointmentsCount = 10;
        //getAppointmentsCount();
        int walkinsCount = 5;
        //getWalkinsCount();
        int cancelCount = 3;
        //getCancelCount();
        int noshowCount = 4;
        //getNoShowCount();

        ViewGroup.LayoutParams params1 = (ViewGroup.LayoutParams) one.getLayoutParams();
        params1.height = appointmentsCount * count;
//        params1.width = 100;
        one.setLayoutParams(params1);

        ViewGroup.LayoutParams params2 = (ViewGroup.LayoutParams) two.getLayoutParams();
        params2.height = walkinsCount * count;
//        params2.width = 100;
        two.setLayoutParams(params2);

        ViewGroup.LayoutParams params3 = (ViewGroup.LayoutParams) three.getLayoutParams();
        params3.height = cancelCount * count;
//        params3.width = 100;
        three.setLayoutParams(params3);

        ViewGroup.LayoutParams params4 = (ViewGroup.LayoutParams) four.getLayoutParams();
        params4.height = noshowCount * count;
//        params4.width = 100;
        four.setLayoutParams(params4);


        layoutOne.setAnimation(animation);
        layoutTwo.setAnimation(animation);
        layoutThree.setAnimation(animation);
        layoutFour.setAnimation(animation);






        //PIE CHART
        int colorOld = Color.parseColor("#2F7FAC");
        int colorNew = Color.parseColor("#14BBD3");

       pieChart = view.findViewById(R.id.piechart);

        ArrayList<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < yData.length; i++) {
            entries.add(new PieEntry(yData[i], xData[i]));
        }

        PieDataSet pieDataSet = new PieDataSet(entries, "Number Of Appointments");

        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

//        pieChart.setEntryLabelColor(colorNew);
        pieChart.setEntryLabelColor(colorOld);

        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        pieDataSet.setValueTextColor(Color.BLACK);


        pieDataSet.setValueLineColor(0xffffff);
        pieDataSet.setUsingSliceColorAsValueLineColor(false);
        pieDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "" + ((int) value);
            }
        });


        pieDataSet.setValueTextSize(12);
        colors = new ArrayList<>();
        colors.add(colorNew);
        colors.add(colorOld);
        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextColors(colors);


        pieChart.animateXY(1500, 1500);
        pieChart.setDescription(null);    // Hide the description
        pieChart.getLegend().setEnabled(false);   // Hide the legends
        pieChart.setHoleRadius(70f);

        pieChart.setData(pieData);
        pieChart.invalidate();


        return view;
    }


}
