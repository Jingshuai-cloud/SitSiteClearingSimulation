public class Caculator {
    Map map;
    int uncleardQuantity = 0;
    int uncleardCost = 0;
    int fuelUsage = 0;
    int protectedTree = 0;
    int protectedTreeCost = 0;
    int paintDamage = 0;
    int paintDamageCost = 0;
    int total = 0;

    public Caculator(Map map){
        this.map = map;
    }

    public String caculateUnclearedQuantity(){
        for(int i=0; i< map.map.size(); i++){
            for(int j=0; j<map.map.get(i).size(); j++){
               if(map.map.get(i).get(j) != 'C' && map.map.get(i).get(j) != 'T'){
                   uncleardQuantity++;
               }
            }
        }
        String count = String.valueOf(uncleardQuantity);
        return count;
    }

    public String caculateUnclearCost(){
        return String.valueOf(uncleardQuantity * 3);
    }

    public String caculateFuelUsage(int x, int y){
        char land = this.map.getLand(x,y);
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

        return String.valueOf(fuelUsage);

    }

    public void caculateProtectedTree(){
        protectedTree++;
        protectedTreeCost = protectedTree * 10;
    }

    public void caculatePaintDamage(){
        paintDamage++;
        paintDamageCost = paintDamage * 2;
    }

    public void caculateTotalCost(int commu){
        total = commu + fuelUsage + uncleardCost + protectedTreeCost + paintDamageCost;
    }

}
