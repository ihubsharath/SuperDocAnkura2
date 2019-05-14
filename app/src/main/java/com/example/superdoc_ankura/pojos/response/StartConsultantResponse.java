package com.example.superdoc_ankura.pojos.response;

public class StartConsultantResponse {

    /**
     * Msg : Doctor Consultant has been started for this appointment
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
        return "StartConsultantResponse{" +
                "Msg='" + Msg + '\'' +
                '}';
    }
}
