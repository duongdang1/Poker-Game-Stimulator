package proj4;

public class CardTester {
    public static void main(String [] args) {
        Testing suite = new Testing();
        Testing.setVerbose(true);
        Testing.startTests();
        testIntRankAndIntSuite(suite);
        testStringRankUpperCase(suite);
        testStringRankStringSuite(suite);
        testGetRank(suite);
        testGetSuite(suite);
        testEquals(suite);
        Testing.finishTests();
    }

    private static void testIntRankAndIntSuite(Testing suite){
        Card card = new Card(5,0);
        String actual = card.toString();
        String expected = "5 of Spade";
        suite.assertEquals("two representation should be the same",expected,actual);
    }

    private static void testStringRankStringSuite(Testing suite){
        Card card = new Card("five","Heart");
        String actual = card.toString();
        String expected = "5 of Heart";
        suite.assertEquals("two representation should be the same",expected,actual);
    }

    private static void testStringRankUpperCase(Testing suite){
        Card card = new Card("FIVE", "Club");
        String actual = card.toString();
        String expected = "5 of Club";
        suite.assertEquals("two representation should be the same",expected, actual);
    }

    private static void testGetRank(Testing suite){
        Card card = new Card(5,0);
        int actual = card.getRank();
        int expected = 5;
        suite.assertEquals("should be 5",expected,actual);
    }

    private static void testEquals(Testing suite){
        Card card = new Card(5,0);
        Card cardTwo = new Card(5,1);
        boolean expected = true;
        suite.assertEquals("should be true",expected,card.equals(cardTwo));
    }
    private static void testGetSuite(Testing suite){
        Card card = new Card(5,0);
        String actual = card.getSuite();
        String expected = "Spade";
        suite.assertEquals("should be spade",expected,actual);
    }
}
