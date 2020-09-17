import java.io.FileNotFoundException;
import java.util.Scanner;

public class Play {

    public static void main(String[] args) throws FileNotFoundException {
        //Initialize all the class
        Bulldozer bulldozer = new Bulldozer();
        Site site = new Site("src/Site.txt");
        Trainee trainee = new Trainee();
        Caculator caculator = new Caculator(site, trainee, bulldozer);
        Game game = new Game();

        //do some preperation
        bulldozer.setSite(site);
        site.readSite();
        site.printSite();

        //play game
        game.startGame();
        game.playGame(trainee,bulldozer, site);
        game.exitGame(trainee, caculator);

    }

}
