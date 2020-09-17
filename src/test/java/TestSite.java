import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class TestSite {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testReadSiteCorrectly() throws FileNotFoundException {
        Site site = new Site("src/TestSite.txt");
        site.readSite();
        System.out.println(site.getSite());
        ArrayList<ArrayList<Character>> testSite = new ArrayList<ArrayList<Character>>();
        ArrayList<Character> list1 = new ArrayList<>();
        list1.add('o'); list1.add('t'); list1.add('T');
        ArrayList<Character> list2 = new ArrayList<>();
        list2.add('o'); list2.add('o'); list2.add('T');
        ArrayList<Character> list3 = new ArrayList<>();
        list3.add('r'); list3.add('r'); list3.add('r');
        testSite.add(list1);testSite.add(list2);testSite.add(list3);
        Assertions.assertEquals(site.getSite(), testSite);
    }

    @Test
    public void testPrintSiteCorrectly() throws FileNotFoundException {
        Site site = new Site("src/TestSite.txt");
        site.readSite();
        site.printSite();
        Assertions.assertEquals("o t T \no o T \nr r r",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void testGetLandMaterialCorrectly() throws FileNotFoundException {
        Site site = new Site("src/TestSite.txt");
        site.readSite();
        Assertions.assertEquals('o', site.getLand(0,0));
        Assertions.assertEquals('T', site.getLand(2,0));
        Assertions.assertEquals('r', site.getLand(2,2));
        Assertions.assertEquals('T', site.getLand(2,1));
    }

    @Test
    public void testClearLandCorrectly() throws FileNotFoundException {
        Site site = new Site("src/TestSite.txt");
        site.readSite();
        site.clearSite(0,0);
        Assertions.assertEquals('C', site.getLand(0,0));
    }

    @Test
    public void testGetPositionStatusCorrectly() throws FileNotFoundException {
        Site site = new Site("src/TestSite.txt");
        site.readSite();
        Assertions.assertEquals("OUT_OF_BORDER", site.getPositionStatus(3,0));
        Assertions.assertEquals("PROTECTED_TREE", site.getPositionStatus(2,1));
        Assertions.assertEquals("VALID", site.getPositionStatus(1,1));
    }
}
