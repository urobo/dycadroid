/**
 * 
 */
package eu.fbk.dycapo.services.broker;

import java.util.Timer;
import java.util.TimerTask;

import eu.fbk.dycapo.activities.Navigation;

/**
 * @author riccardo
 *
 */
public class Broker{
	int role;
	Timer task = null;
	Navigation nav = null;
	
	private static final int SHORT_INTERVAL = 1000;
	private static final int LONG_INTERVAL = 60000;
	private static final int RIDER = 0;
	private static final int DRIVER = 1;
	
	public Broker(){
		
	}
	
	public Broker(int role,Navigation nav){
		this.role = role;
		this.nav = nav;
		task = new Timer();
		switch(role){
		case RIDER:
			
			task.scheduleAtFixedRate(new TimerTask(){

					@Override
					public void run() {
						
					
					}
				
				}, 
			SHORT_INTERVAL,
			LONG_INTERVAL);
			break;
			
		case DRIVER:
			task.scheduleAtFixedRate(new TimerTask(){

					@Override
					public void run() {
						
					
					}
				
				}, 
				SHORT_INTERVAL, 
				LONG_INTERVAL);
			
			break;
		}
		
	}
	
	
	
	

}