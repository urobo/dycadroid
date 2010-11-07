/**
 * 
 */
package eu.fbk.dycapo.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import eu.fbk.dycapo.test.ActivityTester;

/**
 * @author riccardo
 * 
 */
public class TestingActivity extends Activity implements OnClickListener {
	private static final int[] IDS = { R.id.testHome, R.id.testNavigation,
			R.id.testSettings, R.id.testTripSettings, R.id.testFastChoice };
	private List<Button> buttons;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.testing);

		buttons = new ArrayList<Button>();
		for (int i = 0; i < ActivityTester.ACTIVITIES; i++) {
			buttons.add((Button) this.findViewById(IDS[i]));
			buttons.get(i).setOnClickListener(this);
		}

	}

	@Override
	public void onClick(View v) {

		this.startActivity(ActivityTester.testActivity(this, v.getId()));

	}

}
