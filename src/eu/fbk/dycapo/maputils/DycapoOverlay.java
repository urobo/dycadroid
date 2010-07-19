/**
 * 
 */
package eu.fbk.dycapo.maputils;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

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
	private static final int RADIUS=4;
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
				i++;
				projection.toPixels(mRoute.get(i-1), point1);
				projection.toPixels(this.mRoute.get(i), point2);
				paint.setStrokeWidth(5);
				paint.setAlpha(120);
				canvas.drawLine(point1.x, point1.y, point2.x,point2.y, paint);
			}
			
			projection.toPixels(mRoute.get(0), point1);
			projection.toPixels(mRoute.get((size-1)), point2);
			
			
			RectF ovalOrig = new RectF(point1.x - RADIUS,point1.y - RADIUS,
					point1.x + RADIUS,point1.y + RADIUS);
			RectF ovalDest = new RectF(point2.x - RADIUS,point2.y - RADIUS,
					point2.x + RADIUS,point2.y + RADIUS);
	
			paint.setAlpha(255);
			
			/* starting point */
			paint.setColor(Color.GREEN);
			canvas.drawOval(ovalOrig, paint);
			/* ending point */
			paint.setColor(Color.BLUE);
			canvas.drawOval(ovalDest, paint);
			
			mapView.getController().setCenter(mRoute.get(0));
		}
		return super.draw(canvas, mapView, shadow, when);
		
	}


}
