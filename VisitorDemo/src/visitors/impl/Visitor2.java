package visitors.impl;

import java.awt.Color;
import java.awt.Graphics;

import model.*;
import visitors.*;

/**
 * A visitor that sets the paint cmd in the VisitorDemoModel to a cmd that draws various shapes.
 * The params is assumed to be a single value, a reference to the VisitorDemoModel.
 * When the host is a HostA, the installed IPaintCmd draws a blue oval on the screen.
 * When the host is a HostB, the installed IPaintCmd draws a red rectangle on the screen.    
 * When the host is a HostC, the installed IPaintCmd draws a green rounded rectangle on the screen.  
 * Returns a string saying what it did.
 * @author swong
 *
 */
public class Visitor2 implements IVisitor {

	@Override
	public Object caseHostA(HostA host, Object... params) {
		((VisitorDemoModel) params[0]).setPaintCmd(new IPaintCmd() {
			@Override
			public void apply(Graphics g) {
				g.setColor(Color.BLUE);
				g.fillOval(50, 50, 30, 15);
			}
		});
		return "Blue oval made.";
	}

	@Override
	public Object caseHostB(HostB host, Object... params) {
		((VisitorDemoModel) params[0]).setPaintCmd(new IPaintCmd() {
			@Override
			public void apply(Graphics g) {
				g.setColor(Color.RED);
				g.fillRect(50, 50, 30, 15);
			}
		});
		return "Red rectangle made";
	}

	@Override
	public Object caseHostC(HostC host, Object... params) {
		((VisitorDemoModel) params[0]).setPaintCmd(new IPaintCmd() {
			@Override
			public void apply(Graphics g) {
				g.setColor(Color.GREEN);
				g.fillRoundRect(50, 50, 30, 15, 5, 5);
			}
		});
		return "Green rounded rectangle made";
	}

}
