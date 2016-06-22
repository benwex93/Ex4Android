package com.example.benjamin.ex4;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

/**
 * Created by benjamin on 17/06/16.
 */
public class FifthActivityMessaging extends ListActivity {
    private int messages_to_view = 0;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private MessagingListAdapter adapter;
    private static PullUpMenuObserver pullUpMenuObserver = new PullUpMenuObserver();

    //message stub-TODELETE
    private Message message = new Message("13:14", "Frozone", "Where is my supersuit?", "Cal.png");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fifth_activity_messaging);
        welcomeUser();
        updateMessages();
        initializeShaker();
        initializeRefresher();
        initializeRefresherListener();
        startService(new Intent(this, MessageService.class));
    }
    public void initializeShaker()
    {
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                updateMessages();
            }
        });
    }
    public void initializeRefresher()
    {
        final SwipeRefreshLayout swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeView.setEnabled(false);
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeView.setRefreshing(true);
                updateMessages();
                ( new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeView.setRefreshing(false);

                    }
                }, 3000);
            }
        });
    }
    public void initializeRefresherListener(){
        final SwipeRefreshLayout swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe);
        ListView lView = (ListView) findViewById(android.R.id.list);
        lView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {  }
            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0)
                    swipeView.setEnabled(true);
                else
                    swipeView.setEnabled(false);
            }
        });
    }
    public void welcomeUser()
    {
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.my_preferences), Context.MODE_PRIVATE);
        String user = sharedpreferences.getString("username","");
        if(!user.equals("")) {
            Toast.makeText(this, "Welcome " + user + "!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }
    public void toggleUpdates(View view)
    {
        ToggleButton toggleButton = (ToggleButton)view;
        if(toggleButton.isChecked())
            stopService(new Intent(this, MessageService.class));
        else
            startService(new Intent(this, MessageService.class));
    }
    public void updateMessages()
    {
        messages_to_view += 10;
        //gets the amount in messages_to_view of messages

        String[] values = new String[] { "(" + message.time + ") " + message.username + ":" + message.message_content};
        adapter = new MessagingListAdapter(this, values);
        setListAdapter(adapter);
    }
    public void sendMessage(View view)
    {
        String messageContent = ((EditText) findViewById(R.id.message_text)).getText().toString();
        Calendar c = Calendar.getInstance();
        String time = String.valueOf(c.get(Calendar.HOUR)) + ":" + c.get(Calendar.MINUTE);
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.my_preferences), Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username","");
        if(username.equals(""))
        {
            Intent i = new Intent(this, ThirdActivityLogin.class);
            startActivity(i);
        }
        message = new Message(time,username,messageContent,"Cal.png");
        updateMessages();
        ((EditText) findViewById(R.id.message_text)).setText("");

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
        Intent i = new Intent(FifthActivityMessaging.this, SecondActivityFragments.class);
        startActivity(i);
    }
    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
