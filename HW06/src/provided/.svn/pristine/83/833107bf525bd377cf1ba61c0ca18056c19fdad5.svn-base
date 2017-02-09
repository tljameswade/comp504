package provided.token;

/**
 * A command for use in ATokVisitors
 */
public interface ITokVisitorCmd {

	/**
	 * The ATokVisitor delegates its caseAt call to this method
	 * @param id  the id (name) of the host Token
	 * @param host the host token itelf
	 * @param params vararg list of input parameters for use by the command.
	 * @return the result of the running the command.
	 */
	public Object apply(String id, Token host, Object... params);
}