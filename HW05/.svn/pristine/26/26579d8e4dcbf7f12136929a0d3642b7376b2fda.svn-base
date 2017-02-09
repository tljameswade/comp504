package model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.Ball;

/**
 * 	
 * A composite design pattern extension of APaintStrategy that paints a set of paint strategies.
 * @author Suozhi Qi, Zhou Liu
 *
 */
public class MultiPaintStrategy extends APaintStrategy {

	/**
	 * The set of paint strategies to paint
	 */
	private APaintStrategy[] pstrats;

	/**
	 * Constructor that takes the paint strategies that will part of the composite.
	 * @param at the affine transform instance
	 * @param pstrats The set of paint strategies to paint
	 */
	public MultiPaintStrategy(AffineTransform at, APaintStrategy... pstrats) {
		super(at);
		this.pstrats = pstrats;
	}

	/**
	 * Constructor that takes the paint strategies that will part of the composite.
	 * @param pstrats The set of paint strategies to paint
	 */
	public MultiPaintStrategy(APaintStrategy... pstrats) {
		this(new AffineTransform(), pstrats);
	}

	/**
	 * Delegates to all the IPaintStrategies in the composite.
	 */
	@Override
	public void init(Ball host) {
		for (APaintStrategy strategy : pstrats) {
			strategy.init(host);
		}
	}

	/**
	 * Delegates to all the IPaintStrategies in the composite.
	 */
	@Override
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		for (APaintStrategy strategy : pstrats) {
			strategy.paintXfrm(g, host, at);
		}
	}

	/**
	 * Used for doing additional configurations by a subclass.
	 * @param g the Graphics instance
	 * @param host the given ball
	 */
	@Override
	public void paintCfg(Graphics g, Ball host) {
	};

}
