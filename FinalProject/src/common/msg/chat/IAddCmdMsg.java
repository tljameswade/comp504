package common.msg.chat;

import java.util.UUID;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.msg.IChatMsg;

/**
 * IAddCmdMsg interface is used to send a new cmd to the receiver.
 * 
 * Implementation advice:
 * #1 Create fields for Cmd, indexOfCmd and UUID
 * #2 This UUID is same with the MDD key to get the data cached in MDD
 * #3 Use these three fields to construct this Msg
 *      e.g. MyAddCmdMsg(OurDataPacketAlgoCmd<?> myCmd, Class<?> index, UUID uuid);
 * 
 * Implementation advice for AddCmdMsgCmd:
 * #1 Set the new cmd in local DataAlgo.
 * #2 Extract the corresponding message and process it  
 * @author Evita
 *
 */
public interface IAddCmdMsg extends IChatMsg {

	
	public AOurDataPacketAlgoCmd<?,IChatServer> getCmd();

	public Class<?> getClassIdx();
	
	public UUID getUUID();
}
