package chatapp.model;

import java.util.HashSet;

import chatapp.controller.IMain2MiniAdpt;
import common.IChatServer;
import common.IChatroom;

public interface IChatAppModelAdpt {
	/**
	 * Appends the string to the end of the text display on the view The view
	 * does NOT automatically add a linefeed to the end of the string!
	 * 
	 * @param s
	 *            The String to append
	 */
	public void append(String s);

	/**
	 * Sets the displayed remote host address.
	 * @param hostAddress The IP address or host name of the remote host.
	 */
	public void setRemoteHost(String hostAddress);

	/**
	 * Add friend
	 * @param user the user to be added
	 */
	public void addFriend(User user);

	/**
	 * Make a mini MVC
	 * @param newChatServer the Chatserver
	 * @param proxy the hashset of Ichatserver stored
	 * @return the mainto mini adpater
	 */
	public IMain2MiniAdpt makeMini(IChatServer newChatServer, HashSet<IChatServer> proxy);

	public void setChatroomList(HashSet<IChatroom> chatrooms);
}
