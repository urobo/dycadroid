/**
 * 
 */
package eu.fbk.dycapo.activities;


import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.factories.json.DycapoObjectsFetcher;
import eu.fbk.dycapo.factories.json.UserMapper;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.persistency.DBMode;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.persistency.DBProvider;
import eu.fbk.dycapo.persistency.User;
import eu.fbk.dycapo.services.DycapoServiceClient;

/**
 * @author riccardo
 */
public class Home extends Activity implements OnClickListener{
	static final String TAG = "Home";
	static final int DIALOG_CHOOSER = 0;
	static final int DIALOG_REGISTER = 1;
	static final int DIALOG_LOGIN= 2;
	protected static final int FILL_SETTINGS = 0;
	protected static final int SETTINGS_SAVED_CORRECTLY = 3;
	 
	private View layoutLogin=null;
	private View layoutRegister=null;

	Menu myMenu=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.home);
		
		DBProvider.configureProvider(this.getApplicationContext());
		
		
		Button driver = (Button) findViewById(R.id.DriverButton);
		driver.setOnClickListener(this);
		Button rider = (Button) findViewById(R.id.RiderButton);
		rider.setOnClickListener(this);
		
		LayoutInflater inflater = (LayoutInflater) Home.this.getSystemService(LAYOUT_INFLATER_SERVICE);
		layoutRegister = inflater.inflate(R.layout.register,(ViewGroup) findViewById(R.id.newrl));
		layoutLogin = inflater.inflate(R.layout.login,(ViewGroup) findViewById(R.id.rl));
		
		
		User usr = DBPerson.getUser();
		if (!(usr instanceof User))this.showDialog(Home.DIALOG_CHOOSER);
		
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateDialog(int)
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog d = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		switch(id){
		case Home.DIALOG_CHOOSER:
			
	
			builder.setMessage("Is this the first time you are using DyCaDroid?")
			       .setCancelable(false)
			       .setPositiveButton("Register", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                Home.this.showDialog(Home.DIALOG_REGISTER);
			           }
			       })
			       .setNegativeButton("Sign in", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                Home.this.showDialog(Home.DIALOG_LOGIN);
			           }
			       });
			d = builder.create();
			
			break;
		case Home.DIALOG_LOGIN:
			builder.setView(layoutLogin);
			builder.setMessage("Sign in to your DyCaPo Account").setCancelable(false);
			d=builder.create();
			d.setTitle("Login");
			
			d.setButton("Sign in",new android.content.DialogInterface.OnClickListener(){

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
						usr.setPassword(passwordIn);
						} 	else throw new DycapoException ("Invalid Password");
						DBPerson.saveMe(usr);
						JSONObject temp = DycapoServiceClient.callDycapo(DycapoServiceClient.GET, 
								DycapoServiceClient.uriBuilder("persons/"+ usr.getUsername()), 
								null, usr.getUsername(), 
								usr.getPassword());
						usr.setHref(DycapoObjectsFetcher.buildPerson(temp).getHref());
						DBPerson.saveMe(usr);
						Home.this.handleSuccess.sendEmptyMessage(0);
					} catch (DycapoException e) {
						e.alertUser(getBaseContext());
						Message msg = new Message();
						msg.what=Home.DIALOG_LOGIN;
						msg.setTarget(handleFailure);
						msg.sendToTarget();
					} catch (JSONException e) {
						Log.e(TAG, e.getMessage());
						e.printStackTrace();
					}	
					
				}
				
			});
		
			break;
		case Home.DIALOG_REGISTER:
			
			builder.setView(layoutRegister);
			builder.setCancelable(false);
			d=builder.create();
			d.setTitle("Register");
			d.setButton("Register", new android.content.DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					String usernameIn = ((EditText)layoutRegister.findViewById(R.id.newgetNewUsername)).getText().toString();
					String passwordIn = ((EditText)layoutRegister.findViewById(R.id.newgetNewPassword)).getText().toString();
					String passwordConfirmIn = ((EditText)layoutRegister.findViewById(R.id.newgetConfirmNewPassword)).getText().toString();
					String emailIn = ((EditText)layoutRegister.findViewById(R.id.newGetEmail)).getText().toString();
					RadioButton male = (RadioButton)layoutRegister.findViewById(R.id.setMaleGender);
					RadioButton female = (RadioButton)layoutRegister.findViewById(R.id.setFemaleGender);
					User usr= new User();
					try {
						
						if (usernameIn instanceof String && !usernameIn.equals("")){
						usr.setUsername(usernameIn);
						}	else throw new DycapoException ("Invalid Username");
					
						if (passwordIn instanceof String && !passwordIn.equals("")){
							if (passwordConfirmIn instanceof String && !passwordConfirmIn.equals("")){
								
							}	else throw new DycapoException ("Invalid Password Confirmation");
							
							if (!passwordConfirmIn.equals(passwordIn))throw new DycapoException ("Passwords doesn't match");
							else {
								usr.setPassword(passwordConfirmIn);
							}
						} 	else throw new DycapoException ("Invalid Password");
						
						if (emailIn instanceof String && !emailIn.equals("")){
							usr.setEmail(emailIn);
						}	else throw new DycapoException ("Invalid Email");	
						
						TelephonyManager tMgr =(TelephonyManager)Home.this.getSystemService(Context.TELEPHONY_SERVICE);
						String phoneNum = tMgr.getLine1Number();
						usr.setPhone(phoneNum);
						usr.setGender(Person.MALE);
						if (male.isChecked())
							usr.setGender(Person.MALE);
						else if (female.isChecked())
							usr.setGender(Person.FEMALE);
				
						try {
							DBPerson.saveMe(usr);
							JSONObject response = DycapoServiceClient.callDycapo(DycapoServiceClient.POST, 
									DycapoServiceClient.uriBuilder("persons"),
									UserMapper.fromUserToJSONObject(usr),
									null,
									null);
							usr.setHref(DycapoObjectsFetcher.buildPerson(response).getHref());
							DBPerson.saveMe(usr);
							Home.this.handleSuccess.sendEmptyMessage(0);
						} catch (JSONException e) {
							Toast.makeText(Home.this, e.getMessage(), Toast.LENGTH_LONG);
							e.printStackTrace();
						}
						
					} catch (DycapoException e) {
						e.alertUser(getBaseContext());
						Message msg = new Message();
						msg.what=Home.DIALOG_REGISTER;
						msg.setTarget(handleFailure);
						msg.sendToTarget();
					} 	
					
				}
			});
			break;
		}
		return d;
	}
	private Handler handleSuccess = new Handler(){

		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			Intent intent = new Intent();
			intent.setClass(Home.this, Settings.class);
			Home.this.startActivity(intent);
		}
		
	};
	
	
	

	
	
	private Handler handleFailure = new Handler(){

		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
	
			switch (msg.what){
			case Home.DIALOG_LOGIN:
				showDialog(Home.DIALOG_LOGIN);
				break;
			case Home.DIALOG_REGISTER:
				showDialog(Home.DIALOG_REGISTER);
				break;
			}
		}
		
	};

	private void addRegularMenuItems(Menu menu){
		int base=Menu.FIRST; // value is 1
		menu.add(base,base,base,"Settings");
		menu.add(base,base+1,base+1,"Stats");
		menu.add(base,base+2,base+2,"Test");
	}
	


	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		this.myMenu=menu;
		addRegularMenuItems(menu);
		return true;
	}


	@Override
	public void onClick(View v) {	
		String role= null;
		switch(v.getId()){
		case R.id.DriverButton:
			role="driver";
			if (DBMode.getMode()== null){
				
				Intent intent = new Intent();
				intent.setClass(Home.this, Settings.class);
				intent.putExtra(Settings.TAB_FOCUS, Settings.CAR);
				Home.this.startActivity(intent);
				return;
				}
			break;
		case R.id.RiderButton:
			role="rider";
			break;
			
		}
		Intent intent=new Intent();
		intent.setClass(this,FastChoice.class);
		Bundle bundle=new Bundle();
		bundle.putString("role", role);
		intent.putExtras(bundle);
		this.startActivity(intent);
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int selected= item.getItemId();
		Intent i = new Intent();
		if(selected==1){
			i.setClass(this,Settings.class);
			this.startActivity(i);
		}else if (selected==2){
		
			
		}else if (selected==3){
			i.setClass(this, TestingActivity.class);
			this.startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}

}
