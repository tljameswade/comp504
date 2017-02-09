package sq6_kt21.concrete.chat;

import common.IChatServer;
import common.msg.chat.ILeaveMsg;

/**
 * The leave msg class
 * @author Kun Tian
 *
 */
public class LeaveMsg implements ILeaveMsg {

	/**
	 * The serial version ID
	 */
	private static final long serialVersionUID = 4610816515339743730L;
	
	/**
	 * The chat stub of the user to be removed
	 */
	IChatServer chatStub;
	
	/**
	 * The constructor of the leave MSg
	 * @param _chatSub the chat stub of the user to leave to room
	 */
	public LeaveMsg(IChatServer _chatSub) {
		this.chatStub = _chatSub;
	}
	
	public IChatServer getChatStub() {
		return chatStub;
	}

}
