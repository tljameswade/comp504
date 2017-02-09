package chatapp.controller;

import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.util.HashSet;
import chatapp.model.*;
import chatapp.view.*;
import chatroom.controller.ChatRoomController;
import chatroom.model.ChatRoomModel;
import common.IChatServer;
import common.IChatroom;
import common.IUser;
/**
 * 
 * @author Suozhi Qi, kejunliu
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
		_model = new ChatAppModel(new IChatAppModelAdpt(){

			@Override
			public void append(String s) {
				// TODO Auto-generated method stub
				_gui.append(s);
			}

			@Override
			public void setRemoteHost(String hostAddress) {
				// TODO Auto-generated method stub
				_gui.setGUITitle(hostAddress);
			}

			
			/**
			 * make a chatroom
			 */
			@Override
			public IMain2MiniAdpt makeMini(IChatServer newChatServer, HashSet<IChatServer> proxy) {
				// TODO Auto-generated method stub
				ChatRoomController miniController;
				IMain2MiniAdpt miniMVCAdpt = null;
				try {
					miniController = new ChatRoomController(newChatServer.getChatroom(),_model);
					miniMVCAdpt = new IMain2MiniAdpt(){

						@Override
						public void refresh() {
							// TODO Auto-generated method stub
							miniController.refresh();
						}
						@Override
						public void setchatServers(HashSet<IChatServer> chatServerProxys){
							miniController.setchatServers(chatServerProxys);
						}
						
					};
					miniController.start(newChatServer, proxy);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				return miniMVCAdpt;
			}
			
			@Override
			public void setChatroomList(HashSet<IChatroom> chatrooms) {
				// TODO Auto-generated method stub
				_gui.addChatRooms(chatrooms);
			}

		});
		//set the view field
		_gui = new ChatAppGUI(new IChatAppViewAdpt(){

			@Override
			public void createChatRoom(String name){
				// TODO Auto-generated method stub
				try {
					_model.createChatRoom(name);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void setName(String name) {
				// TODO Auto-generated method stub
				_model.setName(name);
			}

			@Override
			public void connect(String ip) {
				// TODO Auto-generated method stub
				_model.connectTo(ip);
			}

			@Override
			public void joinChatRoom(IChatroom chatroom) {
				// TODO Auto-generated method stub
				_model.joinChatRoom(chatroom);
			}

			@Override
			public void shutDown() {
				// TODO Auto-generated method stub
				_model.stop();
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
