/**
 * 
 */
package provided.music;

/**
 * The command used by APhraseVisitor that is associated with a host or hosts.
 * @author rixner
 * ----------------------------------------------
 * Abstract command used by APhraseVisitor.  Each command is associated with a particular host(s).
 */
public interface IPhraseVisitorCmd {
	/**
	 * The method called when the APhraseVisitor delegates its caseAt call to this command.
	 * @param id  the id of the host
	 * @param host the host itself
	 * @param params vararg list of input parameters.
	 * @return the result of this processing of the host.
	 */
	public Object apply(String id, IPhrase host, Object... params);
}
