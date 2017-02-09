package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The Music player view
 * @param <TInstrument> The instrument that we use to play the music
 * @author Qianyi Wu, Suozhi Qi
 * @version 1.0
 *
 */
public class MusicPlayerFrame<TInstrument> extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8418893651461696476L;
	private JPanel contentPane;
	private final JPanel pnlControl = new JPanel();
	private final JTextField txtFile = new JTextField();
	private final JLabel lblFile = new JLabel("File:");
	private final JButton btnLoad = new JButton("Load");
	private final JButton btnParse = new JButton("Parse");
	private final JComboBox<TInstrument> cbPlayer = new JComboBox<TInstrument>();
	private final JButton btnPlay = new JButton("Play");
	private final JButton btnStop = new JButton("Stop");
	private final JSplitPane splitPane = new JSplitPane();
	private final JScrollPane scrollPaneUp = new JScrollPane();
	private final JScrollPane scrollPaneDown = new JScrollPane();
	private final JTextPane txtpnFileContent = new JTextPane();
	private final JTextPane txtpnParsedFile = new JTextPane();

	@SuppressWarnings("unchecked")
	private IModelAdapter<TInstrument> adptr = (IModelAdapter<TInstrument>) IModelAdapter.NULL_OBJ;

	/**
	 * Create the frame.
	 */
	public MusicPlayerFrame() {
		txtFile.setToolTipText("Input the song's name here");
		txtFile.setText("");
		txtFile.setColumns(20);
		initGUI();
	}

	/**
	 * @param madptr model adapter
	 */
	public MusicPlayerFrame(IModelAdapter<TInstrument> madptr) {
		adptr = (IModelAdapter<TInstrument>) madptr;
		initGUI();

	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 1000, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(pnlControl, BorderLayout.NORTH);

		pnlControl.add(lblFile);

		pnlControl.add(txtFile);
		btnParse.setToolTipText("Parse the file of the song into IPhrase object");
		btnParse.setEnabled(false);
		txtFile.setColumns(20);
		btnLoad.setToolTipText("Load the file of the music");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adptr.Open("/songs/" + txtFile.getText() + ".abc");
				btnParse.setEnabled(true);
			}
		});

		pnlControl.add(btnLoad);
		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adptr.Parse();
				btnPlay.setEnabled(true);
			}
		});

		pnlControl.add(btnParse);
		cbPlayer.setToolTipText("Select an instrument to play the music");

		pnlControl.add(cbPlayer);
		btnPlay.setToolTipText("Play the music");
		btnPlay.setEnabled(false);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adptr.Play(cbPlayer.getItemAt(cbPlayer.getSelectedIndex()));
			}
		});

		pnlControl.add(btnPlay);
		btnStop.setToolTipText("Stop playing the music");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adptr.Stop();
			}
		});

		pnlControl.add(btnStop);
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

		contentPane.add(splitPane, BorderLayout.CENTER);

		splitPane.setLeftComponent(scrollPaneUp);
		txtpnFileContent.setToolTipText("File Content");

		scrollPaneUp.setViewportView(txtpnFileContent);

		splitPane.setRightComponent(scrollPaneDown);
		txtpnParsedFile.setToolTipText("Parsed File");

		scrollPaneDown.setViewportView(txtpnParsedFile);

		scrollPaneUp.setBorder(new TitledBorder("File Content:"));

		scrollPaneDown.setBorder(new TitledBorder("Parsed Content:"));

	}

	/**
	 * @param instrument The instrument that we use to play the music
	 */
	public void addInstrument(TInstrument instrument) {
		cbPlayer.addItem(instrument);
	}

	/**
	 * Start the frame
	 */
	public void start() {
		setVisible(true);
	}

	/**
	 * @return txtpn A string that is the parsed result of the song
	 */
	public JTextPane getTxtpnParsedFile() {
		return txtpnParsedFile;
	}

	/**
	 * 
	 * @return txtpn A string that represents the file content of the song
	 */
	public JTextPane getTxtpnFileContent() {
		return txtpnFileContent;
	}
}
