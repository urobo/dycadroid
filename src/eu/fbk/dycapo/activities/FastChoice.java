/**
 * 
 */
package eu.fbk.dycapo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author riccardo
 *
 */
public class FastChoice extends Activity implements OnClickListener{
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.trip);
		
		Button newTrip = (Button)this.findViewById(R.id.newTripButton);
		newTrip.setOnClickListener(this);
		
		Button recentTrip = (Button)this.findViewById(R.id.recentTripButton);
		recentTrip.setOnClickListener(this);
		Button mostTrip = (Button)this.findViewById(R.id.mostTripButton);
		mostTrip.setOnClickListener(this);
		Button historyTrip= (Button)this.findViewById(R.id.historyTripButton);
		historyTrip.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.newTripButton:
			Intent intent= new Intent();
			intent.setClass(this,TripSettings.class);
			intent.putExtras(this.getIntent().getExtras());
			this.startActivity(intent);
			break;
		case R.id.mostTripButton:
			break;
		case R.id.recentTripButton:
			break;
		case R.id.historyTripButton:
			break;
		
		}
	}
}
