package extVisitor;

/**
 * Represents a host who can accept an IExtVisitor type.
 * 
 * @author swong
 *
 */
public interface IHost {
	
	/**
	 * Execute the case on the visitor corresponding to this host.  
	 * @param algo  the visitor
	 * @param params Optional vararg parameters that the algo could use.
	 * @return The results of running the visitor 
	 */
	public Object execute(IExtVisitor algo, Object... params);
}
