package provided.client.model.task;

import java.io.*;
import java.math.BigDecimal;

import provided.compute.ITask;
import provided.compute.ILocalTaskViewAdapter;

/**
 * Task to compute PI to a given number of digits
 * @author swong
 *
 */
public class Pi2 implements ITask<BigDecimal> {

	/**
	 * SerialversionUID for well-defined serialization.
	 */
    private static final long serialVersionUID = 227L;
    
    /**
     * Adapter to the local view.  Marked "transient" so that it is not serialized
     * and instead is reattached at the destination (the server).  
     */
    private transient ILocalTaskViewAdapter taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;

    /** 
     * constants used in pi computation 
     */
    private static final BigDecimal FOUR =
        BigDecimal.valueOf(4);

    /** 
     * rounding mode to use during pi computation 
     */
    private static final int roundingMode = 
        BigDecimal.ROUND_HALF_EVEN;

    /** 
     * digits of precision after the decimal point 
     */
    private final int digits;
    
    /**
     * Construct a task to calculate pi to the specified
     * precision.
     * @param digits the number of digits to calculate PI to
     */
    public Pi2(int digits) {
        this.digits = digits;
        taskView.append("Pi constructing...");
    }

    /**
     * Calculate pi.
     */
    @Override
    public BigDecimal execute() {
      System.out.println("Executing client's Pi2 task.");
      taskView.append("Client's Pi2 executing...");
        return computePi(digits);
    }

    /**
     * Compute the value of pi to the specified number of 
     * digits after the decimal point.  The value is 
     * computed using Machin's formula:
     *
     *          pi/4 = 4*arctan(1/5) - arctan(1/239)
     *
     * and a power series expansion of arctan(x) to 
     * sufficient precision.
     */
    private BigDecimal computePi(int digits) {
        int scale = digits + 5;
        BigDecimal arctan1_5 = arctan(5, scale);
        BigDecimal arctan1_239 = arctan(239, scale);
        BigDecimal pi = arctan1_5.multiply(FOUR).subtract(
                                  arctan1_239).multiply(FOUR);
        return pi.setScale(digits, 
                           BigDecimal.ROUND_HALF_UP);
    }
    /**
     * Compute the value, in radians, of the arctangent of 
     * the inverse of the supplied integer to the specified
     * number of digits after the decimal point.  The value
     * is computed using the power series expansion for the
     * arc tangent:
     *
     * arctan(x) = x - (x^3)/3 + (x^5)/5 - (x^7)/7 + 
     *     (x^9)/9 ...
     */   
    private BigDecimal arctan(int inverseX, 
                                    int scale) 
    {
        BigDecimal result, numer, term;
        BigDecimal invX = BigDecimal.valueOf(inverseX);
        BigDecimal invX2 = 
            BigDecimal.valueOf(inverseX * inverseX);

        numer = BigDecimal.ONE.divide(invX,
                                      scale, roundingMode);

        result = numer;
        int i = 1;
        do {
            numer = 
                numer.divide(invX2, scale, roundingMode);
            int denom = 2 * i + 1;
            term = 
                numer.divide(BigDecimal.valueOf(denom),
                             scale, roundingMode);
            if ((i % 2) != 0) {
                result = result.subtract(term);
            } else {
                result = result.add(term);
            }
            i++;
        } while (term.compareTo(BigDecimal.ZERO) != 0);
        return result;
    }
    

    /**
     * Reinitializes transient fields upon deserialization.  See the 
     * <a href="http://download.oracle.com/javase/6/docs/api/java/io/Serializable.html">
     * Serializable</a> marker interface docs.
     * taskView is set to a default value for now (ILocalTaskViewAdapter.DEFAULT_ADAPTER).
     * @param stream The object stream with the serialized data
     */
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject(); // Deserialize the non-transient data
      taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;  // re-initialize the transient field
    }

    /**
     * Sets the task view adapter to a new value.  Used by the server to attach
     * the task to its view.
     */
    @Override
    public void setTaskViewAdapter(ILocalTaskViewAdapter viewAdapter) {
      taskView = viewAdapter;
    }

}
