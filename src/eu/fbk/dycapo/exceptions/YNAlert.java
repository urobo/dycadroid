/**
 * 
 */
package eu.fbk.dycapo.exceptions;

import eu.fbk.dycapo.activities.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

/**
 * @author riccardo
 *
 */
public class YNAlert extends DycapoAlert {
	private static int INSTANCES = 0;
	private static YNAlert INSTANCE;
	private static Context CTX;
	private static AlertDialog.Builder AB = null;
	
	public static final int CANCEL_REQUESTED_RIDE=0;
	public static final int FINISH_RIDE=1;
	public static final int START_RIDE=2;
	public static final int FINISH_TRIP=3;
	public static final int START_TRIP=4;
	
	private static final void configureAB(){
		AB = new AlertDialog.Builder(CTX)
		.setIcon(0)
		.setTitle("Notification")
		.setPositiveButton("Yes", null)
		.setNegativeButton("No", null);
	}
	
	public static final synchronized YNAlert getInstance(Context ctx, int id){
		CTX = ctx;
		configureAB();
		if (INSTANCES == 0){
			INSTANCE = new YNAlert(null,id);
			INSTANCES = 1;
		}else {
			INSTANCE.setDialog(AB.setMessage(getMessageFromId(id)).create());
		}
		return INSTANCE;
	}
	
	
	private static final String getMessageFromId(int id){
		String msg = "";
		switch(id){
		case CANCEL_REQUESTED_RIDE:
			msg = CTX.getResources().getString(R.string.cancel_requested_ride);
			break;
		case FINISH_RIDE:
			msg = CTX.getResources().getString(R.string.finish_ride);
			break;
		case START_RIDE:
			msg = CTX.getResources().getString(R.string.start_ride);
			break;
		case FINISH_TRIP:
			msg = CTX.getResources().getString(R.string.finish_trip);
			break;
		case START_TRIP:
			msg = CTX.getResources().getString(R.string.start_trip);
			break;
		}
		return msg;
	}
	
	protected YNAlert(String Message,int id) {
		super(Message);
		this.dialog = AB.setMessage(getMessageFromId(id)).create();
	}
	
	public Dialog getDialog(){
		return this.dialog;
	}
	
	private void setDialog(Dialog dialog){
		this.dialog = dialog;
	}
}
