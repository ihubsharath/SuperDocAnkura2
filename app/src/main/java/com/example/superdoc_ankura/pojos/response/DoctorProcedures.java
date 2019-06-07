package com.example.superdoc_ankura.pojos.response;

public class DoctorProcedures {

    /**
     * id : 1
     * procedureName : Consultation
     * duration : null
     */

    private int id;
    private String procedureName;
    private Object duration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public Object getDuration() {
        return duration;
    }

    public void setDuration(Object duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "DoctorProcedures{" +
                "id=" + id +
                ", procedureName='" + procedureName + '\'' +
                ", duration=" + duration +
                '}';
    }
}
