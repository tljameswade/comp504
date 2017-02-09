package provided.client.model;

/**
 * Adapter the model uses to communicate to the view
 */
public interface IViewAdapter {
	/**
	 * Appends the string to the end of the text display on the view The view
	 * does NOT automatically add a linefeed to the end of the string!
	 * 
	 * @param s
	 *            The String to append
	 */
	public void append(String s);

	/**
	 * Sets the displayed remote host address.
	 * @param hostAddress The IP address or host name of the remote host.
	 */
	public void setRemoteHost(String hostAddress);
}