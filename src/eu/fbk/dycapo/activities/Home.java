/**
 * 
 */
package eu.fbk.dycapo.activities;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import eu.fbk.dycapo.persistency.DBProvider;

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
		DBProvider.configureProvider(this.getApplicationContext());
		Button driver = (Button) findViewById(R.id.DriverButton);
		driver.setOnClickListener(this);
		Button rider = (Button) findViewById(R.id.RiderButton);
		rider.setOnClickListener(this);
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
			
				  i = new Intent(Intent.ACTION_VIEW);
				  i.setData(Uri.parse("http://test.dycapo.org/"));
				  this.startActivity(i);
				
		}else if (selected==2){
			i = new Intent();
			i.setClass(this,Settings.class);
			this.startActivity(i);
		}else if (selected==3){
			
		}
		return super.onOptionsItemSelected(item);
	}

	

}
