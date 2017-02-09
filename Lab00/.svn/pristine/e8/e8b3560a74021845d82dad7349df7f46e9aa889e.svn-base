package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainAppFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAppFrame frame = new MainAppFrame();
					frame.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void start() {
		setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public MainAppFrame() {
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlControl = new JPanel();
		pnlControl.setBackground(Color.RED);
		contentPane.add(pnlControl, BorderLayout.NORTH);
		
		JLabel lblInfo = new JLabel("Hello!");
		pnlControl.add(lblInfo);	
		
		JButton btnRun = new JButton("Get better!");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblInfo.setText("Comp504 rocks!");
			}
		});
		pnlControl.add(btnRun);

	}

}
