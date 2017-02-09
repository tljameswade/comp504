package common.msg.chat;

import common.msg.IChatMsg;
/**
 * This message is sent to all chatservers in the chatroom. 
 * It contains a string which is a text message the user want to say in the chatroom.
 */
public interface ITextMsg extends IChatMsg{
	/**
	 * Get the content of the message
	 * @return The content of the text message
	 */
	public String getContent();
}
