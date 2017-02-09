package common;

import java.io.Serializable;
import java.util.HashSet;
import java.util.UUID;

import common.msg.IChatMsg;

/**
 * IChatroom interface
 *
 */
public interface IChatroom extends Serializable {

	/**
	 * Get a unique ID of the chatroom
	 * @return A unique identifier of the chatroom
	 */
	UUID getId();

	/**
	 * Getter of the chatroom display name
	 * This is not exactly needed for the chatroom to function, but it is convenient to have
	 * @return the chatroom display name
	 */
	String getName();

	/**
	 * List of users in the chatroom
	 * @return A set of all the chatServer stubs in this chatroom
	 */
	HashSet<IChatServer> getChatServers();
	
	/**
	 * Add the specified chatserver to this chatroom.
	 * @param chatStub IChatServer stub to be added.
	 * @return  True if chatServer is added to the chatroom and false if chatServer is already in chatroom.
	 */
	boolean addChatServer(IChatServer chatStub);


	/**
	 * Remove the chatserver from this chatroom.
	 * @param chatServer  IChatServer stub to remove.
	 * @return  True if chatServer is removed from the chatroom and false if chatServer is not in chatroom.
	 */
	boolean removeChatServer(IChatServer chatServer);

	/**
	 * Send a message in a dp to all other users in the chatroom.
	 * @param dp - The message packet to be sent
	 */
	<S> void send(OurDataPacket<? extends IChatMsg, S> dp);

}
