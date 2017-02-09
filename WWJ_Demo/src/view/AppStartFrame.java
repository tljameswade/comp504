package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppStartFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3097617897364258363L;
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private final JButton btnMakeMapGUI = new JButton("Make Map on GUI thread");
	private IAppStart2Controller controller;
	private final JButton btnMakeMapNonGUI = new JButton("Make Map fully on Non-GUI thread");
	private final JButton btnMakeMapPartiallyNonGUI = new JButton("Make Map partially on Non-GUI Thread");
	private final JButton btnMakeMapBQ = new JButton("Make Map using BQ");



	/**
	 * Create the frame.
	 */
	public AppStartFrame(IAppStart2Controller controller) {
		initGUI();
		this.controller = controller;
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(panel, BorderLayout.CENTER);
		btnMakeMapGUI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.makeMap();
				controller.startMap();
			}
		});
		
		panel.add(btnMakeMapGUI);
		btnMakeMapNonGUI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new Thread() {
					@Override
					public void run() {
						controller.makeMap();
						controller.startMap();						
					}
				}).start();
			}
		});
		
		panel.add(btnMakeMapNonGUI);
		btnMakeMapPartiallyNonGUI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new Thread() {
					@Override
					public void run() {
						controller.makeMap();    
						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								controller.startMap();	
							}
							
						});
					}
				}).start();
			}
		});
		
		panel.add(btnMakeMapPartiallyNonGUI);
		btnMakeMapBQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.runJob(new Runnable(){

					@Override
					public void run() {
						System.out.println("Main thread job running.");
						controller.makeMap();   // Instantiate the map on the main thread
						SwingUtilities.invokeLater(new Runnable() {   // Show the map on the GUI thread.

							@Override
							public void run() {
								controller.startMap();	
							}
							
						});
					}
					
				});
			}
		});
		
		panel.add(btnMakeMapBQ);
	}
	public void start() {
		setVisible(true);
		
	}

}
