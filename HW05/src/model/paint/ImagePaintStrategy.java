package model.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

import model.Ball;

/**
 * Paint strategy that paints an image from a file, scaled to the host Ball's radius. 
 * Note that this class cannot be used by the BallWar system directly as it is lacking 
 * a no-parameter constructor.
 * @author Suozhi Qi, Zhou Liu
 *
 */
public class ImagePaintStrategy extends APaintStrategy {

	/**
	 * The percentage of the average of the width and height of the image 
	 * that defines a unit radius for the image.
	 */
	private double fillFactor = 1.0;

	/**
	 * The image to paint
	 */
	private Image image;

	/**
	 * ImageObserver needed for some image operations
	 */
	private ImageObserver imageObs;

	/**
	 * A local affine transform used to transform the image into its unit size and location.
	 */
	AffineTransform localAT = new AffineTransform();

	/**
	 * Ratio of the unit radius circle to the effective radius size of the image.
	 */
	private double scaleFactor = 1.0;

	AffineTransform tempAT = new AffineTransform();

	/**
	 * Constructor that takes an external AffineTransform, 
	 * the filename of the image to paint and a fill factor of the image.
	 * @param at the affine transform instance
	 * @param filename the file name of the image to paint
	 * @param fillFactor the fill factor of the image
	 */
	public ImagePaintStrategy(AffineTransform at, String filename, double fillFactor) {
		super(at);
		this.fillFactor = fillFactor;
		try {
			image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(filename));
		} catch (Exception e) {
			System.err.println("ImagePaintStrategy: Error reading file: " + filename + "\n" + e);
		}
	}

	/**
	 * Constructor that takes the image filename and fill factor.
	 * @param filename the file name of the image to paint
	 * @param fillFactor the fill factor of the image
	 */
	public ImagePaintStrategy(String filename, double fillFactor) {
		this(new AffineTransform(), filename, fillFactor);
	}

	/**
	 * Initializes the internal ImageObserver reference from the host Ball 
	 * Also calculates the net scale factor for the image.
	 */
	@Override
	public void init(Ball host) {
		imageObs = host.getCanvas();
		MediaTracker mt = new MediaTracker(host.getCanvas());
		mt.addImage(image, 1);
		try {
			mt.waitForAll();
		} catch (Exception e) {
			System.out.println("ImagePaintStrategy.init(): Error waiting for image.  Exception = " + e);
		}

		scaleFactor = 2.0 / (fillFactor * (image.getWidth(imageObs) + image.getHeight(imageObs)) / 2.0);
	}

	/**
	 * Draws the image on the given Graphics context using the given affine transform 
	 * in combination with the local affine transform.
	 */
	@Override
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		localAT.setToScale(scaleFactor, scaleFactor);
		localAT.translate(-image.getWidth(imageObs) / 2.0, -image.getHeight(imageObs) / 2.0);
		localAT.preConcatenate(at);
		((Graphics2D) g).drawImage(image, localAT, imageObs);
	}

}
