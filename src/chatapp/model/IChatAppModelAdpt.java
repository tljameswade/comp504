package chatapp.model;

import java.util.HashSet;

import chatapp.controller.IMain2MiniAdpt;
import common.IChatServer;
import common.IChatroom;
import common.IUser;
/**
 * adapter from chatapp view to chatapp model
 * @author Suozhi Qi, kejunliu
 *
 */
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
	 * make chatroom mvc
	 * @param newChatServer - chatserver associated with user in that room
	 * @param proxy - all chatservers in that room
	 * @return
	 */
	public IMain2MiniAdpt makeMini(IChatServer newChatServer, HashSet<IChatServer> proxy);
	/**
	 * set chatrooms
	 * @param chatrooms chatrooms user can join
	 */
	public void setChatroomList(HashSet<IChatroom> chatrooms);
}
