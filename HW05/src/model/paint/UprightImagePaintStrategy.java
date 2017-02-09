package model.paint;

import java.awt.Graphics;

import model.Ball;

/**
 * An image painting strategy that adds a paintCfg override that keeps 
 * the image upright no matter which way it is going.
 * @author Suozhi Qi, Zhou Liu
 *
 */
public class UprightImagePaintStrategy extends ImagePaintStrategy {

	/**
	 * Constructor for an image painting strategy
	 * @param filename the filename of the image
	 * @param fillFactor the fillfactor of the image
	 */
	public UprightImagePaintStrategy(String filename, double fillFactor) {
		super(filename, fillFactor);
	}

	/**
	 * Augments the inherited paint method to add flipping of the image to 
	 * keep it upright whenever the ball is going towards the left.
	 */
	public void paintCfg(Graphics g, Ball host) {
		super.paintCfg(g, host);
		if (Math.abs(Math.atan2(host.getVelocity().y, host.getVelocity().x)) > Math.PI / 2.0) {
			at.scale(1.0, -1.0);
		}
	}

}
