import java.util.ArrayList;

public class Caculator {
    Map map;
    Trainee trainee;
    Bulldozer bulldozer;
    int commuCost = 0;
    int uncleardQuantity = 0;
    int uncleardCost = 0;
    int fuelUsage = 0;
    int protectedTree = 0;
    int protectedTreeCost = 0;
    int paintDamage = 0;
    int paintDamageCost = 0;
    int total = 0;

    public Caculator(Map map, Trainee trainee, Bulldozer bulldozer){
        this.map = map;
        this.trainee = trainee;
        this.bulldozer = bulldozer;
    }

    public void caculateCommandCost(){
        ArrayList<String> commands = new ArrayList<String>();
        commands = trainee.commands;
        for(String command: commands){
            if(!(command.equals("Wrong command") || command.equals("quit"))){
                commuCost++;
            }
        }
    }

    public void caculateUnclearedCost(){
        for(int i=0; i< map.map.size(); i++){
            for(int j=0; j<map.map.get(i).size(); j++){
               if(map.map.get(i).get(j) != 'C' && map.map.get(i).get(j) != 'T'){
                   uncleardQuantity++;
               }
            }
        }
        uncleardCost = uncleardQuantity * 3;
    }

    public void caculateFuelUsage(){
        ArrayList<Character> historyPath = bulldozer.historyPath;
        for(char land : historyPath){
            switch(land){
                case 'o':
                    fuelUsage++;
                    break;
                case 'C':
                    fuelUsage++;
                    break;
                case 'r':
                    fuelUsage = fuelUsage + 2;
                    break;
                case 't':
                    fuelUsage = fuelUsage + 2;
                    break;
                default:
                    System.out.println("Wrong land type!");
            }

        }

    }

    public void caculateProtectedTree(){
        protectedTree = bulldozer.protectedTree;
        protectedTreeCost = protectedTree * 10;
    }

    public void caculatePaintDamage(){
        paintDamage = bulldozer.paintDamage;
        paintDamageCost = paintDamage * 2;
    }

    public void caculateTotalCost(){
        total = commuCost + fuelUsage + uncleardCost + protectedTreeCost + paintDamageCost;
    }

    public void doAllCaculations(){
        caculateCommandCost();
        caculatePaintDamage();
        caculateProtectedTree();
        caculateFuelUsage();
        caculateUnclearedCost();
        caculateTotalCost();
    }

    public void printTable(){
        //transfer all the value from int to String
        String commu = String.valueOf(commuCost),
                fuel = String.valueOf(fuelUsage),
                unclearedQ = String.valueOf(uncleardQuantity),
                unclearedC =  String.valueOf(uncleardCost),
                paintD =  String.valueOf(paintDamage),
                painC = String.valueOf(paintDamageCost),
                protectedT = String.valueOf(protectedTree),
                protectedC = String.valueOf(protectedTreeCost),
                totalCost = String.valueOf(total);

        Object[][] table = new String[8][];
        table[0] = new String[] { "Item", "Quantity", "Cost" };
        table[1] = new String[] { "communication overhead", commu, commu };
        table[2] = new String[] { "fuel usage", fuel, fuel };
        table[3] = new String[] { "uncleared squares", unclearedQ, unclearedC };
        table[4] = new String[] { "destruction of protected tree", protectedT, protectedC };
        table[5] = new String[] { "paint damage to bulldozer", paintD, painC };
        table[6] = new String[] { "--------", "", "" };
        table[7] = new String[] { "Total", "", totalCost };
        System.out.println();
        for (Object[] row : table) {
            System.out.format("\n%-30s%15s%15s", row);
        }
    }
}
