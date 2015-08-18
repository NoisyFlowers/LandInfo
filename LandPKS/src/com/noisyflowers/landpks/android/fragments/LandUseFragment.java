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
 * com.noisyflowers.landpks.android.fragments
 * LandUseFragment.java
 */
package com.noisyflowers.landpks.android.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.noisyflowers.landpks.android.LandPKSApplication;
import com.noisyflowers.landpks.android.R;
import com.noisyflowers.landpks.android.model.Plot;
import com.noisyflowers.landpks.android.model.Plot.Grazing;
import com.noisyflowers.landpks.android.util.PlotEditFragment;

/**
 * @author Douglas Meredith
 *
 */
public class LandUseFragment extends PlotEditFragment implements OnCheckedChangeListener {

	private View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_landuse, container, false);

		CheckBox cB = (CheckBox)rootView.findViewWithTag(getString(R.string.plot_grazing_1)); 
		cB.setOnCheckedChangeListener(this); 

		return rootView;
	}

	/* (non-Javadoc)
	 * @see com.noisyflowers.landpks.android.util.PlotEditFragment#load(com.noisyflowers.landpks.android.model.Plot)
	 */
	@Override
	public void load(Plot plot) {
		if (plot.grazing != null) {
			for (Plot.Grazing grazingType : plot.grazing) {   
				CheckBox cB = (CheckBox)rootView.findViewWithTag(LandPKSApplication.getInstance().getApplicationContext().getString(grazingType.name));
				if (cB != null) {
					cB.setChecked(true);
				}
			}	
		}

	}

	/* (non-Javadoc)
	 * @see com.noisyflowers.landpks.android.util.PlotEditFragment#save(com.noisyflowers.landpks.android.model.Plot)
	 */
	@Override
	public void save(Plot plot) {
		plot.grazing = new ArrayList<Grazing>();
		for (Plot.Grazing grazingType : Plot.Grazing.values()) {  //...for each Grazing type... 
			CheckBox cB = (CheckBox)rootView.findViewWithTag(LandPKSApplication.getInstance().getApplicationContext().getString(grazingType.name));
			if (cB.isChecked()) {
				plot.grazing.add(grazingType);
				if (grazingType == Grazing.NONE) { 
					plot.grazed = false;
					break; //no other types allowed if not grazed
				} else {
					plot.grazed = true;
				}
			}	
		}
	}

	/* (non-Javadoc)
	 * @see com.noisyflowers.landpks.android.util.PlotEditFragment#isComplete(com.noisyflowers.landpks.android.model.Plot)
	 */
	@Override
	public boolean isComplete(Plot plot) {
		return (plot.grazing != null && plot.grazing.size() > 0);
	}

	/* (non-Javadoc)
	 * @see android.widget.CompoundButton.OnCheckedChangeListener#onCheckedChanged(android.widget.CompoundButton, boolean)
	 */
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		ViewGroup vG = (ViewGroup)buttonView.getParent();  //...find its ViewGroup...
		for (Plot.Grazing grazing : Plot.Grazing.values()) {  //...for each Grazing type... 
			if (grazing.ordinal() > 0) {
				CheckBox cB = (CheckBox)vG.findViewWithTag(getString(grazing.name));
				if (isChecked) {
					cB.setChecked(false);
					cB.setEnabled(false);
				} else {
					cB.setEnabled(true);
				}
			}
		}
	}

}
