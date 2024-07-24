/**
 * A Card is a playing card from an Anglo-American deck of cards.
 * 
 * @Lang Sun
 * @2/14/2019
 */
public class Card
{
    /** The card's suit: "hearts", "diamonds", "clubs", "spades". */
    private String suit;
    
    /** 
     * The card's rank, between 1 and 13 (1 represents the ace, 11 represents
     * the jack, 12 represents the queen, and 13 represents the king.)
     */
    private int rank;

    /**
     * Constructs a new card with the specified suit and rank.
     */
    public Card(String suit, int rank)
    {
        this.rank = rank;
        this.suit = suit;
    }
    
    /**
     * Returns this card's suit.
     */
    public String suit()
    {
        return suit;
    }
    
    /**
     * Returns this card's rank.
     */
    public int rank()
    {
        return rank;
    }
    
    /**
     * Determines if this card has the same rank as the specified card.
     */
    public boolean hasSameRank(Card aCard)
    {
        return (this.rank() == aCard.rank());
    }
    
    /**
     * Determines if this card is equal to the specified card.
     */
    public boolean isEqualTo(Card aCard)
    {
        return (this.suit().equals(aCard.suit()));
    }
}
