/**
 * 
 */
package eu.fbk.dycapo.activities.settings;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import eu.fbk.dycapo.activities.R;
import eu.fbk.dycapo.models.Preferences;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.persistency.User;

/**
 * @author riccardo
 *
 */
public class Me extends Activity implements OnClickListener {
	
	
	
	
	private String gender=null;
	private OnClickListener gender_listener = new OnClickListener() {
        public void onClick(View v) {
            // Perform action on clicks
            RadioButton rb = (RadioButton) v;
            switch(rb.getId()){
            case R.id.maleGender:
            	gender = "male";
            	break;
            case R.id.femaleGender:
            	gender = "female";
            	break;
            }
            
        }
    };
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.me);
        
        Button save = (Button)this.findViewById(R.id.saveMeButton);
        save.setOnClickListener((OnClickListener) this);
        
        
        RadioButton male=(RadioButton)this.findViewById(R.id.maleGender);
        RadioButton female=(RadioButton)this.findViewById(R.id.femaleGender);
        
        female.setOnClickListener(gender_listener);
        male.setOnClickListener(gender_listener);
        
        
       
		
		
		
        this.update();
    }
	
	public void update(){
		
		User user= DBPerson.getUser();
		if (user instanceof User){
		
		String readDB=user.getFirst_name();
		if (readDB instanceof String)
		((EditText)this.findViewById(R.id.getFirst_Name)).setText(readDB);
		
		readDB=user.getLast_name();
		if (readDB instanceof String)
		((EditText)this.findViewById(R.id.getLast_Name)).setText(readDB);
		
		readDB=user.getEmail();
		if (readDB instanceof String)
		((EditText)this.findViewById(R.id.getEmail)).setText(readDB);
		
		Integer ageDB = user.getAge();
		if (ageDB instanceof Integer)
		((EditText)this.findViewById(R.id.getAge)).setText(ageDB.toString());
		ageDB=null;
		
		readDB = user.getGender();
		if (readDB instanceof String){
			if(readDB.equals(Preferences.GENDER_PREFS[Preferences.MALE])){
				((RadioButton)this.findViewById(R.id.maleGender)).setChecked(true);
			}else
				((RadioButton)this.findViewById(R.id.femaleGender)).setChecked(true);
			}
		}
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.saveMeButton:
			User readForm= new User();
			String input;
		
		
			input= ((EditText)this.findViewById(R.id.getAge)).getText().toString();
			if (input instanceof String && !(input.equals("")))readForm.setAge(Integer.parseInt(input));
	
			input= ((EditText)this.findViewById(R.id.getEmail)).getText().toString();
			if (input instanceof String && !input.equals(""))readForm.setEmail(input);
		
			input=((EditText)this.findViewById(R.id.getFirst_Name)).getText().toString();
			if (input instanceof String && !input.equals(""))readForm.setFirst_name(input);
	
			readForm.setGender(this.gender);
		
			input=((EditText)this.findViewById(R.id.getLast_Name)).getText().toString();
			if (input instanceof String && !input.equals(""))readForm.setLast_name(input);

			DBPerson.updateMe(readForm);
		
			input=null;
			readForm=null;
			
			break;
		}
	}


}