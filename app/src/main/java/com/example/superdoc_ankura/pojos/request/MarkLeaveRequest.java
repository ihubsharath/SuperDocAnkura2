package com.example.superdoc_ankura.pojos.request;

import java.util.List;

public class MarkLeaveRequest {

    /**
     * doctorId : EMPN0001
     * sessionIds : [3,7]
     * sessionDates : ["2019-05-29"]
     * reason : OUT
     */

    private String doctorId;
    private String reason;
    private List<Integer> sessionIds;
    private List<String> sessionDates;

    public MarkLeaveRequest(String doctorId, String reason, List<Integer> sessionIds, List<String> sessionDates) {
        this.doctorId = doctorId;
        this.reason = reason;
        this.sessionIds = sessionIds;
        this.sessionDates = sessionDates;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Integer> getSessionIds() {
        return sessionIds;
    }

    public void setSessionIds(List<Integer> sessionIds) {
        this.sessionIds = sessionIds;
    }

    public List<String> getSessionDates() {
        return sessionDates;
    }

    public void setSessionDates(List<String> sessionDates) {
        this.sessionDates = sessionDates;
    }

    @Override
    public String toString() {
        return "MarkLeaveRequest{" +
                "doctorId='" + doctorId + '\'' +
                ", reason='" + reason + '\'' +
                ", sessionIds=" + sessionIds +
                ", sessionDates=" + sessionDates +
                '}';
    }
}
