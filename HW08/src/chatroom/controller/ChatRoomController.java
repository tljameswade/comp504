package chatroom.controller;

import java.awt.Container;
import java.rmi.RemoteException;
import java.util.HashSet;
import chatapp.model.ChatAppModel;
import chatroom.model.ChatRoomModel;
import chatroom.model.IChatRoomModelAdpt;
import chatroom.view.ChatRoomGUI;
import chatroom.view.IChatRoomViewAdpt;
import common.IChatServer;
import common.IChatroom;

public class ChatRoomController {
	/**
	 * The mini model
	 */
	private ChatRoomModel _model;

	/**
	 * The mini view
	 */
	private ChatRoomGUI<IChatServer> _gui;

	/**
	 * The mini controller
	 * @param room the chatroom
	 * @param mainModel the main model
	 */
	public ChatRoomController(IChatroom room, ChatAppModel mainModel) {
		_model = new ChatRoomModel(new IChatRoomViewAdpt() {

			@Override
			public void append(String user, String text) {
				//_gui.append(user, text);
			}

			@Override
			public Container getMiniGUI() {
				// TODO Auto-generated method stub
				return _gui.getJPanel();
			}

		});
		_gui = new ChatRoomGUI<IChatServer>(new IChatRoomModelAdpt<IChatServer>() {

			@Override
			public void addUser(String IPromote) {
				mainModel.connectTo(IPromote, false);
				_model.addUser(IPromote);
			}

			@Override
			public void removeUser(IChatServer selectedUser) {
				// TODO Auto-generated method stub

			}

			@Override
			public void send(String text) {
				// TODO Auto-generated method stub
				_model.send(text);
			}

			@Override
			public void exit() {
				// TODO Auto-generated method stub

			}

			@Override
			public HashSet<IChatServer> getChatStubs() {
				// TODO Auto-generated method stub
				return _model.getChatStubs();
			}

			@Override
			public void leaveRoom() {
				// TODO Auto-generated method stub
				_model.leaveRoom();
			}

			@Override
			public void sendImg() {
				// TODO Auto-generated method stub
				_model.sendImg();
			}

		});
	}

	/**
	 * Start the model
	 * @param chatServer the chatserver
	 * @param chatServerProxys the proxy chatserver
	 * @throws RemoteException remote exception
	 */
	public void start(IChatServer chatServer, HashSet<IChatServer> chatServerProxys) throws RemoteException {
		_model.start(chatServer);
		_gui.start(chatServerProxys);
	}

	public ChatRoomGUI<IChatServer> getGUI() {
		return _gui;
	}

	/**
	 * Set the chatserver
	 * @param chatServerProxys set of proxyserver
	 */
	public void setchatServers(HashSet<IChatServer> chatServerProxys) {
		System.out.println("chatServerProxys size:  " + chatServerProxys.size());
		_gui.start(chatServerProxys);
	}

	/**
	 * Refresh the gui
	 */
	public void refresh() {
		_gui.refresh();
	}
}
