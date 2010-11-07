/**
 * 
 */
package eu.fbk.dycapo.activities.settings;

import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import eu.fbk.dycapo.activities.R;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.factories.json.DycapoObjectsFetcher;
import eu.fbk.dycapo.factories.json.UserMapper;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.models.Preferences;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.persistency.User;
import eu.fbk.dycapo.services.DycapoServiceClient;

/**
 * @author riccardo
 * 
 */
public class Me extends Activity implements OnClickListener {

	protected static final String TAG = "Settings.Me";

	private Dialog pd = null;

	private String gender = null;
	private OnClickListener gender_listener = new OnClickListener() {
		public void onClick(View v) {
			// Perform action on clicks
			RadioButton rb = (RadioButton) v;
			switch (rb.getId()) {
			case R.id.maleGender:
				gender = Person.MALE;
				break;
			case R.id.femaleGender:
				gender = Person.FEMALE;
				break;
			}

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.me);

		Button save = (Button) this.findViewById(R.id.saveMeButton);
		save.setOnClickListener((OnClickListener) this);

		RadioButton male = (RadioButton) this.findViewById(R.id.maleGender);
		RadioButton female = (RadioButton) this.findViewById(R.id.femaleGender);

		female.setOnClickListener(gender_listener);
		male.setOnClickListener(gender_listener);

		this.update();
	}

	public void update() {

		User user = DBPerson.getUser();
		if (user instanceof User) {

			String readDB = user.getFirst_name();
			if (readDB instanceof String)
				((EditText) this.findViewById(R.id.getFirst_Name))
						.setText(readDB);

			readDB = user.getLast_name();
			if (readDB instanceof String)
				((EditText) this.findViewById(R.id.getLast_Name))
						.setText(readDB);

			readDB = user.getEmail();
			if (readDB instanceof String)
				((EditText) this.findViewById(R.id.getEmail)).setText(readDB);

			Integer ageDB = user.getAge();
			if (ageDB instanceof Integer)
				((EditText) this.findViewById(R.id.getAge)).setText(ageDB
						.toString());
			ageDB = null;

			readDB = user.getGender();
			if (readDB instanceof String) {
				if (readDB.equals(Preferences.GENDER_PREFS[Preferences.MALE])) {
					((RadioButton) this.findViewById(R.id.maleGender))
							.setChecked(true);
				} else
					((RadioButton) this.findViewById(R.id.femaleGender))
							.setChecked(true);
			}
		}
	}

	private static final int ERROR = -1;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.saveMeButton:
			pd = ProgressDialog.show(Me.this, "Processing...",
					"Updating Personal Info", true, false);
			new Thread() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see java.lang.Thread#run()
				 */
				@Override
				public void run() {
					try {
						User readForm = new User();
						String input;

						input = ((EditText) Me.this.findViewById(R.id.getAge))
								.getText().toString();
						if (input instanceof String && !(input.equals("")))
							readForm.setAge(Integer.parseInt(input));

						input = ((EditText) Me.this.findViewById(R.id.getEmail))
								.getText().toString();
						if (input instanceof String && !input.equals(""))
							readForm.setEmail(input);

						input = ((EditText) Me.this
								.findViewById(R.id.getFirst_Name)).getText()
								.toString();
						if (input instanceof String && !input.equals(""))
							readForm.setFirst_name(input);

						readForm.setGender(Me.this.gender);

						input = ((EditText) Me.this
								.findViewById(R.id.getLast_Name)).getText()
								.toString();
						if (input instanceof String && !input.equals(""))
							readForm.setLast_name(input);

						DBPerson.saveMe(readForm);
						User usr = DBPerson.getUser();
						String usrName = usr.getUsername();

						String usrPwd = usr.getPassword();
						String uri = "persons/" + usrName;
						Log.d(TAG, "username : " + usrName);
						Log.d(TAG, "password : " + usrPwd);
						Log.d(TAG, uri);
						JSONObject jobj = UserMapper.fromUserToJSONObject(usr);
						Log.d(TAG, jobj.toString());
						JSONObject jsonObj = DycapoServiceClient.callDycapo(
								DycapoServiceClient.PUT,
								DycapoServiceClient.uriBuilder(uri), jobj,
								usrName, usrPwd);
						readForm.setHref(DycapoObjectsFetcher.buildPerson(
								jsonObj).getHref());
						DBPerson.saveMe(readForm);

						input = null;
						readForm = null;

						Me.this.updateInfo.sendEmptyMessage(OK);
					} catch (DycapoException e) {
						Log.e(TAG, e.getMessage());
						e.printStackTrace();
						Message msg = Message.obtain();
						msg.what = ERROR;
						Bundle data = new Bundle();
						data.putString("errorMsg", e.getMessage());
						msg.setData(data);
						Me.this.updateInfo.sendMessage(msg);
					} catch (JSONException e) {
						Log.e(TAG, e.getMessage());
						e.printStackTrace();
						Message msg = Message.obtain();
						msg.what = ERROR;
						Bundle data = new Bundle();
						data.putString("errorMsg", e.getMessage());
						msg.setData(data);
						Me.this.updateInfo.sendMessage(msg);
					}

				}

			}.start();

			break;
		}
	}

	private static final int OK = 0;
	private Handler updateInfo = new Handler() {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case OK:
				pd.dismiss();
				break;
			case ERROR:
				pd.dismiss();
				Toast.makeText(Me.this, msg.getData().getString("errorMsg"),
						Toast.LENGTH_LONG);
			}
		}
	};

}