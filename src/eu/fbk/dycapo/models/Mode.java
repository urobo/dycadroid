/**
 * 
 */
package eu.fbk.dycapo.models;

import java.util.HashMap;

import eu.fbk.dycapo.xmlrpc.XMLRPCModel;

/**
 * @author riccardo
 *
 */
/**
 * @author riccardo
 *
 */
public class Mode implements XMLRPCModel {
	
	public static final String KIND="kind";
	public static final String CAPACITY="capacity";
	public static final String VACANCY="vacancy";
	public static final String MAKE="make";
	public static final String MODEL="model";
	public static final String YEAR="year";
	public static final String COLOR="color";
	public static final String LIC="lic";
	public static final String COST="cost";
	
	private String kind;			//must
	private Integer capacity;		//must
	private Integer vacancy;		//must
	private String make;			//must
	private String model;			//must
	private Integer year;			//may
	private String color;			//should
	private String lic;				//should
	private Double cost;			//should
	
	/**
	 * 
	 */
	public Mode(){
		
	}

	/**
	 * @return the kind
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * @param kind the kind to set
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the vacancy
	 */
	public int getVacancy() {
		return vacancy;
	}

	/**
	 * @param vacancy the vacancy to set
	 */
	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}

	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the lic
	 */
	public String getLic() {
		return lic;
	}

	/**
	 * @param lic the lic to set
	 */
	public void setLic(String lic) {
		this.lic = lic;
	}

	/**
	 * @return the cost
	 */
	public Double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	public HashMap<String,Object> toHashMap(){
		HashMap<String,Object> result = new HashMap<String,Object>();
		if (this.capacity >= 2)result.put(Mode.CAPACITY,this.capacity);
		if (this.color instanceof java.lang.String)result.put(Mode.COLOR,this.color);
		if (this.cost > 0)result.put(Mode.COST,this.cost);
		if (this.kind instanceof java.lang.String)result.put(Mode.KIND,this.kind);
		if (this.lic instanceof java.lang.String)result.put(Mode.LIC,this.lic);
		if (this.make instanceof java.lang.String)result.put(Mode.MAKE,this.make);
		if (this.model instanceof java.lang.String)result.put(Mode.MODEL,this.model);
		if (this.vacancy >=0 && this.vacancy < this.capacity)result.put(Mode.VACANCY,this.vacancy);
		if (this.year >= 0)result.put(Mode.YEAR,this.year);
		return result;
	}
}
