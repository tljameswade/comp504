package listFW.visitor;

import listFW.IListAlgo;
import listFW.MTList;
import listFW.NEList;

public class ProdAlgo implements IListAlgo {

	@Override
	public Object emptyCase(MTList host, Object... inp) {
		return 1;
	}

	@Override
	public Object nonEmptyCase(NEList host, Object... inp) {

		return (int) host.getFirst() * (int) host.getRest().execute(this);
	}

}
