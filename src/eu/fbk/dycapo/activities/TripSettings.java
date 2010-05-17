/**
 * 
 */
package eu.fbk.dycapo.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author riccardo
 *
 */
public class TripSettings extends Activity implements OnClickListener {
	
	String role = null;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.role = this.getIntent().getExtras().getString("role");
		if (role.equals("rider")) this.setContentView(R.layout.rider_trip);
		else this.setContentView(R.layout.driver_trip);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
