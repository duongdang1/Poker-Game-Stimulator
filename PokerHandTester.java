package proj4;

import java.util.ArrayList;

public class PokerHandTester {
    public static void main(String [] args){
        Testing suite = new Testing();
        Testing.setVerbose(true);
        Testing.startTests();
        testComparePairRanks(suite);
        testCompareTwoPairToOnePair(suite);
        testCompareTwoPairRanks(suite);
        testCompareTwoPairTie(suite);
        testCompareOnePairToHighCard(suite);
        testCompareOnePairRankWhenTie(suite);
        testCompareOnePairTie(suite);
        testCompareTwoPairRankWhenTie(suite);
        testCompareOnePairToFlush(suite);
        testCompareTwoPairToFlush(suite);
        testCompareFlushWithHighCard(suite);
        testCompareFlushTie(suite);
        testCompareFlushWithHighCardWin(suite);
        testCompareHighCard(suite);
        testCompareHighCardTie(suite);
        testCompareKingsTen(suite);
        Testing.finishTests();
    }
    private static String convert_suite(String suite_card){
        String suite = " ";
        if (suite_card == "C"){
            suite = "Club";
        }else if(suite_card == "D"){
            suite = "Diamond";
        }else if(suite_card == "H"){
            suite = "Heart";
        }else if(suite_card == "S"){
            suite = "Spade";
        }
        return suite;

    }
    private static PokerHand createHand(String[][] cardList){
        ArrayList pokerCardList = new ArrayList();
        for ( String[] cardString: cardList){

            String rankCard = cardString[0];
            String suiteCard = cardString[1];
            String suite = convert_suite(suiteCard);
            Card card = new Card(rankCard,suite);
            pokerCardList.add(card);
        }
        return new PokerHand(pokerCardList);
    }
    private static void testComparePairRanks(Testing suite){

        PokerHand firstHand = createHand(new String[][] {{"four","S"},{"jack","S"},{"king","D"},{"eight","C"},{"four","D"}});
        PokerHand secondHand = createHand(new String[][] {{"ten","C"},{"three","D"},{"ace","D"},{"three","H"},{"seven","D"}});
        suite.assertEquals("first Hand (pair of 4) should beat second hand (pair of 3) with higher rank pair",1,firstHand.compareTo(secondHand));
    }

    private static void testCompareKingsTen(Testing suite){
        PokerHand firstHand = createHand(new String[][] {{"king","S"},{"king","H"},{"king","D"},{"king","C"},{"king","C"}});
        PokerHand secondHand = createHand(new String[][] {{"ten","C"},{"king","D"},{"ten","D"},{"king","H"},{"seven","D"}});
        suite.assertEquals("4 of a kind should win",1,firstHand.compareTo(secondHand));
    }
    private static void testCompareTwoPairToOnePair(Testing suite){
        PokerHand firstHand = createHand(new String[][] {{"four","S"},{"jack","S"},{"king","D"},{"jack","C"},{"four","H"}});
        PokerHand secondHand = createHand(new String[][] {{"king","S"},{"two","C"},{"king","H"},{"eight","C"},{"three","D"}});
        suite.assertEquals("first hand(two pair) should beat second hand (one pair)",1,firstHand.compareTo(secondHand));
    }

    private static void testCompareTwoPairRanks(Testing suite){
        PokerHand firstHand = createHand(new String[][] {{"four","S"},{"jack","S"},{"king","D"},{"jack","C"},{"four","D"}});
        PokerHand secondHand = createHand(new String[][] {{"ace","S"},{"three","C"},{"ace","H"},{"eight","C"},{"three","D"}});
        suite.assertEquals("second hand (pair of 14 and pair of 3) should beat the first hand " +
                " with higher two pair ranks",-1,firstHand.compareTo(secondHand));
    }

    private static void testCompareTwoPairTie(Testing suite){
        PokerHand firstHand = createHand(new String[][] {{"four","S"},{"jack","S"},{"king","D"},{"jack","C"},{"four","D"}});
        PokerHand secondHand = createHand(new String[][] {{"four","C"},{"jack","D"},{"king","S"},{"jack","H"},{"four","H"}});
        suite.assertEquals("should be a tie for two same hand",0,firstHand.compareTo(secondHand));
    }

    private static void testCompareOnePairToHighCard(Testing suite){
        PokerHand firstHand = createHand(new String[][] {{"four","S"},{"ten","S"},{"king","D"},{"jack","C"},{"four","D"}});
        PokerHand secondHand = createHand(new String[][] {{"ace","S"},{"three","C"},{"six","H"},{"eight","C"},{"five","D"}});
        suite.assertEquals("first hand (one pair) should beat second hand (high card) ",1,firstHand.compareTo(secondHand));
    }

    private static void testCompareOnePairRankWhenTie(Testing suite){
        PokerHand firstHand = createHand(new String[][] {{"four","S"},{"ten","S"},{"king","D"},{"jack","C"},{"four","D"}});
        PokerHand secondHand = createHand(new String[][] {{"four","C"},{"three","C"},{"six","H"},{"four","H"},{"five","D"}});
        suite.assertEquals("first hand (one pair) should beat second hand (one pair) with high card  ",1,firstHand.compareTo(secondHand));
    }

    private static void testCompareOnePairTie(Testing suite){
        PokerHand firstHand = createHand(new String[][] {{"four","S"},{"ten","S"},{"six","D"},{"jack","C"},{"four","D"}});
        PokerHand secondHand = createHand(new String[][] {{"four","C"},{"ten","C"},{"six","H"},{"four","H"},{"jack","D"}});
        suite.assertEquals("It should be a tie (one pair)",0,firstHand.compareTo(secondHand));
    }

    private static void testCompareTwoPairRankWhenTie(Testing suite){
        PokerHand firstHand = createHand(new String[][] {{"four","S"},{"jack","S"},{"king","D"},{"jack","C"},{"four","D"}});
        PokerHand secondHand = createHand(new String[][] {{"four","H"},{"jack","D"},{"jack","H"},{"eight","C"},{"four","C"}});
        suite.assertEquals("first hand (two pair) should beat the second hand (two pair) with high card",1,firstHand.compareTo(secondHand));
    }

    private static void testCompareOnePairToFlush(Testing suite){
        PokerHand firstHand = createHand(new String[][] {{"four","S"},{"queen","S"},{"king","S"},{"nine","S"},{"five","S"}});
        PokerHand secondHand = createHand(new String[][] {{"ace","H"},{"two","D"},{"jack","H"},{"eight","C"},{"ace","C"}});
        suite.assertEquals("first hand (flush) should beat the second hand (one pair)",1,firstHand.compareTo(secondHand));
    }

    private static void testCompareTwoPairToFlush(Testing suite){
        PokerHand firstHand = createHand(new String[][] {{"four","S"},{"queen","S"},{"king","S"},{"nine","S"},{"five","S"}});
        PokerHand secondHand = createHand(new String[][] {{"ace","H"},{"two","D"},{"jack","H"},{"jack","C"},{"ace","C"}});
        suite.assertEquals("first hand (flush) should beat the second hand (two pair)",1,firstHand.compareTo(secondHand));
    }

    public static void testCompareFlushWithHighCard(Testing suite){
        PokerHand firstHand = createHand(new String[][] {{"four","S"},{"queen","S"},{"king","S"},{"nine","S"},{"five","S"}});
        PokerHand secondHand = createHand(new String[][] {{"ace","H"},{"two","D"},{"jack","H"},{"ten","C"},{"eight","C"}});
        suite.assertEquals("first hand (flush) should beat the second hand (high card)",1,firstHand.compareTo(secondHand));
    }

    public static void testCompareFlushTie(Testing suite){
        PokerHand firstHand = createHand(new String[][] {{"four","S"},{"queen","S"},{"king","S"},{"nine","S"},{"five","S"}});
        PokerHand secondHand = createHand(new String[][] {{"four","H"},{"queen","H"},{"king","H"},{"nine","H"},{"five","H"}});
        suite.assertEquals("should be a tie for tow equal flush",0,firstHand.compareTo(secondHand));
    }

    public static void testCompareFlushWithHighCardWin(Testing suite){
        PokerHand firstHand = createHand(new String[][] {{"four","S"},{"queen","S"},{"king","S"},{"nine","S"},{"five","S"}});
        PokerHand secondHand = createHand(new String[][] {{"four","H"},{"queen","H"},{"ace","H"},{"nine","H"},{"five","H"}});
        suite.assertEquals("second hand(flush) should beat first hand(flush) with high card",-1,firstHand.compareTo(secondHand));
    }

    public static void testCompareHighCard(Testing suite){
        PokerHand firstHand = createHand(new String[][] {{"four","S"},{"six","D"},{"jack","S"},{"three","D"},{"ten","S"}});
        PokerHand secondHand = createHand(new String[][] {{"ace","S"},{"five","D"},{"eight","H"},{"seven","C"},{"two","C"}});
        suite.assertEquals("second hand (high card) should beat first hand(high card) with higher card ",-1,firstHand.compareTo(secondHand));

    }

    public static void testCompareHighCardTie(Testing suite){
        PokerHand firstHand = createHand(new String[][] {{"four","S"},{"six","D"},{"jack","S"},{"three","D"},{"ten","S"}});
        PokerHand secondHand = createHand(new String[][] {{"four","D"},{"six","S"},{"jack","H"},{"three","C"},{"ten","C"}});
        suite.assertEquals("two hand should be equal with same rank",0,firstHand.compareTo(secondHand));
    }
}
