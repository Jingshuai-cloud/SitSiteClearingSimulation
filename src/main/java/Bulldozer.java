import java.util.ArrayList;
import java.util.HashMap;

public class Bulldozer {
   private Map map;
   private int protectedTree;
   private int paintDamage;
   private ArrayList<Character> historyPath = new ArrayList();
   private HashMap<String,String> position = new HashMap<>();

    //initialize the bulldozer position
    public Bulldozer(){
        position.put("X", "-1");
        position.put("Y", "0");
        position.put("facing","EAST");
        protectedTree = 0;
        paintDamage = 0;
    }

    public void setMap(Map map){
        this.map = map;
    }

    public boolean advance(int step){
        //get [1,0] when facing east...then caculate the next coordinate
        Integer[] nextPosition = getNextPosition();
        int newX = 0, newY = 0;

        for(int i =0; i< step; i++){
            //recaculate every step of advance
            newX = Integer.parseInt(this.position.get("X")) + nextPosition[0];
            newY = Integer.parseInt(this.position.get("Y")) + nextPosition[1];
            //get position status from map
            String positionStatus = map.getPositionStatus(newX, newY);
            switch(positionStatus){
                case "OUT_OF_BORDER":
                    System.out.println("Bulldozer is out of the map!");
                    return false;

                case "PROTECTED_TREE":
                    protectedTree++;
                    System.out.println("Bulldozer come cross a protected tree!");
                    return false;

                case "VALID":
                    nextStep(newX, newY, i, step);
                    System.out.println(this.position);
                    break;

                default:
                    System.out.println("Position Status has something wrong!");
            }

        }
        System.out.println(historyPath);
        return true;
    }

    //define how to caculate next position according to current facing
    public Integer[] getNextPosition(){
        HashMap<String, Integer[]> nextBlock = new HashMap<>();
        nextBlock.put("NORTH", new Integer[]{0, -1});
        nextBlock.put("SOUTH", new Integer[]{0, 1});
        nextBlock.put("WEST", new Integer[]{-1, 0});
        nextBlock.put("EAST", new Integer[]{1, 0});

        String facing = this.position.get("facing");
        Integer[] nextPositionIndex = nextBlock.get(facing);
        return nextPositionIndex;
    }

    //when position is valid, change the position
    public void nextStep(int newX, int newY, int i, int step){
        char land = map.getLand(newX, newY);
        if(land == 't' && i != step - 1){
            paintDamage ++;
        }
        historyPath.add(land);
        position.replace("X", String.valueOf(newX));
        position.replace("Y",String.valueOf(newY));
        map.clearMap(newX,newY);
    }

    public void turn (String command){
        HashMap<String, Integer> turnIndex = new HashMap<>();
        turnIndex.put("L",0);
        turnIndex.put("R",1);
        int direction = turnIndex.get(command);

        HashMap<String, String[]> nextFacing = new HashMap<>();
        nextFacing.put("NORTH", new String[]{"WEST", "EAST"});
        nextFacing.put("EAST", new String[]{"NORTH", "SOUTH"});
        nextFacing.put("SOUTH", new String[]{"EAST", "WEST"});
        nextFacing.put("WEST", new String[]{"SOUTH", "NORTH"});

        String facing = this.position.get("facing");
        String newFacing = nextFacing.get(facing)[direction];

        this.position.replace("facing", newFacing);
        System.out.println(this.position);
    }

    public Map getMap(){
        return map;
    }

    public int getProtectedTree(){
        return protectedTree;
    }

    public int getPaintDamage(){
        return paintDamage;
    }

    public ArrayList<Character> getHistoryPath(){
        return historyPath;
    }

    public HashMap<String,String> getPosition(){
        return position;
    }



}


