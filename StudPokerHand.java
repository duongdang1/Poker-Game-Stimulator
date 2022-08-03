package proj4;

import java.sql.Array;
import java.util.ArrayList;

public class StudPokerHand {
    private static int MAX_CARD_IN_STUD = 2;
    private static int MAX_CARD_IN_HAND = 5;
    private ArrayList<Card> studPokerHand;
    private static int SIZE = 5;
    private static int COUNT = 0;
    private CommunityCardSet cc;

    /**
     * This class creates the stud poker hand object
     * @param cc this is the community card set
     * @param cardList This is the given cardlist that is use to make the stud poker hand
     */
    public StudPokerHand(CommunityCardSet cc, ArrayList<Card> cardList){
        this.cc = cc;
        studPokerHand = new ArrayList<Card>();
        for (int index = 0; index < MAX_CARD_IN_STUD; index++){
            addCard(cardList.get(index));
        }
    }

    /**
     * This method add a card in to the stud poker hand
     * @param card this is the card that will be added to the stud poker hand
     */
    public void addCard(Card card){
        studPokerHand.add(card);
    }

    /**
     * This method return the card at the given index from the stud poker hand
     * @param index the index of the card in the stud poker hand
     * @return the card at the given index in the stud poker hand
     */
    public Card getIthCard(int index){
        if (index >= 0){
            return studPokerHand.get(index);
        }else{
            return null;
        }
    }

    /**
     * This method get all the 5 cards hand combination from the given seven card
     * @return the method all the 5 cards hand combinations.
     */
    private ArrayList<PokerHand> getAllFiveCardHands(){
        ArrayList<PokerHand> allFiveCardsHand = new ArrayList<PokerHand>();
        for (int index = 0; index < MAX_CARD_IN_HAND + MAX_CARD_IN_STUD; index++){
            for (int j = index + 1; j < MAX_CARD_IN_HAND + MAX_CARD_IN_STUD; j++){
                ArrayList<Card> combination = new ArrayList<Card>();
                combination.addAll(studPokerHand);
                combination.addAll(cc.communityPokerHand);
                combination.remove(combination.get(j));
                combination.remove(combination.get(index));
                PokerHand pokerHand = new PokerHand(combination);
                if (!allFiveCardsHand.contains(pokerHand)){
                    allFiveCardsHand.add(pokerHand);
                }
            }
        }
        return allFiveCardsHand;
    }

    /**
     * This method get the best hand from the list of all five cards hand
     * @return the best poker hand from the list of all five cards hand
     */
    private PokerHand getBestFiveCardHand(){
        ArrayList<PokerHand> hands = getAllFiveCardHands();
        PokerHand bestSoFar = hands.get(0);
        for (int index = 1; index < hands.size(); index++){
            if (hands.get(index).compareTo(bestSoFar) > 0){
                bestSoFar = hands.get(index);
            }
        }

        return bestSoFar;
    }


    /**
     * Determines how this hand compares to another hand, using the
     * community card set to determine the best 5-card hand it can
     * make. Returns positive, negative, or zero depending on the comparison.
     *
     * @param other The hand to compare this hand to
     * @return a negative number if this is worth LESS than other, zero
     * if they are worth the SAME, and a positive number if this is worth
     * MORE than other
     */
    public int compareTo(StudPokerHand other){
        other.getAllFiveCardHands();
        PokerHand otherBestHand = other.getBestFiveCardHand();
        PokerHand thisHand = getBestFiveCardHand();
        if (thisHand.compareTo(otherBestHand) > 0){
            return 1;
        }else if(getBestFiveCardHand().compareTo(otherBestHand) == 0){
            return 0;
        }return -1;
    }

    public String toString(){
        return " " + studPokerHand;
    }
}
