/**
 * 
 * Copyright 2014 Noisy Flowers LLC
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
 * com.noisyflowers.landpks.android.activities
 * PlotListActivity.java
 */

package com.noisyflowers.landpks.android.activities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.noisyflowers.landpks.android.LandPKSApplication;
import com.noisyflowers.landpks.android.R;
import com.noisyflowers.landpks.android.dal.LandPKSDatabaseAdapter;
import com.noisyflowers.landpks.android.dal.RestClient;
import com.noisyflowers.landpks.android.fragments.NameFragment;
import com.noisyflowers.landpks.android.fragments.SoilHorizonsFragment;
import com.noisyflowers.landpks.android.model.Plot;
import com.noisyflowers.landpks.android.util.QuickClimateDialogFragment;
import com.noisyflowers.landpks.android.util.TaskListener;
import com.noisyflowers.landpks.android.util.QuickClimateTaskFragment;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PlotListActivity extends ActionBarActivity implements LocationListener, QuickClimateTaskFragment.Callbacks, QuickClimateDialogFragment.Callbacks/*, DialogInterface.OnCancelListener*/ {
	private static final String TAG = PlotListActivity.class.getName(); 

	//TODO: put these somewhere else
    private GoogleAccountCredential credential;
    private SharedPreferences settings;
    static final int REQUEST_ACCOUNT_PICKER = 2;
    static final int SETUP_GOOGLE_PLAY = 3;
    public static final String PREF_ACCOUNT_NAME_KEY = "PreferredAccountName";

    //private Menu actionBarMenu;
	
    private boolean showMap = false;
	private MenuItem listItem = null;
   
	//private ProgressDialog progressDialog;
	private QuickClimateTaskFragment quickClimateTaskFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		SettingsActivity.setUITheme(this);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plot_list);	
		
        ActionBar actionBar = getSupportActionBar();
	    actionBar.setTitle(getString(R.string.app_name)); 

		//testing
		FragmentManager fM = getSupportFragmentManager();
		Fragment sF = fM.findFragmentById(R.id.plot_map);
		fM.beginTransaction().hide(sF).commit();
		
	    quickClimateTaskFragment = (QuickClimateTaskFragment) fM.findFragmentByTag(QuickClimateTaskFragment.TAG);
		
		if (LandPKSApplication.getInstance().getCredential().getSelectedAccountName() == null) {
			//chooseAccount();
			Intent settingsIntent = new Intent(this, SettingsActivity.class);
			settingsIntent.putExtra(SettingsActivity.SETTINGS_MODE, SettingsActivity.ACCOUNT_ONLY);
			startActivity(settingsIntent);
			finish();
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.plot_list, menu);
		//this.actionBarMenu = menu;
		//menu.findItem(R.id.action_show_list).setVisible(false);
		
		listItem = menu.findItem(R.id.action_show_list);

		if (showMap) {
			menu.findItem(R.id.action_show_map).setVisible(false);
			menu.findItem(R.id.action_show_list).setVisible(true);
		} else {
			menu.findItem(R.id.action_show_list).setVisible(false);
			//only show map icon when plots are present
			LandPKSDatabaseAdapter dbAdapter = LandPKSApplication.getInstance().getDatabaseAdapter();
			List<Plot> plotList = dbAdapter.getPlots();
			if (plotList.isEmpty()) {
				menu.findItem(R.id.action_show_map).setVisible(false);	
			} else {
				menu.findItem(R.id.action_show_map).setVisible(true);	
				
			}
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_create_plot: {
			    LandPKSApplication.getInstance().setPlot(new Plot());
				Intent detailIntent = new Intent(this, PlotEditListActivity.class);
				//detailIntent.putExtra("position", 0);
				detailIntent.putExtra("fragment", NameFragment.class);
				startActivity(detailIntent);
				return true;
			}

			case R.id.action_show_list: {
				item.setVisible(false);
				//actionBarMenu.findItem(R.id.action_show_map).setVisible(true);
				showMap = false;
				//invalidateOptionsMenu();
				supportInvalidateOptionsMenu();
				FragmentManager fM = getSupportFragmentManager();
				Fragment listFrag = fM.findFragmentById(R.id.plot_list);
				Fragment mapFrag = fM.findFragmentById(R.id.plot_map);
				fM.beginTransaction().hide(mapFrag).show(listFrag).commit();
				return true;
			}
			
			case R.id.action_quick_climate: {
				String message = null;
				LandPKSApplication.getInstance().setPlot(null, false);
		    	startGPS();
				FragmentManager fM = getSupportFragmentManager();
		    	quickClimateTaskFragment = new QuickClimateTaskFragment(location);
		    	fM.beginTransaction().add(quickClimateTaskFragment, QuickClimateTaskFragment.TAG).commit();

				return true;
			}
			
			case R.id.action_show_map: {
				item.setVisible(false);
				//actionBarMenu.findItem(R.id.action_show_list).setVisible(true);
				showMap = true;
				//invalidateOptionsMenu();
				supportInvalidateOptionsMenu();
				FragmentManager fM = getSupportFragmentManager();
				Fragment listFrag = fM.findFragmentById(R.id.plot_list);
				Fragment mapFrag = fM.findFragmentById(R.id.plot_map);
				fM.beginTransaction().hide(listFrag).show(mapFrag).commit();
				return true;
			}
			
	        case R.id.action_settings: {
				Intent settingsIntent = new Intent(this, SettingsActivity.class);
				settingsIntent.putExtra(SettingsActivity.SETTINGS_MODE, SettingsActivity.ALL);
				startActivityForResult(settingsIntent, SettingsActivity.REQUEST_CODE);
	            return true;
			}

	        case R.id.action_copy_to_sd: {
	        	//TODO: include db file as attachment in debug email
	        	new DBBackupTask(this).execute();
	        	return true;
	        }
	        

	        case R.id.action_about: {
				Intent settingsIntent = new Intent(this, AboutActivity.class);
				startActivity(settingsIntent);
	            return true;
			}
	        
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	public void onBackPressed() {
		if (showMap) {
			onOptionsItemSelected(listItem);
		} else {
			super.onBackPressed();
		}
	}	    
	
	private class DBBackupTask extends AsyncTask<Void, Void, String> {
        Context context;
		ProgressDialog progressDialog;
		
    	public DBBackupTask(Context context){
    		this.context = context;
    	}
    	
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(context, "", context.getString(R.string.plot_list_activity_backing_up_db), true);
		}
		
    	protected String doInBackground(Void... params) {
			return LandPKSDatabaseAdapter.copyToSD(context, LandPKSApplication.getInstance().getDatabaseAdapter().getDBPath());
       }

    	protected void onPostExecute(String backupFilePath) {
        	if (progressDialog.isShowing()) 
        		progressDialog.dismiss();
        	if (backupFilePath == null) {
		    	AlertDialog.Builder builder = new AlertDialog.Builder(context);
		    	builder.setMessage(getString(R.string.plot_list_activity_backing_up_db_error))
		    			   .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
		    				   @Override
		    				   public void onClick(DialogInterface dialog, int which) {
		     				   }
		    			   });
		    	AlertDialog alert = builder.create();
		    	alert.show(); 	
        	} else {
		    	AlertDialog.Builder builder = new AlertDialog.Builder(context);
		    	builder.setMessage(getString(R.string.plot_list_activity_backing_up_db_success) + "\n\n(" + backupFilePath + ")")
		    			   .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
		    				   @Override
		    				   public void onClick(DialogInterface dialog, int which) {
		     				   }
		    			   });
		    	AlertDialog alert = builder.create();
		    	alert.show(); 	
        	}
        }
		
	}

		
	private void dismissProgressDialog() {
		DialogFragment dialogFragment = (DialogFragment)getSupportFragmentManager().findFragmentByTag(ProgressDialogFragment.TAG); 
		if (dialogFragment != null) { 
			dialogFragment.dismiss(); 
		} 	
 	}
	
	private void showProgressDialog(String message) {
		ProgressDialogFragment progressDialog = ProgressDialogFragment.newInstance(message);
		progressDialog.setCancelable(false);
		progressDialog.show(getSupportFragmentManager(), ProgressDialogFragment.TAG);
	}

	/* (non-Javadoc)
	 * @see com.noisyflowers.landpks.android.util.QuickClimateTaskFragment.Callbacks#onPreExecute()
	 */
	@Override
	public void onPreExecute() {
		showProgressDialog(getString(R.string.plot_list_activity_fetching_climate_data));
	}

	/* (non-Javadoc)
	 * @see com.noisyflowers.landpks.android.util.QuickClimateTaskFragment.Callbacks#onProgressUpdate(int)
	 */
	@Override
	public void onProgressUpdate(int percent) {
	}

	/* (non-Javadoc)
	 * @see com.noisyflowers.landpks.android.util.QuickClimateTaskFragment.Callbacks#onCancelled()
	 */
	@Override
	public void onCancelled() {
		dismissProgressDialog();
		QuickClimateDialogFragment q = QuickClimateDialogFragment.newInstance(null);
		q.setCancelable(false);
		q.show(getSupportFragmentManager(), QuickClimateDialogFragment.TAG);
	}

	/* (non-Javadoc)
	 * @see com.noisyflowers.landpks.android.util.QuickClimateTaskFragment.Callbacks#onPostExecute()
	 */
	@Override
	public void onPostExecute(Plot plot) {
		dismissProgressDialog();
		QuickClimateDialogFragment q;
		q = QuickClimateDialogFragment.newInstance(plot);
		q.setCancelable(false);
		q.show(getSupportFragmentManager(), QuickClimateDialogFragment.TAG);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.noisyflowers.landpks.android.util.QuickClimateDialogFragment.Callbacks#onOk()
	 */
	public void onOk() {
		quickClimateTaskFragment = null;
	}
	
	
	/*
	 * It's a bit windy, but deriving from progress dialog from DialogFragment alleviates reorientation problems
	 */
	public static class ProgressDialogFragment extends DialogFragment {
		public static final String TAG = ProgressDialogFragment.class.getName();
		
		private static final String MESSAGE = "message";
		
		private String message;
		
		public ProgressDialogFragment () {
		}
		
		static public ProgressDialogFragment newInstance(String message) {
			ProgressDialogFragment f = new ProgressDialogFragment();
			Bundle args = new Bundle();
			args.putString(MESSAGE, message);
			f.setArguments(args);
			return f;
		}		
		
		@Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
			ProgressDialog retDialog = null;

	        message = getArguments().getString(MESSAGE);
			
			retDialog = new ProgressDialog(getActivity());
			retDialog.setMessage(message);		

			return retDialog;
		}
				
	}

	
	/**
	 * GPS stuff below
	 */
	
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

	private LocationManager locationManager;
	private String provider;
	private Location location;

	private void startGPS() {
		this.location = null;
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
    	location = null;
    	stopGPS();
    	quickClimateTaskFragment.cancelTask();
    }

   	public void onLocationChanged(Location location) {
		gpsTimer.cancel();
		if (location.hasAccuracy() && location.getAccuracy() < GPS_ACCURACY_THRESHOLD) { 
    		stopGPS();
    		this.location = location;
	    	quickClimateTaskFragment.setLocation(location);
		} else {
			gpsTimer.start();
		}
	}
	public void onProviderDisabled(String provider) {
	}
	public void onProviderEnabled(String provider) {
	}
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

}
