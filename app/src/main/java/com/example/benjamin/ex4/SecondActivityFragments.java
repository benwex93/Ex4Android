package com.example.benjamin.ex4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SecondActivityFragments extends AppCompatActivity {

    /**
     * handler to handle messages every 3 seconds
     */
    private SlidePagerMultiLangAdapter slidePagerMultiLanguageAdapter;
    private static PullUpMenuObserver pullUpMenuObserver = new PullUpMenuObserver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //check if been here before
        checkIfFirstTime();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity_fragments);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        //creates new slidePager adapter for instruction fragments
        slidePagerMultiLanguageAdapter = new SlidePagerMultiLangAdapter(getSupportFragmentManager(),
                Locale.getDefault().getLanguage().equals("he_he"), (ViewPager) findViewById(R.id.pagerScreenTwo));

        //adds instruction fragments with text image and corresponding radio button
        slidePagerMultiLanguageAdapter.addInstructionFragment(getResources().getString(R.string.welcome0),
                getResources().getDrawable(R.drawable.spongebob),(RadioButton)findViewById(R.id.status0));
        slidePagerMultiLanguageAdapter.addInstructionFragment(getResources().getString(R.string.welcome1),
                getResources().getDrawable(R.drawable.patrick),(RadioButton)findViewById(R.id.status1));
        slidePagerMultiLanguageAdapter.addInstructionFragment(getResources().getString(R.string.welcome2),
                getResources().getDrawable(R.drawable.squidward),(RadioButton)findViewById(R.id.status2));
        slidePagerMultiLanguageAdapter.addInstructionFragment(getResources().getString(R.string.welcome3),
                getResources().getDrawable(R.drawable.plankton),(RadioButton)findViewById(R.id.status3));
        slidePagerMultiLanguageAdapter.addInstructionFragment(getResources().getString(R.string.welcome3),
                getResources().getDrawable(R.drawable.mrincredible),(RadioButton)findViewById(R.id.status4));

        addPageListenerForButtons();

    }
    private void checkIfFirstTime()
    {
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.my_preferences), Context.MODE_PRIVATE);
        String user = sharedpreferences.getString("username","");
        if(!user.equals(""))
        {
            //set firstTime to false
            Intent i = new Intent(SecondActivityFragments.this, FifthActivityMessaging.class);
            startActivity(i);
        }
    }
    /**
     * adds listener for buttons at bottom
     */
    public void addPageListenerForButtons()
    {
        slidePagerMultiLanguageAdapter.vPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {  }

            @Override
            public void onPageSelected(int position)
            {
                if(slidePagerMultiLanguageAdapter.isHeb)
                    position = slidePagerMultiLanguageAdapter.numPages - 1 -position;
                if(position == slidePagerMultiLanguageAdapter.numPages - 1)
                {
                    Button SkipContinue = (Button) findViewById(R.id.skip_continue);
                    SkipContinue.setText(getResources().getString(R.string.cont));
                }
                else
                {
                    Button SkipContinue = (Button) findViewById(R.id.skip_continue);
                    SkipContinue.setText(getResources().getString(R.string.skip));
                }
                slidePagerMultiLanguageAdapter.togglePosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });
    }
    public void goToLogin(View view)
    {
        //set firstTime to false
        Intent i = new Intent(SecondActivityFragments.this, ThirdActivityLogin.class);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        pullUpMenuObserver.initializeItemsToMenu(menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return pullUpMenuObserver.performMenuAction(this, item, getBaseContext());
    }
    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(SecondActivityFragments.this, FirstActivitySplash.class);
        startActivity(i);
    }
}
