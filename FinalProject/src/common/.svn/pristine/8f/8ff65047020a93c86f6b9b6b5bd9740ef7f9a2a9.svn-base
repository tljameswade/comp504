package common.msg.chat;

import java.util.UUID;

import common.msg.IChatMsg;

/**
 * !!!!Advice for implementation of UNKOWN DATA!!!!!
 * When A send B an unkown data:
 * [in defaultCmd of B] 
 * 		#1 Generate UUID uuid and use this uuid to generate a MDD key
 * 			e.g. MixedDataKey<Object> key = new MixedDataKey<Object> (uuid, "", Object.class);
 * 		#2 Cache the unkown data in MDD
 * 			e.g. Cmd2ModelAdpt.putIntoLocalDict(key, host);
 * 		#3 Create a INewCmdReqMsg accordingly
 * 			e.g. MyNewCmdReqMsg msg = new MyNewCmdReqMsg(index, uuid);
 * 		#4 Ask the sender to receive the MyNewCmdReqMsg
 * 
 * [in NewCmdMsgCmd of A]
 * 		#1 Get the DataPacketAlgo (algo) of your ChatServer
 * 			*Add getter in you ChatServer for this algo this cmd is out of your miniModel;
 * 		#2 Get cmd according to the type of this cmd (index)
 * 		#3 Send back to the receiver a IAddCmdMsg
 * 
 * [in AddCmdMsg of B]
 * 		#1 Set the new cmd in local DataPacketAlgo.
 * 			*Add getter in you ChatServer for this algo this cmd is out of your miniModel;
 * 		#2 Generate MDD key according to the UUID of AddCmdMsg
 *      #2 Retrieve the corresponding data in MDD with the key and execute it 
 *      
 *      
 * INewCmdReqMsg interface is used to ask for a new cmd from the sender.
 * Implementation advice:
 * #1 Create a field and a getter for type of this cmd so that corresponding cmd can be found.
 *    E.g. Class<?> index;
 * #2 Create a field and a getter for the UUID of the data cached in MDD
 *    E.g. UUID uuid;
 * #3 Use index and uuid to construct this msg
 * 
 * 
 * 
 * @author Evita
 */
public interface INewCmdReqMsg extends IChatMsg {

	
	public Class<?> getReqClassIdx();
	
	public UUID getUUID();
}
