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
 * com.noisyflowers.landpks.android.fragments
 * NameFragment.java
 */

package com.noisyflowers.landpks.android.fragments;

import com.noisyflowers.landpks.android.LandPKSApplication;
import com.noisyflowers.landpks.android.activities.PlotEditDetailActivity;
import com.noisyflowers.landpks.android.activities.PlotEditListActivity;
import com.noisyflowers.landpks.android.activities.PlotListActivity;
import com.noisyflowers.landpks.android.R;
import com.noisyflowers.landpks.android.model.Plot;
import com.noisyflowers.landpks.android.util.PlotEditFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

public class NameFragment extends PlotEditFragment  implements OnClickListener {
	private static final String TAG = NameFragment.class.getName(); 

	public static final String DISPLAY_NAME = "Plot ID";
	
	private static final double GPS_ACCURACY_THRESHOLD = 10.0;   //TODO: specs say 10, but causing problems with S3
	private static final int GPS_UPDATE_INTERVAL = 5000; //milliseconds
	private static final int GPS_UPDATE_LIMIT = 12;
	private static final int GPS_INITIAL_FIX_TIMEOUT = 30000;
	
	private LocationManager locationManager;
	private String provider;
	private LocationListener locationListener = new MyLocationListener();
	private EditText latitudeTextView, longitudeTextView;
	private boolean newPlot;
	
	private EditText nameField;
	private EditText recorderNameField;
	private EditText organizationField;
	private EditText latField, lonField;
	
	private Button gpsStartButton, gpsCancelButton;
	
	private RadioGroup testRG;
	
	private int defaultTextColor = Color.BLACK;

	private CountDownTimer gpsTimer  = new CountDownTimer(GPS_INITIAL_FIX_TIMEOUT, 1000){
										     public void onTick(long millisUntilFinished) {
										     }
									
										     public void onFinish() {
											        failGPS();
										     }
										}; 
									
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);

		newPlot = ((PlotEditDetailActivity)getActivity()).isNewPlot();

		if (savedInstanceState != null) {
			Boolean gpsActive = savedInstanceState.getBoolean("locationManagerActive");
			if (gpsActive != null && gpsActive) {
				startGPS();
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name, container, false);

        latitudeTextView = (EditText)view.findViewById(R.id.latitudeEditText);
        longitudeTextView = (EditText)view.findViewById(R.id.longitudeEditText);
        nameField = (EditText)view.findViewById(R.id.nameEditText);
        recorderNameField = (EditText)view.findViewById(R.id.recorderNameEditText);
        organizationField = (EditText)view.findViewById(R.id.organizationEditText);
         
        testRG = (RadioGroup)view.findViewById(R.id.name_fragment_testPlotRadioGroup);
        
        defaultTextColor = latitudeTextView.getCurrentTextColor();
        
        latitudeTextView.addTextChangedListener(new MinMaxTextWatcher(-90.0, 90.0));
        longitudeTextView.addTextChangedListener(new MinMaxTextWatcher(-180.0, 180.0));
        
        if (!newPlot) nameField.setEnabled(false);
                
        gpsStartButton = (Button)view.findViewById(R.id.fragment_name_GPS_start_button);
        gpsStartButton.setOnClickListener(this);
        gpsCancelButton = (Button)view.findViewById(R.id.fragment_name_GPS_cancel_button);
        gpsCancelButton.setOnClickListener(this);
        
        LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(getString(R.string.name_fragment_gps_disabled_message))
                   .setCancelable(false)
                   .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                           startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                       }
                   })
                   .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                    	   gpsStartButton.setEnabled(false);
                           dialog.cancel();
                       }
                   });
            AlertDialog alert = builder.create();
            alert.show();        
        }
        
	    return view;
	}
		
	@Override
	public void onPause() {
		super.onPause();
		stopGPS(null,null);
	}

	@Override
	public void onResume() {
		super.onResume();
    	if (newPlot) {
    		recorderNameField.setText(LandPKSApplication.getInstance().getCredential().getSelectedAccountName());
    	}
	}
	
	@Override
	public void onSaveInstanceState(Bundle state) {
		if (locationManager != null && provider != null) {
			state.putBoolean("locationManagerActive", true);
		} else {
			state.putBoolean("locationManagerActive", false);
		}
	}
		
	private void startGPS() {
		latitudeTextView.setFocusable(false);
    	latitudeTextView.setTextColor(Color.RED);
    	latitudeTextView.setText(getString(R.string.name_fragment_gps_wait));
		longitudeTextView.setFocusable(false);
    	longitudeTextView.setTextColor(Color.RED);
    	longitudeTextView.setText(getString(R.string.name_fragment_gps_wait));
    	gpsStartButton.setVisibility(View.GONE);
    	gpsCancelButton.setVisibility(View.VISIBLE);

    	locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
		
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        provider = locationManager.getBestProvider(criteria, true);		
		locationManager.requestLocationUpdates(provider, GPS_UPDATE_INTERVAL, 0, locationListener);  
		
		gpsTimer.start();		
	}
	
	private void stopGPS(Double lat, Double lon) {
		gpsTimer.cancel();
		latitudeTextView.setFocusable(true);
		latitudeTextView.setFocusableInTouchMode(true);
		latitudeTextView.setTextColor(defaultTextColor);
		latitudeTextView.setText(lat == null ? null : Double.toString(lat));
		longitudeTextView.setFocusable(true);
		longitudeTextView.setFocusableInTouchMode(true);
		longitudeTextView.setTextColor(defaultTextColor);
		longitudeTextView.setText(lon == null ? null : Double.toString(lon));
    	gpsCancelButton.setVisibility(View.GONE);
    	gpsStartButton.setVisibility(View.VISIBLE);
		if (locationManager != null && provider != null) {
			locationManager.removeUpdates(locationListener);
		}		
		locationManager = null;  //TODO: setting to null might not be a good idea here
		provider = null;
	}
		
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.fragment_name_GPS_start_button:
				try {
					InputMethodManager iMM = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
					iMM.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
				} catch (Exception eX) {}
				startGPS();
		        break;
			case R.id.fragment_name_GPS_cancel_button:
				Plot plot = LandPKSApplication.getInstance().getPlot();
				stopGPS(plot.latitude, plot.longitude);
				break;
		}
	}
	
	
    private void failGPS() {
    	stopGPS(null, null);
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.name_fragment_gps_fail))
               .setCancelable(false)
               .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                   }
               });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void updateWithCurrentAccuracy(Location location) {
    	if (location != null){
    		latitudeTextView.setText(getString(R.string.name_fragment_gps_wait) + " (accuracy: " + location.getAccuracy() + "m)");
    		longitudeTextView.setText(getString(R.string.name_fragment_gps_wait) + " (accuracy: " + location.getAccuracy() + "m)");

    	}
    }

    private void updateWithNewLocation(Location location) {
    	if (location != null){
    		stopGPS(location.getLatitude(), location.getLongitude());
    	}
    }

    private class MyLocationListener implements LocationListener {
    	
    	int updateCount = 0;
    	
    	public void onLocationChanged(Location location) {
    		gpsTimer.cancel();
    		Log.i(TAG, "location accuracy = " + location.getAccuracy());
    		if (locationManager != null) {  //if locationManager == null, fix was cancelled
	    		if (location.hasAccuracy() && location.getAccuracy() < GPS_ACCURACY_THRESHOLD) { 
	    			updateWithNewLocation(location);
	    		} else if (++updateCount > GPS_UPDATE_LIMIT){
	    			failGPS();
	    		} else {
	    			updateWithCurrentAccuracy(location);
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
    } 
	
	@Override
    public void load(Plot plot) {
		if (plot.testPlot != null) {
			if (plot.testPlot) {
				testRG.check(R.id.name_fragment_testPlot_yes_RadioButton);
			} else  {
				testRG.check(R.id.name_fragment_testPlot_no_RadioButton);
			}
		}
		
    	nameField.setText(plot.name);
    	recorderNameField.setText(plot.recorderName);
    	organizationField.setText(plot.organization);
    	try { latitudeTextView.setText(Double.toString(plot.latitude)); } catch (Exception e){latitudeTextView.setText(null);}
    	try { longitudeTextView.setText(Double.toString(plot.longitude));  } catch (Exception e){longitudeTextView.setText(null);} 	
    }
    	
	@Override
    public void save(Plot plot) {
		if (newPlot &&
			(LandPKSApplication.getInstance().getDatabaseAdapter().plotExists(nameField.getText().toString()) || 
			((PlotEditDetailActivity)getActivity()).isAborted())) {
			//Toast.makeText(getActivity(), getString(R.string.name_fragment_plot_already_exists), Toast.LENGTH_LONG).show();
			InputMethodManager iMM = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			iMM.hideSoftInputFromWindow(nameField.getWindowToken(), 0);
			return;
		}
		
		switch (testRG.getCheckedRadioButtonId()) {
			case R.id.name_fragment_testPlot_yes_RadioButton: 
				plot.testPlot= true;
				break;
			case R.id.name_fragment_testPlot_no_RadioButton: 
				plot.testPlot= false;
				break;
			default:
				//return;
		}
		
		boolean latitudeGood = false;
		boolean longitudeGood = false;
		try { Double.parseDouble(latitudeTextView.getText().toString()); latitudeGood = true; 
		 	  Double.parseDouble(longitudeTextView.getText().toString()); longitudeGood = true; } catch (Exception e){}
		try { 
			plot.name = nameField.getText().toString(); 
			plot.name = "".equals(plot.name) ? null : plot.name;
		} catch (Exception e){}
		try { plot.recorderName = recorderNameField.getText().toString(); } catch (Exception e){}
		try { plot.organization = organizationField.getText().toString(); } catch (Exception e){}	
		if (nameField == null || nameField.getText() == null || "".equals(nameField.getText()))
			return;
		try { plot.latitude = Double.parseDouble(latitudeTextView.getText().toString()); } catch (Exception e){}
		try { plot.longitude = Double.parseDouble(longitudeTextView.getText().toString()); } catch (Exception e){}
		
		if (plot.name != null) LandPKSApplication.getInstance().getDatabaseAdapter().addPlot(plot);
		newPlot = false;
    }
    
	@Override
	public boolean isComplete(Plot plot) {
    	return plot.name != null && plot.recorderName != null && plot.latitude != null && plot.longitude != null &&
    			!"".equals(plot.name) && !"".equals(plot.recorderName);
    }
	
	//TODO: Experimental way of getting dialog instead of Toast for duplicate plot names. Required rewiring of this in PlotEditDetailActivity and PlotEditFragment.
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean retVal = false;
		switch (item.getItemId()) {
			case android.R.id.home:
				if (newPlot &&
					LandPKSApplication.getInstance().getDatabaseAdapter().plotExists(nameField.getText().toString())) {
					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			    	builder.setMessage(getString(R.string.name_fragment_plot_already_exists))
			    			   .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
			    				   @Override
			    				   public void onClick(DialogInterface dialog, int which) {
			    					   nameField.requestFocus();
			     				   }
			    			   });
			    	AlertDialog alert = builder.create();
			    	alert.show();
			    	retVal = true;
				} else if (testRG.getCheckedRadioButtonId() == -1) {
					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			    	builder.setMessage(getString(R.string.name_fragment_test_plot_answer_rqd))
			    			   .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
			    				   @Override
			    				   public void onClick(DialogInterface dialog, int which) {
			     				   }
			    			   });
			    	AlertDialog alert = builder.create();
			    	alert.show();
			    	retVal = true;
				} else {
					retVal = super.onOptionsItemSelected(item);
				}
				break;
			default:
				retVal = super.onOptionsItemSelected(item);
				break;
		}
		return retVal;
	}
	
	private class MinMaxTextWatcher implements TextWatcher {
		
		double min, max;
		
		MinMaxTextWatcher(double min, double max) {
			this.min = min;
			this.max = max;
		}
    	@Override
    	public void afterTextChanged(Editable s) {
    		try {
    			double d = Double.parseDouble(s.toString());
    			if (d > max) {
    				s.replace(0, s.length(), Double.toString(max));
    			} else if (d < min) {
    				s.replace(0, s.length(), Double.toString(min));        				
    			}
    		} catch (NumberFormatException e) {}
    	}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1,
				int arg2, int arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			
		}
        	
	}
}
