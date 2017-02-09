package model.Strategy;

import java.awt.Point;
import java.awt.geom.Point2D;

import model.Ball;
import model.IBallCmd;
import model.IUpdateStrategy;
import util.IDispatcher;

/**
 * The Collide Strategy of the ball, which collide with other balls.
 * 
 * @author Suozhi Qi
 * @author Zhaohan Jia
 * @version 1.0
 * @since 1.0
 * 
 * @param <TDispMsg> The dispatcher message.
 */
public class CollideStrategy<TDispMsg> implements IUpdateStrategy<IBallCmd> {

	/**
	 * The init method no-op.
	 * @param context The context ball.
	 */
	@Override
	public void init(Ball context) {
	}

	/**
	 * The update status method of CollideStrategy.
	 * @param ball Current ball with the context.
	 * @param dispatcher The dispatcher is used.
	 */
	@Override
	public void updateState(Ball ball, IDispatcher<IBallCmd> dispatcher) {
		dispatcher.dispatch(new IBallCmd() {
			public void apply(Ball other, IDispatcher<IBallCmd> dispatcher) {
				if (ball != other) {
					double distance = ball.getLocation().distance(other.getLocation());
					if (distance <= (ball.getRadius() + other.getRadius())) {
						double theReducedMass = reduceMass(ball.getRadius() * ball.getRadius(),
								other.getRadius() * other.getRadius());
						Point.Double theImpulse = impulse(ball.getLocation(), ball.getVelocity(), other.getLocation(),
								other.getVelocity(), theReducedMass, distance,
								ball.getRadius() + other.getRadius() - distance);
						updateCollision(ball, other, theImpulse.getX(), theImpulse.getY(), dispatcher);
						updateCollision(other, ball, -theImpulse.getX(), -theImpulse.getY(), dispatcher);
					}
				}
			}
		});
	}

	/**
	 * Returns the reduced mass of the two balls (m1*m2)/(m1+m2) Gives correct
	 * result if one of the balls has infinite mass.
	 * 
	 * @param mSource Mass of the source ball.
	 * @param mTarget Mass of the target ball.
	 * @return Reduced mass of the two balls.
	 */
	protected double reduceMass(double mSource, double mTarget) {
		if (mSource == Double.POSITIVE_INFINITY)
			return mTarget;
		if (mTarget == Double.POSITIVE_INFINITY)
			return mSource;
		else
			return (mSource * mTarget) / (mSource + mTarget);
	}

	/**
	 * The amount to add to the separation distance to insure that the two balls
	 * are beyond collision distance.
	 */
	private double Nudge = 1.1;

	/**
	 * Calculates the impulse (change in momentum) of the collision in the
	 * direction from the source to the target This method calculates the
	 * impulse on the source ball. The impulse on the target ball is the
	 * negative of the result. Also moves source ball out of collision range
	 * along normal direction. The change in velocity of the source ball is the
	 * impulse divided by the source's mass The change in velocity of the target
	 * ball is the negative of the impulse divided by the target's mass
	 * 
	 * Operational note: Even though theoretically, the difference in velocities
	 * of two balls should be co-linear with the normal line between them, the
	 * discrete nature of animations means that the point where collision is
	 * detected may not be at the same point as the theoretical contact point.
	 * This method calculates the rebound directions as if the two balls were
	 * the appropriate radii such that they had just contacted
	 * _at_the_point_of_collision_detection_. This may give slightly different
	 * rebound direction than one would calculate if they contacted at the
	 * theoretical point given by their actual radii.
	 * 
	 * @param lSource
	 *            Location of the source ball
	 * @param vSource
	 *            Velocity of the source ball
	 * @param lTarget
	 *            Location of the target ball
	 * @param vTarget
	 *            Velocity of the target ball
	 * @param reducedMass
	 *            Reduced mass of the two balls
	 * @param distance
	 *            Distance between the two balls.
	 * @param deltaR
	 *            The minimum allowed separation(sum of the ball radii) minus the actual separation(distance between ball centers). Should be a
	 *            positive value.  This is the amount of overlap of the balls as measured along the line between their centers.
	 * @return the impulse
	 */
	protected Point2D.Double impulse(Point lSource, Point vSource, Point lTarget, Point vTarget, double reducedMass,
			double distance, double deltaR) {
		// Calculate the normal vector, from source to target
		double nx = ((double) (lTarget.x - lSource.x)) / distance;
		double ny = ((double) (lTarget.y - lSource.y)) / distance;

		// delta velocity (speed, actually) in normal direction, source to
		// target
		double dvn = (vTarget.x - vSource.x) * nx + (vTarget.y - vSource.y) * ny;

		// move the source ball beyond collision range of the target ball, along
		// the normal direction.
		lSource.translate((int) Math.ceil(-nx * (Nudge * deltaR)), (int) Math.ceil(-ny * (Nudge * deltaR)));

		return new Point2D.Double(2.0 * reducedMass * dvn * nx, 2.0 * reducedMass * dvn * ny);
	}

	/**
	 * Updates the velocity of the source ball, given an impulse, then uses the
	 * context's interactWith method to determine the post collision behavior, from the context
	 * ball's perspective. The change in velocity is the impulse divided by the (source) ball's mass. To change
	 * the velocity of the target ball, switch the source and target input
	 * parameters and negate the impulse values.   This will also run the post collision behavior from 
	 * the other perspective.
	 * 
	 * @param context
	 *            The ball to update
	 * @param target
	 *            The ball being collided with
	 * @param impX
	 *            x-coordinate of the impulse
	 * @param impY
	 *            y-coordinate of the impulse
	 * @param dispatcher the dispatcher
	 */
	protected void updateCollision(Ball context, Ball target, double impX, double impY,
			IDispatcher<IBallCmd> dispatcher) {
		int mContext = context.getRadius() * context.getRadius();

		context.getVelocity().translate((int) Math.round(impX / mContext), (int) Math.round(impY / mContext));
		context.interactWith(target, dispatcher);
	}
}
