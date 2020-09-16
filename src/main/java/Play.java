import java.io.FileNotFoundException;
import java.util.Scanner;

public class Play {

    public static void main(String[] args) throws FileNotFoundException {
        //Initialize all the class
        Bulldozer bulldozer = new Bulldozer();
        Map map = new Map("src/Map.txt");
        Trainee trainee = new Trainee();
        Caculator caculator = new Caculator(map, trainee, bulldozer);

        //do some preperation
        bulldozer.setMap(map);
        map.readMap();
        map.printMap();

        startGame();
        playGame(trainee,bulldozer,map);
        exitGame(trainee, caculator);

    }

    private static void startGame(){
        System.out.println("Welcome to the Aconex site clearing simulator. Above is a map of the site");

        System.out.println("The bulldozer is currently located at the Northern edge of the\n" +
                "site, immediately to the West of the site, and facing East.");
    }

    private static void playGame(Trainee trainee, Bulldozer bulldozer, Map map){

        Boolean playGame = true;
        while(playGame){
            System.out.println("(l)eft, (r)ight, positive number for advance, (q)uit");
            //write in trainee input
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            //record trainee input
            trainee.transferCommand(input);
            //if the input is string or number
            int step = 0;
            if(trainee.isPositiveNumber(input)){
                step = Integer.parseInt(input);
                input = "a";
            }
            switch(input){
                case "a":
                    playGame = bulldozer.advance(step);
                    break;
                case "l":
                    bulldozer.turn("L");
                    break;
                case "r":
                    bulldozer.turn("R");
                    break;
                case "q":
                    System.out.println("exiting the game...");
                    playGame = false;
                    break;
                default:
                    System.out.println("Please enter the right command");
            }
            map.printMap();
        }

    }

    private static void exitGame(Trainee trainee, Caculator caculator){
        trainee.printAllCommand();
        caculator.doAllCaculations();
        caculator.printTable();
    }




}
