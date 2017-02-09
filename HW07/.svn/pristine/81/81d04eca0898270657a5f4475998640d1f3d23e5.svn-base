package clientView;

/**
 * Adapter the view uses to communicate to the model
 * 
 * @author Xun Luan, Suozhi Qi
 */
public interface IModelAdapter0 {
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

	/**
	 * Calculate the fibonacci number at the given position
	 * @param parseInt the input number
	 * @return the fibonacci number
	 */
	public String calcFibo(int parseInt);

	/**
	 * Display the longest palindrome sequence of the input string
	 * @param text the input string
	 * @return the longest palindrome sequence
	 */
	public String longestPalin(String text);

	/**
	 * Display a sorted array with a certain number of randomly chosen numbers from 0 to 100
	 * @param size the input number
	 * @return the sorted array
	 */
	public String randomArray(int size);

}