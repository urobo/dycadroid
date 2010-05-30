/**
 * 
 */
package eu.fbk.dycapo.bundles;

import android.os.Bundle;

/**
 * @author riccardo
 *
 */
public interface BundleModel {
	public Bundle toBundle();
	public void fromBundle(Bundle data);
}
