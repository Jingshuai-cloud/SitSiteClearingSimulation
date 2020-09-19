package Object;

import java.util.ArrayList;

public class Caculator {
    private Site site;
    private Trainee trainee;
    private Bulldozer bulldozer;
    private int commuCost = 0;
    private int uncleardQuantity = 0;
    private int uncleardCost = 0;
    private int fuelUsage = 0;
    private int protectedTree = 0;
    private int protectedTreeCost = 0;
    private int paintDamage = 0;
    private int paintDamageCost = 0;
    private int total = 0;

    public Caculator(Site site, Trainee trainee, Bulldozer bulldozer){
        this.site = site;
        this.trainee = trainee;
        this.bulldozer = bulldozer;
    }

    public int caculateCommandCost(){
        ArrayList<String> commands = new ArrayList<String>();
        commands = trainee.getCommands();
        for(String command: commands){
            if(!(command.equals("Wrong command") || command.equals("quit"))){
                commuCost++;
            }
        }
        return commuCost;
    }

    //caculate how many land not been cleared in the site
    public int caculateUnclearedQuantity(){
        for(int y = 0; y< site.getSite().size(); y++){
            for(int x = 0; x< site.getSite().get(y).size(); x++){
               if(site.getSite().get(y).get(x) != 'C' && site.getSite().get(y).get(x) != 'T'){
                   uncleardQuantity++;
               }
            }
        }
        return  uncleardQuantity;
    }

    public int caculateUnclearCost(){
        return uncleardCost = uncleardQuantity * 3;
    }

    public int caculateFuelUsage(){
        ArrayList<Character> historyPath = bulldozer.getHistoryPath();
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
            }

        }
        return fuelUsage;
    }

    public int caculateProtectedTree(){
        protectedTree = bulldozer.getProtectedTree();
        return protectedTree;
    }

    public int caculateProtectedTreeCost(){
        protectedTreeCost = protectedTree * 10;
        return protectedTreeCost;
    }

    public int caculatePaintDamage(){
        paintDamage = bulldozer.getPaintDamage();
        return paintDamage;
    }

    public int caculatePaintDamageCost(){
        paintDamageCost = paintDamage * 2;
        return paintDamageCost;
    }

    public int caculateTotalCost(){
        total = commuCost + fuelUsage + uncleardCost + protectedTreeCost + paintDamageCost;
        return total;
    }

    public void doAllCaculations(){
        caculateCommandCost();
        caculatePaintDamage();
        caculatePaintDamageCost();
        caculateProtectedTree();
        caculateProtectedTreeCost();
        caculateFuelUsage();
        caculateUnclearedQuantity();
        caculateUnclearCost();
        caculateTotalCost();
    }

    public void printCaculationResult(){
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
