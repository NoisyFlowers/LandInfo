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
 * QuickClimateDialogFragment.java
 */
package com.noisyflowers.landpks.android.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.noisyflowers.landpks.android.LandPKSApplication;
import com.noisyflowers.landpks.android.R;
import com.noisyflowers.landpks.android.model.Plot;

/**
 * @author Douglas Meredith
 *
 */
public class QuickClimateDialogFragment extends DialogFragment {
	
	public static final String TAG = QuickClimateDialogFragment.class.getName();

	private static final String SUCCESS = "success";
	
    public interface Callbacks {
        void onOk();
    }

	public QuickClimateDialogFragment () {
	}
	
	static public QuickClimateDialogFragment newInstance(Plot plot) {
		LandPKSApplication.getInstance().setQCPlot(plot);
		QuickClimateDialogFragment f = new QuickClimateDialogFragment();
		return f;
	}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof Callbacks)) {
            throw new ClassCastException(activity.toString() + " must implement QuickClimateDialogFragment.Callbacks");
        }
    }
	
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog retDialog = null;

		Plot plot = LandPKSApplication.getInstance().getQCPlot(); 

		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
		if (plot != null) {
			LandPKSApplication.getInstance().setPlot(plot, false); //ClimateView uses global plot
			LayoutInflater inflater = getActivity().getLayoutInflater();
			View dialogView = inflater.inflate(R.layout.dialog_quick_climate, null);
			TextView tV = (TextView)dialogView.findViewById(R.id.quick_climate_TextView_recommendation);
			tV.setText(LandPKSApplication.getInstance().analyticResults(plot));
			dialogBuilder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					((Callbacks)getActivity()).onOk();
				}
			});		
			dialogBuilder.setTitle(getString(R.string.quick_climate_dialog_title));
			dialogBuilder.setCancelable(false);
			dialogBuilder.setView(dialogView);
			retDialog = dialogBuilder.create();
		} else {
	    	dialogBuilder.setMessage(getString(R.string.quick_climate_dialog_failure))
	               .setCancelable(false)
	               .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
							((Callbacks)getActivity()).onOk();
	                   }
	               });
	        retDialog = dialogBuilder.create();
		}
		
		return retDialog;
	}

}	