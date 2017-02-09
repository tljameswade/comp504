package chatapp.view;

import chatroom.controller.ChatRoomController;
import chatroom.model.ChatRoomModel;
import common.IChatroom;
/**
 * 
 * @author Suozhi Qi, kejunliu
 *
 */
public interface IChatAppViewAdpt {
	/**
	 * create chat room
	 * @param name - chatroom's name
	 */
	public void createChatRoom(String name);
	/**
	 * set the name of the user
	 * @param name - user's name
	 */
	public void setName(String name);
	/**
	 * connect to specific ip address
	 * @param ip - ip address
	 */
	public void connect(String ip);
	/**
	 * join chat room
	 * @param chatroom - the room selected to join in
	 */
	public void joinChatRoom(IChatroom chatroom);
	/**
	 * Shut down the chatapp
	 */
	public void shutDown();
	/**
	 * A NULL_OBJECT instance of IChatAppViewAdpt
	 */
	public IChatAppViewAdpt NULL_OBJECT = new IChatAppViewAdpt(){

		@Override
		public  void createChatRoom(String name) {
			// TODO Auto-generated method stub
		}

		@Override
		public void setName(String name) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void connect(String ip) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void joinChatRoom(IChatroom chatroom) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void shutDown() {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	
}
