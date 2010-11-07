/**
 * 
 */
package eu.fbk.dycapo.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import eu.fbk.dycapo.activities.FastChoice;
import eu.fbk.dycapo.activities.Home;
import eu.fbk.dycapo.activities.Navigation;
import eu.fbk.dycapo.activities.R;
import eu.fbk.dycapo.activities.Settings;
import eu.fbk.dycapo.activities.TripSettings;

/**
 * @author riccardo
 * 
 */
public abstract class ActivityTester {
	public static final int ACTIVITIES = 5;

	public static final Intent testActivity(Context ctx, int id) {
		Intent i = new Intent();
		Bundle bundle = new Bundle();
		switch (id) {
		case R.id.testHome:
			i.setClass(ctx, Home.class);
			break;
		case R.id.testNavigation:
			bundle.putString("role", "rider");
			i.putExtras(bundle);
			i.setClass(ctx, Navigation.class);
			break;
		case R.id.testSettings:
			i.setClass(ctx, Settings.class);
			break;
		case R.id.testTripSettings:
			bundle.putString("role", "driver");
			i.putExtras(bundle);
			i.setClass(ctx, TripSettings.class);
			break;
		case R.id.testFastChoice:
			bundle.putString("role", "driver");
			i.putExtras(bundle);
			i.setClass(ctx, FastChoice.class);
			break;
		}

		return i;
	}
}
