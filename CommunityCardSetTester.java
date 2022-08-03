package proj4;

import java.util.ArrayList;
import java.util.List;

public class CommunityCardSetTester {
    public static void main(String [] args){
        Testing suite = new Testing();
        Testing.setVerbose(true);
        Testing.startTests();

        testAddCard(suite);
        testGetIthCard(suite);
        Testing.finishTests();
    }

    private static void testAddCard(Testing suite){
        ArrayList cardList = new ArrayList<>(List.of(
                new Card(4,2),new Card(2,1),
                new Card(10,2), new Card(8, 3), new Card(1,1)
        ));
        CommunityCardSet cc = new CommunityCardSet(cardList);
        cc.addCard(new Card(3,1));
        suite.assertEquals("should be equal",6,cc.size());
    }

    private static void testGetIthCard(Testing suite){
        ArrayList cardList = new ArrayList<>(List.of(
                new Card(4,2),new Card(2,1),
                new Card(10,2), new Card(8, 3), new Card(1,1)
        ));
        CommunityCardSet cc = new CommunityCardSet(cardList);
        String expected = "2 of Spade";
        String actual = cc.getIthCard(1).toString();
        suite.assertEquals("should be equal", expected,actual);
    }
}
