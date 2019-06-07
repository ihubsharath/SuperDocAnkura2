package com.example.superdoc_ankura.pojos.response;

public class ListOfDoctorContactsResponse {

    /**
     * patientId : 2_2@JH1602663
     * patientName : VENKATARAMANI
     * imageData : null
     */

    private String patientId;
    private String patientName;
    private Object imageData;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Object getImageData() {
        return imageData;
    }

    public void setImageData(Object imageData) {
        this.imageData = imageData;
    }

    @Override
    public String toString() {
        return "ListOfDoctorContactsResponse{" +
                "patientId='" + patientId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", imageData=" + imageData +
                '}';
    }
}
