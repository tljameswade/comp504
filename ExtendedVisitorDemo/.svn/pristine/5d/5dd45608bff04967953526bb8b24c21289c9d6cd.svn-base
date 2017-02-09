package extVisitor;

import java.util.*;

/**
 * Hashtable (dictionary/"Map")implementation of IExtVisitor
 * @author swong
 *
 */
public class ExtVisitor implements IExtVisitor {

	/**
	 * Identifying name for this visitor
	 */
	private String name;
	
	/**
	 * Dictionary of commands keyed to a String id.
	 */
	private Map<String, IExtVisitorCmd> cmds = new Hashtable<String, IExtVisitorCmd>();
	
	/**
	 * Default command used when unknown case is encountered.
	 */
	private IExtVisitorCmd defaultCmd = (id, host, params)-> "Default case of "+ExtVisitor.this.toString();
	
//	private IExtVisitorCmd defaultCmd = new IExtVisitorCmd(){
//
//		@Override
//		public Object apply(String id, IHost host, Object... params) {
//			return "Default case of "+ExtVisitor.this.toString();
//		}
//		
//	};

	/**
	 * Constructor for the class
	 * @param name The identifying string for the class
	 */
	public ExtVisitor(String name){
		this.name = name;
	}
	
	@Override
	/**
	 * Parameterized case statement for the visitor.  Will execute the command assoicated with the given id, or the default command if 
	 * no match exists.
	 * @param id String ID that identifies the host
	 * @param host The host for the visitor
	 * @params  Vararg input of parameters for the algo to use.
	 */
	public Object caseAt(String id, IHost host, Object... params) {
		if(cmds.containsKey(id)) {
			return cmds.get(id).apply(id, host, params);
		}
		else {
			return defaultCmd.apply(id, host, params);
		}
	}

	/**
	 * Returns a string representation of the visitor, which is its name prepended with "ExtVisitor_".
	 */
	public String toString(){
		return "ExtVisitor_"+name;
	}

	/**
	 * Add or replace the command associated with the given id.
	 * @param id  The id with which to associate the given command
	 * @param cmd The command to associate wtih the given key.
	 */
	public void addCmd(String id, IExtVisitorCmd cmd) {
		cmds.put(id, cmd);
	}
}
