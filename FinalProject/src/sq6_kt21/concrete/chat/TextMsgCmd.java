package sq6_kt21.concrete.chat;

import java.rmi.RemoteException;

import javax.swing.JComponent;
import javax.swing.JLabel;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.IComponentFactory;
import common.OurDataPacket;
import common.msg.chat.ITextMsg;


/**
 * The text msg command class to process a text msg
 * @author Kun Tian
 *
 */
public class TextMsgCmd extends AOurDataPacketAlgoCmd<ITextMsg, IChatServer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -553519334341642002L;
	
	transient private ICmd2ModelAdapter cmd2ModelAdpt = null;

	@Override
	public Void apply(Class<?> index, OurDataPacket<ITextMsg, IChatServer> host, Object... params) {
		@SuppressWarnings("serial")
		IComponentFactory factory = new IComponentFactory() {
			
			@Override
			public JComponent make() {
				String str = "";
				try {
					str = host.getSender().getUser().getName() + ": " + host.getData().getContent();
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

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		// TODO Auto-generated method stub
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

}
