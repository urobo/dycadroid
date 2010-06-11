/**
 * 
 */
package eu.fbk.dycapo.activities;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.maputils.Directions;
import eu.fbk.dycapo.services.Receiver;

/**
 * 
 * @author riccardo
 *
 */
public class Navigation extends MapActivity{

	private static MapView mapView;
	private static ProgressDialog myProgressDialog;
	private static Receiver br= null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.navigation);

	
	//First Extract the bundle from intent
	
	//morigin = bundle.getString("origin");
	//mdestination = bundle.getString("destination");
	mapView = (MapView)findViewById(R.id.myMapView1);
	mapView.setBuiltInZoomControls(true);
	mapView.setSatellite(false);
	mapView.setStreetView(true);
	//Now try to find destination adress
	
	br  = new Receiver(mapView,Navigation.this);
	
	try {

		mapView.getController().setZoom(15);
				
		//start thread
		myProgressDialog = ProgressDialog.show(Navigation.this,     
             "Please wait...", "Drawing Directions", true,true);
		
		new Thread(){

			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				DrawPath(mapView);
			}
		}.start();
  
				

		
				
	} catch (Exception e) {
		new AlertDialog.Builder(this)
		.setMessage(e.getMessage())
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			   finish();
			}})
			.show();
		
		}

	}

	@Override
	protected boolean isRouteDisplayed() {
	return false;
	}

	/**
	 * Method draws the direction provided by google on the map
	 * @param src is the Source Location
	 * @param dest is the destination Location
	 * @param color is the Color of the overlay
	 * @param mMapView01
	 */
    private void DrawPath(MapView mMapView01)
    {
    		try {
			Directions.drawPath(mMapView01);
		} catch (DycapoException e) {
			e.alertUser(Navigation.this);
		}
    }

	@Override
	protected void onStop() {
		super.onStop();
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();	
	}

}