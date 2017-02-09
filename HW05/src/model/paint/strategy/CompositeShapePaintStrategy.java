package model.paint.strategy;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.Ball;
import model.paint.MultiPaintStrategy;

/**
 * A paint strategy that paints a human shape composite shape
 * @author Suozhi Qi, Zhou Liu
 *
 */
public class CompositeShapePaintStrategy extends MultiPaintStrategy {

	/**
	 * pass the AffineTransform in the constructor 
	 */
	public CompositeShapePaintStrategy() {
		this(new AffineTransform());
	}

	/**
	 * The constructor that takes the affine transform instance as the parameter
	 * @param at the affine transform instance
	 */
	public CompositeShapePaintStrategy(AffineTransform at) {
		super(new EllipsePaintStrategy(new AffineTransform(), 0, -2, 1, 1),
				new RectanglePaintStrategy(new AffineTransform(), 0, -0.5, 1.5, 0.5),
				new RectanglePaintStrategy(new AffineTransform(), -1, 1.5, 0.5, 1.5),
				new RectanglePaintStrategy(new AffineTransform(), 1, 1.5, 0.5, 1.5));
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