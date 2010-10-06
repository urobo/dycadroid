/**
 * 
 */
package eu.fbk.dycapo.util;

import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import eu.fbk.dycapo.activities.Navigation;
import eu.fbk.dycapo.bundles.ParticipationBundle;
import eu.fbk.dycapo.models.Participation;
import eu.fbk.dycapo.persistency.DBParticipation;

/**
 * @author riccardo
 *
 */
public class DriverHandlers extends NavigationHandlers {
	
	public static final int PARTICIPATIONS_CHECKER_ID = 0;
	public DriverHandlers(Navigation nav) {
		super(nav);
		// TODO Auto-generated constructor stub
	}
	
	private Handler participationChecker = new Handler(){

		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			List<Participation> pl = DBParticipation.getParticipations();
			Bundle data = new Bundle();
			int i = 0;
			while (i<pl.size()){
				if(pl.get(i).getStatus().equals(Participation.REQUESTED))
					data.putBundle(String.valueOf(i), ParticipationBundle.toBundle(pl.get(i)));
				i++;
			}
			if(!data.isEmpty()){
				Notification mNotification = DycapoNotification.getDycapoNotification(
					DycapoNotification.NEW_RIDER_ID,
					nav, data);
			
				NotificationManager mManager = (NotificationManager)nav.getSystemService(Context.NOTIFICATION_SERVICE);
				mManager.notify((int)System.currentTimeMillis(), 
					mNotification);
			}
		}
		
	};
	/* (non-Javadoc)
	 * @see eu.fbk.dycapo.util.NavigationHandlers#getHandler(int)
	 */
	@Override
	public Handler getHandler(int code) {
		switch(code){
		case PARTICIPATIONS_CHECKER_ID:
			return this.participationChecker;
		}
		return null;
	}

}