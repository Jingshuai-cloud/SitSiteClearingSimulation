package Game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import Game.Game;
import Object.Bulldozer;
import Object.Site;
import Object.Trainee;
import Object.Caculator;

public class TestGame {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testStartGame(){
        Game game = new Game();
        game.startGame();
        Assertions.assertEquals("Welcome to the Aconex site clearing simulator. Above is a map of the site\n" +
                        "The bulldozer is currently located at the Northern edge of the\n" +
                        "site, immediately to the West of the site, and facing East.",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void testPlayGame() throws FileNotFoundException {
        Trainee trainee = new Trainee();
        Bulldozer bulldozer = new Bulldozer();
        Site site = new Site("src/TestSite.txt");
        site.readSite();
        bulldozer.setSite(site);
        Game game = new Game();
        ByteArrayInputStream in = new ByteArrayInputStream("q".getBytes());
        System.setIn(in);
        game.playGame(trainee,bulldozer,site);
        Assertions.assertEquals(
                "(l)eft, (r)ight, positive number for advance, (q)uit\n" +
                        "exiting the game...\n" +
                        "{X=-1, Y=0, facing=EAST}\n" +
                        "o t T \n" +
                        "o o T \n" +
                        "r r r",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void testExitGame() throws FileNotFoundException {
        Trainee trainee = new Trainee();
        Bulldozer bulldozer = new Bulldozer();
        Site site = new Site("src/TestSite.txt");
        site.readSite();
        bulldozer.setSite(site);
        Caculator caculator = new Caculator(site,trainee,bulldozer);
        Game game = new Game();
        game.exitGame(trainee,caculator);
        Assertions.assertEquals(
                "These are the commands you issued:\n" +
                        "\n" +
                        "\n" +
                        "Item                                 Quantity           Cost\n" +
                        "communication overhead                      0              0\n" +
                        "fuel usage                                  0              0\n" +
                        "uncleared squares                           7             21\n" +
                        "destruction of protected tree               0              0\n" +
                        "paint damage to bulldozer                   0              0\n" +
                        "--------                                                    \n" +
                        "Total                                                     21",
                outputStreamCaptor.toString().trim());
    }
}
