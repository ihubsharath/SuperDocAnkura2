package com.example.superdoc_ankura.pojos.response;

public class GetNotificationsResponse {

    /**
     * apptId : 3
     * patientName : 00918309136351
     * apptTime : 10:03
     * apptStatus : Pending
     * apptDate : 2019-06-11
     */

    private int apptId;
    private String patientName;
    private String apptTime;
    private String apptStatus;
    private String apptDate;

    public int getApptId() {
        return apptId;
    }

    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
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

    public String getApptDate() {
        return apptDate;
    }

    public void setApptDate(String apptDate) {
        this.apptDate = apptDate;
    }

    @Override
    public String toString() {
        return "GetNotificationsResponse{" +
                "apptId=" + apptId +
                ", apptTime='" + apptTime + '\'' +
                ", apptStatus='" + apptStatus + '\'' +
                ", apptDate='" + apptDate + '\'' +
                '}';
    }
}
