/**
 * This module creates a deck of card with methods to deal,shuffle,gather, and get size
 */
package proj4; // do not erase. Gradescope expects this.

import java.util.ArrayList;
import java.util.*;

public class Deck {
    private final static int SIZE = 52;
    private final static String DIAMOND = "Diamonds";
    private final static String HEART = "Hearts";
    private final static String CLUB = "Clubs";
    private final static String SPADE = "Spades";
    private final static String[] SUITES = {SPADE,HEART,CLUB,DIAMOND};
    private final static String[] RANKS = {"two","three","four","five","six","seven","eight","nine","ten","jack","queen","king","ace"};
    private static ArrayList<Card> deckOfCards = new ArrayList<Card>();
    private static int nextToDeal;

    /**
     * This constructor create an object Deck, which is a deck of cards with 52 Card objects.
     */
    public Deck(){
        nextToDeal = 0;
        for (String r: RANKS){
            for (String s: SUITES){
                Card card = new Card(r,s);
                deckOfCards.add(card);
            }
        }
    }

    /**
     * This method shuffle the deck of cards
     */
    public void shuffle(){
        Random rand = new Random();
        int randomIndex;
        for (int index = 0;index < size(); index++){
            randomIndex = rand.nextInt(size());
            Card temp = deckOfCards.get(index);
            deckOfCards.set(index,deckOfCards.get(randomIndex));
            deckOfCards.set(randomIndex,temp);
        }
    }

    /**
     * This method deals a card from a deck when called
     * @return the Card
     */
    public Card deal(){
        if(deckOfCards.size() - this.nextToDeal >= 1){
            Card dealCard = deckOfCards.get(this.nextToDeal);
            nextToDeal += 1;
            return dealCard;
        }else{
            return null;
        }
    }

    public void gather(){
        nextToDeal = 0;
    }
    /**
     * This method return the current size of the deck after being dealt
     * @return the size of the deck.
     */
    public int size(){
        return deckOfCards.size() - nextToDeal;
    }

    public boolean isEmpty(){
        return nextToDeal == 52;

    }

    /**
     * return the visual representation of a deck of cards
     * @return the visual representation of a deck of cards
     */
    public String toString(){
        String deck = "";
        for (int i = 0; i < size(); i++){
            deck += deckOfCards.get(i).getRank() + " of "+ deckOfCards.get(i).getSuite() + " | ";

        }
        return deck;
    }


}
