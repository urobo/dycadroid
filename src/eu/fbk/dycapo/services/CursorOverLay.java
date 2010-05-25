package eu.fbk.dycapo.services;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

@SuppressWarnings("unchecked")
public class CursorOverLay extends ItemizedOverlay
{
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	public CursorOverLay(Drawable arg0) {
		super(boundCenterBottom(arg0));
	}
	@Override
	protected OverlayItem createItem(int i) {
		 return mOverlays.get(i);

	}
	@Override
	public int size() {
		return mOverlays.size();
	}
	
	public void addOverlay(OverlayItem overlay) {
    mOverlays.add(overlay);
    populate();
}
	public void removeOverLay() {
		mOverlays.clear();
		
	}

}
