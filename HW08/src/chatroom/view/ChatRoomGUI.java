package chatroom.view;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import chatroom.model.IChatRoomModelAdpt;

import javax.swing.border.EtchedBorder;
import java.awt.Color;

import javax.swing.JSplitPane;

import javax.swing.JList;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.HashSet;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;

public class ChatRoomGUI<TList> extends JFrame {
	/**
	 * The serial ID
	 */
	private static final long serialVersionUID = -2185492575918668148L;

	/**
	 * The listmodel to display
	 */
	private DefaultListModel<TList> listModel = new DefaultListModel<TList>();

	/**
	 * The JList 
	 */
	private JList<TList> list = new JList<TList>(listModel);

	/**
	 * Title of chatroom
	 */
	private String title;
	private final JPanel pnlController = new JPanel();
	private final JTextField tfMsg = new JTextField();
	private final JButton btnSend = new JButton("Send");
	private final JButton btnSendImage = new JButton("Send Image");
	private final JSplitPane splitPaneDisplay = new JSplitPane();
	private final JScrollPane scrollPaneDialog = new JScrollPane();
	private final JPanel panel = new JPanel();
	//private final JLabel lblIp = new JLabel("IP:");
	//private final JTextField textField = new JTextField();
	//private final JButton btnAddUser = new JButton("Add User");
	//private final JButton btnRemoveUser = new JButton("Remove User");
	private final JButton btnRefresh = new JButton("Refresh");

	private IChatRoomModelAdpt<TList> modelAdpt;

	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel txtPanel = new JPanel();

	public ChatRoomGUI(IChatRoomModelAdpt<TList> modelAdpt) {
		//textField.setColumns(10);
		tfMsg.setColumns(18);
		initGUI();
		this.modelAdpt = modelAdpt;
	}

	private void initGUI() {
		setBounds(400, 280, 600, 400);
		setTitle(this.title);
		getContentPane().setLayout(new BorderLayout(0, 0));
		pnlController.setBorder(null);

		getContentPane().add(pnlController, BorderLayout.SOUTH);

		pnlController.add(tfMsg);
		btnSend.setToolTipText("send text msg");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelAdpt.send(tfMsg.getText());
			}
		});
		btnSendImage.setToolTipText("send a specified image");
		btnSendImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelAdpt.sendImg();
			}
		});

		pnlController.add(btnSend);

		pnlController.add(btnSendImage);

		splitPaneDisplay.setResizeWeight(0.7);

		getContentPane().add(splitPaneDisplay, BorderLayout.CENTER);
		//scrollPaneDialog.setSize(new Dimension(300, 0));
		scrollPaneDialog.setViewportBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Dialog", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		splitPaneDisplay.setLeftComponent(scrollPaneDialog);

		scrollPaneDialog.setRowHeaderView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 15, 102, 0 };
		gbl_panel.rowHeights = new int[] { 26, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.anchor = GridBagConstraints.WEST;
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 0;
		//panel.add(lblIp, gbc_lblIp);

		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.anchor = GridBagConstraints.NORTHWEST;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		//panel.add(textField, gbc_textField);

		GridBagConstraints gbc_btnAddUser = new GridBagConstraints();
		gbc_btnAddUser.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddUser.gridx = 1;
		gbc_btnAddUser.gridy = 1;
		//		btnAddUser.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				modelAdpt.addUser(textField.getText());
		//			}
		//		});
		//panel.add(btnAddUser, gbc_btnAddUser);

		GridBagConstraints gbc_btnRemoveUser = new GridBagConstraints();
		gbc_btnRemoveUser.insets = new Insets(0, 0, 5, 0);
		gbc_btnRemoveUser.gridx = 1;
		gbc_btnRemoveUser.gridy = 2;
		//panel.add(btnRemoveUser, gbc_btnRemoveUser);

		GridBagConstraints gbc_btnRefresh = new GridBagConstraints();
		gbc_btnRefresh.insets = new Insets(0, 0, 5, 0);
		gbc_btnRefresh.gridx = 1;
		gbc_btnRefresh.gridy = 3;
		btnRefresh.setToolTipText("Refresh to get the update of the members list");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				refresh();
			}
		});
		panel.add(btnRefresh, gbc_btnRefresh);

		scrollPaneDialog.setViewportView(txtPanel);
		txtPanel.setLayout(new BoxLayout(txtPanel, BoxLayout.Y_AXIS));

		GridBagConstraints gbc_btnExitChatroom = new GridBagConstraints();
		gbc_btnExitChatroom.gridx = 1;
		gbc_btnExitChatroom.gridy = 9;
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				modelAdpt.leaveRoom();
			}
		});

		//panel.add(btnExitChatroom, gbc_btnExitChatroom);
		scrollPane.setViewportBorder(
				new TitledBorder(null, "Members", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		splitPaneDisplay.setRightComponent(scrollPane);
		list.setToolTipText("User list");

		scrollPane.setViewportView(list);

	}

	public void start(HashSet<TList> chatServerProxys) {
		listModel.removeAllElements();
		for (TList chatserver : chatServerProxys) {
			listModel.addElement(chatserver);
		}
		System.out.println(chatServerProxys);
		setVisible(true);
	}

	/**
	 * Append the text to text field
	 * @param user the user
	 * @param text the string text
	 */
	/*
	public void append(String user, String text) {
		taDialog.append(user + ":" + text + "\n");
		taDialog.setCaretPosition(taDialog.getText().length());
	}*/
	public void refresh() {
		start(modelAdpt.getChatStubs());
	}

	public void closeView() {

	}

	public JPanel getJPanel() {
		return txtPanel;
	}

}
