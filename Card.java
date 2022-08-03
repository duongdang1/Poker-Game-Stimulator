/**
 * This module create an object Card that has getter methods to get the rank and the suite of a card.
 */
package proj4; // do not erase. Gradescope expects this.



public class Card {
    private final static String DIAMOND = "Diamonds";
    private final static String HEART = "Hearts";
    private final static String CLUB = "Clubs";
    private final static String SPADE = "Spades";

    private final static String[] SUITES = {SPADE,HEART,CLUB,DIAMOND};
    private final static String[] RANKS_STRING= {"two","three","four","five","six","seven","eight","nine","ten","jack","queen","king","ace"};
    private final static String[] RANKS_INT = {"2","3","4","5","6","7","8","9","10","11","12","13","14"};
    private final static int RANK_SIZE = 13;
    private final static int DEFAULT_RANK = 2;
    private final static String DEFAULT_SUITE = "Spades";
    private final String suite;
    private final int rank;

    /**
     * This class create an object Card that has a rank and suite attributes
     * @param rank rank of the object card
     * @param suite suite of the object suite
     */
    public Card(int rank, int suite){
        this.rank = rank;
        this.suite = convertIntToString(suite);

    }

    public Card(String rank, String suite){
        this.rank = convertStringToInt(rank);
        this.suite = suite;
    }

    /**
     * This method convert string into integer
     * @param stringToConvert the string that must be converted into integer
     * @return integer
     */
    private int convertStringToInt(String stringToConvert){
        String rankString = stringToConvert.toLowerCase();
        int numOfRanks = RANK_SIZE;
        int rank = 0;
        for (int index = 0; index < 13; index++){
            int rankInt = index + 2;
            if (rankString.equals(RANKS_STRING[index]) || rankString.equals(RANKS_INT[index])){
                rank = rankInt;
            }
        }
        return rank;
    }

    /**
     * This method convert int into string
     * @param intToConvert the integer that must be converted
     * @return the string
     */
    private String convertIntToString(int intToConvert){
        if (intToConvert == 0){
            return SPADE;
        }else if(intToConvert == 1) {
            return HEART;
        }else if(intToConvert == 2) {
            return CLUB;
        }else if(intToConvert == 3) {
            return DIAMOND;
        }

        return DEFAULT_SUITE;
    }

    /**
     * This method return the suite of the card
     * @return return the suite of the card
     */
    public String getSuite(){
        return suite;
    }

    /**
     * This method return the rank of the card
     * @return the rank of the card
     */
    public int getRank(){
        return rank;
    }

    public boolean equals(Card other){
        if (other.getRank() == getRank()){
            return true;
        }
        return false;
    }
    /**
     * return the visual representation of a card
     * @return the visual representation of a card
     */
    public String toString(){
        int rank = getRank();
        String rankOfCard = "";
        if (rank == 11){
            rankOfCard = "Jack";
        }else if (rank == 12){
            rankOfCard = "Queen";
        }else if (rank == 13){
            rankOfCard = "King";
        }else if (rank == 14){
            rankOfCard = "Ace";
        }else{
            rankOfCard = String.valueOf(rank);
        }
        return rankOfCard + " of " + getSuite();
    }


}
