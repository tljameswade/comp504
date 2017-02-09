package sq6_kt21.concrete.chat;

import java.rmi.RemoteException;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.OurDataPacket;
import common.msg.chat.IAddMeMsg;


/**
 * The add me msg command to process an add me msg
 * @author Kun Tian
 *
 */
public class AddMeMsgCommand extends AOurDataPacketAlgoCmd<IAddMeMsg, IChatServer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9066620028941235693L;
	
	transient private ICmd2ModelAdapter cmd2ModelAdpt = null;
	
	/**
	 * The add me msg command constructor
	 * @param cmd2ModelAdpt1 the local cmd to model adapter
	 */
	public AddMeMsgCommand(ICmd2ModelAdapter cmd2ModelAdpt1) {
		cmd2ModelAdpt = cmd2ModelAdpt1;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

	@Override
	public Void apply(Class<?> index, OurDataPacket<IAddMeMsg, IChatServer> host, Object... params) {
		@SuppressWarnings("unused")
		IAddMeMsg addMsg = host.getData();
		try {
			System.out.println("apply");
//			IChatServer chatServer = addMsg.getChatStub();
			IChatServer chatServer = host.getSender();
			IChatServer localServer = cmd2ModelAdpt.getChatServer();
			localServer.getChatroom().addChatServer(chatServer);
			System.out.println("applied");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
