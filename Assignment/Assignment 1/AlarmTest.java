

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class AlarmTest.
 *
 * @author  Lynn Marshall
 * @version A1 Solution
 */
public class AlarmTest
{
    /**
     * Default constructor for test class AlarmTest
     */
    public AlarmTest()
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
     * Test default constructor.
     *
     */
    @Test
    public void testConst()
    {
        Alarm alarm = new Alarm();
        assertEquals(alarm.getTime(),"12:00a.m.");
        assertEquals(alarm.isSet(),false);
    }
    
    /**
     * Test parm constructor.
     *
     */
    @Test
    public void testParmConst()
    {
        Alarm alarm = new Alarm(12,0,"a.m.",false);
        assertEquals(alarm.getTime(),"12:00a.m.");
        assertEquals(alarm.isSet(),false);
        Alarm alarm1 = new Alarm(0,0,"hi",true);
        assertEquals(alarm1.getTime(),"12:00a.m.");
        assertEquals(alarm1.isSet(),true);
        Alarm alarm2 = new Alarm(11,30,"p.m.",false);
        assertEquals(alarm2.getTime(),"11:30p.m.");
        assertEquals(alarm2.isSet(),false);
    }
    
    /**
     * Test set time.
     *
     */
    @Test
    public void testsetTime()
    {
        Alarm alarm = new Alarm(12,0,"a.m.",false);
        assertEquals(alarm.getTime(),"12:00a.m.");
        assertEquals(alarm.isSet(),false);
        alarm.setTime(0,0,"hi");
        assertEquals(alarm.getTime(),"12:00a.m.");
        assertEquals(alarm.isSet(),false);
        alarm.setTime(11,30,"p.m.");
        assertEquals(alarm.getTime(),"11:30p.m.");
        assertEquals(alarm.isSet(),false);
    }
    
    /**
     * Test set alarm.
     *
     */
    @Test
    public void testsetAlarm()
    {
        Alarm alarm = new Alarm(12,0,"a.m.",false);
        assertEquals(alarm.getTime(),"12:00a.m.");
        assertEquals(alarm.isSet(),false);
        alarm.turnOn();
        assertEquals(alarm.getTime(),"12:00a.m.");
        assertEquals(alarm.isSet(),true);
        alarm.turnOn();
        assertEquals(alarm.getTime(),"12:00a.m.");
        assertEquals(alarm.isSet(),true);
        alarm.turnOff();
        assertEquals(alarm.getTime(),"12:00a.m.");
        assertEquals(alarm.isSet(),false);
        alarm.turnOff();
        assertEquals(alarm.getTime(),"12:00a.m.");
        assertEquals(alarm.isSet(),false);
    }
}
