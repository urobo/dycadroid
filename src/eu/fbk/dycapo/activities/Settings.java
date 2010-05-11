/**
 * 
 */
package eu.fbk.dycapo.activities;






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
		    
		    tabHost.addTab(tabHost.newTabSpec(eu.fbk.dycapo.activities.Settings.TAB_TAGS[0]).setContent(new Intent(Settings.this, eu.fbk.dycapo.activities.settings.Me.class)).
		    		 setIndicator(eu.fbk.dycapo.activities.Settings.TAB_TAGS[0], getResources().getDrawable(android.R.drawable.ic_menu_info_details))); 
		    tabHost.addTab(tabHost.newTabSpec(eu.fbk.dycapo.activities.Settings.TAB_TAGS[1]).setContent(new Intent(Settings.this, eu.fbk.dycapo.activities.settings.Prefs.class)).
		    		 setIndicator(eu.fbk.dycapo.activities.Settings.TAB_TAGS[1], getResources().getDrawable(android.R.drawable.ic_menu_preferences)));
		    tabHost.addTab(tabHost.newTabSpec(eu.fbk.dycapo.activities.Settings.TAB_TAGS[2]).setContent(new Intent(Settings.this, eu.fbk.dycapo.activities.settings.Car.class)).
		    		 setIndicator(eu.fbk.dycapo.activities.Settings.TAB_TAGS[2], getResources().getDrawable(android.R.drawable.ic_menu_compass)));
		    tabHost.addTab(tabHost.newTabSpec(eu.fbk.dycapo.activities.Settings.TAB_TAGS[3]).setContent(new Intent(Settings.this, eu.fbk.dycapo.activities.settings.Social.class)).
		    		 setIndicator(eu.fbk.dycapo.activities.Settings.TAB_TAGS[3], getResources().getDrawable(android.R.drawable.ic_menu_send)));

		}
	
}
	

