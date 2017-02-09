package demo.controller;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import demo.model.*;
import demo.view.*;
import extVisitor.*;

/**
 * Controller for the demo app
 * @author swong
 *
 */
public class Controller extends JApplet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5075729855577660504L;
	
	/**
	 * The system view
	 */
	private DemoFrame<IHost, ExtVisitor> view;
	/**
	 * The system model
	 */
	private DemoModel model;
	
	/**
	 * No parameter constructor, used when demo is run as an applet.
	 * Simply calls the other constructor with closeAction = WindowConstants.HIDE_ON_CLOSE
	 */
	public Controller() {
		this(WindowConstants.HIDE_ON_CLOSE);
	}
	
	/**
	 * Constructor for the class
	 * @param closeAction  The action taken when the view frame is closed.  WindowConstants.HIDE_ON_CLOSE for applets and WindowConstants.EXIT_ON_CLOSE for applications. 
	 */
	public Controller(int closeAction){
		view = new DemoFrame<IHost, ExtVisitor>(closeAction, new IModelAdapter<IHost, ExtVisitor>() {

			@Override
			public IHost makeHost(String id) {
				return model.makeHost(id);
			}

			@Override
			public Object run(IHost host, ExtVisitor visitor) {
				return model.run(host, visitor);
			}

			@Override
			public ExtVisitor makeVisitor(String name) {
				return model.makeVisitor(name);
			}

			@Override
			public void addCmd(ExtVisitor visitor, String id, String resultStr) {
				model.addCmd(visitor, id, resultStr);
			}
			
		});	
		
		model = new DemoModel(new IViewAdapter() {
			
		});
	}
	
	/**
	 * Start the app
	 */
	public void start(){
		model.start();
		view.start();	
	}
	
	/**
	* Auto-generated main method to display this JFrame
	 * @param args The command line parameters, not used
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				(new Controller(WindowConstants.EXIT_ON_CLOSE)).start();
			}
		});
	}

}
