/**
 * 
 */
package eu.fbk.dycapo.activities;

import java.util.List;

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

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.maputils.DycapoItemizedOverlay;
import eu.fbk.dycapo.maputils.DycapoOverlay;
import eu.fbk.dycapo.maputils.LocationService;
import eu.fbk.dycapo.models.Participation;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.persistency.ActiveTrip;
import eu.fbk.dycapo.persistency.DBParticipation;
import eu.fbk.dycapo.persistency.DBTrip;
import eu.fbk.dycapo.services.broker.Broker;
import eu.fbk.dycapo.util.Environment;
import eu.fbk.dycapo.util.ParticipationUtils;

/**
 * 
 * @author riccardo
 * 
 */
public class Navigation extends MapActivity{
	private static final String TAG = "Navigation";
	
	private static ProgressDialog myProgressDialog;
	private LocationService dls = null;
	private int navRole;
	private DycapoItemizedOverlay itemized = null;

	public Broker br = null;
	private MapView mapView;

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
					Thread.yield();
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
		
		Log.d(TAG, this.getIntent().getExtras().keySet().toString());
		String tmp = this.getIntent().getExtras().getString("role");
		if(tmp.equals("driver"))
			this.navRole = Environment.DRIVER;
		else this.navRole = Environment.RIDER;
		
		this.dls = new LocationService(this);
		this.br = Broker.BrokerFactory.getBroker(navRole, this);
		
		this.dls.startLocationService();
		this.br.startBroker();
		
		this.mapView = (MapView) findViewById(R.id.myMapView1);
		this.mapView.setBuiltInZoomControls(true);
		this.mapView.setSatellite(false);
		this.mapView.setStreetView(true);
		this.mapView.setClickable(true);
		

		this.button1 = (Button) this.findViewById(R.id.navButton1);
		this.button2 = (Button) this.findViewById(R.id.navButton2);
		this.button3 = (Button) this.findViewById(R.id.navButton3);
		
		
		switch(this.navRole){
		case Environment.DRIVER:
			this.button1.setText("Participants");
			this.button2.setText("Finish Trip");
			try {

				this.mapView.getController().setZoom(15);

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
						DrawPath();
						Navigation.this.handleCommonSuccess.sendEmptyMessage(1);
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
		
	}

	public Handler handleCommonSuccess = new Handler() {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			myProgressDialog.dismiss();
			switch(msg.what){
			case 0:				
				Log.d(TAG, "handling map update");
				List<Participation> list = DBParticipation.getParticipations();
				MapView map = (MapView) Navigation.this
						.findViewById(R.id.myMapView1);
				GeoPoint point;
				Drawable drawable = null;
				OverlayItem overlayitem;
				if (Navigation.this.itemized instanceof DycapoItemizedOverlay) {
					map.getOverlays().remove(Navigation.this.itemized);
					itemized = null;
				}
				
				switch (Navigation.this.navRole){
				case Environment.RIDER:
					drawable = Navigation.this.getResources().getDrawable(R.drawable.driver);
					break;
				case Environment.DRIVER:
					drawable = Navigation.this.getResources().getDrawable(R.drawable.rider);
					break;
				}
				
				itemized = new DycapoItemizedOverlay(drawable);
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getStatus().equals(Participation.ACCEPTED)
							|| list.get(i).getStatus()
									.equals(Participation.START)) {
						Person tmp = list.get(i).getAuthor();
						tmp.setPosition(LocationService.getPosition(tmp));
						
						String geo_point = tmp.getPosition().getGeorss_point();
						int coma = geo_point.indexOf(",");
						double mLat = Double.parseDouble(geo_point.substring(0,
								coma));
						double mLong = Double.parseDouble(geo_point.substring(
								coma + 1, geo_point.length()));

						point = new GeoPoint(
								(int) (((double) mLat / 1E5) * 1E6),
								(int) (((double) mLong / 1E5) * 1E6));

						overlayitem = new OverlayItem(point, list.get(i)
								.getAuthor().getUsername(), list.get(i)
								.toString());

						itemized.addOverlay(overlayitem);
					}
					map.getOverlays().add(itemized);

				}

				break;
			case 1:
				break;
			}
		}

	};



	/* (non-Javadoc)
	 * @see com.google.android.maps.MapActivity#isRouteDisplayed()
	 */
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
	private void DrawPath() {
		try {
			ActiveTrip aTrip = DBTrip.getActiveTrip();
			if (aTrip.getRoute().getmDecodedPolyline() != null)
			this.mapView.getOverlays().add(new DycapoOverlay(aTrip.getRoute().getmDecodedPolyline()));
			this.mapView.getController().animateTo(aTrip.getRoute().getmDecodedPolyline().get(0));
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

}