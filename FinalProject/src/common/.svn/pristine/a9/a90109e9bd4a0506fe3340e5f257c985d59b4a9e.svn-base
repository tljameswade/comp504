package common.msg.user;

import common.IChatroom;
import common.msg.IUserMsg;

/**
 * IInvite2RoomMsg is sent to the user you want to invite to your chatroom
 * When implementing it, you should create a IChatroom field and use it to construct this object
 * E.g. IChatroom chatroom;
 *      MyInvite2RoomMsg(IChatroom cr);
 * Also there is a getter to get the chatroom.
 * 
 * 
 * Implementation advice for Invite2RoomMsgCmd:
 * #1 Create localChatroom and localChatServer
 * #2 Add all the ChatServers in the chatroom (including localChatServer) to the localChatroom's ChatServerlist 
 *    and add localChatroom to the IUser's chatroom list
 * #3 Send AddMeMsg(localChatServer wrapped inside) to all the original chatServers
 * @author Evita
 *
 */
public interface IInvite2RoomMsg extends IUserMsg {

	public IChatroom getChatroom();

}
