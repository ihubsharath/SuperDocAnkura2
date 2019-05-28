package com.example.superdoc_ankura.pojos.response;

public class AllAppointmentsResponse {

    /**
     * apptId : 5
     * patientId : 2_2@JH1602663
     * patientName : VENKATARAMANI
     * apptDate : 2019-05-27
     * apptTime : 19:14
     * apptStatus : CheckedIn
     * procedure : Consultation
     * refferedType : null
     * First : false
     * first : false
     */

    private int apptId;
    private String patientId;
    private String patientName;
    private String apptDate;
    private String apptTime;
    private String apptStatus;
    private String procedure;
    private String refferedType;
    private boolean First;

    public int getApptId() {
        return apptId;
    }

    public void setApptId(int apptId) {
        this.apptId = apptId;
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

    public String getApptDate() {
        return apptDate;
    }

    public void setApptDate(String apptDate) {
        this.apptDate = apptDate;
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

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getRefferedType() {
        return refferedType;
    }

    public void setRefferedType(String refferedType) {
        this.refferedType = refferedType;
    }

    public boolean isFirst() {
        return First;
    }

    public void setFirst(boolean First) {
        this.First = First;
    }

    @Override
    public String toString() {
        return "AllAppointmentsResponse{" +
                "apptId=" + apptId +
                ", patientId='" + patientId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", apptDate='" + apptDate + '\'' +
                ", apptTime='" + apptTime + '\'' +
                ", apptStatus='" + apptStatus + '\'' +
                ", procedure='" + procedure + '\'' +
                ", refferedType='" + refferedType + '\'' +
                ", First=" + First +
                '}';
    }
}
