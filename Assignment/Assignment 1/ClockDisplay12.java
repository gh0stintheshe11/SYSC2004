
/**
 * The ClockDisplay12 class implements a digital clock display for a
 * regular 12 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 12:00a.m. (midnight) to 11:59a.m. (1 minute 
 * before noon), and 12:00p.m. (noon) to 11:59p.m. (1 minute before 
 * midnight). 
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * This is based on the 24hr ClockDisplay class by
 * Michael Kölling and David J. Barnes; version 2011.07.31
 * 
 * @author Lynn Marshall
 * @version Sample Solution Part 1 
 */
public class ClockDisplay12
{
    private NumberDisplay hours; // 0 means 12
    private NumberDisplay minutes;
    private String amPm;
    private String displayString;    // simulates the actual display
    
    public static final String AM = "a.m.";
    public static final String PM = "p.m.";
    
    /**
     * Constructor for ClockDisplay12 objects. This constructor 
     * creates a new clock set at midnight (12:00am)
     */
    public ClockDisplay12()
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        amPm = AM;
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay12 objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.  If any parameter is invalid, it defaults to
     * a valid value (12 for hours, 0 for minutes, am for amPm)
     * 
     * @param hour The hour of the clock display (1 to 12)
     * @param minute The minute of the clock display (0 to 59)
     * @param amPm "a.m." or "p.m."
     */
    public ClockDisplay12(int hour, int minute, String amPm)
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        this.amPm = AM;
        setTime(hour, minute, amPm);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
            if (hours.getValue()==0) { // change am/pm
                if (amPm.equals(AM)) 
                    amPm = PM;
                else
                    amPm = AM;
            }
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute. If the hour is invalid it is unchanged.
     * If the minutes is invalid it is unchanged.
     * If the am/pm is invalid it can default to a.m. or be unchanged.
     * 
     * @param hour The hour of the clock display (1 to 12)
     * @param minute The minute of the clock display (0 to 59)
     * @param amPm "a.m." or "p.m."
     */
    public void setTime(int hour, int minute, String amPm)
    {
        if (hour==12) // change the 12 to a 0, so that this works properl
            hour=0;
        hours.setValue(hour);
        minutes.setValue(minute);
        if (amPm.equals(PM))
            this.amPm = PM;
        else if (amPm.equals(AM))
            this.amPm = AM; 
        // this version (above) won't change am/pm if value is invalid (note a.m. was initialized in the constructor)
        
        // alternate version -- uses a.m. if value is invalid:
        // if (amPm.equals(PM))
            // this.amPm = PM;
        // else 
            // this.amPm = AM; // as this is the default
        // either of the above is fine and there are two different
        // tests in ClockDisplay12Test
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     * 
     * @return String the current time
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     * This is where 0 is changed to 12 for the hours.
     */
    private void updateDisplay()
    {
        int hrs = hours.getValue();
        if (hrs==0) 
            hrs = 12;
        displayString = hrs + ":" + minutes.getDisplayValue() +
                        amPm;
    }
}
