package view;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shape.Arcshape;
import shape.Ashape;
import shape.Circle;
import shape.CompoundShape;
import shape.Rectangle;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.SystemColor;

/**
 * 
 * @author Suozhi Qi, Yiqing Lu
 * The class Frame for showing different shapes
 * @version 1.0
 *
 */

public class HW1mainframe extends JFrame {

	/**
	 * This is the serial version UID 
	 */
	private static final long serialVersionUID = -7860162863458021026L;

	/**
	 * This is the container panel
	 */
	private JPanel contentPane;

	/**
	 * This is a new Ashape that initially belongs 
	 * to the concrete subclass Rectangle
	 */
	private Ashape oneShape = new Rectangle(100, 100, 20, 20);

	/**
	 * This is a center panel that contains a paintComponent method
	 * initially painting an Oval and a rectangle
	 */
	private final JPanel CenterPan = new JPanel() {

		private static final long serialVersionUID = -2285134665659669016L;

		/**
		 * Overridden paintComponent method to paint a shape in the panel.
		 * @param g The Graphics object to paint on.
		 **/
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.RED);
			g.fillOval(75, 100, 20, 40);
			oneShape.paint(g);
		}
	};

	/**
	 * Create new north panel
	 */
	private final JPanel NorthPan = new JPanel();

	/**
	 * Create new north label
	 */
	private final JLabel NorthLabel = new JLabel("504 Rocks!");

	/**
	 * Create new north button
	 */
	private final JButton NorthButton = new JButton("Click me");

	/**
	 * Create new textField
	 */
	private final JTextField textField = new JTextField();

	/**
	 * Create new south panel
	 */
	private final JPanel SouthPanel = new JPanel();

	/**
	 * Create new button to show circle
	 */
	private final JButton btnCircle = new JButton("Circle");

	/**
	 * Create new button to show Arcshape
	 */
	private final JButton btnArc = new JButton("Arc");

	/**
	 * Create new button to show compound shape
	 */
	private final JButton btnCompound = new JButton("Compound");

	/**
	 * Launch the application.
	 * @param args works as the default argument in the main method in Java
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HW1mainframe frame = new HW1mainframe();
					frame.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * start() method to start the frame
	 */

	public void start() {
		setVisible(true);
	}

	/**
	 * Constructor to initiate the GUI and set columns
	 * in the textField
	 */

	public HW1mainframe() {
		textField.setToolTipText("the textArea people could write text here");
		textField.setColumns(10);
		initGUI();
	}

	/**
	 * iniGUI() method to initialize the GUI components but do not start the frame
	 */

	public void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		CenterPan.setBackground(SystemColor.inactiveCaption);

		contentPane.add(CenterPan, BorderLayout.CENTER);
		btnCircle.setToolTipText("paint a magenta circle on the center panel if the \"Circle\" button is pressed");

		btnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oneShape = new Circle(50, 50, 30);
				CenterPan.repaint();
			}
		});
		SouthPanel.add(btnCircle);
		btnArc.setToolTipText("paint a cyan Arc on the center panel if the \"Arc\" button is pressed");

		btnArc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oneShape = new Arcshape(100, 150, 40, 40);
				CenterPan.repaint();
			}
		});
		SouthPanel.add(btnArc);
		btnCompound.setToolTipText(
				"a orange rectangle and a cyan Arc appear together on the panel if the \"Compound\" button is pressed");

		btnCompound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ashape first = new Rectangle(200, 100, 40, 40);
				Ashape second = new Arcshape(230, 100, 80, 80);
				oneShape = new CompoundShape(first, second);
				CenterPan.repaint();
			}
		});
		SouthPanel.add(btnCompound);

		NorthPan.setBackground(SystemColor.activeCaption);
		contentPane.add(NorthPan, BorderLayout.NORTH);
		NorthButton.setToolTipText(
				"press the \"Click me\" button to change the label text to the text written in the testArea");

		NorthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
				NorthLabel.setText(text);
			}
		});

		NorthPan.add(NorthButton);
		NorthLabel.setToolTipText(
				"The label orginally has the string \"504 Rocks!\" and it will be substituded for the text in the textArea if the \"Click me\" button is pressed ");
		NorthPan.add(NorthLabel);
		NorthPan.add(textField);

		SouthPanel.setBackground(new Color(95, 158, 160));
		contentPane.add(SouthPanel, BorderLayout.SOUTH);
	}

}
