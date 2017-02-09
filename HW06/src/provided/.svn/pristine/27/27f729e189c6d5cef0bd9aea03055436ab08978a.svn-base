package provided.token.tokenizer;

import java.io.*;
import java.util.*;

import provided.token.ITokenFactory;
import provided.token.Token;
import provided.token.TokenFactory;

/**
 * Tokenizer for the abc music language. getNextToken() returns next Token in
 * stream. Does not recognize all possible language constructs and symbols
 * (returned as "?" token).  Tokens are the atomic psemantic pieces of a language,
 * such as Note, repeat start, measure, chord, etc.   The token ids (names) returned are: <br/>
 *  <br/>
 *  EOF - end of file<br/>
 *  "|:" - repeat start<br/>
 *  ":|" - repeat end<br/>
 *  "|1" - first repeat start<br/>
 *  "[1" - alternative first repeat start<br/>
 *  "|2" - second repeat start<br/>
 *  "[2" - alternative second repeat start<br/>
 *  "[|" - thick-thin double bar line<br/>
 *  "|]" - thin-thick double bar line<br/>
 *  ":|2" - alternative second repeat start<br/>
 *  "||" - double measure bar<br/>
 *  "::" - short hand for repeat end-start ":||:"<br/>
 *  "|" - Measure bar<br/>
 *  "[") - Chord start<br/>
 *  "]" - Chord end<br/>
 *  "-" - tie<br/>
 *  "&lt;" - the previous note  is dotted, the next note halved<br/> 
 *  "&gt;" - the previous note is halved, the next dotted<br/>
 *  "{" - Grace begin<br/>
 *  "}" - Grace end<br/>
 *  "%" - Comment<br/>
 *  "[Header ID]" - where [Header ID] is a single letter from {ABCDEFGHIKLMNOPQRSTWXZ} <br/> 
 *  "Note" - a single note whose lexeme contains the name, accidentals, octave and duration<br/>
 *  "Tuplet" - the lexeme is the number of subsequent Notes comprise this tuplet. <br/>
 *  "Quote" - lexeme is the quoted text <br/>
 *  "?" - unknown language construct. <br/>
 *   <br/>
 * References: <br/>
 * <a href =
 * "http://download.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html"
 * >java.util.regex.Pattern for regex guide</a><br/>
 * <a href="http://asciitable.com/">ASCII table for codes</a><br/>
 * <a href="http://abcnotation.com/abc2mtex/abc.txt">abc music standard 1.6</a>
 */
public class ABCTokenizer extends StringTokenizer implements ITokenizer {

	/**
	 * maps regex to their ASCII string counterparts. The key is the regex and
	 * the value is the keyword. Used for splitting strings up on the keywords.
	 * keywords1 is searched first
	 */
	private Map<String, String> keywords1 = new Hashtable<String, String>();

	/**
	 * keywords2 is searched second
	 */
	private Map<String, String> keywords2 = new Hashtable<String, String>();

	/**
	 * Ordered sets of keywords to check.
	 */
	private Vector<Map<String, String>> keywordsVector = new Vector<Map<String, String>>();

	/**
	 * Factory for the tokens
	 */
	private ITokenFactory tokFac = TokenFactory.Singleton;

	/**
	 * Push-back stack for tokens.
	 */
	private Stack<Token> stack = new Stack<Token>();

	/**
	 * Allowable note accidentals
	 */
	public static final String ACCIDENTALS = "_=^";

	/**
	 * Constructor for the class
	 * @param inputFileName  File to tokenize
	 * @throws FileNotFoundException  When input file could not be loaded.
	 */
	public ABCTokenizer(String inputFileName) throws FileNotFoundException {
		super(inputFileName);

		// note: "\\7C" => "|", "\\x5B" => "[", "\\x5D" => "]"
		// "\\x7B" => "{",  "\\x7D" => "}"

		// load the regex->keyword mapping
		keywords1.put("\\x7C:", "|:"); // repeat start
		keywords1.put(":\\x7C", ":|"); // repeat end
		keywords1.put("\\x7C1", "|1"); // first repeat start
		keywords1.put("\\x5B1", "[1"); // alternative first repeat start
		keywords1.put("\\x7C2", "|2"); // second repeat start
		keywords1.put("\\x5B2", "[2"); // alternative second repeat start
		keywords1.put("\\x5B\\7C", "[|"); // thick-thin double bar line
		keywords1.put("\\7C\\x5D", "|]"); // thin-thick double bar line
		keywords1.put(":\\x7C2", ":|2"); // alternative second repeat start
		keywords1.put("\\x7C\\x7C", "||"); // double measure bar
		keywords1.put("::", "::"); // short hand for repeat end-start ":||:"

		// Should these be separated out into a different map because order of
		// search is important?
		keywords2.put("\\x7C", "|"); // Measure bar
		keywords2.put("\\x5B", "["); // Chord start
		keywords2.put("\\x5D", "]"); // Chord end
		keywords2.put("-", "-"); // tie
		keywords2.put(">", ">"); // the previous note  is dotted, the next note halved 
		keywords2.put("<", "<"); // the previous note is halved, the next dotted
		keywords2.put("\\x7B", "{"); // Grace begin
		keywords2.put("\\x7D", "}"); // Grace end		

		keywordsVector.add(keywords1);
		keywordsVector.add(keywords2);

	}

	/**
	 * Return the next token, either from the stream or the last token if it was
	 * pushed back
	 */
	public Token getNextToken() {
		if (stack.empty())
			return makeNextToken();
		else
			return stack.pop();
	}

	/*
	 * (non-Javadoc)
	 * @see token.tokenizer.ITokenizer#putBack(token.Token)
	 * 
	 */
	/**
	 *	 Push token back into the stream
	 * @param t the Token you need to put back in the stream. 
	 */
	public void putBack(Token t) {
		stack.push(t);
	}

	/**
	 * Parse the strings down from the getNextStringToken further, separating
	 * out the abc keywords into tokens.
	 * @return The next Token in the stream
	 */
	private Token makeNextToken() {
		String sTok = this.getNextStringToken();
		if (null == sTok)
			return tokFac.makeToken("EOF", "EOF"); // EOF returns as null
		if (sTok.equals("\n"))
			return makeNextToken(); // discard bare LF's.

		// search for first keywords, pushing extra tokens back into tokenizer's
		// stack.
		for (Map<String, String> keywords : keywordsVector) {
			for (String kw : keywords.keySet()) {
				if (keywords.get(kw).equals(sTok))
					return tokFac.makeToken(sTok, sTok);
				String[] split = sTok.split(kw, -1);
				if (split.length > 1) {

					for (int i = split.length - 1; i > 0; i--) {
						if (!split[i].equals("")) {
							this.putStringBack(split[i]);
						}
						this.putStringBack(keywords.get(kw));
					}
					if (!split[0].equals("")) {
						this.putStringBack(split[0]);
					}
					return makeNextToken();
				}
			}
		}

		// Find header tokens, which are the whole line.
		int idx = sTok.indexOf(":");
		if (1 == idx) {
			String header = sTok;
			String next = super.getNextStringToken();
			while (!next.equals("\n")) {
				header += " " + next;
				next = this.getNextStringToken();
			}
			return tokFac
					.makeToken(header.substring(0, 1), header.substring(2));
		}

		// Find comment, which is the rest of the line.
		idx = sTok.indexOf("%");
		if (-1 != idx) {
			// Comment in line somewhere
			if (0 == idx) { // comment is at beginning of line
				String comment = sTok;
				String next = super.getNextStringToken();
				while (!next.equals("\n")) { // get the rest of the line
					comment += next;
					// get the next string token 
					next = this.getNextStringToken();
				}
				return tokFac.makeToken("%", comment.substring(1));
			} else { // comment is in middle of the line
				// push the entire comment, incl. the comment char, back into
				// the tokenizer b/c it can't be dealt with yet.
				this.putStringBack(sTok.substring(idx));
				// push the tokens before the comment back into the tokenizer
				// so that they can be then processed when pulled back out.
				this.putStringBack(sTok.substring(0, idx));
				return makeNextToken(); // Process whatever is in the tokenizer
			}
		}

		// Find tuplet
		if ('(' == sTok.charAt(0)) {
			// Tuplet found
			int n = Integer.parseInt(sTok.substring(1, 2)); // get tuplet size
			this.putStringBack(sTok.substring(2)); // push rest back into tokenizer
			return tokFac.makeToken("Tuplet", "" + n);
		}

		if (Character.isLetter(sTok.charAt(0))
				|| ACCIDENTALS.contains(sTok.subSequence(0, 1))) {
			// Start of Note string found.
			return tokFac.makeToken("Note", parseNote(sTok));
		}

		if ('\"' == sTok.charAt(0)) {
			// Quoted string found
			return tokFac.makeToken("Quote", parseQuote("", sTok.substring(1)));
		}

		if (sTok.equals("\\")) {
			// abc line break detected.  Ignore.
			return makeNextToken();
		}

		// Unknown token
		return tokFac.makeToken("?", sTok);
	}

	/**
	 * Returns the first note string in the given string.
	 * 
	 * @param sTok  The string to parse
	 * @return A String that is the first note in the given string
	 */
	private String parseNote(String sTok) {
		int i = 1;
		// look for pre-note name characters
		if (!Character.isLetter(sTok.charAt(0))) {
			for (; i < sTok.length(); i++) {
				if (sTok.charAt(i) != sTok.charAt(0)) {
					i++; // move to next char
					break; // This assumes that this char is a letter which
							// follows the accidentals
				}
			}
		}

		for (; i < sTok.length(); i++) {
			// look for non-post-note name characters.
			if (!(Character.isDigit(sTok.charAt(i)) || ",/'".contains(sTok
					.subSequence(i, i + 1)))) {
				putStringBack(sTok.substring(i)); // put the rest back in the
													// tokenizer.
				break;
			}
		}
		// i = end of note string
		return sTok.substring(0, i);
	}

	/**
	 * Returns the rest of a string, incl. spaces until the next double quote
	 * @param acc  Accumulated result string
	 * @param sTok the current string token
	 * @return the full quoted string
	 */
	private String parseQuote(String acc, String sTok) {
		for (int i = 0; i < sTok.length(); i++) {
			if ('\"' == sTok.charAt(i)) {
				String rest = sTok.substring(i + 1);
				if (!rest.equals("")) {
					this.putStringBack(rest);
				}
				return acc;
			} else {
				acc += sTok.charAt(i);
			}
		}
		acc += " ";
		return parseQuote(acc, this.getNextStringToken());
	}

}
