package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.ball.ABall;
import util.Dispatcher;
import util.Randomizer;

/**
 * The model class
 * @author Suozhi Qi, Xiang Li
 * @version 1.0
 *
 */

public class BallModel {

	/**
	 * Construct the model to view adapter
	 */
	private IModel2ViewAdapter _model2ViewAdpt = IModel2ViewAdapter.NULL_OBJECT;

	/**
	 * The model class constructor
	 * @param model2ViewAdpt is an object of the model to view adapter
	 */
	public BallModel(IModel2ViewAdapter model2ViewAdpt) {
		this._model2ViewAdpt = model2ViewAdpt;
	}

	/**
	 * The time interval in milliseconds
	 */
	private int _timeSlice = 50;

	/**
	 * The timer
	 */
	private Timer _timer = new Timer(_timeSlice, new ActionListener() {

		/**
		 * The timer "ticks" by calling this method every _timeslice milliseconds
		 */
		public void actionPerformed(ActionEvent e) {
			_model2ViewAdpt.update();
		}
	});

	/**
	 * The dispatcher for adding and removing balls
	 */
	private Dispatcher myDispatcher = new Dispatcher();

	/**
	 * This is the method that is called by the view's adapter to the model, i.e. is called by IView2ModelAdapter.paint().
	 * This method will update the sprites's painted locations by painting all the sprites
	 * onto the given Graphics object.
	 * @param g The Graphics object from the view's paintComponent() call.
	 */
	public void update(Graphics g) {
		myDispatcher.notifyAll(g); // The Graphics object is being given to all the sprites (Observers)
	}

	/**
	 * Start the timer
	 */
	public void start() {
		_timer.start();
	}

	/**
	 * Part if the following code in the loadBall(String) function is derived from
	 * "https://www.clear.rice.edu/comp310/JavaResources/dynamic_class_load.html", 
	 * which accords with the homework instruction 
	 * 
	 * The following method returns an instance of an ABall, given a fully qualified class name (package.classname) of
	 * a subclass of ABall.
	 * The method assumes that there is only one constructor for the supplied class that has the same *number* of
	 * input parameters as specified in the args array and that it conforms to a specific
	 * signature, i.e. specific order and types of input parameters in the args array.
	 * @param className A string that is the fully qualified class name of the desired object
	 * @return An instance of the supplied class. 
	 */
	private ABall loadBall(String className) {
		try {
			int radius = Randomizer.Singleton.randomInt(5, 20);
			int height = _model2ViewAdpt.getCanvas().getHeight();
			int width = _model2ViewAdpt.getCanvas().getWidth();
			Object[] args = new Object[] { Randomizer.Singleton.randomLoc(new Rectangle(0, 0, width, height)), radius,
					Randomizer.Singleton.randomVel(new Rectangle(0, 0, 5, 15)), Randomizer.Singleton.randomColor(),
					_model2ViewAdpt.getCanvas() };
			java.lang.reflect.Constructor<?> cs[] = Class.forName(className).getConstructors(); // get all the constructors
			java.lang.reflect.Constructor<?> c = null;
			for (int i = 0; i < cs.length; i++) { // find the first constructor with the right number of input parameters
				if (args.length == (cs[i]).getParameterTypes().length) {
					c = cs[i];
					break;
				}
			}
			return (ABall) c.newInstance(args); // Call the constructor.   Will throw a null ptr exception if no constructor with the right number of input parameters was found.
		} catch (Exception ex) {
			System.err.println("Class " + className + " failed to load. \nException = \n" + ex);
			System.out.println("Try again!");
			return null;
		}
	}

	/**
	 * This method add an ABall loaded by the loadBall method to the dispatcher
	 * @param classname A string that is the fully qualified class name of the desired object
	 */
	public void makeballs(String classname) {
		ABall ball;
		ball = loadBall(classname);
		myDispatcher.addObserver(ball);
	}

	/**
	 * This method clear all of the balls on the canvas by deleting all the sprites in the dispatcher
	 */
	public void clearballs() {
		myDispatcher.deleteObservers();
	}
}
