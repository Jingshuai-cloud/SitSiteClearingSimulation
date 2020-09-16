import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class TestMap {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testReadMapCorrectly() throws FileNotFoundException {
        Map map = new Map("src/TestMap.txt");
        map.readMap();
        System.out.println(map.getMap());
        ArrayList<ArrayList<Character>> testMap = new ArrayList<ArrayList<Character>>();
        ArrayList<Character> list1 = new ArrayList<>();
        list1.add('o'); list1.add('t'); list1.add('T');
        ArrayList<Character> list2 = new ArrayList<>();
        list2.add('o'); list2.add('o'); list2.add('T');
        ArrayList<Character> list3 = new ArrayList<>();
        list3.add('r'); list3.add('r'); list3.add('r');
        testMap.add(list1);testMap.add(list2);testMap.add(list3);
        Assertions.assertEquals(map.getMap(), testMap);
    }

    @Test
    public void testPrintMapCorrectly() throws FileNotFoundException {
        Map map = new Map("src/TestMap.txt");
        map.readMap();
        map.printMap();
        Assertions.assertEquals("o t T \no o T \nr r r",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void testGetLandMaterialCorrectly() throws FileNotFoundException {
        Map map = new Map("src/TestMap.txt");
        map.readMap();
        Assertions.assertEquals('o',map.getLand(0,0));
        Assertions.assertEquals('T',map.getLand(2,0));
        Assertions.assertEquals('r',map.getLand(2,2));
        Assertions.assertEquals('T',map.getLand(2,1));
    }

    @Test
    public void testClearLandCorrectly() throws FileNotFoundException {
        Map map = new Map("src/TestMap.txt");
        map.readMap();
        map.clearMap(0,0);
        Assertions.assertEquals('C',map.getLand(0,0));
    }

    @Test
    public void testGetPositionStatusCorrectly() throws FileNotFoundException {
        Map map = new Map("src/TestMap.txt");
        map.readMap();
        Assertions.assertEquals("OUT_OF_BORDER",map.getPositionStatus(3,0));
        Assertions.assertEquals("PROTECTED_TREE",map.getPositionStatus(2,1));
        Assertions.assertEquals("VALID",map.getPositionStatus(1,1));
    }
}
