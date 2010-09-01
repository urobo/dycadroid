/**
 * 
 */
package eu.fbk.dycapo.activities;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Trip;
import eu.fbk.dycapo.persistency.ActiveTrip;
import eu.fbk.dycapo.persistency.DBMode;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.persistency.DBPrefs;
import eu.fbk.dycapo.persistency.DBTrip;
import eu.fbk.dycapo.services.DycapoServiceClient;


/**
 * @author riccardo
 *
 */
public class TripSettings extends Activity implements OnClickListener {
	private static final String TAG = "TripSettings";
	private String role = null;
	private String location =null;
	private List<Address> foundAddresses;
	private Menu myMenu=null;
	private static EditText capacity = null;

	private int id;
	private Address Origin=null;
	private Address Destination=null;
	private Dialog pd;
	
    private TextView mDateDisplay;
    private Button mPickDate;
    private int mYear;
    private int mMonth;
    private int mDay;

    static final int DATE_DIALOG_ID = 0;
    
    private TextView mTimeDisplay;
    private Button mPickTime;

    private int mHour;
    private int mMinute;

    static final int TIME_DIALOG_ID = 1;

	private Thread thr; 
	
	private TimePickerDialog.OnTimeSetListener mTimeSetListener =
	    new TimePickerDialog.OnTimeSetListener() {

			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				
				 mHour = hourOfDay;
		         mMinute = minute;
		         updateDisplay();
			}
	    };
	private DatePickerDialog.OnDateSetListener mDateSetListener =
        new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				 mYear = year;
	                mMonth = monthOfYear;
	                mDay = dayOfMonth;
	                updateDisplay();
				
			}
        };
	
	private Handler showLocations = new Handler(){

		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			//String selected = showOptions();
			pd.dismiss();
			Log.d("thr","message received");
			showOptions(msg.what);
			
		}
	};
	
	private void showOptions(final int id){
		if (foundAddresses==null || foundAddresses.size() == 0) {
			Dialog locationError = new AlertDialog.Builder(TripSettings.this).setIcon(0).setTitle(
			"Error").setPositiveButton("Ok", null)
			.setMessage("Sorry, could not find given Location.")
			.create();
			locationError.show();
			EditText set = (EditText)findViewById(id);
			set.setText("");
		} else { 
			String locations[] = new String[foundAddresses.size()];
			for (int i = 0; i < foundAddresses.size(); ++i){
				locations[i] = foundAddresses.get(i).getAddressLine(0).toString() + "\n" +
				foundAddresses.get(i).getAddressLine(1).toString();
			}

			new AlertDialog.Builder(this).setTitle("Available Locations")
			.setItems(locations,
					new DialogInterface.OnClickListener() {
				public void onClick(
						DialogInterface dialoginterface, int i) {
					switch(id){
					case R.id.getOrigin:
						setOrigin(foundAddresses.get(i));
						Log.d("Origin", foundAddresses.get(i).getAddressLine(0) + ", " +foundAddresses.get(i).getAddressLine(1));
						break;
					case R.id.getDestination:
						setDestination(foundAddresses.get(i));
						Log.d("Destination", foundAddresses.get(i).getAddressLine(0) + ", " +foundAddresses.get(i).getAddressLine(1));
						break;
					}
					EditText set = (EditText)findViewById(id);
					set.setText(foundAddresses.get(i).getAddressLine(0) + ", " +foundAddresses.get(i).getAddressLine(1));
				}
			}).show();
		}	
		
	}
	
	private OnClickListener checkLocation= new OnClickListener(){

		@Override
		public void onClick(View v) {
			pd= ProgressDialog.show(TripSettings.this, "Processing...", "Searching for the given location", true, false);
			
			switch (v.getId()){
			case R.id.findOrigin:
				id = R.id.getOrigin;
				location = ((EditText)findViewById(R.id.getOrigin)).getText().toString();
				break;
			case R.id.findDestination:
				id = R.id.getDestination;
				location = ((EditText)findViewById(R.id.getDestination)).getText().toString();
				break;
			}
			
			thr= new Thread(){
				public void run(){
						Geocoder gc = new Geocoder(TripSettings.this, Locale.ENGLISH);
						try {
							foundAddresses= gc.getFromLocationName(location, 3);					
							Log.d("Looking for :", location);
							if (!foundAddresses.isEmpty()){
								for (int i=0; i<foundAddresses.size();i++){
									Log.d("Address"+ String.valueOf(i) , foundAddresses.get(i).getAddressLine(0)+foundAddresses.get(i).getAddressLine(1));
								}
							}else Log.d("Address", "foundAddresses is empty");
							
							showLocations.sendEmptyMessage(id);
						} catch (IOException e) {
							try {
								throw new DycapoException ("GeoCoder.getLocation : "+e.getMessage());
							} catch (DycapoException e1) {
								Log.e("WTF", e.getMessage());
							}
						}
						
				}
			};
			thr.start();
		}
		
	};
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setRole(this.getIntent().getExtras().getString("role"));
		this.setContentView(R.layout.trip_settings);
		
		
		
		EditText getOrigin = ((EditText)this.findViewById(R.id.getOrigin));
		getOrigin.setHint("Insert Origin Here");
		Button findOrigin = ((Button)this.findViewById(R.id.findOrigin));
		findOrigin.setOnClickListener(checkLocation);
		EditText getDestination = ((EditText)this.findViewById(R.id.getDestination));
		getDestination.setHint("Insert Destination Here");
		
		Button findDestination = ((Button)this.findViewById(R.id.findDestination));
		findDestination.setOnClickListener(checkLocation);
		// capture our View elements
        mDateDisplay = (TextView) findViewById(R.id.dateDisplay);
        mPickDate = (Button) findViewById(R.id.pickDate);

        // add a click listener to the button
        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        
        mTimeDisplay = (TextView) findViewById(R.id.timeDisplay);
        mPickTime = (Button) findViewById(R.id.pickTime);

        // add a click listener to the button
        mPickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });

        // get the current time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // display the current date (this method is below)
        updateDisplay();
        
        if (this.role.equals("driver")){
        	//TODO:inflate layout for Capacity
        }
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
	}


	
	
	
	
	//MENU
	private void addRegularMenuItems(Menu menu){
		int base=Menu.FIRST; // value is 1
		String role=this.getIntent().getExtras().getString("role");
		if (role.equals("rider"))role="Search";
		else role="Save";
		menu.add(base,base,base,role);
		menu.add(base,base+1,base+1,"Share");
	}
	


	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		this.setMyMenu(menu);
		addRegularMenuItems(menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		int selected= item.getItemId();
		String role=this.getIntent().getExtras().getString("role");
		//Intent i;
		if(selected==1){
			if (role.equals("driver")){
				pd =ProgressDialog.show(TripSettings.this, "Processing...", "Retrieving the Route", true, false);
				getTrip();
				
				Thread thr = new Thread(){

					/* (non-Javadoc)
					 * @see java.lang.Thread#run()
					 */
					@Override
					public void run() {
						
						eu.fbk.dycapo.maputils.Directions.getRoute(Origin, Destination, TripSettings.this);
						handleRoute.sendEmptyMessage(0);
					} 
					
				};
				thr.start();
				
				
			
			}else {
				
			}
			
		}else if (selected==2){
		
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	private Handler handleRoute= new Handler(){

		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			pd.dismiss();
			
			try {
				ActiveTrip aTrip = DBTrip.getActiveTrip();
				Calendar c = Calendar.getInstance();
				if (aTrip instanceof ActiveTrip){
					c.setTime(aTrip.getOrigin().getLeaves());
				
					c.setTimeInMillis(c.getTimeInMillis() + (aTrip.getRoute().getmDurationSecs()*1000));
					aTrip.getDestination().setLeaves(new Date(c.getTimeInMillis()));
					aTrip.getMode().setVacancy(aTrip.getMode().getCapacity());
					aTrip.getMode().setKind("car");
					aTrip.getPreferences().setAge("18-30");
					try {
						Log.d(TAG,"user: "+ DBPerson.getUser().getUsername() + " , passwd:"+ DBPerson.getUser().getPassword());
//						Object value = client.call(Dycapo.getMethod(Dycapo.ADD_TRIP), aTrip.toHashMap());
//						Response response = (Response) DycapoObjectsFactory.getDycapoObject(DycapoObjectsFactory.XMLRPC, value, true);
						Log.d(TAG, aTrip.toJSONObject().toString());
						
						new AlertDialog.Builder(TripSettings.this).setTitle("User List").setPositiveButton("Ok", null)
						.setCancelable(true).setMessage(aTrip.toJSONObject().toString()).show();
						
						Object response = DycapoServiceClient.callDycapo(DycapoServiceClient.POST, "trips", aTrip.toJSONObject(),DBPerson.getUser().getUsername(),DBPerson.getUser().getPassword());
						if (response instanceof Trip){
							Trip value = (Trip) response;
							Log.d(TAG, "Trip type");
							aTrip.setId(value.getId());
							Trip trip = aTrip;
							Log.d(TAG, "Saving Dycapo Trip as Active");
							DBTrip.saveActiveTrip(aTrip);
							Log.d(TAG, "Saving Dycapo Trip as Normal");
							DBTrip.saveTrip(trip);
						
							Intent intent = new Intent();
							intent.setClass(TripSettings.this, Navigation.class);
							intent.putExtras(TripSettings.this.getIntent().getExtras());
							TripSettings.this.startActivity(intent);
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new DycapoException(e.getMessage());
					}
				}
		}catch (DycapoException e){
				pd.dismiss();
				Log.e(TAG, e.getMessage());
				e.alertUser(TripSettings.this);
		}
	
	}};
	 // updates the date in the TextView
    private void updateDisplay() {
        mDateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("-")
                    .append(mDay).append("-")
                    .append(mYear).append(" "));
        mTimeDisplay.setText(
                new StringBuilder()
                        .append(pad(mHour)).append(":")
                        .append(pad(mMinute)));
    }
    
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DATE_DIALOG_ID:
            return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
       case TIME_DIALOG_ID:
            return new TimePickerDialog(this,
                    mTimeSetListener, mHour, mMinute, false);
        }
        return null;
    }
    
    
    public void getTrip(){
    
    	Trip trip = new Trip();
    	Location loc = new Location();
    	String geoRssPoint = "";
    	Date leaves = new Date();
    	if (this.Origin instanceof Address){
    		geoRssPoint = String.valueOf(Origin.getLatitude()) + ", " + String.valueOf(Origin.getLongitude());
    		if (!geoRssPoint.equals(", ")) loc.setGeorss_point(geoRssPoint);
    	}
    	leaves.setYear(mYear);
    	leaves.setMonth(mMonth);
    	leaves.setDate(mDay);
    	leaves.setHours(mHour);
    	leaves.setMinutes(mMinute);
    	leaves.setSeconds(0);
    	
    	loc.setLeaves(leaves);
    	loc.setPoint(Location.ORIG);
    	trip.setOrigin(loc);
    	
    	loc = new Location();
    	
    	if (this.Destination instanceof Address){
    		geoRssPoint = String.valueOf(Destination.getLatitude()) + ", " + String.valueOf(Destination.getLongitude());
    		if (!geoRssPoint.equals(", ")) loc.setGeorss_point(geoRssPoint);
    	}
    	
    	loc.setLeaves(leaves);
    	loc.setPoint(Location.DEST);
    	trip.setDestination(loc);
    	
    	trip.setExpires(leaves);
    	
    	trip.setAuthor(DBPerson.getUser());
    	trip.setPreferences(DBPrefs.getPrefs());
    	trip.setMode(DBMode.getMode());
    	Log.d(TAG, "saving");
    	
    	
    	
    	DBTrip.saveActiveTripFromTrip(trip);
   
    	Log.d(TAG,"Trip Saved");

    }

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param myMenu the myMenu to set
	 */
	public void setMyMenu(Menu myMenu) {
		this.myMenu = myMenu;
	}

	/**
	 * @return the myMenu
	 */
	public Menu getMyMenu() {
		return myMenu;
	}

	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(Address origin) {
		Origin = origin;
	}

	/**
	 * @return the origin
	 */
	public Address getOrigin() {
		return Origin;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(Address destination) {
		Destination = destination;
	}

	/**
	 * @return the destination
	 */
	public Address getDestination() {
		return Destination;
	}
}
