package sq6_kt21.server.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * The server GUI
 * @author Kun Tian
 *
 */
public class ServerView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -782433115224470790L;
	
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private final JButton btnStartGame = new JButton("Start Game");
	private final JButton btnAssignTeams = new JButton("Assign Teams");
	private final JTextField textField = new JTextField();
	private final JButton btnNewButton = new JButton("Broadcast");
	private final JScrollPane scrollPane = new JScrollPane();
	private JTextArea textArea = new JTextArea();
	
	private IServerView2ModelAdpt serverView2ModelAdpt;
	private final JButton btnShowResults = new JButton("Show Results");
	

	/**
	 * The method to start the GUI
	 */
	public void start() {
		setVisible(true);
		appendView("Server Started. \n");
	}

	/**
	 * Create the frame.
	 * The GUI constructor
	 */
	public ServerView(IServerView2ModelAdpt adapter) {
		textField.setColumns(10);
		this.serverView2ModelAdpt = adapter;
		initGUI();
	}
	
	/**
	 * The method to initialize the GUI
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel.add(textField);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				broadcast(textField.getText());
				textField.setText("");
			}
		});
		
		panel.add(btnNewButton);
		btnAssignTeams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				assignTeams();
			}
		});
		
		panel.add(btnAssignTeams);
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendGame();
			}
		});
		
		panel.add(btnStartGame);
		btnShowResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverView2ModelAdpt.announceResults();
			}
		});
		
		panel.add(btnShowResults);
		
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
	}

	/**
	 * The method to assign teams
	 */
	private void assignTeams() {
		serverView2ModelAdpt.assignTeams();
	}
	
	/**
	 * The method to broadcast message
	 * @param str the text to be broadcasted
	 */
	private void broadcast(String str) {
		serverView2ModelAdpt.broadcast("Server: " + str);
	}
	
	/**
	 * The method to send the game to the client
	 */
	private void sendGame(){
		serverView2ModelAdpt.startGameView();
	}
	
	/**
	 * The method to append the string to the GUI
	 * @param str the text to be appended
	 */
	public void appendView(String str){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					textArea.append(str+'\n');
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * The method to set the title of the GUI
	 * @param s the string representation of the IP of the server
	 */
	public void setGUITitle(String s) {
		setTitle("IP: " + s);
	}
}
