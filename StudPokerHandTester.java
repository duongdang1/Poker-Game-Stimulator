package proj4;

import java.util.ArrayList;

public class StudPokerHandTester {
    public static void main(String [] args){
        Testing suite = new Testing();
        Testing.setVerbose(true);
        Testing.startTests();
        testCompareStud(suite);
        testCompareTwoPairs(suite);
        testTieCommunityCard(suite);
        testTieWithHighCard(suite);
        testCompareFlushToPair(suite);
        testCompareFlushToTwoPair(suite);
        testCompareFlushToHighCard(suite);
        testCompareOnePairTwoPair(suite);
        testCompareOnePairHighCard(suite);
        testGetIthCard(suite);
        testAddCard(suite);


        Testing.finishTests();
    }

    private static String convertSuite(String suite_card){
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
    private static CommunityCardSet createCommunityCardSet(String[][] cardList) {
        ArrayList pokerCardList = new ArrayList();
        for (String[] cardString : cardList) {

            String rankCard = cardString[0];
            String suiteCard = cardString[1];
            String suite = convertSuite(suiteCard);
            Card card = new Card(rankCard, suite);
            pokerCardList.add(card);
        }
        return new CommunityCardSet(pokerCardList);
    }

    private static StudPokerHand createStudHand(String[][] cardList, CommunityCardSet cc){
        ArrayList pokerCardList = new ArrayList();
        for (String[] cardString : cardList) {

            String rankCard = cardString[0];
            String suiteCard = cardString[1];
            String suite = convertSuite(suiteCard);
            Card card = new Card(rankCard, suite);
            pokerCardList.add(card);
        }
        return new StudPokerHand(cc, pokerCardList);
    }
    private static Card getCard(String[] card){
        Card aCard = new Card(card[0],card[1]);
        return aCard;
    }

    private static void testCompareStud(Testing suite){
        CommunityCardSet communityCardSet = createCommunityCardSet(new String[][] {{"four","S"},{"jack","S"},{"king","D"},{"eight","C"},{"four","D"}});
        StudPokerHand firstStud = createStudHand(new String [][] {{"ten","D"},{"jack","H"}}, communityCardSet);
        StudPokerHand secondStud = createStudHand(new String [][] {{"eight","H"},{"5","C"}}, communityCardSet);
        suite.assertEquals("first stud should win with higher pair", 1, firstStud.compareTo(secondStud));

    }

    private static void testCompareFlushToPair(Testing suite){
        CommunityCardSet communityCardSet = createCommunityCardSet(new String[][] {{"four","S"},{"jack","S"},{"king","D"},{"eight","S"},{"four","D"}});
        StudPokerHand firstStud = createStudHand(new String [][] {{"ten","S"},{"five","S"}}, communityCardSet);
        StudPokerHand secondStud = createStudHand(new String [][] {{"seven","H"},{"seven","C"}}, communityCardSet);
        suite.assertEquals("first stud should win with flush", 1, firstStud.compareTo(secondStud));
    }

    private static void testCompareFlushToTwoPair(Testing suite){
        CommunityCardSet communityCardSet = createCommunityCardSet(new String[][] {{"four","S"},{"jack","S"},{"king","D"},{"eight","S"},{"four","D"}});
        StudPokerHand firstStud = createStudHand(new String [][] {{"ten","S"},{"five","S"}}, communityCardSet);
        StudPokerHand secondStud = createStudHand(new String [][] {{"four","H"},{"jack","C"}}, communityCardSet);
        suite.assertEquals("first stud should win with flush", 1, firstStud.compareTo(secondStud));
    }

    private static void testCompareFlushToHighCard(Testing suite){
        CommunityCardSet communityCardSet = createCommunityCardSet(new String[][] {{"four","S"},{"jack","S"},{"king","D"},{"eight","S"},{"four","D"}});
        StudPokerHand firstStud = createStudHand(new String [][] {{"ten","S"},{"five","S"}}, communityCardSet);
        StudPokerHand secondStud = createStudHand(new String [][] {{"ace","H"},{"ten","C"}}, communityCardSet);
        suite.assertEquals("first stud should win with flush", 1, firstStud.compareTo(secondStud));
    }

    private static void testCompareOnePairTwoPair(Testing suite){
        CommunityCardSet communityCardSet = createCommunityCardSet(new String[][] {{"four","S"},{"jack","S"},{"king","D"},{"eight","S"},{"three","D"}});
        StudPokerHand firstStud = createStudHand(new String [][] {{"jack","H"},{"four","S"}}, communityCardSet);
        StudPokerHand secondStud = createStudHand(new String [][] {{"eight","H"},{"ten","C"}}, communityCardSet);
        suite.assertEquals("first stud should win with two pair", 1, firstStud.compareTo(secondStud));
    }

    private static void testCompareOnePairHighCard(Testing suite){
        CommunityCardSet communityCardSet = createCommunityCardSet(new String[][] {{"four","S"},{"jack","S"},{"king","D"},{"eight","S"},{"three","D"}});
        StudPokerHand firstStud = createStudHand(new String [][] {{"ace","H"},{"five","S"}}, communityCardSet);
        StudPokerHand secondStud = createStudHand(new String [][] {{"eight","H"},{"ten","C"}}, communityCardSet);
        suite.assertEquals("second stud should win with one pair", -1, firstStud.compareTo(secondStud));
    }

    private static void testCompareTwoPairs(Testing suite){
        CommunityCardSet communityCardSet = createCommunityCardSet(new String[][] {{"king","S"},{"jack","S"},{"king","D"},{"eight","C"},{"four","D"}});
        StudPokerHand firstStud = createStudHand(new String[][] {{"ten","D"},{"ten","H"}}, communityCardSet);
        StudPokerHand secondStud = createStudHand(new String[][] {{"king","C"},{"king","H"}}, communityCardSet);
        suite.assertEquals("first stud should win with higher pair", -1, firstStud.compareTo(secondStud));
    }

    private static void testTieCommunityCard(Testing suite){
        CommunityCardSet communityCardSet = createCommunityCardSet(new String[][] {{"king","S"},{"jack","S"},{"king","D"},{"eight","C"},{"six","D"}});
        StudPokerHand firstStud = createStudHand(new String[][] {{"two","D"},{"three","H"}}, communityCardSet);
        StudPokerHand secondStud = createStudHand(new String[][] {{"four","C"},{"five","S"}}, communityCardSet);
        suite.assertEquals("best community", 0, firstStud.compareTo(secondStud));
    }

    private static void testTieWithHighCard(Testing suite){
        CommunityCardSet communityCardSet = createCommunityCardSet(new String[][] {{"king","S"},{"jack","S"},{"king","D"},{"eight","C"},{"four","D"}});
        StudPokerHand firstStud = createStudHand(new String[][] {{"Ace","D"},{"three","H"}}, communityCardSet);
        StudPokerHand secondStud = createStudHand(new String[][] {{"Ace","D"},{"three","H"}}, communityCardSet);
        suite.assertEquals("high card community", 0, firstStud.compareTo(secondStud));
    }

    private static void testAddCard(Testing suite){
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(new Card("ten","D"));
        cardList.add(new Card("jack","H"));

    }
    private static void testGetIthCard(Testing suite){
        CommunityCardSet communityCardSet = createCommunityCardSet(new String[][] {{"four","S"},{"jack","S"},{"king","D"},{"eight","C"},{"four","D"}});
        StudPokerHand Stud = createStudHand(new String [][] {{"ten","D"},{"jack","H"}}, communityCardSet);
        suite.assertEquals("should return ten of diamond", new Card("ten","Diamond"), Stud.getIthCard(0));
    }
}
