/**
 * This module creates a representation of a poker hand and do comparison between poker hands
 */
package proj4; // do not erase. Gradescope expects this.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PokerHand {
    private final static int MAX_CARD_IN_HAND = 5;
    private final ArrayList<Card> pokerHand;
    private final static int SIZE = 4;
    private final static int FLUSH = 3;
    private final static int TWO_PAIR = 2;
    private final static int ONE_PAIR = 1;
    private final static int HIGH_CARD = 0;

    /**
     * This class creates an object PokerHand that has 5 cards.
     * @param cardList a list of cards that push into the parameter
     */
    public PokerHand(ArrayList<Card> cardList) {
        this.pokerHand = new ArrayList<Card>();
        for (int index = 0; index < MAX_CARD_IN_HAND; index++) {
            addCard(cardList.get(index));
        }
    }

    /**
     * This method add a card to the poker_hand
     * @param card a card that will be added.
     */
    public void addCard(Card card) {
        if (this.pokerHand.size() < MAX_CARD_IN_HAND){
            this.pokerHand.add(card);
        }
    }

    /**
     * This method returns the card at a given index in the poker_hand
     * @param index index of the card
     * @return the card at a given index in the poker_hand
     */
    private Card getIthCard(int index) {
        return this.pokerHand.get(index);
    }

    /**
     * This method count the number of pairs in a poker_hand
     * @return the number of paris in a poker_hand
     */
    private int countPair() {
        int pairCount = 0;
        int indexCount = 0;
        ArrayList<Integer> rankList = new ArrayList<Integer>();
        for (int index = 0; index < MAX_CARD_IN_HAND; index++) {
            rankList.add(getIthCard(index).getRank());
        }
        Collections.sort(rankList);
        while (indexCount < rankList.size() - 1) {
            if (rankList.get(indexCount).equals(rankList.get(indexCount + 1))) {
                pairCount++;
                indexCount += 2;
            } else {
                indexCount++;
            }
        }
        return pairCount;

    }

    /**
     * This method checks if the hands is flush
     * @return True if the poker_hand is flush
     */
    private boolean checkFlush() {
        int sameSuiteCount = 0;
        String rootSuite = pokerHand.get(0).getSuite();
        for (Card card : pokerHand) {
            if (rootSuite.equals(card.getSuite())) {
                sameSuiteCount++;
            }
        }
        return sameSuiteCount == MAX_CARD_IN_HAND;
    }

    /**
     * This method gets the type of a poker_hand
     * @return the type of a poker_hand (0: High Card, 1: One Pair, 2: Two Pair, 3: Flush)
     */
    private int getType() {
        if (checkFlush()) {
            return FLUSH;
        } else if (countPair() == 2) {
            return TWO_PAIR;
        } else if (countPair() == 1) {
            return ONE_PAIR;
        }
        return HIGH_CARD;

    }

    /**
     * This method compares types of hands and return 1 if first hand win, -1 if first hand
     * loses and 0 if there is a tie.
     * @param other the second poker hand
     * @return 1 if first hand win, -1 if first hand
     * loses and 0 if there is a tie.
     */
    private int compareTypes(PokerHand other) {
        int firstHandScore = getType();
        int secondHandScore = other.getType();
        if (firstHandScore > secondHandScore) {
            return 1;
        } else if (firstHandScore < secondHandScore) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * This method get the Array that contains the ranks of the pairs in the poker_hand
     * @return an arrays that contains the ranks of the pair
     */
    private Integer[] getPairRank() {
        ArrayList<Integer[]> pairDict = new ArrayList<>();
        Integer[] pairList = {0,0};
        int indexCount = 0;
        ArrayList<Integer> handRankList = new ArrayList<Integer>();
        for (int index = 0; index < MAX_CARD_IN_HAND; index++) {
            handRankList.add(getIthCard(index).getRank());
        }
        Collections.sort(handRankList);

        while (indexCount < handRankList.size() - 1) {
            if (handRankList.get(indexCount).equals(handRankList.get(indexCount + 1))) {
                pairDict.add(new Integer[]{handRankList.get(indexCount), 2});
                indexCount += 2;
            } else {
                indexCount++;
            }
        }
        for (Integer[] rank : pairDict) {
            if (rank[1].equals(2)) {
                if (pairList[0] == 0) {
                    pairList[0] = rank[0];
                } else {
                    pairList[1] = rank[0];
                }
            }
        }
        return pairList;
    }

    /**
     * This method compares the rank of the pairs between two hands
     * @param other other poker_hand
     * @return @return 1 if first hand win, -1 if first hand
     * loses and 0 if there is a tie.
     */
    private int comparePairRanks(PokerHand other) {
        Integer[] firstHandRank = getPairRank();
        Integer[] secondHandRank = other.getPairRank();
        Arrays.sort(firstHandRank, Collections.reverseOrder());
        Arrays.sort(secondHandRank, Collections.reverseOrder());
        int compare = Arrays.compare(firstHandRank, secondHandRank);
        if (compare == 1) {
            return 1;
        } else if (compare == -1) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * This method compares which hand has higher ranking cards
     * @param other other poker_hand
     * @return 1 if first hand win, -1 if first hand
     * loses and 0 if there is a tie.
     */
    private int compareHighCards(PokerHand other) {
        Integer[] firstHandRank = {0, 0, 0, 0, 0};
        Integer[] secondHandRank = {0, 0, 0, 0, 0};
        for (int i = 0; i < MAX_CARD_IN_HAND; i++) {
            firstHandRank[i] = getIthCard(i).getRank();
        }
        for (int i = 0; i < MAX_CARD_IN_HAND; i++) {
            secondHandRank[i] = other.getIthCard(i).getRank();
        }
        Arrays.sort(firstHandRank, Collections.reverseOrder());
        Arrays.sort(secondHandRank, Collections.reverseOrder());
        int compare = Arrays.compare(firstHandRank, secondHandRank);

        if (compare == 1 || compare == -1) {
            return compare;
        }
        return 0;

    }

    /**
     * this method compares two poker_hands
     * @param other other poker_hand
     * @return 1 if first hand win, -1 if first hand
     * loses and 0 if there is a tie.
     */
    public int compareTo(PokerHand other){
        int compareTypes = compareTypes(other);
        int comparePair = comparePairRanks(other);
        int compareHighCard = compareHighCards(other);
        if (compareTypes == 1 || compareTypes == -1){
            return compareTypes;
        }else{
            if (comparePair == 1 || comparePair == -1){
                return comparePair;
            }else{
                return compareHighCard;
            }
        }
    }

    /**
     * This method creates a visual representation of a poker_hand
     * @return the visual representation of a poker_hand
     */
    public String toString(){
        return " " + this.pokerHand;
    }

}

