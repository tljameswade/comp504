package sq6_kt21.client.chatroom.model;

import java.rmi.RemoteException;
import java.util.HashSet;


/**
 * The chatroom view to model adapter
 * @author Kun Tian
 *
 * @param <TList> The generic type
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
	 * @throws RemoteException 
	 */

	public void leaveRoom() throws RemoteException;

	public void sendImg();
	
	public void startGame(GameInfo g);

	/**
	 * The method to go to a specific person with provided longitude and latitude
	 * @param text the latitude
	 * @param text2 the longitude
	 */
	public void goLatLong(String text, String text2);
}
