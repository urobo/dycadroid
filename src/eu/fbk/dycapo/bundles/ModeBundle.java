/**
 * 
 */
package eu.fbk.dycapo.bundles;

import eu.fbk.dycapo.models.Mode;
import android.os.Bundle;

/**
 * @author riccardo
 *
 */
public final class ModeBundle {
	public static Bundle toBundle (Mode mode){
		Bundle result = new Bundle();
		
		if (mode.getCapacity() instanceof Integer)
			result.putInt(Mode.CAPACITY, mode.getCapacity());
		
		if (mode.getColor() instanceof String)
			result.putString(Mode.COLOR, mode.getColor());
		
		if (mode.getCost() instanceof Double)
			result.putDouble(Mode.COST, mode.getCost());
		
		if (mode.getKind() instanceof String)
			result.putString(Mode.KIND, mode.getKind());
		
		if (mode.getLic() instanceof String)
			result.putString(Mode.LIC, mode.getLic());
		
		if (mode.getMake() instanceof String)
			result.putString(Mode.MAKE, mode.getMake());
		
		if (mode.getModel() instanceof String)
			result.putString(Mode.MODEL, mode.getModel());
		
		if (mode.getVacancy() instanceof Integer)
			result.putInt(Mode.VACANCY, mode.getVacancy());
		
		if (mode.getYear() instanceof Integer)
			result.putInt(Mode.YEAR, mode.getYear());
					
		return result;
	}
	
	public static Mode fromBundle (Bundle data){
		Mode mode = new Mode();
		
		if (data.containsKey(Mode.CAPACITY))
			mode.setCapacity(data.getInt(Mode.CAPACITY));
		
		if (data.containsKey(Mode.COLOR))
			mode.setColor(data.getString(Mode.COLOR));
		
		if (data.containsKey(Mode.COST))
			mode.setCost(data.getDouble(Mode.COST));
		
		if (data.containsKey(Mode.KIND))
			mode.setKind(data.getString(Mode.KIND));
		
		if (data.containsKey(Mode.LIC))
			mode.setLic(data.getString(Mode.LIC));
		
		if (data.containsKey(Mode.MAKE))
			mode.setMake(data.getString(Mode.MAKE));
			
		if (data.containsKey(Mode.MODEL))
			mode.setModel(data.getString(Mode.MODEL));
		
		if (data.containsKey(Mode.VACANCY))
			mode.setVacancy(data.getInt(Mode.VACANCY));
		
		if (data.containsKey(Mode.YEAR))
			mode.setYear(data.getInt(Mode.YEAR));
		
		return mode;
	}
}
