/**
 * 
 */
package eu.fbk.dycapo.util.handlers;

import eu.fbk.dycapo.activities.Navigation;
import eu.fbk.dycapo.util.Environment;

/**
 * @author riccardo
 * 
 */
public abstract class NavigationHandler {
	protected Navigation nav = null;

	public NavigationHandler(Navigation nav) {
		this.nav = nav;
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

	public abstract static class HandlersFactory {
		public static final NavigationHandler getNavigationHandler(int role,
				Navigation nav) {
			NavigationHandler nh = null;
			switch (role) {
			case Environment.RIDER:
				nh = new RiderHandler(nav);
				break;
			case Environment.DRIVER:
				nh = new DriverHandler(nav);
				break;
			}
			return nh;
		}
	}
}