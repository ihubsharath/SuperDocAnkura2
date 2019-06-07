package com.example.superdoc_ankura.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.adapters.PatientDetailsProceduresAdapter;
import com.example.superdoc_ankura.pojos.response.GetPatientContactDetailsResponse;
import com.example.superdoc_ankura.utils.BaseActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientDetailsActivity extends BaseActivity {
    ListView listView;
    TextView textPatientDetails;
    TextView tvAppointment, tvPersonal;
    TextView patientName, visitingSince, last90Days;
    TextView details, name, phoneNo, alternateNo, emailid, address, pincode;
    TextView etName, etPhoneNo, etAlternateNo, etEmailid, etAddress, etPincode;
    LinearLayout layoutAppointment, layoutPersonal;
    String patientId;
    GetPatientContactDetailsResponse.AppointmentsPersonalBean appointmentsPersonalBean;
    List<GetPatientContactDetailsResponse.AppointmentsInLastNinetyDaysBean> appointmentsInLastNinetyDaysBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        patientId = getIntent().getExtras().getString("patientid");
        listView = findViewById(R.id.listview);
        tvAppointment = findViewById(R.id.tv_appointment);
        tvPersonal = findViewById(R.id.tv_personal);
        patientName = findViewById(R.id.textPatientName);
        visitingSince = findViewById(R.id.textVisitingSince);
        last90Days = findViewById(R.id.textLast90Days);
        textPatientDetails = findViewById(R.id.textPatientDetails);


        patientName.setTypeface(faceLight);
        visitingSince.setTypeface(faceBold);
        tvAppointment.setTypeface(faceLight);
        tvPersonal.setTypeface(faceLight);
        last90Days.setTypeface(faceLight);
        textPatientDetails.setTypeface(faceMedium);


        details = findViewById(R.id.textDetails);
        name = findViewById(R.id.name);
        phoneNo = findViewById(R.id.phoneNo);
        alternateNo = findViewById(R.id.alternateNo);
        address = findViewById(R.id.address);
        pincode = findViewById(R.id.pincode);
        emailid = findViewById(R.id.emailid);

        details.setTypeface(faceLight);
        name.setTypeface(faceLight);
        phoneNo.setTypeface(faceLight);
        alternateNo.setTypeface(faceLight);
        address.setTypeface(faceLight);
        pincode.setTypeface(faceLight);
        emailid.setTypeface(faceLight);

        etName = findViewById(R.id.etName);
        etPhoneNo = findViewById(R.id.etPhoneNo);
        etAlternateNo = findViewById(R.id.etAlternateNo);
        etAddress = findViewById(R.id.etAddress);
        etPincode = findViewById(R.id.etPincode);
        etEmailid = findViewById(R.id.etEmailid);

        etName.setTypeface(faceLight);
        etPhoneNo.setTypeface(faceLight);
        etAlternateNo.setTypeface(faceLight);
        etAddress.setTypeface(faceLight);
        etPincode.setTypeface(faceLight);
        etEmailid.setTypeface(faceLight);


        layoutAppointment = findViewById(R.id.layout_appointment);
        layoutPersonal = findViewById(R.id.layout_personal);


        Call<GetPatientContactDetailsResponse> call = serviceCalls.getPatientContactDetails(sessionManager.getDOCTORID(), patientId);
        Log.d("patientdetails",sessionManager.getDOCTORID()+"\n"+patientId);
        call.enqueue(new Callback<GetPatientContactDetailsResponse>() {
            @Override
            public void onResponse(Call<GetPatientContactDetailsResponse> call, Response<GetPatientContactDetailsResponse> response) {
                if (response.code() == 200) {
                    GetPatientContactDetailsResponse getPatientContactDetailsResponse = response.body();
                    patientName.setText(getPatientContactDetailsResponse.getAppointmentsPersonal().getPatientName());
                    visitingSince.setText("Visiting since  "+getPatientContactDetailsResponse.getVisitingSince());
                    appointmentsPersonalBean = getPatientContactDetailsResponse.getAppointmentsPersonal();
                    etName.setText(appointmentsPersonalBean.getPatientName());
                    etPhoneNo.setText(appointmentsPersonalBean.getPhoneNumber());
                    etAlternateNo.setText(appointmentsPersonalBean.getAlternatePhoneNumber());
                    etAddress.setText(appointmentsPersonalBean.getAddress());
                    etPincode.setText(appointmentsPersonalBean.getPincode());
                    etEmailid.setText(appointmentsPersonalBean.getEmail());


                    appointmentsInLastNinetyDaysBeans = getPatientContactDetailsResponse.getAppointmentsInLastNinetyDays();
                    PatientDetailsProceduresAdapter patientDetailsProceduresAdapter =
                            new PatientDetailsProceduresAdapter(PatientDetailsActivity.this, appointmentsInLastNinetyDaysBeans);
                    listView.setAdapter(patientDetailsProceduresAdapter);
                    patientDetailsProceduresAdapter.notifyDataSetChanged();
                } else if (response.code() == 204) {
                    showToast("OPPS something went wrong...");
                }
            }

            @Override
            public void onFailure(Call<GetPatientContactDetailsResponse> call, Throwable t) {
                showAlertDialog(t.getMessage());
            }
        });

        tvAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvPersonal.setBackgroundResource(R.drawable.tv_bottom_line_light);
                tvAppointment.setBackgroundResource(R.drawable.tv_bottom_line_dark);

                if (layoutAppointment.getVisibility() == View.GONE) {
                    layoutAppointment.setVisibility(View.VISIBLE);
                    layoutPersonal.setVisibility(View.GONE);
                }
            }
        });

        tvPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvPersonal.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                tvAppointment.setBackgroundResource(R.drawable.tv_bottom_line_light);

                if (layoutPersonal.getVisibility() == View.GONE) {
                    layoutAppointment.setVisibility(View.GONE);
                    layoutPersonal.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
