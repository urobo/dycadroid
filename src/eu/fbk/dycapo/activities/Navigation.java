/**
 * 
 */
package eu.fbk.dycapo.activities;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import eu.fbk.dycapo.bundles.LocationBundle;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.maputils.Directions;
import eu.fbk.dycapo.maputils.LocationService;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.persistency.DBTrip;
import eu.fbk.dycapo.persistency.Participation;

/**
 * 
 * @author riccardo
 *
 */
public class Navigation extends MapActivity implements OnClickListener{
	private static final String TAG = "Navigation";
	private static MapView mapView;
	private static ProgressDialog myProgressDialog;
	@SuppressWarnings("unused")
	private LocationService dls= null;
	
	private String role = null;
	
	private Button button1;
	private Button button2;
	private Button button3;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.navigation);
	
	this.dls = new LocationService(this);
	
	Log.d(TAG, this.getIntent().getExtras().keySet().toString());
	this.role = this.getIntent().getExtras().getString("role");
	
	mapView = (MapView)findViewById(R.id.myMapView1);
	mapView.setBuiltInZoomControls(true);
	mapView.setSatellite(false);
	mapView.setStreetView(true);
	
	
	this.button1 = (Button) this.findViewById(R.id.navButton1);
	this.button1.setOnClickListener(this);
	this.button2 = (Button) this.findViewById(R.id.navButton2);
	this.button2.setOnClickListener(this);
	this.button3 = (Button) this.findViewById(R.id.navButton3);
	this.button3.setOnClickListener(this);
	
	if (this.role.equals("driver")){
		this.button1.setText("Participants");
		this.button2.setText("Finish Trip");
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
		}else {
		
			this.button1.setText("Show Driver");
			this.button2.setText("Start Participation");
		}
		this.button3.setText("Cancel");
		
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
			myProgressDialog.dismiss();
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

	@Override
	public void onClick(View v) {
		
			switch(v.getId()){
			case R.id.navButton1:
				List<Participation> parts;
				try {
					parts = DBTrip.getActiveTrip().getmParticipants();
					String[] participants = null;
					if (!parts.isEmpty()){
						 participants = new String[parts.size()]; 
						for (int i = 0 ; i< participants.length;i++){
							participants[i] = parts.get(i).getmParticipant().getUsername();
						}
					}else{
						participants = new String[1];
						participants[0] = "There are no other users involved in participation";
					}
				new AlertDialog.Builder(this).setTitle("User List").setPositiveButton("Ok", null)
				.setItems(participants, null).setCancelable(true).show();
				} catch (DycapoException e) {
					e.printStackTrace();
					e.alertUser(this);
				}
				break;
				
			case R.id.navButton2:
				if (this.role.equals("driver")){
					
				}else{
					this.button2.setText("Finish Participation");
				}
				break;
				
			case R.id.navButton3:
				if (this.role.equals("driver")){
					
				}else{
					
				}
				break;
			}
	}
	
	
	private static final int UPDATE_LOCATION = 0;
	private static final int UPDATE_PARTICIPANTS_LOCATIONS = 1;
	
	public Handler myViewUpdateHandler = new Handler(){

        public void handleMessage(Message msg) {
                switch (msg.what) {
                
                
                case UPDATE_LOCATION:
                	                	
                	Bundle data = msg.getData();
                    Location loc = LocationBundle.fromBundle(data.getBundle("location"));
                    LocationService.updatePosition(loc);
                        	
                    double mLong = data.getDouble("longitude");
                    double mLat = data.getDouble("latitude");
                        	
                    Navigation.this.updateUserPosition(new GeoPoint((int) (((double) mLat / 1E5) *1E6),
                 		           (int) (((double) mLong / 1E5) * 1E6 )));
                        	                
                	break;
                case UPDATE_PARTICIPANTS_LOCATIONS:
                	break;
           
                }
               	
               

                super.handleMessage(msg);
        }
	};
	
	
	private void updateUserPosition(GeoPoint posi){
		
	}
}