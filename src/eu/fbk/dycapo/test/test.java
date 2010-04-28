package eu.fbk.dycapo.test;


import eu.fbk.dycapo.test.R;
import eu.fbk.dycapo.test.R.id;
import eu.fbk.dycapo.test.R.layout;
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
        	Button testadd = (Button) findViewById (R.id.Button03);
        	testget.setOnClickListener((OnClickListener)this);
        	Button testsearch = (Button) findViewById (R.id.Button04);
        	testget.setOnClickListener((OnClickListener)this);
        	Button testrequest = (Button) findViewById (R.id.Button05);
        	testget.setOnClickListener((OnClickListener)this);
        	Button testcheck = (Button) findViewById (R.id.Button06);
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
    		}
    		
    		xmlrpc=null;
    		launchTest=null;
    	}
    
}