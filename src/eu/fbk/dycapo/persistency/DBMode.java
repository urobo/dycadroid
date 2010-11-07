/**
 * 
 */
package eu.fbk.dycapo.persistency;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import eu.fbk.dycapo.models.Mode;

/**
 * @author riccardo
 * 
 */
public final class DBMode {
	public static boolean saveMode(Mode car) {
		ObjectContainer db = DBProvider.getDatabase();
		ObjectSet<Mode> found = db.queryByExample(Mode.class);
		Mode p_car;

		if (!found.isEmpty()) {
			p_car = found.next();
			db.delete(Mode.class);
		}

		else
			p_car = new Mode();

		String input = null;

		if (car.getCapacity() instanceof Integer)
			p_car.setCapacity(car.getCapacity());

		input = car.getColor();
		if (input instanceof String)
			p_car.setColor(input);

		input = car.getMake();
		if (input instanceof String)
			p_car.setMake(input);

		input = car.getModel();
		if (input instanceof String)
			p_car.setModel(input);

		input = car.getLic();
		if (input instanceof String)
			p_car.setLic(input);

		db.store(p_car);
		db.commit();

		return true;
	}

	public static Mode getMode() {
		ObjectContainer db = DBProvider.getDatabase();
		ObjectSet<Mode> found = db.queryByExample(Mode.class);

		if (!found.isEmpty()) {
			Mode car = found.next();
			return car;
		}

		return null;
	}
}
