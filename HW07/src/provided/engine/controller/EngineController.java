package provided.engine.controller;

import provided.engine.model.*;
import provided.engine.view.*;

/**
 * Controller for the Server (Engine) part of the system.
 * @author swong
 *
 */
public class EngineController {

	/**
	 * The view
	 */
	private EngineGUI view;

	/**
	 * The model
	 */
	private EngineModel model;
	
	/**
	 * Constructor of the class.   Instantiates and connects the model and the view.
	 */
	public EngineController() {

		view = new EngineGUI(new IModelAdapter(){
			/**
			 * Exit the application by calling the model's stop() method.
			 */
			@Override
			public void quit(){
				model.stop();
			}
		});
		model = new EngineModel(new IViewAdapter(){

			/**
			 * Append the given string onto the view's display
			 * @param s The string to append
			 */
			@Override
			public void append(String s) {
				view.append(s);			
			}
			
		});
	}

	/**
	 * Start the system up.  The view is started first so that 
	 * the model can use it to display any status or error messages.
	 */
	public void start(){
		view.start();
		model.start();
	}

	/**
	 * Main method that instantiates the controller and starts it.
	 * @param args ignored.
	 */
	public static void main(String[] args) {
		(new EngineController()).start();
	}


}
