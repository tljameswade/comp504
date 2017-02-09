package model.Strategy;

import model.Ball;
import model.IBallCmd;
import model.IInteractStrategy;
import model.IUpdateStrategy;
import model.MultiInteractStrategy;
import util.IDispatcher;

/**
 * The eatStrategy eats a collide ball and grows bigger
 * Interaction criteria is when balls collide
 * @author Suozhi Qi, Zhaohan Jia
 *
 * @param <TDispMsg> The generic type of the parameter
 */
public class EatStrategy<TDispMsg> implements IUpdateStrategy<IBallCmd> {

	/**
	 * If the ball interact with a collide ball, it will grow bigger and
	 * the target ball will disappear
	 */
	@Override
	public void init(Ball context) {
		context.setInteractStrategy(new MultiInteractStrategy(context.getInteractStrategy(), new IInteractStrategy() {
			public void interact(Ball context, Ball target, IDispatcher<IBallCmd> disp) {
				double contextMass = context.getRadius() * context.getRadius();
				double targetMass = target.getRadius() * target.getRadius();
				double newMass = contextMass + targetMass;
				context.setRadius((int) Math.sqrt(newMass));
				disp.deleteObserver(target);
			}
		}));
	}

	/**
	 * No-op
	 */
	@Override
	public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {
	}

}
