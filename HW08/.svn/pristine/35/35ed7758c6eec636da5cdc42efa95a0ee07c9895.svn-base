package chatapp.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import chatapp.model.User;
import common.IChatroom;

public class ChatAppGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1726103471982316790L;
	@SuppressWarnings("unused")
	private String title = "";
	@SuppressWarnings("unused")
	private String userName = "default";
	private IChatAppViewAdpt _view2ModelAdpt = IChatAppViewAdpt.NULL_OBJECT;
	private final JPanel controllPnl = new JPanel();
	private final JButton btnUserName = new JButton("Create UserName");
	private final JLabel lblUserIP = new JLabel("Current Username:");
	private final JLabel lblUserIpContent = new JLabel("default");
	private final JButton btnCreateChatroom = new JButton("Create ChatRoom");
	private final JComboBox<User> combFriend = new JComboBox<User>();
	private final JButton btnConnect = new JButton("Add Friend");
	private final JTextField tfConnectIP = new JTextField();
	private final JSplitPane splitPaneChatRooms = new JSplitPane();
	private final JLabel lblChatrooms = new JLabel("ChatRooms");
	private final JComboBox<IChatroom> combChatRooms = new JComboBox<IChatroom>();
	private final JButton btnJoin = new JButton("Join");
	private final JScrollPane scrollPaneInfo = new JScrollPane();
	private final JTextArea textArea = new JTextArea();

	public ChatAppGUI(IChatAppViewAdpt _adapter) {
		this._view2ModelAdpt = _adapter;
		tfConnectIP.setToolTipText("Input friend's IP address");
		tfConnectIP.setColumns(10);
		initGUI();
	}

	/**
	 * Starts the already initialized frame, making it visible and ready to interact with the user.
	 */
	public void start() {
		setVisible(true);
	}

	private void initGUI() {

		setBounds(300, 300, 420, 300);
		getContentPane().setLayout(new GridLayout(2, 1, 0, 0));

		getContentPane().add(controllPnl);
		controllPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnUserName.setToolTipText("Create a new user name");

		controllPnl.add(btnUserName);
		btnUserName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String username = setUserName();
				lblUserIpContent.setText(username);
				_view2ModelAdpt.setName(username);
			}

		});

		controllPnl.add(lblUserIP);

		controllPnl.add(lblUserIpContent);
		btnCreateChatroom.setToolTipText("Create a chatroom");

		controllPnl.add(btnCreateChatroom);
		btnCreateChatroom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String roomName = getNewChatroomName();
				_view2ModelAdpt.createChatRoom(roomName);
			}

		});
		btnConnect.setToolTipText("Add a friend");

		controllPnl.add(btnConnect);
		btnConnect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ip = tfConnectIP.getText();
				_view2ModelAdpt.connect(ip);
			}

		});

		controllPnl.add(tfConnectIP);

		controllPnl.add(splitPaneChatRooms);

		splitPaneChatRooms.setLeftComponent(lblChatrooms);

		splitPaneChatRooms.setRightComponent(combChatRooms);
		btnJoin.setToolTipText("Join a chatroom");

		controllPnl.add(btnJoin);
		btnJoin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				_view2ModelAdpt.joinChatRoom((IChatroom) combChatRooms.getSelectedItem());
			}

		});
		scrollPaneInfo
				.setViewportBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		getContentPane().add(scrollPaneInfo);
		textArea.setRows(8);

		scrollPaneInfo.setViewportView(textArea);
	}

	/**
	 * Get a new chat room name
	 * @return The new chat room name
	 */
	public String getNewChatroomName() {
		String chatroomName = null;
		do {
			chatroomName = JOptionPane.showInputDialog(null, "Please input new chatroom's name:", "Chatroom Name",
					JOptionPane.INFORMATION_MESSAGE);
			if (chatroomName == null) {
				return null;
			}

			chatroomName = chatroomName.trim();
		} while (chatroomName.isEmpty());

		return chatroomName;
	}

	/**
	 * set a new userName
	 * @return The new userName
	 */
	public String setUserName() {
		String userName = null;
		do {
			userName = JOptionPane.showInputDialog(null, "Please input userName:", "UserName",
					JOptionPane.INFORMATION_MESSAGE);
			if (userName == null) {
				return null;
			}

			userName = userName.trim();
		} while (userName.isEmpty());
		this.userName = userName;
		return userName;
	}

	/**
	 * Append the given string(s) to the view's output text adapter.  
	 * @param s the string to display.
	 */
	public void append(String s) {
		textArea.append(s);
		//Force the JScrollPane to go to scroll down to the new text
		textArea.setCaretPosition(textArea.getText().length());
	}

	public void setGUITitle(String s) {
		setTitle("IP: " + s);
	}

	public void addFriend(User user) {
		combFriend.addItem(user);
	}

	public void addChatRooms(HashSet<IChatroom> rooms) {
		for (IChatroom room : rooms) {
			combChatRooms.addItem(room);
		}
	}
}
