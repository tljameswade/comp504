package provided.token.tokenizer;

import provided.token.*;

/**
 * Extract and return an appropriate Token from some given source.
 */
public abstract interface ITokenizer {
	/**
	 * Return the next token.
	 * @return the next token in the tokenizer
	 */
	public abstract Token getNextToken();

	/**
	 * Put the previously consumed token back into the token stream. 
	 * @param t the token to put back into the tokenizer
	 */
	public abstract void putBack(Token t);
}
