/**
 * 
 */
package eu.fbk.dycapo.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import eu.fbk.dycapo.activities.Navigation;
import eu.fbk.dycapo.activities.R;
import eu.fbk.dycapo.bundles.LocationBundle;
import eu.fbk.dycapo.bundles.PersonBundle;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.factories.DycapoObjectsFactory;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.models.Response;
import eu.fbk.dycapo.persistency.ActiveTrip;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.persistency.DBTrip;
import eu.fbk.dycapo.persistency.Participation;
import eu.fbk.dycapo.xmlrpc.XMLRPCClient;
import eu.fbk.dycapo.xmlrpc.XMLRPCException;



/**
 * @author riccardo
 *
 */
public class PositionUpdater extends Service implements LocationListener{
	private static final String TAG = "PositionUpdater";
	private NotificationManager notificationMgr = null;
	private static AlarmManager alarmMgr = null;
	private static LocationManager locationMgr = null;
	private Timer mGetPositionsTimer = new Timer();
	private Timer mUpdatePositionTimer = new Timer();
	private static long UPDATE_INTERVAL = 120000;
	private android.location.Location location = null;
	public static final String TASK="task";
	
	private XMLRPCClient client = new XMLRPCClient(Dycapo.DYCAPO_URL,DBPerson.getUser().getUsername(), DBPerson.getUser().getPassword());
	
	private static Intent intentGetPositions = null;
	private static PendingIntent sendGetPositions = null;
	private static Intent intentUpdatePosition = null;
	private static PendingIntent sendUpdatePosition = null;
	
	private static Bundle getPositionsExtras = null;
	private static Bundle updatePositionExtras = null;
	
	private static final String[] TASK_NAME={
		"get_positions",
		"update_position"
	};
	
	public static final int GET_POSITIONS = 0;
	public static final int UPDATE_POSITION = 1;
	
	public static final String getTask(int task){
		if (task>=0 && task<TASK_NAME.length)
			return TASK_NAME[task];
		return null;
	}
	
	/* (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
		 super.onCreate();
		    Log.d(TAG, "OnCreate()");
		    notificationMgr =(NotificationManager)getSystemService(
		   NOTIFICATION_SERVICE);
		    
		    
		    
		    alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		 
		    displayNotificationMessage("starting Background Service");
		   
		    mGetPositionsTimer.scheduleAtFixedRate(
					new TimerTask() {
						@Override
						public void run() {
							getPositions();
						}
					},
					0,
					UPDATE_INTERVAL);
		    
		    mUpdatePositionTimer.schedule(
		    		new TimerTask(){
						@Override
						public void run() {
							updatePosition();
						}
					},
					0, 
					UPDATE_INTERVAL);
		    
	}
	

	public void getPositions(){
		ActiveTrip get;
		try {
			get = DBTrip.getActiveTrip();
		
		List<Participation> participants = get.getmParticipants();
		
		getPositionsExtras = new Bundle();
		getPositionsExtras.putString(PositionUpdater.TASK,PositionUpdater.getTask(GET_POSITIONS));
		
		int size = participants.size();
		
		
			for (int index = 0 ; index < size ; index++){
				Person fetcher = participants.get(index).getmParticipant();
				Object value = client.call(Dycapo.getMethod(Dycapo.GET_POSITION), fetcher.toHashMap());
				Response response = (Response) DycapoObjectsFactory.getDycapoObject(DycapoObjectsFactory.XMLRPC, value, true);
				if (response.getType().equals(Location.TAG.toLowerCase())){
					fetcher.setPosition((Location)response.getValue());
					getPositionsExtras.putBundle("participant"+ String.valueOf(index), PersonBundle.toBundle(fetcher));
				}else if (response.getType().equals("boolean")){
					participants.remove(index);
				}
			}
			intentGetPositions = null;
			intentGetPositions = new Intent(getApplicationContext(),MapReceiver.class);
			intentGetPositions.putExtras(getPositionsExtras);
			sendGetPositions = null;
			sendGetPositions = PendingIntent.getBroadcast(getApplicationContext(), 666, intentGetPositions, PendingIntent.FLAG_ONE_SHOT);
			alarmMgr.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(), sendGetPositions);				
		} catch (XMLRPCException e) {
			Log.e(TAG, e.getMessage());
		} catch (DycapoException e) {
			Log.e(TAG, e.getMessage());
		}
		
	}
	
	public void updatePosition(){
		locationMgr = (LocationManager) getSystemService (Context.LOCATION_SERVICE);
		location = locationMgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		Location position = new Location ();
		position.setGeorss_point(String.valueOf(location.getLatitude()) + "," + String.valueOf(location.getLongitude()));
		position.setLeaves(new Date());
		position.setPoint(Location.POSI);
		
		updatePositionExtras = null;
		updatePositionExtras = new Bundle();
		updatePositionExtras.putString(PositionUpdater.TASK,PositionUpdater.getTask(UPDATE_POSITION));
		updatePositionExtras.putBundle(Location.TAG, LocationBundle.toBundle(position));
		intentUpdatePosition = null;
		intentUpdatePosition = new Intent(getApplicationContext(),MapReceiver.class);
		intentUpdatePosition.putExtras(updatePositionExtras);
		sendUpdatePosition = null;
		sendUpdatePosition = PendingIntent.getBroadcast(getApplicationContext(), 666, intentUpdatePosition, PendingIntent.FLAG_ONE_SHOT);		
		
		
		try {
			client.call(Dycapo.getMethod(Dycapo.UPDATE_POSITION),position);
			alarmMgr.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(), sendUpdatePosition );
			locationMgr=null;
		} catch (XMLRPCException e) {
			Log.e(TAG, e.getMessage());
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
	 * @see android.app.Service#onLowMemory()
	 */
	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
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
	 * @see android.app.Service#onUnbind(android.content.Intent)
	 */
	@Override
	public boolean onUnbind(Intent intent) {
		 Log.d(TAG, "OnUnbind()");
		return super.onUnbind(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		 Log.d(TAG, "OnBind()");
		return null;
	}
	
	 
    private void displayNotificationMessage(String message)
	 {
   	 Notification notification = new Notification(android.R.drawable.ic_menu_preferences,
   			 message,System.currentTimeMillis());
	     PendingIntent contentIntent =
	    	 PendingIntent.getActivity(this, 0,new Intent(this, Navigation.class), 0);
	     notification.setLatestEventInfo(this, "Background Service",message,
	    		 	contentIntent);
	     notificationMgr.notify(R.string.app_notification_id, notification);
	  }

	@Override
	public void onLocationChanged(android.location.Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}


}
