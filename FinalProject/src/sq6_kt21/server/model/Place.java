package sq6_kt21.server.model;

import gov.nasa.worldwind.geom.Position;

public class Place {
	private String   name;
	private Position pos;
	private int value;
	
	public Place(String _name, Position _pos, int _val) {
		name = _name;
		pos = _pos;
		value = _val;
	}
	
	public Position getPosition() {
		return pos;
	}
	
	public String toString() {
		return name;
	}
	
	public int getValue(){
		return value;
	}
	
	public void clearValue(){
		value = 0;
	}
}
