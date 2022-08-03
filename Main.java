package proj4;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final int FIRST_HAND_WIN = 1;
    private static final int SECOND_HAND_WIN = -1;
    private static final int TIE = 0;
    private static final int MAX_CARD_IN_COMMUNITY_HAND = 5;
    private static final int MAX_CARD_IN_STUD_HAND = 2;
    private static final String WIN = "win";
    private static final String LOSE = "lose";
    private static final int MAX_CARDS_IN_ROUND = 4;
    private static CommunityCardSet communityCardSet;


    /**
     * this method return the user's choice with numbers from 0 to 2
     * @return the user's choice with numbers from 0 to 2
     */
    private static int userInput(){

        Scanner obj = new Scanner(System.in);
        String a = obj.nextLine();
        if (a.equals("a")){
            return FIRST_HAND_WIN;
        }else if (a.equals("b")) {
            return SECOND_HAND_WIN;
        }
        return TIE;
    }

    /**
     * This method return the stud poker hand
     * @param deckOfCards the deck of cards
     * @return the stud poker hand
     */
    private static ArrayList<Card> getStud(Deck deckOfCards){
        ArrayList<Card> studPokerHand = new ArrayList<>();

        for (int count = 0; count < MAX_CARD_IN_STUD_HAND;count++){

            studPokerHand.add(deckOfCards.deal());
        }
        return studPokerHand;
    }


    /**
     * This method returns the community card set
     * @param deckOfCards the deck of cards
     * @return the community card set
     */
    private static CommunityCardSet getCommunityCardSet(Deck deckOfCards){
        ArrayList<Card> pokerHand = new ArrayList<>();
        for (int count = 0; count < MAX_CARD_IN_COMMUNITY_HAND; count ++){
            pokerHand.add(deckOfCards.deal());
        }
        return new CommunityCardSet((pokerHand));
    }


    /**
     * This method plays a round of poker and determine the winner with value from 0 to 2
     * @param deckOfCards the deck of cards
     * @param cc the community card set
     * @return the winner with value from 0-2
     */
    private static String oneRound(Deck deckOfCards, CommunityCardSet cc){
        ArrayList<Card> firstHandArray = getStud(deckOfCards);
        StudPokerHand firstPokerHand = new StudPokerHand(communityCardSet, firstHandArray);
        ArrayList<Card> secondHandArray = getStud(deckOfCards);
        StudPokerHand secondPokerHand = new StudPokerHand(communityCardSet, secondHandArray);
        System.out.println("Which of the following hands is worth more");
        System.out.println("hand a:" + firstPokerHand);
        System.out.println("hand b:" + secondPokerHand);
        System.out.println("Enter a or b (or SPACE to indicate they are of equal value)");
        int compare_hands = firstPokerHand.compareTo(secondPokerHand);
        int user_input = userInput();
        if (compare_hands == user_input){
            return WIN;
        }else{
            return LOSE;
        }
    }
    public static void main(String[] args){
        Deck deckOfCards = new Deck();
        deckOfCards.shuffle();
        int deckLength = deckOfCards.size();

        int playerPoint = 0;
        boolean isPlaying = true;
        CommunityCardSet cc = getCommunityCardSet(deckOfCards);
        deckLength -= 5;
        while(isPlaying && deckLength >= MAX_CARDS_IN_ROUND){
            System.out.println(cc);

            String result = oneRound(deckOfCards, cc);

            if (result == WIN) {
                System.out.println("correct");
                playerPoint += 1;
            } else if (result == LOSE) {
                System.out.println("you lose");
                isPlaying = false;

            }

            deckLength -= MAX_CARDS_IN_ROUND;
        }

        System.out.println("game over");
        System.out.println("your point:" + playerPoint);

    }
}
