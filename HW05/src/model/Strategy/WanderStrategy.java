package model.Strategy;

import model.Ball;
import model.IUpdateStrategy;
import util.IDispatcher;
import util.Randomizer;

/**
 * this class implements IUpdateStrategy and create a wander ball
 * @author SuozhiQi,HongyangZhang
 *
 */
public class WanderStrategy<TDispMsg> implements IUpdateStrategy<TDispMsg> {

	@Override
	public void init(Ball context) {
	}

	/**
	 * Update the state of the ball to have randomized the velocity
	 * at the x-axis and y-axis to mimic wandering
	 */
	@Override
	public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
		context.getVelocity().x = Randomizer.Singleton.randomInt(-10, 10);
		context.getVelocity().y = Randomizer.Singleton.randomInt(-10, 10);
	}

}
