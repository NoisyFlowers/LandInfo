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
 * QuickClimateTaskFragment.java
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
 *	Note: Much of this class was derived from http://www.androiddesignpatterns.com/2013/04/retaining-objects-across-config-changes.html.
 *
 */
public class QuickClimateTaskFragment extends Fragment {
	
	public static final String TAG = QuickClimateTaskFragment.class.getName();
		
	public interface Callbacks {
		void onPreExecute();
		void onProgressUpdate(int percent);
		void onCancelled();
		void onPostExecute(Plot plot);
	}

	private Location location;

	private Callbacks callbacks;
	private QuickClimateTask task;
	
	public QuickClimateTaskFragment (Location location) {
		this.location = location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
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
	
	public void cancelTask() {
		task.cancel(true);
	}
	
	public boolean isTaskRunning() {
		return task != null && task.getStatus() == AsyncTask.Status.RUNNING;
	}
	
	private class QuickClimateTask extends AsyncTask<Void, Void, Plot> {

		Plot plot = new Plot();

		protected void onPreExecute() {
			if (callbacks != null) {
				callbacks.onPreExecute();
			}
		}
        
    	protected Plot doInBackground(Void... params) {
    		while (location == null && !this.isCancelled()) {
    			try {Thread.sleep(1000);} catch (Exception eX) {} 
    		}
    		if (location != null) {
				plot.latitude = location.getLatitude();
				plot.longitude = location.getLongitude();
				plot = RestClient.getInstance().getQuickClimate(plot);    
    		} else {
    			plot = null;
    		}
            return plot;
    	}

    	protected void onPostExecute(Plot plot) {    	   
    		if (callbacks != null) {
    			callbacks.onPostExecute(plot);
    		}
    	}
       
    	protected void onCancelled(Plot plot) {
    		if (callbacks != null) {
    			callbacks.onCancelled();
    		}
    	}



    }
	
}

