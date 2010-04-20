/**
 * 
 */
package eu.fbk.dycapo.models;

import java.util.Date;
import java.util.HashMap;

/**
 * @author riccardo
 *
 */
public class Location implements XMLRPCModel {
	
	private static final String LABEL="label";
	private static final String STREET="street";
	private static final String POINT="point";
	private static final String COUNTRY="country";
	private static final String REGION="region";
	private static final String TOWN="town";
	private static final String POSTCODE="postcode";
	private static final String SUBREGION="subregion";
	private static final String INTERSECTION="intersection";
	private static final String ADDRESS="address";
	private static final String GEORSS_POINT="georss_point";
	private static final String GEORSS_LATITUDE="georss_latitude";
	private static final String GEORSS_LONGITUDE="georss_longitude";
	private static final String OFFSET="offset";
	private static final String RECURS="recurs";
	private static final String DAYS="days";
	private static final String LEAVES="leaves";	
	
	private String label;
	private String street;
	private String point;
	private String country;
	private String region;
	private String town;
	private int postcode;
	private String subregion;
	private String intersection;
	private String address;
	private String georss_point;
	private float georss_latitude;
	private float georss_longitude;
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
	public String getPoint() {
		return point;
	}
	/**
	 * @param point the point to set
	 */
	public void setPoint(String point) {
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
	 * @return the intersection
	 */
	public String getIntersection() {
		return intersection;
	}
	/**
	 * @param intersection the intersection to set
	 */
	public void setIntersection(String intersection) {
		this.intersection = intersection;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	public float getGeorss_latitude() {
		return georss_latitude;
	}
	/**
	 * @param georssLatitude the georss_latitude to set
	 */
	public void setGeorss_latitude(float georssLatitude) {
		georss_latitude = georssLatitude;
	}
	/**
	 * @return the georss_longitude
	 */
	public float getGeorss_longitude() {
		return georss_longitude;
	}
	/**
	 * @param georssLongitude the georss_longitude to set
	 */
	public void setGeorss_longitude(float georssLongitude) {
		georss_longitude = georssLongitude;
	}
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
	public void setLeaves(Date leaves) {
		this.leaves = leaves;
	}
	
	public HashMap<String,Object> toHashMap(){
		HashMap<String,Object> result = new HashMap<String,Object>();
		result.put(Location.ADDRESS,this.address);
		result.put(Location.COUNTRY,this.country);
		result.put(Location.DAYS,this.days);
		result.put(Location.GEORSS_LATITUDE,this.georss_latitude);
		result.put(Location.GEORSS_LONGITUDE,this.address);
		result.put(Location.GEORSS_POINT,this.address);
		result.put(Location.INTERSECTION,this.address);
		result.put(Location.LABEL,this.address);
		result.put(Location.LEAVES,this.address);
		result.put(Location.OFFSET,this.address);
		result.put(Location.POINT,this.address);
		result.put(Location.POSTCODE,this.address);
		result.put(Location.RECURS,this.address);
		result.put(Location.REGION,this.address);
		result.put(Location.STREET,this.address);
		result.put(Location.SUBREGION,this.address);
		result.put(Location.TOWN, this.town);
		return result;
	}
}
