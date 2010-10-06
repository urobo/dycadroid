/**
 * 
 */
package eu.fbk.dycapo.maputils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import eu.fbk.dycapo.activities.Navigation;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Participation;
import eu.fbk.dycapo.persistency.DBParticipation;

/**
 * @author riccardo
 *
 */
public class PositionUpdater {
	private static final String TAG= "PositionUpdater";
	
	private Navigation nav = null;
	private Timer task = null;
	
	protected static final int SHORT_INTERVAL = 1000;
	protected static final int LONG_INTERVAL = 60000;
	
	/**
	 * @param nav
	 */
	private PositionUpdater(Navigation nav) {
		this.nav = nav;
		this.task = new Timer();
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
	
	public void startPositionUpdater(){
		this.task.scheduleAtFixedRate(new TimerTask(){

			@Override
			public void run() {
				List<Participation> lp = DBParticipation.getParticipations();
				int size = lp.size();
				List<Location> ppositions = new ArrayList<Location>();
				for (int i = 0; i< size; i++){
					if (lp.get(i).getStatus().equals(Participation.ACCEPTED)){
						ppositions.add(LocationService.getPosition(lp.get(i).getPerson()));
					}
				//redraw
				}
			}}, 
			SHORT_INTERVAL,
			LONG_INTERVAL);
	}
	public void stopPositionUpdater(){
		
	}
}
