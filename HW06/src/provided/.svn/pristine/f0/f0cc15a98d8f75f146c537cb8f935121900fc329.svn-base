package provided.music;

import java.util.Hashtable;
import java.util.Map;

/**
 * Command-based implementation of IPhraseVisitor that uses IPhraseVisitorCmds stored in a hash table.
 * @author swong
 *
 */
public abstract class APhraseVisitor implements IPhraseVisitor {

	/**
	 * The default command to use when caseAt is called with an ID that is not in the hashtable as a key.
	 */
	private IPhraseVisitorCmd defaultCmd;

	/**
	 * The dictionary of commands, implemented using a hash table.
	 */
	private Map<String, IPhraseVisitorCmd> cmds = new Hashtable<String, IPhraseVisitorCmd>();

	/**
	 * Constructor that installs a default cmd that throws an IllegalArgumentException on any unknown host. * ----------------------------------------------
	 * Abstract extended visitor implementation that uses a dictionary of commands to associate commands with host IDs.
	 */
	public APhraseVisitor() {
		defaultCmd = new IPhraseVisitorCmd() {
			public Object apply(String idx, IPhrase host, Object... inps) {
				throw new IllegalArgumentException(
						"APhraseVisitor: Unknown index encountered: " + idx);
			}
		};
	}

	/**
	 * Constructor that sets the default cmd to the supplied cmd.
	 * @param defaultCmd   the default cmd to use.
	 */
	public APhraseVisitor(IPhraseVisitorCmd defaultCmd) {
		this.defaultCmd = defaultCmd;
	}

	/**
	 * Runs the command associated with the given id, passing along the given host and vararg parameters.  
	 * If there is no cmd associated with the given id in the dictionary, then use the default cmd.
	 * @param id The ID value that identifies the host
	 * @param host The host IPhrase object
	 * @param params  vararg list of parameters that might be used.   Can be called with no input params.
	 * @return the result of executing the associated cmd.
	 */
	public Object caseAt(String id, IPhrase host, Object... params) {
		if (cmds.containsKey(id)) {
			return cmds.get(id).apply(id, host, params);
		} else {
			return defaultCmd.apply(id, host, params);
		}
	}

	/**
	 * Add the given command to the dictionary, associated with the given id value.
	 * @param id The id of the host that will use this command
	 * @param cmd The command that will be run when the host calls for it.
	 */
	public void addCmd(String id, IPhraseVisitorCmd cmd) {
		cmds.put(id, cmd);
	}

}
