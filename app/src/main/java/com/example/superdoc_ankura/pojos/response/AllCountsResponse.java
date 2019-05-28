package com.example.superdoc_ankura.pojos.response;

public class AllCountsResponse {

    /**
     * allCount : 10
     * checkinCount : 0
     * noShowCount : 0
     * cancelCount : 1
     * newCount : 0
     * oldCount : 10
     */

    private int allCount;
    private int checkinCount;
    private int noShowCount;
    private int cancelCount;
    private int newCount;
    private int oldCount;

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

    @Override
    public String toString() {
        return "AllCountsResponse{" +
                "allCount=" + allCount +
                ", checkinCount=" + checkinCount +
                ", noShowCount=" + noShowCount +
                ", cancelCount=" + cancelCount +
                ", newCount=" + newCount +
                ", oldCount=" + oldCount +
                '}';
    }
}
