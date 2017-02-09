package model.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import model.Ball;

/**
 * Concrete class that defines invariant painting behaviors to paint 
 * Shape objects for all its subclasses. 
 * Note: This cannot be directly instantiated by the BallWorld system 
 * because it lacks a no-parameter constructor.
 * @author Suozhi Qi, Zhou Liu
 *
 */
public class ShapePaintStrategy extends APaintStrategy {

	/**
	 * The Shape to be painted
	 */
	private Shape shape;

	/**
	 * Constructor that allocates a new AffineTransform for internal use.
	 * @param shape The Shape to be painted
	 */
	public ShapePaintStrategy(Shape shape) {
		this(new AffineTransform(), shape);
	}

	/**
	 * Constructor that uses a supplied AffineTransform for internal use.
	 * @param at the supplied AffineTranfrom instance
	 * @param shape The Shape to be painted
	 */
	public ShapePaintStrategy(AffineTransform at, Shape shape) {
		super(at);
		this.shape = shape;
	}

	/**
	 * Paints the shape on the given Graphics context using the supplied AffineTransform.
	 */
	@Override
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		((Graphics2D) g).fill(at.createTransformedShape(shape));
	}

}
