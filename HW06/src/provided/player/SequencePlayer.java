package provided.player;

import javax.sound.midi.*;

import provided.music.Note;

/**
 * Sequence Player to create and play MIDI music sequences.
 */
public class SequencePlayer {
	/**
	 * End of track marker
	 */
	public static final int END_OF_TRACK = 47;

	/**
	 * Music sequence
	 */
	private Sequence _sequence;

	/**
	 * Track within music sequence
	 */
	private Track _track;

	/**
	 * Tempo (beats per minute)
	 */
	private int _bpm;

	/**
	 * Default note duration
	 */
	private int _ticksPerDefaultNote;

	/**
	 * Definition of a tick
	 */
	private int _ticksPerQuarterNote;

	/**
	 * MIDI Instrument
	 */
	private int _instrument;

	/**
	 * MIDI sequencer object
	 */
	private Sequencer _sequencer;

	/**
	 *  MIDI synthesizer object
	 */
	private Synthesizer _synthesizer;

	/**
	 * Create a new SequencePlayer to create and play music.
	 * 
	 * @param  ticksPerQuarterNote      - tick definition
	 * @param  instrument               - MIDI instrument
	 */
	public SequencePlayer(int ticksPerQuarterNote, int instrument) {
		init(ticksPerQuarterNote, instrument);
	}

	/**
	 * Initialize the SequencePlayer as per the supplied ticksPerQuarterNote and instrument
	 * this SequencePlayer was instantiated with.   This method is called by the constructor to 
	 * initialize the player upon instantiation and can be called again to reinitialize the 
	 * SequencePlayer.
	 * 
	 * @param  ticksPerQuarterNote      - tick definition
	 * @param  instrument               - MIDI instrument
	 * @return                         - true if properly initialized, false otherwise
	 */
	public boolean init(int ticksPerQuarterNote, int instrument) {
		_ticksPerQuarterNote = ticksPerQuarterNote;
		_instrument = instrument;
		_bpm = 0;
		_ticksPerDefaultNote = _ticksPerQuarterNote;

		_sequencer = null;
		_synthesizer = null;
		_track = null;

		try {
			_sequencer = MidiSystem.getSequencer();
			_synthesizer = MidiSystem.getSynthesizer();

			_sequence = new Sequence(Sequence.PPQ, _ticksPerQuarterNote);
			_track = _sequence.createTrack();

			ShortMessage sm = new ShortMessage();
			sm.setMessage(ShortMessage.PROGRAM_CHANGE, 0, _instrument, 0);
			_track.add(new MidiEvent(sm, 0));
		} catch (MidiUnavailableException e) {
			System.out
					.println("MIDI Unavailable, SequencePlayer not initialized.");
			return false;
		} catch (InvalidMidiDataException e) {
			System.out.println("Invalid MIDI data.");
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Half note steps from C in an octave
	 */
	private static final int[] _offsets = { // add these amounts to the base value
	// A   B  C  D  E  F  G
			9, 11, 0, 2, 4, 5, 7 };

	/**
	 * Add a note to the MIDI sequence with a default velocity of 64 for middle volume
	 * 
	 * @param note     - the note to schedule in the sequence
	 * @param start    - the tick at which this note should start playing
	 * @return         - the tick at which this note stops playing
	 */
	public int addNote(Note note, int start) {
		return addNote(note, start, 64);
	}

	/**
	 * Add a note to the MIDI sequence.
	 * 
	 * @param note     - the note to schedule in the sequence
	 * @param start    - the tick at which this note should start playing
	 * @param velocity - the volume (0-127)
	 * @return         - the tick at which this note stops playing
	 */
	public int addNote(Note note, int start, int velocity) {
		if (_track == null) {
			return -1;
		}

		int duration = (int) Math.round(note.getDuration()
				* _ticksPerDefaultNote);
		if ('Z' == note.getName()) {
			// Rest - just return end tick count
			return start + duration;
		}

		int key = 60; // start at middle C
		key += note.getOctave() * 12;
		key += _offsets[note.getName() - 'A'];
		key += note.getAccidental();

		ShortMessage on;
		ShortMessage off;
		try {
			on = new ShortMessage();
			on.setMessage(ShortMessage.NOTE_ON, 0, key, velocity);
			off = new ShortMessage();
			off.setMessage(ShortMessage.NOTE_OFF, 0, key, velocity);
		} catch (InvalidMidiDataException e) {
			System.out.println("Invalid MIDI Data, note not added (" + note
					+ ", " + start + ").");
			// e.printStackTrace();
			return start;
		}

		_track.add(new MidiEvent(on, start));
		_track.add(new MidiEvent(off, start + duration));
		return start + duration;
	}

	/**
	 * Play the created Sequence.
	 * @param statusCmd The finished() method of this command is called when the track finishes being played, i.e. a normal termination.  There is no notification if play is forcibly stopped.
	 */
	public void play(final ISequencePlayerStatus statusCmd) {
		try {
			_sequencer.open();
			_synthesizer.open();

			_sequencer.getTransmitter().setReceiver(_synthesizer.getReceiver());

			// Specify the sequence to play, and the tempo to play it at
			_sequencer.setSequence(_sequence);
			_sequencer.setTempoInBPM(_bpm);

			// Let us know when it is done playing
			_sequencer.addMetaEventListener(new MetaEventListener() {
				public void meta(MetaMessage m) {
					// A message of this type is automatically sent
					// when we reach the end of the track
					if (m.getType() == END_OF_TRACK) {
						System.out.println("Finished Playing");
						stop();
						statusCmd.finished();
					}
				}
			});

			// And start playing now.
			_sequencer.start();
		} catch (MidiUnavailableException e) {
			System.out.println("Unable to open MIDI synthesizer.");
			// e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			System.out.println("Unable to play sequence.");
			// e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Stop playing music and close resources.
	 */
	public void stop() {
		_sequencer.close();
		_synthesizer.close();
	}

	/**
	 * Accessor to get the current ticks per default note
	 * @return the _ticksPerDefaultNote
	 */
	public int getTicksPerDefaultNote() {
		return _ticksPerDefaultNote;
	}

	/**
	 * Accessor to set the current ticks per default note
	 * @param ticksPerDefaultNote the _ticksPerDefaultNote to set
	 */
	public void setTicksPerDefaultNote(int ticksPerDefaultNote) {
		_ticksPerDefaultNote = ticksPerDefaultNote;
	}

	/**
	 * Accessor for the current tempo
	 * @return the tempo (in beats per minute)
	 */
	public int getTempo() {
		return _bpm;
	}

	/**
	 * Set the tempo - can't change the tempo in the middle of a sequence,
	 * the last tempo set will be used for the whole sequence.
	 * 
	 * @param bpm - beats per minute
	 */
	public void setTempo(int bpm) {
		_bpm = bpm;
	}

	/**
	 * Accessor for the current number of ticks per quarter note.
	 * @return the number of ticks per quarter note
	 */
	public int getTicksPerQuarterNote() {
		return _ticksPerQuarterNote;
	}

}
