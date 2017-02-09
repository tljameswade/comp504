package chatapp.controller;

import java.util.HashSet;

import common.IChatServer;
/**
 * adapter from chatapp to chatroom
 * @author Suozhi Qi, kejunliu
 *
 */
public interface IMain2MiniAdpt {
	/**
	 * refresh the member list
	 */
	public void refresh();
	/**
	 * set chatserver
	 * @param chatServerProxys
	 */
	void setchatServers(HashSet<IChatServer> chatServerProxys);
}
