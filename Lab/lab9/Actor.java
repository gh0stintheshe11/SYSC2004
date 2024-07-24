import java.util.*;
/**
 * Write a description of class Actor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface Actor
{
    // instance variables - replace the example below with your own

    void act(List<Actor> newActors);
    
    boolean isActive();
}
