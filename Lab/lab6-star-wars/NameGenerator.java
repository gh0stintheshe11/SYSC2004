import java.util.*;
/**
 * Write a description of class NameGenerator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NameGenerator
{

    /**
     * Constructor for objects of class NameGenerator
     */
    public NameGenerator()
    {

    }

    private String starWarFirstName(String firstName,String lastName){
        String x = Character.toString(lastName.charAt(0));
        String y = Character.toString(lastName.charAt(1));
        String z = Character.toString(lastName.charAt(2));
        String a = Character.toString(firstName.charAt(0));
        String b = Character.toString(firstName.charAt(1));
        String m = x + y + z + a + b;
        return m;
    }
    
    private String starWarFirstName2(String firstName,String lastName){
        String x = lastName.substring(0,3);
        String y = firstName.substring(0,2);
        String m = x + y;
        return m;
    }

    private String starWarLastName(String maidenName, String cityBorn){
        String x = Character.toString(maidenName.charAt(0));
        String y = Character.toString(maidenName.charAt(1));
        String z = Character.toString(cityBorn.charAt(0));
        String a = Character.toString(cityBorn.charAt(1));
        String b = Character.toString(cityBorn.charAt(2));
        String n = x + y + z + a + b;
        return n;
    }
    
    private String starWarLastName2(String maidenName, String cityBorn){
        String x = maidenName.substring(0,2);
        String y = cityBorn.substring(0,3);
        String m = x + y;
        return m;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void generateStarWarsName(String firstName,
    String lastName,String motherName, String cityBorn)
    {
        // put your code here
        String s = starWarFirstName(firstName,lastName) 
            + " " + starWarLastName(motherName,cityBorn);
        String m = starWarFirstName2(firstName,lastName) 
            + " " + starWarLastName2(motherName,cityBorn);
        System.out.println(s+"\n"+m);
    }
}
