package sq6_kt21.client.chatapp.controller;

import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.util.HashSet;

import common.IChatServer;
import common.IChatroom;
import sq6_kt21.client.chatapp.model.*;
import sq6_kt21.client.chatapp.view.*;
import sq6_kt21.client.chatroom.controller.ChatRoomController;
import sq6_kt21.concrete.ChatServer;
import sq6_kt21.concrete.User;


/**
 * 
 * The ChatApp controller
 * @author Kun Tian, Suozhi Qi
 *
 */
public class ChatAppController {
	/**
	 * The gui object for chatapp
	 */
	private ChatAppGUI _gui;
	/**
	 * The model object for chatapp
	 */
	private ChatAppModel _model;

	
	/**
	 * The constructor of Controller
	 */
	private ChatAppController() {
		//		//set the model field
		_model = new ChatAppModel(new IChatAppModelAdpt() {

			/**
			 * Append the string to the gui
			 */
			@Override
			public void append(String s) {
				// TODO Auto-generated method stub
				_gui.append(s);
			}

			/**
			 * Set the remote host according to the provided IP address
			 */
			@Override
			public void setRemoteHost(String hostAddress) {
				// TODO Auto-generated method stub
				_gui.setGUITitle(hostAddress);
			}

			/**
			 * make a chatroom: main2 mini adpt
			 */
			@Override
			public IMain2MiniAdpt makeMini(IChatServer newChatServer, HashSet<IChatServer> proxy) {
				// TODO Auto-generated method stub
				ChatRoomController miniController;
				IMain2MiniAdpt miniMVCAdpt = null;
				try {
					miniController = new ChatRoomController(newChatServer.getChatroom(), _model);
					miniMVCAdpt = new IMain2MiniAdpt() {

						@Override
						public void refresh() {
							// TODO Auto-generated method stub
							miniController.refresh();
						}

						@Override
						public void setchatServers(HashSet<IChatServer> chatServerProxys) {
							miniController.setchatServers(chatServerProxys);
						}

					};
					((ChatServer) newChatServer).setController(miniController);
					miniController.start(newChatServer, proxy);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				return miniMVCAdpt;
			}

			/**
			 * Add a friend
			 */
			@Override
			public void addFriend(User user) {
				// TODO Auto-generated method stub
				_gui.addFriend(user);
			}

			/**
			 * Set the chatroom list
			 */
			@Override
			public void setChatroomList(HashSet<IChatroom> chatrooms) {
				// TODO Auto-generated method stub
				_gui.addChatRooms(chatrooms);
			}

		});
		//set the view field
		_gui = new ChatAppGUI(new IChatAppViewAdpt() {

			/**
			 * Create a new chatroom
			 */
			@Override
			public void createChatRoom(String name) {
				// TODO Auto-generated method stub
				try {
					_model.createChatRoom(name);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			/**
			 * Set the name of the user
			 */
			@Override
			public void setName(String name) {
				// TODO Auto-generated method stub
				_model.setName(name);
			}

			/**
			 * Connect to a remote host
			 */
			@Override
			public void connect(String ip) {
				// TODO Auto-generated method stub
				_model.connectTo(ip, false);
			}

			/**
			 * The method to join a specific chatroom
			 */
			@Override
			public void joinChatRoom(IChatroom chatroom) {
				// TODO Auto-generated method stub
				_model.joinChatRoom(chatroom);
			}

		});
	}

	/**
	 * Start the system
	 */
	public void start() {
		_model.start();
		_gui.start();
	}

	/**
	 * Launch the application.
	 * @param args Arguments given by the system or command line.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { // Java specs say that the system must be constructed on the GUI event thread.
			public void run() {
				try {
					ChatAppController controller = new ChatAppController(); // instantiate the system
					controller.start(); // start the system
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
