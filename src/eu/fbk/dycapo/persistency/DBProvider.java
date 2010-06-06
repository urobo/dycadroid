/**
 * 
 */
package eu.fbk.dycapo.persistency;
import android.content.Context;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.config.Configuration;

import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Mode;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.models.Preferences;
import eu.fbk.dycapo.models.Trip;

/**
 * @author riccardo
 *
 */
public class DBProvider {

	   private static ObjectContainer database;
	   private static Context applicationContext;
	   private static Configuration dbConfiguration;
	   
		private static String db4oDBFullPath() {
		      return applicationContext.getDir("data", 0) + "/" + "dycadroid.db4o"; //
		}
		
		@SuppressWarnings("deprecation")
		public static ObjectContainer provideDB(){
			
			return Db4o.openFile(DBProvider.configure(), DBProvider.db4oDBFullPath());
		}
		
		public static ObjectContainer getDatabase(){
			if (database == null) database= DBProvider.provideDB();
			return database;
		}
		
		public static void configureProvider(Context ctx){
			applicationContext=ctx;
			configure();
			database=provideDB();
		}
		
		 @SuppressWarnings("deprecation")
		private static Configuration configure(){
			 	dbConfiguration =Db4o.newConfiguration();
			    
			 	dbConfiguration.objectClass(Trip.class).objectField(Trip.ID).indexed(true);
			    dbConfiguration.objectClass(Trip.class).cascadeOnUpdate(true);
			    dbConfiguration.objectClass(Trip.class).cascadeOnDelete(true);
			     
			    dbConfiguration.objectClass(Location.class).objectField(Location.GEORSS_POINT).indexed(true);
			    dbConfiguration.objectClass(Location.class).cascadeOnDelete(true);
			    dbConfiguration.objectClass(Location.class).cascadeOnUpdate(true);
			    
			    dbConfiguration.objectClass(Person.class).objectField(Person.USERNAME).indexed(true);
			    dbConfiguration.objectClass(Person.class).cascadeOnUpdate(true);
			    dbConfiguration.objectClass(Person.class).cascadeOnDelete(true);
			     
			    dbConfiguration.objectClass(Preferences.class);
			    dbConfiguration.objectClass(Preferences.class).cascadeOnDelete(true);
			    dbConfiguration.objectClass(Preferences.class).cascadeOnUpdate(true);
			    
			    dbConfiguration.objectClass(Mode.class).objectField(Mode.LIC).indexed(true);
			    dbConfiguration.objectClass(Mode.class).cascadeOnDelete(true);
			    dbConfiguration.objectClass(Mode.class).cascadeOnUpdate(true);
			    
			    dbConfiguration.objectClass(User.class).objectField(Person.USERNAME).indexed(true);
			    dbConfiguration.objectClass(User.class).cascadeOnUpdate(true);
			    dbConfiguration.objectClass(User.class).cascadeOnDelete(true);
			    
			    dbConfiguration.objectClass(Participation.class).objectField(Participation.PARTICIPANT).indexed(true);
			    dbConfiguration.objectClass(Participation.class).cascadeOnUpdate(true);
			    dbConfiguration.objectClass(Participation.class).cascadeOnDelete(true);
			    
			    dbConfiguration.objectClass(ActiveTrip.class).objectField(ActiveTrip.ID).indexed(true);
			    dbConfiguration.objectClass(ActiveTrip.class).cascadeOnUpdate(true);
			    dbConfiguration.objectClass(ActiveTrip.class).cascadeOnDelete(true);
			    
			    dbConfiguration.objectClass(Route.class).cascadeOnUpdate(true);
			    dbConfiguration.objectClass(Route.class).cascadeOnDelete(true);
			  			   
			 	dbConfiguration.lockDatabaseFile(false);
			    dbConfiguration.messageLevel(2);
			    return dbConfiguration;
		 }
		
		public static Configuration getdbConfiguration(){
			return dbConfiguration;
		}
		public static void setdbConfiguraiton(Configuration configuration){
			dbConfiguration = configuration;
		}
		
		public static Context getContext(){
			return applicationContext;
		}
		public static void setContext(Context ctx){
			applicationContext = ctx;
		}
		
		
}


