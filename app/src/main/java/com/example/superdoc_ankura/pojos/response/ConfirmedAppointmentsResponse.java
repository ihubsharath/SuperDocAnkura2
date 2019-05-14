package com.example.superdoc_ankura.pojos.response;

public class ConfirmedAppointmentsResponse {

    /**
     * apptID : 120
     * patientId : 1_1@UMR19000338
     * patientName : ANAND  RAO
     * bookeddate : 08/04/2019
     * apptTime : 09:00
     * apptStatus : Confirmed
     */

    private int apptID;
    private String patientId;
    private String patientName;
    private String bookeddate;
    private String apptTime;
    private String apptStatus;

    public ConfirmedAppointmentsResponse(int apptID, String patientId, String patientName, String bookeddate, String apptTime, String apptStatus) {
        this.apptID = apptID;
        this.patientId = patientId;
        this.patientName = patientName;
        this.bookeddate = bookeddate;
        this.apptTime = apptTime;
        this.apptStatus = apptStatus;
    }

    public int getApptID() {
        return apptID;
    }

    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getBookeddate() {
        return bookeddate;
    }

    public void setBookeddate(String bookeddate) {
        this.bookeddate = bookeddate;
    }

    public String getApptTime() {
        return apptTime;
    }

    public void setApptTime(String apptTime) {
        this.apptTime = apptTime;
    }

    public String getApptStatus() {
        return apptStatus;
    }

    public void setApptStatus(String apptStatus) {
        this.apptStatus = apptStatus;
    }
}
