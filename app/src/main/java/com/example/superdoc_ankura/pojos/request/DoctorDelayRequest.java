package com.example.superdoc_ankura.pojos.request;

public class DoctorDelayRequest {

    /**
     * doctorId : EMPN0001
     * sessionId : 7
     * startTime : 15:00
     * endTime : 20:30
     * delayedTime : 30
     */

    private String doctorId;
    private int sessionId;
    private String startTime;
    private String endTime;
    private String delayedTime;

    public DoctorDelayRequest(String doctorId, int sessionId, String startTime, String endTime, String delayedTime) {
        this.doctorId = doctorId;
        this.sessionId = sessionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.delayedTime = delayedTime;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDelayedTime() {
        return delayedTime;
    }

    public void setDelayedTime(String delayedTime) {
        this.delayedTime = delayedTime;
    }

    @Override
    public String toString() {
        return "DoctorDelayRequest{" +
                "doctorId='" + doctorId + '\'' +
                ", sessionId=" + sessionId +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", delayedTime='" + delayedTime + '\'' +
                '}';
    }
}
