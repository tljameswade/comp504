package model;

public class DynamicLoader<T> {

	private String prefix;
	private String postfix;	
	
	public DynamicLoader(String prefix, String postfix) {
		this.prefix = prefix;
		this.postfix = postfix;
	}
	
	
	@SuppressWarnings("unchecked")
	public T load(String classname) {
		try {
			Object[] args = new Object[]{}; 
			java.lang.reflect.Constructor<?> cs[] = Class.forName(prefix+classname+postfix).getConstructors();  // get all the constructors
			java.lang.reflect.Constructor<?> c = null; 
			for(int i=0;i < cs.length; i++) {  // find the first constructor with the right number of input parameters
				if(args.length == (cs[i]).getParameterTypes().length) {
					c = cs[i];
					break;
				}
			}
			return (T) c.newInstance(args);
		}
		catch(Exception ex) {
			System.err.println("Class "+classname+" failed to load. \nException = \n"+ ex);
			ex.printStackTrace();  // print the stack trace to help in debugging.
			return null;
		}
	}

}
