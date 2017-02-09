package extVisitor;

/**
 * An individual command that, when installed into an ExtVisitor, is associated with a particular host type.
 * @author swong
 *
 */
public interface IExtVisitorCmd {
	/**
	 * The method called when this command is executed.  
	 * @param id The id of the calling host.
	 * @param host The calling host itself.
	 * @param params  Any extra input parameters the command might need.
	 * @return The results of performing this command when inspired by the associated host.
	 */
	public Object apply(String id, IHost host, Object... params);
}
