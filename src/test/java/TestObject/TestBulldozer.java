package TestObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import Object.Bulldozer;
import Object.Site;


public class TestBulldozer {

    @Test
    public void testAnvanceValid() throws FileNotFoundException {
        Bulldozer bulldozer = new Bulldozer();
        Site testSite = new Site("src/TestSite.txt");
        testSite.readSite();
        bulldozer.setSite(testSite);
        Assertions.assertEquals(true, bulldozer.advance(1));
    }

    @Test
    public void testAnvanceProtestTree(){
        Bulldozer bulldozer = new Bulldozer();
        Site testSite = new Site("src/TestSite.txt");
        bulldozer.setSite(testSite);
        Assertions.assertEquals(false, bulldozer.advance(3));
    }

    @Test
    public void testAnvanceOutOfBorder() throws FileNotFoundException {
        Bulldozer bulldozer = new Bulldozer();
        Site testSite = new Site("src/TestSite.txt");
        bulldozer.setSite(testSite);
        testSite.readSite();
        Assertions.assertEquals(false, bulldozer.advance(4));
    }

    @Test
    public void testSetSiteCorrectly(){
        Bulldozer bulldozer = new Bulldozer();
        Site testSite = new Site("src/TestSite.txt");
        bulldozer.setSite(testSite);
        Assertions.assertEquals(bulldozer.getSite(), testSite);
    }

    @Test
    public void getNextPositionCorrectly(){
        Bulldozer bulldozer = new Bulldozer();
        Site testSite = new Site("src/TestSite.txt");
        bulldozer.setSite(testSite);
        Integer[] nextWest = new Integer[]{-1,0};
        Integer[] nextEast = new Integer[]{1,0};
        Integer[] nextSouth = new Integer[]{0,1};
        Integer[] nextNorth = new Integer[]{0,-1};
        Assertions.assertArrayEquals(nextEast, bulldozer.getNextPositionCaculateIndex());
        bulldozer.turn("R");
        Assertions.assertArrayEquals(nextSouth, bulldozer.getNextPositionCaculateIndex());
        bulldozer.turn("R");
        Assertions.assertArrayEquals(nextWest, bulldozer.getNextPositionCaculateIndex());
        bulldozer.turn("R");
        Assertions.assertArrayEquals(nextNorth, bulldozer.getNextPositionCaculateIndex());
    }


    @Test
    public void goToNextStepCorrectly() throws FileNotFoundException {
        Bulldozer bulldozer = new Bulldozer();
        Site testSite = new Site("src/TestSite.txt");
        bulldozer.setSite(testSite);
        testSite.readSite();
        bulldozer.moveToNextStep(0,0,0,3);

        Assertions.assertEquals(0, bulldozer.getPaintDamage());
        bulldozer.moveToNextStep(1,0,1,3);
        Assertions.assertEquals(1, bulldozer.getPaintDamage());

        ArrayList<Character> testHistoryPath = new ArrayList<>();
        testHistoryPath.add('o'); testHistoryPath.add('t');
        Assertions.assertEquals(testHistoryPath, bulldozer.getHistoryPath());

        String x = "1", y = "0";
        Assertions.assertEquals(x, bulldozer.getPosition().get("X"));
        Assertions.assertEquals(y, bulldozer.getPosition().get("Y"));
    }

    @Test
    public void testTurnCorrectly(){
        Bulldozer bulldozer = new Bulldozer();
        Site mockSite = new Site("src/TestSite.txt");
        bulldozer.setSite(mockSite);
        bulldozer.turn("L");
        Assertions.assertEquals("NORTH", bulldozer.getPosition().get("facing"));
        bulldozer.turn("L");
        Assertions.assertEquals("WEST", bulldozer.getPosition().get("facing"));
        bulldozer.turn("L");
        Assertions.assertEquals("SOUTH", bulldozer.getPosition().get("facing"));
        bulldozer.turn("L");
        Assertions.assertEquals("EAST", bulldozer.getPosition().get("facing"));
        bulldozer.turn("R");
        Assertions.assertEquals("SOUTH", bulldozer.getPosition().get("facing"));
        bulldozer.turn("R");
        Assertions.assertEquals("WEST", bulldozer.getPosition().get("facing"));
        bulldozer.turn("R");
        Assertions.assertEquals("NORTH", bulldozer.getPosition().get("facing"));
    }

    @Test
    public void testGetSiteCorrectly(){
        Bulldozer bulldozer = new Bulldozer();
        Site mockSite = new Site("src/TestSite.txt");
        bulldozer.setSite(mockSite);
        Assertions.assertEquals(mockSite,bulldozer.getSite());
    }

    @Test
    public void testGetProtectTreeCorrectly(){
        Bulldozer bulldozer = new Bulldozer();
        Site mockSite = new Site("src/TestSite.txt");
        bulldozer.setSite(mockSite);
        Assertions.assertEquals(0, bulldozer.getProtectedTree());
    }

    @Test
    public void testGetPaintDamageCorrectly(){
        Bulldozer bulldozer = new Bulldozer();
        Site mockSite = new Site("src/TestSite.txt");
        bulldozer.setSite(mockSite);
        Assertions.assertEquals(0, bulldozer.getPaintDamage());
    }

    @Test
    public void testGetHistoryPathCorrectly(){
        Bulldozer bulldozer = new Bulldozer();
        Site mockSite = new Site("src/TestSite.txt");
        bulldozer.setSite(mockSite);
        ArrayList<String> testHistoryPath = new ArrayList<>();
        Assertions.assertEquals(testHistoryPath, bulldozer.getHistoryPath());
    }

    @Test
    public void testGetPositionCorrectly(){
        Bulldozer bulldozer = new Bulldozer();
        Site mockSite = new Site("src/TestSite.txt");
        bulldozer.setSite(mockSite);
        HashMap<String,String> testPosition = new HashMap<>();
        testPosition.put("X", "-1");
        testPosition.put("Y", "0");
        testPosition.put("facing","EAST");
        Assertions.assertEquals(testPosition, bulldozer.getPosition());
    }



}
