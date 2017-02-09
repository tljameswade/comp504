package provided.music;

/**
 * A musical triplet.  The three notes should be played with 2/3 of their
 * indicated duration. 
 * ----------------------------------------------
 * A set of 3 notes played in sequence in a special manner.
 */
public class Triplet extends NoteCollection {

	/**
	 * The ID value of this class
	 */
	public static final String ID = "Triplet";

	/**
	 * Create a new Note out of three notes
	 * 
	 * @param n1 the first note in the triplet
	 * @param n2 the second note in the triplet
	 * @param n3 the third note in the triplet
	 */
	public Triplet(Note n1, Note n2, Note n3) {
		super(n1, n2, n3);
	}

	/**
	 * Calls the Triplet.ID case of the visitor
	 * @param algo the visitor being used
	 * @param params vararg list of parameters for use by the visitor
	 * @return the result of the "Triplet" case of the visitor.
	 */
	@Override
	public Object execute(IPhraseVisitor algo, Object... params) {
		return algo.caseAt(ID, this, params);
	}

	/**
	 * Return string representation of the triplet
	 * @return "Triplet(n1, n2, n3)"
	 */
	public String toString() {
		String result = "Triplet(" + notes[0];
		for (int i = 1; i < notes.length; i++) {
			result += ", " + notes[i];
		}
		return result + ")";
	}
}
