package com.example.superdoc_ankura.pojos.response;

public class ListOfSessionsBySelectedDatesResponse {

    /**
     * doctorSessionId : 3
     * todayAppointmentsCount : 10
     * organizationName : Jubilee Hospitals
     * sessionTime : 09:00-12:00
     */

    private int doctorSessionId;
    private int todayAppointmentsCount;
    private String organizationName;
    private String sessionTime;

    public int getDoctorSessionId() {
        return doctorSessionId;
    }

    public void setDoctorSessionId(int doctorSessionId) {
        this.doctorSessionId = doctorSessionId;
    }

    public int getTodayAppointmentsCount() {
        return todayAppointmentsCount;
    }

    public void setTodayAppointmentsCount(int todayAppointmentsCount) {
        this.todayAppointmentsCount = todayAppointmentsCount;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    @Override
    public String toString() {
        return "ListOfSessionsBySelectedDatesResponse{" +
                "doctorSessionId=" + doctorSessionId +
                ", todayAppointmentsCount=" + todayAppointmentsCount +
                ", organizationName='" + organizationName + '\'' +
                ", sessionTime='" + sessionTime + '\'' +
                '}';
    }
}
