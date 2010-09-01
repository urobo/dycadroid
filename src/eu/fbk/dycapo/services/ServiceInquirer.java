package eu.fbk.dycapo.services;

import java.util.Timer;
import java.util.TimerTask;

import android.app.AlarmManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import eu.fbk.dycapo.bundles.LocationBundle;
import eu.fbk.dycapo.models.Location;

public class ServiceInquirer extends Service{
	private static final String TAG = "ServiceInquirer";
	public static final String TASK = "task";
	private static final String[] TASKS = {
		"search_trip",
		"check_ride_requests"
	};
	
	
	
	private Location orig = null;
	private Location dest = null;
	private static final int SEARCH_TRIP = 0;
	private static final int CHECK_RIDE_REQUESTS = 1;
	
	private Bundle mReceivedData = null;
	
	public static final String getTask(int key){
		if (key >= 0 && key < TASKS.length)return TASKS[key];
		return null;
	}
 	
	private static int UPDATE_INTERVAL = 60000;
	private static AlarmManager alarmMgr = null;
	private Timer mTimerTask = new Timer();
	private int taskId;
	
	/* (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
	
		super.onCreate();
		
		alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Service#onStart(android.content.Intent, int)
	 */
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		this.mReceivedData = intent.getExtras();
		this._unpackBundle();
		mTimerTask.scheduleAtFixedRate(
				new TimerTask() {
					@Override
					public void run() {
						inquire();
					}
				},
				0,
				UPDATE_INTERVAL);
	}
	
	public void _unpackBundle(){
		if (this.mReceivedData.containsKey(TASK))
			switch (this.mReceivedData.getInt(TASK)){
			case ServiceInquirer.SEARCH_TRIP:
				this.taskId = ServiceInquirer.SEARCH_TRIP;
				if (this.mReceivedData.containsKey(Location.getPointType(Location.ORIG)))
					this.orig = LocationBundle.fromBundle(
							this.mReceivedData.getBundle(
									Location.getPointType(
											Location.ORIG)));
				if (this.mReceivedData.containsKey(Location.getPointType(Location.DEST)))
					this.dest = LocationBundle.fromBundle(
							this.mReceivedData.getBundle(
									Location.getPointType(
											Location.DEST)));
				break;
			case ServiceInquirer.CHECK_RIDE_REQUESTS:
				this.taskId = ServiceInquirer.CHECK_RIDE_REQUESTS;
				break;
			}
	}
	
	private void _checkRideRequests(){
		
	}
	private void _findTrip(){
		
		Object value;
		//TODO look on dycapo
	}

	public void inquire() {
		switch(this.taskId){
		
		case ServiceInquirer.SEARCH_TRIP:
			_findTrip();
			break;

		case ServiceInquirer.CHECK_RIDE_REQUESTS:
			_checkRideRequests();
			break;
		}
	}
}
