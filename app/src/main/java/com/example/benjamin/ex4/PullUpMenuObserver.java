package com.example.benjamin.ex4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by benjamin on 21/06/16.
 */
public class PullUpMenuObserver {
    public void initializeItemsToMenu(Menu menu)
    {
        menu.add(Menu.NONE, R.id.Hebrew, Menu.NONE, R.string.hebrew);
        menu.add(Menu.NONE, R.id.English, Menu.NONE, R.string.english);
        menu.add(Menu.NONE, R.id.Logout, Menu.NONE, R.string.logout);
        menu.add(Menu.NONE, R.id.Exit, Menu.NONE, R.string.exit);
    }
    public boolean performMenuAction(Context context, MenuItem item, Context baseContext)
    {
        switch (item.getItemId()) {
            case R.id.Hebrew:
                Locale locale = new Locale("he_HE");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                context.getApplicationContext().getResources().updateConfiguration(config, baseContext.getResources().getDisplayMetrics());
                Intent i = new Intent(context, context.getClass());
                context.startActivity(i);
                return true;
            case R.id.English:
                Locale locale2 = new Locale("en_EN");
                Locale.setDefault(locale2);
                Configuration config2 = new Configuration();
                config2.locale = locale2;
                context.getApplicationContext().getResources().updateConfiguration(config2, baseContext.getResources().getDisplayMetrics());
                Intent intent2 = new Intent(context, context.getClass());
                context.startActivity(intent2);
                return true;
            case R.id.Logout:
                SharedPreferences sharedpreferences = context.getSharedPreferences(context.getString(R.string.my_preferences), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("username","");
                editor.commit();
                Intent intent3 = new Intent(context, ThirdActivityLogin.class);
                context.startActivity(intent3);
                return true;
            case R.id.Exit:
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                context.startActivity(intent);
                System.exit(0);
                return true;
            default:
                Toast.makeText(context, "Invalid Menu Option", Toast.LENGTH_LONG).show();
                return false;
        }
    }
}
