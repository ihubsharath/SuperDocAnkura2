package com.example.superdoc_ankura.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.pojos.request.LoginRequest;
import com.example.superdoc_ankura.pojos.response.LoginResponse;
import com.example.superdoc_ankura.utils.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    LinearLayout loginButton, ll;
    Animation animation, bluetoothAnim;
    ImageView bluetooth;
    EditText emailid, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.loginButton);
        ll = findViewById(R.id.ll);
        bluetooth = findViewById(R.id.bluetooth);
        emailid = findViewById(R.id.emailid);
        password = findViewById(R.id.password);

        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_in_bottom_login);
        bluetoothAnim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bluetooth_anim);
        ll.setAnimation(animation);
        bluetooth.setAnimation(bluetoothAnim);

        emailid.setText("EMPS0001");
        password.setText("sharath");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * username : EMPMA001
                 * password : maniihub
                 */
                if (internetStatus.isNetworkAvailable()) {
                    if (emailid.getText().toString().isEmpty()) {
                        emailid.setError(getResources().getString(R.string.required_field));
                    } else if (password.getText().toString().isEmpty()) {
                        password.setError(getResources().getString(R.string.required_field));
                    } else {
                        LoginRequest loginRequest = new LoginRequest(emailid.getText().toString(), password.getText().toString());
                        Call<LoginResponse> call = serviceCalls.doLogin(loginRequest);
                        showDialog();
                        call.enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                closeDialog();
                                if (response.code() == 200) {
                                    LoginResponse loginResponse = response.body();
                                    sessionManager.setDOCTORID(loginResponse.getDoctorId());
                                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(i);
                                } else {
                                    showAlertDialog("Error :" + "Login Failed" + "\n" + response.code());
                                }

                            }

                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                closeDialog();
                                showAlertDialog(t.getMessage());
                            }
                        });
                    }
                } else {
                    showToast("please connect to the internet");
                }

            }
        });
    }
}
