package com.example.benjamin.ex4.__SecondActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.benjamin.ex4._____FifthActivity.FifthActivityMessaging;
import com.example.benjamin.ex4._____FifthActivity.PullUpMenuObserver;
import com.example.benjamin.ex4.R;
import com.example.benjamin.ex4.___ThirdActivity.ThirdActivityLogin;
import com.example.benjamin.ex4._FirstActivity.FirstActivitySplash;

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
        slidePagerMultiLanguageAdapter.addInstructionFragment(getResources().getString(R.string.instruction0),
                getResources().getDrawable(R.drawable.mrs_incredible),(RadioButton)findViewById(R.id.status0));
        slidePagerMultiLanguageAdapter.addInstructionFragment(getResources().getString(R.string.instruction1),
                getResources().getDrawable(R.drawable.dash),(RadioButton)findViewById(R.id.status1));
        slidePagerMultiLanguageAdapter.addInstructionFragment(getResources().getString(R.string.instruction2),
                getResources().getDrawable(R.drawable.violet),(RadioButton)findViewById(R.id.status2));
        slidePagerMultiLanguageAdapter.addInstructionFragment(getResources().getString(R.string.instruction3),
                getResources().getDrawable(R.drawable.syndrome),(RadioButton)findViewById(R.id.status3));
        slidePagerMultiLanguageAdapter.addInstructionFragment(getResources().getString(R.string.instruction4),
                getResources().getDrawable(R.drawable.frozone),(RadioButton)findViewById(R.id.status4));

        addPageListenerForButtons();

    }
    private void checkIfFirstTime()
    {
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.my_preferences), Context.MODE_PRIVATE);
        String firstTime = sharedpreferences.getString("firstTime","");
        if(!firstTime.equals(""))
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
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.my_preferences), Context.MODE_PRIVATE);
        String firstTime = sharedpreferences.getString("firstTime","");
        if(firstTime.equals(""))
        {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("firstTime","true");
            editor.commit();
        }
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
