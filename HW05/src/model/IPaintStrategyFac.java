package model;

/**
 * An interface that defines a factory that instantiates a specific IPaintStrategy
 * @author Suozhi Qi, Zhaohan Jia
 * @version 1.0
 */
public interface IPaintStrategyFac {

	/**
	 * Instantiate the specific IPaintStrategy for which this factory is defined.
	 * @return paint strategy that the factory makes
	 */
	public IPaintStrategy make();
}
