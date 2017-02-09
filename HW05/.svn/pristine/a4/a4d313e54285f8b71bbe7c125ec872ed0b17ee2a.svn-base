package model.paint.shape;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

/**
 * Concrete IShapeFactory that provides the invariant behavior to instantiate a 
 * Shape that is a Polygon. This class can be instantiated and used simply by 
 * supplying the desired points in its constructor, or sub-classed an the constructor overridden.
 * @author Suozhi Qi, Zhou Liu
 *
 */
public class PolygonFactory implements IShapeFactory {

	/**
	 * The AffineTransform used for internal calculations
	 */
	private AffineTransform at;

	/**
	 * The Polygon shape to use as the prototype.
	 */
	private Polygon poly = new Polygon();

	/**
	 * Scale factor that scales the integer Point-defined Polygon to a 
	 * unit size, which requires doubles.
	 */
	private double scaleFactor = 1.0;

	/**
	 * Constructor that uses an externally defined AffineTransform for 
	 * internal use plus takes the defining points of the prototype Polygon 
	 * and a scale factor to scale the given points to the desired unit size.
	 * @param at The AffineTransform used for internal calculations
	 * @param scaleFactor Scale factor that scales the integer Point-defined Polygon to a unit size, 
	 * which requires doubles. The value of scaleFactor is The ratio of the 
	 * desired unit size to the defined size of the prototype Polygon.
	 * @param pts the locations of the vertices of the polygon
	 */
	public PolygonFactory(AffineTransform at, double scaleFactor, Point... pts) {
		this.at = at;
		this.scaleFactor = scaleFactor;
		for (Point points : pts) {
			poly.addPoint(points.x, points.y);
		}
	}

	/**
	 * Instantiates a Shape object that is the prototype Polygon 
	 * translated by the given (x, y) point and scaled by given (xScale, yScale) 
	 * factor times the internal scaleFactor. Note that this method does *not* return a 
	 * Polygon object, but rather a abstract Shape object. The return value *cannot* be 
	 * cast to Polygon.
	 * @param x x-coordinate of the center of the resultant Polygon
	 * @param y y-coordinate of the center of the resultant Polygon
	 * @param xScale The x-dimension of the polygon, usually the x-radius.
	 * @param yScale The y-dimension of the polygon, usually the y-radius.
	 */
	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		at.setToTranslation(x, y);
		at.scale(xScale * scaleFactor, yScale * scaleFactor);
		return at.createTransformedShape(poly);
	}

}
