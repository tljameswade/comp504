package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private final JPanel pnlControl = new JPanel();
	private final JPanel pnlDisplay = new JPanel();
	private final JButton btnClickMe = new JButton("Click me!");
	private final JLabel lblNewLabel = new JLabel("Hello!");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		initGUI();
	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		pnlControl.setBackground(Color.RED);

		contentPane.add(pnlControl, BorderLayout.NORTH);
		btnClickMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setText("Comp504 rocks!");
			}
		});

		pnlControl.add(btnClickMe);

		pnlControl.add(lblNewLabel);
		pnlDisplay.setBackground(Color.GREEN);
		pnlDisplay.setForeground(Color.WHITE);

		contentPane.add(pnlDisplay, BorderLayout.CENTER);
	}

}
