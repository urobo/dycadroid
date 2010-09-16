/**
 * 
 */
package eu.fbk.dycapo.activities;

import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import eu.fbk.dycapo.bundles.SearchBundle;
import eu.fbk.dycapo.models.Search;
import eu.fbk.dycapo.models.Trip;
import eu.fbk.dycapo.util.SearchTrip;

/**
 * @author riccardo
 *
 */
public class SearchActivity extends ListActivity {
	private Search search;
	private List<Trip> trips;
	private String[] tripsMeta;
	private ProgressDialog pd;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		Bundle data = this.getIntent().getExtras();
		final Search search = SearchBundle.fromBundle(data);
		SearchActivity.this.pd = ProgressDialog.show(SearchActivity.this, "Searching...", "Searching for a suitable Trip", true, false);
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
				SearchActivity.this.trips = SearchActivity.this.search.getTrips();
				int size = SearchActivity.this.trips.size();
				SearchActivity.this.tripsMeta = new String[size];
				for (int i = 0; i < size; i++){
					SearchActivity.this.tripsMeta[i] = SearchActivity.this.trips.get(i).getHref();
				}
				pd.dismiss();
				SearchActivity.this.populateView();
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
		      // When clicked, show a toast with the TextView text
		      Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
		          Toast.LENGTH_SHORT).show();
		    }
		  });
	}

}
