/**
 * 
 */
package eu.fbk.dycapo.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
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
import com.google.android.maps.OverlayItem;

import eu.fbk.dycapo.bundles.LocationBundle;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.maputils.Directions;
import eu.fbk.dycapo.maputils.DycapoItemizedOverlay;
import eu.fbk.dycapo.maputils.LocationService;
import eu.fbk.dycapo.maputils.PositionUpdater;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Participation;
import eu.fbk.dycapo.persistency.DBParticipation;
import eu.fbk.dycapo.services.broker.Broker;
import eu.fbk.dycapo.util.Environment;
import eu.fbk.dycapo.util.NavigationHandler;
import eu.fbk.dycapo.util.ParticipationUtils;


/**
 * 
 * @author riccardo
 * 
 */
public class Navigation extends MapActivity{
	private static final String TAG = "Navigation";
	private MapView mapView;
	private static ProgressDialog myProgressDialog;
	@SuppressWarnings("unused")
	private LocationService dls = null;
	private PositionUpdater pu = null;
	private int navRole;
	private DycapoItemizedOverlay user_position;


	/**
	 * @return the mapView
	 */
	public MapView getMapView() {
		return mapView;
	}

	/**
	 * @param mapView the mapView to set
	 */
	public void setMapView(MapView mapView) {
		this.mapView = mapView;
	}

	public NavigationHandler nh = null;
	public Broker br = null;

	private Button button1;
	private Button button2;
	private Button button3;
	
	
	private OnClickListener startTrip = new OnClickListener() {

		@Override
		public void onClick(View v) {
			myProgressDialog = ProgressDialog.show(Navigation.this,
					"Please wait...", "Updating On the Server", true,
					true);
			new Thread() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see java.lang.Thread#run()
				 */
				@Override
				public void run() {
					int what = 0;
					Participation tmp = DBParticipation
							.getParticipations().get(0);
					if (riderOnBoard == false) {
						tmp.setStatus(Participation.START);
						Navigation.this.button2
								.setText("Finish Participation");
					} else {
						tmp.setStatus(Participation.FINISH);
						what = 1;
					}
					ParticipationUtils.updateDycapoParticipation(tmp);
					DBParticipation.updateParticipation(tmp);
					Navigation.this.handleCommonSuccess
							.sendEmptyMessage(what);
				}

			}.start();

		}

	};
	
	private static boolean riderOnBoard = false;
	
	/* (non-Javadoc)
	 * @see com.google.android.maps.MapActivity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigation);

		this.dls = new LocationService(this);

		Log.d(TAG, this.getIntent().getExtras().keySet().toString());
		String tmp = this.getIntent().getExtras().getString("role");
		if(tmp.equals("driver"))
			this.navRole = Environment.DRIVER;
		else this.navRole = Environment.RIDER;
		mapView = (MapView) findViewById(R.id.myMapView1);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(false);
		mapView.setStreetView(true);

		this.button1 = (Button) this.findViewById(R.id.navButton1);
		this.button2 = (Button) this.findViewById(R.id.navButton2);
		this.button3 = (Button) this.findViewById(R.id.navButton3);
		
		
		switch(this.navRole){
		case Environment.DRIVER:
			this.button1.setText("Participants");
			this.button2.setText("Finish Trip");
			try {

				mapView.getController().setZoom(15);

				// start thread
				myProgressDialog = ProgressDialog.show(Navigation.this,
						"Please wait...", "Drawing Directions", true, true);

				new Thread() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see java.lang.Thread#run()
					 */
					@Override
					public void run() {
						DrawPath(mapView);
					}
				}.start();
			} catch (Exception e) {
				new AlertDialog.Builder(this).setMessage(e.getMessage())
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										finish();
									}
								}).show();
			}
			break;
		case Environment.RIDER:
			this.button1.setText("Show Driver");
			this.button2.setText("Start Participation");

			this.button2.setOnClickListener(this.startTrip);
			break;
		}
		this.button3.setText("Cancel");
		nh = NavigationHandler.HandlersFactory.getNavigationHandler(this.navRole, this);
		pu = PositionUpdater.PositionUpdaterFactory.buildPoistionUpdater(navRole, this);
		br = Broker.BrokerFactory.getBroker(navRole, this);
		
		br.startBroker();
		pu.startPositionUpdater();
	}

	private Handler handleCommonSuccess = new Handler() {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			myProgressDialog.dismiss();
			if (msg.what == 1) {
				// TODO ask for next trip or something
			}
		}

	};

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	/**
	 * Method draws the direction provided by google on the map
	 * 
	 * @param src
	 *            is the Source Location
	 * @param dest
	 *            is the destination Location
	 * @param color
	 *            is the Color of the overlay
	 * @param mMapView01
	 */
	private void DrawPath(MapView mMapView01) {
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


	private static final int UPDATE_LOCATION = 0;
	

	public Handler myViewUpdateHandler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {

			case UPDATE_LOCATION:

				Bundle data = msg.getData();
				Location loc = LocationBundle.fromBundle(data
						.getBundle("location"));
				LocationService.updatePosition(loc);

				double mLong = data.getDouble("longitude");
				double mLat = data.getDouble("latitude");

				Navigation.this.updateUserPosition(new GeoPoint(
						(int) (((double) mLat / 1E5) * 1E6),
						(int) (((double) mLong / 1E5) * 1E6)));

				break;
			}
		}
	};

	private void updateUserPosition(GeoPoint posi) {
		if (this.user_position instanceof DycapoItemizedOverlay){
			this.mapView.getOverlays().remove(this.user_position);
			this.user_position = null;
		}
		Drawable drawable = null;
		switch(this.navRole){
		case Environment.RIDER:
			this.getResources().getDrawable(R.drawable.rider);
			break;
		case Environment.DRIVER:
			this.getResources().getDrawable(R.drawable.driver);
			break;
		}
		
		this.user_position = new DycapoItemizedOverlay(drawable);
		OverlayItem overlayitem = new OverlayItem(posi, "You", "");
		this.user_position.addOverlay(overlayitem);
		this.mapView.getOverlays().add(this.user_position);
	}
}