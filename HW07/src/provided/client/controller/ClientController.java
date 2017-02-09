package provided.client.controller;

import provided.client.model.*;
import provided.client.view.*;

/**
 * Controller for the Client MVC system.
 * @author swong
 *
 */
public class ClientController {

	/**
	 * The view of the MVC
	 */
	private ClientGUI view;
	
	/**
	 * The model of the MVC
	 */
	private ClientModel model;
	
	/**
	 * Constructor of the class.   Instantiates and connects the model and the view.
	 */
	public ClientController() {

		model = new ClientModel(new IViewAdapter() {
			/**
			 * Send the string to the view
			 * @param s The string to display in the view
			 */
			@Override
			public void append(String s){
				view.append(s);
			}

			/**
			 * Sets the displayed remote host on the view
			 * @param hostAddress The address of the host to display
			 */
			@Override
			public void setRemoteHost(String hostAddress) {
				view.setRemoteHost(hostAddress);
			}
		});
		
		view = new ClientGUI(new IModelAdapter() {
			/**
			 * Connect to the given remote host
			 * @param the remote host to connect to.
			 */
			@Override
			public String connectTo(String remoteHost) {
				return model.connectTo(remoteHost);
			}

			/**
			 * Calculate PI to the given number of digits
			 * @param numDigits The numer of digits to compute PI to.
			 */
			@Override
			public String calcPi(int numDigits) {
				return model.calcPi(numDigits);
			}

			/**
			 * Get information about the remote host
			 * @param p A string passed to the remote task.
			 */
			@Override
			public String calcGetInfo(String p) {
				return model.calcGetInfo(p);
			}
			
			/**
			 * Quits the current connection and closes the application.   
			 * Causes the model to stop and thus end the application. 
			 */
			@Override
			public void quit(){
				model.stop();
			}
		});

	}

	/**
	 * Starts the view then the model.  The view needs to be started first so that it can display 
	 * the model status updates as it starts.
	 */
	public void start(){
		view.start();
		model.start();
	}


	/**
	 * Main() method of the client application. Instantiates and then starts the controller.
	 * @param args ignored
	 */
	public static void main(String[] args) {
		(new ClientController()).start();
	}
}




