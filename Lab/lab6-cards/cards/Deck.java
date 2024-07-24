import java.util.ArrayList;
import java.util.Random;

/**
 * Deck models a deck of 52 Anglo-American playing cards.
 * 
 * @Lang Sun
 * @2/14/2019
 *
 */
public class Deck
{
    /** 
     * The cards are stored in a linked-list implementation of the
     * List collection.
     */
    private ArrayList<Card> cards;
    
    /** Lowest ranking card (the ace). */
    private static final int ACE = 1;

    /** Highest ranking card (the king). */
    private static final int KING = 13;
    
    
    /** other ranking cards (Q and J). */
    public static final int QUEEN = 12;
    public static final int JACK = 11;
    
    /** 
     * Total number cards in the deck (4 suits, each with 13 cards of 
     * different ranks).
     */ 
    private static final int TOTAL_CARDS = 52;
    
    /** 
     * Some constants that define the Strings for the various suits.
     */ 
    private static final String HEARTS = "hearts";
    private static final String DIAMONDS = "diamonds";
    private static final String CLUBS = "clubs";
    private static final String SPADES = "spades";

    /**
     * Constructs a new, unshuffled deck containing 52 playing cards.
     */
    public Deck()
    {
        cards = new ArrayList<Card>();
        buildSuit(SPADES);
        buildSuit(DIAMONDS);
        buildSuit(HEARTS);
        buildSuit(CLUBS);
    }

    /**
     * Creates the 13 cards for the specified suit, and adds them
     * to this deck.
     */
    private void buildSuit(String suit)
    {
        for (int i = ACE; i <= KING; i++) 
        {
            cards.add(new Card(suit, i));
        }
    }
    
    /**
     * Shuffles this deck of cards.
     */
    public void shuffle()
    { 
        Random r = new Random();
        for (int i = 0; i < 10000; i++) 
        {
            int a = r.nextInt(cards.size());
            int b = r.nextInt(cards.size());
            Card tmp = cards.get(a);
            cards.set(a, cards.get(b));
            cards.set(b, tmp);
        }
    }
      
    /**
     * Removes a card from this deck.
     */
    public Card dealCard()
    {
        if (isEmpty()) 
        {
            return null;
        }
        return cards.remove(0);
    }
 
    /**
     * Determines if this deck is empty.
     */
    public boolean isEmpty()
    {
         return cards.isEmpty();
    }
  
    /**
     * Returns the number of cards that are currently in the deck.
     */
    public int size()
    {
         return cards.size();
    }
}
