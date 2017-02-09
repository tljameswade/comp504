package provided.client.view;

/**
 * Adapter the view uses to communicate to the model
 */
public interface IModelAdapter {
	/**
	 * Requests that model connect to the RMI Registry at the given remote host
	 * 
	 * @param remoteHost
	 *            The remote host to connect to.
	 * @return A status string regarding the connection result
	 */
	public String connectTo(String remoteHost);

	/**
	 * Calculates PI to the given number of decimal places by sending an entire
	 * Pi2 object to the remote ICompute object.
	 * 
	 * @param numDigits
	 *            The number of digits to compute PI to.
	 * @return A status string on the computation.
	 */
	public String calcPi(int numDigits);

	/**
	 * Gets the system information from the remote server by sending an entire GetInfo
	 * object to the remote server.   
	 * @param p   A string passed to the GetInfo object to use as it pleases.
	 * @return A status string on the GetInfo task.
	 */
	public String calcGetInfo(String p);

	/**
	 * Quits the applications and gracefully shuts down the RMI-related resources.
	 */
	public void quit();

}