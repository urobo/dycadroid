/**
 * 
 */
package eu.fbk.dycapo.services;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author riccardo
 *
 */
public class TripParser extends Service {
	private NotificationManager notificationMgr;
	
	/* (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		this.notificationMgr= (NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
		//displayNotificationMessage("starting Background Service");
		Thread thr= new Thread (null,new ServiceWorker(),"TripParser");
	}
	
	
	class ServiceWorker implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// do background task
			// stop the service when done
			// TripParser.stopSelf();
		}
		
		
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onDestroy()
	 */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onStart(android.content.Intent, int)
	 */
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
