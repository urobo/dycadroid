/**
 * 
 */
package eu.fbk.dycapo.persistency;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

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
	
	@SuppressWarnings({ "serial", "rawtypes" })
	public static final void updateParticipation (Participation p){
		final String pname = p.getPerson().getUsername();
		ObjectContainer db = DBProvider.getDatabase();
		ObjectSet result = db.query(new Predicate<Participation>() {
		      public boolean match(Participation proto) {
		          return proto.getPerson().getUsername().equals(pname);
		    }
		});
		db.delete(result);
		db.store(p);
		db.commit();
	}
	
	@SuppressWarnings({ "serial" })
	public static final void removeParticipation (Participation p){
		final String pname = p.getPerson().getUsername();
		ObjectContainer db = DBProvider.getDatabase();
		ObjectSet<Participation> result = db.query(new Predicate<Participation>() {
		      public boolean match(Participation proto) {
		          return proto.getPerson().getUsername().equals(pname);
		    }
		});
		db.delete(result);
		db.commit();
	}
	
	public static final void saveParticipationList(List<Participation> pl){
		ObjectContainer db = DBProvider.getDatabase();
		ObjectSet<Participation> oldpl = db.queryByExample(Participation.class);
		db.delete(oldpl);
		int size = pl.size();
		for (int i = 0 ; i< size; i++)
			db.store(pl.get(i));
		db.commit();
	}
	
	@SuppressWarnings("serial")
	public static final List<Participation> getActiveParcitipants(){
		
		ObjectContainer db = DBProvider.getDatabase();
		List<Participation> result = db.query(new Predicate<Participation>() {
		      public boolean match(Participation proto) {
		          return proto.getStatus().equals(Participation.START)
		          || proto.getStatus().equals(Participation.ACCEPTED);
		    }
		});
		return result;
	}
}
