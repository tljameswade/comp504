package controller;

import java.awt.EventQueue;

import javax.swing.JApplet;
import javax.swing.WindowConstants;

import view.*;
import listFW.IList;
import model.*;

/**
 * Controller for the ListFW demo app
 * @author swong
 *
 */
public class ListDemoApp extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5749546570904821385L;
	private ListDemoFrame<IList> view;
	private ListDemoModel model;
	
	/**
	 * No parameter constructor for use with applets.   Sets window closing behavior to WindowConstants.HIDE_ON_CLOSE
	 */
	public ListDemoApp() {
		this(WindowConstants.HIDE_ON_CLOSE);
	}
	
	/**
	 * Constructor for the class
	 * @param closeAction Window closing behavior
	 */
	public ListDemoApp(int closeAction) {
		view = new ListDemoFrame<IList>( new IModelAdapter<IList>() {

			@Override
			public String runAlgo(IList host, String classname, String param) {

				return model.runListAlgo(host, classname, param);
			}

			@Override
			public String runFoldR(IList host, String accClassname) {
				return model.runFoldR(host, accClassname);
			}

			@Override
			public String runFoldL(IList host, String accClassname) {
				return model.runFoldL(host, accClassname);
			}			
		}, closeAction);
		
		model = new ListDemoModel( new IViewAdapter() {

			@Override
			public void setLists(IList... lists) {
				view.setHosts(lists);
			}
			
		});		
	}
	
	/**
	 * Start the application
	 */
	public void start() {
		model.start();
		view.start();
	}
	
	/**
	 * Launch the application.   Sets the window closing behavior to WindowConstants.EXIT_ON_CLOSE
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					(new ListDemoApp(WindowConstants.EXIT_ON_CLOSE)).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
