package model.Strategy;

import model.Ball;
import model.IBallCmd;
import model.IInteractStrategy;
import model.IUpdateStrategy;
import model.MultiInteractStrategy;
import util.IDispatcher;

/**
 * This strategy "kills" a collide ball
 * Interact criteria is when balls collide
 * @author Suozhi Qi, Zhaohan Jia
 *
 * @param <TDispMsg> The generic type of the parameter
 */
public class KillStrategy<TDispMsg> implements IUpdateStrategy<IBallCmd> {

	/**
	 * Interaction of the ball with a collide ball makes the collide ball
	 * disappear
	 */
	@Override
	public void init(Ball context) {
		context.setInteractStrategy(new MultiInteractStrategy(context.getInteractStrategy(), new IInteractStrategy() {
			public void interact(Ball context, Ball target, IDispatcher<IBallCmd> disp) {
				disp.deleteObserver(target);
			}
		}));
	}

	@Override
	public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {
	}
}
