/**
 * 
 */
package eu.fbk.dycapo.factories.json;

import org.json.JSONException;
import org.json.JSONObject;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Mode;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.persistency.User;
import eu.fbk.dycapo.services.DycapoServiceClient;

/**
 * @author riccardo
 *
 */
public abstract class ModeFetcher {
	public static final Mode fetchMode(JSONObject responseValue) throws DycapoException{

		Mode result = new Mode();
		try{
			
			if (responseValue.has(DycapoObjectsFetcher.HREF)){
				result.setHref(responseValue.getString(DycapoObjectsFetcher.HREF));
				
				}
			
			if (responseValue.has(Mode.CAPACITY))
				result.setCapacity(responseValue.getInt(Mode.CAPACITY));
			else 
				throw new DycapoException("error ModeFetcher.fetchMode : Mode.CAPACITY is compulsory");
			
			if (responseValue.has(Mode.COLOR))
				result.setColor(responseValue.getString(Mode.COLOR));
		
			if (responseValue.has(Mode.COST))
				result.setCost(responseValue.getDouble(Mode.COST));
			
			if (responseValue.has(Mode.KIND))
				result.setKind(responseValue.getString(Mode.KIND));
			else
				throw new DycapoException("error ModeFetcher.fetchMode : Mode.KIND is compulsory");
			
			if (responseValue.has(Mode.LIC))
				result.setLic(responseValue.getString(Mode.LIC));
			
			if (responseValue.has(Mode.MAKE))
				result.setMake(responseValue.getString(Mode.MAKE));
			else
				throw new DycapoException("error ModeFetcher.fetchMode : Mode.MAKE is compulsory");
				
			if (responseValue.has(Mode.MODEL))	
				result.setModel(responseValue.getString(Mode.MODEL));
			else
				throw new DycapoException("error ModeFetcher.fetchMode : Mode.MODEL is compulsory");
			
			if(responseValue.has(Mode.VACANCY))	
				result.setVacancy(responseValue.getInt(Mode.VACANCY));
			else
				throw new DycapoException("error ModeFetcher.fetchMode : Mode.VACANCY is compulsory");
			
			if (responseValue.has(Mode.YEAR))
				result.setYear(responseValue.getInt(Mode.YEAR));
			
			return result;
		}catch(JSONException e){
			e.printStackTrace();
		}
		return null;
	}
}
