package common.msg.chat;

import common.IChatServer;
import common.OurDataPacket;
import common.msg.IChatMsg;

/**
 * Super-interface for the two actual status message types
 * and no case in a processing visitor should be made for
 * IStatusMsg explicitly, only for its sub-classes.
 *
 */
public interface IStatusMsg extends IChatMsg {

	/**
	 * @return the text description of the status
	 */
	String getText();
	
	/**
	 * @returns the originally sent datapacket to which the status refers.
	 */
	OurDataPacket<?,IChatServer> getOriginalDataPacket(); 
}
