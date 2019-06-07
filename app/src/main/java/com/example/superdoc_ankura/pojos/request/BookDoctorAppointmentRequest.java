package com.example.superdoc_ankura.pojos.request;

public class BookDoctorAppointmentRequest {

    /**
     * slotId : 7132
     * procedureId : 1
     * mobileNumber : 9059139016
     * patientName : Mani
     * emailId : mani.reddy727@gmail.com
     * bookedType : Doctor
     */

    private int slotId;
    private int procedureId;
    private String mobileNumber;
    private String patientName;
    private String emailId;
    private String bookedType;

    public BookDoctorAppointmentRequest(int slotId, int procedureId, String mobileNumber, String patientName, String emailId, String bookedType) {
        this.slotId = slotId;
        this.procedureId = procedureId;
        this.mobileNumber = mobileNumber;
        this.patientName = patientName;
        this.emailId = emailId;
        this.bookedType = bookedType;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(int procedureId) {
        this.procedureId = procedureId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getBookedType() {
        return bookedType;
    }

    public void setBookedType(String bookedType) {
        this.bookedType = bookedType;
    }

    @Override
    public String toString() {
        return "BookDoctorAppointmentRequest{" +
                "slotId=" + slotId +
                ", procedureId=" + procedureId +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", patientName='" + patientName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", bookedType='" + bookedType + '\'' +
                '}';
    }
}
