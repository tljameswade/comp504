package sq6_kt21.concrete.chat;

import java.rmi.RemoteException;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.OurDataPacket;
import common.msg.chat.ILeaveMsg;

/**
 * The command class to process a leave msg
 * @author Kun Tian
 *
 */
public class LeaveMsgCommand extends AOurDataPacketAlgoCmd<ILeaveMsg, IChatServer> {

	
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1274028707452344933L;
	
	transient private ICmd2ModelAdapter cmd2ModelAdpt = null;

	/**
	 * The leave Msg command constructor
	 * @param cmd2ModelAdpt1 The local command to model adapter
	 */
	public LeaveMsgCommand(ICmd2ModelAdapter cmd2ModelAdpt1) {
		cmd2ModelAdpt = cmd2ModelAdpt1;
	}
	
	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}


	@Override
	public Void apply(Class<?> index, OurDataPacket<ILeaveMsg, IChatServer> host, Object... params) {
		//ILeaveMsg leaveMsg = host.getData();
		try {
			IChatServer chatServer = host.getSender();
			IChatServer localServer = cmd2ModelAdpt.getChatServer();
			localServer.getChatroom().removeChatServer(chatServer);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
