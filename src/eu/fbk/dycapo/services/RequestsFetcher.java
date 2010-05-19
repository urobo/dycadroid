/**
 * 
 */
package eu.fbk.dycapo.services;

/**
 * @author riccardo
 *
 */
public class RequestsFetcher implements Runnable {
	public static final String DYCAPO="dycapo.";
	private String method=null;
	
	public RequestsFetcher(String methodName){
		this.method=methodName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
