package provided.music;

/**
 * Basic class representing musical Notes. 
 * ----------------------------------------------
 * A playable note or rest.
 */
public class Note implements INote {

	/**
	 * The ID value of this class
	 */
	public static final String ID = "Note";

	/**
	 * Note name:  {A, B, C, D, E, F, G, Z}  (Z = rest)
	 */
	private char name = 'Z';
	/**
	 * Octave value
	 */
	private int octave = 0;
	/**
	 * Accidental  (normally in {-1, 0, +1})
	 */
	private int accidental = 0;
	/**
	 * Duration
	 */
	private double duration = 0;

	/**
	 * Flag to override key signature
	 **/
	private boolean isNatural = false;

	/**
	 * Constructor for the class
	 * @param name  The name of the note
	 * @param octave The octave of the note w.r.t. middle C.  Zero = same octave as middle C.
	 * @param accidental accidental setting sharp &gt; 0, flat &lt; 0
	 * @param duration duration of note
	 * @param isNatural flag to override key signature.  True if note should be natural, overriding key signature.
	 */
	public Note(char name, int octave, int accidental, double duration,
			boolean isNatural) {
		setName(name);
		setOctave(octave);
		setAccidental(accidental);
		setDuration(duration);
		setIsNatural(isNatural);
	}

	/**
	 * Returns a string representation of the Note  
	 * @return "(name, octave, duration)"
	 */
	public String toString() {
		String s = "(";
		s += Character.toString(name);
		int a = accidental;
		if (isNatural) {
			s += "n";
		}
		while (a < 0) {
			s += "b";
			a++;
		}
		while (a > 0) {
			s += "#";
			a--;
		}
		s += ", " + Integer.toString(octave) + ", ";
		s += String.format("%3.2f", duration);
		s += ")";

		return s;
	}

	/**
	 * Calls the Note.ID case of the visitor.
	 */
	@Override
	public Object execute(IPhraseVisitor algo, Object... params) {
		return algo.caseAt(ID, this, params);
	}

	/**
	 * Accessor to set the name of the note
	 * @param name - the name to set
	 */
	public void setName(char name) {
		switch (name) {
		case 'a':
		case 'A':
			this.name = 'A';
			break;

		case 'b':
		case 'B':
			this.name = 'B';
			break;

		case 'c':
		case 'C':
			this.name = 'C';
			break;

		case 'd':
		case 'D':
			this.name = 'D';
			break;

		case 'e':
		case 'E':
			this.name = 'E';
			break;

		case 'f':
		case 'F':
			this.name = 'F';
			break;

		case 'g':
		case 'G':
			this.name = 'G';
			break;

		default:
			this.name = 'Z';
		}
	}

	/**
	 * Accessor to get the name of the note
	 * @return the name
	 */
	public char getName() {
		return name;
	}

	/**
	 * Accessor to set the octave of the note
	 * @param octave - the octave to set
	 */
	public void setOctave(int octave) {
		this.octave = octave;
	}

	/**
	 * Accessor to get the octave
	 * @return the octave
	 */
	public int getOctave() {
		return octave;
	}

	/**
	 * Accessor to set the accidental value
	 * @param accidental - the accidental to set
	 */
	public void setAccidental(int accidental) {
		this.accidental = accidental;
	}

	/**
	 * Accessor to get the value of the accidental
	 * @return the accidental
	 */
	public int getAccidental() {
		return accidental;
	}

	/**
	 * Accessor to set the note duration
	 * @param duration - the duration to set
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}

	/**
	 * Accessor to get the note duraton
	 * @return the duration
	 */
	public double getDuration() {
		return duration;
	}

	/**
	 * Accessor for isNatural flag
	 * @return isNatural flag setting
	 */
	public boolean getIsNatural() {
		return isNatural;
	}

	/**
	 * Settor for isNatural flag.  Accidentals will be set to zero if isNatural is true.
	 * @param isNatural  New isNatural setting
	 */
	public void setIsNatural(boolean isNatural) {
		this.isNatural = isNatural;
		if (isNatural)
			accidental = 0;
	}

}
