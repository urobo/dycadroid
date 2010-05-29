/**
 * 
 */
package eu.fbk.dycapo.activities;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.persistency.DBProvider;
import eu.fbk.dycapo.persistency.User;

/**
 * @author riccardo
 */
public class Home extends Activity implements OnClickListener{
	
	static final int DIALOG_CHOOSER = 0;
	static final int DIALOG_REGISTER = 1;
	static final int DIALOG_LOGIN= 2;
	 
	private View layoutLogin=null;
	private View layoutRegister=null;
	
	
	Menu myMenu=null;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
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
						
						} 	else throw new DycapoException ("Invalid Password");
												
						DBPerson.saveMe(usr);
						
					} catch (DycapoException e) {
						e.alertUser(getBaseContext());
						Message msg = new Message();
						msg.what=Home.DIALOG_LOGIN;
						msg.setTarget(handleFailure);
						msg.sendToTarget();
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
						
						DBPerson.saveMe(usr);
						
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
	
	private Handler handleFailure= new Handler(){

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
		Intent i;
		if(selected==1){
			i = new Intent();
			i.setClass(this,Settings.class);
			this.startActivity(i);
			
		}else if (selected==2){
		
			
		}
		return super.onOptionsItemSelected(item);
	}

}
