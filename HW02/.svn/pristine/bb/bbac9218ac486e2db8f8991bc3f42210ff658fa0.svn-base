package model.ball;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import util.Randomizer;

/**
 * The BreathingBall concrete subclass
 * @author Suozhi Qi, Xiang Li
 * @version 1.0
 *
 */
public class BreathingBall extends ABall {

	/**
	 * The constructor inherited from the super class
	 * @param StartLoc The starting location of the ball
	 * @param startRadius The starting radius of the ball
	 * @param startVel The starting velocity of the ball
	 * @param startColor The starting color of the ball
	 * @param theCanvas The canvas
	 */
	public BreathingBall(Point StartLoc, int startRadius, Point startVel, Color startColor, Component theCanvas) {
		super(StartLoc, startRadius, startVel, startColor, theCanvas);
	}

	/**
	 * The method to change the radius of the ball
	 * as the distinct behavior in the BreathingBall
	 * concrete subclass
	 */
	public void differentBalls() {
		radius = Randomizer.Singleton.randomInt(5, 20);
	}
}
