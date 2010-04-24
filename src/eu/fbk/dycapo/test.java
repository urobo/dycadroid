package eu.fbk.dycapo;

import java.net.MalformedURLException;
import java.util.Date;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.xmlrpc.XMLRPCClient;
import eu.fbk.dycapo.xmlrpc.XMLRPCException;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class test extends Activity implements OnClickListener{
	
	    /** Called when the activity is first created. */
    @Override
  
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try{
        	Button testupdate = (Button) findViewById(R.id.Button01);
        	testupdate.setOnClickListener((OnClickListener)this);
        	Button testget = (Button) findViewById (R.id.Button02);
        	testget.setOnClickListener((OnClickListener)this);
        	}catch (Exception e){
        		Log.e("exception", e.getMessage());
        	}
        }


    public void onClick(View v){
    		Test xmlrpc;
    		Thread launchTest;
    		switch (v.getId()){
    		case R.id.Button01:		
    			xmlrpc = new Test("dycapo.set_position");
    			launchTest = new Thread(xmlrpc);
    			launchTest.start();
    			break;
    			
    		case R.id.Button02:
    			xmlrpc = new Test("dycapo.update_position");
    			launchTest = new Thread(xmlrpc);
    			launchTest.start();
    			break;
    			}
    	}
    
}