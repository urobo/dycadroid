/**
 * 
 */
package eu.fbk.dycapo.activities;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import eu.fbk.dycapo.factories.bundles.ParticipationBundle;
import eu.fbk.dycapo.models.Participation;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.util.ParticipationUtils;

/**
 * @author riccardo
 * 
 */
public class RiderList extends ListActivity {
	private static Participation current = null;
	private static ProgressDialog pd = null;
	private static final int ACCEPT = 0;
	private static final int REFUSE = 1;

	private Handler sender = new Handler() {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ACCEPT:
				current.setStatus(Participation.ACCEPTED);
				ParticipationUtils.acceptRideRequest(current);
				break;
			case REFUSE:
				ParticipationUtils.refuseRideRequest(current);
				break;
			}
			pd.dismiss();
		}

	};

	private OnClickListener acceptRide = new OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			pd = ProgressDialog.show(RiderList.this, "Processing",
					"Sending your answer to DycapoServer", true, false);
			RiderList.this.sender.sendEmptyMessage(ACCEPT);
		}

	};

	private OnClickListener refuseRide = new OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			pd = ProgressDialog.show(RiderList.this, "Processing",
					"Sending your answer to DycapoServer", true, false);
			RiderList.this.sender.sendEmptyMessage(REFUSE);
		}

	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setListAdapter(new ArrayAdapter<Participation>(this, R.layout.search,
				(Participation[]) ParticipationBundle.unboxParticipations(
						(this.getIntent().getExtras())).toArray()));
		ListView lv = this.getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Participation p = (Participation) parent.getAdapter().getItem(
						position);
				current = p;
				AlertDialog alert = null;
				alert = new AlertDialog.Builder(RiderList.this)
						.setTitle(
								p.getAuthor().getUsername() + " "
										+ p.getStatus())
						.setMessage(generateMessage(p))
						.setPositiveButton("Accept", acceptRide)
						.setNegativeButton("Refuse", refuseRide).create();
				alert.show();
			}
		});
	}

	private static final String generateMessage(Participation p) {
		String message = "";
		if (p.getAuthor() instanceof Person) {
			message += p.getAuthor().getUsername() + " wants to share a Ride";
			message += " Age : " + p.getAuthor().getAge();
			message += " Gender : " + p.getAuthor().getGender();
			message += " Info : ";
			if (p.getAuthor().getDeaf())
				message += "D";
			if (p.getAuthor().getBlind())
				message += "B";
			if (p.getAuthor().getDog())
				message += "P";
			if (p.getAuthor().getSmoker())
				message += "S";
		}
		return message;
	}
}
