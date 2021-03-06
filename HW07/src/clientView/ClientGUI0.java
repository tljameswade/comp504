package clientView;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * The view of the client MVC system.
 * 
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
public class ClientGUI0 extends JFrame {

	/**
	 * SerialVersionUId for the class.
	 */
	private static final long serialVersionUID = -199099598475124566L;

	/**
	 * The default remote host reference.
	 */
	private static final String DEFAULT_REMOTE_HOST = "localhost";

	/**
	 * The adapter to the model.
	 */
	private IModelAdapter0 model;

	/**
	 * The top control panel
	 */
	private JPanel controlPnl;

	/**
	 * The status output text area
	 */
	private JTextArea outputTA;

	/**
	 * The connect button
	 */
	private JButton connectBtn;

	/**
	 * The quit button
	 */
	private JButton quitBtn;

	/**
	 * The button to calculate the remote server's info
	 */
	private JButton calcGetInfoBtn;

	/**
	 * An general purpose input text field used to input parameters to send with the 
	 * task executions. 
	 */
	private JTextField inputTF;

	/**
	 * The remote server's IP address info input text field.
	 */
	private JTextField remoteHostTF;
	private JButton btnFibo;
	private JPanel panel_Right;
	private JPanel panel_Right_Line1;
	private JPanel panel_Left;
	private JPanel panel_Left_Down;
	private JPanel panel_Left_Up;
	private JPanel panel_Right_Line2;
	private JPanel panel_Right_Line3;
	private JButton btnLongestPalin;
	private JPanel panel_Right_Line4;
	private JButton btnRandomarray;

	/**
	 * Constructor of the class
	 * @param ma the ModelAdapter 
	 */
	public ClientGUI0(IModelAdapter0 ma) {
		super("Client GUI");
		model = ma;
		initGUI();
	}

	/**
	 * Initializes the view and its components.
	 */
	protected void initGUI() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			/**
			 * tell the model to quit if the window is closing, even if very slowly.
			 */
			public void windowClosing(WindowEvent evt) {
				System.out.println("this.windowClosing, event=" + evt);
				model.quit();
			}
		});
		setSize(600, 400);
		controlPnl = new JPanel();
		outputTA = new JTextArea();
		JScrollPane scroll = new JScrollPane(outputTA);
		Container contentPane = getContentPane();
		contentPane.add(controlPnl, BorderLayout.NORTH);
		controlPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel_Left = new JPanel();
		panel_Left.setBackground(Color.YELLOW);
		controlPnl.add(panel_Left);
		panel_Left.setLayout(new GridLayout(2, 1, 0, 0));

		panel_Left_Up = new JPanel();
		panel_Left_Up.setBackground(Color.LIGHT_GRAY);
		panel_Left.add(panel_Left_Up);
		panel_Left_Up.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel remoteHostLbl = new JLabel("Remote Host:");
		panel_Left_Up.add(remoteHostLbl);
		remoteHostTF = new JTextField(DEFAULT_REMOTE_HOST);
		remoteHostTF.setToolTipText("The IP address of the server");
		panel_Left_Up.add(remoteHostTF);
		remoteHostTF.setPreferredSize(new Dimension(100, 25));

		remoteHostTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		{

			panel_Left_Down = new JPanel();
			panel_Left_Down.setBackground(Color.LIGHT_GRAY);
			panel_Left.add(panel_Left_Down);
			panel_Left_Down.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			connectBtn = new JButton();
			connectBtn.setToolTipText("Connect to the server");
			panel_Left_Down.add(connectBtn);
			connectBtn.setText("Connect");
			quitBtn = new JButton();
			quitBtn.setToolTipText("Quit the client app");
			panel_Left_Down.add(quitBtn);
			quitBtn.setText("Quit");
			quitBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					System.out.println("quitBtn.actionPerformed, event=" + evt);
					model.quit();
				}
			});
			connectBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					System.out.println("connectBtn.actionPerformed, event=" + evt);
					connect();
				}
			});
		}
		contentPane.add(scroll, BorderLayout.CENTER);
		{

			panel_Right = new JPanel();
			panel_Right.setBackground(Color.CYAN);
			controlPnl.add(panel_Right);
			panel_Right.setLayout(new GridLayout(0, 1, 0, 0));

			panel_Right_Line1 = new JPanel();
			panel_Right_Line1.setBackground(Color.LIGHT_GRAY);
			panel_Right_Line1.setBorder(null);
			panel_Right.add(panel_Right_Line1);

			JLabel paramsLbl = new JLabel("Parameters:");
			panel_Right_Line1.add(paramsLbl);
			inputTF = new JTextField("5");
			inputTF.setToolTipText("Please input parameters here");
			panel_Right_Line1.add(inputTF);
			inputTF.setPreferredSize(new Dimension(100, 25));

			panel_Right_Line2 = new JPanel();
			panel_Right_Line2.setBackground(Color.LIGHT_GRAY);
			panel_Right.add(panel_Right_Line2);
			calcGetInfoBtn = new JButton();
			calcGetInfoBtn.setToolTipText("Get server's system properties");
			panel_Right_Line2.add(calcGetInfoBtn);
			calcGetInfoBtn.setText("Get Info");
			JButton calcPiBtn = new JButton("Calculate Pi");
			calcPiBtn.setToolTipText("Calculate Pi to the given number of decimals");
			panel_Right_Line2.add(calcPiBtn);
			calcPiBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						append("Calculate Pi: ");
						append(model.calcPi(Integer.parseInt(inputTF.getText())) + "\n");
					} catch (Exception excpt) {
						append("Calculate Pi exception: " + excpt + "\n");
					}
				}
			});
			calcGetInfoBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					try {
						append("Calculate Get Info: ");
						append(model.calcGetInfo(inputTF.getText()) + "\n");
					} catch (Exception excpt) {
						append("Calculate Get Info exception: " + excpt + "\n");
					}
				}
			});

			panel_Right_Line3 = new JPanel();
			panel_Right_Line3.setBackground(Color.LIGHT_GRAY);
			panel_Right.add(panel_Right_Line3);

			btnFibo = new JButton("Calculate Fibo");
			btnFibo.setToolTipText("Calculate the fibonacci number at given position");
			panel_Right_Line3.add(btnFibo);

			btnLongestPalin = new JButton("Longest Palin");
			btnLongestPalin.setToolTipText("Calculate the longest palindrome sequence from the given string");
			panel_Right_Line3.add(btnLongestPalin);

			panel_Right_Line4 = new JPanel();
			panel_Right_Line4.setBackground(Color.LIGHT_GRAY);
			panel_Right.add(panel_Right_Line4);

			btnRandomarray = new JButton("RandomArray");
			btnRandomarray.setToolTipText(
					"Randomly generate a sorted array with given number of randomly chosen numbers from 0 to 100");
			btnRandomarray.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int size = Integer.parseInt(inputTF.getText());
						append("Generate a random array of size " + size + " and sort it...\n");
						append("The sorted array is: ");
						append(model.randomArray(size) + "\n");
					} catch (Exception excpt) {
						append("Find the Longest Palindromic Substring: " + excpt + "\n");
					}
				}
			});
			panel_Right_Line4.add(btnRandomarray);

			btnLongestPalin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						append("Find the Longest Palindromic Substring ...\n");
						append("The Longest Palindromic Substring is:  ");
						append(model.longestPalin(inputTF.getText()) + "\n");
					} catch (Exception excpt) {
						append("Find the Longest Palindromic Substring: " + excpt + "\n");
					}
				}
			});

			btnFibo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						append("Calculate Fibo...\n");
						append("The " + Integer.parseInt(inputTF.getText()) + "th Fibonacci number is: ");
						append(model.calcFibo(Integer.parseInt(inputTF.getText())) + "\n");
					} catch (Exception excpt) {
						append("Calculate Fibo exception: " + excpt + "\n");
					}
				}
			});
		}
	}

	/**
	 * Have the model connect to the remote server.
	 */
	private void connect() {
		append("Connecting...\n");
		append(model.connectTo(remoteHostTF.getText()) + "\n");
	}

	/**
	 * Set the displayed remote host text field to the actual remote system's IP address or host name 
	 * @param host The name of the remote host 
	 */
	public void setRemoteHost(String host) {
		remoteHostTF.setText(host);
	}

	/**
	 * Append the given string(s) to the view's output text adapter.  
	 * @param s the string to display.
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