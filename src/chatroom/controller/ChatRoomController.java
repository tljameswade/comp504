package chatroom.controller;

import java.rmi.RemoteException;
import java.util.HashSet;
import chatapp.model.ChatAppModel;
import chatroom.model.ChatRoomModel;
import chatroom.model.IChatRoomModelAdpt;
import chatroom.view.ChatRoomGUI;
import chatroom.view.IChatRoomViewAdpt;
import common.IChatServer;
import common.IChatroom;
/**
 * controller for chatroom
 * @author Suozhi Qi, kejunliu
 *
 */
public class ChatRoomController {
	/**
	 * model for chatroom
	 */
	private ChatRoomModel _model;
	/**
	 * all chatserver in the chatroom
	 */
	private HashSet<IChatServer> chatServerProxys;
	/**
	 * gui for chatroom
	 */
	private ChatRoomGUI<IChatServer> _gui;
	/**
	 * constructor for chatroom
	 * @param room - chatroom
	 * @param mainModel - chatapp model
	 */
	public ChatRoomController(IChatroom room,ChatAppModel mainModel) {
		_model = new ChatRoomModel(new IChatRoomViewAdpt() {

			@Override
			public void append(String user, String text) {
				_gui.append(user, text);
			}
			
		});
		_gui = new ChatRoomGUI<IChatServer>(new IChatRoomModelAdpt<IChatServer>() {

			@Override
			public void addUser(String IPromote) {
				mainModel.connectTo(IPromote);
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
			
		});
	}
	
	/**
	 * start the chatroom controller
	 * @param chatServer - current user's chatserver in that chatroom
	 * @param chatServerProxys - all chatservers in that room
	 * @throws RemoteException
	 */
	public void start(IChatServer chatServer, HashSet<IChatServer> chatServerProxys) throws RemoteException{
		_model.start(chatServer);
		_gui.start(chatServerProxys);
	}
	/**
	 * set chatservers in that chatroom
	 * @param chatServerProxys
	 */
	public void setchatServers(HashSet<IChatServer> chatServerProxys){
		this.chatServerProxys = chatServerProxys;
		System.out.println("chatServerProxys size:  "+ chatServerProxys.size());
		_gui.start(chatServerProxys);
	}
	/**
	 * refresh the chatroom gui
	 */
	public void refresh(){
		_gui.refresh();
	}
}
