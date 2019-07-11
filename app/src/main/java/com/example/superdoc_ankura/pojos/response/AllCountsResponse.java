package com.example.superdoc_ankura.pojos.response;

public class AllCountsResponse {

    /**
     * allCount : 29
     * checkinCount : 19
     * noShowCount : 0
     * cancelCount : 2
     * newCount : 0
     * oldCount : 29
     * walkIn : 3
     * avgWaitingTime : 0.4827586206896552
     * avgConsultationTime : 0.0
     */

    private int allCount;
    private int checkinCount;
    private int noShowCount;
    private int cancelCount;
    private int newCount;
    private int oldCount;
    private int walkIn;
    private String avgWaitingTime;
    private String avgConsultationTime;

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public int getCheckinCount() {
        return checkinCount;
    }

    public void setCheckinCount(int checkinCount) {
        this.checkinCount = checkinCount;
    }

    public int getNoShowCount() {
        return noShowCount;
    }

    public void setNoShowCount(int noShowCount) {
        this.noShowCount = noShowCount;
    }

    public int getCancelCount() {
        return cancelCount;
    }

    public void setCancelCount(int cancelCount) {
        this.cancelCount = cancelCount;
    }

    public int getNewCount() {
        return newCount;
    }

    public void setNewCount(int newCount) {
        this.newCount = newCount;
    }

    public int getOldCount() {
        return oldCount;
    }

    public void setOldCount(int oldCount) {
        this.oldCount = oldCount;
    }

    public int getWalkIn() {
        return walkIn;
    }

    public void setWalkIn(int walkIn) {
        this.walkIn = walkIn;
    }

    public String getAvgWaitingTime() {
        return avgWaitingTime;
    }

    public void setAvgWaitingTime(String avgWaitingTime) {
        this.avgWaitingTime = avgWaitingTime;
    }

    public String getAvgConsultationTime() {
        return avgConsultationTime;
    }

    public void setAvgConsultationTime(String avgConsultationTime) {
        this.avgConsultationTime = avgConsultationTime;
    }

    @Override
    public String toString() {
        return "AllCountsResponse{" +
                "allCount=" + allCount +
                ", checkinCount=" + checkinCount +
                ", noShowCount=" + noShowCount +
                ", cancelCount=" + cancelCount +
                ", newCount=" + newCount +
                ", oldCount=" + oldCount +
                ", walkIn=" + walkIn +
                ", avgWaitingTime='" + avgWaitingTime + '\'' +
                ", avgConsultationTime='" + avgConsultationTime + '\'' +
                '}';
    }
}
