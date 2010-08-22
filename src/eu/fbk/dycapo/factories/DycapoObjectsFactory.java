/**
 * 
 */
package eu.fbk.dycapo.factories;

import eu.fbk.dycapo.exceptions.DycapoException;

/**
 * @author riccardo
 *
 */
public abstract class DycapoObjectsFactory {
	
	public static final int XMLRPC = 1;
	public static final int REST = 2;
	
	public static final Object getDycapoObject (int method, Object value, boolean log) throws DycapoException{
		switch (method){
		case XMLRPC:
			return eu.fbk.dycapo.factories.xmlrpc.DycapoObjectsFetcher.fetchXMLRPCResponse(value,log);
		case REST:
			return eu.fbk.dycapo.factories.json.DycapoObjectsFetcher.fetchJSONResponse(value,log);
		}
		return null;
	}

}
