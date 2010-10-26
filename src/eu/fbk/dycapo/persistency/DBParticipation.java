/**
 * 
 */
package eu.fbk.dycapo.persistency;

import java.util.List;

import android.util.Log;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import eu.fbk.dycapo.models.Participation;

/**
 * @author riccardo
 *
 */
public class DBParticipation {
	private static final String TAG = "DBParticipation";
	
	public static final void newTrip(){
		ObjectContainer db = DBProvider.getDatabase();
		db.delete(Participation.class);
		db.commit();
	}
	public static final void addParticipation(Participation p){
		ObjectContainer db = DBProvider.getDatabase();
		Participation tmp = new Participation();
		tmp.setAuthor(p.getAuthor());
		
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
		final String pname = p.getAuthor().getUsername();
		ObjectContainer db = DBProvider.getDatabase();
		ObjectSet result = db.query(new Predicate<Participation>() {
		      public boolean match(Participation proto) {
		          return proto.getAuthor().getUsername().equals(pname);
		    }
		});
		db.delete(result);
		db.store(p);
		db.commit();
	}
	
	@SuppressWarnings({ "serial" })
	public static final void removeParticipation (Participation p){
		final String pname = p.getAuthor().getUsername();
		ObjectContainer db = DBProvider.getDatabase();
		ObjectSet<Participation> result = db.query(new Predicate<Participation>() {
		      public boolean match(Participation proto) {
		          return proto.getAuthor().getUsername().equals(pname);
		    }
		});
		db.delete(result);
		db.commit();
	}
	
	public static final void saveParticipationList(List<Participation> pl){
		ObjectContainer db = DBProvider.getDatabase();
		db.delete(Participation.class);
		int size = pl.size();
		Log.d(TAG, "saving participation list. size : "+  String.valueOf(size));
		for (int i = 0 ; i< size; i++){
			Log.d(TAG, "saving "+ String.valueOf(i)+ "-th participation");
			db.store(pl.get(i));
			}
		db.commit();
	}
	
	@SuppressWarnings("serial")
	public static final List<Participation> getActiveParcitipants(){
		
		ObjectContainer db = DBProvider.getDatabase();
		List<Participation> result = db.query(new Predicate<Participation>() {
		      public boolean match(Participation proto) {
		          return proto.getStatus().equals(Participation.STARTED)
		          || proto.getStatus().equals(Participation.ACCEPTED);
		    }
		});
		return result;
	}
}
