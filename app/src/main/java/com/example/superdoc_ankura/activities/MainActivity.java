package com.example.superdoc_ankura.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.superdoc_ankura.R;
import com.example.superdoc_ankura.utils.BaseActivity;

public class MainActivity extends BaseActivity {

    PreferenceManager preferenceManager;
    LinearLayout Layout_bars;
    //TextView[] bottomBars;
    ImageView[] bottomBars;
    int[] screens;
    TextView Skip, Next;
    ViewPager vp;
    MyViewPagerAdapter myvpAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = (ViewPager) findViewById(R.id.view_pager);
        Layout_bars = (LinearLayout) findViewById(R.id.layoutBars);
        Skip = findViewById(R.id.skip);
        Next = findViewById(R.id.next);
        Next.setTypeface(faceRegular);
        Skip.setTypeface(faceRegular);
        screens = new int[]{
                R.layout.screen_one,
                R.layout.screen_two,
                R.layout.screen_three};
        myvpAdapter = new MyViewPagerAdapter();
        vp.setAdapter(myvpAdapter);
        preferenceManager = new PreferenceManager(this);
        vp.addOnPageChangeListener(viewPagerPageChangeListener);
        if (!preferenceManager.FirstLaunch()) {
            launchMain();
            finish();
        }
        ColoredBars(0);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(v);
            }
        });
        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip(v);
            }
        });
    }


    public void next(View v) {
        int i = getItem(+1);
        if (i < screens.length) {
            vp.setCurrentItem(i);
        } else {
            launchMain();
        }
    }

    public void skip(View view) {
        launchMain();
    }

    private void ColoredBars(int thisScreen) {
        int[] colorsInactive = getResources().getIntArray(R.array.dot_on_page_not_active);
        int[] colorsActive = getResources().getIntArray(R.array.dot_on_page_active);
        bottomBars = new ImageView[screens.length];


        Layout_bars.removeAllViews();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 8, 8, 8);

        //tv1.setLayoutParams(params);

        LinearLayout.LayoutParams layoutDarkParams = new LinearLayout.LayoutParams(40, 40);
        layoutDarkParams.setMargins(16, 16, 16, 16);
        LinearLayout.LayoutParams layoutLightParams = new LinearLayout.LayoutParams(30, 30);
        layoutLightParams.setMargins(16, 16, 16, 16);

        for (int i = 0; i < bottomBars.length; i++) {
            bottomBars[i] = new ImageView(this);
            bottomBars[i].setBackgroundResource(R.drawable.light_dot);
            bottomBars[i].setLayoutParams(layoutLightParams);
//            bottomBars[i].setTextSize(30);
//            bottomBars[i].setGravity(Gravity.CENTER);
//            bottomBars[i].setLayoutParams(params);
//            bottomBars[i].setText(Html.fromHtml("\u2022"));
            Layout_bars.addView(bottomBars[i]);

//            bottomBars[i].setTextColor(colorsInactive[thisScreen]);
        }
        if (bottomBars.length > 0) {
            bottomBars[thisScreen].setBackgroundResource(R.drawable.dark_dot);
            bottomBars[thisScreen].setLayoutParams(layoutDarkParams);
//            bottomBars[thisScreen].setTextSize(40);
//            bottomBars[thisScreen].setLayoutParams(params);
//            bottomBars[thisScreen].setTextColor(colorsActive[thisScreen]);
        }

    }

    private int getItem(int i) {
        return vp.getCurrentItem() + i;
    }

    private void launchMain() {
        preferenceManager.setFirstTimeLaunch(false);
        if (sessionManager.getDOCTORID()==null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }else {
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        }

    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            ColoredBars(position);
            if (position == screens.length - 1) {
                Next.setText("Get Started");
                Skip.setVisibility(View.GONE);
            } else {
                Next.setText("Next");
                Skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater inflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(screens[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return screens.length;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View) object;
            container.removeView(v);
        }

        @Override
        public boolean isViewFromObject(View v, Object object) {
            return v == object;
        }
    }
}