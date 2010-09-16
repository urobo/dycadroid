/**
 * 
 */
package eu.fbk.dycapo.maputils;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

/**
 * @author riccardo
 *
 */
public class DycapoOverlay extends Overlay {
	private List<GeoPoint> mRoute;
	
	public DycapoOverlay (List<GeoPoint> route){
		this.mRoute = route;
	}
		
	/* (non-Javadoc)
	 * @see com.google.android.maps.Overlay#draw(android.graphics.Canvas, com.google.android.maps.MapView, boolean, long)
	 */
	@Override
	public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
		Projection projection = mapView.getProjection();
		if (shadow == false)
		{
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			Point point1 = new Point();
			Point point2 = new Point();
			
			int size = this.mRoute.size();
			int i = 0;
			paint.setColor(Color.RED);
			while (i< (size-1))
			{
				
				projection.toPixels(this.mRoute.get(i), point1);
				projection.toPixels(this.mRoute.get(i+1), point2);
				paint.setStrokeWidth(5);
				paint.setAlpha(120);
				canvas.drawLine(point1.x, point1.y, point2.x,point2.y, paint);
				i++;
			}
			
			mapView.getController().animateTo(mRoute.get(0));
		}
		return super.draw(canvas, mapView, shadow, when);
		
	}


}
