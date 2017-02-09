package provided.token;

import java.util.*;

/**
 * A visitor to an Token that implements ITokVisitor using a dictionary of commands.
 */
public abstract class ATokVisitor implements ITokVisitor {
	/**
	 * The default command to use if there are no command associated with the given host id.
	 */
	private ITokVisitorCmd defaultCmd;

	/**
	 * The dictionary mapping token id's (names) to associated commands.  Implemented using a hast table.
	 */
	private Map<String, ITokVisitorCmd> cmds = new Hashtable<String, ITokVisitorCmd>();

	/**
	 * Constructor that sets the default cmd to one that throws and IllegalArgumentException" for any unknown tokens.
	 */
	public ATokVisitor() {
		defaultCmd = new ITokVisitorCmd() {
			public Object apply(String idx, Token host, Object... inps) {
				throw new IllegalArgumentException(
						"ATokVisitor: Unknown token encountered: " + idx);
			}
		};
	}

	/**
	 * Constructor that sets the default cmd to the given command.
	 * @param defaultCmd the default cmd to use.
	 */
	public ATokVisitor(ITokVisitorCmd defaultCmd) {
		this.defaultCmd = defaultCmd;
	}

	/**
	 * Delegates to the ITokVisitorCmd associated with the given host id (name), 
	 * or if nothing is associated with the id, use the default cmd.  
	 * @param id the name of the host Token
	 * @param host the host token itself.
	 * @return the result of the delegation to the associated cmd.
	 */
	public Object caseAt(String id, Token host, Object... params) {
		if (cmds.containsKey(id)) {
			return cmds.get(id).apply(id, host, params);
		} else {
			return defaultCmd.apply(id, host, params);
		}
	}

	/**
	 * Add a new cmd or replace an existing cmd associated with the given host id.
	 * @param id the id of the host to associate with this visitor.
	 * @param cmd The command to associate with the given key value.
	 */
	public void addCmd(String id, ITokVisitorCmd cmd) {
		cmds.put(id, cmd);
	}

}