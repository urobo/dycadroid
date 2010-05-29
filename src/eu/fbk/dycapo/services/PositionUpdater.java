/**
 * 
 */
package eu.fbk.dycapo.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import eu.fbk.dycapo.activities.R;
import eu.fbk.dycapo.maputils.GeoService;

/**
 * @author riccardo
 *
 */
public class PositionUpdater extends Service {
	
	private static final String TAG = "PositionUpdater";

	private NotificationManager notificationMgr;
	@Override
	public void onCreate() {
	    super.onCreate();
	    Log.d(TAG, "OnCreate()");
	    notificationMgr =(NotificationManager)getSystemService(
	   NOTIFICATION_SERVICE);
	    displayNotificationMessage("starting Background Service");
	    Thread thr = new Thread(null, new ServiceWorker(), TAG);
	    thr.start();
	}
	class ServiceWorker implements Runnable
	{
	    public void run() {
	        // do background processing here...
	        // stop the service when done...
	        // BackgroundService.this.stopSelf();
	    }
	}
	@Override
	public void onDestroy()
	{
	    displayNotificationMessage("stopping Background Service");
	    super.onDestroy();
	}
	
	                    
	@Override
	public void onStart(Intent intent, int startId) {
	   super.onStart(intent, startId);
	 }
     @Override
     public IBinder onBind(Intent intent) {
    	 Log.d(TAG, "OnBind()");
    	 return null;
     }
     
     private void displayNotificationMessage(String message)
	 {
    	 Notification notification = new Notification(android.R.drawable.ic_notification_overlay,
    			 message,System.currentTimeMillis());
	     PendingIntent contentIntent =
	    	 PendingIntent.getActivity(this, 0,new Intent(this, GeoService.class), 0);
	     notification.setLatestEventInfo(this, "Background Service",message,
	    		 	contentIntent);
	     notificationMgr.notify(R.string.app_notification_id, notification);
	  }
}
