/**
 * 
 */
package eu.fbk.dycapo.services;

import android.os.Bundle;

/**
 * @author riccardo
 *
 */
public interface BundleModel {
	public Bundle toBundle();
	public void fromBundle(Bundle data);
}