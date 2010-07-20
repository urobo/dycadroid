/**
 * 
 */
package eu.fbk.dycapo.services;

import java.util.Timer;
import java.util.TimerTask;

import eu.fbk.dycapo.bundles.LocationBundle;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.xmlrpc.XMLRPCClient;
import android.app.AlarmManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

/**
 * @author riccardo
 *
 */
public class DriverInquirer extends Service implements Inquirer{
	private static final String TAG = "DriverInquirer";
	public static final String TASK = "task";
	private static final String[] TASKS = {
		"check_requested_ride"
	};
	private static final int CHECK_REQUESTED_RIDE = 0;
	
	private XMLRPCClient client = new XMLRPCClient(Dycapo.DYCAPO_URL,DBPerson.getUser().getUsername(), DBPerson.getUser().getPassword());
	
	
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
		// TODO Auto-generated method stub
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
	
	
	

	@Override
	public void _unpackBundle() {
		if (this.mReceivedData.containsKey(TASK))
			switch (this.mReceivedData.getInt(TASK)){
			case DriverInquirer.CHECK_REQUESTED_RIDE:
				
				break;
			}
		
	}

	@Override
	public void inquire() {
				
	}

}
