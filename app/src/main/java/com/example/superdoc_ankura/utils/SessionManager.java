package com.example.superdoc_ankura.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.superdoc_ankura.activities.LoginActivity;


/**
 * Created by Aparna on 14-06-2018.
 */

public class SessionManager {
    private static SessionManager sessionManager;
    static SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor,editor2;
    Context context;


    public static final String DOCTORID = "doctorid";
    public static final String DOCTORNAME = "doctorname";
    public static final String HOSPITALNAME = "hospitalname";
    public static final String APPID = null;




    public void setDOCTORNAME(String doctorname) {
        editor.putString(DOCTORNAME, doctorname);
        editor.commit();
    }
    public static String getDOCTORNAME() {
        return sharedPreferences.getString(DOCTORNAME, null);
    }

    public void setHOSPITALNAME(String hospitalname) {
        editor.putString(HOSPITALNAME, hospitalname);
        editor.commit();
    }
    public static String getHOSPITALNAME() {
        return sharedPreferences.getString(HOSPITALNAME, null);
    }

    public void setDOCTORID(String doctorid) {
        editor.putString(DOCTORID, doctorid);
        editor.commit();
    }

    public static String getDOCTORID() {
        return sharedPreferences.getString(DOCTORID, null);
    }

    public void setAPPID(String appid) {
        editor2.putString(APPID, appid);
        editor2.commit();
    }

    public static String getAPPID() {
        return sharedPreferences.getString(APPID, null);
    }


    public static SessionManager getInstance(Context context) {
        if (sessionManager == null) {
            sessionManager = new SessionManager(context);
        }
        return sessionManager;
    }

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("IHUB_DB", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor2 = sharedPreferences.edit();
    }
//public void logout(){
//        editor2.clear();
//        //editor2.commit();
//}
    public void logout() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }
}


