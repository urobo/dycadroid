/**
 * 
 */
package eu.fbk.dycapo.services.broker;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.util.Log;
import eu.fbk.dycapo.activities.Navigation;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Participation;
import eu.fbk.dycapo.persistency.ActiveTrip;
import eu.fbk.dycapo.persistency.DBParticipation;
import eu.fbk.dycapo.persistency.DBTrip;
import eu.fbk.dycapo.util.Environment;
import eu.fbk.dycapo.util.ParticipationUtils;
import eu.fbk.dycapo.util.handlers.DriverHandler;
import eu.fbk.dycapo.util.handlers.NavigationHandler;

/**
 * @author riccardo
 *
 */
public class DriverBroker extends Broker {
	private static final String TAG = "DriverBroker";
	private DriverHandler dh = null;
	private static DriverBroker Instance = null;
	
	public static final DriverBroker getInstance(Navigation nav){
		if(Instance instanceof DriverBroker){
			Instance = null;
		}
		Instance = new DriverBroker(nav);
		return Instance;
	}
	
	/**
	 * @param role
	 * @param nav
	 */
	private DriverBroker(Navigation nav) {
		super(nav);
		this.task = new Timer();
		this.dh = (DriverHandler) NavigationHandler.HandlersFactory.getNavigationHandler(Environment.DRIVER, this.nav);
	}

	/* (non-Javadoc)
	 * @see eu.fbk.dycapo.services.broker.Broker#startBroker()
	 */
	@Override
	public void startBroker() {
		Log.d(TAG, "starting broker!");
		this.task.scheduleAtFixedRate(new TimerTask(){

			@Override
			public void run() {
				try {
					Log.d(TAG, "checking participations");
					ActiveTrip at = DBTrip.getActiveTrip();
					List<Participation>tmp = ParticipationUtils.getListOfParticipations(at);
					DBParticipation.saveParticipationList(tmp);
					DriverBroker.this.dh.participationChecker.sendEmptyMessage(0);
					DriverBroker.this.nav.handleCommonSuccess.sendEmptyMessage(0);
				} catch (DycapoException e) {
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
