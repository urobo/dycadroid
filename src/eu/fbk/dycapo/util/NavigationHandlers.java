/**
 * 
 */
package eu.fbk.dycapo.util;

import android.os.Handler;
import eu.fbk.dycapo.activities.Navigation;

/**
 * @author riccardo
 *
 */
public abstract class NavigationHandlers {
	protected Navigation nav = null;
	
	public NavigationHandlers(Navigation nav){
		this.nav = nav;
	}
	
	public abstract Handler getHandler (int code);
	
}
