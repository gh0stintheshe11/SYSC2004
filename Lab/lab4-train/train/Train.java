import java.util.ArrayList;

public class Train
{
    private ArrayList<Car> cars;
    
    public Train(int numberOfCars)
    {
        cars = new ArrayList<Car>(0);
    }
   
    public void addCar(Car car)
    {
        cars.add(car);
    }

    public ArrayList<Car> cars()
    {
        return cars;
    }    
    
    public boolean issueTicket(boolean requestedClass)
    {
        return false;
    }
   
    public boolean cancelTicket(int id, int seatNo)
    {
        return false;
    }
}
