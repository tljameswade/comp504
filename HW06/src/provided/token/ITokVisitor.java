package provided.token;

/**
 * Interface for a token visitor.
 */
public interface ITokVisitor {
	/**
	 * Host Token calls this method with its ID to run its associated processing. 
	 * @param id the id of the host token
	 * @param host the host token
	 * @param params vararg list of input parameters that the processing might need.
	 * @return the result of processing the host.
	 */
	public Object caseAt(String id, Token host, Object... params);
}
