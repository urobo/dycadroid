/**
 * 
 */
package eu.fbk.dycapo.services;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.maps.MapView;

import eu.fbk.dycapo.bundles.LocationBundle;
import eu.fbk.dycapo.bundles.PersonBundle;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Person;

/**
 * @author riccardo
 *
 */
public class Receiver extends BroadcastReceiver {
	
	private MapView mMapView;
	
	public Receiver (MapView mMapView){
		this.mMapView = mMapView;
	}
	/* (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle extras = intent.getExtras();
		if (extras.containsKey(PositionUpdater.TASK)){
			if (extras.getString(PositionUpdater.TASK).equals(PositionUpdater.getTask(PositionUpdater.GET_POSITIONS))){
				int i = 0;
				ArrayList<Person> participants = new ArrayList<Person>();
				while (extras.containsKey("participant"+ String.valueOf(i))){
					participants.add(PersonBundle.fromBundle(extras.getBundle("participant"+String.valueOf(i))));
					i++;
				}
				updateParticipationPositions(participants);
			}else if (extras.getString(PositionUpdater.TASK).equals(PositionUpdater.getTask(PositionUpdater.UPDATE_POSITION))){
				if (extras.containsKey(Location.TAG))
					updatePosition(LocationBundle.fromBundle(extras.getBundle(Location.TAG)));
			}		
		}
	}
	
	private void updatePosition(Location position){
		
	}
	
	private void updateParticipationPositions(ArrayList<Person> participants){
		
	}

}
