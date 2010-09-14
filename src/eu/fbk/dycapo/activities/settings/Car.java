/**
 * 
 */
package eu.fbk.dycapo.activities.settings;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import eu.fbk.dycapo.activities.R;
import eu.fbk.dycapo.models.Mode;
import eu.fbk.dycapo.persistency.DBMode;

/**
 * @author riccardo
 *
 */
public class Car extends Activity implements OnClickListener{
	private static final String TAG = "Settings.Car";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.car);
        Button save= (Button) this.findViewById(R.id.saveCarButton);
        save.setOnClickListener((OnClickListener)this);
        
        update();
    }
	public void update()
	{
		
		Mode car=DBMode.getMode();
		if (car instanceof Mode){
			if(car.getCapacity() instanceof Integer)((EditText)this.findViewById(R.id.getCapacity)).setText(car.getCapacity().toString());
			if(car.getColor() instanceof String)((EditText)this.findViewById(R.id.getColor)).setText(car.getColor());
			if(car.getModel()instanceof String) ((EditText)this.findViewById(R.id.getModel)).setText(car.getModel());
			if(car.getLic() instanceof String) ((EditText)this.findViewById(R.id.getLic)).setText(car.getLic());
			if(car.getMake() instanceof String) ((EditText)this.findViewById(R.id.getMaker)).setText(car.getMake());
		}
		car=null;

		}
	
	@Override
	public void onClick(View v) {
		Mode car= new Mode();
		String input=null;
		
		input= ((EditText)this.findViewById(R.id.getColor)).getText().toString();
		if(input instanceof String) car.setColor(input);
		
		input=null;
		input= ((EditText)this.findViewById(R.id.getLic)).getText().toString();
		if(input instanceof String) car.setLic(input);
		
		input=null;
		input= ((EditText)this.findViewById(R.id.getMaker)).getText().toString();
		if (input instanceof String) car.setMake(input);
		
		input=null;
		input= ((EditText)this.findViewById(R.id.getModel)).getText().toString();
		if (input instanceof String) car.setModel(input);
		
		int capacity = 0;
		input = null;
		input = ((EditText)this.findViewById(R.id.getCapacity)).getText().toString();
		try{
			if (input instanceof String)capacity= Integer.parseInt(input);
			car.setCapacity(capacity);
		
		DBMode.saveMode(car);
		}catch(NumberFormatException e){
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
	}
	
}
