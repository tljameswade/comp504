package model.paint.strategy;

import java.awt.Point;
import java.awt.geom.AffineTransform;

import model.paint.ShapePaintStrategy;
import model.paint.shape.PolygonFactory;

/**
 * PaintStrategy that paints a polygon
 * @author Suozhi Qi, Zhou Liu
 *
 */
public class PolygonPaintStrategy extends ShapePaintStrategy {

	/**
	 * The polygon paintstrategy constructor
	 */
	public PolygonPaintStrategy() {
		this(new AffineTransform());
	}

	/**
	 * The constructor that takes an affine transform as the parameter
	 * @param at the affine transform instance
	 */
	public PolygonPaintStrategy(AffineTransform at) {
		super(at, new PolygonFactory(new AffineTransform(), 1.0, new Point(1, 1), new Point(-2, -1), new Point(2, -1),
				new Point(-1, 1), new Point(0, -3)).makeShape(0, 0, 1, 1));
	}
}
