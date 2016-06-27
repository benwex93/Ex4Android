package com.example.benjamin.ex4.__SecondActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;

import com.example.benjamin.ex4.__SecondActivity.AnnouncementFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin on 17/06/16.
 */
public class SlidePagerMultiLangAdapter {
    private List<AnnouncementFragment> instructionFragments;
    //The number of pages (wizard steps) to show in this demo.
    public int numPages = 0;
     //The pager widget, which handles animation and allows swiping horizontally to access previous
     //and next wizard steps.
    public ViewPager vPager;
     //The pager adapter, which provides the pages to the view pager widget.
    private PagerAdapter vPagerAdapter;
    private Context context;
    public boolean isHeb;
    public SlidePagerMultiLangAdapter(FragmentManager fm, boolean isHeb, ViewPager vPager)
    {
        this.isHeb = isHeb;
        this.vPager = vPager;
        this.vPagerAdapter = new ScreenSlidePagerAdapter(fm);
        //sests view pager in adapter
        this.vPager.setAdapter(this.vPagerAdapter);
        this.instructionFragments = new ArrayList<>();
    }
    public void togglePosition(int position)
    {
        if(isHeb)
            position = numPages - 1 - position;
        instructionFragments.get(position).toggleRadioButton();
    }
    public void addInstructionFragment(String text, Drawable image, RadioButton radio)
    {
        AnnouncementFragment faif = new AnnouncementFragment();
        faif.setText(text);
        faif.setImage(image);
        faif.setRadioButton(radio);
        radio.setEnabled(false);
        //adds them in reverse order if hebrew
        if(isHeb)
            instructionFragments.add(0,faif);
        else
            instructionFragments.add(faif);
        numPages++;

        if(isHeb)
            vPager.setCurrentItem(numPages - 1);
        vPagerAdapter.notifyDataSetChanged();
        togglePosition(0);
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
            return instructionFragments.get(position);
        }

        @Override
        public int getCount() {
            return numPages;
        }

    }
}
