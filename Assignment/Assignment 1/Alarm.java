/**
 * The alarm time (represented by 12 hour clock display)
 * and whether or not it is set.
 * 
 * @author Lynn Marshall 
 * @version Sample Solution Part 2 
 */
public class Alarm
{
    private ClockDisplay12 alarmTime;
    private boolean set;

    /**
     * Constructor for objects of class Alarm.  The alarm
     * time is set to midnight and the alarm is not set.
     */
    public Alarm()
    {
        alarmTime = new ClockDisplay12();
        set = false;
    }
    
    /**
     * Constructor for objects of class Alarm, where the time
     * and alarm status is provided.  If any value is invalid
     * it is changed to a valid value: hours to 12, minutes to 0,
     * amPm to "a.m.".
     * 
     * @param hours The alarm time hours (1-12)
     * @param minutes The alarm time minutes (0-59)
     * @param amPm "a.m." or "p.m."
     * @param set Whether the alarm is set or not (boolean)
     */
    public Alarm(int hours, int minutes, String amPm, boolean set)
    {
        alarmTime = new ClockDisplay12(hours, minutes, amPm);
        this.set = set;
    }

    /**
     * Sets the alarm time.  If any value is invalid
     * it is changed to a valid value: hours to 12, minutes to 0,
     * amPm to "a.m.".
     * 
     * @param hours The alarm time hours (1-12)
     * @param minutes The alarm time minutes (0-59)
     * @param amPm "a.m." or "p.m."
     */
    public void setTime(int hours, int minutes, String amPm)
    {
        alarmTime.setTime(hours, minutes, amPm);
    }
    
    /**
     * Turns the alarm on.  (No effect if alarm is already on.)
     */
    public void turnOn()
    {
        set = true;
    }
    
    /**
     * Turns the alarm off.  (No effect if alarm is already off.)
     */
    public void turnOff()
    {
        set = false;
    }
    
    /**
     * Returns a String representing the time, e.g. "12:01a.m."
     * 
     * @return String the current alarm time
     */
    public String getTime()
    {
        return alarmTime.getTime();
    }
    
    /**
     * Returns true if the alarm is set, false otherwise.
     * 
     * @return boolean true if alarm is set, false otherwise
     */
    public boolean isSet()
    {
        return set;
    }
    
}
