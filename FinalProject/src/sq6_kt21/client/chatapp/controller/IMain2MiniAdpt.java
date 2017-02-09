package sq6_kt21.client.chatapp.controller;

import java.util.HashSet;

import common.IChatServer;

/**
 * The main to mini adapter
 * @author Suozhi Qi, Kun Tian
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
