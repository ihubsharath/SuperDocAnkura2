package com.example.superdoc_ankura.pojos.response;

public class NoShowAppointmentsResponse {
    /**
     * Msg : Consultation has entered into not show status for this appointment
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
        return "NoShowAppointmentsResponse{" +
                "Msg='" + Msg + '\'' +
                '}';
    }
}
