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
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.persistency.DBPerson;

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

    }

	@Override
	public void onClick(View v) {
		Person readForm= new Person();
		String input;
		
		input= ((EditText)this.findViewById(R.id.getAge)).getText().toString();
		if (input instanceof String)readForm.setAge(Integer.parseInt(input));
		
		input= ((EditText)this.findViewById(R.id.getEmail)).getText().toString();
		if (input instanceof String)readForm.setEmail(input);
		
		input=((EditText)this.findViewById(R.id.getFirst_Name)).getText().toString();
		if (input instanceof String)readForm.setFirst_name(input);
		
		readForm.setGender(this.gender);
		
		input=((EditText)this.findViewById(R.id.getLast_Name)).getText().toString();
		if (input instanceof String)readForm.setLast_name(input);
		
		input=((EditText)this.findViewById(R.id.getPassword)).getText().toString();
		if (input instanceof String)readForm.setPassword(input);
		
		input=((EditText)this.findViewById(R.id.getUsername)).getText().toString();
		if (input instanceof String)readForm.setUsername(input);
		
		DBPerson.saveMe(readForm);
		
		input=null;
		readForm=null;
	}

}
