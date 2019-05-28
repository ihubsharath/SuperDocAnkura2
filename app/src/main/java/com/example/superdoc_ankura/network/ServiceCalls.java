package com.example.superdoc_ankura.network;


//import com.example.ihubtechnologies.superdocnew.pojos.request.CloseConsultantRequest;
//import com.example.ihubtechnologies.superdocnew.pojos.request.OtpVerificationRequest;
//import com.example.ihubtechnologies.superdocnew.pojos.request.StartConsultantRequest;
//import com.example.ihubtechnologies.superdocnew.pojos.response.AllAppointmentsResponse;
//import com.example.ihubtechnologies.superdocnew.pojos.response.CancelAppointmentResponse;
//import com.example.ihubtechnologies.superdocnew.pojos.response.CloseConsultantResponse;
//import com.example.ihubtechnologies.superdocnew.pojos.response.ClosedAppointmentsResponse;
//import com.example.ihubtechnologies.superdocnew.pojos.response.ConfirmedAppointmentsResponse;

import com.example.superdoc_ankura.pojos.request.CloseConsultantRequest;
import com.example.superdoc_ankura.pojos.request.LoginRequest;
import com.example.superdoc_ankura.pojos.request.StartConsultantRequest;
import com.example.superdoc_ankura.pojos.response.AllAppointmentsResponse;
import com.example.superdoc_ankura.pojos.response.AllCountsResponse;
import com.example.superdoc_ankura.pojos.response.CancelAppointmentResponse;
import com.example.superdoc_ankura.pojos.response.CloseConsultantResponse;
import com.example.superdoc_ankura.pojos.response.ConfirmedAppointmentsResponse;
import com.example.superdoc_ankura.pojos.response.DoctorSessionResponse;
import com.example.superdoc_ankura.pojos.response.GetListOfCancelledAppointmentsResponse;
import com.example.superdoc_ankura.pojos.response.GetListOfNoShowAppointmentsResponse;
import com.example.superdoc_ankura.pojos.response.ListOfTotalCountsWithDatesResponse;
import com.example.superdoc_ankura.pojos.response.LoginResponse;
import com.example.superdoc_ankura.pojos.response.NoShowAppointmentsResponse;
import com.example.superdoc_ankura.pojos.response.StartConsultantResponse;
//import com.example.ihubtechnologies.superdocnew.pojos.response.GetListOfCancelledAppointmentsResponse;
//import com.example.ihubtechnologies.superdocnew.pojos.response.GetListOfNoShowAppointmentsResponse;
//import com.example.ihubtechnologies.superdocnew.pojos.response.LoginResponse;
//import com.example.ihubtechnologies.superdocnew.pojos.response.NoShowAppointmentsResponse;
//import com.example.ihubtechnologies.superdocnew.pojos.response.OtpVerificationResponse;
//import com.example.ihubtechnologies.superdocnew.pojos.response.StartConsultantResponse;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.http.Body;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
//import retrofit2.http.POST;
//import retrofit2.http.Query;

public interface ServiceCalls {

    //
//    @GET("loginWithMobile")
//    Call<LoginResponse> doLogin(@Query("mobilenumber") String s);
//
//    @POST("otpValidation")
//    Call<OtpVerificationResponse> doVerifyOTP(@Body OtpVerificationRequest otpVerificationRequest);
//
    @GET("listOfSessions")
    Call<List<DoctorSessionResponse>> getDoctorSessions(@Query("doctorId") String doctorid);


    @POST("startConsultation")
    Call<StartConsultantResponse> doStartConsultant(@Body StartConsultantRequest startConsultantRequest);


    @GET("listOfCheckinAppointmentsV2")
    Call<List<ConfirmedAppointmentsResponse>> getListOfConfirmedAppointments(@Query("doctorId") String doctorid, @Query("sessionId") String sessionId);


    @POST("closeConsultation")
    Call<CloseConsultantResponse> doCloseConsultant(@Body CloseConsultantRequest closeConsultantRequest);

    @GET("listOfAllAppointmentsV2")
    Call<List<AllAppointmentsResponse>> getAllAppointments(@Query("doctorId") String doctorid, @Query("sessionId") String sessionId);


    @GET("cancelAppointment")
    Call<CancelAppointmentResponse> cancelAppointment(@Query("apptId") int appid);


    @GET("listOfCancelAppointmentsV2")
    Call<List<GetListOfCancelledAppointmentsResponse>> getListOfCancelledAppointments(@Query("doctorId") String doctorId, @Query("sessionId") String sessionId);


    @GET("listOfNoShowAppointmentsV2")
    Call<List<GetListOfNoShowAppointmentsResponse>> getListOfNoShowAppointments(@Query("doctorId") String doctorid, @Query("sessionId") String sessionId);


    @GET("noShowAppointment")
    Call<NoShowAppointmentsResponse> noShowAppointment(@Query("apptId") int appid, @Query("isShow") boolean isShow);

    @POST("doctorLogin")
    Call<LoginResponse> doLogin(@Body LoginRequest loginRequest);

    @GET("listOfAppointmentsCountWithDatesV2")
    Call<ArrayList<ListOfTotalCountsWithDatesResponse>> getListOfTotalCountsWithDates(@Query("doctorId") String doctorid);


    @GET("appointmentCountsV2")
    Call<AllCountsResponse> getAllCounts(@Query("doctorId") String doctorid, @Query("sessionId") String sessionId);
}
