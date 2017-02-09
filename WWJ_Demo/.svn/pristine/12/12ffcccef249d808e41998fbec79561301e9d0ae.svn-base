package view;

import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globes.Earth;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import map.IRightClickAction;
import map.MapLayer;
import map.MapPanel;


public class AppFrame<CBType> extends JFrame {
	private static final long  serialVersionUID = -1046744381305932964L;
	private JPanel             _ctrlPanel;
	private MapPanel           _mapPanel;
	private JComboBox<CBType>          _places;
	private JTextField         _latField;
	private JButton            _goLatLong;
	private JTextField         _longField;
	private JButton            _gotoBtn;
	private IView2ModelAdapter<CBType> _adpt;
	//private IRightClickAction  _rightClick;


	/**
	 * Auto-generated main method to display this JFrame
	 */

	public AppFrame(IView2ModelAdapter<CBType> adpt, IRightClickAction rightClick) {
		super();
		_adpt = adpt;
		//_rightClick = rightClick;
		initGUI(rightClick);
	}

	private void initGUI(IRightClickAction rightClick) {
		try {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

			_ctrlPanel = new JPanel();
			FlowLayout jPanel1Layout = new FlowLayout();
			_ctrlPanel.setLayout(jPanel1Layout);
			getContentPane().add(_ctrlPanel, BorderLayout.SOUTH);
			_ctrlPanel.setPreferredSize(new java.awt.Dimension(390, 43));
			_ctrlPanel.setBackground(new java.awt.Color(128,255,128));

			_latField = new JTextField();
			_ctrlPanel.add(_latField);
			_latField.setText("latitude");
			_latField.setPreferredSize(new java.awt.Dimension(88, 22));

			_longField = new JTextField();
			_ctrlPanel.add(_longField);
			_longField.setText("longitude");

			_goLatLong = new JButton();
			_ctrlPanel.add(_goLatLong);
			_goLatLong.setText("Go!");
			_goLatLong.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					_adpt.goLatLong(_latField.getText(), _longField.getText());
					_mapPanel.repaint();
				}
			});

			_places = new JComboBox<CBType>();
			_ctrlPanel.add(_places);

			_gotoBtn = new JButton();
			_ctrlPanel.add(_gotoBtn);
			_gotoBtn.setText("Go!");
			_gotoBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					_adpt.goPlace(_places.getItemAt(_places.getSelectedIndex()));
					_mapPanel.repaint();
				}
			});

			_mapPanel = new MapPanel(Earth.class);
			getContentPane().add(_mapPanel, BorderLayout.CENTER);
			_mapPanel.setPreferredSize(new java.awt.Dimension(600, 400));
			_mapPanel.addRightClickAction(rightClick);

			pack();
		} catch (Exception e) {
			//add your error handling code here
			e.printStackTrace();
		}
	}

	public void start() {		
		_mapPanel.start();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setPosition(Position pos)
	{
		_mapPanel.setPosition(pos, true);
	}

	public void addMapLayer(MapLayer layer)
	{
		_mapPanel.addLayer(layer);
	}

	public void removeMapLayer(MapLayer layer)
	{
		_mapPanel.removeLayer(layer);
	}

	public void addPlace(CBType p) {
		_places.insertItemAt(p, 0);
		_places.setSelectedIndex(0);
	}
}
