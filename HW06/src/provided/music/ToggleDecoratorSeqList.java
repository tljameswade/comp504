package provided.music;

/**
 * Multiple Decorator ISeqList where the active decoree (the "target") is
 * toggled (cycled, actually) through all the available decorees.   
 * This is arguably a State Design Pattern where each decoree is 
 * considered to be another state of the system.
 * This is used to implement repeats where the first time through, the notes are linked
 * back to a repeat start (decoree #0) but the next time through, the next note is the one
 * after the repeat (decoree #1).
 * 
 * @author swong
 * ----------------------------------------------
 * Decorated ISeqList that transparently cycles through multiple targets (decorees)
 */
public class ToggleDecoratorSeqList implements ISeqList {

	/**
	 * The decoree/target ISeqList list.  An MTSeqList by default.
	 */
	private ISeqList[] decorees = { MTSeqList.Singleton, MTSeqList.Singleton };

	/**
	 * The index of the current target decoree
	 */
	private int targetIdx = 0;

	/**
	 * Constructor used to set the decorees to a known values at construction time.
	 * @param decorees vararg list of ISeqList decorees
	 */
	public ToggleDecoratorSeqList(ISeqList... decorees) {
		setDecorees(decorees);
		setTargetToDecoree(0);
	}

	/**
	 * Constructor that is used when the decoree is not known at construction time.
	 * Creates a 2-decoree object only.   For more, use the other constructor 
	 * with default values for decorees.
	 */
	public ToggleDecoratorSeqList() {
	}

	/**
	 * Settor for the decorees
	 * @param decorees vararg list of ISeqLists to decorate
	 */
	public void setDecorees(ISeqList... decorees) {
		this.decorees = decorees;
	}

	/**
	 * Setter methods used to set a particular decoree.   
	 * The specified index must already exist.
	 * @param idx The index of the decoree to replace
	 * @param decoree  The new decoree ISeqList.
	 */
	public void setDecoree(int idx, ISeqList decoree) {
		this.decorees[idx] = decoree;
	}

	/**
	 * Used to explicitly set the target (active) decoree if needed.
	 * @param targetIdx  the index of the target decoree 
	 */
	public void setTargetToDecoree(int targetIdx) {
		this.targetIdx = targetIdx;
	}

	/**
	 * Accessor for the current target (active) decoree
	 * @return  The current target (active) decoree
	 */
	public ISeqList getTargetDecoree() {
		return decorees[targetIdx];
	}

	/* (non-Javadoc)
	 * @see music.IPhrase#execute(music.IPhraseVisitor, java.lang.Object[])
	 */
	/**
	 * Simply delegates the call to the decoree.   The target decoree is advanced to the next decoree, or
	 * cycled back to the #0 decoree if the current target is the last one.
	 */
	@Override
	public Object execute(IPhraseVisitor algo, Object... params) {
		ISeqList target = decorees[targetIdx++];
		if (targetIdx >= decorees.length)
			targetIdx = 0; // This must be set before the delegate because it may be needed before the recursion returns.
		return target.execute(algo, params);
	}

	/**
	 * Borrows the _toStringAlgo from NESeqList to perform a toString conversion.
	 */
	@Override
	public String toString() {
		return (String) this.execute(NESeqList.getToStringAlgo(), "{");
	}
}
