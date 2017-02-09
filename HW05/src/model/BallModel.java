package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.Strategy.SwitcherStrategy;
import util.IDispatcher;
import util.Randomizer;
import util.SetDispatcherSequential;

/**
 * The model class
 * @author Suozhi Qi,Zhanhao Jia
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
	private IDispatcher<IBallCmd> myDispatcher = new SetDispatcherSequential<IBallCmd>();

	/**
	 * The switcherstrategy instance
	 */
	SwitcherStrategy<IBallCmd> s1;

	private Timer updatetimer = new Timer(_timeSlice, new ActionListener() {

		/**
		 * The timer "ticks" by calling this method every _timeslice milliseconds
		 */
		public void actionPerformed(ActionEvent e) {
			myDispatcher.dispatch(new IBallCmd() {
				public void apply(Ball context, IDispatcher<IBallCmd> disp) {
					context.updateState(disp);
					context.bounce();
					context.move();
				}
			});
		}
	});

	/**
	 * Update all balls in the model
	 * @param g The Graphics object from the view's paintComponent() call.
	 */
	public void update(Graphics g) {
		myDispatcher.dispatch(new IBallCmd() {
			public void apply(Ball context, IDispatcher<IBallCmd> disp) {
				context.paint(g);
			}
		});
	}

	/**
	 * Start the timer
	 */
	public void start() {
		_timer.start();
		updatetimer.start();
	}

	/**
	 * Part if the following code in the loadBall(String) function is derived from
	 * "https://www.clear.rice.edu/comp310/JavaResources/dynamic_class_load.html", 
	 * which accords with the homework instruction 
	 * 
	 * The following method returns an IUpdateStrategy, given a fully qualified class name (package.classname) of
	 * a subclass of ABall.
	 * The method assumes that there is only one constructor for the supplied class that has the same *number* of
	 * input parameters as specified in the args array and that it conforms to a specific
	 * signature, i.e. specific order and types of input parameters in the args array.
	 * @param className A string that is the fully qualified class name of the desired object
	 * @return An instance of IUpdateStrategy 
	 */
	@SuppressWarnings("unchecked")
	private IUpdateStrategy<IBallCmd> loadStrategy(String className) {
		try {
			Object[] args = new Object[] {};
			java.lang.reflect.Constructor<?> cs[] = Class.forName(className).getConstructors(); // get all the constructors
			java.lang.reflect.Constructor<?> c = null;
			for (int i = 0; i < cs.length; i++) { // find the first constructor with the right number of input parameters
				if (args.length == (cs[i]).getParameterTypes().length) {
					c = cs[i];
					break;
				}
			}
			return (IUpdateStrategy<IBallCmd>) c.newInstance(args); // Call the constructor.   Will throw a null ptr exception if no constructor with the right number of input parameters was found.
		} catch (Exception ex) {
			System.err.println("Class " + className + " failed to load. \nException = \n" + ex);
			System.out.println("Try again!");
			return (IUpdateStrategy<IBallCmd>) _errorStrategyFac;
		}
	}

	/**
	 * Part if the following code in the loadBall(String) function is derived from
	 * "https://www.clear.rice.edu/comp310/JavaResources/dynamic_class_load.html", 
	 * which accords with the homework instruction 
	 * 
	 * The following method returns an instance of IPaintStrategy, 
	 * given a fully qualified class name (package.classname) of
	 * a subclass of ABall.
	 * The method assumes that there is only one constructor for the supplied class that has the same *number* of
	 * input parameters as specified in the args array and that it conforms to a specific
	 * signature, i.e. specific order and types of input parameters in the args array.
	 * @param className A string that is the fully qualified class name of the desired object
	 * @return An instance of IPaintStrategy 
	 */
	private IPaintStrategy loadPaintStrategy(String className) {
		try {
			Object[] args = new Object[] {};
			java.lang.reflect.Constructor<?> cs[] = Class.forName(className).getConstructors(); // get all the constructors
			java.lang.reflect.Constructor<?> c = null;
			for (int i = 0; i < cs.length; i++) { // find the first constructor with the right number of input parameters
				if (args.length == (cs[i]).getParameterTypes().length) {
					c = cs[i];
					break;
				}
			}
			return (IPaintStrategy) c.newInstance(args); // Call the constructor.   Will throw a null ptr exception if no constructor with the right number of input parameters was found.
		} catch (Exception ex) {
			System.err.println("Class " + className + " failed to load. \nException = \n" + ex);
			System.out.println("Try again!");
			return null;
		}
	}

	/**
	 * This method add an ABall loaded by the loadBall method to the dispatcher
	 * @param s1 The strategy that the ball to be made will use
	 * @param p1 paint Strategy that the ball uses to paint
	 */
	public void makeballs(IUpdateStrategy<IBallCmd> s1, IPaintStrategy p1) {
		int radius = Randomizer.Singleton.randomInt(5, 20);
		int height = _model2ViewAdpt.getCanvas().getHeight();
		int width = _model2ViewAdpt.getCanvas().getWidth();
		Color color = Randomizer.Singleton.randomColor();
		Ball ball = new Ball(Randomizer.Singleton.randomLoc(new Rectangle(0, 0, width, height)), radius,
				Randomizer.Singleton.randomVel(new Rectangle(0, 0, 5, 15)), color, _model2ViewAdpt.getCanvas(), s1, p1);
		myDispatcher.addObserver(ball);
	}

	/**
	 * This method clear all of the balls on the canvas by deleting all the sprites in the dispatcher
	 */
	public void clearballs() {
		myDispatcher.deleteObservers();
	}

	/**
	 * A factory for a beeping error strategy that beeps the speaker every 25 updates.
	 * Either use the _errorStrategyFac variable directly if you need a factory that makes an error strategy,
	 * or call _errorStrategyFac.make() to create an instance of a beeping error strategy.
	 */
	private IStrategyFac<IBallCmd> _errorStrategyFac = new IStrategyFac<IBallCmd>() {
		@Override
		/**
		 * Make the beeping error strategy
		 * @return  An instance of a beeping error strategy
		 */
		public IUpdateStrategy<IBallCmd> make() {
			return new IUpdateStrategy<IBallCmd>() {
				private int count = 0; // update counter

				@Override
				public void init(Ball context) {
				}

				@Override
				/**
				 * Beep the speaker every 25 updates
				 */
				public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {
					if (25 < count++) {
						java.awt.Toolkit.getDefaultToolkit().beep();
						count = 0;
					}
				}
			};
		}
	};

	/**
	 * A factory for a beeping error strategy that beeps the speaker every 25 updates.
	 * Either use the _errorPaintStrategyFac variable directly if you need a 
	 * factory that makes an error strategy,
	 * or call _errorPaintStrategyFac.make() to create an instance of a beeping error strategy.
	 */
	private IPaintStrategyFac _errorPaintStrategyFac = new IPaintStrategyFac() {
		public IPaintStrategy make() {
			return IPaintStrategy.NULL_OBJECT;
		}
	};

	/**
	 * Returns an IStrategyFac that can instantiate the strategy specified by
	 * classname. Returns a factory for a beeping error strategy if classname is null. 
	 * The toString() of the returned factory is the classname.
	 * 
	 * @param classname  Shortened name of desired strategy
	 * @return A factory to make that strategy
	 */
	public IStrategyFac<IBallCmd> makeStrategyFac(final String classname) {
		if (null == classname)
			return _errorStrategyFac;
		return new IStrategyFac<IBallCmd>() {
			/**
			 * Instantiate a strategy corresponding to the given class name.
			 * @return An IUpdateStrategy instance
			 */
			public IUpdateStrategy<IBallCmd> make() {
				IUpdateStrategy<IBallCmd> updateStrategy = loadStrategy(fixNameUpdate(classname));
				if (updateStrategy == null) {
					return _errorStrategyFac.make();
				}
				return updateStrategy;
			}

			/**
			 * Return the given class name string
			 */
			public String toString() {
				return classname;
			}
		};
	}

	/**
	 * 
	 * Returns an IPaintStrategyFac that can instantiate the strategy specified by
	 * classname. Returns a factory for a beeping error strategy if classname is null. 
	 * The toString() of the returned factory is the classname.
	 * @param classname The provided classname for paintstrategy
	 * @return Paint factory to make a paintstrategy
	 */
	public IPaintStrategyFac makePaintStrategyFac(final String classname) {
		if (null == classname)
			return _errorPaintStrategyFac;
		return new IPaintStrategyFac() {
			/**
			 * Instantiate a strategy corresponding to the given class name.
			 * @return An IUpdateStrategy instance
			 */
			public IPaintStrategy make() {
				IPaintStrategy paintStrategy = loadPaintStrategy(fixNamePaint(classname));
				if (paintStrategy == null)
					return _errorPaintStrategyFac.make();
				return paintStrategy;
			}

			/**
			 * Return the given class name string
			 */
			public String toString() {
				return classname;
			}
		};
	}

	/**
	 * Fix the name for an updatestrategy
	 * @param classname text in textField
	 * @return can be used in reflection
	 */
	public String fixNameUpdate(String classname) {
		return "model.Strategy." + classname + "Strategy";
	}

	/**
	 * Fix the name for a paintstrategy
	 * @param classname text in textInput
	 * @return fixed name of a paintstrategy
	 */
	public String fixNamePaint(String classname) {
		return "model.paint.strategy." + classname + "PaintStrategy";
	}

	/**
	 * Returns an IStrategyFac that can instantiate a MultiStrategy with the two
	 * strategies made by the two given IStrategyFac objects. Returns null if
	 * either supplied factory is null. The toString() of the returned factory
	 * is the toString()'s of the two given factories, concatenated with "-". 
	 * If either factory is null, then a factory for a beeping error strategy is returned.
	 * 
	 * @param stratFac1 An IStrategyFac for a strategy
	 * @param stratFac2 An IStrategyFac for a strategy
	 * @return An IStrategyFac for the composition of the two strategies
	 */
	public IStrategyFac<IBallCmd> combineStrategyFacs(final IStrategyFac<IBallCmd> stratFac1,
			final IStrategyFac<IBallCmd> stratFac2) {
		if (null == stratFac1 || null == stratFac2)
			return _errorStrategyFac;
		return new IStrategyFac<IBallCmd>() {
			/**
			 * Instantiate a new MultiStrategy with the strategies from the given strategy factories
			 * @return A MultiStrategy instance
			 */
			public IUpdateStrategy<IBallCmd> make() {
				return new MultiStrategy<IBallCmd>(stratFac1.make(), stratFac2.make());
			}

			/**
			 * Return a string that is the toString()'s of the given strategy factories concatenated with a "-"
			 */
			public String toString() {
				return stratFac1.toString() + "-" + stratFac2.toString();
			}
		};
	}

	/**
	 * this function will switch a ball to a new state
	 * @param strategy newStrategy needed to be updated
	 */
	public void SetSwitcherStrategy(IUpdateStrategy<IBallCmd> strategy) {
		if (s1 != null) {
			s1.setStrategy(strategy);
		}
	}

	/**
	* make a default SwithcherStrategy ball
	* @return SwitcherStrategy
	*/
	public IUpdateStrategy<IBallCmd> getSwitcherStrategy() {
		if (s1 == null)
			s1 = new SwitcherStrategy<IBallCmd>();
		return s1;
	}
}
