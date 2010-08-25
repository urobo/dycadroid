/**
 * 
 */
package eu.fbk.dycapo.factories;

import android.util.Log;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Response;

/**
 * @author riccardo
 *
 */
public abstract class DycapoObjectsFactory {
	private static final String TAG ="DycapoObjectsFactory";
	public static final int XMLRPC = 1;
	public static final int REST = 2;
	
	public static final Object getDycapoObject (int method, Object value, boolean log) throws DycapoException{
		Response response = null;
		switch (method){
		case XMLRPC:
			response =  eu.fbk.dycapo.factories.xmlrpc.DycapoObjectsFetcher.fetchXMLRPCResponse(value);
		case REST:
			response =  eu.fbk.dycapo.factories.json.DycapoObjectsFetcher.fetchJSONResponse(value);
		}
		if (log) logResponse(response);
		return response;
	}
	
	public static final void logResponse(Response response){
		Log.d(TAG, "Response status code : " + String.valueOf(response.getCode()));
		Log.d(TAG, "Response message : " + ((response.getValue() instanceof String)?(String)response.getValue():Response.resolveType(response.getCode())));
		Log.d(TAG, "Response of Type : " + ((response.getType() instanceof String)?response.getType(): "No Type Provided!" ));
	}

}