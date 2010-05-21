/**
 * 
 */
package eu.fbk.dycapo.activities.settings;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import eu.fbk.dycapo.activities.R;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.exceptions.Tag;
import eu.fbk.dycapo.models.Preferences;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.persistency.DBPrefs;
import eu.fbk.dycapo.persistency.User;

/**
 * @author riccardo
 *
 */
public class Prefs extends Activity implements OnClickListener{
//	@SuppressWarnings("unchecked")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.prefs);
        Button save = (Button)this.findViewById(R.id.savePrefsButton);
        save.setOnClickListener((OnClickListener) this);
        this.update();
        
//        Spinner s = (Spinner) findViewById(R.id.spinner);
//        ArrayAdapter adapter = ArrayAdapter.createFromResource(
//                this, R.array.genders, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        s.setAdapter(adapter);
    }
	
	public void update(){
		User user= DBPerson.getUser();
		if(user instanceof User){
		if(user.getBlind() instanceof Boolean) ((CheckBox)this.findViewById(R.id.checkBlind)).setChecked(user.getBlind());
		if(user.getDeaf() instanceof Boolean)((CheckBox)this.findViewById(R.id.checkDeaf)).setChecked(user.getDeaf());
		if(user.getSmoker() instanceof Boolean)((CheckBox)this.findViewById(R.id.checkSmoker)).setChecked(user.getSmoker());
		if(user.getDog() instanceof Boolean)((CheckBox)this.findViewById(R.id.checkDog)).setChecked(user.getDog());
	
		}
		
		Preferences prefs= DBPrefs.getPrefs();
		if (prefs instanceof Preferences){
			if(prefs.getNonsmoking() instanceof Boolean)((CheckBox)this.findViewById(R.id.checkNonSmoking)).setChecked(prefs.getNonsmoking());
			if(prefs.getPet() instanceof Boolean)((CheckBox)this.findViewById(R.id.checkPet)).setChecked(prefs.getPet());
		
			Integer gender = prefs.getGender();
			if(gender instanceof Integer) {
				if (gender.intValue()==Preferences.MALE)
					((CheckBox)this.findViewById(R.id.checkMale)).setChecked(true);
				else if (gender.intValue()==Preferences.FEMALE)
					((CheckBox)this.findViewById(R.id.checkFemale)).setChecked(true);
				else{
					((CheckBox)this.findViewById(R.id.checkFemale)).setChecked(true);
					((CheckBox)this.findViewById(R.id.checkMale)).setChecked(true);
				}
			
			}
		

		
	 }
	}
	
	@Override
	public void onClick(View v) {
		Preferences prefs= new Preferences();
		User user = DBPerson.getUser();
		
				
		user.setBlind( ((CheckBox)this.findViewById(R.id.checkBlind)).isChecked());
		user.setDeaf(((CheckBox)this.findViewById(R.id.checkDeaf)).isChecked());
		user.setDog(((CheckBox)this.findViewById(R.id.checkDog)).isChecked());
		user.setSmoker(((CheckBox)this.findViewById(R.id.checkSmoker)).isChecked());
		
		try {
			DBPerson.savePersonalPrefs(user);
		} catch (DycapoException e) {
			Log.d(Tag.LOG + ".SavePersonalPrefs", e.getMessage());
		}

		prefs.setNonsmoking(((CheckBox)this.findViewById(R.id.checkNonSmoking)).isChecked());
		
		boolean male=((CheckBox)this.findViewById(R.id.checkMale)).isChecked();
		boolean female=((CheckBox)this.findViewById(R.id.checkFemale)).isChecked();
		if ( male&&female || !male && !female)
			prefs.setGender(Preferences.BOTH);
		else if(male)
			prefs.setGender(Preferences.MALE);
		else if(female)
			prefs.setGender(Preferences.FEMALE);
		
			
		try{
		
		DBPrefs.savePrefs(prefs);
		}catch (Exception e){
			Log.d(Tag.LOG +".SavePrefs", e.getMessage());
		}
		
	}
}
