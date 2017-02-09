package provided.token.tokenizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.*;

/**
 * Special tokenizer that converts a stream of various types of tokens from  StreamTokenizer and converts them into a stream of strings. 
 * @author swong
 *
 */
public class StringTokenizer implements IStringTokenizer {

	/**
	 * Stack used for token push-back
	 */
	private Stack<String> stack = new Stack<String>();

	/**
	 * StreamTokenizer to use.
	 */
	protected StreamTokenizer _st;

	/**
	 * Reader to use.
	 */
	protected Reader _fileReader;

	/**
	 * Initialize _st to read from a input Reader file with the given input file
	 * name.
	 * 
	 * @param inputFileName
	 *            the name of the input text file
	 * @throws FileNotFoundException When the input file could not be opened
	 */
	public StringTokenizer(String inputFileName) throws FileNotFoundException {
		// Create an input text file object:

		//_st = new StreamTokenizer(new BufferedReader(url.openStream()));
		//_fileReader = new FileReader(inputFileName);
		try {
			InputStream is = this.getClass().getResource(inputFileName)
					.openStream();

			_fileReader = new BufferedReader(new InputStreamReader(is));

			//_fileReader = new FileReader(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("StringTokenizer error opening file: " + e);
			e.printStackTrace();
		}

		// Create a Streamtokenizer to parse the input text file:
		_st = new StreamTokenizer(_fileReader);

		_st.eolIsSignificant(true);
		_st.wordChars(33, 125);
	}

	/**
	 * Get the next token in the stream
	 * @return the next token.
	 */
	@Override
	public String getNextStringToken() {
		if (stack.empty())
			return makeNextStringToken();
		else
			return stack.pop();
	}

	/**
	 * Puts the given string back into the stream.   If nothing else is done, 
	 * this would be the next token returned by getNextToken
	 */
	@Override
	public void putStringBack(String str) {
		stack.push(str);
	}

	/**
	 * Returns the next string in the stream.  
	 * Returns LF ("\n") at end of line.  
	 * Returns null for EOF or error
	 * 
	 * @return A string token or null if EOF
	 */
	private String makeNextStringToken() {
		try {
			int t = _st.nextToken();
			if (StreamTokenizer.TT_EOL == t)
				return "\n";
			if (StreamTokenizer.TT_EOF != t) {
				switch (_st.ttype) {

				case StreamTokenizer.TT_WORD: {
					return _st.sval;
				}

				case StreamTokenizer.TT_NUMBER: {
					// check if number is an integer
					if (Math.floor(_st.nval) == _st.nval) {
						return "" + (int) _st.nval;
					} else {
						return "" + _st.nval;
					}
				}

				default:
					throw new IllegalArgumentException(
							"Tokenizer1: Illegal token type = " + _st.ttype);
				}
			} else {
				_fileReader.close();
				System.err.println("EOF Token");
				return null;
			}
		} catch (IOException e) {
			System.out.println("StringTokenizer.makeNextToken(): Exception = "
					+ e.getMessage());
			return null;
		}

	}

}
