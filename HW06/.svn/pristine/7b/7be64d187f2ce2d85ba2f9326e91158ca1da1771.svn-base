/**
 * 
 */
package model;

import model.visitor.PlayMusicVisitor;
import model.visitor.ToStringVisitor;
import provided.abcParser.ABCParser;

import provided.music.IPhrase;
import provided.music.IPhraseVisitor;
import provided.music.NESeqList;
import provided.player.ISequencePlayerStatus;
import provided.player.SequencePlayer;
import provided.util.ABCInstrument;
import provided.util.ABCUtil;

/**
 * The model for the music player
 * @author Qianyi Wu, Suozhi Qi
 * @version 1.0
 * 
 */
public class MusicPlayerModel {

	/**
	 * The host, which is an IPhrase object
	 */
	private IPhrase host;

	/**
	 * The view adapter
	 */
	private IViewAdapter adptr = IViewAdapter.NULL_OBJ;

	/**
	 * The sequence player that we use to play music
	 */
	private SequencePlayer player;

	/**
	 * Default constructor for the model
	 */
	public MusicPlayerModel() {

	}

	/**
	 * Constructor of the model
	 * @param vadptr The view adapter
	 */
	public MusicPlayerModel(IViewAdapter vadptr) {
		adptr = vadptr;
	}

	/**
	 * Load file method that takes in the name of the file
	 * @param filename the name of the song to be played
	 */
	public void LoadFile(String filename) {
		adptr.DisplayFileContent(ABCUtil.Singleton.getFileContents(filename));
		host = new ABCParser(filename).parse();
	}

	/**
	 * The method that gets the host using IPhrase as the type
	 * @return Host the host is an IPhrase object
	 */
	public IPhrase getHost() {
		return host;
	}

	/**
	 * The play method using selected instrument
	 * @param instrument The instrument that we use to play the music
	 */
	public void Play(ABCInstrument instrument) {
		player = new SequencePlayer(16, instrument.getValue());
		player.setTempo(120); //default
		host.execute(new PlayMusicVisitor(), player, 0);
		player.play(ISequencePlayerStatus.NULL);
	}

	/**
	 * The parse string method
	 * @return String The result of parsing
	 */
	public String getParsed() {
		IPhraseVisitor v = new ToStringVisitor();
		NESeqList.setToStringAlgo(v);

		return host.toString();
	}

	/**
	 * The start method
	 */
	public void start() {
	}

	/**
	 * The stop method to stop the player from playing the music
	 */
	public void stop() {
		player.stop();
	}
}
