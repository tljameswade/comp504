package sq6_kt21.concrete.chat;

import java.util.UUID;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.msg.chat.IAddCmdMsg;

/**
 * The concrete add cmd msg
 * @author Kun Tian
 *
 */
public class AddCmdMsg implements IAddCmdMsg {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1550203420049107419L;
	IChatServer chatStub;
	AOurDataPacketAlgoCmd<?, IChatServer> cmd;
	Class<?> cls;
	UUID uuid;

	/**
	 * The constructor
	 * @param command the command for this msg
	 * @param c the class of the msg
	 * @param id the UUID
	 */
	public AddCmdMsg(AOurDataPacketAlgoCmd<?, IChatServer> command, Class<?> c, UUID id) {
		this.cmd = command;
		this.cls = c;
		this.uuid = id;
	}

	@Override
	public Class<?> getClassIdx() {
		return cls;
	}

	@Override
	public AOurDataPacketAlgoCmd<?, IChatServer> getCmd() {
		// TODO Auto-generated method stub
		return cmd;
	}

	@Override
	public UUID getUUID() {
		// TODO Auto-generated method stub
		return uuid;
	}
}
