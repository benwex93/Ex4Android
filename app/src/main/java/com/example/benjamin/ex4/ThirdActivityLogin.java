package com.example.benjamin.ex4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdActivityLogin extends AppCompatActivity {
    private static PullUpMenuObserver pullUpMenuObserver = new PullUpMenuObserver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity_login);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
    }
    public void attemptLogin(View view)
    {
        //check for valid username
        EditText userNameView = (EditText) findViewById(R.id.user_name);
        userNameView.setError(null);
        String userName = userNameView.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            userNameView.setError(getString(R.string.error_username_required));
            userNameView.requestFocus();
            return;
        }
        //check for valid password
        EditText passwordView = (EditText) findViewById(R.id.password);
        passwordView.setError(null);
        String password = passwordView.getText().toString();

        if (TextUtils.isEmpty(password)) {
            passwordView.setError(getString(R.string.error_password_required));
            passwordView.requestFocus();
            return;
        }
        //if real username
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.my_preferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("username",((EditText)findViewById(R.id.user_name)).getText().toString());
        editor.commit();


        //if invalid username
        Toast.makeText(this, "Wrong Username", Toast.LENGTH_LONG).show();

        Intent i = new Intent(ThirdActivityLogin.this, FifthActivityMessaging.class);
        startActivity(i);
    }
    public void goToSignUp(View view)
    {
        Intent i = new Intent(ThirdActivityLogin.this, FourthActivitySignUp.class);
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
        Intent i = new Intent(ThirdActivityLogin.this, SecondActivityFragments.class);
        startActivity(i);
    }
}
