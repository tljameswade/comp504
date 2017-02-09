package visitors;

/**
 * An abstract visitor to an IHost object.  Provides cases for each IHost subclass type.
 * @author swong
 *
 */
public interface IVisitor {

	/**
	 * Case for HostA
	 * @param host The HostA instance
	 * @param params Optional input parameters 
	 * @return The result of processing HostA with the given parameters.
	 */
	public Object caseHostA(HostA host, Object... params);

	/**
	 * Case for HostB
	 * @param host The HostB instance
	 * @param params Optional input parameters 
	 * @return The result of processing HostB with the given parameters.
	 */
	public Object caseHostB(HostB host, Object... params);

	/**
	 * Case for HostC
	 * @param host The HostC instance
	 * @param params Optional input parameters 
	 * @return The result of processing HostC with the given parameters.
	 */
	public Object caseHostC(HostC host, Object... params);

	/**
	 * A null visitor, used when no visitor is yet defined.  Simply returns a string indicating which host executed this visitor.
	 */
	public static final IVisitor NULL_VISITOR = new IVisitor() {

		@Override
		public Object caseHostA(HostA host, Object... params) {
			return "NULL_VISITOR.caseHostA";
		}

		@Override
		public Object caseHostB(HostB host, Object... params) {
			return "NULL_VISITOR.caseHostB";
		}

		@Override
		public Object caseHostC(HostC host, Object... params) {
			return "NULL_VISITOR.caseHostC";
		}

	};

}
