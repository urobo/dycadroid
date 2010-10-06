/**
 * 
 */
package eu.fbk.dycapo.services.broker;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;
import eu.fbk.dycapo.activities.Navigation;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.factories.json.DycapoObjectsFetcher;
import eu.fbk.dycapo.models.Participation;
import eu.fbk.dycapo.persistency.ActiveTrip;
import eu.fbk.dycapo.persistency.DBParticipation;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.persistency.DBTrip;
import eu.fbk.dycapo.persistency.User;
import eu.fbk.dycapo.services.DycapoServiceClient;
import eu.fbk.dycapo.util.DriverHandler;

/**
 * @author riccardo
 *
 */
public class DriverBroker extends Broker {
	private static final String TAG = "DriverBroker";
	
	private static DriverBroker Instance = null;
	public static final DriverBroker getInstance(Navigation nav){
		if(Instance instanceof DriverBroker){
			Instance.setNav(nav);
			return Instance;
		}
			
		else {
			Instance = new DriverBroker(nav);
			return Instance;
		}
	}
	
	/**
	 * @param role
	 * @param nav
	 */
	private DriverBroker(Navigation nav) {
		super(nav);
		this.task = new Timer();
	}

	/* (non-Javadoc)
	 * @see eu.fbk.dycapo.services.broker.Broker#startBroker()
	 */
	@Override
	public void startBroker() {
		this.task.scheduleAtFixedRate(new TimerTask(){

			@Override
			public void run() {
				try {
					User usr = DBPerson.getUser();
					ActiveTrip at = DBTrip.getActiveTrip();
					JSONArray pl =new JSONArray(DycapoServiceClient.callDycapo(
							DycapoServiceClient.GET,
							at.getHref() + "participations/", 
							null, 
							usr.getUsername(),
							usr.getPassword()).toString());
					List<Participation>tmp = DycapoObjectsFetcher.extractTripParticipations(pl);
					DBParticipation.saveParticipationList(tmp);
					DriverBroker.this.nav.nh.getHandler(DriverHandler.PARTICIPATIONS_CHECKER_ID).sendEmptyMessage(0);
				} catch (DycapoException e) {
					Log.e(TAG, e.getMessage());
					e.printStackTrace();
				} catch (JSONException e) {
					Log.e(TAG, e.getMessage());
					e.printStackTrace();
				}
			}
			
		},
		SHORT_INTERVAL,
		LONG_INTERVAL);
	}

	/* (non-Javadoc)
	 * @see eu.fbk.dycapo.services.broker.Broker#stopBroker()
	 */
	@Override
	public void stopBroker() {
		this.task.cancel();
	}

}
