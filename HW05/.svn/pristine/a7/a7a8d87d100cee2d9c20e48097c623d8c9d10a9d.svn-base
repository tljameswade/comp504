package model.Strategy;

import model.Ball;
import model.IUpdateStrategy;
import util.IDispatcher;

/**
 * this class will create a curve ball
 * the updateState function will change the velocity of a default ball
 * @author Suozhi Qi, Zhaohan Jia
 *
 */
public class CurveStrategy<TDispMsg> implements IUpdateStrategy<TDispMsg> {

	@Override
	public void init(Ball context) {
	}

	/**
	 * This method will make the ball turn by changing its velocity
	 * in x-axis and y-axis
	 */
	@Override
	public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
		int Vx = context.getVelocity().x;
		int Vy = context.getVelocity().y;
		context.getVelocity().x = (int) Math.round((Math.cos(Math.PI / 20) * Vx - Math.sin(Math.PI / 20) * Vy));
		context.getVelocity().y = (int) Math.round(Math.cos(Math.PI / 20) * Vy + Math.sin(Math.PI / 20) * Vx);
	}

}
