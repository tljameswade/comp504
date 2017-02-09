package provided.player;

/**
 * Command that is used to notify a user that a track has stopped playing.
 * @author swong
 *
 */
public interface ISequencePlayerStatus {
	/**
	 * Called when a track stops playing.
	 */
	public void finished();

	/**
	 * Null Object instance that simply prints a status message to the console.
	 */
	public static final ISequencePlayerStatus NULL = new ISequencePlayerStatus() {

		/**
		 * Prints "ISequencePlayerStatus.NULL.finished(): Track finished playing!" to console.
		 */
		@Override
		public void finished() {
			System.out
					.println("ISequencePlayerStatus.NULL.finished(): Track finished playing!");
		}

	};
}