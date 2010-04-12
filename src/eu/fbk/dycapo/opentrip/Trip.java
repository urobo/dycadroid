/**
 * 
 */
package eu.fbk.dycapo.opentrip;

import java.sql.Timestamp;


/**
 * @author riccardo
 *
 */
public class Trip {
	private Timestamp published;
	private Timestamp updated;
	private Timestamp expires;
	private String content;
	private boolean active;
	private Person author;
	
	/**
	 * 
	 */
	public Trip(){
		
	}

	/**
	 * @return the published
	 */
	public Timestamp getPublished() {
		return published;
	}

	/**
	 * @param published the published to set
	 */
	public void setPublished(Timestamp published) {
		this.published = published;
	}

	/**
	 * @return the updated
	 */
	public Timestamp getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	/**
	 * @return the expires
	 */
	public Timestamp getExpires() {
		return expires;
	}

	/**
	 * @param expires the expires to set
	 */
	public void setExpires(Timestamp expires) {
		this.expires = expires;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the author
	 */
	public Person getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(Person author) {
		this.author = author;
	}
	
	
	
}
