package com.example.superdoc_ankura.utils;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.network.InternetStatus;
import com.example.superdoc_ankura.network.ServiceCalls;
import com.example.superdoc_ankura.network.ServiceGenaretor;

public class BaseActivity extends AppCompatActivity {
    static  BaseActivity baseActivity;
    public Typeface faceLight, faceRegular, faceBold, faceMedium;
    public InternetStatus internetStatus;
    public ServiceCalls serviceCalls;
    public SessionManager sessionManager;
    private ProgressDialog dialog;
    public AlertDialog.Builder builder;
    public AlertDialog alertDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = this;
        faceLight = Typeface.createFromAsset(getAssets(), "Montserrat-Light.otf");
        faceRegular = Typeface.createFromAsset(getAssets(), "Montserrat-Regular.otf");
        faceBold = Typeface.createFromAsset(getAssets(), "Montserrat-Bold.otf");
        faceMedium = Typeface.createFromAsset(getAssets(), "Montserrat-Medium.otf");
        internetStatus = new InternetStatus(BaseActivity.this);
        sessionManager = new SessionManager(BaseActivity.this);
        serviceCalls = ServiceGenaretor.createService(ServiceCalls.class);
    }
    public static BaseActivity getInstance() {
        return baseActivity;
    }

    @Override
    protected void onStart() {
        super.onStart();
        }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public final void showDialog() {
        dialog = new ProgressDialog(BaseActivity.this,R.style.AlertDialogColor);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(getResources().getString(R.string.loading_text));
        dialog.setCancelable(false);
        dialog.show();
    }

    public final void closeDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
    public final void showAlertDialog(String message) {
        try {
            builder = new AlertDialog.Builder(BaseActivity.this,R.style.CustomDialogTheme);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });
            alertDialog = builder.create();
            alertDialog.setTitle("Message");
            alertDialog.setMessage(message);
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}
