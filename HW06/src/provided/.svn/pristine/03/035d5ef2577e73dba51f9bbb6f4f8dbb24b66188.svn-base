package provided.token;

/**
 * Represents a token in the language.
 */
public class Token {

	/**
	 * The name of this token, which is its id.
	 */
	String _name;

	/**
	 * The _lexeme (lexical value) this token is representing.
	 */
	private String _lexeme;

	/**
	 * Token constructor.
	 *
	 * @param name The name associated with this token.  This is the id value when it executes its visitors.
	 * @param lexeme _lexeme this token is representing.
	 */
	public Token(String name, String lexeme) {
		_name = name;
		_lexeme = lexeme;
	}

	/**
	 * Executes (accepts) the visitor, calling the case associated with this host's name value.
	 * @param algo The visitor to execute
	 * @param params The input parameters supplied to the algo when its appropriate case is called.
	 * @return The return value from executing the appropriate case on the visitor.
	 */
	public Object execute(ITokVisitor algo, Object... params) {
		return algo.caseAt(_name, this, params);
	}

	/**
	 * Accessor for the name of this token
	 * @return The name of this token
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Accessor for the lexeme of this token
	 * @return The lexeme of this token
	 */
	public String getLexeme() {
		return _lexeme;
	}

	/**
	 * Return a string representation of the token: "[name] lexeme"
	 *
	 * @return string representation
	 */
	public String toString() {
		return "[" + _name + "] " + _lexeme;
	}
}
