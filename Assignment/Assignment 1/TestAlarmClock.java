

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TestAlarmClock.
 *
 * @author  Lynn Marshall
 * @version A1 Solution
 */
public class TestAlarmClock
{
    /**
     * Default constructor for test class TestAlarmClock
     */
    public TestAlarmClock()
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
    public void testConst()
    {
        AlarmClock ac = new AlarmClock();
        assertEquals(ac.getTime(),"12:00a.m.");
        assertEquals(ac.getAlarmTime(),"12:00a.m.");
        assertEquals(ac.isAlarmSet(),false);
    }
    
    /**
     * Test parm constructor
     *
     */
    @Test
    public void testParmConst()
    {
        AlarmClock ac = new AlarmClock(12,0,"a.m.",12,0,"a.m.",false);
        assertEquals(ac.getTime(),"12:00a.m.");
        assertEquals(ac.getAlarmTime(),"12:00a.m.");
        assertEquals(ac.isAlarmSet(),false);
        AlarmClock ac1 = new AlarmClock(99,99,"99",100,100,"hello.",true);
        assertEquals(ac1.getTime(),"12:00a.m.");
        assertEquals(ac1.getAlarmTime(),"12:00a.m.");
        assertEquals(ac1.isAlarmSet(),true);
        AlarmClock ac2 = new AlarmClock(11,30,"p.m.",1,1,"p.m.",false);
        assertEquals(ac2.getTime(),"11:30p.m.");
        assertEquals(ac2.getAlarmTime(),"1:01p.m.");
        assertEquals(ac2.isAlarmSet(),false);
    }
    
    /**
     * Test set time
     *
     */
    @Test
    public void testSetTime()
    {
        AlarmClock ac = new AlarmClock(12,0,"a.m.",12,0,"a.m.",false);
        assertEquals(ac.getTime(),"12:00a.m.");
        assertEquals(ac.getAlarmTime(),"12:00a.m.");
        assertEquals(ac.isAlarmSet(),false);
        ac.setTime(99,99,"99");
        assertEquals(ac.getTime(),"12:00a.m.");
        assertEquals(ac.getAlarmTime(),"12:00a.m.");
        assertEquals(ac.isAlarmSet(),false);
        ac.setTime(11,30,"p.m.");
        assertEquals(ac.getTime(),"11:30p.m.");
        assertEquals(ac.getAlarmTime(),"12:00a.m.");
        assertEquals(ac.isAlarmSet(),false);
    }
    
    /**
     * Test set alarm time
     *
     */
    @Test
    public void testSetAlarmTime()
    {
        AlarmClock ac = new AlarmClock(12,0,"a.m.",12,0,"a.m.",false);
        assertEquals(ac.getTime(),"12:00a.m.");
        assertEquals(ac.getAlarmTime(),"12:00a.m.");
        assertEquals(ac.isAlarmSet(),false);
        ac.setAlarmTime(100,100,"hello.");
        assertEquals(ac.getTime(),"12:00a.m.");
        assertEquals(ac.getAlarmTime(),"12:00a.m.");
        assertEquals(ac.isAlarmSet(),false);
        ac.setAlarmTime(1,1,"p.m.");
        assertEquals(ac.getTime(),"12:00a.m.");
        assertEquals(ac.getAlarmTime(),"1:01p.m.");
        assertEquals(ac.isAlarmSet(),false);
    }
    
    /**
     * Test set alarm 
     *
     */
    @Test
    public void testSetAlarm()
    {
        AlarmClock ac = new AlarmClock(12,0,"a.m.",12,0,"a.m.",false);
        assertEquals(ac.getTime(),"12:00a.m.");
        assertEquals(ac.getAlarmTime(),"12:00a.m.");
        assertEquals(ac.isAlarmSet(),false);
        ac.setAlarm();
        assertEquals(ac.getTime(),"12:00a.m.");
        assertEquals(ac.getAlarmTime(),"12:00a.m.");
        assertEquals(ac.isAlarmSet(),true);
        ac.setAlarm();
        assertEquals(ac.getTime(),"12:00a.m.");
        assertEquals(ac.getAlarmTime(),"12:00a.m.");
        assertEquals(ac.isAlarmSet(),true);
        ac.unsetAlarm();
        assertEquals(ac.getTime(),"12:00a.m.");
        assertEquals(ac.getAlarmTime(),"12:00a.m.");
        assertEquals(ac.isAlarmSet(),false);
        ac.unsetAlarm();
        assertEquals(ac.getTime(),"12:00a.m.");
        assertEquals(ac.getAlarmTime(),"12:00a.m.");
        assertEquals(ac.isAlarmSet(),false);
    }
    
    /**
     * Test tick : Note: If the tests pass properly, "RING RING RING" is output exactly twice in the Terminal Window.
     * (Checking whether or not it is printed cannot be tested here.)
     *
     */
    @Test
    public void testClockTick()
    {
        AlarmClock ac = new AlarmClock(12,0,"a.m.",12,1,"a.m.",true);
        assertEquals(ac.getTime(),"12:00a.m.");
        assertEquals(ac.getAlarmTime(),"12:01a.m.");
        assertEquals(ac.isAlarmSet(),true);
        ac.clockTick();
        assertEquals(ac.getTime(),"12:01a.m.");
        assertEquals(ac.getAlarmTime(),"12:01a.m.");
        assertEquals(ac.isAlarmSet(),false);
        
        ac.setTime(11,59,"p.m.");
        ac.setAlarm();
        assertEquals(ac.getTime(),"11:59p.m.");
        assertEquals(ac.getAlarmTime(),"12:01a.m.");
        assertEquals(ac.isAlarmSet(),true);
        ac.clockTick();
        assertEquals(ac.getTime(),"12:00a.m.");
        assertEquals(ac.getAlarmTime(),"12:01a.m.");
        assertEquals(ac.isAlarmSet(),true);
        ac.clockTick();
        assertEquals(ac.getTime(),"12:01a.m.");
        assertEquals(ac.getAlarmTime(),"12:01a.m.");
        assertEquals(ac.isAlarmSet(),false);
        ac.clockTick();
        assertEquals(ac.getTime(),"12:02a.m.");
        assertEquals(ac.getAlarmTime(),"12:01a.m.");
        assertEquals(ac.isAlarmSet(),false);
        
        ac.setTime(11,59,"p.m.");
        ac.unsetAlarm();
        assertEquals(ac.getTime(),"11:59p.m.");
        assertEquals(ac.getAlarmTime(),"12:01a.m.");
        assertEquals(ac.isAlarmSet(),false);
        ac.clockTick();
        assertEquals(ac.getTime(),"12:00a.m.");
        assertEquals(ac.getAlarmTime(),"12:01a.m.");
        assertEquals(ac.isAlarmSet(),false);
        ac.clockTick();
        assertEquals(ac.getTime(),"12:01a.m.");
        assertEquals(ac.getAlarmTime(),"12:01a.m.");
        assertEquals(ac.isAlarmSet(),false);
        ac.clockTick();
        assertEquals(ac.getTime(),"12:02a.m.");
        assertEquals(ac.getAlarmTime(),"12:01a.m.");
        assertEquals(ac.isAlarmSet(),false);
    }
}
