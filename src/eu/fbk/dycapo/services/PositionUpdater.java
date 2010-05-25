/**
 * 
 */
package eu.fbk.dycapo.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author riccardo
 *
 */
public class PositionUpdater extends Service {

	/* (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}

}
