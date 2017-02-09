package view;

/**
 * Adapter to the model from the view
 * @author swong
 * @param THost  the type of the host to be used.
 */
public interface IModelAdapter<THost> {

	/**
	 * Returns the result of the model processing given the visitor's classname and the given parameter string using the given host.
	 * @param host The host that runs the given visitor 
	 * @param classname  The visitor's class name to use
	 * @param param The input parameter to use
	 * @return The result of the processing
	 */
	String runAlgo(THost host, String classname, String param);

	/**
	 * Returns the result of the model processing given the 
	 * accumulator's classname and the given parameter string 
	 * using the given host with the FoldRAlgo visitor.
	 * @param host The host that runs the FoldRAlgo visitor 
	 * @param accClassname  The accumulator's class name to use
	 * @return The result of the processing
	 */
	String runFoldR(THost host, String accClassname);
	
	/**
	 * Returns the result of the model processing given the 
	 * accumulator's classname and the given parameter string 
	 * using the given host with the FoldLAlgo visitor.
	 * @param host The host that runs the FoldLAlgo visitor 
	 * @param accClassname  The accumulator's class name to use
	 * @return The result of the processing
	 */	
	String runFoldL(THost host, String accClassname);}
