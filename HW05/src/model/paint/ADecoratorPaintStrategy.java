package model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.Ball;

/**
 * Abstract class that provides default behavior for subclasses that will decorate 
 * another IPaintStrategy to add functionality to that strategy.
 * @author Suozhi Qi, Zhou Liu
 *
 */
public class ADecoratorPaintStrategy extends APaintStrategy {

	/**
	 * The "decoree" paint strategy whose methods are being 
	 * augmented by this decorator paint strategy.
	 */
	private APaintStrategy decoree;

	/**
	 * Constructor that takes the decoree paint strategy
	 * @param decoree The "decoree" paint strategy whose methods are being 
	 * augmented by this decorator paint strategy
	 */
	public ADecoratorPaintStrategy(APaintStrategy decoree) {
		super(new AffineTransform());
		this.decoree = decoree;
	}

	/**
	 * Default behavior is to simply delegate to the decoree's init method
	 */
	public void init(Ball host) {
		decoree.init(host);
	}

	/**
	 * Default behavior is to simply delegate to the decoree's paint method
	 */
	public void paint(Graphics g, Ball host) {
		decoree.paint(g, host);
	}

	/**
	 * Default behavior is to simply delegate to the decoree's paintXfrm method
	 */
	@Override
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		decoree.paintXfrm(g, host, at);
	}

}
