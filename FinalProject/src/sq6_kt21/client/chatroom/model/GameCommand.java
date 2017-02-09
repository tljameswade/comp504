package sq6_kt21.client.chatroom.model;

import javax.swing.JComponent;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.IComponentFactory;
import common.OurDataPacket;
import gov.nasa.worldwind.globes.Earth;
import map.MapPanel;


/**
 * The game command class
 * @author Kun Tian
 *
 */
public class GameCommand extends AOurDataPacketAlgoCmd<GameInfo, IChatServer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1747369738712570262L;
	transient private ICmd2ModelAdapter cmd2ModelAdpt = null;

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

	@Override
	public Void apply(Class<?> index, OurDataPacket<GameInfo, IChatServer> host, Object... params) {
		@SuppressWarnings("serial")
		IComponentFactory factory = new IComponentFactory() {
			
			@Override
			public JComponent make() {
				MapPanel mapPanel= new MapPanel(Earth.class);
				mapPanel.setPreferredSize(new java.awt.Dimension(600, 400));
				mapPanel.start();
				return mapPanel;
			}
		};
		
		cmd2ModelAdpt.buildComponentInNonScrollable(factory);
		
		return null;
	}

}
