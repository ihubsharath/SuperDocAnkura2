package com.example.superdoc_ankura.pojos.response;

public class BookDoctorAppointmentResponse {

    /**
     * Msg : Doctor Consultation has been booked successfully
     */

    private String Msg;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    @Override
    public String toString() {
        return "BookDoctorAppointmentResponse{" +
                "Msg='" + Msg + '\'' +
                '}';
    }
}
