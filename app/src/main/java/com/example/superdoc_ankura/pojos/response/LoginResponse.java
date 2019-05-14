package com.example.superdoc_ankura.pojos.response;

import java.util.List;

public class LoginResponse {

    /**
     * doctorId : EMPMA001
     * doctorName : Manidhar Reddy D
     * doctorExperience : 0
     * doctorFee : null
     * designation : sr doctor
     * organization : Jubilee Hospitals
     * doctorSpeciality : ["General Medicine"]
     * doctorDepartment : ["General Medicine"]
     * doctorstudies : MBBS
     * doctorProfileImg : null
     */

    private String doctorId;
    private String doctorName;
    private String doctorExperience;
    private Object doctorFee;
    private String designation;
    private String organization;
    private String doctorstudies;
    private Object doctorProfileImg;
    private List<String> doctorSpeciality;
    private List<String> doctorDepartment;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorExperience() {
        return doctorExperience;
    }

    public void setDoctorExperience(String doctorExperience) {
        this.doctorExperience = doctorExperience;
    }

    public Object getDoctorFee() {
        return doctorFee;
    }

    public void setDoctorFee(Object doctorFee) {
        this.doctorFee = doctorFee;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDoctorstudies() {
        return doctorstudies;
    }

    public void setDoctorstudies(String doctorstudies) {
        this.doctorstudies = doctorstudies;
    }

    public Object getDoctorProfileImg() {
        return doctorProfileImg;
    }

    public void setDoctorProfileImg(Object doctorProfileImg) {
        this.doctorProfileImg = doctorProfileImg;
    }

    public List<String> getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public void setDoctorSpeciality(List<String> doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }

    public List<String> getDoctorDepartment() {
        return doctorDepartment;
    }

    public void setDoctorDepartment(List<String> doctorDepartment) {
        this.doctorDepartment = doctorDepartment;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "doctorId='" + doctorId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", doctorExperience='" + doctorExperience + '\'' +
                ", doctorFee=" + doctorFee +
                ", designation='" + designation + '\'' +
                ", organization='" + organization + '\'' +
                ", doctorstudies='" + doctorstudies + '\'' +
                ", doctorProfileImg=" + doctorProfileImg +
                ", doctorSpeciality=" + doctorSpeciality +
                ", doctorDepartment=" + doctorDepartment +
                '}';
    }
}
