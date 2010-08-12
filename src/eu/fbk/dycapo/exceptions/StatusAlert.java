/**
 * 
 */

package eu.fbk.dycapo.exceptions;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;


/**
 * @author riccardo
 *
 */

//TODO
public class StatusAlert extends DycapoAlert {
	private static Context CTX = null;
	private static AlertDialog.Builder AB = null;
	private static final void configureAB(){
		AB = new AlertDialog.Builder(CTX)
		.setIcon(0)
		.setTitle("Notification")
		.setPositiveButton("Ok", null);
	}
	
	protected StatusAlert(String Message) {
		super(Message);
		AB.setMessage(Message);
		this.dialog = AB.create();
	}
	
	private static int INSTANCES = 0;
	private static StatusAlert INSTANCE;
	
	public static final synchronized StatusAlert getInstance(Context ctx,String Message){
		CTX= ctx;
		configureAB();
		if (INSTANCES == 0){
			INSTANCE = new StatusAlert(Message);
			INSTANCES = 1;
		}else {
			AB.setMessage(Message);
			INSTANCE.dialog = AB.create();
		}
		return INSTANCE;
	}

	public Dialog getDialog(){
		return this.dialog;
	}
}