package sq6_kt21.server.game.view;

import java.io.Serializable;
import java.util.List;

import gov.nasa.worldwind.geom.Position;
import map.MapLayer;
import map.ToggleAnnotation;

/**
 * The class to further define the map layer
 * @author suozhiqi
 *
 */
public class MapLayerHelper extends MapLayer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5306728921302275124L;
	
	private int count;
	
	private List<ToggleAnnotation> list;

	private ToggleAnnotation makeAnnotation(String unselectedText, String selectedText, Position pos)
	{
		ToggleAnnotation ta =  new ToggleAnnotation(unselectedText, selectedText, Position.fromDegrees(pos.getLatitude().degrees, pos.getLongitude().degrees, 0));
		list.add(ta);
		return ta;
	}
	
	@Override
	public void addToggleAnnotation(String unselectedText, String selectedText, Position pos)
	{
		addAnnotation(makeAnnotation(unselectedText, selectedText, pos));
	}

	public int getCount() {
		
		for (ToggleAnnotation toggle : list) {
			if (toggle.isSelected()) count++;
		}
		return count;
	}
}
