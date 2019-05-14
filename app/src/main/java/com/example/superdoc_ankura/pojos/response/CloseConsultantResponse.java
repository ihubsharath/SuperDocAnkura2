package com.example.superdoc_ankura.pojos.response;

public class CloseConsultantResponse {
    /**
     * Msg : Consultation has closed for this appointment
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
        return "CloseConsultantResponse{" +
                "Msg='" + Msg + '\'' +
                '}';
    }
}
