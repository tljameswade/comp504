package chatroom.view;

/**
 * Adapter from chat room model to chat room view
 * @author Suozhi Qi, kejunliu
 *
 */
public interface IChatRoomViewAdpt {
	/**
	 * append message to gui
	 * @param user - user name
	 * @param text - text msg
	 */
	public void append(String user, String text);
}
