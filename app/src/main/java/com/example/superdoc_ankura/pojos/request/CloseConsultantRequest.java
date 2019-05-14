package com.example.superdoc_ankura.pojos.request;

public class CloseConsultantRequest {

    /**
     * appId : 120
     * startConsultant : 1
     */

    private int appId;


    public CloseConsultantRequest(int appId) {
        this.appId = appId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "CloseConsultantRequest{" +
                "appId=" + appId +
                '}';
    }
}
