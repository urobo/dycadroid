/**
 * 
 */
package eu.fbk.dycapo.activities;


import java.net.URI;

import eu.fbk.dycapo.models.Trip.Content;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author riccardo
 */
public class Home extends Activity implements OnClickListener{
	Menu myMenu=null;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.home);
		Button driver = (Button) findViewById(R.id.DriverButton);
		driver.setOnClickListener((OnClickListener)this);
		Button rider = (Button) findViewById(R.id.RiderButton);
		rider.setOnClickListener((OnClickListener)this);
	}
	
	
	private void addRegularMenuItems(Menu menu){
		int base=Menu.FIRST; // value is 1
		menu.add(base,base,base,"Register");
		menu.add(base,base+1,base+1,"Settings");
		menu.add(base,base+2,base+2,"Stats");
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
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.DriverButton:
			break;
		case R.id.RiderButton:
			break;

		}
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int selected= item.getItemId();
		Intent i;
		if(selected==1){
			
		}else if (selected==2){
			i = new Intent();
			i.setClass(this.getApplicationContext(),eu.fbk.dycapo.activities.Settings.class);
			this.startActivity(i);
		}else if (selected==3){
			
		}
		return super.onOptionsItemSelected(item);
	}

	

}
