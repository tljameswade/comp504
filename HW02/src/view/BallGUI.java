package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The view class that runs the GUI
 * @author Suozhi Qi, Xiang Li
 * @version 1.0
 */
public class BallGUI extends JFrame {

	/**
	 * The serial version ID
	 */
	private static final long serialVersionUID = -2479160785166496395L;

	/**
	 * The content JPanel
	 */
	private JPanel contentPane;

	/**
	 * The north Panel
	 */
	private final JPanel Northpanel = new JPanel();

	/**
	 * The button to make a new ball
	 */
	private final JButton btnMakeBall = new JButton("Make Ball");

	/**
	 * The button to clear all balls
	 */
	private final JButton btnClearAll = new JButton("Clear All");

	/**
	 * The text frame to receive input to direct which
	 * type of balls to make
	 */
	private final JTextField textField = new JTextField();

	/**
	 * The model adapter is initialized to a no-op to insure that system always has well-defined behavior
	 */
	private IView2ModelAdapter _view2ModelAdpt = IView2ModelAdapter.NULL_OBJECT;

	/**
	 * Create a special panel with an overridden paintComponent method.
	 */
	private JPanel _pnlCanvas = new JPanel() {
		private static final long serialVersionUID = 2;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			_view2ModelAdpt.paint(g);
		}
	};

	/**
	 * Launch the application.
	 */
	public void start() {
		setVisible(true);
	}

	/**
	 * Updates the view by repainting the canvas
	 */
	public void update() {
		_pnlCanvas.repaint();
	}

	/**
	 * Get the Component of the panel
	 * @return The Component of the panel
	 */
	public Component getCanvas() {
		return _pnlCanvas;
	}

	/**
	 * The constructor of the view BallGUI
	 * @param _view2ModelAdpt The view to model adapter
	 */
	public BallGUI(IView2ModelAdapter _view2ModelAdpt) {
		this._view2ModelAdpt = _view2ModelAdpt;
		textField.setToolTipText("Please enter the kind of ball you want to load\nStart with \"model.ball.\"");
		textField.setColumns(10);
		textField.setText("model.ball.StraightBall");
		initGUI();
	}

	/**
	 * Initialize the GUI
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(Northpanel, BorderLayout.NORTH);

		Northpanel.add(textField);
		btnMakeBall.setToolTipText("Make a new ball whose type is entered in the textFrame on the left");
		btnMakeBall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
				_view2ModelAdpt.loadBall(text);
			}
		});

		Northpanel.add(btnMakeBall);
		btnClearAll.setToolTipText("Erase all balls in the canvas");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_view2ModelAdpt.clearballs();
			}
		});

		Northpanel.add(btnClearAll);

		contentPane.add(_pnlCanvas, BorderLayout.CENTER);
	}

}
