/**
 * 
 */
package eu.fbk.dycapo.activities.settings;

import org.json.JSONException;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import eu.fbk.dycapo.activities.R;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.exceptions.Tag;
import eu.fbk.dycapo.factories.json.UserMapper;
import eu.fbk.dycapo.models.Preferences;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.persistency.DBPrefs;
import eu.fbk.dycapo.persistency.User;
import eu.fbk.dycapo.services.DycapoServiceClient;

/**
 * @author riccardo
 *
 */
public class Prefs extends Activity implements OnClickListener{
	private static final String TAG = "Settings.Prefs";
	private static Dialog pd;
	//	@SuppressWarnings("unchecked")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.prefs);
        Button save = (Button)this.findViewById(R.id.savePrefsButton);
        save.setOnClickListener((OnClickListener) this);
        this.update();

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
		pd = ProgressDialog.show(Prefs.this, "Processing...", "Updating Personal Preferences", true, false);
		new Thread(){

			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				Preferences prefs= new Preferences();
				User user = DBPerson.getUser();
				
						
				user.setBlind( ((CheckBox)Prefs.this.findViewById(R.id.checkBlind)).isChecked());
				user.setDeaf(((CheckBox)Prefs.this.findViewById(R.id.checkDeaf)).isChecked());
				user.setDog(((CheckBox)Prefs.this.findViewById(R.id.checkDog)).isChecked());
				user.setSmoker(((CheckBox)Prefs.this.findViewById(R.id.checkSmoker)).isChecked());
				
				

				prefs.setNonsmoking(((CheckBox)Prefs.this.findViewById(R.id.checkNonSmoking)).isChecked());
				
				boolean male=((CheckBox)Prefs.this.findViewById(R.id.checkMale)).isChecked();
				boolean female=((CheckBox)Prefs.this.findViewById(R.id.checkFemale)).isChecked();
				if ( male&&female || !male && !female)
					prefs.setGender(Preferences.BOTH);
				else if(male)
					prefs.setGender(Preferences.MALE);
				else if(female)
					prefs.setGender(Preferences.FEMALE);
				
				
				try {
					DBPrefs.savePrefs(prefs);
					DBPerson.savePersonalPrefs(user);
					user = DBPerson.getUser();
					DycapoServiceClient.callDycapo(DycapoServiceClient.PUT, 
							DycapoServiceClient.uriBuilder("persons/"+ user.getUsername()),
							UserMapper.fromUserToJSONObject(user) ,
							user.getUsername() , 
							user.getPassword());
					Prefs.this.updatePrefs.sendEmptyMessage(OK);
				} catch (DycapoException e) {
					e.alertUser(Prefs.this);
					Log.d(Tag.LOG + ".SavePersonalPrefs", e.getMessage());
				} catch (JSONException e) {
					Log.e(TAG, e.getMessage());
					e.printStackTrace();
				}
				
				
			}
			
		}.start();
		
	}
	
	private static final int OK = 0;
	protected Handler updatePrefs = new Handler(){

		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case OK:
				pd.dismiss();
				break;
			}
		}
		
	};
}
