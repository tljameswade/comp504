package provided.music;

/**
 * Interface for a Phrase visitor. 
 * ----------------------------------------------
 * Abstract extended visitor.
 * Supplies case behavior for every host.
 */
public interface IPhraseVisitor {
	/**
	 * Called by the host to run the appropriate processing for that host.
	 * @param id The id of the host
	 * @param host The host itself
	 * @param params vararg list of input parameters that the algo might use.
	 * @return the result of running the associated processing for the host.
	 */
	public Object caseAt(String id, IPhrase host, Object... params);
}
