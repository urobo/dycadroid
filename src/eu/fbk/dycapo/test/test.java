package eu.fbk.dycapo.test;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import eu.fbk.dycapo.activities.R;



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
        	Button testadd = (Button) findViewById (R.id.Button03);
        	testadd.setOnClickListener((OnClickListener)this);
        	Button testsearch = (Button) findViewById (R.id.Button04);
        	testsearch.setOnClickListener((OnClickListener)this);
        	Button teststart = (Button) findViewById (R.id.Button05);
        	teststart.setOnClickListener((OnClickListener)this);
        	Button testrequest = (Button) findViewById (R.id.Button06);
        	testrequest.setOnClickListener((OnClickListener)this);
        	Button testcheck = (Button) findViewById (R.id.Button07);
        	testcheck.setOnClickListener((OnClickListener)this);
        	Button testaccept = (Button) findViewById (R.id.Button08);
        	testaccept.setOnClickListener((OnClickListener)this);

        	}catch (Exception e){
        		Log.e("exception", e.getMessage());
        	}
        }


    public void onClick(View v){
    		Test xmlrpc;
    		Thread launchTest;
    		switch (v.getId()){
    		case R.id.Button01:		
    			xmlrpc = new Test(Test.METHODS[0]);
    			launchTest = new Thread(xmlrpc);
    			launchTest.start();
    			break;
    			
    		case R.id.Button02:
    			xmlrpc = new Test(Test.METHODS[1]);
    			launchTest = new Thread(xmlrpc);
    			launchTest.start();
    			break;
    		case R.id.Button03:
    			xmlrpc = new Test(Test.METHODS[2]);
    			launchTest = new Thread(xmlrpc);
    			launchTest.start();
    			break;
    		case R.id.Button04:
    			xmlrpc = new Test(Test.METHODS[3]);
    			launchTest = new Thread(xmlrpc);
    			launchTest.start();
    			break;
    		case R.id.Button05:
    			xmlrpc = new Test(Test.METHODS[4]);
    			launchTest = new Thread(xmlrpc);
    			launchTest.start();
    			break;
    		case R.id.Button06:
    			xmlrpc = new Test(Test.METHODS[5]);
    			launchTest = new Thread(xmlrpc);
    			launchTest.start();
    			break;
    		case R.id.Button07:
    			xmlrpc = new Test(Test.METHODS[6]);
    			launchTest = new Thread(xmlrpc);
    			launchTest.start();
    			break;
    		case R.id.Button08:
    			xmlrpc = new Test(Test.METHODS[7]);
    			launchTest = new Thread(xmlrpc);
    			launchTest.start();
    			break;
    		}
    		
    		xmlrpc=null;
    		launchTest=null;
    	}
    
}