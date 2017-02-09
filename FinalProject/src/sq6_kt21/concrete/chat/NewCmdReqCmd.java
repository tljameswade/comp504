package sq6_kt21.concrete.chat;

import java.rmi.RemoteException;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.OurDataPacket;
import common.msg.chat.IAddCmdMsg;
import common.msg.chat.INewCmdReqMsg;
import provided.datapacket.DataPacketAlgo;

/**
 * The new command request command class to process a new command request msg
 * @author Kun Tian
 *
 */
public class NewCmdReqCmd extends AOurDataPacketAlgoCmd<INewCmdReqMsg, IChatServer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4431509414702371463L;
	
	/**
	 * The local command to model adapter
	 */
	private transient ICmd2ModelAdapter cmd2modelAdpt;
	
	/**
	 * The local dataPacketAlgo
	 */
	private transient DataPacketAlgo<Void, Object> dataPacketAlgo;
	
	/**
	 * The constructor for the new command request command class
	 * @param cmd2modelAdpt the local command to model adapter
	 * @param dataPacketAlgo the local data packet algo dictionary
	 */
	public NewCmdReqCmd(ICmd2ModelAdapter cmd2modelAdpt, DataPacketAlgo<Void, Object> dataPacketAlgo) {
		this.cmd2modelAdpt = cmd2modelAdpt;
		this.dataPacketAlgo = dataPacketAlgo;
	}
	
	@Override
	public Void apply(Class<?> index, OurDataPacket<INewCmdReqMsg, IChatServer> host, Object... params) {
		@SuppressWarnings("unchecked")
		OurDataPacket<IAddCmdMsg, IChatServer> dp = new OurDataPacket<IAddCmdMsg, IChatServer> (IAddCmdMsg.class, 
				
				new AddCmdMsg(
						(AOurDataPacketAlgoCmd<?, IChatServer>) dataPacketAlgo.getCmd(host.getData().getReqClassIdx()),
						host.getData().getReqClassIdx(), host.getData().getUUID()), 
				
				cmd2modelAdpt.getChatServer());
		try {
			host.getSender().receive(dp);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2modelAdpt = cmd2modelAdpt;
	}
	
}
