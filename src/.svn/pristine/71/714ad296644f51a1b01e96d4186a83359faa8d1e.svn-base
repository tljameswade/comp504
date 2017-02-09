package chatroom.model;

import java.util.HashSet;

import common.IChatServer;
/**
 * adapter from chat room view to chat room model
 * @author Suozhi Qi, kejunliu
 *
 */
public interface IChatRoomModelAdpt<TList> {

	/**
	 * Add user
	 * @param IPremote the IP address of the user to be invited
	 */
	public void addUser(String IPremote);
	
	/**
	 * Remove the selected user from the chatroom
	 * @param selectedUser the selected user
	 */
	public void removeUser(TList selectedUser);
	
	public HashSet<TList> getChatStubs();
	
	/**
	 * Send a string text
	 * @param text the string text
	 */
	public void send(String text);
	
	/**
	 * Exit the current chatroom
	 */
	public void exit();
	/**
	 * leave the current chat room
	 */
	public void leaveRoom();
}
