/**
 * 
 */
package provided.music;

/**
 * Class that represents a tuplet, a series of notes played in a fraction of their normal total time.
 * The exact fraction depends on the meter and the number of notes in the tuplet.
 * A triplet is a specific type of tuplet.
 * @author swong
 * ----------------------------------------------
 * A arbitrary set of notes played in sequence in a special manner.
 */
public class Tuplet extends NoteCollection {

	/**
	 * The ID value of this class
	 */
	public static final String ID = "Tuplet";

	/**
	 * Constructor for a tuplet
	 * @param notes vararg list of notes that form the tuplet, in order of play.
	 */
	public Tuplet(Note... notes) {
		super(notes);
	}

	/**
	 * Calls the Tuplet.ID case of the visitor.
	 * @param algo the visitor being used
	 * @param params vararg list of parameters for the visitor
	 * @return the result of the Tuplet case of the visitor.
	 */
	@Override
	public Object execute(IPhraseVisitor algo, Object... params) {
		return algo.caseAt(ID, this, params);
	}

	/**
	 * String representation of the tuplet:
	 * @return "Tuplet(n1, n2, n3, etc)"
	 */
	public String toString() {
		String result = "Tuplet(" + notes[0];
		for (int i = 1; i < notes.length; i++) {
			result += ", " + notes[i];
		}
		return result + ")";
	}

}
