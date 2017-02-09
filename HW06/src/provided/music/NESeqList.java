package provided.music;

/**
 * A non-empty ("cons") sequence list.
 * ----------------------------------------------
 * A non-empty ISeqList 
 */
public class NESeqList implements ISeqList {

	/**
	 * The ID value of this class
	 */
	public static final String ID = "NESeqList";

	/**
	 * The "first" of the list
	 */
	private IPhrase _first;

	/**
	 * The "rest" of the list
	 */
	private ISeqList _rest;

	/**
	 * Cons an IPhrase to a Sequence List.
	 * @param first The first element of the sequence
	 * @param rest The rest of the sequence
	 */
	public NESeqList(IPhrase first, ISeqList rest) {
		this._rest = rest;
		this._first = first;
	}

	/**
	 * Accessor method for the first element
	 * @return the IPhrase that is the first element of the sequence list
	 */
	public IPhrase getFirst() {
		return _first;
	}

	/**
	 * Accessor method for the rest of the lsit
	 * @return the rest of the sequence list
	 */
	public ISeqList getRest() {
		return _rest;
	}

	/**
	 * Calls the NESeqList.ID case of the visitor.
	 * @param algo  The visitor that is being used
	 * @param params vararg input parameters for the visitor.
	 * @return the result of running the visitor on this host.
	 */
	@Override
	public Object execute(IPhraseVisitor algo, Object... params) {
		return algo.caseAt(ID, this, params);
	}

	/**
	 * toString algo for rest, the recursive helper function of the ToString algo. 
	 */
	private static IPhraseVisitor _toStringAlgo;

	/**
	 * Setter for the _toStringAlgo helper.  This non-typical method is provided solely 
	 * for the purpose of enabling the student to install their toStringAlgo into the 
	 * read-only provided code.
	 * @param stringAlgo The helper algo to use.
	 */
	public static void setToStringAlgo(IPhraseVisitor stringAlgo) {
		_toStringAlgo = stringAlgo;
	}

	/**
	 * Getter for the _toStringAlgo helper.  Only for use internally inside this package
	 * so ToggleDecoratorSeqList and FineToggleDecoratorSeqList can use the same visitor.
	 * @return the toStringAlgo helper visitor
	 */
	static IPhraseVisitor getToStringAlgo() {
		return _toStringAlgo;
	}

	/**
	 * Run a visitor over the list to convert it to a String.  
	 * Delegates to the rest of the list using the helper algo, with the accumulator 
	 * (params[0]) set to "{"+_first.toString().  
	 * @return "{a, b, c, d}"
	 */
	public String toString() {
		return (String) _rest.execute(_toStringAlgo, "{" + _first.toString());
	}
}
