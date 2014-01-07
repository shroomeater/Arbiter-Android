package com.lmn.Arbiter_Android.CordovaPlugins.Helpers;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.lmn.Arbiter_Android.ArbiterProject;
import com.lmn.Arbiter_Android.ArbiterState;
import com.lmn.Arbiter_Android.R;
import com.lmn.Arbiter_Android.BaseClasses.Feature;
import com.lmn.Arbiter_Android.DatabaseHelpers.FeatureDatabaseHelper;
import com.lmn.Arbiter_Android.DatabaseHelpers.TableHelpers.FeaturesHelper;
import com.lmn.Arbiter_Android.Dialog.Dialogs.FeatureDialog.FeatureDialog;
import com.lmn.Arbiter_Android.ProjectStructure.ProjectStructure;

public class FeatureHelper {
	private FragmentActivity activity;
	
	public FeatureHelper(FragmentActivity activity){
		this.activity = activity;
	}
	
	public void displayUpdatedFeature(String layerId){
		displayDialog(ArbiterState.getArbiterState()
				.isEditingFeature(), layerId, true);
	}
	
	public void displayFeatureDialog(String featureType, String featureId, String layerId){
		SQLiteDatabase db = getFeatureDatabase();
		
		Feature feature = getFeature(db, featureType, featureId);
		
		displayDialog(feature, layerId, false);
	}
	
	private SQLiteDatabase getFeatureDatabase(){
		Context context = activity.getApplicationContext();
		
		String openProjectName = ArbiterProject.
				getArbiterProject().getOpenProject(activity);
		
		return FeatureDatabaseHelper.getHelper(context,
				ProjectStructure.getProjectPath(context, 
						openProjectName), false).getWritableDatabase();
	}
	
	private Feature getFeature(SQLiteDatabase db, String featureType, String id){
		return FeaturesHelper.getHelper().
				getFeature(db, id, featureType);
	}
	
	private void displayDialog(Feature feature, String layerId, boolean startInEditMode){
		Resources resources = activity.getResources();
		
		String title = resources.getString(R.string.feature_dialog_title);
		
		FeatureDialog dialog = FeatureDialog.newInstance(title, 
				R.layout.feature_dialog, feature, layerId, startInEditMode);
		
		FragmentManager manager = activity.getSupportFragmentManager();
		
		dialog.show(manager, FeatureDialog.TAG);
	}
}
