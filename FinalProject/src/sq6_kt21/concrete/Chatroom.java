package sq6_kt21.concrete;

import java.util.HashSet;
import java.util.UUID;

import common.IChatServer;
import common.IChatroom;
import common.OurDataPacket;
import common.msg.IChatMsg;

/**
 * The concrete chatroom class
 * @author suozhiqi
 *
 */
public class Chatroom implements IChatroom {


	private static final long serialVersionUID = -8937274963052934631L;

	/**
	 * The UUID of the chatroom
	 */
	private UUID chatroomId = UUID.randomUUID();

	/**
	 * The string representation of the chatroom
	 */
	private String chatroomName;

	/**
	 * A set of chatserver stub
	 */
	private HashSet<IChatServer> chatservers = new HashSet<>();

	/**
	 * The constructor of the Chatroom with a name and its creator (the default user)
	 * @param name - Chatroom name
	 * @param chatServer - The IChatServer associated with this chatroom
	 */
	public Chatroom(String name) {
		this.chatroomName = name;
		//chatServers.add(chatServer);
	}

	/**
	 * the equals method
	 */
	public boolean equals(Object o) {
		if (o != null) {
			if (o instanceof Chatroom) {
				return ((Chatroom) o).getId().equals(this.getId());
			}
		}
		return false;
	}

	/**
	 * get the UUID
	 */
	@Override
	public UUID getId() {
		return this.chatroomId;
	}

	/**
	 * Get the name of the chatroom
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.chatroomName;
	}

	/**
	 * Set the name of the chatroom
	 */
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.chatroomName = name;
	}

	/**
	 * Get the chatservers in the chatroom
	 */
	@Override
	public HashSet<IChatServer> getChatServers() {
		// TODO Auto-generated method stub
		return chatservers;
	}

	/**
	 * Add the chatserver
	 */
	public boolean addChatServer(IChatServer chatServer) {
		// TODO Auto-generated method stub
		return chatservers.add(chatServer);
	}

	@Override
	public boolean removeChatServer(IChatServer chatServer) {
		// TODO Auto-generated method stub
		return chatservers.remove(chatServer);
	}

	public String toString() {
		return this.chatroomName;
	}

	@Override
	public <S> void send(OurDataPacket<? extends IChatMsg, S> dp) {
		// TODO Auto-generated method stub
		
	}



}
