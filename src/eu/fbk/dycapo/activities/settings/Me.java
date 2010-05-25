/**
 * 
 */
package eu.fbk.dycapo.activities.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import eu.fbk.dycapo.activities.R;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Preferences;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.persistency.User;

/**
 * @author riccardo
 *
 */
public class Me extends Activity implements OnClickListener {
	
	static final int DIALOG_CHANGE_LOGIN = 0;
	
	private View layoutLogin=null;
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
        
        
        LayoutInflater inflater = (LayoutInflater) Me.this.getSystemService(LAYOUT_INFLATER_SERVICE);
		layoutLogin = inflater.inflate(R.layout.login,(ViewGroup) findViewById(R.id.rl));
		
		Button changeLogin = ((Button)this.findViewById(R.id.changeLogin));
		changeLogin.setOnClickListener(this);
		
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
		case R.id.changeLogin:
			showDialog(Me.DIALOG_CHANGE_LOGIN);
			break;
		}
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateDialog(int)
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(Me.this);
		AlertDialog d =  null;
		switch(id){
		case Me.DIALOG_CHANGE_LOGIN:
			builder.setView(layoutLogin);
			builder.setCancelable(true);
			d=builder.create();
			d.setTitle("Login");
			d.setMessage("Insert new DyCaPo Login credentials");
			d.setButton("Save",new android.content.DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					String usernameIn = ((EditText)layoutLogin.findViewById(R.id.getNewUsername)).getText().toString();
					String passwordIn = ((EditText)layoutLogin.findViewById(R.id.getNewPassword)).getText().toString();
					User usr = new User();
					try {
						
						if (usernameIn instanceof String && !usernameIn.equals("")){
						usr.setUsername(usernameIn);
						}	else throw new DycapoException ("Invalid Username");
					
						if (passwordIn instanceof String && !passwordIn.equals("")){
						
						} 	else throw new DycapoException ("Invalid Password");
												
						DBPerson.saveMe(usr);
						
					} catch (DycapoException e) {
						e.alertUser(getBaseContext());
						Message msg = new Message();
						msg.what=Me.DIALOG_CHANGE_LOGIN;
						msg.setTarget(handleFailure);
						msg.sendToTarget();
					}	
					
				}
				});
			break;
		}
		return d;
	}
	
	private Handler handleFailure= new Handler(){

	/* (non-Javadoc)
	 * @see android.os.Handler#handleMessage(android.os.Message)
	 */
	@Override
	public void handleMessage(Message msg) {

		switch (msg.what){
		case Me.DIALOG_CHANGE_LOGIN:
			showDialog(Me.DIALOG_CHANGE_LOGIN);
			break;
			}
		}
	};
}