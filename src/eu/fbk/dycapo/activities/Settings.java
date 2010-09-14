/**
 * 
 */
package eu.fbk.dycapo.activities;






import eu.fbk.dycapo.activities.settings.Car;
import eu.fbk.dycapo.activities.settings.Me;
import eu.fbk.dycapo.activities.settings.Prefs;
import eu.fbk.dycapo.activities.settings.Social;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;



/**
 * @author riccardo
 *
 */
public class Settings extends TabActivity implements OnClickListener{
	public static final String[] TAB_TAGS={	"Me",
											"Prefs",
											"Car",
											"Social"
											};
	public static final int ME = 0;
	public static final int PREFS = 1;
	public static final int CAR = 2;
	public static final int SOCIAL = 3; 
	public static final String TAB_FOCUS = "tab_focus";
	
	Menu myMenu= null;
	TabHost tabHost= null;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		    super.onCreate(savedInstanceState);
		    
		    tabHost = getTabHost();  
		    
		    tabHost.addTab(tabHost.newTabSpec(eu.fbk.dycapo.activities.Settings.TAB_TAGS[0]).setContent(new Intent(this, Me.class)).
		    		 setIndicator(eu.fbk.dycapo.activities.Settings.TAB_TAGS[0], getResources().getDrawable(android.R.drawable.ic_menu_info_details))); 
		    tabHost.addTab(tabHost.newTabSpec(eu.fbk.dycapo.activities.Settings.TAB_TAGS[1]).setContent(new Intent(this, Prefs.class)).
		    		 setIndicator(eu.fbk.dycapo.activities.Settings.TAB_TAGS[1], getResources().getDrawable(android.R.drawable.ic_menu_preferences)));
		    tabHost.addTab(tabHost.newTabSpec(eu.fbk.dycapo.activities.Settings.TAB_TAGS[2]).setContent(new Intent(this, Car.class)).
		    		 setIndicator(eu.fbk.dycapo.activities.Settings.TAB_TAGS[2], getResources().getDrawable(android.R.drawable.ic_menu_compass)));
		    tabHost.addTab(tabHost.newTabSpec(eu.fbk.dycapo.activities.Settings.TAB_TAGS[3]).setContent(new Intent(this, Social.class)).
		    		 setIndicator(eu.fbk.dycapo.activities.Settings.TAB_TAGS[3], getResources().getDrawable(android.R.drawable.ic_menu_send)));
		    
		    
		    if (this.getIntent().hasExtra(TAB_FOCUS))
		    	tabHost.setCurrentTab(this.getIntent().getIntExtra(TAB_FOCUS, ME));
		}
	
}
	

