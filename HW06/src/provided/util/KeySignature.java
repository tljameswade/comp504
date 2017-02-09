package provided.util;

import java.util.Hashtable;
import java.util.Map;

import provided.music.Note;

/**
 * Utility class to simplify dealing with key signatures <br/>
 * Usage:<br/>
 * KeySignature keySig = new KeySignature(aKey); // aKey is a string representing a key, e.g. "C", "Bb", "Am"<br/>
 * Note adjustedNote = keySig.adjust(originalNote); // returns the Note object, adjusted so that it matches the key,
 * e.g for key of "D", an "F" --&gt; "F#".<br/>
 */
public class KeySignature {
	/**
	 * Half steps in a Major key
	 */
	private static final int[] _major = { 0, 2, 2, 1, 2, 2, 2 //, 1
	};

	/**
	 * Half steps in a Minor key
	 */
	private static final int[] _minor = { // Half steps in a Minor key
	0, 2, 1, 2, 2, 1, 2 //, 2
	};

	/**
	 * Utility class to keep track of the position of notes
	 */
	private class KeyNote {
		/**
		 * The letter of the note
		 */
		public char letter;
		/**
		 * Whether or not the note is accidental:  -1 = flat, 0 = natural, +1 = sharp
		 */
		public int accidental;

		/**
		 * Constructor for the class
		 * @param letter  The letter of the note
		 * @param accidental  Whether or not the note is accidental:  -1 = flat, 0 = natural, +1 = sharp
		 */
		KeyNote(char letter, int accidental) {
			this.letter = letter;
			this.accidental = accidental;
		}

		public String toString() {
			return "" + letter + accidental;
		}
	}

	/**
	 * Map of sharps/flats in the key signature
	 */
	private Map<Character, Integer> keymap = new Hashtable<Character, Integer>();

	/**
	 * Ordering of notes (using only sharps)
	 */
	private KeyNote[] notes = { new KeyNote('A', 0), new KeyNote('A', 1),
			new KeyNote('B', 0), new KeyNote('C', 0), new KeyNote('C', 1),
			new KeyNote('D', 0), new KeyNote('D', 1), new KeyNote('E', 0),
			new KeyNote('F', 0), new KeyNote('F', 1), new KeyNote('G', 0),
			new KeyNote('G', 1) };

	/**
	 * Special case for F# and D#m
	 */
	private KeyNote[] fs_dsm = { new KeyNote('A', 1), new KeyNote('B', 0),
			new KeyNote('C', 1), new KeyNote('D', 1), new KeyNote('E', 1),
			new KeyNote('F', 1), new KeyNote('G', 1) };

	/**
	 * Generate a "Key" object that enables easy access to the key signature.
	 * 
	 * @param key        - String indicating key (i.e., "C" or "D#m")
	 */
	public KeySignature(String key) {
		// Get rid of spurious "maj" at the end, if it's there
		if (key.endsWith("maj")) {
			key = key.replace("maj", "");
		}

		// Special case keys with 6 sharps
		if ((key == "F#") || (key == "D#m")) {
			for (KeyNote n : fs_dsm) {
				keymap.put(n.letter, n.accidental);
			}
			return;
		}

		char keynote = key.charAt(0);
		int cur = -1;

		for (int i = 0; i < notes.length; i++) {
			if (notes[i].letter == keynote) {
				cur = i;
				break;
			}
		}

		if (cur == -1) {
			System.out.println("Invalid key: " + key);
			return;
		}

		int[] steps = _major;

		int idx = 1;
		while (idx < key.length()) {
			switch (key.charAt(idx)) {
			case '#':
				cur += 1;
				break;

			case 'b':
				cur -= 1;
				break;

			case 'm':
				steps = _minor;
				break;

			default:
				System.out.println("Invalid key: " + key);
				return;
			}
			idx++;
		}

		if (cur == -1) {
			cur = notes.length - 1;
		}

		for (int i : steps) {
			cur += i;
			if (cur >= notes.length) {
				cur -= notes.length;
			}
			KeyNote n = notes[cur];
			insert(n);
		}
	}

	/**
	 * Helper method to convert a note to its equivalent flat version.
	 * 
	 * @param n  - note letter
	 * @return   - equivalent flat version of note n
	 */
	private KeyNote convertflat(char n) {
		char next = (char) (n + 1);
		if (next == 'H') {
			next = 'A';
		}
		return new KeyNote(next, -1);
	}

	/**
	 * Helper method to insert the given note into the keymap.
	 * 
	 * @param n  - note to add to the map
	 */
	private void insert(KeyNote n) {
		if (!keymap.containsKey(n.letter)) {
			keymap.put(n.letter, n.accidental);
			return;
		}

		if (n.accidental == 1) {
			// Move sharp up to next flat
			insert(convertflat(n.letter));
			return;
		}

		if ((n.accidental == 0) || (n.accidental == -1)) {
			// Conflict, move higher note up to next flat
			int a = keymap.get(n.letter);
			a = Math.min(a, n.accidental);
			insert(convertflat(n.letter));
			keymap.put(n.letter, a);
			return;
		}

		// Shouldn't get here
		System.out.println("Should not reach here.");
	}

	/**
	 * Takes a note (with no accidentals) and returns the accidental for this
	 * key signature.
	 * 
	 * @param  note - in [A, B, C, D, E, F, G]
	 * @return accidental for the note in this key signature [-1, 0, 1]
	 */
	private int map(Character note) {
		if (keymap.containsKey(note)) {
			return (int) keymap.get(note);
		} else {
			return 0;
		}
	}

	/**
	 * Takes a note and returns the properly transposed note in this key signature
	 *
	 * @param n - note to be adjusted
	 * @return A new, adjusted Note
	 */
	public Note adjust(Note n) {
		if (n.getAccidental() != 0 && !n.getIsNatural()) {
			return n; // Accidentals set and is not natural
		} else if (n.getIsNatural()) {
			return n; // override key signature
		} else {
			// normal key signature mapping applies
			int accidental = map(n.getName());
			if (accidental == 0) {
				return n; // nothing to change
			}
			return new Note(n.getName(), n.getOctave(), accidental,
					n.getDuration(), false); // must be false isNatural
		}
	}

	/**
	 * Print the mapping for this key signature
	 */
	public void printmap() {
		System.out.println(keymap);
	}
}
