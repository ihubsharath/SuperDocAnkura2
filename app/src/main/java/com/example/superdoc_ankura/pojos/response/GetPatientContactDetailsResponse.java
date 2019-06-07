package com.example.superdoc_ankura.pojos.response;

import java.util.List;

public class GetPatientContactDetailsResponse {

    /**
     * visitingSince : 2019-06-06
     * appointmentsInLastNinetyDays : [{"procedure":"Consultation","date":"2019-06-06"},{"procedure":"Consultation","date":"2019-06-06"},{"procedure":"Consultation","date":"2019-06-06"},{"procedure":"Consultation","date":"2019-06-06"},{"procedure":"Consultation","date":"2019-06-06"},{"procedure":"Consultation","date":"2019-06-06"}]
     * appointmentsPersonal : {"patientName":"VENKATARAMANI  ","phoneNumber":"8374700088","alternatePhoneNumber":null,"email":null,"address":"null,null","pincode":"Not Available"}
     */

    private String visitingSince;
    private AppointmentsPersonalBean appointmentsPersonal;
    private List<AppointmentsInLastNinetyDaysBean> appointmentsInLastNinetyDays;

    public String getVisitingSince() {
        return visitingSince;
    }

    public void setVisitingSince(String visitingSince) {
        this.visitingSince = visitingSince;
    }

    public AppointmentsPersonalBean getAppointmentsPersonal() {
        return appointmentsPersonal;
    }

    public void setAppointmentsPersonal(AppointmentsPersonalBean appointmentsPersonal) {
        this.appointmentsPersonal = appointmentsPersonal;
    }

    public List<AppointmentsInLastNinetyDaysBean> getAppointmentsInLastNinetyDays() {
        return appointmentsInLastNinetyDays;
    }

    public void setAppointmentsInLastNinetyDays(List<AppointmentsInLastNinetyDaysBean> appointmentsInLastNinetyDays) {
        this.appointmentsInLastNinetyDays = appointmentsInLastNinetyDays;
    }

    public static class AppointmentsPersonalBean {
        /**
         * patientName : VENKATARAMANI
         * phoneNumber : 8374700088
         * alternatePhoneNumber : null
         * email : null
         * address : null,null
         * pincode : Not Available
         */

        private String patientName;
        private String phoneNumber;
        private String alternatePhoneNumber;
        private String email;
        private String address;
        private String pincode;

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getAlternatePhoneNumber() {
            return alternatePhoneNumber;
        }

        public void setAlternatePhoneNumber(String alternatePhoneNumber) {
            this.alternatePhoneNumber = alternatePhoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }
    }

    public static class AppointmentsInLastNinetyDaysBean {
        /**
         * procedure : Consultation
         * date : 2019-06-06
         */

        private String procedure;
        private String date;

        public String getProcedure() {
            return procedure;
        }

        public void setProcedure(String procedure) {
            this.procedure = procedure;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    @Override
    public String toString() {
        return "GetPatientContactDetailsResponse{" +
                "visitingSince='" + visitingSince + '\'' +
                ", appointmentsPersonal=" + appointmentsPersonal +
                ", appointmentsInLastNinetyDays=" + appointmentsInLastNinetyDays +
                '}';
    }
}
