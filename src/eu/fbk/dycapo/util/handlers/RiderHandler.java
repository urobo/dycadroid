/**
 * 
 */
package eu.fbk.dycapo.util.handlers;

import eu.fbk.dycapo.activities.Navigation;
import eu.fbk.dycapo.bundles.ParticipationBundle;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.persistency.DBParticipation;
import eu.fbk.dycapo.persistency.DBTrip;
import android.app.AlertDialog;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * @author riccardo
 *
 */
public class RiderHandler extends NavigationHandler {

	private static final String TAG = "NavigationHandlers";
	
	private static RiderHandler Instance = null;
	public static final int STATUS_CHANGED_ID = 0;
	
	public static final RiderHandler getInstance(Navigation nav){
		if (Instance instanceof RiderHandler)
			Instance = null;
		Instance = new RiderHandler(nav);
		return Instance;
	}
	
	private RiderHandler(Navigation nav) {
		super(nav);
	}
	
	public Handler handleStatusChange = new Handler(){

		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			RiderHandler.this.nav.br.stopBroker();
			try {
				Person driver = DBTrip.getActiveTrip().getAuthor();
				String opnoun; 
				if (driver.getGender().equals(Person.MALE))
					opnoun = "him";
				else opnoun = "her";
				new AlertDialog.Builder(nav).setTitle("Driver")
					.setMessage(driver.getUsername() + 
							" Confirmed the Trip! wait for " + opnoun)
							.setPositiveButton("!", null).create().show();
				DBParticipation.updateParticipation(ParticipationBundle.fromBundle(msg.getData()));
			} catch (DycapoException e) {
				Log.e(TAG,e.getMessage());
				e.printStackTrace();
				e.alertUser(nav);
			}
			
		}
		
	};

}
