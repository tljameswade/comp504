package provided.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 * Singleton utility class that has methods for parsing,
 * reading file content and getting the list of instruments.
 *
 */
public class ABCUtil {

	/**
	 * Singleton instance of the class
	 */
	public static final ABCUtil Singleton = new ABCUtil();

	/**
	 * private constructor
	 */
	private ABCUtil() {
	}

	/**
	 * Return the evaluation of a string "a/b".
	 * 
	 * @param frac - a string containing a fraction
	 * @return     - the result of evaluating the fraction
	 */
	public double parseFraction(String frac) {
		String[] vals = frac.split("/");
		if (vals.length != 2) {
			throw new NumberFormatException(frac + " is not a fraction.");
		}
		double num = Double.parseDouble(vals[0]);
		double denom = Double.parseDouble(vals[1]);
		return num / denom;
	}

	/**
	 * Return the tempo given by the input string.  The returned value is in the 
	 * units of quarter notes per minute.
	 * 
	 * Accepts "bpm" (beats per default note) or "a/b=bpm" (beats per a/b note).
	 * 
	 * @param tempo                      - tempo represented as a string
	 * @param defaultNotesPerQuarterNote - default notes per quarter note 
	 * @return                           - the tempo indicated by the string
	 */
	public double parseTempo(String tempo, double defaultNotesPerQuarterNote) {
		String els[] = tempo.split("=");
		double bpm = 0;
		if (1 == els.length) {
			bpm = Double.parseDouble(els[0]) / defaultNotesPerQuarterNote;
		} else {
			double note = parseFraction(els[0]);
			bpm = Double.parseDouble(els[1]) / (0.25 / note);
		}
		return bpm;
	}

	/**
	 * Returns the contents of a text file, e.g. an abc file, as a single string.
	 * @param filename the name of the file with respect to the default package, where "/" refers to the default package, e.g. a file in the "data" package would "/data/myfile.abc"
	 * @return the contents of the file
	 */
	public String getFileContents(String filename) {
		String fileContents = "";

		try {
			InputStream is = this.getClass().getResource(filename).openStream();

			BufferedReader input = new BufferedReader(new InputStreamReader(is));

			try {
				// use buffering, reading one line at a time
				// FileReader always assumes default
				// encoding is OK!

				String line = null; // not declared within while loop

				/*
				 * readLine is a bit quirky : it returns the
				 * content of a line MINUS the newline. it
				 * returns null only for the END of the
				 * stream. it returns an empty String if two
				 * newlines appear in a row.
				 */
				while ((line = input.readLine()) != null) {
					fileContents += line + "\n";
				}
			} finally {
				input.close();
			}
		} catch (Exception e) {
			return "Error loading file: " + filename + "\n" + e + "\n";
		}

		return fileContents;
	}

	/**
	 * Get an array of all the available instruments as ABCInstruments 
	 * where the instrument's value is the MIDI instrument number
	 * 
	 * @return an array of ABCInstruments
	 */
	public ABCInstrument[] getInstruments() {
		try {
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			Instrument[] instruments = synth.getAvailableInstruments();
			ABCInstrument[] result = new ABCInstrument[instruments.length];
			for (int i = 0; i < instruments.length; i++) {
				result[i] = new ABCInstrument(instruments[i].getName(),
						instruments[i].getPatch().getProgram());
			}

			synth.close();
			return result;
		} catch (MidiUnavailableException e) {
			System.err.println("ABCUtil.getInstruments: " + e);
			return null;
		}
	}

}
