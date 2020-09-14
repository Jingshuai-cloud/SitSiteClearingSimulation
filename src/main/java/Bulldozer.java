import java.util.HashMap;

public class Bulldozer {
    private Map map;
    private Caculator caculator;
    HashMap<String,String> position = new HashMap<>();

    public Bulldozer(){
        position.put("X", "-1");
        position.put("Y", "0");
        position.put("facing","EAST");
    }

    public void setMap(Map map){
        this.map = map;
    }

    public void setCaculator(Caculator caculator){
        this.caculator = caculator;
    }

    public boolean advance(int step){
        HashMap<String, Integer[]> nextBlock = new HashMap<>();
        nextBlock.put("NORTH", new Integer[]{0, -1});
        nextBlock.put("SOUTH", new Integer[]{0, 1});
        nextBlock.put("WEST", new Integer[]{-1, 0});
        nextBlock.put("EAST", new Integer[]{1, 0});

        String facing = this.position.get("facing");
        Integer[] nextPosition = nextBlock.get(facing);
        int newX = 0, newY = 0;
        for(int i =0; i< step; i++){
            newX = Integer.parseInt(this.position.get("X")) + nextPosition[0];
            newY = Integer.parseInt(this.position.get("Y")) + nextPosition[1];

            if(this.map.isProtectTree(newX,newY)){
                this.caculator.caculateProtectedTree();
                return false;
            }

            if(this.map.isPositionValid(newX,newY)){
                //this.map.clearMap(newX,newY,facing);
                if(this.map.getLand(newX, newY) == 't' && i != step){
                    caculator.caculatePaintDamage();
                }
                this.position.replace("X", String.valueOf(newX));
                this.position.replace("Y",String.valueOf(newY));
                this.caculator.caculateFuelUsage(newX,newY);
                this.map.clearMap(newX,newY);
                System.out.println(this.position);
            }else
                return false;

        }

        return true;

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


}


