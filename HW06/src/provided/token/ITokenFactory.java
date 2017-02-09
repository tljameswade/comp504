package provided.token;

/**
 * Abstract factory to produce tokens
 */
public interface ITokenFactory {
	/**
	 * Creates an instance of an Token from the given name and lexeme values
	 * @param name the name of the token
	 * @param lexeme the lexeme of the token
	 * @return the corresponding Token object
	 */
	public Token makeToken(String name, String lexeme);
}