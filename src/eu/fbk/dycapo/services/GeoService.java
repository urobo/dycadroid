/**
 * 
 */
package eu.fbk.dycapo.services;

import eu.fbk.dycapo.activities.R;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

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
	private static DocumentBuilderFactory dbf = null;
	private static DocumentBuilder db = null;
	private static String title ="";
	private static Drawable drawable;
	private static CursorOverLay itemizedOverlay;
	private static OverlayItem overlayitem=null;
	private static GeoPoint srcGeoPoint = null;
	private static GeoPoint current=null;
	private static GeoPoint destGeoPoint = null;
	private static ProgressDialog myProgressDialog;
	private static Location location=null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.navigation);

	drawable = this.getResources().getDrawable(android.R.drawable.arrow_down_float);
	itemizedOverlay = new CursorOverLay(drawable);
	//First Extract the bundle from intent
	Bundle bundle = getIntent().getExtras();
	title = bundle.getString("title");
	mapView = (MapView)findViewById(R.id.myMapView1);
	mapView.setBuiltInZoomControls(true);
	mapView.setSatellite(false);
	mapView.setStreetView(true);
	//Now try to find destination adress
	
	try {
	    dbf = DocumentBuilderFactory.newInstance();
	    db = dbf.newDocumentBuilder();
		locationManager =	(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		
		location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		//SET TO 50 m on deployment
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,this);
	
		Geocoder c = new Geocoder(getBaseContext(),Locale.ITALY);
  	List<Address> destination;
			destination = c.getFromLocationName(title, 1);
		
  	if(destination.size()==0){
  		throw new Exception("Cannot find any Location");
  	}
  	dest_lat=destination.get(0).getLatitude();
  	dest_long=destination.get(0).getLongitude();
		destGeoPoint = new GeoPoint((int) (dest_lat * 1E6),
				(int) (dest_long * 1E6));
		if(location.getLatitude()==0.0||location.getLongitude()==0.0)
  		return;
  	
  	
  	src_lat=location.getLatitude();
  	src_long=location.getLongitude();
  	
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
    // connect to map web service
    StringBuilder urlString = new StringBuilder();
    urlString.append("http://maps.google.com/maps?f=d&hl=en");
    urlString.append("&saddr=");//from
    urlString.append( Double.toString((double)src.getLatitudeE6()/1.0E6 ));
    urlString.append(",");
    urlString.append( Double.toString((double)src.getLongitudeE6()/1.0E6 ));
    urlString.append("&daddr=");//to
    urlString.append( Double.toString((double)dest.getLatitudeE6()/1.0E6 ));
    urlString.append(",");
    urlString.append( Double.toString((double)dest.getLongitudeE6()/1.0E6 ));
    urlString.append("&ie=UTF8&0&om=0&output=kml");
    //Log.d("xxx","URL="+urlString.toString());
    // get the kml (XML) doc. And parse it to get the coordinates(direction route).
    Document doc = null;
    HttpURLConnection urlConnection= null;
    URL url = null;
    try
    {
    	url = new URL(urlString.toString());
    	urlConnection=(HttpURLConnection)url.openConnection();
    	urlConnection.setRequestMethod("GET");
    	urlConnection.setDoOutput(true);
    	urlConnection.setDoInput(true);
    	urlConnection.connect();

    	doc = db.parse(urlConnection.getInputStream());	

    	if(doc.getElementsByTagName("GeometryCollection").getLength()>0)
    	{
    		//String path = doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getNodeName();
    		String path = doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getFirstChild().getNodeValue() ;
    		//Log.d("xxx","path="+ path);
    		String[] pairs = path.split(" ");
    		String[] lngLat = pairs[0].split(","); // lngLat[0]=longitude lngLat[1]=latitude lngLat[2]=height
    		// src
    		GeoPoint startGP = new GeoPoint((int)(Double.parseDouble(lngLat[1])*1E6),(int)(Double.parseDouble(lngLat[0])*1E6));
    		mMapView01.getOverlays().add(new MyOverLay(startGP,startGP,1));
    		GeoPoint gp1;
    		GeoPoint gp2 = startGP;
    		for(int i=1;i<pairs.length;i++) // the last one would be crash
    		{
    			lngLat = pairs[i].split(",");
    			gp1 = gp2;
    			// watch out! For GeoPoint, first:latitude, second:longitude
    			gp2 = new GeoPoint((int)(Double.parseDouble(lngLat[1])*1E6),(int)(Double.parseDouble(lngLat[0])*1E6));
    			mMapView01.getOverlays().add(new MyOverLay(gp1,gp2,2,color));
    			//Log.d("xxx","pair:" + pairs[i]);
    		}
    		mMapView01.getOverlays().add(new MyOverLay(dest,dest, 3)); // use the default color
    		}
    	}catch (MalformedURLException e){
    	e.printStackTrace();
    	}catch (IOException e){
    	e.printStackTrace();
    	}catch (SAXException e){
    	e.printStackTrace();
    	}
    }

    @Override
		public void onLocationChanged(Location location) {
    	//Just show user position on the map
    	
    	current = new GeoPoint((int) (1E6*location.getLatitude()),(int) (location.getLongitude()*1E6));
    	//FIRST TIME DONT REMOVE
    	if(overlayitem!=null)
    		mapView.getOverlays().remove(mapView.getOverlays().remove(overlayitem)); 
    	overlayitem = new OverlayItem(current, "", "");
    	itemizedOverlay.removeOverLay();
    	itemizedOverlay.addOverlay(overlayitem);
    	
    	mapView.getOverlays().add(itemizedOverlay);
    	 
    	 mapView.getController().animateTo(current);
				mapView.getController().setZoom(15);
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
}