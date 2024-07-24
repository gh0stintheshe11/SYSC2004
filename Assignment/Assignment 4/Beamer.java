
/**
 * Beamer is a subclass of Item.  When a player is carrying
 * a beamer, it can be charged and fired.  If a charged beamer
 * is fired, it returns the player to the room in which it 
 * was charged.
 * 
 * @author Lynn Marshall
 * @version A4 Solution
 */
public class Beamer extends Item
{
    // The room in which the beamer was charged
    private Room charged;

    /**
     * Constructor for objects of class Beamer.
     * (User doesn't need to enter the information as
     * we can assume all beamers are the same.)
     */
    public Beamer()
    {
        super("A wonderful beamer", 1, "beamer");
    }

    /**
     * Charges the beamer and sets the room in which
     * it was charged
     */
    public void charge(Room room)
    {
        charged=room;
    }
    
    /**
     * Returns true if beamer is charged, false otherwise
     * 
     * @return true if beamer charged, false otherwise
     */
    public boolean isCharged()
    {
        return (charged!=null);
    }
    
    /**
     * Fires the beamer, returning the room in which
     * it was charged.  If the beamer is not charged, it
     * returns null.
     */
    public Room fire()
    {
        Room goTo = charged;
        charged=null; // need to charge before firing again
        return goTo;
    }
}
