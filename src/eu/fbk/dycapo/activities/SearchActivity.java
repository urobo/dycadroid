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
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import eu.fbk.dycapo.bundles.SearchBundle;
import eu.fbk.dycapo.models.Search;
import eu.fbk.dycapo.models.Trip;
import eu.fbk.dycapo.util.SearchTrip;

/**
 * @author riccardo
 *
 */
public class SearchActivity extends ListActivity implements OnClickListener {
	private Search search;
	private List<Trip> trips;
	private String[] tripsMeta;
	private ProgressDialog pd;
	private static View ExpandTripLayout = null;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		Bundle data = this.getIntent().getExtras();
		final Search search = SearchBundle.fromBundle(data);
		SearchActivity.this.pd = ProgressDialog.show(SearchActivity.this, "Searching...", "Searching for a suitable Trip", true, false);
		LayoutInflater inflater = (LayoutInflater) SearchActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
		ExpandTripLayout = inflater.inflate(R.layout.expand_trip, (ViewGroup)findViewById(R.id.expandTrip));
		
	      
		new Thread(){

			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				
				SearchActivity.this.search = SearchTrip.searchTrips(search);
				
				searchResults.sendEmptyMessage(0);
			}
			
		}.start();
		
	}
	
	private Handler searchResults = new Handler(){

		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case 0:
				if ((SearchActivity.this.search.getTrips() instanceof List<?>)){
					SearchActivity.this.trips = SearchActivity.this.search.getTrips();
					int size = SearchActivity.this.trips.size();
					SearchActivity.this.tripsMeta = new String[size];
					for (int i = 0; i < size; i++){
						SearchActivity.this.tripsMeta[i] = SearchActivity.this.trips.get(i).getHref();
					}
					SearchActivity.this.populateView();
				}
					pd.dismiss();
					
				break;
			}
		}
		
	};
	
	

	private void populateView(){
		
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.search, tripsMeta));
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view,
		      int position, long id) {
		      Dialog d;
		      Trip trip = SearchActivity.this.trips.get(position);
		      SimpleDateFormat formatter =new SimpleDateFormat();
		      
		      TextView tx = (TextView)ExpandTripLayout.findViewById(R.id.showAuthor);
		      tx.setText(trip.getAuthor().getUsername());
		      
		      tx = (TextView)ExpandTripLayout.findViewById(R.id.showDateOfCreation);
		      tx.setText(formatter.format(trip.getPublished()));
		      
		      tx = (TextView)ExpandTripLayout.findViewById(R.id.showDateOfExpiration);
		      tx.setText(formatter.format(trip.getExpires()));
		      
		      tx = (TextView)ExpandTripLayout.findViewById(R.id.showArrivalTime);
		      tx.setText(formatter.format(trip.getDestination().getLeaves()));
		      
		      d = new AlertDialog.Builder(SearchActivity.this).setView(ExpandTripLayout).setCancelable(true)
		      .setPositiveButton("Participate!", SearchActivity.this).setTitle("Trip").create();
		      d.show();
		      }
		  });
	}



	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		i.setClass(SearchActivity.this, Navigation.class);
		Bundle data = new Bundle();
		data.putString("role", "rider");
		i.putExtras(data);
		this.startActivity(i);
	}
//	Dialog d;
//	
//	Calendar c = Calendar.getInstance();
//	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//	User usr = DBPerson.getUser();
//	
//	TextView tx = (TextView) ExpandTripLayout.findViewById(R.id.showArrivalTime);
//	tx.setText(formatter.format(c.getTime()));
//	
//	tx = (TextView) ExpandTripLayout.findViewById(R.id.showAuthor);
//	tx.setText(usr.getUsername());
//	
//	tx = (TextView) ExpandTripLayout.findViewById(R.id.showDateOfCreation);
//	tx.setText(formatter.format(c.getTime()));
//	
//	tx = (TextView) ExpandTripLayout.findViewById(R.id.showDateOfExpiration);
//	tx.setText(formatter.format(c.getTime()));
//	
//	d = new AlertDialog.Builder(SearchActivity.this).setView(ExpandTripLayout).setCancelable(true)
//      .setPositiveButton("Participate!", SearchActivity.this).setTitle("Trip").create();
//    d.show();
}
