package sq6_kt21.concrete.chat;

import java.util.UUID;

import common.msg.chat.INewCmdReqMsg;


/**
 * The new command request command msg class
 * @author Kun Tian
 *
 */
public class NewCmdReqMsg implements INewCmdReqMsg{

	/**
	 * 
	 */
	
	private UUID uuid;
	private Class<?> classIdx;
	
	/**
	 * The constructor
	 * @param _classIdx the class of the newCmdReqMsg
	 * @param _uuid the UUID for caching the data
	 */
	public NewCmdReqMsg(Class<?> _classIdx, UUID _uuid) {
		classIdx = _classIdx;
		uuid = _uuid;
	}
	private static final long serialVersionUID = -2793331233472679718L;

	@Override
	public Class<?> getReqClassIdx() {
		return classIdx;
	}

	@Override
	public UUID getUUID() {
		return uuid;
	}

}
