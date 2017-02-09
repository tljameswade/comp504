package model.Strategy;

import model.Ball;
import model.IUpdateStrategy;
import util.IDispatcher;
import util.Randomizer;

/**
 * this class will create a Breathingball on the basis of a default one
 * @author SuozhiQi,HongyangZhang
 *
 */
public class BreathingStrategy<TDispMsg> implements IUpdateStrategy<TDispMsg> {

	@Override
	public void init(Ball context) {
	}

	/**
	 * This method will update the radius of the ball to randomize
	 * from 5 to 15
	 */
	@Override
	public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
		context.setRadius(Randomizer.Singleton.randomInt(5, 15));
	}
}
