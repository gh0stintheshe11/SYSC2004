import java.util.*;
/**
 * TransporterRoom is a subclass of Room.  When we leave a
 * transporter room, we move randomly to another room. 
 * 
 * @author Lynn Marshall 
 * @version A4 Solution 
 */
public class TransporterRoom extends Room
{
    private Random r;
    
    /** 
     * Creates a transporter room.
     */
    public TransporterRoom () 
    {
        super("in a transporter room.");
        r = new Random(); // the random number generator
        setExit("anywhere", null);
    }
    
    /**
     * Returns a random room, independent of the direction parameter.
     * 
     * @param direction Ignored.
     * @return A randomly selected room.
     */
    public Room getExit(String direction)
    {
        return findRandomRoom();
    }

    /**
     * Choose a random room.
     */
    private Room findRandomRoom()
    {
        /*
	     * //alternative showing the steps:
		 * int numRooms = Room.getRooms().size();
		 * // generate a random number between 0 and numRooms-1
		 * int randomNumber = r.nextInt(numRooms); 
		 * // get the room corresponding to that index
		 * Room myRoom = Room.getRooms().get(randomNumber);
		 * // and return it
 		 * return myRoom;
		 */
        return Room.getRooms().get(r.nextInt(Room.getRooms().size()));
    }
}

