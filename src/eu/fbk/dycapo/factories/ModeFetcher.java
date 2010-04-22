/**
 * 
 */
package eu.fbk.dycapo.factories;

import java.util.HashMap;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Mode;

/**
 * @author riccardo
 *
 */
public class ModeFetcher {
	public static Mode fetchMode(HashMap<String,Object> value) throws DycapoException{
		if(value instanceof HashMap<?,?>){
			Mode result = new Mode();
			
			if(value.containsKey(Mode.CAPACITY))
				result.setCapacity((Integer)value.get(Mode.CAPACITY));
			else 
				throw new DycapoException("error ModeFetcher.fetchMode : Mode.CAPACITY is compulsory");
			
			if(value.containsKey(Mode.COLOR))
				result.setColor((String)value.get(Mode.COLOR));
		
			if (value.containsKey(Mode.COST))
				result.setCost((Double)value.get(Mode.COST));
			
			if (value.containsKey(Mode.KIND))
				result.setKind((String)value.get(Mode.KIND));
			else
				throw new DycapoException("error ModeFetcher.fetchMode : Mode.KIND is compulsory");
			
			if (value.containsKey(Mode.LIC))
				result.setLic((String)value.get(Mode.LIC));
			
			if (value.containsKey(Mode.MAKE))
				result.setMake((String)value.get(Mode.MAKE));
			else
				throw new DycapoException("error ModeFetcher.fetchMode : Mode.MAKE is compulsory");
				
			if (value.containsKey(Mode.MODEL))	
				result.setModel((String)value.get(Mode.MODEL));
			else
				throw new DycapoException("error ModeFetcher.fetchMode : Mode.MODEL is compulsory");
			
			if(value.containsKey(Mode.VACANCY))	
				result.setVacancy((Integer)value.get(Mode.VACANCY));
			else
				throw new DycapoException("error ModeFetcher.fetchMode : Mode.VACANCY is compulsory");
			
			if (value.containsKey(Mode.YEAR))
				result.setYear((Integer)value.get(Mode.YEAR));
			
			return result;
		}
		return null;
	}
}
