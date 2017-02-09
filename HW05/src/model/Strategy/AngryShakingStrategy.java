package model.Strategy;

import java.awt.Color;

import model.Ball;
import model.IUpdateStrategy;
import util.IDispatcher;
import util.Randomizer;

/**
 * this class will create a AngryShakingball on the basis of a default one
 * @author SuozhiQi,HongyangZhang
 *
 */
public class AngryShakingStrategy<TDispMsg> implements IUpdateStrategy<TDispMsg> {

	/**
	 * The count used to slower the speed of changing radius of the ball
	 */
	int count = 0;

	@Override
	public void init(Ball context) {
	}

	/**
	 * In this update method, the velocity in the x-axis is set to randomize
	 * from -5 to 5 and the velocity in the y-axis is set to 0 to mimic shaking.
	 * The color of the ball is set to red to mimic angry face.
	 * When the count goes up to 100, the radius will automatically increase by 3.
	 */
	@Override
	public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
		context.getVelocity().x = Randomizer.Singleton.randomInt(-5, 5);
		context.getVelocity().y = 0;
		context.setColor(Color.red);

		count++;
		if (count == 100) {
			context.setRadius(3 + context.getRadius());
			count = 0;
		}
	}

}
