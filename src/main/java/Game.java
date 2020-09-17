import java.util.Scanner;

public class Game {

    public void startGame(){
        System.out.println("Welcome to the Aconex site clearing simulator. Above is a map of the site");

        System.out.println("The bulldozer is currently located at the Northern edge of the\n" +
                "site, immediately to the West of the site, and facing East.");
    }


    public void playGame(Trainee trainee, Bulldozer bulldozer, Site site){

        Boolean playGame = true;
        while(playGame){
            System.out.println("(l)eft, (r)ight, positive number for advance, (q)uit");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            //record trainee input
            trainee.transferCommand(input);
            //if the input is number
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
            site.printSite();
        }

    }

    public void exitGame(Trainee trainee, Caculator caculator){
        trainee.printAllCommand();
        caculator.doAllCaculations();
        caculator.printTable();
    }
}
