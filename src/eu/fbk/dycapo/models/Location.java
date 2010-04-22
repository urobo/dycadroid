/**
 * 
 */
package eu.fbk.dycapo.models;

import java.util.Date;
import java.util.HashMap;

import eu.fbk.dycapo.xmlrpc.XMLRPCModel;

/**
 * @author riccardo
 *
 */
public class Location implements XMLRPCModel {
	
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
	public static final String[] POINT_TYPE = {	"orig",
													"dest",
													"wayp",
													"posi"
													};
	
	private String label;
	private String street;
	private int point;
	private String country;
	private String region;
	private String town;
	private int postcode;
	private String subregion;
	private String georss_point;
	private int offset;
	private String recurs;
	private String days;
	private Date leaves; 
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
	public int getPoint() {
		return point;
	}
	/**
	 * @param point the point to set
	 */
	public void setPoint(int point) {
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
	public int getPostcode() {
		return postcode;
	}
	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(int postcode) {
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
	 * @return the georss_latitude
	 */
	
	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}
	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
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
	//FIXME
	public void setLeaves(Date leaves) {
		this.leaves = leaves;
	}

	public HashMap<String,Object> toHashMap(){
		HashMap<String,Object> result = new HashMap<String,Object>();
	    if (this.country instanceof java.lang.String)result.put(Location.COUNTRY,this.country);
		if (this.days instanceof java.lang.String)result.put(Location.DAYS,this.days);
		if (this.georss_point instanceof java.lang.String)result.put(Location.GEORSS_POINT,this.georss_point);
		if (this.label instanceof java.lang.String)result.put(Location.LABEL,this.label);
		if (this.leaves instanceof java.util.Date)result.put(Location.LEAVES,this.leaves.toString());
		if (this.offset > 0)result.put(Location.OFFSET,this.offset);
		if (this.point >= 0 && this.point <4)result.put(Location.POINT,Location.POINT_TYPE[this.point]);
		if (this.postcode >= 0)result.put(Location.POSTCODE,this.postcode);
		if (this.recurs instanceof java.lang.String)result.put(Location.RECURS,this.recurs);
		if (this.region instanceof java.lang.String)result.put(Location.REGION,this.region);
		if (this.street instanceof java.lang.String)result.put(Location.STREET,this.street);
		if (this.subregion instanceof java.lang.String)result.put(Location.SUBREGION,this.subregion);
		if (this.town instanceof java.lang.String)result.put(Location.TOWN, this.town);
		return result;
	}
}
