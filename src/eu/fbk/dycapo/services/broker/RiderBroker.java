/**
 * 
 */
package eu.fbk.dycapo.services.broker;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Message;
import eu.fbk.dycapo.activities.Navigation;
import eu.fbk.dycapo.bundles.ParticipationBundle;
import eu.fbk.dycapo.models.Participation;
import eu.fbk.dycapo.persistency.DBParticipation;
import eu.fbk.dycapo.util.ParticipationUtils;
import eu.fbk.dycapo.util.RiderHandlers;

/**
 * @author riccardo
 *
 */
public class RiderBroker extends Broker{

	/**
	 * @param role
	 * @param nav
	 */
	public RiderBroker(int role, Navigation nav) {
		super(role, nav);
		this.task = new Timer();
	}

	/* (non-Javadoc)
	 * @see eu.fbk.dycapo.services.broker.Broker#startBroker()
	 */
	@Override
	public void startBroker() {
		this.task.scheduleAtFixedRate(
				new TimerTask(){

					@Override
					public void run() {
						
						Participation p = DBParticipation.getParticipations().get(0);
						String oldStatus = p.getStatus();
						p = ParticipationUtils.checkParticipationStatus(p);
						if (oldStatus.equals(Participation.REQUESTED) && 
								p.getStatus().equals(Participation.ACCEPTED)){
							Message msg = Message.obtain();
							msg.setData(ParticipationBundle.toBundle(p));
							RiderBroker.this.nav.nh.getHandler(RiderHandlers.STATUS_CHANGED_ID).sendMessage(msg);
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
