package test;

public class Test1 {
	
	public static void main (String[] args) {
		SequencePlayer sp = new SequencePlayer(16, 0);  // 16 = ticks per QUARTER note
		sp.setTicksPerDefaultNote(16);  // 16 = ticks per DEFAULT note
		sp.setTempo(140);   // 140 quarter notes (beats) per minute
		Note g = new Note('G', 0, 0, 1.0, false);
		sp.addNote(g, 10);   // 10 = start tick of note.  Tick of next note is returned.
		sp.play(ISequencePlayerStatus.NULL);   // No-op end-of-song cmd supplied.
	}

}
