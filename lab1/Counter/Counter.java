import javax.management.relation.Role;

/**
 * The counter class implements a counter that will roll over to the initial
 * value when it hits the maximum value.
 * 
 * @author Charles Hoot 
 * @version 4.0
 */
public class Counter
{
    // PUT PRIVATE DATA FIELDS HERE
    private int minimum;
    private int maximum;
    private int counter;
    private boolean rolledOver;

    /**
     * The default constructor for objects of class Counter.  Minimum is 0 and the maximum
     * is the largest possible integer.
     */
    public Counter()
    {
        minimum = 0;
        maximum = Integer.MAX_VALUE;
        counter = 0;
        rolledOver = false;
    }
    
    
    /**
     * The alternate constructor for objects of class Counter.  The minimum and maximum values are given as parameters.
     * The counter starts at the minimum value.
     * @param min The minimum value that the counter can have
     * @param max The maximum value that the counter can have
     * */
    public Counter(int min, int max)
    {
        if (min >= max) {
            throw new CounterInitializationException("Min is greater than max");
        }
        minimum = min;
        maximum = max;
        counter = min;
        rolledOver = false;
    }
    
    /**
     * Determine if two counters are in the same state
     *
     * @param  otherObject   the object to test against for equality
     * @return     true if the objects are in the same state
     */
    public boolean equals(Object otherObject)
    {
        boolean result = true;
        if (otherObject instanceof Counter)
        {
            Counter other = (Counter) otherObject;
            if (other.rolledOver == rolledOver && other.counter == counter && other.minimum == minimum
            && other.maximum == maximum) {
                result = true;
            }
            else {
                result = false;
            }
        }
        return result;
    }
    
    

    /**
     * Increases the counter by one
     */
    public void increase()
    {
        if (counter + 1 > maximum) {
            counter = minimum;
            rolledOver = true;
        }
        else {
            counter++;
            rolledOver = false;
        }
    }
 
 
     /**
     * Decreases the counter by one
     */
    public void decrease()
    {
        if (counter - 1 < minimum) {
            counter = maximum;
            rolledOver = true;
        }
        else {
            counter--;
            rolledOver = false;
        }
    }
    
    /**
     * Get the value of the counter
     *
     * @return     the current value of the counter
     */
    public int value()
    {
        // CHANGE THE RETURN TO GIVE THE CURRENT VALUE OF THE COUNTER
        return counter;
		
    }
    
    
    /**
     * Accessor that allows the client to determine if the counter
     *             rolled over on the last count
     *
     * @return     true if the counter rolled over
     */
    public boolean rolledOver()
    {
        // CHANGE THE RETURN TO THE ROLLOVER STATUS OF THE COUNTER
        return rolledOver;
    }
    
    /**
     * Override the toString method to provide a more informative
     * description of the counter
     *
     * @return     a descriptive string about the object
     */
    public String toString()
    {
        // CHANGE THE RETURN TO A DESCRIPTION OF THE COUNTER
        return "Counter Value: " + counter + "\n" +
        "Min Value: " + minimum + "\n" + "Max Value: "
        + maximum + "\n" + "Rolled Over: " + rolledOver;		
    }
 
}
