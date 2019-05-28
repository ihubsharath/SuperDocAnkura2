package com.example.superdoc_ankura.pojos.response;

public class ListOfTotalCountsWithDatesResponse {

    /**
     * Date : 2019-05-27
     * appointmentsCount : 11
     */

    private String Date;
    private int appointmentsCount;

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public int getAppointmentsCount() {
        return appointmentsCount;
    }

    public void setAppointmentsCount(int appointmentsCount) {
        this.appointmentsCount = appointmentsCount;
    }

    @Override
    public String toString() {
        return "ListOfTotalCountsWithDatesResponse{" +
                "Date='" + Date + '\'' +
                ", appointmentsCount=" + appointmentsCount +
                '}';
    }
}
