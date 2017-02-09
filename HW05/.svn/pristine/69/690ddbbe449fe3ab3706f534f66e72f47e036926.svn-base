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
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * The view class that runs the GUI
 * @author Suozhi Qi, Zhaohan Jia
 * @version 1.0
 * @param <TDropListItem> The droplist that has the IUpdatestrategy
 * @param <TPaintListItem> The droplist that has the paintstrategy to choose
 */
public class BallGUI<TDropListItem, TPaintListItem> extends JFrame {

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
	 * The button to clear all balls
	 */
	private final JButton btnClearAll = new JButton("Clear All");

	/**
	 * Adapter back to the model for control tasks.
	 */
	private IModelControlAdapter<TDropListItem, TPaintListItem> _modelControlAdpt;

	/**
	 * The model adapter is initialized to a no-op to insure that system always has well-defined behavior
	 */
	private IModelPaintAdapter PaintAdpt = IModelPaintAdapter.NULL_OBJECT;

	/**
	 * Create a special panel with an overridden paintComponent method.
	 */
	private JPanel _pnlCanvas = new JPanel() {
		private static final long serialVersionUID = 2;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			PaintAdpt.paint(g);
		}
	};
	private final JSplitPane splitPane = new JSplitPane();
	private final JButton MakeSwitcher = new JButton("Make Switcher");
	private final JButton btnSwitch = new JButton("Switch!");
	private final JSplitPane splitPane_1 = new JSplitPane();
	private final JButton btnMakeSelectedBall = new JButton("Make Selected Ball");
	private final JButton btnCombine = new JButton("Combine!");
	private final JSplitPane splitPane_2 = new JSplitPane();
	private final JTextField textInput = new JTextField();
	private final JButton btnAddToLists = new JButton("Add to lists");
	private final JSplitPane splitPane_3 = new JSplitPane();

	/**
	 * The top drop list, used to select what IUpdate strategy to use in a new ball and
	 * to switch the switcher to.
	 */
	private final JComboBox<TDropListItem> _list1DL = new JComboBox<TDropListItem>();

	/**
	 * Bottom drop list, used for combining with the top list selection.
	 */
	private final JComboBox<TDropListItem> _list2DL = new JComboBox<TDropListItem>();
	private final JSplitPane splitPane_4 = new JSplitPane();

	/**
	 * The button to add a paintstrategy
	 */
	private final JButton btnAddPaintStrategy = new JButton("Add");

	/**
	 * The textfield to input paintstrategy
	 */
	private final JTextField PaintInput = new JTextField();

	/**
	 * The droplist to select a paint strategy
	 */
	private final JComboBox<TPaintListItem> PaintList = new JComboBox<TPaintListItem>();

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
	 * @param modelCtrlAdpt The view to model control adapter
	 * @param modelPaintAdpt The view to model paint adapter
	 */
	public BallGUI(IModelControlAdapter<TDropListItem, TPaintListItem> modelCtrlAdpt,
			IModelPaintAdapter modelPaintAdpt) {
		PaintInput.setToolTipText("Name of, XXX, from model.paint.strategy.XXXPaintStrategy.java");
		PaintInput.setText("Ball");
		PaintInput.setColumns(10);
		textInput.setToolTipText("Name of strategy, XXX, from model.Strategy.XXX");
		textInput.setText("Straight");
		textInput.setColumns(10);
		this.PaintAdpt = modelPaintAdpt;
		this._modelControlAdpt = modelCtrlAdpt;
		initGUI();
	}

	/**
	 * Initialize the GUI
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 900, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Northpanel.setBackground(Color.GREEN);

		contentPane.add(Northpanel, BorderLayout.NORTH);
		GridBagLayout gbl_Northpanel = new GridBagLayout();
		gbl_Northpanel.columnWidths = new int[] { 120, 120, 150, 127, 75, 113, 97, 0 };
		gbl_Northpanel.rowHeights = new int[] { 71, 0 };
		gbl_Northpanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_Northpanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		Northpanel.setLayout(gbl_Northpanel);

		/**
		 * when click on the clearball button,the clearballs function in IModelControlAdapter gets implemented
		 */

		/**
		 * addlists button will trigger the addStrategy function
		 * also the return object will be added to the top and bottom droplists
		 */
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);

		GridBagConstraints gbc_splitPane_2 = new GridBagConstraints();
		gbc_splitPane_2.anchor = GridBagConstraints.WEST;
		gbc_splitPane_2.insets = new Insets(0, 0, 0, 5);
		gbc_splitPane_2.gridx = 0;
		gbc_splitPane_2.gridy = 0;
		Northpanel.add(splitPane_2, gbc_splitPane_2);

		splitPane_2.setLeftComponent(textInput);
		btnAddToLists.setToolTipText("Add strategy to both the droplists");
		btnAddToLists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TDropListItem o = _modelControlAdpt.addStrategy(textInput.getText());
				if (null == o)
					return; // just in case

				_list1DL.insertItemAt(o, 0);
				_list2DL.insertItemAt(o, 0);
			}
		});

		splitPane_2.setRightComponent(btnAddToLists);
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);

		GridBagConstraints gbc_splitPane_3 = new GridBagConstraints();
		gbc_splitPane_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_splitPane_3.insets = new Insets(0, 0, 0, 5);
		gbc_splitPane_3.gridx = 1;
		gbc_splitPane_3.gridy = 0;
		Northpanel.add(splitPane_3, gbc_splitPane_3);
		_list2DL.setToolTipText("Name of strategy, XXX, from model.Strategy.XXX");

		splitPane_3.setRightComponent(_list2DL);
		_list1DL.setToolTipText("Name of strategy, XXX, from model.Strategy.XXX");

		splitPane_3.setLeftComponent(_list1DL);

		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);

		GridBagConstraints gbc_splitPane_1 = new GridBagConstraints();
		gbc_splitPane_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_splitPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_splitPane_1.gridx = 2;
		gbc_splitPane_1.gridy = 0;
		Northpanel.add(splitPane_1, gbc_splitPane_1);

		/**
		 * makeBall button will trigger the loadBall function in IModelControlAdapter
		 */

		btnMakeSelectedBall.setToolTipText("Instantiate a ball with the selected strategy in the upper droplist");
		btnMakeSelectedBall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_modelControlAdpt.loadBall(_list1DL.getItemAt(_list1DL.getSelectedIndex()),
						PaintList.getItemAt(PaintList.getSelectedIndex()));
			}
		});

		splitPane_1.setLeftComponent(btnMakeSelectedBall);

		/**
		 * combine button will trigger the combineStrategy function in IModelControlAdapter
		 * the return object will be added to the top and bottom droplists.
		 */
		btnCombine.setToolTipText(
				"Combine the selected strategies in both of the droplists into a single strategy and add that strategy to both of the droplists");
		btnCombine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TDropListItem o = _modelControlAdpt.combineStrategies(_list1DL.getItemAt(_list1DL.getSelectedIndex()),
						_list2DL.getItemAt(_list2DL.getSelectedIndex()));
				_list1DL.insertItemAt(o, 0);
				_list2DL.insertItemAt(o, 0);
			}
		});

		splitPane_1.setRightComponent(btnCombine);

		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_splitPane.insets = new Insets(0, 0, 0, 5);
		gbc_splitPane.gridx = 3;
		gbc_splitPane.gridy = 0;
		Northpanel.add(splitPane, gbc_splitPane);

		/**
		 * MakeSwitcher button will trigger makeSwitcher function in ImodelControlAdapter
		 */
		MakeSwitcher.setToolTipText("Instantiate a ball that can switch strategies");
		MakeSwitcher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_modelControlAdpt.makeSwitcher(PaintList.getItemAt(PaintList.getSelectedIndex()));
			}
		});
		splitPane.setLeftComponent(MakeSwitcher);

		/**
		 * the switch button will trigger the SwitchStrategy function in IModelControlAdapter
		 */

		btnSwitch.setToolTipText(
				"Switch strategy on all the switcher balls with the selected strategy in the upper droplist");
		btnSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_modelControlAdpt.SwitchStrategy(_list1DL.getItemAt(_list1DL.getSelectedIndex()));
			}
		});

		splitPane.setRightComponent(btnSwitch);
		btnClearAll.setToolTipText("Erase all balls in the canvas");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_modelControlAdpt.clearballs();
			}
		});
		GridBagConstraints gbc_btnClearAll = new GridBagConstraints();
		gbc_btnClearAll.insets = new Insets(0, 0, 0, 5);
		gbc_btnClearAll.anchor = GridBagConstraints.WEST;
		gbc_btnClearAll.gridx = 4;
		gbc_btnClearAll.gridy = 0;
		Northpanel.add(btnClearAll, gbc_btnClearAll);

		GridBagConstraints gbc_splitPane_4 = new GridBagConstraints();
		gbc_splitPane_4.insets = new Insets(0, 0, 0, 5);
		gbc_splitPane_4.fill = GridBagConstraints.BOTH;
		gbc_splitPane_4.gridx = 5;
		gbc_splitPane_4.gridy = 0;
		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
		Northpanel.add(splitPane_4, gbc_splitPane_4);
		btnAddPaintStrategy.setToolTipText("Add paint strategy to list.");
		btnAddPaintStrategy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TPaintListItem o = _modelControlAdpt.addPaintStrategy(PaintInput.getText());
				if (null == o)
					return; // just in case

				PaintList.insertItemAt(o, 0);
			}
		});

		splitPane_4.setRightComponent(btnAddPaintStrategy);

		splitPane_4.setLeftComponent(PaintInput);

		GridBagConstraints gbc_PaintList = new GridBagConstraints();
		gbc_PaintList.fill = GridBagConstraints.HORIZONTAL;
		gbc_PaintList.gridx = 6;
		gbc_PaintList.gridy = 0;
		PaintList.setToolTipText("Name of, XXX, from model.paint.strategy.XXXPaintStrategy.java");
		Northpanel.add(PaintList, gbc_PaintList);
		_pnlCanvas.setBackground(Color.LIGHT_GRAY);

		contentPane.add(_pnlCanvas, BorderLayout.CENTER);
	}

}
