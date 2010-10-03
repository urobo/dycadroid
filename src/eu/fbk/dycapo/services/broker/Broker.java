/**
 * 
 */
package eu.fbk.dycapo.services.broker;

import java.util.Timer;

import eu.fbk.dycapo.activities.Navigation;

/**
 * @author riccardo
 *
 */
public abstract class Broker{
	protected int role;
	protected Timer task = null;
	protected Navigation nav = null;
	
	protected static final int SHORT_INTERVAL = 1000;
	protected static final int LONG_INTERVAL = 60000;
	protected static final int RIDER = 0;
	protected static final int DRIVER = 1;
	
	public Broker(){
		
	}
	
	public Broker(int role,Navigation nav){
		this.role = role;
		this.nav = nav;
	}

	/**
	 * @return the role
	 */
	public int getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(int role) {
		this.role = role;
	}

	/**
	 * @return the task
	 */
	public Timer getTask() {
		return task;
	}

	/**
	 * @param task the task to set
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
	 * @param nav the nav to set
	 */
	public void setNav(Navigation nav) {
		this.nav = nav;
	}
	
	public void startBroker(){
		
	}
	
	public void stopBroker(){
		
	}
}