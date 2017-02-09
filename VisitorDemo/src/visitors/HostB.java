package visitors;

/**
 * A concrete host type
 * @author swong
 *
 */
public class HostB implements IHost {

	@Override
	/** 
	 * Always calls algo.caseHostB(this, params)
	 */
	public Object execute(IVisitor algo, Object... params) {
		return algo.caseHostB(this, params);
	}

	public String toString() {
		return "HostB";
	}
}
