import java.io.FileNotFoundException;
import java.util.Scanner;

public class Play {

    public static void main(String[] args) throws FileNotFoundException {
        Bulldozer bulldozer = new Bulldozer();
        Map map = new Map("src/Map.txt");
        bulldozer.setMap(map);
        map.placeBulldozer(bulldozer);
        map.readMap();
        map.caculateMapSize();
        map.printMap();
        System.out.println(map.X);
        System.out.println(map.Y);
        Caculator caculator = new Caculator(map);
        bulldozer.setCaculator(caculator);
        Trainee trainee = new Trainee();
        playGame(trainee,bulldozer,map);
        trainee.printAllCommand();
        printTable(String.valueOf(trainee.caculateCommandCost()), String.valueOf(caculator.fuelUsage), caculator.caculateUnclearedQuantity(),
                caculator.caculateUnclearCost(), String.valueOf(caculator.paintDamage), String.valueOf(caculator.pantDamageCost),
                String.valueOf(caculator.protectedTree), String.valueOf(caculator.protectedTreeCost));
    }

    public static void playGame(Trainee trainee, Bulldozer bulldozer, Map map){
        startGame();
        Boolean playGmae = true;
        while(playGmae){
            System.out.println("(l)eft, (r)ight, positive number for advance, (q)uit");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            trainee.addCommand(input);
            int step = 0;
            if(isPositiveNumber(input)){
                step = Integer.parseInt(input);
                input = "a";
            }
            switch(input){
                case "a":
                    playGmae = bulldozer.advance(step);
                    break;
                case "l":
                    bulldozer.turn("L");
                    break;
                case "r":
                    bulldozer.turn("R");
                    break;
                case "q":
                    System.out.println("exiting the game...");
                    playGmae = false;
                    break;
                default:
                    System.out.println("Please enter the right command");
            }
            map.printMap();
        }

    }

    private static boolean isPositiveNumber(String input) {
        int step = -1;
        try
        {
            step = Integer.parseInt(input);
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
        return step > 0;
    }

    private static void startGame(){
        System.out.println("Welcome to the Aconex site clearing simulator. Above is a map of the site");

        System.out.println("The bulldozer is currently located at the Northern edge of the\n" +
                "site, immediately to the West of the site, and facing East.");
    }

    private static void printTable(String commu, String fuel, String unclearedQ, String unclearedC,
                                   String paintD, String painC, String protectedTree, String protectedCost){
        Object[][] table = new String[8][];
        table[0] = new String[] { "Item", "Quantity", "Cost" };
        table[1] = new String[] { "communication overhead", commu, commu };
        table[2] = new String[] { "fuel usage", fuel, fuel };
        table[3] = new String[] { "uncleared squares", unclearedQ, unclearedC };
        table[4] = new String[] { "destruction of protected tree", protectedTree, protectedCost };
        table[5] = new String[] { "paint damage to bulldozer", paintD, painC };
        table[6] = new String[] { "--------", "", "" };
        table[7] = new String[] { "Total", "bar4", "baz4" };
        System.out.println();
        for (Object[] row : table) {
            System.out.format("\n%-30s%15s%15s", row);
        }
    }

}
