package model;

import listFW.*;
import listFW.visitor.*;

/** ListFW demo app model
 * 
 * @author swong
 *
 */
public class ListDemoModel {
	
	private IViewAdapter view;

	private IList list0 = MTList.Singleton;
	
	private IList list1 = new NEList(42, list0);
	
	private IList list2 = new NEList(-99, list1);
	
	
	private DynamicLoader<IAccumulator> accLoader = new DynamicLoader<IAccumulator>("listFW.visitor.", "");
	private DynamicLoader<IListAlgo> visLoader = new DynamicLoader<IListAlgo>("listFW.visitor.", "");
	
	private IListAlgo foldr = new FoldRAlgo();
	private IListAlgo foldl = new FoldLAlgo();	
	
	/** 
	 * Constructor for the class
	 * 
	 * @param view  The view adapter
	 */
	public ListDemoModel(IViewAdapter view) {
		this.view = view;
	}

	/** 
	 * Start the model
	 */
	public void start() {
		view.setLists(list0, list1, list2, new NEList(-123, new NEList(2012, list2)));
		
	}


	/**
	 * Returns the result of a host list executing the given visitor with the given parameter
	 * @param list The host list object
	 * @param classname  The class name of the visitor to use, without the leading "listFW.visitor."
	 * @param param  An input parameter for the visitor execution
	 * @return The result of the visitor execution by the list.
	 */
	public String runListAlgo(IList list, String classname, String param) {
		IListAlgo algo = visLoader.load(classname); //loadVisitor(classname);
		return  list+".execute("+classname+", "+param+") = " + list.execute(algo, param);	
	}
	
	
	/**
	 * Run the FoldRAlgo visitor on the given list using the given IAccumulator
	 * @param list The host list object
	 * @param classname The class name of the accumulator to use, without the leading "listFW.visitor."
	 * @return The result of the foldr visitor execution by the list.
	 */
	public String runFoldR(IList list, String classname) {
		IAccumulator acc = accLoader.load(classname);
		return list+".execute(FoldRAlgo,"+classname+"("+acc+")) = " + list.execute(foldr, acc);	
	}

	
	/**
	 * Run the FoldLAlgo visitor on the given list using the given IAccumulator
	 * @param list The host list object
	 * @param classname The class name of the accumulator to use, without the leading "listFW.visitor."
	 * @return The result of the foldl visitor execution by the list.
	 */
	public String runFoldL(IList list, String classname) {
		IAccumulator acc = accLoader.load(classname);
		return list+".execute(FoldLAlgo,"+classname+"("+acc+")) = " + list.execute(foldl, acc);	
	}
	
		

}
