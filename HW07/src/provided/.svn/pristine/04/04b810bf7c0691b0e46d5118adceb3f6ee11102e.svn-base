package provided.engine.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
/**
 * This class represents the "view" for the Model-View-Controller architecture used by the Hangman game system. 
 * The GUI provides all the input and output user interfaces needed by the user to play the game. 
 * The communication to the the model is accomplished via two adapters.
 */
public class EngineGUI extends JFrame {


	/**
	 * SerialversionUID to enable well-defined serialization of class.
	 */
	private static final long serialVersionUID = -4584473405991544203L;

	/**
	 * The top panel holding the controls
	 */
	private JPanel controlPanel;

	/**
	 * The text area where status info is shown
	 */
	private JTextArea outputTA;

	/**
	 * Button to quit the application and stop the model.
	 */
	private JButton quitBtn;

	/**
	 * Adapter to the model
	 */
	private IModelAdapter model;
	
	/**
	 * Constructor for the class
	 * @param model Adapter to the model
	 */
	public EngineGUI(IModelAdapter model){
		super("RMI Engine");
		this.model = model;
		initGUI();
	}

	/**
	 * Component initialization
	 */
	protected void initGUI() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				quit();
			}
		});
		controlPanel = new JPanel();

		outputTA = new JTextArea();
		quitBtn = new JButton("Quit");

		setSize(800, 300);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(controlPanel, BorderLayout.NORTH);
		JScrollPane scroll = new JScrollPane();
		scroll.setAutoscrolls(true);
		scroll.getViewport().add(outputTA, null);
		contentPane.add(scroll, BorderLayout.CENTER);

		controlPanel.add(quitBtn);

		quitBtn.addActionListener(new java.awt.event.ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				quit();
			} 
		}); 
	}

	/**
	 * Centralized management point for exiting the application.
	 * All calls to exit the system should go through here.
	 * Shuts system down by stopping the model.
	 */
	private void quit() {
		System.out.println("EngineGUI: Server is quitting...");
		model.quit();
	}

	/**
	 * Appends the given string to the end of the output text area.
	 * Will force the the scroll pane to show the appended text.
	 * Does NOT add a linefeed to the string!
	 * @param s The string to append
	 */
	public void append(String s) {
		outputTA.append(s);
		//Force the JScrollPane to go to scroll down to the new text
		outputTA.setCaretPosition(outputTA.getText().length()); 
	}

	/**
	 * Starts the view by making it visible.
	 */
	public void start() {
		setVisible(true);
	}
}