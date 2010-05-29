/**
 * 
 */
package eu.fbk.dycapo.maputils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import eu.fbk.dycapo.activities.R;

/**
 * 
 * @author riccardo
 *
 */
public class GeoService extends MapActivity implements LocationListener{

	private static MapView mapView;
	private static double src_lat=46.42567;
	private static double src_long=11.33514;
	private static double dest_lat = 46.4685468; // the testing destination
	private static double dest_long = 11.3269901;
	private static LocationManager locationManager;    

//	private static String morigin ="";
//	private static String mdestination =""; 


	private static GeoPoint srcGeoPoint = null;

	private static GeoPoint destGeoPoint = null;
	private static ProgressDialog myProgressDialog;
	private static Location location=null;
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
	
	try {
	    
	  
		locationManager =	(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		
		location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		//SET TO 50 m on deployment
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,this);
	
//		Geocoder c = new Geocoder(getBaseContext(),Locale.ITALY);
//		List<Address> destination;
//		destination = c.getFromLocationName(mdestination, 1);
//		List<Address> origin ;
//		origin = c.getFromLocationName(morigin, 1);
		
//  	if(destination.size()==0){
//  		throw new Exception("Cannot find any Location");
//  	}
//  	dest_lat=destination.get(0).getLatitude();
//  	dest_long=destination.get(0).getLongitude();
		Address dest= this.getIntent().getExtras().getParcelable("origin"); 
		dest_lat = dest.getLatitude();
		dest_long = dest.getLongitude();
		destGeoPoint = new GeoPoint((int) (dest_lat * 1E6),
				(int) (dest_long * 1E6));
//		if(location.getLatitude()==0.0||location.getLongitude()==0.0)
//  		return;
  	
  	
//  	src_lat=location.getLatitude();
//  	src_long=location.getLongitude();
//		src_lat = origin.get(0).getLatitude();
//		src_long = origin.get(0).getLongitude();
		
		Address orig = this.getIntent().getExtras().getParcelable("destination");
		src_lat = orig.getLatitude();
		src_long = orig.getLongitude();
		srcGeoPoint = new GeoPoint((int) (src_lat * 1E6),
				(int) (src_long * 1E6));
				
				//start thread
				 myProgressDialog = ProgressDialog.show(GeoService.this,     
             "Please wait...", "Getting new directions...", true,true);
   
   new Thread() {
        public void run() {
             try{
            	 DrawPath(srcGeoPoint, destGeoPoint, Color.BLUE, mapView);
            	 onLocationChanged(location);
             } finally{
             // Dismiss the Dialog
             myProgressDialog.dismiss();
             }
        }
   }.start(); 
				

				mapView.getController().animateTo(srcGeoPoint);
				mapView.getController().setZoom(15);
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
    private void DrawPath(GeoPoint src,GeoPoint dest, int color, MapView mMapView01)
    {
    	Directions.drawPath(src, dest, color, mMapView01);
    }

    
	@Override
	public void onProviderDisabled(String provider) {
		
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.d("status-e", provider);
	}

	@Override
	protected void onStop() {
		super.onStop();
		locationManager.removeUpdates(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		//SET TO 50 m on deployment
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}
}