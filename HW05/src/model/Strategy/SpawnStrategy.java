package model.Strategy;

import java.awt.Point;

import model.Ball;
import model.IBallCmd;
import model.IUpdateStrategy;
import util.IDispatcher;

/**
 * The Spawn Strategy of the ball, which spawns a new ball in sometime when collides.
 * 
 * @author Suozhi Qi
 * @author Zhaohan Jia
 * @version 1.0
 * @since 1.0
 * 
 * @param <TDispMsg> The dispatcher message.
 */
public class SpawnStrategy<TDispMsg> implements IUpdateStrategy<IBallCmd> {

	/**
	 * Tick counter that counts out the delay before another ball can be spawned.
	 */
	private int count = 0;

	/**
	 * Tick delay which increases at each spawn to keep total spawn rate from exponentially exploding.
	 */
	private int delay = 100;

	/**
	 * The init method no-op.
	 * @param context The context ball.
	 */
	@Override
	public void init(Ball context) {
		//No-op
	}

	/**
	 * The update status method of SpawnStrategy.
	 * @param ball Current ball with the context.
	 * @param dispatcher The dispatcher is used.
	 */
	@Override
	public void updateState(Ball ball, IDispatcher<IBallCmd> dispatcher) {
		if (delay < count++) {
			dispatcher.dispatch(new IBallCmd() {
				@Override
				public void apply(Ball other, IDispatcher<IBallCmd> dispatcher) {
					if (count != 0 && ball != other) {
						double distance = ball.getLocation().distance(other.getLocation());
						if (distance < other.getRadius() + ball.getRadius()) {
							dispatcher.addObserver(new Ball(new Point(ball.getLocation()), ball.getRadius(),
									new Point(-ball.getVelocity().x, -ball.getVelocity().y), ball.getColor(),
									ball.getCanvas(), new SpawnStrategy<IBallCmd>(), ball.getPaintStrategy()));
							count = 0;
							delay *= 5;
						}
					}
				}
			});
		}
	}
}