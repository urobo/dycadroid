/**
 * 
 */
package eu.fbk.dycapo.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import android.util.Log;

import com.google.android.maps.GeoPoint;

/**
 * @author riccardo
 * 
 */
public abstract class GeoGuard {
	private static final String TAG = "GeoPointBuilder";

	public static final GeoPoint parseGeoRSSPoint(String geo) {
		int separator = geo.indexOf(" ");

		String slat = geo.substring(0, separator - 1);
		String slng = geo.substring(separator + 1);
		double lat = parseStringToDouble(slat);
		double lng = parseStringToDouble(slng);
		GeoPoint point = new GeoPoint((int) ((lat) * 1E6), (int) ((lng) * 1E6));
		return point;
	}

	public static final double parseStringToDouble(String number) {
		NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
		nf.setMaximumFractionDigits(6);
		nf.setMinimumFractionDigits(6);
		try {
			Number number_value = nf.parse(number);
			Log.d(TAG, number_value.toString());
			return number_value.doubleValue();
		} catch (ParseException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
		return (Double) null;
	}

	public static final String validateGeoRSSPoint(String geo) {
		int separator = geo.indexOf(" ");
		String slat = geo.substring(0, separator - 1).trim();
		String slng = geo.substring(separator + 1).trim();
		double lat = parseStringToDouble(slat);
		double lng = parseStringToDouble(slng);
		Log.d(TAG, (lat + " " + lng));
		return (lat + " " + lng);
	}
}
