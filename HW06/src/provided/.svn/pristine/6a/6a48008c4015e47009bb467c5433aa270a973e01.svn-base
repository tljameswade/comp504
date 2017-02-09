package provided.music;

/**
 * An empty sequence list.
 * ----------------------------------------------
 * An empty ISeqList
 */
public class MTSeqList implements ISeqList {

	/**
	 * The ID value of this class
	 */
	public static final String ID = "MTSeqList";

	/**
	 * Singleton instance of the class
	 */
	public static final MTSeqList Singleton = new MTSeqList();

	/**
	 * private constructor
	 */
	private MTSeqList() {
	}

	/**
	 * Calls the MTSeqList.ID case of the visitor.
	 */
	@Override
	public Object execute(IPhraseVisitor algo, Object... params) {
		return algo.caseAt(ID, this, params);
	}

	/**
	 * Returns "{}"
	 * return "{}" always.
	 */
	public String toString() {
		return "{}";
	}
}
