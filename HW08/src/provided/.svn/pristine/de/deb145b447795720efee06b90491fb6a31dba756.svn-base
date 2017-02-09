package provided.mixedData;

/**
 * Abstract mixed data dictionary interface
 * A mixed data dictionary can hold multiple value types and retrieve those
 * values in a type-safe manner.   The use of a MixedDataKey is required 
 * as this special key carries typing information.   This guarantees that 
 * the type of the values associated with the key match the type specified
 * by the key.  Thus, no casting of the value returned by a "get" operation 
 * is necessary.
 * @author swong
 *
 */
public interface IMixedDataDictionary {

	/**
	 * Check if the dictionary contains a value for the given key
	 * @param key The key to check
	 * @return true if a value exists in the dictionary for the given key, false otherwise.
	 */
	public abstract boolean containsKey(MixedDataKey<?> key);


	/**
	 * Put a value into the dictionary, associated with the given key,
	 * replacing any value already in the dictionary that is already associated
	 * with the key.   The type of the value must be a that or a subclass of that 
	 * specified by the key.
	 * @param key   the key to use to find the value
	 * @param value  The value to associate with the key
	 * @return The previous value associated with the key or null if there was none. 
	 * @param <T>  The type of data being put in
	 */
	public abstract <T> T put(MixedDataKey<T> key, T value);


	/**
	 * Get the value associated with the given key
	 * @param key  the key to use
	 * @return The value associated with the key or null if there is no entry for the key.  The returned value is properly typed to the expected type so no casting is required.
	 * @param <T> The type of data being retrieved
	 */
	public abstract <T> T get(MixedDataKey<T> key);

}