package provided.client.view;
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
public class ClientGUI extends JFrame {

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
	private IModelAdapter model;

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

	/**
	 * Constructor of the class
	 * @param ma the ModelAdapter 
	 */
	public ClientGUI(IModelAdapter ma) {
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
				System.out.println("this.windowClosing, event="+evt);
				model.quit();
			}
		});
		setSize(800,400);
		controlPnl = new JPanel();
		outputTA = new JTextArea();
		JScrollPane scroll = new JScrollPane(outputTA);
		Container contentPane = getContentPane();
		contentPane.add(controlPnl, BorderLayout.NORTH);
		{
			quitBtn = new JButton();
			controlPnl.add(quitBtn);
			quitBtn.setText("Quit");
			quitBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					System.out.println("quitBtn.actionPerformed, event="+evt);
					model.quit();
				}
			});
		}
		contentPane.add(scroll, BorderLayout.CENTER); 

		JLabel remoteHostLbl = new JLabel("Remote Host:");
		controlPnl.add(remoteHostLbl);
		remoteHostTF = new JTextField(DEFAULT_REMOTE_HOST);
		remoteHostTF.setPreferredSize(new Dimension(100,25));
		controlPnl.add(remoteHostTF);
		{
			connectBtn = new JButton();
			controlPnl.add(connectBtn);
			connectBtn.setText("Connect");
			connectBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					System.out.println("connectBtn.actionPerformed, event="+evt);
					connect();
				}
			});
		}
		remoteHostTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});

		JLabel paramsLbl = new JLabel("Parameters:");
		controlPnl.add(paramsLbl);
		inputTF = new JTextField("5");
		inputTF.setPreferredSize(new Dimension(100,25));
		JButton calcPiBtn = new JButton("Calculate Pi"); 
		controlPnl.add(inputTF);
		controlPnl.add(calcPiBtn);
		{
			calcGetInfoBtn = new JButton();
			controlPnl.add(calcGetInfoBtn);
			calcGetInfoBtn.setText("Get Info");
			calcGetInfoBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					try {
						append("Calculate Get Info: ");
						append(model.calcGetInfo(inputTF.getText())+"\n");
					}
					catch(Exception excpt) {
						append("Calculate Get Info exception: "+excpt+"\n");
					}
				}				
			});
		}
		calcPiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					append("Calculate Pi: ");
					append(model.calcPi(Integer.parseInt(inputTF.getText()))+"\n");
				}
				catch(Exception excpt) {
					append("Calculate Pi exception: "+excpt+"\n");
				}
			}
		});
	}
	
	/**
	 * Have the model connect to the remote server.
	 */
	private void connect() {
		append("Connecting...\n");
		append(model.connectTo(remoteHostTF.getText())+"\n");
	}
	
	/**
	 * Set the displayed remote host text field to the actual remote system's IP address or host name 
	 * @param host The name of the remote host 
	 */
	public void setRemoteHost(String host){
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