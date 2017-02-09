package model;

import util.IDispatcher;

/**
 * The strategy that runs when a Ball updates its state.
 * 
 * @author Suozhi Qi, Zhanhan Jia
 * 
 * @param TDispMsg The type of message that the supplied IDispatcher can send. 
 */
public interface IUpdateStrategy<TDispMsg> {
	/**
	 * Initializes the strategy.   Should be called every time the Ball sets a new strategy.
	 * @param context  The ball using this strategy.
	 */
	public void init(Ball context);

	/**
	 * Update the state of the context Ball.
	 * @param context  The context (host) Ball whose state is to be updated
	 * @param dispatcher  The Dispatcher who sent the command that is calling through to here.
	 */
	public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher);

	/**
	 * A factory for a typed null strategy object.<br>
	 * Usage: instantiate this factory class using the desired TDispMsg type and then call its make() method
	 * to create the correctly typed null strategy object. 
	 */
	public static final class NullFactory<TDispMsg> implements IStrategyFac<TDispMsg> {

		/**
		 * Returns a no-op null strategy
		 */
		@Override
		public IUpdateStrategy<TDispMsg> make() {
			return new IUpdateStrategy<TDispMsg>() {

				@Override
				/**
				 * No-op
				 * @param context Ignored
				 */
				public void init(Ball context) {
				}

				@Override
				/**
				 * No-op
				 * @param context Ignored
				 * @param dispatcher Ignored
				 */
				public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
				}
			};
		}
	}

}