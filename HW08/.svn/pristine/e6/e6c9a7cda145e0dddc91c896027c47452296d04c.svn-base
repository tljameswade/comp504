package chatroom.model;

import java.util.HashSet;

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

	/**
	 * Get the chatstubs in the chatroom
	 * @return a set of the chat stubs
	 */
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

	public void leaveRoom();

	public void sendImg();
}
