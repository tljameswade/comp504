package provided.compute;

/**
 * A local view adapter for a task
 * 
 * @author swong
 * 
 */
public interface ILocalTaskViewAdapter {

	/**
	 * Default view adapter that simply prints to standard out.
	 */
	public static final ILocalTaskViewAdapter DEFAULT_ADAPTER = new ILocalTaskViewAdapter() {
		public void append(String s) {
			System.out.println("ITaskViewAdapter.append: " + s);
		}
	};

	/**
	 * Append the given string to the view's display
	 * @param s the string to display
	 */
	public void append(String s);
}