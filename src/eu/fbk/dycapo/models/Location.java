/**
 * 
 */
package eu.fbk.dycapo.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import android.util.Log;
import eu.fbk.dycapo.xmlrpc.XMLRPCModel;


/**
 * @author riccardo
 *
 */
public class Location implements XMLRPCModel{
	public static final String TAG = "Location";
	
	public static final String LABEL="label";
	public static final String STREET="street";
	public static final String POINT="point";
	public static final String COUNTRY="country";
	public static final String REGION="region";
	public static final String TOWN="town";
	public static final String POSTCODE="postcode";
	public static final String SUBREGION="subregion";
	public static final String GEORSS_POINT="georss_point";
	public static final String OFFSET="offset";
	public static final String RECURS="recurs";
	public static final String DAYS="days";
	public static final String LEAVES="leaves";
	public static final String[] POINT_TYPE = {		"orig",
													"dest",
													"wayp",
													"posi"
													};
	public static final int ORIG = 0;
	public static final int DEST = 1;
	public static final int WAYP = 2;
	public static final int POSI = 3;
	
	protected String label;			//may
	protected String street;			//should*
	protected Integer point;				//must
	protected String country;			//may
	protected String region;			//may
	protected String town;			//should*
	protected Integer postcode;			//should*
	protected String subregion;		//may
	protected String georss_point;	//should*
	protected Integer offset;				//should
	protected String recurs;			//may
	protected String days;			//may
	protected Date leaves; 			//must
	/**
	 * 
	 */
	public Location() {
		
	}
	
	/** 
	 * @param label
	 * @param point
	 * @param georssPoint
	 * @param offset
	 * @param leaves
	 */
	public Location(String label, int point, String georssPoint,
			int offset, Date leaves) {
		this.label = label;
		this.point = point;
		this.georss_point = georssPoint;
		this.offset = offset;
		this.leaves = leaves;
	}

	

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the point
	 */
	public Integer getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(Integer point) {
		if (point >= 0 && point < Location.POINT_TYPE.length)
		this.point = point;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}

	/**
	 * @param town the town to set
	 */
	public void setTown(String town) {
		this.town = town;
	}

	/**
	 * @return the postcode
	 */
	public Integer getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}

	/**
	 * @return the subregion
	 */
	public String getSubregion() {
		return subregion;
	}

	/**
	 * @param subregion the subregion to set
	 */
	public void setSubregion(String subregion) {
		this.subregion = subregion;
	}

	/**
	 * @return the georss_point
	 */
	public String getGeorss_point() {
		return georss_point;
	}

	/**
	 * @param georssPoint the georss_point to set
	 */
	public void setGeorss_point(String georssPoint) {
		georss_point = georssPoint;
	}

	/**
	 * @return the offset
	 */
	public Integer getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	/**
	 * @return the recurs
	 */
	public String getRecurs() {
		return recurs;
	}

	/**
	 * @param recurs the recurs to set
	 */
	public void setRecurs(String recurs) {
		this.recurs = recurs;
	}

	/**
	 * @return the days
	 */
	public String getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(String days) {
		this.days = days;
	}

	/**
	 * @return the leaves
	 */
	public Date getLeaves() {
		return leaves;
	}

	/**
	 * @param leaves the leaves to set
	 */
	public void setLeaves(Date leaves) {
		this.leaves = leaves;
	}

	public HashMap<String,Object> toHashMap(){
		HashMap<String,Object> result = new HashMap<String,Object>();
	    if (this.country instanceof java.lang.String)result.put(Location.COUNTRY,this.country);
		if (this.days instanceof java.lang.String)result.put(Location.DAYS,this.days);
		if (this.georss_point instanceof java.lang.String)result.put(Location.GEORSS_POINT,this.georss_point);
		if (this.label instanceof java.lang.String)result.put(Location.LABEL,this.label);
		if (this.leaves instanceof java.util.Date){
			SimpleDateFormat formatter
		     = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
			result.put(Location.LEAVES,formatter.format(this.leaves));
			Log.i("sdf",formatter.format(this.leaves));
		}
		Log.i("date",this.leaves.toString());
		//if (this.leaves instanceof java.util.Date)result.put(Location.LEAVES,this.leaves);
		if (this.offset instanceof Integer)result.put(Location.OFFSET,this.offset);
		if (this.point instanceof Integer)result.put(Location.POINT,Location.POINT_TYPE[this.point]);
		if (this.postcode instanceof Integer)result.put(Location.POSTCODE,this.postcode);
		if (this.recurs instanceof java.lang.String)result.put(Location.RECURS,this.recurs);
		if (this.region instanceof java.lang.String)result.put(Location.REGION,this.region);
		if (this.street instanceof java.lang.String)result.put(Location.STREET,this.street);
		if (this.subregion instanceof java.lang.String)result.put(Location.SUBREGION,this.subregion);
		if (this.town instanceof java.lang.String)result.put(Location.TOWN, this.town);
		return result;
	}
	
	
//	@Override
//	public void fromBundle(Bundle data) {
//		
//		if(data.containsKey(Location.COUNTRY))
//			this.country = data.getString(Location.COUNTRY);
//		
//		if(data.containsKey(Location.DAYS))
//			this.days = data.getString(Location.DAYS);
//		
//		if(data.containsKey(Location.GEORSS_POINT))
//			this.georss_point = data.getString(Location.GEORSS_POINT);
//		
//		if(data.containsKey(Location.LABEL))
//			this.label = data.getString(Location.LABEL);
//		
//		if(data.containsKey(Location.LEAVES)){
//			SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
//			try {
//				this.leaves = sdf.parse(data.getString(Location.LEAVES));
//			} catch (ParseException e) {
//				Log.d(TAG, e.getMessage());
//			}
//		}
//		
//		if(data.containsKey(Location.OFFSET))
//			this.offset = data.getInt(Location.OFFSET);
//		
//		if(data.containsKey(Location.POINT)){
//			String point_type = data.getString(Location.POINT);
//			if (point_type.equals(Location.POINT_TYPE[Location.ORIG]))
//				this.point = Location.ORIG;
//			else if (point_type.equals(Location.POINT_TYPE[Location.DEST]))
//				this.point = Location.DEST;
//			else if (point_type.equals(Location.POINT_TYPE[Location.WAYP]))
//				this.point = Location.WAYP;
//			else if (point_type.equals(Location.POINT_TYPE[Location.POSI]))
//				this.point = Location.POSI;
//		}
//		
//		if(data.containsKey(Location.POSTCODE))
//			this.postcode = data.getInt(Location.POSTCODE);
//		
//		if(data.containsKey(Location.RECURS))
//			this.recurs = data.getString(Location.RECURS);
//		
//		if(data.containsKey(Location.REGION))
//			this.region = data.getString(Location.REGION);
//		
//		if(data.containsKey(Location.STREET))
//			this.street = data.getString(Location.STREET);
//		
//		if(data.containsKey(Location.SUBREGION))
//			this.subregion = data.getString(Location.SUBREGION);
//		
//		if(data.containsKey(Location.TOWN))
//			this.town = data.getString(Location.TOWN);
//		
//	}
//
//	@Override
//	public Bundle toBundle() {
//		Bundle result = new Bundle();
//		if(this.country instanceof String) result.putString(Location.COUNTRY, this.country);
//		if(this.days instanceof String) result.putString(Location.DAYS, this.days);
//		if(this.georss_point instanceof String) result.putString(Location.GEORSS_POINT, this.georss_point);
//		if(this.label instanceof String) result.putString(Location.LABEL, this.label);
//		if(this.leaves instanceof Date) {
//			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
//			result.putString(Location.LEAVES, formatter.format(this.leaves));
//		}
//		if(this.offset instanceof Integer) result.putInt(Location.OFFSET, this.offset);
//		if(this.point instanceof Integer) result.putString(Location.POINT, Location.POINT_TYPE[this.point]);
//		if(this.postcode instanceof Integer) result.putInt(Location.POSTCODE, this.postcode);
//		if(this.recurs instanceof String) result.putString(Location.RECURS, this.recurs);
//		if(this.region instanceof String) result.putString(Location.REGION, this.region);
//		if(this.street instanceof String) result.putString(Location.STREET, this.street);
//		if(this.subregion instanceof String) result.putString(Location.SUBREGION, this.subregion);
//		if(this.town instanceof String) result.putString(Location.TOWN, this.town);
//		return result;
//	}
}
