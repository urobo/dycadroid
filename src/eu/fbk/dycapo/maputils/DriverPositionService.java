/**
 * 
 */
package eu.fbk.dycapo.maputils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import eu.fbk.dycapo.activities.Navigation;
import eu.fbk.dycapo.activities.R;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Participation;
import eu.fbk.dycapo.persistency.DBParticipation;

/**
 * @author riccardo
 *
 */
public class DriverPositionService extends PositionUpdater {
	private static DriverPositionService Instance = null;
	
	public static final DriverPositionService getInstace(Navigation nav){
		if(Instance instanceof DriverPositionService){
			Instance.stopPositionUpdater();
			Instance = null;
		}
		Instance = new DriverPositionService(nav);
		return Instance;
	}
	
	protected DriverPositionService(Navigation nav) {
		super(nav);
		this.task = new Timer();
	}

	@Override
	public void startPositionUpdater() {
		this.task.scheduleAtFixedRate(new TimerTask(){

			@Override
			public void run() {
				List<Participation> active = DBParticipation.getActiveParcitipants();
				DriverPositionService.this.drawParticipants(active);
				
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
		int size = participants.size();
		MapView map = nav.getMapView();
		List<Overlay> mapOverlays = map.getOverlays();
		
		Drawable drawable = nav.getResources().getDrawable(R.drawable.rider);
		
		GeoPoint point = null;
		OverlayItem overlayitem = null;
		Location position = null;
		
		if (this.itemizedoverlay instanceof DycapoItemizedOverlay){
			mapOverlays.remove(this.itemizedoverlay);
			this.itemizedoverlay = null;
		}
		
		this.itemizedoverlay = new DycapoItemizedOverlay(drawable);
		
		for (int i = 0 ; i < size ; i++){
		    	position = LocationService.getPosition(participants.get(i).getPerson());
				String geo_point = position.getGeorss_point();
		    	int coma = geo_point.indexOf(",");
				double mLat = Double.parseDouble(geo_point.substring(0, coma-1));
				double mLong = Double.parseDouble(geo_point.substring(coma+1,geo_point.length()));
				
		    	point = new GeoPoint(
						(int) (((double) mLat / 1E5) * 1E6),
						(int) (((double) mLong / 1E5) * 1E6));
		    	
		    	overlayitem = new OverlayItem(point,
		    			participants.get(i).getPerson().getUsername(),
		    			participants.get(i).toString());
		    	
		    	itemizedoverlay.addOverlay(overlayitem);
		    }
		mapOverlays.add(itemizedoverlay);
	}

}
