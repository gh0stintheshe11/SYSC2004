import java.util.*;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kolling.
 * @version 2006.03.30
 *
 * @author Lynn Marshall, SCE
 * @version Assignment #2 Sample Solution 
 * 
 */
public class Auction
{
    /** The list of Lots in this auction. */
    private ArrayList<Lot> lots;

    /** 
     * The number that will be given to the next lot entered
     * into this auction.  Every lot gets a new number, even if some lots have
     * been removed.  (For example, if lot number 3 has been deleted, we don't
     * reuse it.)
     */
    private int nextLotNumber;

    // You need to add something here.  (Hint: an auction may be open or closed.)

    /**
     * Indicates whether the auction is still open for bidding (true)
     * or if it has been closed (false).
     */
    private boolean isOpen;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
        // you need to add something here -- see hint above.
        isOpen = true;
    }
    
    /**
     * Create a new auction starting with the unsold items from an
     * existing closed auction.  If the auction parameter is not closed
     * or is null, then the default constructor values are used.
     * 
     * @param auction The closed auction to use as the starting point.
     */
    public Auction(Auction auction)
    {
        if (auction==null || auction.isOpen)
        {
            lots = new ArrayList<Lot>();
            nextLotNumber = 1;
        }
        else
        {
            lots = auction.getNoBids();
            nextLotNumber = auction.nextLotNumber;
        }
        isOpen = true;
    }
    
    /**
     * Enter a new lot into the auction.  Returns false if the
     * auction is not open or if the description is null.
     * (You need to add the return type, documentation, and change
     * the code.)
     *
     * @param description A description of the lot.
     * 
     * @return true if successful, false otherwise
     */
    public boolean enterLot(String description)
    {

        if (!isOpen || description==null) return false;

        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
        return true;
    }

    /**
     * Show the full list of lots in this auction.
     *
     * Print a blank line first, to make our output look nicer. 
     * If there are no lots, print a message indicating this 
     * (You need to add these to the code given below.)
     */
    public void showLots()
    {
        System.out.println();
        if (lots.size()==0) 
            System.out.println("No lots in this auction.");

        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }
    
    /**
     * Bid for a lot.
     * Do not assume that the lots are stored in numerical order.
     * Prints a message indicating whether the bid is successful or not.
     *   
     * First print a blank line.  
     * Then print whether or not the bid is successful.
     * If the bid is successful, also print the
     * lot number, high bidder's name, and the bid value.
     * If the bid is not successful, also print the lot number 
     * and high bid (but not the high bidder's name).
     * 
     * Returns false if the auction is closed, the lot doesn't
     * exist, the bidder is null, or the bid was not positive
     * and true otherwise.
     *
     * @param number The lot number being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     * 
     * @return false if the auction is closed, lot doesn't exist, or
     *               bidder is null; true otherwise (even though bid
     *               might not be the highest)
     */
    public boolean bidFor(int lotNumber, Person bidder, long value)
    {
        if (!isOpen || bidder==null || value<=0) return false;
        
        Lot lot = getLot(lotNumber); // get the lot
        
        if (lot==null) return false;
        
        System.out.println();
        boolean successful = lot.bidFor(new Bid(bidder, value));
        if(successful) {
           System.out.println("The bid for lot number " +
              lotNumber + " was successful.");
           System.out.println("The high bidder is now "
              + bidder.getName() + " with a bid of $" + value + ".");
        } else {
            // Report which bid is higher.
            Bid highestBid = lot.getHighestBid();
            System.out.println("Lot number: " + lotNumber +
                " already has a bid of $" + highestBid.getValue() + ".");
        }
        return true;  
    }


    /**
     * Return the lot with the given number. 
     * Do not assume that the lots are stored in numerical order.   
     *
     * @param lotNumber The number of the lot to return.
     *
     * @return the Lot with the given number (null if the Lot does not 
     * exist)
     */
    public Lot getLot(int lotNumber)
    {
        for (Lot lot : lots) {
            if (lot.getNumber()==lotNumber) // the one we want
                return lot; 
        }
        // if we get here, the lot does not exist
        return null;
    }
    
    /**
     * Closes the auction and prints information on the lots.
     * First print a blank line.  Then for each lot,
     * its number and description are printed.
     * If it did sell, the high bidder and bid value are also printed.  
     * If it didn't sell, that information is printed.
     * 
     * Returns true if successful, false otherwise (auction not open).
     * 
     * @return true if successful, false if auction is not open.
     */
    public boolean close()
    {
        if (!isOpen) return false;

        isOpen = false;
        System.out.println();
        for(Lot lot : lots) {
            System.out.print(lot.getNumber() + ": " + lot.getDescription() + " ");
            if (lot.getHighestBid()==null) { // not sold
                System.out.println("was not sold");  
            } else {
                Bid highBid = lot.getHighestBid();
                System.out.println("was sold to " + highBid.getBidder().getName()
                   + " for $" + highBid.getValue());
            }    
        }
        return true;
    }
    
    /**
     * Returns an ArrayList containing all the items that have no bids so far.
     * (or have not sold if the auction has ended).
     * 
     * @return an ArrayList of the Lots which currently have no bids
     */
    public ArrayList<Lot> getNoBids()
    {
        ArrayList<Lot> noBids = new ArrayList<Lot>();
        for (Lot lot : lots) {
            if (lot.getHighestBid()==null) // not sold
                noBids.add(lot); 
        }
        return noBids;
    }
    
    /**
     * Remove the lot with the given lot number, as long as the lot has
     * no bids, and the auction is open.  
     * You must use an Iterator object to search the list and,
     * if applicable, remove the item.
     * Do not assume that the lots are stored in numerical order.
     *
     * Returns true if successful, false otherwise (auction closed,
     * lot does not exist, or lot has a bid).
     *
     * @param number The number of the lot to be removed.
     * 
     * @return true if successful, false otherwise.
     */
    public boolean removeLot(int number)
    {
        if (!isOpen) return false; // auction isn't open

        Lot lot;
        Iterator<Lot> iter = lots.iterator();
        while (iter.hasNext()) {
            lot = iter.next();
            if (lot.getNumber()==number) {// the one to remove
                if (lot.getHighestBid()!=null) return false; // has bids
                iter.remove();
                return true;
            }
        }
        // lot does not exist
        return false;
    }
          
}
