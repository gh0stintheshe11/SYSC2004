import java.util.ArrayList;

/**
 * The test class TrainTest.
 *
 * @author  Lynn Marshall
 * @version May 2015
 */
public class TrainTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class TrainTest
     */
    private Train aTrain;
    private Car car1,car2,car3,car4;
    private ArrayList<Car> cars;
    public TrainTest()
    {
        aTrain = new Train();
        cars = new ArrayList<Car>();
        car1 = new Car(1250, true);
        car2 = new Car(1300, false);
        car3 = new Car(1740, false);
        car4 = new Car(4321, true);
        aTrain.addCar(car1);
        aTrain.addCar(car2);
        aTrain.addCar(car3);
        aTrain.addCar(car4);
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {

    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

    public void testCreateEmptyTrain()
    {
        Train aTrain = new Train();

        /* Verify that a new train has no cars. */
        assertEquals(0, aTrain.cars().size());
    }

    public void testAddCar()
    {
        ArrayList<Car> cars = aTrain.cars();
        assertEquals(4, cars.size());

        /* Verify that each car added to the train was placed at
         * the end of the list.
         */

        /* Important - assertSame() does not compare the Car objects 
         * referred to by car1 and aCar to detemine if they are equal
         * (have the same state). It verifies that car1 an aCar refer to
         * the same object; i.e., that the Car (reference) retrieved by get(0)
         * is the first first that was added to the ArrayList.
         */
        assertSame(car1, cars.get(0));

        assertSame(car2, cars.get(1));

        assertSame(car3, cars.get(2)); 

        assertSame(car4, cars.get(3));
    }

    public void testIssueTicket()
    {
        /* Book all the seats in the business-class car. */
        for (int i = 0; i <Car.BUSINESS_SEATS*2; i++) {
            assertTrue(aTrain.issueTicket(true));
        }

        /* Attempt to book one more business-class seat, even
         * though they are all booked.
         */
        assertFalse(aTrain.issueTicket(true)); 

        ArrayList<Car> cars = aTrain.cars();

        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }

        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(1).seats()[i].isBooked());
        }

        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(2).seats()[i].isBooked());
        }

        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }

        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }

        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }

        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(1).seats()[i].isBooked());
        }

        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(2).seats()[i].isBooked());
        }

        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }

        /* Book all the seats in the second economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }

        /* check that all seats are now booked */
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }

        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(1).seats()[i].isBooked());
        }

        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(2).seats()[i].isBooked());
        } 

        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }

    
        /* Try to book another business class seat (fails)*/
        assertFalse(aTrain.issueTicket(true));
        /* Try to book another economy class seat (fails)*/
        assertFalse(aTrain.issueTicket(false));
    }

    public void testCancelTicket()
    {
        /* Book all the seats in the business-class car. */
        for (int i = 0; i <Car.BUSINESS_SEATS; i++) {
            assertTrue(aTrain.issueTicket(true));
        }

        for (int i = 0; i <(Car.BUSINESS_SEATS/2); i++) {
            assertTrue(aTrain.issueTicket(true));
        }

        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }

        ArrayList<Car> cars = aTrain.cars();

        assertTrue(aTrain.cancelTicket(4321,10));
        assertFalse(aTrain.cancelTicket(4321,20));

        assertTrue(aTrain.cancelTicket(1300, 4));
        assertFalse(cars.get(1).seats()[3].isBooked());        

        /* Cancel ticket in a non-existent car. */
        assertFalse(aTrain.cancelTicket(1500, 7));

        /* Cancel ticket in a non-existent seat. */
        assertFalse(aTrain.cancelTicket(1300, 54));

        /* Cancel ticket for a seat that hasn't been booked. */
        assertFalse(aTrain.cancelTicket(1740, 21));
        assertFalse(cars.get(2).seats()[20].isBooked());        

        /* Attempt to cancel the same ticket twice. */
        assertTrue(aTrain.cancelTicket(1250, 11));
        assertFalse(cars.get(0).seats()[10].isBooked());

        assertFalse(aTrain.cancelTicket(1250, 11));   
        assertFalse(cars.get(0).seats()[10].isBooked());         
    }

    public void testBookCancelTicket()
    {
        /* Book all the seats in the business-class car. */
        for (int i = 0; i <Car.BUSINESS_SEATS; i++) {
            assertTrue(aTrain.issueTicket(true));
        }

        for (int i = 0; i <(Car.BUSINESS_SEATS/2); i++) {
            assertTrue(aTrain.issueTicket(true));
        }

        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }

        ArrayList<Car> cars = aTrain.cars();
        aTrain.cancelTicket(1300,10);
        aTrain.cancelTicket(1300,20);
        aTrain.cancelTicket(1300,30);
        
        

        cars.get(2).seats()[5].book();
        cars.get(2).seats()[10].book();
        cars.get(2).seats()[20].book();
        cars.get(2).seats()[25].book();
        
        assertTrue(cars.get(2).seats()[5].isBooked());
        assertTrue(cars.get(2).seats()[10].isBooked());
        assertTrue(cars.get(2).seats()[20].isBooked());
        assertTrue(cars.get(2).seats()[25].isBooked());

        assertFalse(aTrain.cancelTicket(1740,5));
        assertFalse(aTrain.cancelTicket(1740,10));
        assertFalse(aTrain.cancelTicket(1740,20));
        assertFalse(aTrain.cancelTicket(1740,0));

        aTrain.cancelTicket(1250,10);
        aTrain.cancelTicket(4321,5);
        aTrain.cancelTicket(4321,10);

        cars.get(3).seats()[25].book();
        cars.get(3).seats()[20].book();
        cars.get(3).seats()[18].book();
        cars.get(3).seats()[17].book();

        //assertTrue(aTrain.cancelTicket(4321,25));
        //assertTrue(aTrain.cancelTicket(4321,20));
        //assertTrue(aTrain.cancelTicket(4321,18));
        //assertFalse(aTrain.cancelTicket(4321,16));
    }
}
