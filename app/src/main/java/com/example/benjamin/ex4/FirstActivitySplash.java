package com.example.benjamin.ex4;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class FirstActivitySplash extends AppCompatActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private LockableViewPagerAdapter mPager;

    /**
     * handler to handle messages every 3 seconds
     */
    final Handler handler = new Handler();
    private boolean exitCalled = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity_splash);
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (LockableViewPagerAdapter) findViewById(R.id.pagerScreenOne);
        mPager.initializeAdapter(getSupportFragmentManager());
        mPager.setSwipeable(false);
        initializeRunner();
    }
    public void initializeRunner()
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mPager.getCurrentItem() != mPager.getCount() - 1) {
                    mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                    handler.postDelayed(this, SPLASH_TIME_OUT);
                }
                else{
                    Intent i = new Intent(FirstActivitySplash.this, SecondActivityFragments.class);
                    startActivity(i);
                }
            }

        };
        handler.postDelayed(runnable, SPLASH_TIME_OUT);
    }
    @Override
    public void onBackPressed()
    {
        //ends thread created to show fragments
        handler.removeCallbacksAndMessages(null);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
