package com.example.superdoc_ankura.pojos.request;

import java.util.List;

public class ListOfSessionsBySelectedDatesRequest {

    /**
     * doctorId : EMPN0001
     * dates : ["2019-05-27"]
     */

    private String doctorId;
    private List<String> dates;

    public ListOfSessionsBySelectedDatesRequest(String doctorId, List<String> dates) {
        this.doctorId = doctorId;
        this.dates = dates;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "ListOfSessionsBySelectedDatesRequest{" +
                "doctorId='" + doctorId + '\'' +
                ", dates=" + dates +
                '}';
    }
}
