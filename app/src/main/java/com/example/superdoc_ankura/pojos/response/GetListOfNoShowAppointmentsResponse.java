package com.example.superdoc_ankura.pojos.response;

import com.google.gson.annotations.SerializedName;

public class GetListOfNoShowAppointmentsResponse {

    /**
     * apptID : 5
     * patientId : 2_2@JH1602663
     * patientName : VENKATARAMANI
     * bookeddate : 25/04/2019
     * apptTime : 19:14
     * apptStatus : Pending
     * startConsultation : false
     * checkedIn : false
     * closeConsultation : false
     * “apptmentShow” : true
     */

    private int apptID;
    private String patientId;
    private String patientName;
    private String bookeddate;
    private String apptTime;
    private String apptStatus;
    private boolean startConsultation;
    private boolean checkedIn;
    private boolean closeConsultation;
    @SerializedName("“apptmentShow”")
    private boolean _$ApptmentShow14; // FIXME check this code

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

    public boolean isStartConsultation() {
        return startConsultation;
    }

    public void setStartConsultation(boolean startConsultation) {
        this.startConsultation = startConsultation;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public boolean isCloseConsultation() {
        return closeConsultation;
    }

    public void setCloseConsultation(boolean closeConsultation) {
        this.closeConsultation = closeConsultation;
    }

    public boolean is_$ApptmentShow14() {
        return _$ApptmentShow14;
    }

    public void set_$ApptmentShow14(boolean _$ApptmentShow14) {
        this._$ApptmentShow14 = _$ApptmentShow14;
    }

    @Override
    public String toString() {
        return "GetListOfNoShowAppointmentsResponse{" +
                "apptID=" + apptID +
                ", patientId='" + patientId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", bookeddate='" + bookeddate + '\'' +
                ", apptTime='" + apptTime + '\'' +
                ", apptStatus='" + apptStatus + '\'' +
                ", startConsultation=" + startConsultation +
                ", checkedIn=" + checkedIn +
                ", closeConsultation=" + closeConsultation +
                ", _$ApptmentShow14=" + _$ApptmentShow14 +
                '}';
    }
}
