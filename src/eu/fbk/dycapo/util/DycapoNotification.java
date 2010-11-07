/**
 * 
 */
package eu.fbk.dycapo.util;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import eu.fbk.dycapo.activities.RiderList;

/**
 * @author riccardo
 * 
 */
public abstract class DycapoNotification {

	private static Notification notification = null;
	public static final int NEW_RIDER_ID = 0;

	public static final Notification getDycapoNotification(int id, Context ctx,
			Bundle data) {
		if (!(notification instanceof Notification))
			notification = new Notification();
		switch (id) {
		case NEW_RIDER_ID:
			Intent intent = new Intent(ctx, RiderList.class);
			intent.putExtras(data);
			PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 0,
					intent, Intent.FLAG_ACTIVITY_NEW_TASK);
			notification.setLatestEventInfo(ctx, "new Riders",
					"There are some Users waiting for a Ride!", pendingIntent);
			notification.when = System.currentTimeMillis();
			notification.defaults |= Notification.DEFAULT_SOUND;
			notification.defaults |= Notification.DEFAULT_VIBRATE;
			notification.defaults |= Notification.DEFAULT_LIGHTS;
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			notification.flags |= Notification.FLAG_INSISTENT;
			notification.flags |= Notification.FLAG_SHOW_LIGHTS;
			notification.ledARGB = 0xff00ff00;
			notification.ledOnMS = 300;
			notification.ledOffMS = 1000;
			notification.icon = eu.fbk.dycapo.activities.R.drawable.rider;
			return notification;
		}
		return notification;
	}
}
