package sq6_kt21.client.chatroom.view;

import java.awt.Container;
import java.rmi.RemoteException;

/**
 * The model to view adapter
 * @author Kun Tian, Suozhi Qi
 *
 */
public interface IChatRoomViewAdpt {
	
	/**
	 * The method to append text to the chatroom GUI
	 * @param user The sender
	 * @param text the text to be sent
	 */
	public void append(String user, String text);

	public Container getMiniGUI();
	
	public Container getmapPanel();
	
	public void closeRoom() throws RemoteException;
}
