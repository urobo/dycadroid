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
		
		if( user.getBlind() instanceof Boolean) ((CheckBox)this.findViewById(R.id.checkBlind)).setChecked(user.getBlind());
		if(user.getDeaf() instanceof Boolean)((CheckBox)this.findViewById(R.id.checkDeaf)).setChecked(user.getDeaf());
		if(user.getSmoker() instanceof Boolean)((CheckBox)this.findViewById(R.id.checkSmoker)).setChecked(user.getSmoker());
		if(user.getDog() instanceof Boolean)((CheckBox)this.findViewById(R.id.checkDog)).setChecked(user.getDog());
		
		Preferences prefs= user.getPrefs();
		if(prefs.getDrive() instanceof Boolean)((CheckBox)this.findViewById(R.id.checkDrive)).setChecked(prefs.getDrive());
		if(prefs.getRide() instanceof Boolean)((CheckBox)this.findViewById(R.id.checkRide)).setChecked(prefs.getRide());
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
		
//		String ageRange= prefs.getAge();
//		int i=0;
//			
//		if (ageRange instanceof String){
//			while(ageRange.charAt(i)!='-')i++;
//			((EditText)this.findViewById(R.id.ageLeftBound)).setText(ageRange.subSequence(0, i));
//			((EditText)this.findViewById(R.id.ageRightBound)).setText(ageRange.subSequence(i+1, ageRange.length()));
//		}
		
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
		
		prefs.setDrive(((CheckBox)this.findViewById(R.id.checkDrive)).isChecked());
		prefs.setNonsmoking(((CheckBox)this.findViewById(R.id.checkNonSmoking)).isChecked());
		prefs.setRide(((CheckBox)this.findViewById(R.id.checkRide)).isChecked());
		
		
		boolean male=((CheckBox)this.findViewById(R.id.checkMale)).isChecked();
		boolean female=((CheckBox)this.findViewById(R.id.checkFemale)).isChecked();
		if ( male&&female || !male && !female)
			prefs.setGender(Preferences.BOTH);
		else if(male)
			prefs.setGender(Preferences.MALE);
		else if(female)
			prefs.setGender(Preferences.FEMALE);
		
			
		try{
		//int left = Integer.parseInt(((EditText)this.findViewById(R.id.ageLeftBound)).getText().toString());
		//int right = Integer.parseInt(((EditText)this.findViewById(R.id.ageRightBound)).getText().toString());
		//if (left<=right)prefs.setAge(String.valueOf(left) + "-"+ String.valueOf(right));
		DBPrefs.savePrefs(prefs);
		}catch (Exception e){
			Log.d(Tag.LOG +".SavePrefs.parseInt", e.getMessage());
		}
		
	}
}