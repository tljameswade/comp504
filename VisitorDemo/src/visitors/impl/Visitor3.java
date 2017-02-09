package visitors.impl;

import visitors.HostA;
import visitors.HostB;
import visitors.HostC;
import visitors.IVisitor;

public class Visitor3 implements IVisitor {

	@Override
	public Object caseHostA(HostA host, Object... params) {
		return "Hey!";
	}

	@Override
	public Object caseHostB(HostB host, Object... params) {
		return "Nice!";
	}

	@Override
	public Object caseHostC(HostC host, Object... params) {
		return "Drink";
	}

}
