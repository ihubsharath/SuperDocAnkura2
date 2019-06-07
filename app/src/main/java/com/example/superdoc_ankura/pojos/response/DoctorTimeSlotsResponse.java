package com.example.superdoc_ankura.pojos.response;

public class DoctorTimeSlotsResponse {

    /**
     * slotId : 756
     * timeSlot : 09:00
     * booked : false
     */

    private int slotId;
    private String timeSlot;
    private boolean booked;

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public String toString() {
        return "DoctorTimeSlotsResponse{" +
                "slotId=" + slotId +
                ", timeSlot='" + timeSlot + '\'' +
                ", booked=" + booked +
                '}';
    }
}
