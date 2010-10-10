/**
 * 
 */
package eu.fbk.dycapo.maputils;

import java.util.List;
import java.util.Timer;

import eu.fbk.dycapo.activities.Navigation;
import eu.fbk.dycapo.models.Participation;
import eu.fbk.dycapo.util.Environment;

/**
 * @author riccardo
 *
 */
public abstract class PositionUpdater {
	
	protected static final int SHORT_INTERVAL = 1000;
	protected static final int LONG_INTERVAL = 60000;
	
	protected DycapoItemizedOverlay itemizedoverlay = null;
	protected Navigation nav =null;
	protected Timer task = null;
	
	
	
	/**
	 * @return the itemizedoverlay
	 */
	public DycapoItemizedOverlay getItemizedoverlay() {
		return itemizedoverlay;
	}
	/**
	 * @param itemizedoverlay the itemizedoverlay to set
	 */
	public void setItemizedoverlay(DycapoItemizedOverlay itemizedoverlay) {
		this.itemizedoverlay = itemizedoverlay;
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
	
	
	protected PositionUpdater(Navigation nav){
		this.nav=nav;
	}
	
	public abstract void startPositionUpdater();
		
	
	public abstract void stopPositionUpdater();
	
	
	public abstract void drawParticipants (List<Participation> participants);
	
	public abstract static class PositionUpdaterFactory{
		public static final PositionUpdater buildPoistionUpdater(int role, Navigation nav){
			PositionUpdater pu = null;
			switch(role){
			case Environment.RIDER:
				pu = RiderPositionService.getInstace(nav);
				break;
			case Environment.DRIVER:
				pu = DriverPositionService.getInstace(nav);
				break;
			}
			return pu;
		}
	}
}
