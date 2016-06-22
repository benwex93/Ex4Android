package com.example.benjamin.ex4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by benjamin on 17/06/16.
 */
public class FourthActivitySignUp extends AppCompatActivity {
    private static PullUpMenuObserver pullUpMenuObserver = new PullUpMenuObserver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_activity_sign_up);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

    }
    public void attemptSignUp(View view)
    {
        Map editText_Errors = new LinkedHashMap();
        editText_Errors.put(getString(R.string.error_username_required),findViewById(R.id.user_name));
        editText_Errors.put(getString(R.string.error_password_required),findViewById(R.id.password));
        editText_Errors.put(getString(R.string.error_name_required),findViewById(R.id.name));
        editText_Errors.put(getString(R.string.error_email_required),findViewById(R.id.email));
        editText_Errors.put(getString(R.string.error_icon_required),findViewById(R.id.iconSource));

        Set set = editText_Errors.entrySet();
        // Get an iterator
        Iterator i = set.iterator();
        // Display elements
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            EditText textView = (EditText) me.getValue();
            textView.setError(null);
            String userDetail = textView.getText().toString();
            if (TextUtils.isEmpty(userDetail)) {
                textView.setError((String)me.getKey());
                textView.requestFocus();
                return;
            }
        }
        //add user
        User newUser = new User(((EditText)findViewById(R.id.user_name)).getText().toString(),
            ((EditText)findViewById(R.id.password)).getText().toString(),
            ((EditText)findViewById(R.id.name)).getText().toString(),
            ((EditText)findViewById(R.id.email)).getText().toString(),
            ((EditText)findViewById(R.id.iconSource)).getText().toString());

        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.my_preferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("username",((EditText)findViewById(R.id.user_name)).getText().toString());
        editor.commit();

        //save to background
        Intent intent = new Intent(FourthActivitySignUp.this, FifthActivityMessaging.class);
        startActivity(intent);
    }
    public void clickIcon(View view)
    {
        ((ImageButton)findViewById(R.id.icon1)).setImageDrawable(null);
        ((ImageButton)findViewById(R.id.icon2)).setImageDrawable(null);
        ((ImageButton)findViewById(R.id.icon3)).setImageDrawable(null);
        ImageButton im = (ImageButton)view;
        im.setImageDrawable(getResources().getDrawable(R.drawable.check_mark));
        ((TextView)findViewById(R.id.iconSource)).setText(view.getTag().toString());
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
        Intent intent = new Intent(FourthActivitySignUp.this, ThirdActivityLogin.class);
        startActivity(intent);
    }
}
