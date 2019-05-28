package com.example.superdoc_ankura.pojos.response;

import java.io.Serializable;

public class ListOfTotalCountsWithDatesResponse2 implements Serializable {


    /**
     * date : 2019-05-28
     * appointmentsCount : 2
     */

    private String date;
    private int appointmentsCount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAppointmentsCount() {
        return appointmentsCount;
    }

    public void setAppointmentsCount(int appointmentsCount) {
        this.appointmentsCount = appointmentsCount;

    }

    @Override
    public String toString() {
        return "ListOfTotalCountsWithDatesResponse2{" +
                "date='" + date + '\'' +
                ", appointmentsCount=" + appointmentsCount +
                '}';
    }
}
