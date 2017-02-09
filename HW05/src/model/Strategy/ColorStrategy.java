package model.Strategy;

import model.Ball;
import model.IUpdateStrategy;
import util.IDispatcher;
import util.Randomizer;

/**
 * this class will create a colorball on the basis of a default one
 * @author Suozhi Qi, Zhaohan Jia
 *
 */
public class ColorStrategy<TDispMsg> implements IUpdateStrategy<TDispMsg> {

	@Override
	public void init(Ball context) {
	}

	/**
	 * This method will randomize the color of the ball
	 */
	@Override
	public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
		context.setColor(Randomizer.Singleton.randomColor());
	}
}
