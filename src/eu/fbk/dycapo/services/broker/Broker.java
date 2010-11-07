/**
 * 
 */
package eu.fbk.dycapo.services.broker;

import java.util.Timer;

import eu.fbk.dycapo.activities.Navigation;
import eu.fbk.dycapo.util.Environment;

/**
 * @author riccardo
 * 
 */
public abstract class Broker {

	protected Timer task = null;
	protected Navigation nav = null;

	protected static final int SHORT_INTERVAL = 1000;
	protected static final int LONG_INTERVAL = 60000;

	protected Broker() {

	}

	protected Broker(Navigation nav) {

		this.nav = nav;
	}

	/**
	 * @return the task
	 */
	public Timer getTask() {
		return task;
	}

	/**
	 * @param task
	 *            the task to set
	 */
	public void setTask(Timer task) {
		this.task = task;
	}

	/**
	 * @return the nav
	 */
	public Navigation getNav() {
		return nav;
	}

	/**
	 * @param nav
	 *            the nav to set
	 */
	public void setNav(Navigation nav) {
		this.nav = nav;
	}

	public void startBroker() {

	}

	public void stopBroker() {

	}

	public abstract static class BrokerFactory {
		public static final Broker getBroker(int role, Navigation nav) {
			Broker br = null;
			switch (role) {
			case Environment.RIDER:
				br = new RiderBroker(nav);
				break;
			case Environment.DRIVER:
				br = new DriverBroker(nav);
				break;
			}
			return br;
		}
	}
}