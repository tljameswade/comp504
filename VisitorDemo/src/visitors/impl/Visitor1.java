package visitors.impl;

import visitors.HostA;
import visitors.HostB;
import visitors.HostC;
import visitors.IVisitor;

/**
 * A visitor that returns a String that says "This is the result from HostX,
 * where HostX is the type of the IHost object executing this visitor. 
 * @author swong
 *
 */
public class Visitor1 implements IVisitor {

	@Override
	public Object caseHostA(HostA host, Object... params) {
		return "This is the result from HostA!";
	}

	@Override
	public Object caseHostB(HostB host, Object... params) {
		return "This is the result from HostB!";
	}

	@Override
	public Object caseHostC(HostC host, Object... params) {
		return "This is the result from HostC!";
	}

}
