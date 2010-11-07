/**
 * 
 */
package eu.fbk.dycapo.services.broker;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Message;
import android.util.Log;
import eu.fbk.dycapo.activities.Navigation;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.factories.bundles.ParticipationBundle;
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

	/**
	 * @param role
	 * @param nav
	 */
	public DriverBroker(Navigation nav) {
		super(nav);
		this.task = new Timer();
		this.dh = (DriverHandler) NavigationHandler.HandlersFactory
				.getNavigationHandler(Environment.DRIVER, this.nav);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.fbk.dycapo.services.broker.Broker#startBroker()
	 */
	@Override
	public void startBroker() {
		Log.d(TAG, "starting broker!");
		this.task.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				try {
					Log.d(TAG, "checking participations");
					ActiveTrip at = DBTrip.getActiveTrip();
					List<Participation> tmp = ParticipationUtils
							.getListOfParticipations(at);
					DBParticipation.saveParticipationList(tmp);
					List<Participation> requested = new ArrayList<Participation>();
					List<Participation> operational = new ArrayList<Participation>();
					int size = tmp.size();
					for (int i = 0; i < size; i++) {
						if (tmp.get(i).getStatus()
								.equals(Participation.REQUESTED))
							requested.add(tmp.get(i));
						else if (tmp.get(i).getStatus()
								.equals(Participation.STARTED)
								|| tmp.get(i).getStatus()
										.equals(Participation.ACCEPTED))
							operational.add(tmp.get(i));
					}
					Log.d(TAG, "sending messages");

					if (!requested.isEmpty()) {
						Message msgChecker = Message.obtain();
						msgChecker.setData(ParticipationBundle
								.encapsulateParticipations(requested));
						DriverBroker.this.dh.participationChecker
								.sendMessage(msgChecker);
					}

					if (!operational.isEmpty()) {
						Message msgUpdater = Message.obtain();
						msgUpdater.setData(ParticipationBundle
								.encapsulateParticipations(operational));
						DriverBroker.this.nav.handleMapUpdate
								.sendMessage(msgUpdater);
					}
				} catch (DycapoException e) {
					Log.e(TAG, e.getMessage());
					e.printStackTrace();
				}
			}

		}, SHORT_INTERVAL, LONG_INTERVAL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.fbk.dycapo.services.broker.Broker#stopBroker()
	 */
	@Override
	public void stopBroker() {
		this.task.cancel();
	}

}
