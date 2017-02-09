package provided.token;

/**
 * Concrete factory to create tokens
 */
public class TokenFactory implements ITokenFactory {
	/**
	 * Singleton instance of this class
	 */
	public static final TokenFactory Singleton = new TokenFactory();

	/**
	 * private constructor needed for singleton
	 */
	private TokenFactory() {
	}

	/**
	 * Creates Tokens from the given name and lexeme. The returned tokens will
	 * call the case in their visitors corresponding to their *name*.
	 * 
	 * For many tokens the name and lexeme are the same, but for some, it is
	 * not, e.g. an Quote token, where the name is "Quote", but the lexeme is the
	 * string value of the quoted text. 
	 * 
	 * @param name
	 *            The name of the token
	 * @param lexeme
	 *            The lexeme value for the token
	 * @return An Token instance with the given name and lexeme which calls the
	 *         named case of its visitors.
	 */
	public Token makeToken(final String name, String lexeme) {
		return new Token(name, lexeme);
	}
}