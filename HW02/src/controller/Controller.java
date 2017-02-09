package controller;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;

import model.BallModel;
import view.BallGUI;
import view.IView2ModelAdapter;
import model.IModel2ViewAdapter;

/**
 * MVC Controller for the system
 * @author Suozhi Qi, Xiang Li
 * @version 1.0
 * 
 */
public class Controller {

	/**
	 * The model side object
	 */
	private BallModel model;

	/**
	 * The view side object
	 */
	private BallGUI view;

	/**
	 * Controller constructor builds the system
	 */
	public Controller() {
		model = new BallModel(new IModel2ViewAdapter() {
			@Override
			public Component getCanvas() {
				return view.getCanvas();
			}

			@Override
			public void update() {
				view.update();
			}
		});

		view = new BallGUI(new IView2ModelAdapter() {
			public void paint(Graphics g) {
				model.update(g);
			}

			public void clearballs() {
				model.clearballs();
			}

			public void loadBall(String classname) {
				model.makeballs(classname);
			}
		});
	}

	/**
	 * Start the system
	 */
	public void start() {
		model.start();
		view.start();
	}

	/**
	 * Launch the application.
	 * @param args Arguments given by the system or command line.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller controller = new Controller();
					controller.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
