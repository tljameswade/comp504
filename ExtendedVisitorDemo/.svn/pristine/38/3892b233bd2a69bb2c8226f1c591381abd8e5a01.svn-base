package demo.view;

/**
 * The adapter from the view to the model
 * @author swong
 *
 * @param <THost> The type of object held by the "hosts" drop lists.
 * @param <TVisitor> The type of the object held by the "visitor" drop lists.
 */
public interface IModelAdapter<THost, TVisitor> {

	/**
	 * Returns a new host object with the given id 
	 * @param id  The id of the new host
	 * @return A THost instance
	 */
	THost makeHost(String id);
	
	/**
	 * Return the results of having the given host execute the given visitor with no input parameters.
	 * @param host   The host to use
	 * @param visitor  The visitor to use.
	 * @return  The results of host.execute(visitor)
	 */
	Object run(THost host, TVisitor visitor);

	/**
	 * Returns a new visitor with the given name (shows when its toString() method is called).
	 * @param name   The name of the visitor
	 * @return A new TVisitor instance
	 */
	TVisitor makeVisitor(String name);
	
	/**
	 * Add or replace a command in the given visitor with a new command for the given host id 
	 * with the given result string.
	 * @param id The id of the host corresponding to the new command
	 * @param resultStr  The String that is returned when that visitor is run on a host with the given id.
	 * @param visitor  The visitor to modify
	 */
	void addCmd(TVisitor visitor, String id, String resultStr);

}
