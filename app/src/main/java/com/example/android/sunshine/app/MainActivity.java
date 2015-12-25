package com.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
        //int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.perrmission.ACCESS_WIFI_STATE);
        //ActivityCompat

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public static final int REQUEST_LOCATION=1;
    public static final int RESULT_OK = 1;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            //startActivityForResult(intent,);
            return true;
        }else if(id == R.id.action_map){

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String location = prefs.getString(getString(R.string.key_location), getString(R.string.default_location));

            Uri geoLocation= Uri.parse("geo:0,0?").buildUpon()
                    .appendQueryParameter("q",location)
                    .build();
            Intent getLocation = new Intent(Intent.ACTION_VIEW);
            getLocation.setData(geoLocation);
            if(getLocation.resolveActivity(getPackageManager())!=null){
                //startActivityForResult(getLocation, REQUEST_LOCATION);
                startActivity(getLocation);
            }else{
                Log.d(ForecastFragment.FetchWeatherTask.class.getSimpleName(),"Couldn't call "+location);
            }
        }

        return super.onOptionsItemSelected(item);
    }

/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_LOCATION){
            if(resultCode == RESULT_OK){

            }
        }

    }
    */


}
