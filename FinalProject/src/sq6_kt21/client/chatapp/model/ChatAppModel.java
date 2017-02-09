package sq6_kt21.client.chatapp.model;

import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;

import common.IChatServer;
import common.IChatroom;
import common.IUser;
import common.OurDataPacket;
import common.msg.IChatMsg;
import common.msg.chat.IAddMeMsg;
import common.msg.chat.ILeaveMsg;
import provided.datapacket.DataPacketAlgo;
import provided.rmiUtils.IRMIUtils;
import provided.rmiUtils.IRMI_Defs;
import provided.rmiUtils.RMIUtils;
import provided.util.IVoidLambda;
import sq6_kt21.client.chatapp.controller.IMain2MiniAdpt;
import sq6_kt21.concrete.ChatServer;
import sq6_kt21.concrete.Chatroom;
import sq6_kt21.concrete.User;
import sq6_kt21.concrete.chat.AddMeMsg;
import sq6_kt21.concrete.chat.LeaveMsg;

/**
 * The model of the client system.
 * 
 * @author Suozhi Qi, kejunliu
 * 
 */
public class ChatAppModel {
	public boolean connected = false;
	public int countRoom = 0;
	public IUser user;
	/**
	 * Adapter to the mini-View and main Model
	 */
	private HashMap<IChatroom, IMain2MiniAdpt> miniMVCAdpts = new HashMap<IChatroom, IMain2MiniAdpt>();
	/**
	 * The RMI Registry
	 */
	private Registry registry;
	/**
	 * output command used to put multiple strings up onto the view.
	 */
	private IVoidLambda<String> outputCmd = new IVoidLambda<String>() {

		@Override
		public void apply(String... params) {
			for (String s : params) {
				view.append(s + "\n");
			}
		}
	};

	/**
	 * Factory for the Registry and other uses.
	 */
	private IRMIUtils rmiUtils = new RMIUtils(outputCmd);

	/**
	 * Adapter to the view
	 */
	private IChatAppModelAdpt view;

	/**
	 * Constructor for the class
	 * 
	 * @param view - The adapter to the view.
	 */
	public ChatAppModel(IChatAppModelAdpt view) {
		this.view = view;
	}
	

	/**
	 * THe proxyIChatServer class
	 * @author swong
	 *
	 */
	
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
		public ProxyIChatServer(IChatServer stub) {
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
		/*
		// --- Other methods and fields elided -----------
		
		/**
		 * Overridden equals() method to compare UUID's
		 * @return  Equal if UUID's are equal.  False otherwise.
		 */
		/*
		@Override
		public boolean equals(Object other) {
			if (other instanceof IChatServer) { // make sure that other object is an IUser
				try {
					// Equality of IUsers is same as equality of UUID's.
					return stub.getUser().getId().equals(((IChatServer) other).getUser().getId());
				} catch (RemoteException e) {
					// Deal with the exception without throwing a RemoteException.
					System.err.println("ProxyUser.equals(): error getting UUID: " + e);
					e.printStackTrace();
					// Fall through and return false
				}
			}
			return false;
		}
		*/
		
		/**
		 * Overridden hashCode() method to create a hashCode from that is hashCode of the UUID since
		 * equality is based on equality of UUID.
		 * @return a hashCode of the UUID.	
		 */
		/*
		@Override
		public int hashCode() {
			try {
				// hashCode is shorter than UUID, but Java spec says that if two objects are equal then
				// their hashCodes must also be equal, which will be true here since equals() is based on 
				// UUID equality.  Java does NOT require that unequal entities have unequal hashCodes. 
				return stub.getUser().getId().hashCode();
			} catch (RemoteException e) {
				// Deal with the exception without throwing a RemoteException.
				System.err.println("ProxyStub.hashCode(): Error calling remote method on IUser stub: " + e);
				e.printStackTrace();
				return super.hashCode(); // return some sort of hashCode
			}
		}
		*/

		/**
		 * Get user method
		 */
		@Override
		public IUser getUser() throws RemoteException {
			// TODO Auto-generated method stub
			return stub.getUser();
		}

		/**
		 * Get chatroom method
		 */
		@Override
		public IChatroom getChatroom() throws RemoteException {
			// TODO Auto-generated method stub
			return stub.getChatroom();
		}

		/**
		 * Join chatroom method
		 */
		/*
		public boolean joinChatroom(IChatServer chatStub) throws RemoteException {
			// TODO Auto-generated method stub
			return stub.joinChatroom(chatStub);
		}
		*/

		/**
		 * Leave the chatroom with the chatserver
		 */
		/*
		public boolean leaveChatroom(IChatServer chatStub) throws RemoteException {
			// TODO Auto-generated method stub
			return stub.leaveChatroom(chatStub);
		}
		*/

		/**
		 * Set the data packet algo
		 */
		
		@SuppressWarnings("unused")
		public void setDataPacketAlgo(DataPacketAlgo<Void, Object> dataPacketAlgo) throws RemoteException {
			// TODO Auto-generated method stub
			((ChatServer) stub).setDataPacketAlgo(dataPacketAlgo);
		}

		@Override
		public <S> void receive(OurDataPacket<? extends IChatMsg, S> dp) throws RemoteException {
			// TODO Auto-generated method stub
			stub.receive(dp);
		}
	}

	/**
	 * Starts the model by setting all the required RMI system properties,
	 * starts up the class server and installs the security manager.
	 */
	public void start() {
		rmiUtils.startRMI(IRMI_Defs.CLASS_SERVER_PORT_SERVER);

		try {
			view.setRemoteHost(rmiUtils.getLocalAddress()); //TODO Is this stored somewhere?
			user = new User("default", rmiUtils.getLocalAddress());
			view.append("Create User " + user.getName() + "\n");

			registry = rmiUtils.getLocalRegistry();
			IUser stub = (IUser) UnicastRemoteObject.exportObject(user, IUser.BOUND_PORT_SERVER);
			view.append("Found registry: " + registry + "\n");

			registry.rebind(IUser.BOUND_NAME, stub);
			view.append("IUser bound to " + IUser.BOUND_NAME + "\n");
			

		} catch (Exception e) {
			System.err.println("Error getting local address: " + e);
		}
	}
	/**
	 * Stops the client by shutting down the class server.
	 */
	public void stop() {
		System.out.println("ClientModel.stop(): client is quitting.");
		try {
			rmiUtils.stopRMI();

		} catch (Exception e) {
			System.err.println("ClientModel.stop(): Error stopping class server: " + e);
		}
		System.exit(0);
	}

	/**
	 * Connects to the given remote host and retrieves the stub to the ICompute object bound 
	 * to the ICompute.BOUND_NAME name in the remote Registry on port 
	 * IRMI_Defs.REGISTRY_PORT.  
	 * 
	 * @param remoteHost The IP address or host name of the remote server.
	 * @return  A status string on the connection.
	 */
	public String connectTo(String remoteHost, boolean connected) {
		try {
			view.append("Locating registry at " + remoteHost + "...\n");
			Registry registry = rmiUtils.getRemoteRegistry(remoteHost);
			view.append("Found registry: " + registry + "\n");
			IUser stub = (IUser) registry.lookup(IUser.BOUND_NAME);
			view.append("Found remote Compute object: " + stub + "\n");
			view.setChatroomList(stub.getChatrooms());
		} catch (Exception e) {
			view.append("Exception connecting to " + remoteHost + ": " + e + "\n");
			e.printStackTrace();
			return "No connection established!";
		}
		return "Connection to " + remoteHost + " established!";
	}

	/**
	 * pop a chat room
	 * @param chatroom the chatroom should be poped
	 * @param chatserver the user's chatserver in that chatroom
	 * @throws AccessException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public void popChatRoom(IChatroom chatroom, IChatServer chatserver)
			throws AccessException, RemoteException, NotBoundException {
		IChatServer chatStub;
		try {
			chatStub = (IChatServer) UnicastRemoteObject.exportObject(chatserver, IChatServer.BOUND_PORT_SERVER);
			registry.rebind(IUser.BOUND_NAME + chatroom.hashCode(), chatStub);
			chatroom.addChatServer(chatStub);
		} catch (RemoteException e) {
			// TODO Auto-generated catch bloc
			System.out.println("pop chat room: find-> " + IUser.BOUND_NAME + chatroom.hashCode());
			chatStub = (IChatServer) registry.lookup(IUser.BOUND_NAME + chatroom.hashCode());
			e.printStackTrace();
		}
		((User)user).addRoom(chatroom);
		view.append("Add new chatroom <" + chatroom.getName() + "> to user <" + user.getName() + ">\n");
		//add all chatservers in that room to hashset
		HashSet<IChatServer> proxy = new HashSet<IChatServer>();
		for (IChatServer item : chatserver.getChatroom().getChatServers()) {
			IChatServer proxyChatServer = new ProxyIChatServer(item);
			proxy.add(proxyChatServer);
		}
		IMain2MiniAdpt miniMVCAdpt = view.makeMini(chatserver, proxy);
		miniMVCAdpts.put(chatroom, miniMVCAdpt);
		countRoom++;
	}

	/**
	 * create a new chat room
	 * @param name the new chat room's name
	 * @throws RemoteException
	 */
	public void createChatRoom(String name) throws RemoteException {

		try {
			IChatServer chatStub;
			// make a new chatroom with supplied name
			IChatroom newRoom = new Chatroom(name);
			view.append("Make new ChatRoom: " + newRoom.getName() + "\n");
			// make a new chatserver stub for user in the newchatroom
			ChatServer newChatServer = new ChatServer(user, newRoom);
			view.append("Make new ChatServer: <User:" + newChatServer.getUser().getName() + ", ChatServer: "
					+ newChatServer.getChatroom().getName() + ">\n");
			//get a chatstub for new chatserver 
			chatStub = (IChatServer) UnicastRemoteObject.exportObject(newChatServer, IChatServer.BOUND_PORT_SERVER);
			registry.rebind(IUser.BOUND_NAME + newRoom.hashCode(), chatStub);
			view.append("Make new ChatServer Stub for chatserver.\n");
			//add chatstub to newRoom
			newRoom.addChatServer(chatStub);
			view.append("Add new ChatServer Stub to newRoom.\n");
			//add newchatroom to the user chatroom lists
			((User)user).addRoom(newRoom);
			view.append("Add new chatroom <" + newRoom.getName() + "> to user <" + user.getName() + ">\n");
			//add all chatservers in that room to hashset
			HashSet<IChatServer> proxy = new HashSet<IChatServer>();
			for (IChatServer item : newChatServer.getChatroom().getChatServers()) {
				IChatServer proxyChatServer = new ProxyIChatServer(item);
				proxy.add(proxyChatServer);
			}
			IMain2MiniAdpt miniMVCAdpt = view.makeMini(newChatServer, proxy);
			miniMVCAdpts.put(newRoom, miniMVCAdpt);
			countRoom++;
		} catch (Exception e) {
			view.append("Error creating chatroom!");
		}

	}

	/**
	 * set username
	 * @param name user's name
	 */
	public void setName(String name) {
		try {
			((User)user).setName(name);
			view.append("Set username to " + user.getName() + "\n");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * join a chat room
	 * @param room the room to be joint
	 */
	public void joinChatRoom(IChatroom room) {
		view.append("Join ChatRoom" + room.getName());
		countRoom++;
		IChatroom chatroom = new Chatroom("Chatroom" + countRoom);
		IChatServer chatServer = new ChatServer(user, chatroom);
		try {
			view.append("Start joining..");
			IChatServer chatStub = (IChatServer) UnicastRemoteObject.exportObject(chatServer,
					IChatServer.BOUND_PORT_SERVER + countRoom);
			for (IChatServer members : room.getChatServers()) {
				chatroom.addChatServer(members);
				OurDataPacket<IAddMeMsg, IChatServer> datapacket = new OurDataPacket<IAddMeMsg, IChatServer>(IAddMeMsg.class, new AddMeMsg(chatStub), chatStub);
				members.receive(datapacket);
			}
			
			chatroom.addChatServer(chatStub);
			
			((User)user).addRoom(chatroom);
			registry.rebind(IUser.BOUND_NAME + chatroom.hashCode(), chatStub);
			HashSet<IChatServer> proxyChatServers = new HashSet<IChatServer>();
			for (IChatServer stub : chatroom.getChatServers()) {
				ProxyIChatServer proxyChatServer = new ProxyIChatServer(stub);
				proxyChatServers.add(proxyChatServer);
			}
			IMain2MiniAdpt miniMVCAdpt = view.makeMini(chatServer, proxyChatServers);
			miniMVCAdpts.put(chatroom, miniMVCAdpt);
			countRoom++;
			view.append("Success!");
			//			miniMVCAdpt.refresh();
			
		} catch (Exception e) {
			view.append("Fail to join the room!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Leave a specific chatroom
	 * @param room The room to leave
	 * @throws RemoteException The remote exception
	 */
	public void leaveChatRoom(IChatroom room) throws RemoteException {
		view.append("Leave ChatRoom" + room.getName());
		countRoom--;
		
		for(IChatServer chatserver : room.getChatServers()){
			
				if(chatserver.getUser().getId().equals(user.getId()) == false) continue;

				((User)user).removeRoom(room);
				
				System.out.println("now, user has chatroom: ");
				
				for (IChatroom hasroom : user.getChatrooms()) {
					System.out.println(hasroom.getName());
				}
	
				HashSet<IChatServer> others = room.getChatServers();
				for (IChatServer other : others) {
					if(other.getUser().getId().equals(user.getId())) continue;
					other.receive(new OurDataPacket<ILeaveMsg,IChatServer>(ILeaveMsg.class, new LeaveMsg(chatserver), chatserver));
				}
				System.out.println("now, chatroom has user: ");
				for (IChatServer item : room.getChatServers()) {
					System.out.println(item.getUser());
				}
				
				System.out.println("removed self: " + user.getName() + " from " + room.getName());
				
			}
		
	}
}
