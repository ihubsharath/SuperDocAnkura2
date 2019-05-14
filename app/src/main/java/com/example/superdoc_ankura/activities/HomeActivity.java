package com.example.superdoc_ankura.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.fragments.CalendarFragment;
import com.example.superdoc_ankura.fragments.ContactsFragment;
import com.example.superdoc_ankura.fragments.DashboardFragment;
import com.example.superdoc_ankura.fragments.ProfileFragment;
import com.example.superdoc_ankura.utils.BaseActivity;

public class HomeActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // replayIntroSlider();
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout, new DashboardFragment())
                .commit();


    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    fragment = new DashboardFragment();
//                    Intent i = new Intent(HomeActivity.this, DashboardFragment.class);
//                    startActivity(i);
                    break;
                case R.id.navigation_calendar:
                    fragment = new CalendarFragment();
                    break;
                case R.id.navigation_contacts:
                    fragment = new ContactsFragment();
                    break;
                case R.id.navigation_profile:
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


}
