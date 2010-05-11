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
import android.widget.EditText;
import eu.fbk.dycapo.activities.R;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.exceptions.Tag;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.models.Preferences;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.persistency.DBPrefs;

/**
 * @author riccardo
 *
 */
public class Prefs extends Activity implements OnClickListener{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.prefs);
        Button save = (Button)this.findViewById(R.id.savePrefsButton);
        save.setOnClickListener((OnClickListener) this);
    }

	@Override
	public void onClick(View v) {
		Preferences prefs= new Preferences();
		Person user = DBPerson.getUser();
		
				
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
		int left = Integer.parseInt(((EditText)this.findViewById(R.id.ageLeftBound)).getText().toString());
		int right = Integer.parseInt(((EditText)this.findViewById(R.id.ageRightBound)).getText().toString());
		if (left<=right)prefs.setAge(left + "-"+ right);
		DBPrefs.savePrefs(prefs);
		}catch (Exception e){
			Log.d(Tag.LOG +".SavePrefs.parseInt", e.getMessage());
		}
		
	}
}
