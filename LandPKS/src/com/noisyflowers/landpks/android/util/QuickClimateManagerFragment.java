/**
 * 
 * Copyright 2015 Noisy Flowers LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * 
 * com.noisyflowers.landpks.android.util
 * QuickClimateManagerFragment.java
 */
package com.noisyflowers.landpks.android.util;

import com.noisyflowers.landpks.android.LandPKSApplication;
import com.noisyflowers.landpks.android.R;
import com.noisyflowers.landpks.android.dal.RestClient;
import com.noisyflowers.landpks.android.model.Plot;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


/**
 * @author Douglas Meredith
 *
 */
public class QuickClimateManagerFragment extends Fragment {
	
	public static final String TAG = QuickClimateManagerFragment.class.getName();
	
	public interface Callbacks {
		void onPreExecute();
		void onProgressUpdate(int percent);
		void onCancelled();
		void onPostExecute(Plot plot);
	}
	
	private Callbacks callbacks;
	private QuickClimateTask task;
		
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		callbacks = (Callbacks) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setRetainInstance(true);
	    task = new QuickClimateTask();
	    task.execute();
	}
	
	@Override
	public void onDetach() {
	    super.onDetach();
	    callbacks = null;
	}
	
	/*
	public void cancelTask() {
		task.cancel(true);
	}
	*/
	public boolean isTaskRunning() {
		return task != null && task.getStatus() == AsyncTask.Status.RUNNING;
	}
	
	private class QuickClimateTask extends AsyncTask<Void, Void, Plot> implements LocationListener {

		private static final double GPS_ACCURACY_THRESHOLD = 100.0;   
		private static final int GPS_UPDATE_INTERVAL = 5000; //milliseconds
		private static final int GPS_INITIAL_FIX_TIMEOUT = 30000;
		
		private CountDownTimer gpsTimer  = new CountDownTimer(GPS_INITIAL_FIX_TIMEOUT, 1000){
		     public void onTick(long millisUntilFinished) {
		     }
	
		     public void onFinish() {
			        failGPS();
		     }
		}; 

		Plot plot = new Plot();

		private LocationManager locationManager;
		private String provider;
		
		protected void onPreExecute() {
			if (callbacks != null) {
				callbacks.onPreExecute();
			}
		}
        
    	protected Plot doInBackground(Void... params) {
            if (Looper.myLooper() == null) Looper.prepare();
    		startGPS();
            Looper.loop();		
    		/***
    		while(!isCancelled() && plot != null && plot.latitude == null) {
    			 try {
    			        Thread.sleep(1000);         
    			 } catch (InterruptedException e) {
    			       //e.printStackTrace();
    			 }
    		}
    		if (plot != null) 
    			plot = RestClient.getInstance().getQuickClimate(plot);
     		***/
           
            return plot;
    	}

    	protected void onPostExecute(Plot plot) {    	   
    		if (callbacks != null) {
    			callbacks.onPostExecute(plot);
    		}
    	}
       
    	protected void onCancelled(Plot plot) {
    		gpsTimer.cancel();
    		stopGPS();
    		try {Looper.myLooper().quit();} catch (Exception e) {}
    		if (callbacks != null) {
    			callbacks.onCancelled();
    		}
    	}

	   	public void onLocationChanged(Location location) {
			gpsTimer.cancel();
			if (!isCancelled()) {  
	    		if (location.hasAccuracy() && location.getAccuracy() < GPS_ACCURACY_THRESHOLD) { 
	        		stopGPS();
	    			plot.latitude = location.getLatitude();
	    			plot.longitude = location.getLongitude();
	    			plot = RestClient.getInstance().getQuickClimate(plot);
	    			try {Looper.myLooper().quit();} catch (Exception e) {}
	    		} else {
	    			gpsTimer.start();
	    		}
			}
		}
		public void onProviderDisabled(String provider) {
		}
		public void onProviderEnabled(String provider) {
		}
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		private void startGPS() {
        	locationManager = (LocationManager)LandPKSApplication.getInstance().getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setCostAllowed(true);
            criteria.setPowerRequirement(Criteria.POWER_HIGH);
            provider = locationManager.getBestProvider(criteria, true);		
			gpsTimer.start();		
    		locationManager.requestLocationUpdates(provider, GPS_UPDATE_INTERVAL, 0, this);  
		}

		private void stopGPS() {
			gpsTimer.cancel();
			if (locationManager != null && provider != null) {
				locationManager.removeUpdates(this);
			}		
			locationManager = null;  
			provider = null;
		}
		
	    private void failGPS() {
	    	plot = null;
	    	stopGPS();
	    }


    }
	
}

