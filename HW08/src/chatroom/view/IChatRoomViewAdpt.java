package chatroom.view;

import java.awt.Container;

public interface IChatRoomViewAdpt {
	public void append(String user, String text);

	public Container getMiniGUI();
}