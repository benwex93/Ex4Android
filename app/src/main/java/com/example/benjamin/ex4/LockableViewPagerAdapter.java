package com.example.benjamin.ex4;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by benjamin on 20/06/16.
 */
public class LockableViewPagerAdapter extends ViewPager {
    /*
    * The number of pages (wizard steps) to show in this demo.
    */
    private static final int NUM_PAGES = 4;
    private boolean swipeable;
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    public LockableViewPagerAdapter(Context context) {
        super(context);
    }

    public LockableViewPagerAdapter(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.swipeable = true;
    }
    public void initializeAdapter(FragmentManager fm)
    {
        mPagerAdapter = new ScreenSlidePagerAdapter(fm);
        this.setAdapter(mPagerAdapter);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.swipeable) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.swipeable) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }
    public int getCount() {
        return NUM_PAGES;
    }
    public void setSwipeable(boolean swipeable) {
        this.swipeable = swipeable;
    }
    /**
     * A simple pager adapter that represents 3 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            AnnouncementFragment announcementFragment = new AnnouncementFragment();
            switch(position) {
                case 0:
                    announcementFragment.setText(getResources().getString(R.string.welcome0));
                    break;
                case 1:
                    announcementFragment.setText(getResources().getString(R.string.welcome1));
                    break;
                case 2:
                    announcementFragment.setText(getResources().getString(R.string.welcome2));
                    break;
                case 3:
                    announcementFragment.setText(getResources().getString(R.string.welcome3));
                    break;
                default:
                    break;
            }

            return announcementFragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

    }
}
