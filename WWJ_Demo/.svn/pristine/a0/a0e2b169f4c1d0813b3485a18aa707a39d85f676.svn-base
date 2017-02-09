package model;

import map.MapLayer;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.GlobeAnnotation;

public class MapModel {
	IModel2ViewAdapter _adpt;
	MapLayer           _layer = new MapLayer();
		
	public MapModel(IModel2ViewAdapter adpt) {
		_adpt = adpt;
	}
	
	public void click(Position p) {
		System.out.println("Mouse clicked at: " + p+" altitude = "+p.getAltitude());
	}
	
	public void start() {
		Position willy = Position.fromDegrees(29.718550, -95.399068, 150);
		Position epcot = Position.fromDegrees(28.374454, -81.549363, 1000);
		Position nyc   = Position.fromDegrees(40.748974, -73.990288, 10000);
		
		_adpt.addPlace(new Place("Greenwich", Position.fromDegrees(51.477222, 0.0, 1000)));
		_adpt.addPlace(new Place("Louvre", Position.fromDegrees(48.860930, 2.336461, 1000)));
		_adpt.addPlace(new Place("London Eye", Position.fromDegrees(51.503367, -0.119968, 1000)));
		_adpt.addPlace(new Place("Acropolis", Position.fromDegrees(37.971458, 23.726647, 800)));
		_adpt.addPlace(new Place("Colosseum", Position.fromDegrees(41.890306, 12.492354, 500)));
		_adpt.addPlace(new Place("Taj Mahal", Position.fromDegrees(27.174932, 78.042144, 1000)));
		_adpt.addPlace(new Place("Pyramids", Position.fromDegrees(29.976788, 31.134001, 1500)));
		_adpt.addPlace(new Place("Statue of Liberty", Position.fromDegrees(40.68925, -74.044493, 500)));
		_adpt.addPlace(new Place("NYC", nyc));
		_adpt.addPlace(new Place("Luxor", Position.fromDegrees(36.095568, -115.176033, 1500)));
		_adpt.addPlace(new Place("Grand Canyon", Position.fromDegrees(36.108091, -113.214912, 90000)));
		_adpt.addPlace(new Place("Golden Gate", Position.fromDegrees(37.82035, -122.4778804, 5000)));
		_adpt.addPlace(new Place("Epcot Center", epcot));
		_adpt.addPlace(new Place("Willy", willy));
		_adpt.addPlace(new Place("Rice", Position.fromDegrees(29.71724, -95.40150, 1000)));

		_layer.addToggleAnnotation("Willy", "Willy Selected!", willy);
		_layer.addToggleAnnotation("Epcot Center", "Epcot Selected!", epcot, 5000, 1000000);
		_layer.addAnnotation(new GlobeAnnotation("NYC", nyc));
		_adpt.show(_layer);
	}
}
