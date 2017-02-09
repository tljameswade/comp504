package model.visitor;

import provided.music.APhraseVisitor;
import provided.music.Chord;
import provided.music.Header;
import provided.music.IPhrase;
import provided.music.IPhraseVisitorCmd;
import provided.music.MTSeqList;
import provided.music.NESeqList;
import provided.music.Note;
import provided.music.Triplet;
import provided.player.SequencePlayer;
import provided.util.ABCUtil;
import provided.util.KeySignature;

/**
 * The visitor that is used to play music
 * @author Qianyi Wu, Suozhi Qi
 * @version 1.0
 * 
 */
public class PlayMusicVisitor extends APhraseVisitor {

	private KeySignature k = null;

	/**
	 * The visitor constructor that adds command to play different elements in a song(music)
	 */
	public PlayMusicVisitor() {

		this.addCmd(Note.ID, new IPhraseVisitorCmd() {
			public Object apply(String id, IPhrase host, Object... params) {
				SequencePlayer musicPlayer = (SequencePlayer) params[0];
				Note newNote = k.adjust(((Note) host));
				return musicPlayer.addNote(newNote, (int) params[1]);
			}
		});

		this.addCmd(Chord.ID, new IPhraseVisitorCmd() {
			public Object apply(String id, IPhrase host, Object... params) {
				Note[] notes = ((Chord) host).getNotes();
				int firstTick = (int) params[1];
				for (Note note : notes) {
					firstTick = (int) note.execute(PlayMusicVisitor.this, params);
				}
				return firstTick;
			}
		});

		this.addCmd(Triplet.ID, new IPhraseVisitorCmd() {
			public Object apply(String id, IPhrase host, Object... params) {
				Note[] notes = ((Triplet) host).getNotes();
				int firstTick = (int) params[1];
				for (Note note : notes) {
					note.setDuration(note.getDuration() * 2 / 3);
					firstTick = (int) note.execute(PlayMusicVisitor.this, params);
				}
				return firstTick;
			}
		});

		this.addCmd(MTSeqList.ID, new IPhraseVisitorCmd() {

			@Override
			public Object apply(String id, IPhrase host, Object... params) {
				return params[1];
			}
		});

		this.addCmd(NESeqList.ID, new IPhraseVisitorCmd() {

			@Override
			public Object apply(String id, IPhrase host, Object... params) {
				int firstTick = (int) ((NESeqList) host).getFirst().execute(PlayMusicVisitor.this, params);
				firstTick = (int) ((NESeqList) host).getRest().execute(PlayMusicVisitor.this, params[0], firstTick);
				return firstTick;
			}

		});

		String headerToDisregard = "ABCDEFGHIJMNOPRSTUVWXYZ";

		for (int i = 0; i < headerToDisregard.length(); i++) {
			this.addCmd("" + headerToDisregard.charAt(i), new IPhraseVisitorCmd() {

				@Override
				public Object apply(String id, IPhrase host, Object... params) {
					// TODO Auto-generated method stub
					return params[1];
				}
			});
		}

		this.addCmd("L", new IPhraseVisitorCmd() {

			@Override
			public Object apply(String id, IPhrase host, Object... params) {
				// TODO Auto-generated method stub
				double fraction = ABCUtil.Singleton.parseFraction(((Header) host).getValue());
				SequencePlayer player = (SequencePlayer) params[0];
				fraction *= player.getTicksPerQuarterNote() * 4;
				player.setTicksPerDefaultNote((int) fraction);
				System.out.println("bpm:" + player.getTempo());
				return params[1];
			}
		});

		this.addCmd("Q", new IPhraseVisitorCmd() {

			@Override
			public Object apply(String id, IPhrase host, Object... params) {
				// TODO Auto-generated method stub

				System.out.println(params[1]);
				SequencePlayer player = (SequencePlayer) params[0];
				double defaultNotesPerQuarterNote = player.getTicksPerQuarterNote() / player.getTicksPerDefaultNote();
				int bpm = (int) ABCUtil.Singleton.parseTempo(((Header) host).getValue(), defaultNotesPerQuarterNote);
				System.out.println("bpm: " + bpm);
				player.setTempo(bpm);
				return params[1];
			}
		});

		this.addCmd("K", new IPhraseVisitorCmd() {

			@Override
			public Object apply(String id, IPhrase host, Object... params) {
				// TODO Auto-generated method stub
				System.out.println(params[1]);
				k = new KeySignature(((Header) host).getValue());
				return params[1];
			}
		});
	}
}
