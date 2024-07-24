
/**
 * Write a description of class EventPost here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EventPost extends Post
{
    // instance variables - replace the example below with your own
    public String eventType;
    public String eventName;
    public String date;
    public int day;
    public int month;
    public int year;

    /**
     * Constructor for objects of class EventPost
     */
    public EventPost(String author,String eventName)
    {
        // initialise instance variables
        super(author);
        this.eventName = eventName;
        eventType = null;
        date = null;
        day = 0;
        month = 0;
        year = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    public void setEventDate(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getEventDate(){
        return date;
    }

    public String getEventType()
    {
        return eventType;
    }

    public String toString(){
        date = day + "/" + month + "/" +year;
        return eventType + eventName + date + super.getAuthor();
    }
}
