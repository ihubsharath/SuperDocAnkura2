package com.example.superdoc_ankura.pojos.response;

public class MarkLeaveResponse {

    /**
     * Msg : Approved Successfully
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
        return "MarkLeaveResponse{" +
                "Msg='" + Msg + '\'' +
                '}';
    }
}
