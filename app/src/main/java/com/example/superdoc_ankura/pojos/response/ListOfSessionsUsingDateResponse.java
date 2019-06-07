package com.example.superdoc_ankura.pojos.response;

public class ListOfSessionsUsingDateResponse {

    /**
     * doctorSessionId : 3
     * todayAppointmentsCount : null
     * organizationName : Jubilee Hospitals
     * sessionTime : 09:00-12:00
     */

    private int doctorSessionId;
    private Object todayAppointmentsCount;
    private String organizationName;
    private String sessionTime;

    public int getDoctorSessionId() {
        return doctorSessionId;
    }

    public void setDoctorSessionId(int doctorSessionId) {
        this.doctorSessionId = doctorSessionId;
    }

    public Object getTodayAppointmentsCount() {
        return todayAppointmentsCount;
    }

    public void setTodayAppointmentsCount(Object todayAppointmentsCount) {
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
        return "ListOfSessionsUsingDateResponse{" +
                "doctorSessionId=" + doctorSessionId +
                ", todayAppointmentsCount=" + todayAppointmentsCount +
                ", organizationName='" + organizationName + '\'' +
                ", sessionTime='" + sessionTime + '\'' +
                '}';
    }
}
