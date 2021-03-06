package chatroom.model;

import java.awt.Container;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import common.ADataPacketAlgoCmdGroupF;
import common.DataPacketGroupF;
import common.IChatServer;
import common.ICmd2ModelAdapter;

public class ImageCommand extends ADataPacketAlgoCmdGroupF<ImageIcon> {

	/**
	 * Image command to process images
	 */
	private static final long serialVersionUID = -5798488755739020735L;
	transient private ICmd2ModelAdapter cmd2ModelAdpt = null;

	@Override
	public String apply(Class<?> index, DataPacketGroupF<ImageIcon> host, IChatServer... params) {
		try {
			System.out.println((host.getSender().getUser().getName()));
			Container container = cmd2ModelAdpt.getScrollable();
			JLabel label1 = new JLabel(host.getSender().getUser().getName() + ": ");
			System.out.println(host.getData().toString());
			JLabel label2 = new JLabel(host.getData());
			container.add(label1);
			container.add(label2);
			container.revalidate();
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
