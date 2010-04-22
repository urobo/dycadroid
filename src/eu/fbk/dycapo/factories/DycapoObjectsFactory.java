/**
 * 
 */
package eu.fbk.dycapo.factories;

import eu.fbk.dycapo.exceptions.DycapoException;



/**
 * @author riccardo
 *
 */
public interface DycapoObjectsFactory {
	public Object fetchXMLRPCResponse(Object value) throws DycapoException;
}
