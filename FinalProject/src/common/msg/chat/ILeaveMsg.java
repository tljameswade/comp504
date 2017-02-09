package common.msg.chat;

import common.msg.IChatMsg;

/**
 * This message is sent to all chatservers in the chatroom to notify them to remove leaving user's chatserver
 * 
 * Implementation advice for LeaveMsgCmd:
 * Remove the sender from the ChatServerList of your Chatroom
 *
 * The proper protocol to leave a room is (IChatServer X is leaving a room that IChatServer Y is still in):
 * 1.      X removes themselves from their local copy of the room (IChatroom.removeChatServer())
 * 2.      X tells everyone in the room that they are leaving (ILeaveMsg)
 * 3.      Y receives the ILeaveMsg and removes the sender, X, from their local copy of the room (IChatroom.removeChatServer())
 */
public interface ILeaveMsg extends IChatMsg {

	

}
