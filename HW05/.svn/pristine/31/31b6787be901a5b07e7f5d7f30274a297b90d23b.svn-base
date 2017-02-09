package model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.Ball;
import model.IPaintStrategy;

/**
 * The top-level affine transform-based paint strategy that provides 
 * services for its subclasses, plus default behaviors and abstract behaviors.
 * @author Suozhi Qi, Zhou Liu
 *
 */
public abstract class APaintStrategy implements IPaintStrategy {

	/**
	 * The affine transform used by this paint strategy to translate, scale and rotate the image.
	 */
	protected AffineTransform at;

	/**
	 * Constructor that initializes the strategy with an affine transform
	 * @param at The affine transform instance
	 */
	public APaintStrategy(AffineTransform at) {
		this.at = at;
	}

	/**
	 * Protected accessor for the internal affine transform
	 * @return the internal affine transform
	 */
	AffineTransform getAT() {
		return at;
	}

	/**
	 * By default, do nothing for initialization.
	 */
	@Override
	public void init(Ball context) {
	}

	/**
	 * Paints on the given graphics context using the color, 
	 * scale and direction provided by the host.
	 */
	@Override
	public void paint(Graphics g, Ball host) {
		double scale = host.getRadius();
		at.setToTranslation(host.getLocation().x, host.getLocation().y);
		at.scale(scale, scale);
		at.rotate(Math.atan2(host.getVelocity().y, host.getVelocity().x));
		g.setColor(host.getColor());
		paintCfg(g, host);
		paintXfrm(g, host, at);
	}

	/**
	 * Used for doing additional configurations by a subclass.
	 * @param g the Graphics instance
	 * @param host the given ball
	 */
	public void paintCfg(Graphics g, Ball host) {
	};

	/**
	 * Paints the host onto the given Graphics context.
	 * @param g the Graphics instance
	 * @param host the given ball
	 * @param at the given affine transform
	 */
	public abstract void paintXfrm(Graphics g, Ball host, AffineTransform at);
}
