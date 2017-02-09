package model.ball;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

/**
 * The abstract ball class that will be extended to several concrete subclasses
 * @author Suozhi Qi, Xiang Li
 * @version 1.0
 *
 */
public abstract class ABall implements Observer {
	/**
	 * The location of the ball
	 */
	Point location;

	/**
	 * The radius of the ball
	 */
	int radius;

	/**
	 * The velocity of the ball during moving, which is represented by a Point
	 */
	Point velocity;

	/**
	 * The color of the ball
	 */
	Color color;

	/**
	 * The canvas
	 */
	Component Canvas;

	/**
	 * The constructor of the ABall
	 * @param StartLoc The starting location of the ball
	 * @param startRadius The starting radius of the ball
	 * @param startVel The starting velocity of the ball
	 * @param startColor The starting color of the ball
	 * @param theCanvas The canvas
	 */
	public ABall(Point StartLoc, int startRadius, Point startVel, Color startColor, Component theCanvas) {
		this.location = StartLoc;
		this.radius = startRadius;
		this.velocity = startVel;
		this.color = startColor;
		this.Canvas = theCanvas;
	}

	/**
	 * This method updates the condition of the ball based on their distinct sub-classes
	 */
	public void update(Observable o, Object arg) {
		paint((Graphics) arg);
		move();
		differentBalls();
		bounce();
	}

	/**
	 * This method paints the ball
	 * @param g The graphics object
	 */
	void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(location.x - radius, location.y - radius, 2 * radius, 2 * radius);
	}

	/**
	 * This method simulate the movement of the ball
	 */
	void move() {
		location.x += velocity.x;
		location.y += velocity.y;
	}

	/**
	 * This method calls different properties of the ball in different subclasses
	 * and can be overriden in concrete subclasses
	 */
	void differentBalls() {
	}

	/**
	 * This method simulate the bouncing behavior of the ball
	 */
	void bounce() {
		if (location.x - radius < 0) {
			velocity.x = -velocity.x;
			location.x = radius * 2 - location.x;
		}

		if (location.y - radius < 0) {
			velocity.y = -velocity.y;
			location.y = radius * 2 - location.y;
		}

		if (location.x + radius > Canvas.getWidth()) {
			velocity.x = -velocity.x;
			location.x = 2 * (Canvas.getWidth() - radius) - location.x;
		}

		if (location.y + radius > Canvas.getHeight()) {
			velocity.y = -velocity.y;
			location.y = 2 * (Canvas.getHeight() - radius) - location.y;
		}
	}

}
