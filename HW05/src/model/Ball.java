package model;

import java.awt.Point;

import util.IDispatcher;
import util.IObserver;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

/**
 * The concrete ball class
 * @author Suozhi Qi, Zhaohan Jia
 * @version 1.0
 *
 */
public class Ball implements IObserver<IBallCmd> {
	/**
	 * The location of the ball
	 */
	private Point location;

	/**
	 * The radius of the ball
	 */
	private int radius;

	/**
	 * The velocity of the ball during moving, which is represented by a Point
	 */
	private Point velocity;

	/**
	 * The color of the ball
	 */
	private Color color;

	/**
	 * The canvas
	 */
	Component Canvas;

	/**
	 * The updatestrategy that the ball uses
	 */
	private IUpdateStrategy<IBallCmd> updatestrategy;

	/**
	 * The paintstrategy that the ball uses
	 */
	private IPaintStrategy paintStrategy;

	/**
	 * The interactStrategy that the ball uses
	 */
	public IInteractStrategy interactStrategy = IInteractStrategy.NULL_STRATEGY;

	/**
	 * The constructor of the ABall
	 * @param StartLoc The starting location of the ball
	 * @param startRadius The starting radius of the ball
	 * @param startVel The starting velocity of the ball
	 * @param startColor The starting color of the ball
	 * @param theCanvas The canvas
	 * @param updatestrategy The strategy that the ball will use
	 * @param paintStrategy The paintstrategy that the ball will use
	 */
	public Ball(Point StartLoc, int startRadius, Point startVel, Color startColor, Component theCanvas,
			IUpdateStrategy<IBallCmd> updatestrategy, IPaintStrategy paintStrategy) {
		this.setLocation(StartLoc);
		this.setRadius(startRadius);
		this.setVelocity(startVel);
		this.setColor(startColor);
		this.Canvas = theCanvas;
		this.setUpdateStrategy(updatestrategy);
		;
		this.setPaintStrategy(paintStrategy);
	}

	/**
	 * @return the location of the ball 
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * set the location of the ball
	 * @param location of the ball
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * 
	 * @return velocity of the ball
	 */
	public Point getVelocity() {
		return velocity;
	}

	/**
	 * Set the velocity of the ball
	 * @param velocity of the ball
	 */
	public void setVelocity(Point velocity) {
		this.velocity = velocity;
	}

	/**
	 * @return color of the ball
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * set the color of the ball
	 * @param color of the ball
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * get the radius of the ball
	 * @return radius of the ball
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * set the radius of the ball
	 * @param radius of the ball
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * set the canvas
	 * @param canvas The canvas
	 */
	public void setCanvas(Component canvas) {
		this.Canvas = canvas;
	}

	/**
	 * get the canvas
	 * @return canvas
	 */
	public Component getCanvas() {
		return Canvas;
	}

	/**
	 * get the paintstrategy
	 * @return paintStrategy paintstrategy of the ball
	 */
	public IPaintStrategy getPaintStrategy() {
		return paintStrategy;
	}

	/**
	 * Set the paintstrategy of the ball
	 * @param paintStrategy of the ball
	 */
	public void setPaintStrategy(IPaintStrategy paintStrategy) {
		this.paintStrategy = paintStrategy;
		this.paintStrategy.init(this);
	}

	/**
	 * Set the interact strategy of the ball
	 * @param interactStrategy of the ball
	 */
	public void setInteractStrategy(IInteractStrategy interactStrategy) {
		this.interactStrategy = interactStrategy;
	}

	/**
	 * The interactstrategy getter
	 * @return the interact strategy
	 */
	public IInteractStrategy getInteractStrategy() {
		return interactStrategy;
	}

	/**
	 * The IUpdateStrategy getter
	 * @return the updatestrategy
	 */
	public IUpdateStrategy<IBallCmd> getupdateStrategy() {
		return updatestrategy;
	}

	/**
	 * The IUpdatestrategy setter
	 * @param updateStrategy the updateStrategy
	 */
	public void setUpdateStrategy(IUpdateStrategy<IBallCmd> updateStrategy) {
		this.updatestrategy = updateStrategy;
		updateStrategy.init(this);
	}

	/**
	 * The update method called by the main ball Dispatcher to notify all the balls to perform the given command.
	 * The given command is executed.
	 * @param o The Dispatcher that sent the update request.
	 * @param cmd The IBallCmd that will be run.
	 */
	public void update(IDispatcher<IBallCmd> o, IBallCmd cmd) {
		cmd.apply(this, o);
	}

	/**
	 * This method paints the ball
	 * @param g The graphics object
	 */
	void paint(Graphics g) {
		paintStrategy.paint(g, this);
	}

	/**
	 * This method simulate the movement of the ball
	 */
	void move() {
		location.x += getVelocity().x;
		location.y += getVelocity().y;
	}

	/**
	 * Update the state of the ball.   Delegates to the update strategy.
	 * @param dispatcher The Dispatcher that sent the command that is calling this method.
	 */
	public void updateState(IDispatcher<IBallCmd> dispatcher) {
		updatestrategy.updateState(this, dispatcher); // update this ball's state using the strategy.		
	}

	/**
	 * The ball to interact with another ball
	 * @param context the target ball that "this" ball will interact with
	 * @param disp the dispatcher
	 */
	public void interactWith(Ball context, IDispatcher<IBallCmd> disp) {
		interactStrategy.interact(this, context, disp);
	}

	/**
	 * This method simulate the bouncing behavior of the ball
	 */
	void bounce() {
		if (location.x - getRadius() < 0) {
			getVelocity().x = -getVelocity().x;
			location.x = getRadius() * 2 - location.x;
		}

		if (location.y - getRadius() < 0) {
			getVelocity().y = -getVelocity().y;
			location.y = getRadius() * 2 - location.y;
		}

		if (location.x + getRadius() > Canvas.getWidth()) {
			getVelocity().x = -getVelocity().x;
			location.x = 2 * (Canvas.getWidth() - getRadius()) - location.x;
		}

		if (location.y + getRadius() > Canvas.getHeight()) {
			getVelocity().y = -getVelocity().y;
			location.y = 2 * (Canvas.getHeight() - getRadius()) - location.y;
		}
	}

}
