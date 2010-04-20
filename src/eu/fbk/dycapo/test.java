package eu.fbk.dycapo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.models.Response;

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
        	Button testbutton= (Button) findViewById(R.id.Button01);
        	testbutton.setOnClickListener((OnClickListener)this);
        	}catch (Exception e){
        		Log.e("cacca1", e.getMessage());
        	}
        }

    public void xmlrpc_call() throws MalformedURLException, XMLRPCException{
		// Create a trust manager that does not validate certificate chains
		XMLRPCClient client = new XMLRPCClient("http://test.dycapo.org/RPC2");
    	
    	Person user = new Person("rider1", "rico.sleeps@gmail.com","password",
    			"","", 21 , "male",
    			true,true,true,true);
    	HashMap<String,Object> test = new HashMap<String,Object>();
    	test.put("username","rider1");
    	
    	client.setBasicAuthentication(user.getUsername(),user.getPassword());
    	Response p = (Response) client.call("get_position",test);
    	Log.i("Response",p.getMessage());
       	
   }

    
    public void onClick(View v){

    		switch (v.getId()){
    		case R.id.Button01:		
    			int attempt=0;
    			while(attempt<3){
    				try {
    					this.xmlrpc_call();
    				} catch (MalformedURLException e) {
    					Log.e("MalformedURLException", e.getMessage());
						attempt++;
    				} catch (XMLRPCException e) {
    					Log.e("XMLRPCException", e.getMessage());
    					attempt++;	
    				} catch (Exception e){
    					Log.e("Exception", e.getMessage());
    					attempt++;	
    				}
    			}
    			break;
    	}
    }
}