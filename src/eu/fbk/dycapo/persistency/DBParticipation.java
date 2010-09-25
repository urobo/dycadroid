/**
 * 
 */
package eu.fbk.dycapo.persistency;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import eu.fbk.dycapo.models.Participation;

/**
 * @author riccardo
 *
 */
public class DBParticipation {
	
	public static final void newTrip(){
		ObjectContainer db = DBProvider.getDatabase();
		db.delete(Participation.class);
		db.commit();
	}
	public static final void addParticipation(Participation p){
		ObjectContainer db = DBProvider.getDatabase();
		Participation tmp = new Participation();
		tmp.setPerson(p.getPerson());
		
		List<Participation> ps = db.queryByExample(tmp);
		if (!ps.isEmpty()){
			tmp = null;
			return;
		}else{
			db.store(p);
			db.commit();
			tmp = null;
		}
	}
	
	public static final List<Participation> getParticipations(){
		ObjectContainer db = DBProvider.getDatabase();
		List<Participation> lp = db.queryByExample(Participation.class);
		return lp;
	}
	
	public static final void removeParticipation (Participation p){
		ObjectContainer db = DBProvider.getDatabase();
		Participation tmp = new Participation();
		tmp.setPerson(p.getPerson());
		
		ObjectSet<Participation> ps = db.queryByExample(tmp);
		if(ps.hasNext()){
			db.delete(ps);
			db.commit();	
		}
		tmp = null;
	}
}
