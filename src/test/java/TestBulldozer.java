import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.when;


public class TestBulldozer {


    Bulldozer bulldozer = new Bulldozer();
    Map mockMap = Mockito.mock(Map.class);

    @BeforeEach
    public void setUp(){
        bulldozer.setMap(mockMap);
    }

    @Test
    public void testAnvanceValid(){
        when(mockMap.getPositionStatus(0,0)).thenReturn("VALID");
        Assertions.assertEquals(true, bulldozer.advance(1));
    }

    @Test
    public void testAnvanceProtestTree(){
        when(mockMap.getPositionStatus(0,0)).thenReturn("VALID");
        when(mockMap.getPositionStatus(1,0)).thenReturn("VALID");
        when(mockMap.getPositionStatus(2,0)).thenReturn("PROTECTED_TREE");
        Assertions.assertEquals(false, bulldozer.advance(3));
    }

    @Test
    public void testAnvanceOutOfBorder(){
        when(mockMap.getPositionStatus(0,0)).thenReturn("OUT_OF_BORDER");
        Assertions.assertEquals(false, bulldozer.advance(1));
    }

    @Test
    public void testSetMapCorrectly(){
        Bulldozer testBulldozer = new Bulldozer();
        Map testMap = new Map("src/TestMap.js");
        testBulldozer.setMap(testMap);
        Assertions.assertEquals(testBulldozer.getMap(), testMap);
    }

    @Test
    public void getNextPositionCorrectly(){
        Integer[] nextWest = new Integer[]{-1,0};
        Integer[] nextEast = new Integer[]{1,0};
        Integer[] nextSouth = new Integer[]{0,1};
        Integer[] nextNorth = new Integer[]{0,-1};
        Assertions.assertArrayEquals(nextEast, bulldozer.getNextPosition());
        bulldozer.turn("R");
        Assertions.assertArrayEquals(nextSouth, bulldozer.getNextPosition());
        bulldozer.turn("R");
        Assertions.assertArrayEquals(nextWest, bulldozer.getNextPosition());
        bulldozer.turn("R");
        Assertions.assertArrayEquals(nextNorth, bulldozer.getNextPosition());
    }


    @Test
    public void goToNextStepCorrectly(){
        when(mockMap.getLand(1,0)).thenReturn('t');
        bulldozer.nextStep(1,0,1,3);
        Assertions.assertEquals(1, bulldozer.getPaintDamage());
        ArrayList<Character> testHistoryPath = new ArrayList();
        testHistoryPath.add('t');
        Assertions.assertEquals(testHistoryPath, bulldozer.getHistoryPath());
        String x = "1";
        String y = "0";
        Assertions.assertEquals(x, bulldozer.getPosition().get("X"));
        Assertions.assertEquals(y, bulldozer.getPosition().get("Y"));
    }

    @Test
    public void testTurnCorrectly(){
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
    public void testGetMapCorrectly(){
        Assertions.assertEquals(mockMap,bulldozer.getMap());
    }

    @Test
    public void testGetProtectTreeCorrectly(){
        Assertions.assertEquals(0, bulldozer.getProtectedTree());
    }

    @Test
    public void testGetPaintDamageCorrectly(){
        Assertions.assertEquals(0, bulldozer.getPaintDamage());
    }

    @Test
    public void testGetHistoryPathCorrectly(){
        ArrayList<String> testHistoryPath = new ArrayList<>();
        Assertions.assertEquals(testHistoryPath, bulldozer.getHistoryPath());
    }

    @Test
    public void testGetPositionCorrectly(){
        HashMap<String,String> testPosition = new HashMap<>();
        testPosition.put("X", "-1");
        testPosition.put("Y", "0");
        testPosition.put("facing","EAST");
        Assertions.assertEquals(testPosition, bulldozer.getPosition());
    }



}
