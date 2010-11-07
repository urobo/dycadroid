/**
 * 
 */
package eu.fbk.dycapo.util.handlers;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import eu.fbk.dycapo.activities.Navigation;
import eu.fbk.dycapo.util.DycapoNotification;

/**
 * @author riccardo
 * 
 */
public class DriverHandler extends NavigationHandler {
	public static final int PARTICIPATIONS_CHECKER_ID = 0;
	public static final String TAG = "DriverHandler";

	public DriverHandler(Navigation nav) {
		super(nav);
	}

	public Handler participationChecker = new Handler() {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			Log.d(TAG, "checking for new particiaptions");

			if (!msg.getData().isEmpty()) {
				Notification mNotification = DycapoNotification
						.getDycapoNotification(DycapoNotification.NEW_RIDER_ID,
								nav, msg.getData());

				NotificationManager mManager = (NotificationManager) nav
						.getSystemService(Context.NOTIFICATION_SERVICE);
				mManager.notify((int) System.currentTimeMillis(), mNotification);
			}

		}

	};

}
