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
public abstract class NavigationHandler {
	protected Navigation nav = null;
	
	public NavigationHandler(Navigation nav){
		this.nav = nav;
	}
	
	public abstract Handler getHandler (int code);
	
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

	public abstract static class HandlersFactory{
		public static final NavigationHandler getNavigationHandler(int role, Navigation nav){
			NavigationHandler nh = null;
			switch(role){
			case Environment.RIDER:
				nh = RiderHandler.getInstance(nav);
				break;
			case Environment.DRIVER:
				nh = DriverHandler.getInstance(nav);
				break;
			}
			return nh;
		}
	}
}