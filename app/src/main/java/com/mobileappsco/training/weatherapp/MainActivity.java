package com.mobileappsco.training.weatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mobileappsco.training.weatherapp.entities.APIObj;
import com.mobileappsco.training.weatherapp.retrofit.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tv1 = (TextView) findViewById(R.id.tv1);

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("Piedras Negras, Mexico");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv1.setText(s);
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            Retrofit rtBuilder = new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RetrofitInterface rfInterface = rtBuilder.create(RetrofitInterface.class);
            Call<APIObj> requestCall = rfInterface.getCurrentWeather("Wellington,NZ");
            APIObj apiObj = null;
            try {
                apiObj = requestCall.execute().body();
                result += apiObj.getCoord().getLat().toString();
                result += apiObj.getCoord().getLon().toString();
                result += apiObj.getWeather().get(0).getMain();
                result += apiObj.getWeather().get(0).getDescription();
                result += apiObj.getWeather().get(0).getIcon();
                result += apiObj.getBase();
                result += apiObj.getMain().getTemp().toString();
                result += apiObj.getMain().getPressure().toString();
                result += apiObj.getMain().getHumidity().toString();
                result += apiObj.getMain().getTempMin().toString();
                result += apiObj.getMain().getTempMax().toString();
                result += apiObj.getMain().getSeaLevel().toString();
                result += apiObj.getMain().getGrndLevel().toString();
                result += apiObj.getWind().getSpeed().toString();
                result += apiObj.getWind().getDeg().toString();
                result += apiObj.getClouds().getAll().toString();
                result += apiObj.getDt().toString();
                result += apiObj.getSys().getMessage().toString();
                result += apiObj.getSys().getCountry();
                result += apiObj.getSys().getSunrise().toString();
                result += apiObj.getSys().getSunset().toString();
                result += apiObj.getId().toString();
                result += apiObj.getName();
                result += apiObj.getCod().toString();
            } catch (Exception e) {
                Log.e("WeatherApp", "Error on execute: " + e.getMessage());
            }

            return result;
        }
    }
}
