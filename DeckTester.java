package proj4;

public class DeckTester {
    public static void main(String [] args) {
        Testing suite = new Testing();
        Testing.setVerbose(true);
        Testing.startTests();
        testGather(suite);
        testDeal(suite);
        testIsEmpty(suite);
        Testing.finishTests();
    }

    private static void testGather(Testing suite){
        Deck deck = new Deck();
        deck.deal();
        deck.gather();
        int sizeActual = deck.size();
        int expected = 52;
        suite.assertEquals("should be 52 (true)",expected,sizeActual);
    }

    private static void testDeal(Testing suite){
        Deck deck = new Deck();
        Card card = deck.deal();
        String actual = card.toString();
        String expected = "2 of Club";
        suite.assertEquals("should be 52(true",expected,actual);
    }

    private static void testIsEmpty(Testing suite){
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++){
            deck.deal();
        }
        boolean actual = deck.isEmpty();
        boolean expected = true;
        suite.assertEquals("should be true (empty)", expected, actual);
    }
}
