import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * 
 * @author Lynn Marshall
 * @version A4 Solution 
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Stack<Room> previousRoomStack;

    // the item being held
    private Item holding;

    // the number of items we can carry before we need to eat
    private int numCarryBeforeEat;

    // food that we can eat
    public static final String FOOD = "cookie";

    // beamer that we can charge/fire
    public static final String BEAMER = "beamer";

    /**
     * Create the game and initialise its internal map, as well
     * as the previous room (none) and previous room stack (empty),
     * and what the player is holding (nothing), and how many things
     * he/she can carry before needing to pick up and eat a cookie.
     * So far we have no rooms.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        previousRoom = null;
        previousRoomStack = new Stack<Room>();
        holding = null; 
        numCarryBeforeEat = 0;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;
        TransporterRoom transporter;
        Item chair, bar, computer, computer2, tree, cookie;
        Beamer beamer;

        // create some items
        chair = new Item("a wooden chair",5, "chair");
        bar = new Item("a long bar with stools",95.67, "bar");
        computer = new Item("a PC",10, "PC");
        computer2 = new Item("a Mac",5, "Mac");
        tree = new Item("a fir tree",500.5, "tree");
        cookie = new Item("a yummy chocolate chip cookie", .2,"cookie");
        beamer = new Beamer();

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        transporter = new TransporterRoom();

        // put items in the rooms
        outside.addItem(tree);
        outside.addItem(tree);
        outside.addItem(beamer);
        theatre.addItem(chair);
        theatre.addItem(cookie);
        pub.addItem(bar);
        pub.addItem(cookie);
        pub.addItem(cookie);
        lab.addItem(chair);
        lab.addItem(computer);
        lab.addItem(chair);
        lab.addItem(computer2);
        office.addItem(chair);
        office.addItem(computer);
        office.addItem(cookie);
        office.addItem(beamer);
        transporter.addItem(cookie);
        transporter.addItem(chair);

        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);
        lab.setExit("south", transporter);

        office.setExit("west", lab);

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printRoomAndCarry();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command The command to be processed
     * @return true If the command ends the game, false otherwise
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look(command);
        }
        else if (commandWord.equals("eat")) {
            eat(command);
        }
        else if (commandWord.equals("back")) {
            back(command);
        }
        else if (commandWord.equals("stackBack")) {
            stackBack(command);
        }
        else if (commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("drop")) {
            drop(command);
        }
        else if (commandWord.equals("charge")) {
            charge(command);
        }
        else if (commandWord.equals("fire")) {
            fire(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print a cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getCommands());
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * If we go to a new room, update previous room and previous room stack.
     * 
     * @param command The command to be processed
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            previousRoom = currentRoom; // store the previous room
            previousRoomStack.push(currentRoom); // and add to previous room stack
            currentRoom = nextRoom;
            printRoomAndCarry();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @param command The command to be processed
     * @return true, if this command quits the game, false otherwise
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /** 
     * "Look" was entered. Check the rest of the command to see
     * whether we really want to look.
     * 
     * @param command The command to be processed
     */
    private void look(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Look what?");
        }
        else {
            // output the long description of this room and what we're carrying
            printRoomAndCarry();
        }
    }

    /** 
     * "Eat" was entered. Check the rest of the command to see
     * whether we really want to eat.
     * 
     * @param command The command to be processed
     */
    private void eat(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Eat what?");
        }
        else if (holding==null || !holding.getName().equals(FOOD)) {
            // output that we have not eaten
            System.out.println("You are not carrying a " + FOOD + ".");
        } else {
            // output that we have eaten
            System.out.println("You have eaten and are no longer hungry.");
            holding=null; // food is gone!
            // we can now carry 5 items before we need another cookie
            numCarryBeforeEat=5; // or could choose to add 5
        }
    }

    /** 
     * "Back" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @param command The command to be processed
     */
    private void back(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Back what?");
        }
        else {
            // go back to the previous room, if possible
            if (previousRoom==null) {
                System.out.println("No room to go back to.");
            } else {
                // go back and swap previous and current rooms,
                // and put current room on previous room stack
                Room temp = currentRoom;
                currentRoom = previousRoom;
                previousRoom = temp;
                previousRoomStack.push(temp);
                // and print description
                printRoomAndCarry();
            }
        }
    }

    /** 
     * "StackBack" was entered. Check the rest of the command to see
     * whether we really want to stackBack.
     * 
     * @param command The command to be processed
     */
    private void stackBack(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("StackBack what?");
        }
        else {
            // step back one room in our stack of rooms history, if possible
            if (previousRoomStack.isEmpty()) {
                System.out.println("No room to go stack back to.");
            } else {
                // current room becomes previous room, and
                // current room is taken from the top of the stack
                previousRoom = currentRoom;
                currentRoom = previousRoomStack.pop();
                // and print description
                printRoomAndCarry();
            }
        }
    }

    /** 
     * "Take" was entered. Check the rest of the command to see
     * whether we really want to take.
     * 
     * If so, check to see if that item exists in the current
     * room.  If so, remove it from the room and the player is
     * now carrying it.
     * 
     * @param command The command to be processed
     */
    private void take(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return;
        }

        if (holding!=null) {
            // can't take
            System.out.println("You are already holding something.");
            return;
        }

        // store the item name that we want to pick up
        String itemName = command.getSecondWord();
        
        if (numCarryBeforeEat<=0 && !itemName.equals(FOOD)) {
            // we need a cookie next and this isn't a cookie
            System.out.println("You must take and eat a cookie before taking anything else.");
            return;
        }

        // attempt to remove that item from room and store in holding
        holding = currentRoom.removeItem(itemName);  
        if (holding==null) {
            // item was not found
            System.out.println("That item is not in the room.");
        } else {
            // item has been picked up
            System.out.println("You picked up " + itemName + ".");
            numCarryBeforeEat--; // decrement number of things carried
            // Note that we could pick up and drop cookies without
            // eating them, thus the <=0 in the check above
        }
    }

    /** 
     * "Drop" was entered. Check the rest of the command to see
     * whether we really want to drop.
     * 
     * If so, and we are carrying something, put it down.
     * Otherwise, the command has no effect.
     * 
     * @param command The command to be processed
     */
    private void drop(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Drop what?");
        } else if (holding==null) {
            // output that we have eaten
            System.out.println("You have nothing to drop.");
        } else {
            // put the item in the room
            currentRoom.addItem(holding);
            System.out.println("You have dropped " + holding.getName() + ".");
            holding=null;
        }
    }

    /** 
     * "Charge" was entered. Check the rest of the command to see
     * whether we really want to charge.
     * 
     * If so, and we are carrying a beamer, it gets charged.
     * Otherwise, the command has no effect.
     * 
     * @param command The command to be processed
     */
    private void charge(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Charge what?");
        } else if (holding==null || !holding.getName().equals(BEAMER)) {
            // not holding a beamer
            System.out.println("You have no " + BEAMER + " to charge.");
        } else {
            // we have a beamer
            Beamer b = (Beamer) holding;
            if (b.isCharged()) {
                // beamer already charged
                System.out.println("The " + BEAMER + " is already charged.");
            } else {
                // charge the beamer by storing the current room
                b.charge(currentRoom);
                System.out.println(BEAMER + " successfully charged.");
            }
        }
    }

    /** 
     * "Fire" was entered. Check the rest of the command to see
     * whether we really want to fire.
     * 
     * If so, and we are carrying a beamer, and it is charged,
     * it gets fired.
     * Otherwise, the command has no effect.
     * 
     * @param command The command to be processed
     */
    private void fire(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Fire what?");
        } else if (holding==null || !holding.getName().equals(BEAMER)) {
            // not holding a charged beamer
            System.out.println("You are not holding a " + BEAMER + ".");
        } else {
            // we have a beamer
            Beamer b = (Beamer) holding;
            if (!b.isCharged()) {
                // beamer is not charged
                System.out.println("The " + BEAMER + " is not charged.");
                return;
            }
            // fire the beamer, which returns us to the stored room
            System.out.println(BEAMER + " successfully fired.");
            previousRoom = currentRoom; // store the previous room
            previousRoomStack.push(currentRoom); // and add to previous room stack
            currentRoom = b.fire();
            printRoomAndCarry();
        }
    }

    /**
     * Print out a description of the room and what we are carrying, 
     * if anything.
     */
    private void printRoomAndCarry() 
    {
        // output the long description of this room
        System.out.println(currentRoom.getLongDescription());
        // and output what we are carrying
        if (holding==null) {
            System.out.println("You are not carrying anything.");
        } else {
            System.out.println("You are carrying:");
            System.out.println("    " + holding.getDescription());
        }
    }
}
