package chatroom.model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import java.util.HashSet;
import java.util.Iterator;
import java.util.HashSet;

import javax.swing.JPanel;


import chatroom.view.IChatRoomViewAdpt;
import common.ADataPacketAlgoCmdGroupF;
import common.DataPacketGroupF;
import common.IChatServer;
import common.IChatroom;
import common.ICmd2ModelAdapter;
import common.IUser;
import provided.datapacket.ADataPacket;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;
import provided.datapacket.DataPacketAlgo;
import provided.rmiUtils.IRMIUtils;
import provided.rmiUtils.RMIUtils;
import provided.util.IVoidLambda;

/**
 * The chatroom minimodel
 * @author Suozhi Qi, Kejun Liu
 *
 */
public class ChatRoomModel {
	
	/**
	 * output command used to put multiple strings up onto the view.
	 */
	private IVoidLambda<String> outputCmd = new IVoidLambda<String>() {

		@Override
		public void apply(String... params) {
			for (String s : params) {
				try {
					chatroomViewadpt.append(chatserver.getUser().getName(), s + "\n");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};
	
	
	/**
	 * The RMI Registry
	 */
	private Registry registry;

	/**
	 * Factory for the Registry and other uses.
	 */
	IRMIUtils rmiUtils = new RMIUtils(outputCmd);
	
	/**
	 * The chatserver stub in the mini-model
	 */
	private IChatServer chatserver;
	
	private IChatroom chatroom;
	
	/**
	 * The chatroom view adapter
	 */
	private IChatRoomViewAdpt chatroomViewadpt;
	
	/**
	 * The dataPacketAlgo
	 */
	private DataPacketAlgo<String, IChatServer> dataPacketAlgo = new DataPacketAlgo<>(null);
	
	/**
	 * The constructor
	 */
	public ChatRoomModel(IChatRoomViewAdpt chatroomViewadpt){
		this.chatroomViewadpt = chatroomViewadpt;
	}
	
	/**
	 * Start the mini-model
	 * @param chatServer 
	 * @throws RemoteException 
	 */
	public void start(IChatServer chatServer) throws RemoteException{
		this.chatserver = chatServer;
		this.chatroom = chatserver.getChatroom();
		initDataPacketAlgo();
		try {chatserver.setDataPacketAlgo(dataPacketAlgo);}
		catch (RemoteException e) {
			e.printStackTrace();
		}		
	}

	private class ProxyIChatServer implements IChatServer, Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 7179282090628974973L;
		/**
		 * The decoree
		 */
		private IChatServer stub;
		/**
		 * Constructor for the class
		 * @param stub The decoree
		 */
		public ProxyIChatServer(IChatServer stub){
			this.stub = stub;
		}
		/**
		 * Get the decoree
		 * @return the decoree
		 */
		@SuppressWarnings("unused")
		public IChatServer getStub() {
			return stub;
		}
		// --- Other methods and fields elided -----------

		/**
		 * Overridden equals() method to compare UUID's
		 * @return  Equal if UUID's are equal.  False otherwise.
		 */
		@Override
		public boolean equals(Object other){
			if(other instanceof IChatServer) { // make sure that other object is an IUser
				try {
					// Equality of IUsers is same as equality of UUID's.
					return stub.getUser().getId().equals(((IChatServer)other).getUser().getId());
				} catch (RemoteException e) {
					// Deal with the exception without throwing a RemoteException.
					System.err.println("ProxyUser.equals(): error getting UUID: "+ e);
					e.printStackTrace();
					// Fall through and return false
				}
			}
			return false;
		}		

		/**
		 * Overridden hashCode() method to create a hashCode from that is hashCode of the UUID since
		 * equality is based on equality of UUID.
		 * @return a hashCode of the UUID.	
		 */
		@Override
		public int hashCode(){
			try {
				// hashCode is shorter than UUID, but Java spec says that if two objects are equal then
				// their hashCodes must also be equal, which will be true here since equals() is based on 
				// UUID equality.  Java does NOT require that unequal entities have unequal hashCodes. 
				return stub.getUser().getId().hashCode();
			} catch (RemoteException e) {
				// Deal with the exception without throwing a RemoteException.
				System.err.println("ProxyStub.hashCode(): Error calling remote method on IUser stub: "+e);
				e.printStackTrace();
				return super.hashCode();  // return some sort of hashCode
			}
		}

		@Override
		public IUser getUser() throws RemoteException {
			// TODO Auto-generated method stub
			return stub.getUser();
		}

		@Override
		public IChatroom getChatroom() throws RemoteException {
			// TODO Auto-generated method stub
			return stub.getChatroom();
		}

		@Override
		public boolean joinChatroom(IChatServer chatStub) throws RemoteException {
			// TODO Auto-generated method stub
			return stub.joinChatroom(chatStub);
		}

		@Override
		public boolean leaveChatroom(IChatServer chatStub) throws RemoteException {
			// TODO Auto-generated method stub
			return stub.leaveChatroom(chatStub);
		}

		@Override
		public void receive(ADataPacket message) throws RemoteException {
			// TODO Auto-generated method stub
			stub.receive(message);
		}

		@Override
		public void setDataPacketAlgo(DataPacketAlgo<String, IChatServer> dataPacketAlgo) throws RemoteException {
			// TODO Auto-generated method stub
			stub.setDataPacketAlgo(dataPacketAlgo);
		}

		@Override
		public void refresh() throws RemoteException {
			// TODO Auto-generated method stub
			stub.refresh();
		}
		@Override
		public ADataPacketAlgoCmdGroupF<?> getNewCommand(Class<?> requestClass) throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}
		
		public String toString() {
			try {
				String s = stub.getUser().getName() + "@" + stub.getUser().getIP();
				return s;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
	
	public void initDataPacketAlgo() {
        dataPacketAlgo.setCmd(String.class, new ADataPacketAlgoCmdGroupF<String>() {

					/**
					 * The serial ID
					 */
					private static final long serialVersionUID = 7345061299313502581L;


					@Override
					public String apply(Class<?> index, DataPacketGroupF<String> host, IChatServer... params) {
						try {
							chatroomViewadpt.append(host.getSender().getUser().getName(), host.getData().toString());
						} catch (RemoteException e) {
							e.printStackTrace();
						};
						return null;
					}


					@Override
					public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
					}
        	
        });
	}
	
	/**
	 * Add user 
	 * @param IPremote the IP address of the user to be invited
	 */
	
	public void addUser(String friend) {

		Registry registry = rmiUtils.getRemoteRegistry(friend);
		try {
			IUser user = (IUser) registry.lookup(IUser.BOUND_NAME);
			System.out.println("add user (name): "+user.getName());
			IChatServer chatstub = user.createNewRoomServer(chatroom);
			
			for (IChatServer members: chatroom.getChatServers()){				
				if(members.joinChatroom(chatstub)){
//					members.refresh();
					chatroomViewadpt.append(members.getUser().getName(), "Successfully add " + members.getUser().getName() + " to chatroom!\n");
					}
				else {
					chatroomViewadpt.append(members.getUser().getName(), "Failed to add this member:" + members.getUser().getName() + "to chatroom!\n");
				}
			}
			for(IChatServer server: chatroom.getChatServers()){
				System.out.println(server.getUser().getIP());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Leave self from the chatroom
	 * 1. remove the chatroom from the user;
	 * 2. remove the user chatserver from other's chatroom
	 */
	public IChatServer leaveRoom() {
		try {
			//get the user's chatserver
			IUser user = this.chatserver.getUser();
			IChatroom room = this.chatserver.getChatroom();
			//remove the chatroom from the user
			user.removeRoom(room);
			
			HashSet<IChatServer> temp = new HashSet<IChatServer>();
			for (IChatServer server :room.getChatServers()) {
				if (!server.getUser().getId().equals(chatserver.getUser().getId()) ) {
					temp.add(server);
				}
			}
			Iterator<IChatServer> iterator = temp.iterator();
			 while(iterator.hasNext()) {
				 IChatServer server = iterator.next();
				 if(server == chatserver) {
					 iterator.remove();
				 }
				 server.leaveChatroom(chatserver);
			 }
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.chatserver;
	}
	/**
	 * Remove the selected user from the chatroom
	 * @param selectedUser the selected user
	 */
	public void removeUser(IChatServer selectedUser) {};
	
	/**
	 * Send a string text
	 * @param text the string text
	 */
	public void send(String text) {
		try {
			chatserver.getChatroom().send(new DataPacketGroupF<String>(String.class, text, chatserver));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	};
	
	public HashSet<IChatServer> getChatStubs() {
		HashSet<IChatServer> proxyChatServer = new HashSet<>();
		HashSet<IChatServer> chatStubs;
		try {
			chatStubs = chatserver.getChatroom().getChatServers();
			for (IChatServer stub: chatStubs){
				ProxyIChatServer proxyChatter = new ProxyIChatServer(stub);
				proxyChatServer.add(proxyChatter);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return proxyChatServer;
	}
	
	/**
	 * Exit the current chatroom
	 */
	public void exit() {};
}