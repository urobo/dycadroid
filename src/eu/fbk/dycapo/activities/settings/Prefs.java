/**
 * 
 */
package eu.fbk.dycapo.activities.settings;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import eu.fbk.dycapo.activities.R;

/**
 * @author riccardo
 *
 */
public class Prefs extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.prefs);
        
    }
}
