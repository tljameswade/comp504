package model;

import gov.nasa.worldwind.geom.Position;

public class Place {
	private String   _name;
	private Position _pos;
	
	public Place(String name, Position pos) {
		_name = name;
		_pos = pos;
	}
	
	public Position getPosition() {
		return _pos;
	}
	
	public String toString() {
		return _name;
	}
}
