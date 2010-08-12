/**
 * 
 */
package eu.fbk.dycapo.exceptions;

import android.app.Dialog;

/**
 * @author riccardo
 *
 */
public abstract class DycapoAlert {
	protected Dialog dialog;
	protected DycapoAlert(String Message){
	}
}
