
/**
 * An AlarmClock has a time and an alarm time.  If the alarm is set, it
 * rings when time is equal to alarm time.
 * 
 * @author Lynn Marshall 
 * @version Sample Solution Part 3 
 */
public class AlarmClock
{
    private ClockDisplay12 clock; // the time
    private Alarm alarmTime; // the alarm

    /**
     * An alarm clock defaults to a clock display of midnight
     * an alarm time of midnight, and no alarm set.   
     */
    public AlarmClock()
    {
        clock = new ClockDisplay12();
        alarmTime = new Alarm(); 
    }
    
    /**
     * We can set the time, the alarm time, and whether the alarm is set.
     * 
     *  @param clockhour The hour portion of the clock time (1-12)
     *  @param clockmin The minutes portion of the clock time (0-59)
     *  @param clockamPm Whether the clock time is "a.m." or "p.m."
     *  @param alarmhour The hour portion of the alarm time (1-12)
     *  @param alarmmin The minutes portion of the alarm time (0-59)
     *  @param alarmamPm Whether the alarm time is "a.m." or "p.m."
     *  @param alarmset Whether or not the alarm is set (boolean)
     */
    public AlarmClock(int clockhour, int clockmin, String clockamPm,
        int alarmhour, int alarmmin, String alarmamPm, boolean alarmset)
    {
        clock = new ClockDisplay12(clockhour,clockmin,clockamPm);
        alarmTime = new Alarm(alarmhour,alarmmin,alarmamPm,alarmset); // doesn't matter as alarm is not set
    }
    
    /**
     * Set the clock time. 
     * 
     *  @param hour The hour portion of the time (1-12)
     *  @param min The minutes portion of the time (0-59)
     *  @param amPm Whether the time is "a.m." or "p.m."
     */
    public void setTime(int hour, int min, String amPm)
    {
        clock.setTime(hour,min,amPm);
    }
    
    /**
     * Set the alarm time. 
     * 
     *  @param hour The hour portion of the time (1-12)
     *  @param min The minutes portion of the time (0-59)
     *  @param amPm Whether the time is "a.m." or "p.m."
     */
    public void setAlarmTime(int hour, int min, String amPm)
    {
        alarmTime.setTime(hour,min,amPm);
    }

    /**
     * The clock time moves forward one minute.  If the alarm time
     * equals the new clock time, the alarm will ring and then 
     * be turned off.  The alarm rings by outputting "RING RING RING"
     * to the console.
     */
    public void clockTick()
    {
        clock.timeTick();
        if (isAlarmSet() && getAlarmTime().equals(getTime())) {
            System.out.println("RING RING RING");
            unsetAlarm();
        }
    }
    
    /**
     * Turns the alarm on.  Has no effect if the alarm is already
     * turned on.
     */
    public void setAlarm()
    {
        alarmTime.turnOn();
    }

    /**
     * Turns the alarm off.  Has no effect if the alarm is already
     * turned off.
     */
    public void unsetAlarm()
    {
        alarmTime.turnOff();
    }
    
    /**
     * Returns a String representing the current time, e.g. "12:01a.m.".
     * 
     * @return String the current time
     */
    public String getTime()
    {
        return clock.getTime();
    }
    
    /**
     * Returns a String representing the current alarm time, 
     * e.g. "12:01a.m.".
     * 
     * @return String the current alarm time
     */
    public String getAlarmTime()
    {
        return alarmTime.getTime();
    }
    
    /**
     * Returns true if the alarm is set and false otherwise.
     * 
     * @return boolean true if alarm set, false otherwise
     */
    public boolean isAlarmSet()
    {
        return alarmTime.isSet();
    }
    
}
