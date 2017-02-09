package model;

import util.IDispatcher;

/**
 * The multiInteractStrategy class combines two interact strategies
 * @author Suozhi Qi, Zhaohan Jia
 *
 */
public class MultiInteractStrategy implements IInteractStrategy {

	/**
	 * The first interact strategy
	 */
	private IInteractStrategy s1;

	/**
	 * The second interact strategy
	 */
	private IInteractStrategy s2;

	/**
	 * The constructor that initiates the class
	 * @param interact1 The first interact strategy
	 * @param interact2 The second interact strategy
	 */
	public MultiInteractStrategy(IInteractStrategy interact1, IInteractStrategy interact2) {
		this.s1 = interact1;
		this.s2 = interact2;
	}

	/**
	 * The interact method for the multi interact strategy
	 * Implements the interact methods for both interact strategies
	 */
	@Override
	public void interact(Ball context, Ball target, IDispatcher<IBallCmd> disp) {
		this.s1.interact(context, target, disp);
		this.s2.interact(context, target, disp);
	}

}
