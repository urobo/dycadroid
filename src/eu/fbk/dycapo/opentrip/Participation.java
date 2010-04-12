/**
 * 
 */
package eu.fbk.dycapo.opentrip;

import java.sql.Timestamp;

/**
 * @author riccardo
 *
 */
public class Participation {
	private String role;
	private boolean requested;
	private Timestamp requested_timestamp;
	private boolean accepted;
	private Timestamp accepted_timestamp;
	private boolean started;
	private Timestamp started_timestamp;
	private boolean finished;
	private Timestamp finished_timestamp;
	
	/**
	 * 
	 */
	public Participation() {
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the requested
	 */
	public boolean isRequested() {
		return requested;
	}

	/**
	 * @param requested the requested to set
	 */
	public void setRequested(boolean requested) {
		this.requested = requested;
	}

	/**
	 * @return the requested_timestamp
	 */
	public Timestamp getRequested_timestamp() {
		return requested_timestamp;
	}

	/**
	 * @param requestedTimestamp the requested_timestamp to set
	 */
	public void setRequested_timestamp(Timestamp requestedTimestamp) {
		requested_timestamp = requestedTimestamp;
	}

	/**
	 * @return the accepted
	 */
	public boolean isAccepted() {
		return accepted;
	}

	/**
	 * @param accepted the accepted to set
	 */
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	/**
	 * @return the accepted_timestamp
	 */
	public Timestamp getAccepted_timestamp() {
		return accepted_timestamp;
	}

	/**
	 * @param acceptedTimestamp the accepted_timestamp to set
	 */
	public void setAccepted_timestamp(Timestamp acceptedTimestamp) {
		accepted_timestamp = acceptedTimestamp;
	}

	/**
	 * @return the started
	 */
	public boolean isStarted() {
		return started;
	}

	/**
	 * @param started the started to set
	 */
	public void setStarted(boolean started) {
		this.started = started;
	}

	/**
	 * @return the started_timestamp
	 */
	public Timestamp getStarted_timestamp() {
		return started_timestamp;
	}

	/**
	 * @param startedTimestamp the started_timestamp to set
	 */
	public void setStarted_timestamp(Timestamp startedTimestamp) {
		started_timestamp = startedTimestamp;
	}

	/**
	 * @return the finished
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * @param finished the finished to set
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	/**
	 * @return the finished_timestamp
	 */
	public Timestamp getFinished_timestamp() {
		return finished_timestamp;
	}

	/**
	 * @param finishedTimestamp the finished_timestamp to set
	 */
	public void setFinished_timestamp(Timestamp finishedTimestamp) {
		finished_timestamp = finishedTimestamp;
	}
	
	
}
