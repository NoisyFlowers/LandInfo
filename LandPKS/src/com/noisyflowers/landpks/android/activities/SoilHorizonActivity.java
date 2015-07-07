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
 * SoilHorizonActivity.java
 */

package com.noisyflowers.landpks.android.activities;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import com.noisyflowers.landpks.android.LandPKSApplication;
import com.noisyflowers.landpks.android.R;
import com.noisyflowers.landpks.android.fragments.SoilHorizonFragment;
import com.noisyflowers.landpks.android.fragments.SoilHorizonsFragment;
import com.noisyflowers.landpks.android.model.Plot;
import com.noisyflowers.landpks.android.util.PlotEditFragment;
import com.noisyflowers.landpks.android.util.PlotEditPagerAdapter;
import com.noisyflowers.landpks.android.R.layout;
import com.noisyflowers.landpks.android.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SoilHorizonActivity extends ActionBarActivity {
	
	public SoilProfilePagerAdapter soilprofilePagerAdapter;
	private ViewPager pager;
	private String plotName;
	boolean newPlot = false;
	public int position;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SettingsActivity.setUITheme(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soil_horizon);
		
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

    	List<Fragment> fragments = LandPKSApplication.getInstance().getHorizonFragments();
        
        Plot plot = LandPKSApplication.getInstance().getPlot();
        plotName = plot.name;
        if (plotName == null) {  
        	newPlot = true;
        	plotName = getString(R.string.new_plot);
        }

        pager = (ViewPager)super.findViewById(R.id.horizonsPager);
        pager.setOffscreenPageLimit(6); //this is key to having this work, though I'm not completely clear on why
        pager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                	                	
                    @Override
                    public void onPageSelected(int position) {
        		        if (position >= soilprofilePagerAdapter.getCount()) {
        		        	//getSupportActionBar().setSelectedNavigationItem(soilprofilePagerAdapter.getCount()-1);
        		        	pager.setCurrentItem(soilprofilePagerAdapter.getCount()-1);
        		        } else {
        		        	getSupportActionBar().setSelectedNavigationItem(position);
        		        }

                    }
                });   
        
        ActionBar actionBar = getSupportActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    actionBar.setTitle(plotName + " " + getResources().getString(R.string.SoilHorizonsFragment_display_name)); 
	    
	    ActionBar.TabListener tabListener = new ActionBar.TabListener() {
	        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
		        if (soilprofilePagerAdapter != null && tab.getPosition() >= soilprofilePagerAdapter.getCount()) {
                    //pager.setCurrentItem(soilprofilePagerAdapter.getCount()-1);
                    getSupportActionBar().setSelectedNavigationItem(soilprofilePagerAdapter.getCount()-1);
                    //hacky fix to get rid of select indicator (blue bar) on wrong tab
                    int pos = tab.getPosition();
                    getSupportActionBar().removeTab(tab);
                    getSupportActionBar().addTab(tab, pos);
		        } else {
		        	pager.setCurrentItem(tab.getPosition());
		        }
	        	
	        }

	        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
	        	try { //need this try/catch here for the hacky fix above
		    	    List<Fragment> fragments = LandPKSApplication.getInstance().getHorizonFragments();
		    	    Fragment f = fragments.get(tab.getPosition());
			        boolean completed = false;
	    			Plot plot = LandPKSApplication.getInstance().getPlot();
	    	    	completed = ((PlotEditFragment) f).isComplete(plot);
	
		    	    if (completed) {
						tab.setIcon(R.drawable.btn_check_buttonless_on);
					} else {
						tab.setIcon(null);
					}
	        	}catch (Exception eX) {}
	        }

			@Override
			public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
			}

	    };
	
	    for (Fragment f: fragments) {
	    	String name = "";
			try {
				name = f.getArguments().getString("Name");
			} catch (Exception ex) {}
	    	
			ActionBar.Tab tab = actionBar.newTab().setText(name).setTabListener(tabListener);

			if (((PlotEditFragment) f).isComplete(plot)) {
				tab.setIcon(R.drawable.btn_check_buttonless_on);
			} else {
				tab.setIcon(null);
			}
			actionBar.addTab(tab);
	    }
	    
        //this.plotPagerAdapter  = new PlotEditPagerAdapter(super.getSupportFragmentManager(), fragments);
        this.soilprofilePagerAdapter  = new SoilProfilePagerAdapter(super.getSupportFragmentManager(), fragments);
        pager.setAdapter(this.soilprofilePagerAdapter);
        pager.setCurrentItem(position);
	    
	}
	
	
	public void doSoilColor(View view) {
		Toast.makeText(this, "Start soil color app here", Toast.LENGTH_LONG).show();
	}

	public void doSoilTexture(View view) {
		Toast.makeText(this, "Start soil texture app here", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.soil_horizon, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			Intent detailIntent = new Intent(this, PlotEditDetailActivity.class);
			//detailIntent.putExtra("position", 4);//TODO:  Very hokey!!
			detailIntent.putExtra("fragment", SoilHorizonsFragment.class);
			NavUtils.navigateUpTo(this,
					detailIntent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	
	public class SoilProfilePagerAdapter extends PlotEditPagerAdapter {
		public SoilProfilePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm, fragments);
		}
		
		@Override
		public int getCount() {
			int retVal = 1;
    		Plot plot = LandPKSApplication.getInstance().getPlot();
		    for (Fragment f : fragments) {
	        	if (((PlotEditFragment) f).isComplete(plot)) {
	        		if (!((SoilHorizonFragment) f).isRestrictiveLayer(plot)) {
	        			retVal++;
	        		} else {
	        			break;
	        		}
	        	} 	
		    }
		    return retVal > fragments.size() ? fragments.size() : retVal;
		}
	}
		
}
