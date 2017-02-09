package provided.abcParser;

import java.util.*;

import provided.token.*;
import provided.token.tokenizer.ABCTokenizer;
import provided.music.*;

/**
 * Parses an abc file into IPhrase music structures<br/>
 * 
 * Usage: <br/>
 * ABCParser parser = new ABCParser("/data/aMusicFile.abc"); <br/>
 * IPhrase phrase = parser.parse(); <br/>
 * 
 * Cases handled:<br/>
 * - single notes with and without accidentals and naturals with regards to measures<br/>
 * - chords<br/>
 * - dotted notes<br/>
 * - grace notes<br/>
 * - repeats, including first and second repeats.<br/>
 * - triplets <br/>
 * - tuplets<br/>
 * - all headers<br/>
 * - quoted text<br/>
 * - D.C. al fine<br/>
 * - EOF (End Of File)<br/>
 * <br/>
 * All other cases ignored.
 * 
 * @author swong
 * 
 */
public class ABCParser {

	/**
	 * ABCTokenizer to use.
	 */
	private ABCTokenizer abcT;

	/**
	 * Proxy to that is used to reference the beginning of the phrase before it
	 * has finished being calculated.
	 */
	private DecoratorSeqList beginningTarget = new DecoratorSeqList();

	/**
	 * A stack that hold the latest jump targets (repeat start) for repeats.
	 */
	private Stack<DecoratorSeqList> jumpTargetStack = new Stack<DecoratorSeqList>();

	/**
	 * Pull off the current jump target from the jump target stack. If the stack
	 * is empty, return the proxy for the beginning of the phrase, as this is
	 * the default jump target.
	 * 
	 * @return The current jump target or the beginning of the phrase.
	 */
	private DecoratorSeqList popJumpTarget() {
		if (jumpTargetStack.isEmpty())
			return beginningTarget;
		else
			return jumpTargetStack.pop();
	}

	/**
	 * Push the given jump target onto the stack so that it is now the current
	 * jump target. This corresponds to the innermost nested repeat.
	 * 
	 * @param target
	 *            The new current jump target.
	 */
	private void pushJumpTarget(DecoratorSeqList target) {
		jumpTargetStack.push(target);
	}

	/**
	 * Stack that holds the proxies to the current second repeat start location.  
	 */
	private Stack<DecoratorSeqList> jumpSecondRepeatStack = new Stack<DecoratorSeqList>();

	/**
	 * Main algo used to create a list of IPhrases from the stream of tokens that represents the progression of the song (phrase).
	 */
	private ATokVisitor makeSeqListAlgo = new ATokVisitor(new ITokVisitorCmd() {

		@Override
		/**
		 * Default behavior is to ignore any unrecognized tokens by merely proceeding to the next one.
		 */
		public Object apply(String id, Token host, Object... params) {
			System.out.println("Unhandled Token: " + host);
			return abcT.getNextToken().execute(makeSeqListAlgo, params);
		}

	}) {
	};

	/**
	 * Constructs a parser for the given ABC input file.   Does not actually run that parser yet.
	 * Prints error message to System.err if there is a problem opening the given file.   The returned 
	 * parser will be null in the event of an error.
	 * @param inputFileName
	 *            the name of the input text file
	 */
	public ABCParser(String inputFileName) {

		try {
			abcT = new ABCTokenizer(inputFileName);

			// ------ EOF -----------------------------------------------------
			ITokVisitorCmd eofCmd = new ITokVisitorCmd() {

				@Override
				public Object apply(String id, Token host, Object... params) {
					return MTSeqList.Singleton;
				}
			};

			// ------ Note -----------------------------------------------------
			ITokVisitorCmd noteCmd = new ITokVisitorCmd() {

				@Override
				public Object apply(String id, Token host, Object... params) {
					Note note = makeNote(host);
					System.out.println("Note parsed: " + note);

					return new NESeqList(note, (ISeqList) abcT.getNextToken()
							.execute(makeSeqListAlgo, params));
				}
			};

			// ------ Header-------------------------------------------
			// Same command for all headers
			ITokVisitorCmd headerCmd = new ITokVisitorCmd() {
				@Override
				public Object apply(String id, Token host, Object... params) {
					Header header = new Header(id, host.getLexeme());
					System.out.println("Header parsed: " + header);
					return new NESeqList(header, (ISeqList) abcT.getNextToken()
							.execute(makeSeqListAlgo, params));
				}
			};

			// ------ Chord----------------------------------------------
			ITokVisitorCmd chordStartCmd = new ITokVisitorCmd() {

				// Internal visitor to get rest of chord
				ATokVisitor makeChordAlgo = new ATokVisitor(
						new ITokVisitorCmd() {
							@Override
							public Object apply(String id, Token host,
									Object... params) {
								throw new IllegalArgumentException(
										"ABCParser.chordCmd: Non-Note token encountered:"
												+ host);
							}

						}) {
					// Initializer block for internal visitor
					{
						this.addCmd("Note", new ITokVisitorCmd() {
							@SuppressWarnings("unchecked")
							@Override
							public Object apply(String id, Token host,
									Object... params) {
								Note note = makeNote(host);
								System.out
										.println("Chord parsed Note: " + note);
								((Vector<Note>) params[0]).add(note);
								return abcT.getNextToken().execute(
										makeChordAlgo, params);
							}
						});

						this.addCmd("]", new ITokVisitorCmd() {
							@SuppressWarnings("unchecked")
							@Override
							public Object apply(String id, Token host,
									Object... params) {
								Vector<Note> vNotes = (Vector<Note>) params[0];
								Note[] result = new Note[vNotes.size()];
								for (int i = 0; i < vNotes.size(); i++)
									result[i] = vNotes.get(i);
								return result;
							}
						});

					}
				};

				@Override
				public Object apply(String id, Token host, Object... params) {
					Note[] notes = (Note[]) abcT.getNextToken().execute(
							makeChordAlgo, new Vector<Note>());
					Chord chord = new Chord(notes);
					System.out.println("Chord parsed: " + chord);
					return new NESeqList(chord, (ISeqList) abcT.getNextToken()
							.execute(makeSeqListAlgo, params));
				}
			};

			// ------ Grace Note Start---------------------------------------
			ITokVisitorCmd graceStartCmd = new ITokVisitorCmd() {
				@Override
				public Object apply(String id, Token host, Object... params) {
					System.out.println("Grace Start parsed.");
					isGrace = true; // Set grace flag on
					// Continue on to next token.
					return abcT.getNextToken().execute(makeSeqListAlgo, params);
				}
			};

			// ------ Grace Note End--------------------------------------------
			ITokVisitorCmd graceEndCmd = new ITokVisitorCmd() {
				@Override
				public Object apply(String id, Token host, Object... params) {
					System.out.println("Grace End parsed.");
					isGrace = false; // Set grace flag on
					// Continue on to next token.
					return abcT.getNextToken().execute(makeSeqListAlgo, params);
				}
			};

			// ------ Tuplet----------------------------------------------
			ITokVisitorCmd tupletCmd = new ITokVisitorCmd() {

				// Internal visitor to get rest of chord
				ATokVisitor makeTupletAlgo = new ATokVisitor(
						new ITokVisitorCmd() {
							@Override
							public Object apply(String id, Token host,
									Object... params) {
								throw new IllegalArgumentException(
										"ABCParser.tupletCmd: Non-Note token encountered: "
												+ host);
							}

						}) {
					// Initializer block for internal visitor
					{
						this.addCmd("Note", new ITokVisitorCmd() {
							@SuppressWarnings("unchecked")
							@Override
							public Object apply(String id, Token host,
									Object... params) {
								int count = (Integer) params[0];
								Vector<Note> vNotes = ((Vector<Note>) params[1]);

								Note note = makeNote(host);
								System.out.println("Tuplet parsed Note: "
										+ note + " count = " + count);
								vNotes.add(note);

								if (1 == count) {
									Note[] result = new Note[vNotes.size()];
									for (int i = 0; i < vNotes.size(); i++)
										result[i] = vNotes.get(i);
									return result;
								} else {
									return abcT.getNextToken().execute(
											makeTupletAlgo, --count, vNotes);
								}
							}
						});

					}
				};

				@Override
				public Object apply(String id, Token host, Object... params) {
					int nNotes = Integer.parseInt(host.getLexeme());

					Note[] notes = (Note[]) abcT.getNextToken().execute(
							makeTupletAlgo, nNotes, new Vector<Note>());

					NoteCollection result;

					if (3 == nNotes) {
						result = new Triplet(notes[0], notes[1], notes[2]);
					} else {
						result = new Tuplet(notes);
					}
					System.out.println("Tuplet parsed: " + result);
					return new NESeqList(result, (ISeqList) abcT.getNextToken()
							.execute(makeSeqListAlgo, params));
				}
			};

			// ------ Measure bar --------------------------------------------
			ITokVisitorCmd measureBarCmd = new ITokVisitorCmd() {
				@Override
				public Object apply(String id, Token host, Object... params) {
					accidentalNotes.clear(); // clear any accidentals
					System.out
							.println("Measure bar parsed.  Accidental Notes cleared.");
					// Continue on to next token.
					return abcT.getNextToken().execute(makeSeqListAlgo, params);
				}
			};

			// ------ Repeat Start -----------------------------------------
			ITokVisitorCmd repeatStartCmd = new ITokVisitorCmd() {
				@Override
				public Object apply(String id, Token host, Object... params) {
					DecoratorSeqList repeatStart = new DecoratorSeqList();
					pushJumpTarget(repeatStart);
					System.out.println("Repeat Start parsed");
					ISeqList result = (ISeqList) abcT.getNextToken().execute(
							makeSeqListAlgo, params);
					repeatStart.setDecoree(result);
					return result;
				}
			};

			// ------ Repeat End -----------------------------------------
			ITokVisitorCmd repeatEndCmd = new ITokVisitorCmd() {
				@Override
				public Object apply(String id, Token host, Object... params) {
					ISeqList jumpTarget = popJumpTarget();
					System.out.println("Repeat End parsed");
					if (jumpSecondRepeatStack.isEmpty()) {
						return new ToggleDecoratorSeqList(jumpTarget,
								(ISeqList) abcT.getNextToken().execute(
										makeSeqListAlgo, params));
					} else {
						abcT.getNextToken().execute(makeSeqListAlgo, params);
						return jumpTarget;
					}
				}
			};

			// ------ First Repeat Start Repeat --------------------------
			ITokVisitorCmd firstRepeatStartCmd = new ITokVisitorCmd() {
				@Override
				public Object apply(String id, Token host, Object... params) {
					DecoratorSeqList secondRepeatStart = new DecoratorSeqList();
					jumpSecondRepeatStack.push(secondRepeatStart);
					System.out
							.println("First Repeat Start parsed.  ID = " + id);
					return new ToggleDecoratorSeqList((ISeqList) abcT
							.getNextToken().execute(makeSeqListAlgo, params),
							secondRepeatStart);
				}
			};

			// ------ Second Repeat Start Repeat --------------------------
			ITokVisitorCmd secondRepeatStartCmd = new ITokVisitorCmd() {
				@Override
				public Object apply(String id, Token host, Object... params) {
					DecoratorSeqList secondRepeatStart = jumpSecondRepeatStack
							.pop();
					System.out.println("Second Repeat Start parsed.  ID = "
							+ id);
					ISeqList result = (ISeqList) abcT.getNextToken().execute(
							makeSeqListAlgo, params);
					secondRepeatStart.setDecoree(result);
					return result;
				}
			};

			// ------ Alt. Second Repeat Start Repeat --------------------------
			/**
			 * This covers the ":|2" token which includes the repeat.
			 */
			ITokVisitorCmd secondRepeatStartAltCmd = new ITokVisitorCmd() {
				@Override
				public Object apply(String id, Token host, Object... params) {
					DecoratorSeqList secondRepeatStart = jumpSecondRepeatStack
							.pop();
					System.out
							.println("Second Repeat Start Alt. parsed.  ID = "
									+ id);
					ISeqList result = (ISeqList) abcT.getNextToken().execute(
							makeSeqListAlgo, params);
					secondRepeatStart.setDecoree(result);
					return popJumpTarget();
				}
			};

			// ------ Quote processing --------------------------
			ITokVisitorCmd quoteCmd = new ITokVisitorCmd() {

				FineToggleDecoratorSeqList fineToggle = new FineToggleDecoratorSeqList();

				boolean isSwapped = false;

				@Override
				public Object apply(String id, Token host, Object... params) {
					if (host.getLexeme().equals("_fine")) {

						System.out.println("\"_fine\" parsed");
						fineToggle.setDecoree(0, (ISeqList) abcT.getNextToken()
								.execute(makeSeqListAlgo, params));
						return fineToggle;
					} else if (host.getLexeme().equals("_D.C al Fine")) {
						if (isSwapped) {
							System.out.println("\"_D.C al Fine\" parsed");
							return new ISeqList() {

								@Override
								public Object execute(IPhraseVisitor algo,
										Object... params) {
									fineToggle.setFine();
									return beginningTarget
											.execute(algo, params);
								}
							};
						} else {
							isSwapped = true;
							System.out
									.println("\"_D.C al Fine\" parsed, but swapped for next token.");
							return swapToken(host).execute(makeSeqListAlgo,
									params);
						}
					} else {
						System.out.println("Quote parsed - ignored");
						return abcT.getNextToken().execute(makeSeqListAlgo,
								params);
					}
				}
			};

			// ---- Add Commands to Visitor------------------------------

			makeSeqListAlgo.addCmd("EOF", eofCmd);
			makeSeqListAlgo.addCmd("Note", noteCmd);

			String headers = "ABCDEFGHIKLMNOPQRSTWXZ";
			for (int i = 0; i < headers.length(); i++) {
				makeSeqListAlgo.addCmd("" + headers.charAt(i), headerCmd);
			}

			makeSeqListAlgo.addCmd("[", chordStartCmd);
			makeSeqListAlgo.addCmd("{", graceStartCmd);
			makeSeqListAlgo.addCmd("}", graceEndCmd);
			makeSeqListAlgo.addCmd("Tuplet", tupletCmd);
			makeSeqListAlgo.addCmd("|", measureBarCmd);
			makeSeqListAlgo.addCmd("|:", repeatStartCmd);
			makeSeqListAlgo.addCmd(":|", repeatEndCmd);
			makeSeqListAlgo.addCmd("|1", firstRepeatStartCmd);
			makeSeqListAlgo.addCmd("[1", firstRepeatStartCmd);
			makeSeqListAlgo.addCmd("[2", secondRepeatStartCmd);
			makeSeqListAlgo.addCmd("|2", secondRepeatStartCmd);
			makeSeqListAlgo.addCmd(":|2", secondRepeatStartAltCmd);
			makeSeqListAlgo.addCmd("Quote", quoteCmd);
		} catch (Exception e) {
			System.err.println("ABCParser constructor exception: " + e);
		}
	}

	/**
	 * 
	 * Run the parser and parse the entire file, returning an IPhrase object that represents the entire piece of music.
	 * 
	 * @return An IPhrase object
	 */
	public IPhrase parse() {
		ISeqList beginning = (ISeqList) abcT.getNextToken().execute(
				makeSeqListAlgo, MTSeqList.Singleton);
		beginningTarget.setDecoree(beginning);
		return beginning;
	}

	// ---- Note construction flags -------------------

	/**
	 * Current notes to be parsed are grace notes
	 */
	private boolean isGrace = false;

	/**
	 * Grace note duration multiplier.
	 */
	double grace_multiplier = 0.2;

	/**
	 * Total grace note duration of previous grace notes
	 */
	double graceDuration = 0.0;

	/**
	 * Parse a Note from the given Token
	 * 
	 * @param host
	 *            The Note token
	 * @return A Note object
	 */
	private Note makeNote(Token host) {
		char name = 'C';
		int octave = 0;
		int accidental = 0;
		double duration = 1.0;
		boolean isNatural = false;

		String lex = host.getLexeme();

		// get accidental value
		int i = 0;
		for (; i < lex.length(); i++) {
			if (Character.isLetter(lex.charAt(i))) {
				break;
			}
			if (lex.charAt(i) == '^') {
				accidental += +1;
			} else if (lex.charAt(i) == '_') {
				accidental += -1;
			} else if (lex.charAt(i) == '=') {
				isNatural = true;
			}
		}

		if (Character.isLowerCase(lex.charAt(i))) {
			octave++;
		}
		name = Character.toUpperCase(lex.charAt(i));
		i++;

		// get octave value

		for (; i < lex.length(); i++) {
			if (Character.isDigit(lex.charAt(i)) || '/' == lex.charAt(i)) {
				break;
			}
			if ('\'' == lex.charAt(i))
				octave += +1;
			else if (',' == lex.charAt(i))
				octave += -1;
		}

		if (i < lex.length()) {
			if (Character.isDigit(lex.charAt(i))) {
				int idx = lex.substring(i).indexOf('/');
				if (-1 == idx) {
					duration = Double.parseDouble(lex.substring(i));
				} else if (lex.length() > i + idx + 1) {
					duration = Double.parseDouble(lex.substring(i, i + idx))
							/ Double.parseDouble(lex.substring(i + idx + 1));
				} else {
					duration = Double.parseDouble(lex.substring(i, i + idx)) / 2.0;
				}
			} else {
				i++;
				if (i < lex.length()) {
					duration /= Double.parseDouble(lex.substring(i));
				} else {
					duration /= 2.0; // Slash w/o number is divide by two.
				}
			}
		}

		if (isGrace) {
			duration *= grace_multiplier;
			graceDuration += duration; // sum the duration of the grace notes
		} else if (graceDuration > 0.0) {
			// Correct the length of the current note to get it back on beat.
			duration -= graceDuration;
			graceDuration = 0.0;
		}

		if (dottedCorrection != 0.0) {
			duration += dottedCorrection;
			dottedCorrection = 0.0;
		}

		duration = (Double) abcT.getNextToken().execute(checkNextNoteAlgo,
				duration);

		Note note = new Note(name, octave, accidental, duration, isNatural);

		if (!isNatural) // naturals always override prior accidentals in measure
			checkAccidentals(note);
		return note;
	}

	/**
	 * The amount to additively correct the duration by due to dotted notes;
	 */
	private double dottedCorrection = 0.0;

	/**
	 * Checks for dotted notes. Takes in a duration and returns the correct
	 * value as per any dotted-ness.
	 */
	private ATokVisitor checkNextNoteAlgo = new ATokVisitor(
			new ITokVisitorCmd() {
				@Override
				public Object apply(String id, Token host, Object... params) {
					// No dotted modification detected.
					abcT.putBack(host); // put token back to be reprocessed
										// later
					dottedCorrection = 0.0;
					return params[0]; // return duration unchanged.
				}

			}) {
		// Initializer block for internal visitor
		{
			this.addCmd("<", new ITokVisitorCmd() {
				@Override
				public Object apply(String id, Token host, Object... params) {
					// the previous note is halved, the next dotted
					double duration = (Double) params[0] / 2.0;
					dottedCorrection = duration;
					System.out.println("Notes parsed \"" + id
							+ "\" dotting.  durationCorrection = "
							+ dottedCorrection);
					return duration;
				}
			});

			this.addCmd(">", new ITokVisitorCmd() {
				@Override
				public Object apply(String id, Token host, Object... params) {
					// the previous note is dotted, the next note halved
					double duration = (Double) params[0];
					dottedCorrection = -duration / 2.0;
					duration -= dottedCorrection;

					System.out.println("Notes parsed \"" + id
							+ "\" dotting.  durationCorrection = "
							+ dottedCorrection);
					return duration;
				}
			});
		}
	};

	/**
	 * Set of accidental notes in the current measure.
	 */
	private Set<Note> accidentalNotes = new HashSet<Note>();

	/**
	 * Checks to see if the given Note is in the set of accidentals Checks name
	 * and octave but not duration or accidental
	 * 
	 * @param note
	 *            The note to check
	 */
	private void checkAccidentals(Note note) {
		for (Note n : accidentalNotes) {
			if (n.getName() == note.getName()
					&& n.getOctave() == note.getOctave()) {
				System.out
						.println(note
								+ " found in accidentals.  Overriding accidentals and isNatural to "
								+ n);
				note.setAccidental(n.getAccidental());
				note.setIsNatural(n.getIsNatural()); // this will override
														// accidentals if true
				return;
			}
		}
		if (note.getAccidental() != 0 || note.getIsNatural()) {
			addAccidental(note);
		}
		return;
	}

	/**
	 * Adds a note to the set of accidentals replaces any note with the same
	 * name and octave. duration and accidental are ignored.
	 * 
	 * @param note
	 *            the Note to add.
	 */
	private void addAccidental(Note note) {
		removeAccidental(note); // remove anything like this note
		accidentalNotes.add(note); // put this specific note in
	}

	/**
	 * Removes a note from the set of accidentals Removes any note with the same
	 * name and octave. duration and accidental are ignored.
	 * 
	 * @param note
	 *            the note to remove
	 */
	private void removeAccidental(Note note) {
		for (Note n : accidentalNotes) {
			if (n.getName() == note.getName()
					&& n.getOctave() == note.getOctave()) {
				accidentalNotes.remove(n);
				// Check all notes, just to be safe
			}
		}
	}

	/**
	 * Swap the given token for the next token in the tokenizer, returning the
	 * next token. The given token will be the next token out of the tokenizer.
	 * 
	 * @param t The token to be swapped into the tokenizer
	 * @return the token that previously was next in the tokenizer. 
	 */
	private Token swapToken(Token t) {
		Token next = abcT.getNextToken();
		abcT.putBack(t);
		return next;
	}

}
