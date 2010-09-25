/**
 * 
 */
package eu.fbk.dycapo.maputils;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/**
 * @author riccardo
 *
 */
@SuppressWarnings("unchecked")
public class DycapoItemizedOverlay extends ItemizedOverlay{
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;

	public DycapoItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
	public DycapoItemizedOverlay(Drawable defaultMarker, Context context) {
		  super(defaultMarker);
		  mContext = context;
		}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.mOverlays.size();
	}

	/* (non-Javadoc)
	 * @see com.google.android.maps.ItemizedOverlay#createItem(int)
	 */
	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		  return mOverlays.get(i);
	}

	
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}
	/* (non-Javadoc)
	 * @see com.google.android.maps.ItemizedOverlay#onTap(com.google.android.maps.GeoPoint, com.google.android.maps.MapView)
	 */
	@Override
	public boolean onTap(int index) {
		// TODO Auto-generated method stub
		  OverlayItem item = mOverlays.get(index);
		  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		  dialog.setTitle(item.getTitle());
		  dialog.setMessage(item.getSnippet());
		  dialog.show();
		  return true;
	}
	
	
}
