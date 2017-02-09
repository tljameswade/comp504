package sq6_kt21.client.chatroom.model;

import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.IComponentFactory;
import common.OurDataPacket;

/**
 * The image command class
 * @author Kun Tian
 *
 */
public class ImageCommand extends AOurDataPacketAlgoCmd<ImageIcon, IChatServer> {

	/**
	 * Image command to process images
	 */
	private static final long serialVersionUID = -5798488755739020735L;
	transient private ICmd2ModelAdapter cmd2ModelAdpt = null;

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		// TODO Auto-generated method stub
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

	@Override
	public Void apply(Class<?> index, OurDataPacket<ImageIcon, IChatServer> host, Object... params) {
		IComponentFactory factory1 = new IComponentFactory() {
			
			@Override
			public JComponent make() {
				String str = "";
				try {
					str = host.getSender().getUser().getName() + ": ";
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JLabel label1 = new JLabel(str);
				return label1;
			}
		};
		IComponentFactory factory2 = new IComponentFactory() {
			
			@Override
			public JComponent make() {
				JLabel label2 = new JLabel(host.getData());
				return label2;
			}
		};
		cmd2ModelAdpt.buildComponentInScrollable(factory1);
		cmd2ModelAdpt.buildComponentInScrollable(factory2);
		return null;
	}

}