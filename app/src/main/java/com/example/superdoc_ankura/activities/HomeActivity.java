package com.example.superdoc_ankura.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.fragments.CalendarFragment;
import com.example.superdoc_ankura.fragments.ContactsFragment;
import com.example.superdoc_ankura.fragments.DashboardFragment;
import com.example.superdoc_ankura.fragments.GraphFragment;
import com.example.superdoc_ankura.fragments.ProfileFragment;
import com.example.superdoc_ankura.utils.BaseActivity;

public class HomeActivity extends BaseActivity {
LinearLayout graphLayout;
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
         navView = findViewById(R.id.nav_view);
         graphLayout = findViewById(R.id.graphLayout);




        // replayIntroSlider();
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout, new DashboardFragment())
                .commit();




    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            if (AllAppointmentsActivity.getInstance().stop) {
                AllAppointmentsActivity.getInstance().stop = true;
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.framelayout, new GraphFragment());
                ft.addToBackStack(null);
                ft.commitAllowingStateLoss();

                navView.getMenu().getItem(0).setCheckable(false);
            }
//            else {
//                AllAppointmentsActivity.getInstance().stop = false;
////                showAlertDialog("No Appointments Today");
//            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    item.setCheckable(true);
                    fragment = new DashboardFragment();
//                    Intent i = new Intent(HomeActivity.this, DashboardFragment.class);
//                    startActivity(i);
                    break;
                case R.id.navigation_calendar:
                    item.setCheckable(true);
                    fragment = new CalendarFragment();
                    break;
                case R.id.navigation_contacts:
                    item.setCheckable(true);
                    fragment = new ContactsFragment();
                    break;
                case R.id.navigation_profile:
                    item.setCheckable(true);
                    fragment = new ProfileFragment();
                    break;
            }
            return loadFragment(fragment);
        }
    };

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

    public void replayIntroSlider() {
        PreferenceManager preferenceManager = new PreferenceManager(getApplicationContext());
        preferenceManager.setFirstTimeLaunch(true);
        startActivity(new Intent(HomeActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        BottomNavigationView mBottomNavigationView = findViewById(R.id.nav_view);
        if (mBottomNavigationView.getSelectedItemId() == R.id.navigation_dashboard)
        {
            //super.onBackPressed();
            new AlertDialog.Builder(this)
                    .setIcon(R.mipmap.ic_launcher_round)
                    .setTitle("Alert!")
                    .setMessage("Are you sure want to exit?")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            finish();
                            moveTaskToBack(true);
                        }
                    }).create().show();
        }
//        else if (graphLayout.getVisibility()== View.VISIBLE){
//            mBottomNavigationView.setSelectedItemId(R.id.navigation_dashboard);
//        }
        else
        {
            mBottomNavigationView.setSelectedItemId(R.id.navigation_dashboard);
        }



    }
}
