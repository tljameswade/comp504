package provided.music;

/**
 * A collection of notes that make up a Chord.  All of the notes should
 * be played simultaneously.   The last note defines the length of all the notes.
 * ----------------------------------------------
 * A collection of Notes all played at once
 */
public class Chord extends NoteCollection {

	/**
	 * The ID value of this class
	 */
	public static final String ID = "Chord";

	/**
	 * Create a Chord out of a collection of notes.
	 * @param notes vararg list of notes used  in the chord.
	 */
	public Chord(Note... notes) {
		super(notes);
	}

	/**
	 * Calls the Chord.ID case of the given algo.
	 * @param algo The IPhraseVisitor algo to use.
	 * @param params vararg list of input parameters
	 * @return the result of running the Chord case of the visitor.
	 */
	@Override
	public Object execute(IPhraseVisitor algo, Object... params) {
		return algo.caseAt(ID, this, params);
	}

	/**
	 * Returns a string representation of the chord: "(noteA, noteB, ...)"
	 * @return string representation of the chord
	 */
	public String toString() {
		String result = "Chord(" + notes[0];
		for (int i = 1; i < notes.length; i++) {
			result += ", " + notes[i];
		}
		return result + ")";
	}
}
