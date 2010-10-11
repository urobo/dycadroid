/**
 * 
 */
package eu.fbk.dycapo.maputils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import eu.fbk.dycapo.activities.Navigation;
import eu.fbk.dycapo.activities.R;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Participation;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.persistency.DBTrip;

/**
 * @author riccardo
 *
 */
public class RiderPositionService extends PositionUpdater {

	protected static final String TAG = "RiderPositionService";
	private static RiderPositionService Instance = null;
	
	public static final RiderPositionService getInstace(Navigation nav){
		if(Instance instanceof RiderPositionService){
			Instance.stopPositionUpdater();
			Instance = null;
		}
		Instance = new RiderPositionService(nav);
		return Instance;
	}
	
	protected RiderPositionService(Navigation nav) {
		super(nav);
		this.task = new Timer();
	}

	@Override
	public void startPositionUpdater() {
		this.task.scheduleAtFixedRate(new TimerTask(){

			@Override
			public void run() {
				try {
					Person driver = DBTrip.getActiveTrip().getAuthor();
					driver.setPosition(LocationService.getPosition(driver));
					RiderPositionService.this.drawDriver(driver);
				} catch (DycapoException e) {
					Log.e(TAG, e.getMessage());
					e.printStackTrace();
				}
				
			}}, 
			SHORT_INTERVAL, 
			LONG_INTERVAL);
		
	}

	@Override
	public void stopPositionUpdater() {
		this.task.cancel();
	}

	@Override
	public void drawParticipants(List<Participation> participants) {
		
		
	}
	
	public void drawDriver(Person driver){
		MapView map = this.nav.getMapView();
		List<Overlay> mapOverlays = map.getOverlays();
		Drawable drawable = this.nav.getResources().getDrawable(R.drawable.driver);
		
		if (this.itemizedoverlay instanceof DycapoItemizedOverlay){
			mapOverlays.remove(this.itemizedoverlay);
			this.itemizedoverlay = null;
		}
		
		this.itemizedoverlay = new DycapoItemizedOverlay(drawable);
		String geo_point = driver.getPosition().getGeorss_point();
    	int coma = geo_point.indexOf(",");
		double mLat = Double.parseDouble(geo_point.substring(0, coma-1));
		double mLong = Double.parseDouble(geo_point.substring(coma+1,geo_point.length()));
		
    	GeoPoint point = new GeoPoint(
				(int) (((double) mLat / 1E5) * 1E6),
				(int) (((double) mLong / 1E5) * 1E6));
    	
    	OverlayItem overlayitem = new OverlayItem(point,
    			driver.getUsername(),
    			"");
    	
    	itemizedoverlay.addOverlay(overlayitem);
    
    	mapOverlays.add(itemizedoverlay);
	}

}
