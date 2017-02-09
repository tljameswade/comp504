package demo.view;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;





/**
 * The "view" of the demo app.
 * @param <THost> The type of object held by the "hosts" drop lists.
 * @param <TVisitor> The type of the object held by the "visitor" drop lists.
 */
public class DemoFrame<THost, TVisitor> extends javax.swing.JFrame {
	/**
	 * req'd id for serialization
	 */
	private static final long serialVersionUID = 9006845054851797382L;
	/**
	 * Enables outputTA to be scrolled
	 */
	private JScrollPane outputSPane;
	/**
	 * Panel to hold controls to create and add a new command to a given visitor
	 */
	private JPanel cmdPnl;
	/**
	 * Textfield to input an ID value to identify a visitor
	 */
	private JTextField visitorIDTF;
	/**
	 * Button to run a given visitor on a given host
	 */
	private JButton executeBtn;
	/**
	 * Button for making a new visitor
	 */
	private JButton makeVisitorBtn;
	/**
	 * Droplist for selecting a visitor
	 */
	private JComboBox<TVisitor> visitorCBx;
	/**
	 * Panel that holds controls for making a new or selecting an existing visitor
	 */
	private JPanel visitorPnl;
	/**
	 * Droplist for choosing a host
	 */
	private JComboBox<THost> hostCBx;
	/**
	 * Button for creating a new host
	 */
	private JButton makeHostBtn;
	/**
	 * Panel that holds controls for making new host
	 */
	private JPanel hostPnl;
	/**
	 * Textfield for inputting new host ID
	 */
	private JTextField idTF;
	/**
	 * Label for ID input textfield
	 */
	private JLabel idLbl;
	/**
	 * Textfield for inputting command results
	 */
	private JTextField cmdTF;
	/**
	 * Btn to make a cmd for a visitor
	 */
	private JButton makeCmdBtn;
	/**
	 * Panel holds controls for setting host IDs
	 */
	private JPanel idPnl;
	/**
	 * Panel that holds all controls
	 */
	private JPanel controlPnl;
	/**
	 * Text area for outputting results
	 */
	private JTextArea outputTA;
	/**
	 * Adapter to the model
	 */
	private IModelAdapter<THost, TVisitor> model;



	/**
	 * Constructor for the class
	 * @param closeAction   The action to take when closing the frame, could be either WindowConstants.EXIT_ON_CLOSE or WindowConstants.HIDE_ON_CLOSE
	 * @param model The adapter to the model
	 */
	public DemoFrame(int closeAction, IModelAdapter<THost, TVisitor> model) {
		super();
		this.model = model;
		initGUI();
		setDefaultCloseOperation(closeAction);
	}

	/**
	 * Start the view
	 */
	public void start() {
		setLocationRelativeTo(null);
		setVisible(true);		
	}

	/** 
	 * Initialize the GUI components
	 */
	private void initGUI() {
		try {


			outputSPane = new JScrollPane();
			getContentPane().add(outputSPane, BorderLayout.CENTER);
			{
				outputTA = new JTextArea();
				outputSPane.setViewportView(outputTA);
				outputTA.setToolTipText("The output of executing the visitors is shown here.");
			}

			controlPnl = new JPanel();
			getContentPane().add(controlPnl, BorderLayout.NORTH);
			controlPnl.setPreferredSize(new java.awt.Dimension(670, 106));

			idPnl = new JPanel();
			controlPnl.add(idPnl);
			idPnl.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			idPnl.setBackground(new java.awt.Color(128,255,255));
			idPnl.setPreferredSize(new java.awt.Dimension(96, 35));

			idLbl = new JLabel();
			idPnl.add(idLbl);
			idLbl.setText("ID = ");
			idLbl.setToolTipText("ID for host or command");

			idTF = new JTextField();
			idPnl.add(idTF);
			idTF.setText("A");
			idTF.setPreferredSize(new java.awt.Dimension(59, 23));
			idTF.setToolTipText("Type ID value here");


			hostPnl = new JPanel();
			GridLayout hostPnlLayout = new GridLayout(2, 1);
			hostPnlLayout.setColumns(1);
			hostPnlLayout.setHgap(5);
			hostPnlLayout.setVgap(5);
			hostPnlLayout.setRows(2);
			hostPnl.setLayout(hostPnlLayout);
			controlPnl.add(hostPnl);
			hostPnl.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			hostPnl.setPreferredSize(new java.awt.Dimension(117, 53));

			makeHostBtn = new JButton();
			hostPnl.add(makeHostBtn);
			makeHostBtn.setText("Make Host");
			makeHostBtn.setToolTipText("Make a new host with the given ID");
			makeHostBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					hostCBx.addItem(model.makeHost(idTF.getText()));
					hostCBx.setSelectedIndex(hostCBx.getItemCount()-1);
				}
			});


			hostCBx = new JComboBox<THost>();
			hostPnl.add(hostCBx);
			hostCBx.setPreferredSize(new java.awt.Dimension(73, 23));
			hostCBx.setOpaque(false);
			hostCBx.setToolTipText("All available hosts");



			executeBtn = new JButton();
			controlPnl.add(executeBtn);
			executeBtn.setText("Execute!");
			executeBtn.setBackground(new java.awt.Color(255,0,0));
			executeBtn.setToolTipText("Execute the given extended visitor on the given host.");
			executeBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					THost host = hostCBx.getItemAt(hostCBx.getSelectedIndex());
					TVisitor algo =  visitorCBx.getItemAt(visitorCBx.getSelectedIndex());
					Object result = model.run(host, algo); 
					outputTA.append(host+".execute("+algo+") ==> "+result+"\n");
				}
			});

			visitorPnl = new JPanel();
			controlPnl.add(visitorPnl);
			GridLayout jPanel1Layout = new GridLayout(3, 1);
			jPanel1Layout.setColumns(1);
			jPanel1Layout.setRows(3);
			jPanel1Layout.setHgap(5);
			jPanel1Layout.setVgap(5);
			visitorPnl.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new java.awt.Color(0,0,0)));
			visitorPnl.setLayout(jPanel1Layout);
			visitorPnl.setPreferredSize(new java.awt.Dimension(188, 94));

			makeVisitorBtn = new JButton();
			visitorPnl.add(makeVisitorBtn);
			makeVisitorBtn.setText("Make Visitor with name:");
			makeVisitorBtn.setSelected(true);
			makeVisitorBtn.setToolTipText("Make a new visitor with the given name.");
			makeVisitorBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					visitorCBx.addItem(model.makeVisitor(visitorIDTF.getText()));
					visitorCBx.setSelectedIndex(visitorCBx.getItemCount()-1);
				}
			});

			visitorIDTF = new JTextField();
			visitorPnl.add(visitorIDTF);
			visitorIDTF.setText("1");
			visitorIDTF.setToolTipText("Type an identifying name for the visitor");


			visitorCBx = new JComboBox<TVisitor>();
			visitorPnl.add(visitorCBx);
			visitorCBx.setPreferredSize(new java.awt.Dimension(73,23));
			visitorCBx.setOpaque(false);
			visitorCBx.setToolTipText("The available visitors");

			cmdPnl = new JPanel();
			GridLayout cmdPnlLayout = new GridLayout(2, 1);
			cmdPnlLayout.setColumns(1);
			cmdPnlLayout.setHgap(5);
			cmdPnlLayout.setVgap(5);
			cmdPnlLayout.setRows(2);
			cmdPnl.setLayout(cmdPnlLayout);
			controlPnl.add(cmdPnl);
			cmdPnl.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));

			makeCmdBtn = new JButton();
			cmdPnl.add(makeCmdBtn);
			makeCmdBtn.setText("Make Cmd for Visitor for ID");
			makeCmdBtn.setToolTipText("Make a new command for the selected visitor that will service hosts with the given ID.");
			makeCmdBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					model.addCmd(visitorCBx.getItemAt(visitorCBx.getSelectedIndex()), idTF.getText(), cmdTF.getText());
				}
			});

			cmdTF = new JTextField();
			cmdPnl.add(cmdTF);
			cmdTF.setText("Do something");
			cmdTF.setToolTipText("Type some sort of output for this command");

			pack();
			this.setSize(757, 300);
		} catch (Exception e) {
			//add your error handling code here
			e.printStackTrace();
		}
	}

}
