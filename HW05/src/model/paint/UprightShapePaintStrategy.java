package model.paint;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import model.Ball;

/**
 * A shape painting strategy that adds a paintCfg override that keeps 
 * the shape upright no matter which direction it is going.
 * @author Suozhi Qi, Zhou Liu
 *
 */
public class UprightShapePaintStrategy extends ShapePaintStrategy {

	/**
	 * Constructor that takes both a Shape and an affine transform
	 * @param at the supplied affine transform
	 * @param shape the provided shape
	 */
	public UprightShapePaintStrategy(AffineTransform at, Shape shape) {
		super(at, shape);
	}

	/**
	 * Constructor that takes a Shape, generates its own affine transform.
	 * @param shape The supplied shape
	 */
	public UprightShapePaintStrategy(Shape shape) {
		this(new AffineTransform(), shape);
	}

	/**
	 * Override paintCfg to add the transform needed to keep the shape upright at all times.
	 */
	@Override
	public void paintCfg(Graphics g, Ball host) {
		super.paintCfg(g, host);
		if (Math.abs(Math.atan2(host.getVelocity().y, host.getVelocity().x)) > Math.PI / 2.0) {
			at.scale(1.0, -1.0);
		}
	}

}
