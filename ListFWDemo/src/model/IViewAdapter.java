package model;

import listFW.IList;

/**
 * Adapter to the view from the model
 * @author swong
 *
 */
public interface IViewAdapter {

	/**
	 * Give the view an array of strings to label the different list hosts.  The i'th label corresponds to the i'th list.
	 * @param lists
	 */
	void setLists(IList... lists);

}
