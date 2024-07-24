

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ClockDisplay12Test.
 *
 * @author  Lynn Marshall
 * @version A1 solution
 */
public class ClockDisplay12Test
{
    /**
     * Default constructor for test class ClockDisplay12Test
     */
    public ClockDisplay12Test()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * Test default constructor
     * 
     */
    @Test
    public void testDefConst()
    {
        ClockDisplay12 cd12 = new ClockDisplay12();
        assertEquals(cd12.getTime(),"12:00a.m.");
    }
    
    /**
     * Test other constructor
     * 
     */
    @Test
    public void testParmConst()
    {
        ClockDisplay12 cd12 = new ClockDisplay12(12,0,"a.m.");
        assertEquals(cd12.getTime(),"12:00a.m.");
        ClockDisplay12 cd12a = new ClockDisplay12(0,0,"hi");
        assertEquals(cd12a.getTime(),"12:00a.m.");
        ClockDisplay12 cd12b = new ClockDisplay12(11,59,"p.m.");
        assertEquals(cd12b.getTime(),"11:59p.m.");
    }
    
    /**
     * Test tick
     * 
     */
    @Test
    public void testTick()
    {
        ClockDisplay12 cd12 = new ClockDisplay12(11,59,"p.m.");
        assertEquals(cd12.getTime(),"11:59p.m.");
        cd12.timeTick();
        assertEquals(cd12.getTime(),"12:00a.m.");
        cd12.timeTick();
        assertEquals(cd12.getTime(),"12:01a.m.");
        ClockDisplay12 cd12a = new ClockDisplay12(11,58,"a.m.");
        assertEquals(cd12a.getTime(),"11:58a.m.");
        cd12a.timeTick();
        assertEquals(cd12a.getTime(),"11:59a.m.");
        cd12a.timeTick();
        assertEquals(cd12a.getTime(),"12:00p.m.");
        cd12a.timeTick();
        assertEquals(cd12a.getTime(),"12:01p.m.");
    }
    
    
    /**
     * Test set Time
     * 
     */
    @Test
    public void testSetTime()
    {
        ClockDisplay12 cd12 = new ClockDisplay12(12,0,"a.m.");
        assertEquals(cd12.getTime(),"12:00a.m.");
        cd12.setTime(0,0,"hi");
        assertEquals(cd12.getTime(),"12:00a.m.");
        cd12.setTime(11,59,"p.m.");
        assertEquals(cd12.getTime(),"11:59p.m.");
        // 12 causes a change; 75 doesn't; and "x" doesn't
        // or if student has interpreted it as changing "x" to "a.m." you get the second choice below
        cd12.setTime(12,75,"x");
        assertTrue(cd12.getTime().equals("12:59p.m.")||cd12.getTime().equals("12:59a.m."));
    }
    
    /**
     * Test tick and set Time
     * 
     */
    /*@Test
    public void testTickSetTime()
    {
        ClockDisplay12 cd12 = new ClockDisplay12();
        assertEquals(cd12.getTime(),"12:00a.m.");
        cd12.timeTick();
        assertEquals(cd12.getTime(),"12:01a.m.");
        cd12.setTime(12,75,"hi");
        assertEquals(cd12.getTime(),"12:00a.m.");
        cd12.setTime(0,0,"a.m.");
        assertEquals(cd12.getTime(),"12:00a.m.");
        cd12.setTime(11,59,"p.m.");
        assertEquals(cd12.getTime(),"11:59p.m.");
    }*/
    
}

