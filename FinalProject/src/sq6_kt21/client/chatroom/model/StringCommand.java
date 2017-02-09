package sq6_kt21.client.chatroom.model;

import java.rmi.RemoteException;

import javax.swing.JComponent;
import javax.swing.JLabel;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.IComponentFactory;
import common.OurDataPacket;

/**
 * The string command class
 * @author Kun Tian
 *
 */
public class StringCommand extends AOurDataPacketAlgoCmd<String, IChatServer> {

	/**
	 * String command to process string
	 */

	private static final long serialVersionUID = -6829927260727331813L;

	transient private ICmd2ModelAdapter cmd2ModelAdpt = null;

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		// TODO Auto-generated method stub
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}


	@Override
	public Void apply(Class<?> index, OurDataPacket<String, IChatServer> host, Object... params) {
		IComponentFactory factory = new IComponentFactory() {
			
			@Override
			public JComponent make() {
				String str = "";
				try {
					str = host.getSender().getUser().getName() + ": " + host.getData().toString();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JLabel lab = new JLabel(str);
				return lab;
			}
		};
		cmd2ModelAdpt.buildComponentInScrollable(factory);
		//chatroomViewadpt.append(host.getSender().getUser().getName(), host.getData().toString());
		return null;
	}

}
