import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test; 
 
/**
 * The test class AuctionTest.
 *
 * @author  Lynn Marshall
 * @version Assignment #2 Sample Solution Unit Tests
 */
public class AuctionTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class AuctionTest
     */
    public AuctionTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    public void tearDown()
    {
    }
    
    /**
     * Test add item
     */
    public void testAddLot()
    {
        Auction a1 = new Auction();
        assertFalse(a1.enterLot(null)); // null
        a1.close();
        assertFalse(a1.enterLot("item")); // closed
        
        a1 = new Auction();
        assertTrue(a1.enterLot("item1")); // usual item
        Lot l1 = a1.getLot(1);
        assertEquals(1,l1.getNumber()); // #1
        assertEquals("item1",l1.getDescription());
        assertNull(l1.getHighestBid());
        
        assertTrue(a1.enterLot("item2"));
        Lot l2 = a1.getLot(2);
        assertEquals(2,l2.getNumber()); // #2
        assertEquals("item2",l2.getDescription());
        assertNull(l2.getHighestBid());
        
        a1.close();
        assertFalse(a1.enterLot("item3"));
        
        Auction a2 = new Auction(a1); // 2nd constructor
        assertTrue(a2.enterLot("item3"));
        Lot l3 = a2.getLot(3);
        assertEquals(3,l3.getNumber()); // #3
        assertEquals("item3",l3.getDescription());
        assertNull(l3.getHighestBid());
    }
    
    /**
     * Test bid
     */
    public void testBid()
    {
        Auction a1 = new Auction();
        Person p1 = new Person("A");
        Person p2 = new Person("B");
        a1.enterLot("item1"); // 1
        a1.enterLot("item2"); // 2
        assertFalse(a1.bidFor(1,p1,-10)); // -ve amount
        assertFalse(a1.bidFor(1,p1,0)); // 0 amount
        assertFalse(a1.bidFor(1,null,10)); // null person
        assertFalse(a1.bidFor(3,p1,10)); // no such item
        assertFalse(a1.bidFor(-5,p1,10)); // no such item
        assertFalse(a1.bidFor(3,p1,10)); // no such item
        a1.close();
        assertFalse(a1.bidFor(1,p1,10)); // closed
        
        a1 = new Auction();
        a1.enterLot("item1"); // 1
        a1.enterLot("item2"); // 2
        a1.enterLot("item3"); // 3
        
        assertTrue(a1.bidFor(1,p1,10));
        Lot l = a1.getLot(1); 
        Bid b = l.getHighestBid();
        assertEquals(10,b.getValue()); // bid value
        assertEquals("A",b.getBidder().getName()); // bidder
        
        assertTrue(a1.bidFor(2,p2,100));
        l = a1.getLot(2); 
        b = l.getHighestBid();
        assertEquals(100,b.getValue()); // bid value
        assertEquals("B",b.getBidder().getName()); // bidder
        
        assertTrue(a1.bidFor(2,p1,50)); // too low
        l = a1.getLot(2); 
        b = l.getHighestBid();
        assertEquals(100,b.getValue()); // bid value
        assertEquals("B",b.getBidder().getName()); // bidder
        
        a1.close();
        Auction a2 = new Auction(a1); // 2nd constructor
        a2.enterLot("item4");
        
        assertTrue(a2.bidFor(4,p2,1));
        l = a2.getLot(4); 
        b = l.getHighestBid();
        assertEquals(1,b.getValue()); // bid value
        assertEquals("B",b.getBidder().getName()); // bidder
        
        assertTrue(a2.bidFor(3,p1,5));
        l = a2.getLot(3); 
        b = l.getHighestBid();
        assertEquals(5,b.getValue()); // bid value
        assertEquals("A",b.getBidder().getName()); // bidder
        
        assertTrue(a2.bidFor(4,p1,2)); // higher bid
        l = a2.getLot(4); 
        b = l.getHighestBid();
        assertEquals(2,b.getValue()); // bid value
        assertEquals("A",b.getBidder().getName()); // bidder
    }
    
    /**
     * Test getLot
     */
    public void testGetLot()
    {
        Auction a1 = new Auction();
        Person p = new Person("A");
        a1.enterLot("item1"); // 1
        a1.enterLot("item2"); // 2
        a1.enterLot("item3"); // 3
        a1.bidFor(2,p,10);
        
        assertNull(a1.getLot(0)); // too low
        assertNull(a1.getLot(-3)); // much too low
        assertNull(a1.getLot(4)); // too high
        assertNull(a1.getLot(10)); // much too high
        
        Lot l = a1.getLot(1); // 1
        assertEquals(1,l.getNumber());
        assertEquals("item1",l.getDescription());
        assertNull(l.getHighestBid());
        
        l = a1.getLot(2); // 1
        assertEquals(2,l.getNumber());
        assertEquals("item2",l.getDescription());
        Bid b = l.getHighestBid();
        assertEquals(10,b.getValue());
        assertEquals("A",b.getBidder().getName());
        
        l = a1.getLot(3); // 1
        assertEquals(3,l.getNumber());
        assertEquals("item3",l.getDescription());
        assertNull(l.getHighestBid());
    }
    
    /**
     * Test close
     */
    public void testClose()
    {
        Auction a1 = new Auction();
        assertTrue(a1.close());
        assertFalse(a1.close());
        
        a1 = new Auction();
        Person p = new Person("A");
        a1.enterLot("item1"); // 1
        a1.enterLot("item2"); // 2
        a1.enterLot("item3"); // 3
        a1.bidFor(2,p,10);
        
        assertTrue(a1.close());
        assertFalse(a1.close());
        
        Auction a2 = new Auction(a1);
        assertTrue(a2.close());
        assertFalse(a2.close());
        
        Auction a3 = new Auction(a2);
        assertTrue(a3.close());
        assertFalse(a3.close());
    }
    
    /**
     * test getNoBids
     */
    public void testNoBids()
    {
        Auction a1 = new Auction();
        Person p = new Person("A");
        ArrayList<Lot> nb = a1.getNoBids();
        assertTrue(nb.isEmpty()); // empty
        
        a1.enterLot("item1"); // 1
        a1.enterLot("item2"); // 2
        a1.enterLot("item3"); // 3
        nb = a1.getNoBids();
        assertEquals(3,nb.size()); // 3 items
        int i=1;
        for (Lot l : nb) {
            assertEquals(i++,l.getNumber());
        }
        
        a1.bidFor(2,p,10);
        nb = a1.getNoBids();
        assertEquals(2,nb.size()); // 2 items
        assertEquals(1,nb.get(0).getNumber()); // 1
        assertEquals(3,nb.get(1).getNumber()); // 3
        
        a1.bidFor(1,p,10);
        nb = a1.getNoBids();
        assertEquals(1,nb.size()); // 1 items
        assertEquals(3,nb.get(0).getNumber()); // 3
        
        a1.bidFor(3,p,10);
        nb = a1.getNoBids();
        assertTrue(nb.isEmpty()); // 0 items
        
        a1.enterLot("item1"); // 1
        a1.enterLot("item2"); // 2
        a1.enterLot("item3"); // 3
        nb = a1.getNoBids();
        assertEquals(3,nb.size()); // 3 items
        i=4; // items 4, 5, and 6
        for (Lot l : nb) {
            assertEquals(i++,l.getNumber());
        }
        
        a1.close(); // closing the auction doesn't change the no bid list
        nb = a1.getNoBids();
        assertEquals(3,nb.size()); // 3 items
        i=4; // items 4, 5, and 6
        for (Lot l : nb) {
            assertEquals(i++,l.getNumber());
        }
    }
    
    /**
     * test removeLot()
     */
    public void testRemoveLot()
    {
        Auction a1 = new Auction();
        Person p = new Person("A");
        a1.enterLot("item1"); // 1
        a1.enterLot("item2"); // 2
        a1.enterLot("item3"); // 3
        a1.bidFor(2,p,10);
        
        assertFalse(a1.removeLot(0)); // too small
        assertFalse(a1.removeLot(-5)); // much too small
        assertFalse(a1.removeLot(4)); // too big
        assertFalse(a1.removeLot(10)); // much too big
        assertFalse(a1.removeLot(2)); // has a bid
        
        a1.close();
        assertFalse(a1.removeLot(1)); // closed
        
        a1 = new Auction();
        a1.enterLot("item1"); // 1
        a1.enterLot("item2"); // 2
        a1.enterLot("item3"); // 3
        
        assertTrue(a1.removeLot(2));
        ArrayList<Lot> nb = a1.getNoBids();
        assertEquals(2,nb.size()); // 2 items left 
        assertEquals(1,nb.get(0).getNumber());
        assertEquals(3,nb.get(1).getNumber());

        assertTrue(a1.removeLot(1));
        nb = a1.getNoBids();
        assertEquals(1,nb.size()); // 1 item left 
        assertEquals(3,nb.get(0).getNumber());
        
        assertTrue(a1.removeLot(3));
        nb = a1.getNoBids();
        assertTrue(nb.isEmpty()); // 0 items left 
        
        a1.enterLot("item1"); // 4
        a1.enterLot("item2"); // 5
        a1.enterLot("item3"); // 6
        
        assertTrue(a1.removeLot(6));
        assertTrue(a1.removeLot(4));
        assertTrue(a1.removeLot(5));
        
        a1.enterLot("item1"); // 7
        a1.enterLot("item2"); // 8
        a1.enterLot("item3"); // 9
        a1.bidFor(7,p,10);
        a1.bidFor(8,p,10);
        a1.bidFor(9,p,10);
        
        assertFalse(a1.removeLot(7));
        assertFalse(a1.removeLot(8));
        assertFalse(a1.removeLot(9));
        
        nb = a1.getNoBids();
        assertTrue(nb.isEmpty());
    }
}
