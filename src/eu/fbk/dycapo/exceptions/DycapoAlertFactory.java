/**
 * 
 */
package eu.fbk.dycapo.exceptions;

import android.content.Context;

/**
 * @author riccardo
 *
 */
public abstract class DycapoAlertFactory {
	public static final int YNALERT=0;
	public static final int STATUSALERT=1;
	
	public static final DycapoAlert getInstance(Context ctx,int type,int id,
			String Message) throws DycapoException{
		DycapoAlert alert=null;
		switch(type){
		case YNALERT:
			if (ctx instanceof Context)
				alert = YNAlert.getInstance(ctx, id);
			else throw new DycapoException("Context must be provided");
			break;
		case STATUSALERT:
			if(ctx instanceof Context){
				if (Message instanceof String)
					alert = StatusAlert.getInstance(ctx, Message);
				else 
					throw new DycapoException("Message is undefined");
			}else 
				throw new DycapoException("Context must be provided");
			break;
		}
		return alert;
	}
}
