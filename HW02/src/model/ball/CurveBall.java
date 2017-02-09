package model.ball;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

/**
 * The CurveBall concrete subclass
 * @author Suozhi Qi, Xiang Li
 * @version 1.0
 */
public class CurveBall extends ABall {

	/**
	 * The constructor inherited from the super class
	 * @param StartLoc The starting location of the ball
	 * @param startRadius The starting radius of the ball
	 * @param startVel The starting velocity of the ball
	 * @param startColor The starting color of the ball
	 * @param theCanvas The canvas
	 */
	public CurveBall(Point StartLoc, int startRadius, Point startVel, Color startColor, Component theCanvas) {
		super(StartLoc, startRadius, startVel, startColor, theCanvas);
	}

	/**
	 * The method to change the direction of movement of 
	 * the ball as the distinct behavior in the CurveBall
	 * concrete subclass
	 */
	public void differentBalls() {
		int Vx = velocity.x;
		int Vy = velocity.y;
		velocity.x = (int) Math.round((Math.cos(Math.PI / 20) * Vx - Math.sin(Math.PI / 20) * Vy));
		velocity.y = (int) Math.round(Math.cos(Math.PI / 20) * Vy + Math.sin(Math.PI / 20) * Vx);
	}
}
