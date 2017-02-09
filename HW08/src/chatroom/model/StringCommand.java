package chatroom.model;

import java.awt.Container;
import java.rmi.RemoteException;

import javax.swing.JLabel;

import common.ADataPacketAlgoCmdGroupF;
import common.DataPacketGroupF;
import common.IChatServer;
import common.ICmd2ModelAdapter;

public class StringCommand extends ADataPacketAlgoCmdGroupF<String> {

	/**
	 * String command to process string
	 */

	private static final long serialVersionUID = -6829927260727331813L;

	transient private ICmd2ModelAdapter cmd2ModelAdpt = null;

	@Override
	public String apply(Class<?> index, DataPacketGroupF<String> host, IChatServer... params) {
		try {
			Container container = cmd2ModelAdpt.getScrollable();
			JLabel label = new JLabel(host.getSender().getUser().getName() + ": " + host.getData().toString());

			container.add(label);

			container.revalidate();

			//chatroomViewadpt.append(host.getSender().getUser().getName(), host.getData().toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		// TODO Auto-generated method stub
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

}
