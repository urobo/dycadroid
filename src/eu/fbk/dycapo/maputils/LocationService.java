/**
 * 
 */
package eu.fbk.dycapo.maputils;

import java.util.Date;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import eu.fbk.dycapo.bundles.LocationBundle;

/**
 * @author riccardo
 *
 */
public abstract class LocationService {
	private static LocationManager LMGR;
	private static Context CTX;
	
	public static final void initializeLocationService(Context ctx){
		CTX = ctx;
		LMGR = (LocationManager)CTX.getSystemService(Context.LOCATION_SERVICE);
	}
	public static final void getCurrentPosition (final Handler handler){
		new Thread(){

			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				Location cloc = LMGR.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				
				eu.fbk.dycapo.models.Location uloc = new eu.fbk.dycapo.models.Location();
				
				uloc.setGeorss_point(cloc.getLatitude() + "," + cloc.getLongitude());
				uloc.setPoint(eu.fbk.dycapo.models.Location.POSI);
				uloc.setLeaves(new Date());
				
				Message msg = new Message();
				msg.setData(LocationBundle.toBundle(uloc));
				handler.sendMessage(msg);
				LMGR = null;
				CTX = null;
			}
			
		}.start();
		
	}
}
