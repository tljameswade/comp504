package chatroom.model;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.UUID;

import common.IChatServer;
import common.IChatroom;
import provided.datapacket.ADataPacket;
/**
 * concrete chatroom
 * @author Suozhi Qi, kejunliu
 *
 */
public class Chatroom implements IChatroom{

	/**
	 * The serial number
	 */
	private static final long serialVersionUID = -6510484503569818881L;

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
	public Chatroom(String name){
		this.chatroomName = name;
		//chatServers.add(chatServer);
	}
	
	public boolean equals(Object o) {
		if (o != null) {
			if (o instanceof Chatroom) {
				return ((Chatroom) o).getId().equals(this.getId());
			}
		}
		return false;
	}
	
	
	@Override
	public UUID getId() {
		return this.chatroomId;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.chatroomName;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.chatroomName = name;
	}

	@Override
	public HashSet<IChatServer> getChatServers() {
		// TODO Auto-generated method stub
		return chatservers;
	}

	@Override
	public boolean addChatServer(IChatServer chatServer) {
		// TODO Auto-generated method stub
		return chatservers.add(chatServer);
	}

	@Override
	public boolean removeChatServer(IChatServer chatServer) {
		// TODO Auto-generated method stub
		return chatservers.remove(chatServer);
	}

	@Override
	public void send(ADataPacket message) {
		// TODO Auto-generated method stub
		(new Thread() {
            @Override
            public void run() {
                super.run();
                chatservers.iterator().forEachRemaining(
        				(chatserver) -> { 
        					try {chatserver.receive(message);}        					
        					catch(RemoteException e) {e.printStackTrace();}
        				});
            }            
        }).start();		
	}
	public String toString() {
		return this.chatroomName;
	}
}
