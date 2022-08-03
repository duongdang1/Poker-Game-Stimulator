package proj4;
import java.util.ArrayList;
import java.util.*;

public class CommunityCardSet {
    private static int MAX_NUM_CARD = 5;
    public static ArrayList<Card> communityPokerHand;

    /**
     * This class create an object Community card set that includes 5 cards
     * @param cardList the list of cards that will be use to create the 5 cards community card set
     */
    public CommunityCardSet(ArrayList<Card> cardList){
        communityPokerHand = new ArrayList<Card>();
        for(int index = 0; index < MAX_NUM_CARD; index++){
            addCard(cardList.get(index));
        }
    }

    /**
     * this method adds a card into the community card set
     * @param card the card that will be added into the community card set
     */
    public void addCard(Card card){
        communityPokerHand.add(card);
    }

    /**
     * this method returns the card at a given index
     * @param index the index of the card.
     * @return the card at the given index
     */
    public Card getIthCard(int index){
        if (index >= 0){
            return communityPokerHand.get(index);
        }
        else{
            return null;
        }
    }

    /**
     * return the size of the community card set
     * @return the size of the community card set
     */
    public int size(){
        return communityPokerHand.size();
    }
    public String toString(){
        return "The community cards are:" + communityPokerHand;
    }
}
