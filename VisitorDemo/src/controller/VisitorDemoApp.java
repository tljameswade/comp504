package controller;

import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import model.*;

import view.*;
import visitors.*;


/**
 * The controller for the Visitor Demo system.
 * Also double-duties as an applet so that it can be used in a web page.
 * @author swong
 *
 */
public class VisitorDemoApp extends JApplet {

	private static final long serialVersionUID = 8966089115543457015L;
	private DemoFrame<IHost> view;
	private VisitorDemoModel model;
	
	/**
	 * No-parameter constructor used by web pages to bring the applet up.
	 */
	public VisitorDemoApp() {
		this(WindowConstants.HIDE_ON_CLOSE);
	}
	
	/**
	 * The constructor for the class
	 * @param closeAction  The window closing action.  Is WindowConstants.HIDE_ON_CLOSE for applets and WindowConstants.EXIT_ON_CLOSE for applications
	 */
	public VisitorDemoApp(int closeAction) {
		view = new DemoFrame<IHost>(closeAction, new IModelAdapter<IHost>() {

			@Override
			public IHost[] getHosts() {
				return model.getHosts();
			}

			@Override
			public String run(IHost host, String visitorClassname) {
				return (String) model.run(host, visitorClassname);
			}

			@Override
			public void paint(Graphics g) {
				model.paint(g);
			}
			
		});
		
		model = new VisitorDemoModel(new IViewAdapter() {

			@Override
			public void repaint() {
				view.repaint();			
			}

			
		});
	}
	
	
	/**
	 * Starts the view
	 */
	public void start() {
		view.start();
	}
	
	/**
	* Auto-generated main method to display this controller
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				(new VisitorDemoApp(WindowConstants.EXIT_ON_CLOSE)).start();
			}
		});
	}
}
