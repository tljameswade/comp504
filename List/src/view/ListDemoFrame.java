package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.GridLayout;

/**
 * The view for the ListFW demo app
 * @author swong
 *
 */
public class ListDemoFrame<THost> extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3147647062119454221L;
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private final JTextField tfClassname = new JTextField();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextArea taOutput = new JTextArea();
	private final JLabel lblVisitorClassname = new JLabel("Visitor Classname:");
	private final JLabel lblInputParameter = new JLabel("Input parameter:");
	private final JTextField tfParam = new JTextField();
	private final JLabel lblRunVisitorOn = new JLabel("List:");

	private IModelAdapter<THost> model;
	private final JComboBox<THost> cbHosts = new JComboBox<THost>();
	private final JButton btnRun = new JButton("Run");
	private final JLabel lblAccumulator = new JLabel("Accumulator Classname:");
	private final JTextField tfAccClassname = new JTextField();
	private final JButton btnFoldr = new JButton("FoldR");
	private final JButton btnFoldL = new JButton("FoldL");
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();
	private final JPanel panel_3 = new JPanel();
	private final JPanel panel_4 = new JPanel();
	

	/**
	 * Constructor for the view
	 * @param model The adapter to the model
	 * @param closeAction The window closing action to use.  Should be either WindowConstants.EXIT_ON_CLOSE or WindowConstants.HIDE_ON_CLOSE
	 */
	public ListDemoFrame(IModelAdapter<THost> model, int closeAction) {


		setDefaultCloseOperation(closeAction);

		this.model = model;


		initGUI();
	}
	
	/**
	 * Initialize the GUI components
	 */
	private void initGUI() {

		setBounds(100, 100, 500, 427);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		panel.setBackground(new Color(153, 255, 255));
		
		contentPane.add(panel, BorderLayout.NORTH);
		
		panel.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		tfParam.setColumns(10);
		tfClassname.setColumns(10);
		panel_4.add(panel_1);
		panel_1.add(lblVisitorClassname);
		panel_1.add(tfClassname);
		panel_1.add(lblInputParameter);
		panel_1.add(tfParam);
		panel_4.add(panel_2);
		panel_2.add(lblRunVisitorOn);
		panel_2.add(cbHosts);
		panel_2.add(btnRun);
		tfAccClassname.setColumns(10);
		panel_4.add(panel_3);
		panel_3.add(lblAccumulator);
		panel_3.add(tfAccClassname);
		panel_3.add(btnFoldr);
		panel_3.add(btnFoldL);
		btnFoldL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = model.runFoldL(cbHosts.getItemAt(cbHosts.getSelectedIndex()), tfAccClassname.getText());
				taOutput.append(result+"\n");				}
		});
		
		btnFoldr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String result = model.runFoldR(cbHosts.getItemAt(cbHosts.getSelectedIndex()), tfAccClassname.getText());
				taOutput.append(result+"\n");				
			}
		});
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String result = model.runAlgo(cbHosts.getItemAt(cbHosts.getSelectedIndex()), tfClassname.getText(), tfParam.getText());
				taOutput.append(result+"\n");
			}
		});
		
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(taOutput);
	}
	
	/**
	 * Start the view, i.e. make it visible.
	 */
	public void start() {
		setVisible(true);
		
		
	}
	
	/**
	 * Set drop list (cbHosts) to hold the given host objects.
	 * @param hosts  A vararg of THosts.
	 */
	public void setHosts(THost... hosts) {
		for( THost host: hosts) {
			cbHosts.addItem(host);
		}

	}

}
