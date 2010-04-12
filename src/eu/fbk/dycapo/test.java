package eu.fbk.dycapo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;



public class test extends Activity {
	
	
    /** Called when the activity is first created. */
    @Override
  
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try{

        }catch (Exception e){
        	Log.e("cacca",e.getMessage());
        }
    }
}