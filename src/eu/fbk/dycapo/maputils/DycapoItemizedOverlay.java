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
public class DycapoItemizedOverlay extends ItemizedOverlay {
	private ArrayList<DycapoOverlayItem> mOverlays = new ArrayList<DycapoOverlayItem>();
	private Context mContext;
	/**
	 * @param defaultMarker
	 */
	public DycapoItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
	
	public DycapoItemizedOverlay(Drawable defaultMarker, Context context) {
		  super(boundCenterBottom(defaultMarker));
		  mContext = context;
		}

	/* (non-Javadoc)
	 * @see com.google.android.maps.ItemizedOverlay#createItem(int)
	 */
	@Override
	protected OverlayItem createItem(int i) {
		  return mOverlays.get(i);
	}

	/* (non-Javadoc)
	 * @see com.google.android.maps.ItemizedOverlay#size()
	 */
	@Override
	public int size() {
		return this.mOverlays.size();
	}
	
	public void addOverlay(DycapoOverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}

	/* (non-Javadoc)
	 * @see com.google.android.maps.ItemizedOverlay#onTap(int)
	 */
	@Override
	protected boolean onTap(int index) {
		  OverlayItem item = mOverlays.get(index);
		  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		  dialog.setTitle(item.getTitle());
		  dialog.setMessage(item.getSnippet());
		  dialog.show();
		  return true;
	}
	
}
