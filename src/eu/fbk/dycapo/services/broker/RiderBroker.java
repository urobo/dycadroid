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
import eu.fbk.dycapo.models.Trip;
import eu.fbk.dycapo.persistency.DBParticipation;
import eu.fbk.dycapo.persistency.DBTrip;
import eu.fbk.dycapo.util.Environment;
import eu.fbk.dycapo.util.ParticipationUtils;
import eu.fbk.dycapo.util.handlers.NavigationHandler;
import eu.fbk.dycapo.util.handlers.RiderHandler;

/**
 * @author riccardo
 * 
 */
public class RiderBroker extends Broker {
	protected static final String TAG = "RiderBroker";

	private RiderHandler rh = null;

	/**
	 * @param role
	 * @param nav
	 */
	public RiderBroker(Navigation nav) {
		super(nav);
		this.task = new Timer();
		this.rh = (RiderHandler) NavigationHandler.HandlersFactory
				.getNavigationHandler(Environment.RIDER, this.nav);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.fbk.dycapo.services.broker.Broker#startBroker()
	 */
	@Override
	public void startBroker() {
		this.task.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {

				Participation p = DBParticipation.getMyParticipation();
				String oldStatus = p.getStatus();
				if (oldStatus.equals(Participation.REQUESTED)) {
					DBParticipation.removeParticipation(p);
					p = ParticipationUtils.checkParticipationStatus(p);
					DBParticipation.addParticipation(p);
					if (p.getStatus().equals(Participation.ACCEPTED)) {
						Message msg = Message.obtain();
						msg.setData(ParticipationBundle.toBundle(p));
						RiderBroker.this.rh.handleStatusChange.sendMessage(msg);
					}
				} else if (oldStatus.equals(Participation.ACCEPTED)
						|| oldStatus.equals(Participation.STARTED)) {
					try {
						Trip trip = DBTrip.getActiveTrip();
						List<Participation> list = ParticipationUtils
								.getListOfParticipations(trip);
						DBParticipation.saveParticipationList(list);
						List<Participation> operational = new ArrayList<Participation>();
						int size = list.size();
						for (int i = 0; i < size; i++) {
							if (list.get(i).getStatus()
									.equals(Participation.ACCEPTED)
									|| list.get(i).getStatus()
											.equals(Participation.STARTED))
								operational.add(list.get(i));
						}
						if (!operational.isEmpty()) {
							Message msg = Message.obtain();
							msg.setData(ParticipationBundle
									.encapsulateParticipations(operational));
							RiderBroker.this.nav.handleMapUpdate
									.sendMessage(msg);

						}

					} catch (DycapoException e) {
						Log.e(TAG, e.getMessage());
						e.printStackTrace();
					}
				} else if (oldStatus.equals(Participation.FINISHED)) {
					this.cancel();
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
