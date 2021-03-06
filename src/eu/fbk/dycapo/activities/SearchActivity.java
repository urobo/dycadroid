/**
 * 
 */
package eu.fbk.dycapo.activities;

import java.text.SimpleDateFormat;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import eu.fbk.dycapo.factories.bundles.SearchBundle;
import eu.fbk.dycapo.models.Mode;
import eu.fbk.dycapo.models.Search;
import eu.fbk.dycapo.models.Trip;
import eu.fbk.dycapo.persistency.DBParticipation;
import eu.fbk.dycapo.persistency.DBTrip;
import eu.fbk.dycapo.util.ParticipationUtils;
import eu.fbk.dycapo.util.SearchTrip;

/**
 * @author riccardo
 * 
 */
public class SearchActivity extends ListActivity implements OnClickListener {
	private Search search;
	private int currentPosition;
	private List<Trip> trips;
	private ProgressDialog pd;
	private static View ExpandTripLayout = null;
	private Dialog d;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Bundle data = this.getIntent().getExtras();
		this.search = SearchBundle.fromBundle(data);
		SearchActivity.this.pd = ProgressDialog.show(SearchActivity.this,
				"Searching...", "Searching for a suitable Trip", true, false);
		LayoutInflater inflater = (LayoutInflater) SearchActivity.this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		ExpandTripLayout = inflater.inflate(R.layout.expand_trip,
				(ViewGroup) findViewById(R.id.expandTrip));
		this.searchTrips.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onResume();
		SearchActivity.this.pd = ProgressDialog.show(SearchActivity.this,
				"Searching...", "Searching for a suitable Trip", true, false);
		this.searchTrips.start();
	}

	private void populateView() {

		setListAdapter(new ArrayAdapter<Trip>(this, R.layout.search, this.trips));
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				SearchActivity.this.currentPosition = position;
				Trip trip = SearchActivity.this.trips.get(position);
				SimpleDateFormat formatter = new SimpleDateFormat(
						"MM-dd hh:mm:ss");

				TextView tx = (TextView) ExpandTripLayout
						.findViewById(R.id.showAuthor);
				tx.setText(trip.getAuthor().getUsername());

				tx = (TextView) ExpandTripLayout
						.findViewById(R.id.showDateOfCreation);
				tx.setText(formatter.format(trip.getPublished()));

				tx = (TextView) ExpandTripLayout
						.findViewById(R.id.showDateOfExpiration);
				tx.setText(formatter.format(trip.getExpires()));

				tx = (TextView) ExpandTripLayout
						.findViewById(R.id.showArrivalTime);
				tx.setText(formatter.format(trip.getDestination().getLeaves()));

				Mode car = trip.getMode();
				tx = (TextView) ExpandTripLayout.findViewById(R.id.showMaker);
				tx.setText(car.getMake());

				tx = (TextView) ExpandTripLayout.findViewById(R.id.showColor);
				tx.setText(car.getColor());

				tx = (TextView) ExpandTripLayout.findViewById(R.id.showKind);
				tx.setText(car.getKind());

				tx = (TextView) ExpandTripLayout.findViewById(R.id.showLic);
				tx.setText(car.getLic());

				tx = (TextView) ExpandTripLayout.findViewById(R.id.showModel);
				tx.setText(car.getModel());

				d = new AlertDialog.Builder(SearchActivity.this)
						.setView(ExpandTripLayout).setCancelable(true)
						.setPositiveButton("Participate!", SearchActivity.this)
						.setTitle("Trip").create();
				d.show();
			}
		});
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		d.dismiss();
		d = null;
		DBParticipation.newTrip();
		ParticipationUtils.postParticipation(this.trips
				.get(this.currentPosition));
		DBTrip.saveActiveTripFromTrip(this.trips.get(this.currentPosition));
		Intent i = new Intent();
		i.setClass(SearchActivity.this, Navigation.class);
		Bundle data = new Bundle();
		data.putString("role", "rider");
		i.putExtras(data);
		this.startActivity(i);

	}

	Thread searchTrips = new Thread() {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {

			SearchActivity.this.search = SearchTrip.searchTrips(search);

			searchResults.sendEmptyMessage(0);
		}

	};

	private Handler searchResults = new Handler() {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				if ((SearchActivity.this.search.getTrips() instanceof List<?>)) {
					SearchActivity.this.trips = SearchActivity.this.search
							.getTrips();
					SearchActivity.this.populateView();
				}
				pd.dismiss();

				break;
			}
		}

	};
}
