import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class TestTrainee {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testPrintAllCommand(){
    Trainee trainee = new Trainee();
    ArrayList<String> testCommands = new ArrayList<>();
    testCommands.add("advance 2");
    testCommands.add("turn left");
    testCommands.add("turn right");
    testCommands.add("quit");
    trainee.setCommands(testCommands);
    trainee.printAllCommand();
    Assertions.assertEquals("These are the commands you issued:\nadvance 2, turn left, turn right, quit,",
            outputStreamCaptor.toString().trim());
    }

    @Test
    public void testTransferCommand(){
      Trainee trainee = new Trainee();
      trainee.transferCommand("2");
        ArrayList<String> testCommand = new ArrayList<>();
        testCommand.add("advance 2");
        Assertions.assertEquals(trainee.getCommands(), testCommand);

        trainee.transferCommand("l");
        testCommand.add("turn left");
        Assertions.assertEquals(trainee.getCommands(), testCommand);

        trainee.transferCommand("r");
        testCommand.add("turn right");
        Assertions.assertEquals(trainee.getCommands(), testCommand);

        trainee.transferCommand("q");
        testCommand.add("quit");
        Assertions.assertEquals(trainee.getCommands(), testCommand);

        trainee.transferCommand("w");
        testCommand.add("Wrong command");
        Assertions.assertEquals(trainee.getCommands(), testCommand);
    }

    @Test
    public void testIsPositiveNumber(){
        Trainee trainee = new Trainee();
        Assertions.assertEquals(true, trainee.isPositiveNumber("1"));
        Assertions.assertEquals(false, trainee.isPositiveNumber("-1"));
        Assertions.assertEquals(false, trainee.isPositiveNumber("s"));
    }

    @Test
    public void setAndGetCommandsCorrectly(){
        Trainee trainee = new Trainee();
        ArrayList<String> testCommands = new ArrayList<>();
        trainee.setCommands(testCommands);
        Assertions.assertEquals(testCommands, trainee.getCommands());
    }

}
