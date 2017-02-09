package provided.music;

/**
 * An abc Header.  Headers are identified by a one character "id" and
 * can have an arbitrary string value. <br/>
 * Note that this class represents many different *types* of headers, 
 * each with its own id value. 
 * ----------------------------------------------
 * A single Header class represents multiple, distinct header hosts. 
 */
public class Header implements IPhrase {

	/**
	 * The ID of this header.  The case corresponding to this ID is called on the visitors.
	 */
	private String _id;

	/**
	 * the text value of this header.
	 */
	private String _value;

	/**
	 * Create a header out of an id and value
	 * 
	 * @param id    - one character corresponding to the abc header name
	 * @param value - arbitrary string value of the header
	 */
	public Header(String id, String value) {
		_id = id;
		_value = value;
	}

	/* (non-Javadoc)
	 * @see music.IPhrase#execute(music.IPhraseVisitor, java.lang.Object[])
	 */
	/**
	 * Calls the header id'th case of the visitor algo.
	 * @param algo The visitor that is being used.  
	 * @param params vararg list of input parameters that the algo might use.
	 * @return the result of running the id'th case of the visitor.
	 */
	@Override
	public Object execute(IPhraseVisitor algo, Object... params) {
		return algo.caseAt(_id, this, params);
	}

	/**
	 * Accessor for the id.
	 * @return the _id
	 */
	public String getID() {
		return _id;
	}

	/**
	 * Accessor for the text value of this header.
	 * @return the _value
	 */
	public String getValue() {
		return _value;
	}

	/**
	 * String representation of this header: "id: text"
	 */
	public String toString() {
		return _id + ":" + _value;
	}
}
