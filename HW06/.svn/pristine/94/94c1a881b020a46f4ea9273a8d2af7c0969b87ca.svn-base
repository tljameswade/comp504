/**
 * 
 */
package view;

/**
 * The model adapter
 * @param <TInstrument> The instrument used to play music 
 * @author Qianyi Wu, Suozhi Qi
 * @version 1.0
 *
 */
public interface IModelAdapter<TInstrument> {

	/**
	 * @param filename string that is the name of the song
	 */
	void Open(String filename);

	/**
	 * The parse method
	 */
	void Parse();

	/**
	 * @param instrument The instrument used to play the song
	 * 
	 */
	void Play(TInstrument instrument);

	/**
	 * Stop the music
	 */
	void Stop();

	/**
	 * null constructor
	 */
	public static final IModelAdapter<?> NULL_OBJ = new IModelAdapter<Object>() {

		@Override
		public void Open(String filename) {
			// TODO Auto-generated method stub

		}

		@Override
		public void Parse() {
			// TODO Auto-generated method stub

		}

		@Override
		public void Play(Object instrument) {
			// TODO Auto-generated method stub

		}

		@Override
		public void Stop() {
			// TODO Auto-generated method stub

		}
	};

}
