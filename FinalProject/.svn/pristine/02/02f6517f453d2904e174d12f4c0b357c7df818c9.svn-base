package sq6_kt21.client.chatroom.controller;

import java.awt.Container;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.HashSet;


import common.IChatServer;
import common.IChatroom;
import gov.nasa.worldwind.geom.Position;
import sq6_kt21.client.chatapp.model.ChatAppModel;
import sq6_kt21.client.chatroom.model.ChatRoomModel;
import sq6_kt21.client.chatroom.model.GameInfo;
import sq6_kt21.client.chatroom.model.IChatRoomModelAdpt;
import sq6_kt21.client.chatroom.view.ChatRoomGUI;
import sq6_kt21.client.chatroom.view.IChatRoomViewAdpt;

/**
 * The chatroom controller
 * @author Kun Tian, Suozhi Qi
 *
 */
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

			/**
			 * Append string to the gui
			 * @param user the sender of the text
			 * @param text the text to be sent
			 */
			@Override
			public void append(String user, String text) {
				//_gui.append(user, text);
			}

			/**
			 * get the JPanel of the miniModel
			 */
			@Override
			public Container getMiniGUI() {
				// TODO Auto-generated method stub
				return _gui.getJPanel();
			}

			/**
			 * Get the map panel of the miniModel
			 */
			@Override
			public Container getmapPanel() {
				// TODO Auto-generated method stub
				return _gui.getMapPanel();
			}

			/**
			 * Close the window of the chatroom
			 */
			@Override
			public void closeRoom() throws RemoteException {
				mainModel.leaveChatRoom(room);
				_gui.dispatchEvent(new WindowEvent(_gui, WindowEvent.WINDOW_CLOSING));
			}
			
			

		});
		_gui = new ChatRoomGUI<IChatServer>(new IChatRoomModelAdpt<IChatServer>() {

			/**
			 * Add a user with specified IP address to the chatroom
			 * @param IPromote the IP address of the remote host
			 */
			@Override
			public void addUser(String IPromote) {
				mainModel.connectTo(IPromote, false);
				_model.addUser(IPromote);
			}

			/**
			 * remove a selected user from the chatroom
			 * @param selectedUser the user to be removed
			 */
			@Override
			public void removeUser(IChatServer selectedUser) {
				// TODO Auto-generated method stub

			}

			/**
			 * Send text message to all users in the chatroom
			 * @param text the text to be sent
			 */
			@Override
			public void send(String text) {
				// TODO Auto-generated method stub
				_model.send(text);
			}

			/**
			 * The method for getting all chatstubs in the chatrooms
			 * 
			 */
			@Override
			public HashSet<IChatServer> getChatStubs() {
				// TODO Auto-generated method stub
				return _model.getChatStubs();
			}

			/**
			 * The method to leave the chatroom
			 */
			@Override
			public void leaveRoom() throws RemoteException { 
				_model.leaveRoom();
			}

			/**
			 * The method to send a image to all users
			 */
			@Override
			public void sendImg() {
				// TODO Auto-generated method stub
				_model.sendImg();
			}

			/**
			 * The method to start game
			 * @param g the GameInfo of the game
			 */
			@Override
			public void startGame(GameInfo g) {
				// TODO Auto-generated method stub
				_model.startGame(g);
			}

			/**
			 * The method to go to a place with defined latitude and longitude
			 * @param latitude the string representation of the latitude
			 * @param longitude the string representation of the longitude
			 */
			@Override
			public void goLatLong(String latitude, String longitude) {
				try {
					_gui.setPosition(Position.fromDegrees(Double.parseDouble(latitude), Double.parseDouble(longitude), 4000));
				} catch (Exception e) {
					System.out.println("Improper latitude, longitude: " + latitude + ", " + longitude);
				}
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
