package sq6_kt21.server.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.AnnotationAttributes;
import gov.nasa.worldwind.render.ScreenRelativeAnnotation;
import gov.nasa.worldwind.util.WWUtil;
import map.MapLayer;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

/**
 * The GameView class
 * 
 * @author Kun Tian, Suozhi Qi
 *
 */
public class GameGUI extends JFrame {

	/**
	 * Unique class id
	 */
	private static final long serialVersionUID = -882049752363166760L;

	/**
	 * Adapter inside model to communicate to the view
	 */
	private IGameView2ModelAdapter modelAdapter;
	
	/**
	 * The content panel
	 */
	public JPanel contentPane;
	
	/**
	 * The world map
	 */
	private MapView map;
	
	/**
	 * The text field for team chat room
	 */
	private JTextField textFieldUpper;
	
	/**
	 * The text field for global chat room
	 */
	private JTextField textFieldLower;
	
	/**
	 * The panel to hold picture and two chat rooms
	 */
	private JSplitPane splitPaneLeft = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	
	/**
	 * The panel to hold the team chat room
	 */
	private JSplitPane splitPaneUpper = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	
	/**
	 * The panel to hold the global chat room
	 */
	private JSplitPane splitPaneLower = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	
	/**
	 * The text area for global chat room
	 */
	private JTextArea textAreaLower = new JTextArea();
	
	/**
	 * The text area for team chat room
	 */
	private JTextArea textAreaUpper = new JTextArea();
	
	/**
	 * The tab pane to hold global and local chat rooms
	 */
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	private JLabel lblNewLabel;
	
	/**
	 * The panel to hold the picture
	 */
//	private JPanel imagePane = null;
	
	/**
	 * The layer to added onto the map
	 */
//	protected RenderableLayer layer;
	
	/**
	 * The player selected position
	 */
	@SuppressWarnings("unused")
	private Position place = null;
	
	private AnnotationAttributes defaultAttributes = new AnnotationAttributes();
	
//	private AnnotationLayer annotationLayer = new AnnotationLayer();
	
	@SuppressWarnings("unused")
	private ScreenRelativeAnnotation hint;
//	private Annotation currentAnnotation;
//	private Color savedBorderColor;
	private JTextField textField;
	
	private Timer timer;
	private int time;
	
	private int count;
	
	private JPanel leaderBoardPanel;
	private JLabel leaderBoardLabel;
	
	/**
	 * Constructor for GameView
	 * 
	 * @param adapter  Adapter inside model to communicate to the view 
	 */
	public GameGUI(IGameView2ModelAdapter adapter) {
		this.modelAdapter = adapter;
		this.initGUI();
	}
	
	/**
	 * Create the frame.
	 */
	public void initGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 1050, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		map = new MapView();
		
		JPanel panel = new JPanel();
		panel.setToolTipText("The panel to hold the button");
		contentPane.add(panel, BorderLayout.SOUTH);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblRemainingTime = new JLabel("Remaining Time:");
		panel.add(lblRemainingTime);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setToolTipText("The remaining time on this round will show here.");
		panel.add(lblNewLabel);
		
		JButton SubmitButton = new JButton("Submit");
		panel.add(SubmitButton);
		SubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count = modelAdapter.getCount();
				int total = modelAdapter.getCities();
				if (time > 0) {
					if (count == 3) {
						modelAdapter.submitResults(total);
						SubmitButton.setEnabled(false);
					}
					else {
						alert("Please select 3 cities!");
					}
				}
				else {
					modelAdapter.submitResults(total);
					SubmitButton.setEnabled(false);
					alert("Your time is up!");
				}
			}
		});
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		splitPane.setRightComponent(map);
		splitPane.setResizeWeight(0.2);
		splitPaneLeft.setToolTipText("The big panel to hold chatrooms");
		
		splitPane.setLeftComponent(splitPaneLeft);
		splitPaneLeft.setResizeWeight(0.5);

		tabbedPane.setToolTipText("The tab panel to hold chatrooms");

		splitPaneLeft.setRightComponent(tabbedPane);

		// Add global chat room onto the panel
		tabbedPane.addTab("To EveryOne", splitPaneLower);
		tabbedPane.addTab("Within Team", splitPaneUpper);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPaneUpper.setLeftComponent(scrollPane);
		textAreaUpper.setToolTipText("The team chatroom");
		
		textAreaUpper.setBorder(new TitledBorder(null, "Team Chat:", TitledBorder.LEADING, TitledBorder.TOP));
		textAreaLower.setToolTipText("The global chatroom");
		textAreaLower.setBorder(new TitledBorder(null, "Announcement:", TitledBorder.LEADING, TitledBorder.TOP));
		scrollPane.setViewportView(textAreaUpper);
		
		JPanel panel_1 = new JPanel();
		panel_1.setToolTipText("The panel to hold the button and text messages");
		splitPaneUpper.setRightComponent(panel_1);
		splitPaneUpper.setResizeWeight(0.9);
		
		textFieldUpper = new JTextField();
		textFieldUpper.setToolTipText("The text box for input a text message");
		panel_1.add(textFieldUpper);
		textFieldUpper.setColumns(10);
		
		JButton btnSendUpper = new JButton("Send");
		btnSendUpper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelAdapter.sendToTeam(modelAdapter.getIP() + ":  " + textFieldUpper.getText());
				textFieldUpper.setText("");
			}
		});
		btnSendUpper.setToolTipText("The button to send out the messages");
		panel_1.add(btnSendUpper);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPaneLower.setLeftComponent(scrollPane_1);
		splitPaneLower.setResizeWeight(0.9);
		scrollPane_1.setViewportView(textAreaLower);
		
		JPanel panel_2 = new JPanel();
		panel_2.setToolTipText("The panel to hold the button and text messages.");
		splitPaneLower.setRightComponent(panel_2);
		
		textFieldLower = new JTextField();
		textFieldLower.setToolTipText("The box to input the messages");
		panel_2.add(textFieldLower);
		textFieldLower.setColumns(10);
		
		JButton btnSendLower = new JButton("Send");
		btnSendLower.setToolTipText("The send button to send out the messages");
		btnSendLower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelAdapter.sendToGlobal(modelAdapter.getIP() + ":  " + textFieldLower.getText());
			}
		});
		panel_2.add(btnSendLower);
		textAreaUpper.setEditable(false);
		textAreaLower.setEditable(false);
        
		
		leaderBoardPanel = new JPanel();
		leaderBoardLabel = new JLabel();
		leaderBoardPanel.setBorder(new TitledBorder(null, "Leader Board:", TitledBorder.LEADING, TitledBorder.TOP));
		leaderBoardPanel.add(leaderBoardLabel);
		tabbedPane.addTab("Leader Board", leaderBoardPanel);
	}

	/**
	 * The function to start the GUI and initialize some elements
	 */
	public void start() {
		map.start();
		setVisible(true);
		textAreaLower.append("Welcome to our Game! \n");
		
		// Configure the initial view parameters so that the browser balloon is centered in the viewport.
        Configuration.setValue(AVKey.INITIAL_LATITUDE, 60);
        Configuration.setValue(AVKey.INITIAL_LONGITUDE, -85);
        Configuration.setValue(AVKey.INITIAL_ALTITUDE, 9500000);
        Configuration.setValue(AVKey.INITIAL_PITCH, 45);
        
        // Initialize defaultAttributes
        this.defaultAttributes.setBackgroundColor(new Color(0f, 0f, 0f, 0f));
        this.defaultAttributes.setTextColor(Color.RED);
        this.defaultAttributes.setLeaderGapWidth(14);
        this.defaultAttributes.setCornerRadius(0);
        this.defaultAttributes.setSize(new Dimension(300, 0));
        this.defaultAttributes.setAdjustWidthToText(AVKey.SIZE_FIT_TEXT); // use strict dimension width - 200
        this.defaultAttributes.setFont(Font.decode("Arial-BOLD-24"));
        this.defaultAttributes.setBorderWidth(0);
        this.defaultAttributes.setHighlightScale(1);             // No highlighting either
        this.defaultAttributes.setCornerRadius(0);
        
//		this.layer = new RenderableLayer();
//		this.layer.setName("Game");

        // Size the World Window to provide enough screen space for the BrowserBalloon, and center the World Window
        // on the screen.
        Dimension size = new Dimension(1200, 800);
        this.setPreferredSize(size);
        this.pack();
        WWUtil.alignComponent(null, this, AVKey.CENTER);
        
     	// Handle left clicks on mouse
        /**
        MouseListener listener = new MouseAdapter() {
     		public void mouseClicked(MouseEvent e) {
     			if (e.getButton() == 1) {
     				System.out.println("Detect the left mouse click!");
     				
     				if (map.getWWD().getCurrentPosition() != null) {
     					modelAdapter.setPlaceMark(map.getWWD().getCurrentPosition());
     				}
     			}
     		}
     	};
     	this.map.getWWD().addMouseListener(listener);
     	*/
     	
     	//setupSelection();
     	
//     	insertBeforePlacenames(map.getWWD(), this.layer);
        this.getLayeredPane().update(map.getWWD().getGraphics());        
        
        initTimer(2);
        
        addWindowListener(new WindowAdapter() {
			@Override 
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				modelAdapter.sendQuitMessage();
			}
		});
	}
	
	public void setLeaderBoard(String str) {
		leaderBoardLabel.setText(str);
	}
	
	public void initTimer(int minute) {
		// Stop previous work if exists
		if (this.timer != null) {
			this.timer.stop();
		}
		// Display the initial time
		this.time = minute * 60;
		lblNewLabel.setText(getStandardTime(this.time));
		this.timer = new Timer(1000, (e) -> {
            this.time -= 1;
            if (this.time >= 0) {
            	lblNewLabel.setText(getStandardTime(this.time));
            	// Set the color of words to be red, if less than 30 seconds are left.
            	if (this.time == 30) {
            		lblNewLabel.setForeground(Color.red);
            	}
            }
		});
		// Start the timer
		this.timer.start();
	}
	
	private String getStandardTime(int num) {
		return " " + Integer.toString(num / 60) + " : " + Integer.toString(num % 60);
	}
	
/**	
	private void setupSelection()
    {
        // Add a select listener to select or highlight annotations on rollover
        map.getWWD().addSelectListener(new SelectListener()
        {

            public void selected(SelectEvent event)
            {
            	
                if (event.hasObjects() && event.getTopObject() instanceof Annotation)
                {
                    // Handle cursor change on hyperlink
                    if (event.getTopPickedObject().getValue(AVKey.URL) != null)
                        ((Component) map.getWWD()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    else
                        ((Component) map.getWWD()).setCursor(Cursor.getDefaultCursor());
                }

                // Select/unselect on left click on annotations
                if (event.getEventAction().equals(SelectEvent.LEFT_CLICK))
                {
                    if (event.hasObjects())
                    {
                    	if (event.getTopObject() instanceof GlobeAnnotation) {
                    		System.out.println("Is GlobeAnnotation");
                    	} else if (event.getTopObject() instanceof Annotation)
                        {                        	
                        	System.out.println("Is annotation!");
                        }
                    }
                }
            }
        });
    }
    */
	
	/**
	 * Ask user to make a guess on place.
	 */
//	public void askForSelection() {
//		alert("Please select a position to submit");
//	}
	
	/**
	 * Append the message onto the global chat room.
	 * 
	 * @param info  The message to be appended onto the global chat room
	 */
	public void appendToGlobal(String info) {
		this.textAreaLower.append(info+"\n");
	}
	
	public void appendToTeamChat(String info) {
		this.textAreaUpper.append(info+"\n");
	}
	
	
	/**
	 * Insert the layer into the layer list just before the placenames.
	 * 
	 * @param wwd  The world map
	 * @param layer The layer to be added onto the map
	 */
	/**
	public void insertBeforePlacenames(WorldWindow wwd, Layer layer)
    {
        // Insert the layer into the layer list just before the placenames.
        int compassPosition = 0;
        LayerList layers = wwd.getModel().getLayers();
        for (Layer l : layers)
        {
            if (l instanceof PlaceNameLayer)
                compassPosition = layers.indexOf(l);
        }
        layers.add(compassPosition, layer);
    }
    */
	
	/**
	 * Pop up an alert window on the screen to show some messages 
	 * 
	 * @param msg  The message to be shown on the screen
	 */
	public void alert(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	/**
	 * The method to add another map layers
	 * @param _layer the map layer to be added
	 */
	public void addMapLayer(MapLayer _layer) {
		map.addLayer(_layer);
	}

}
