package model.Strategy;

import model.Ball;
import model.IUpdateStrategy;
import util.IDispatcher;

/**
 * this class will make a SwitcherStrategy ball
 * @author SuozhiQi,HongyangZhang
 *
 */
public class SwitcherStrategy<TDispMsg> implements IUpdateStrategy<TDispMsg> {

	/**
	 * The strategy to switch to
	 */
	public IUpdateStrategy<TDispMsg> Switch;

	/**
	 * The default ball would implement a straight strategy
	 */
	public SwitcherStrategy() {
		this.Switch = new StraightStrategy<TDispMsg>();
	}

	/**
	* Set the strategy of the ball to the new switcherstrategy
	* @param Switch the new strategy that the ball will switch to
	*/
	public void setStrategy(IUpdateStrategy<TDispMsg> Switch) {
		this.Switch = Switch;
	}

	@Override
	public void init(Ball context) {
	}

	/**
	 * Update the strategy the ball is using
	 * @param context the ball to update strategy
	 */
	@Override
	public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
		Switch.updateState(context, dispatcher);
	}

}
