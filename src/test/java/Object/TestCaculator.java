package Object;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import Object.Bulldozer;
import Object.Site;
import Object.Trainee;
import Object.Caculator;

public class TestCaculator {
    private Site testSite = new Site("src/TestSite.txt");
    private Trainee testTrainee = new Trainee();
    private Bulldozer testBulldozer = new Bulldozer();
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testCaculateCommandCost(){
        Caculator caculator = new Caculator(testSite,testTrainee,testBulldozer);
        ArrayList<String> testCommands = new ArrayList<String>();
        testCommands.add("advance 2");
        testCommands.add("left");
        testCommands.add("right");
        testCommands.add("Wrong command");
        testCommands.add("quit");
        testTrainee.setCommands(testCommands);
        Assertions.assertEquals(3, caculator.caculateCommandCost());
    }

    @Test
    public void testCaculateUnclearedQuantity() throws FileNotFoundException {
        testSite.readSite();
        Caculator caculator = new Caculator(testSite, testTrainee, testBulldozer);
        Assertions.assertEquals(7, caculator.caculateUnclearedQuantity());
    }

    @Test
    public void testCaculateUnclearedCost() throws FileNotFoundException {
        testSite.readSite();
        Caculator caculator = new Caculator(testSite,testTrainee,testBulldozer);
        caculator.caculateUnclearedQuantity();
        Assertions.assertEquals(21, caculator.caculateUnclearCost());

    }

    @Test
    public void testCaculateUFuelUsage(){
        ArrayList<Character> historyPath = new ArrayList<>();
        historyPath.add('o'); historyPath.add('t');
        historyPath.add('C'); historyPath.add('r');
        testBulldozer.setHistoryPath(historyPath);
        Caculator caculator = new Caculator(testSite,testTrainee,testBulldozer);
        Assertions.assertEquals(6, caculator.caculateFuelUsage());
    }

    @Test
    public void testCaculateProtectedTree(){
        testBulldozer.setProtectedTree(1);
        Caculator caculator = new Caculator(testSite,testTrainee,testBulldozer);
        Assertions.assertEquals(1, caculator.caculateProtectedTree());
    }

    @Test
    public void testCaculateProtectedTreeCost(){
        testBulldozer.setProtectedTree(1);
        Caculator caculator = new Caculator(testSite,testTrainee,testBulldozer);
        caculator.caculateProtectedTree();
        Assertions.assertEquals(10, caculator.caculateProtectedTreeCost());
    }

    @Test
    public void testCaculatePaintDamage(){
        testBulldozer.setPaintDamage(2);
        Caculator caculator = new Caculator(testSite,testTrainee,testBulldozer);
        Assertions.assertEquals(2, caculator.caculatePaintDamage());
    }

    @Test
    public void testCaculatePaintDamageCost(){
        testBulldozer.setPaintDamage(2);
        Caculator caculator = new Caculator(testSite,testTrainee,testBulldozer);
        caculator.caculatePaintDamage();
        Assertions.assertEquals(4, caculator.caculatePaintDamageCost());
    }

    @Test
    public void testTotalDamgeCost() throws FileNotFoundException {
        testSite.readSite();
        ArrayList<Character> historyPath = new ArrayList<>();
        historyPath.add('o'); historyPath.add('t');
        historyPath.add('C'); historyPath.add('r');
        testBulldozer.setHistoryPath(historyPath);
        testBulldozer.setPaintDamage(2);
        testBulldozer.setProtectedTree(1);
        Caculator caculator = new Caculator(testSite,testTrainee,testBulldozer);
        caculator.caculatePaintDamage();
        caculator.doAllCaculations();
        Assertions.assertEquals(41, caculator.caculateTotalCost());
    }

    @Test
    public void testPrintTable(){
        Caculator caculator = new Caculator(testSite,testTrainee,testBulldozer);
        caculator.printCaculationResult();
        Assertions.assertEquals(
                "Item                                 Quantity           Cost\n" +
                        "communication overhead                      0              0\n" +
                        "fuel usage                                  0              0\n" +
                        "uncleared squares                           0              0\n" +
                        "destruction of protected tree               0              0\n" +
                        "paint damage to bulldozer                   0              0\n" +
                        "--------                                                    \n" +
                        "Total                                                      0",
                outputStreamCaptor.toString().trim());

    }
}

