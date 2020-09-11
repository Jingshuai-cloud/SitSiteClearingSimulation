import java.util.HashMap;

public class Bulldozer {
   HashMap<String,String> position = new HashMap<>();

    public Bulldozer(){
        position.put("X", "0");
        position.put("Y", "0");
        position.put("facing","EAST");
    }

    public void advance(int step){
        HashMap<String, Integer[]> nextBlock = new HashMap<>();
        nextBlock.put("NORTH", new Integer[]{0, -step});
        nextBlock.put("SOUTH", new Integer[]{0, step});
        nextBlock.put("WEST", new Integer[]{-step, 0});
        nextBlock.put("EAST", new Integer[]{step, 0});

        String facing = this.position.get("facing");
        Integer[] nextPosition = nextBlock.get(facing);
        int newX = Integer.parseInt(this.position.get("X")) + nextPosition[0];
        int newY = Integer.parseInt(this.position.get("X")) + nextPosition[1];

        this.position.replace("X", String.valueOf(newX));
        this.position.replace("Y",String.valueOf(newY));
        System.out.println(this.position);
    }

    public void turn (String command){
        HashMap<String, Integer> turnIndex = new HashMap<>();
        turnIndex.put("L",0);
        turnIndex.put("R",1);
        int direction = turnIndex.get(command);

        HashMap<String, String[]> nextFacing = new HashMap<>();
        nextFacing.put("NORTH", new String[]{"EAST", "WEST"});
        nextFacing.put("EAST", new String[]{"NORTH", "SOUTH"});
        nextFacing.put("SOUTH", new String[]{"WEST", "EAST"});
        nextFacing.put("WEST", new String[]{"SOUTH", "WEST"});

        String facing = this.position.get("facing");
        String newFacing = nextFacing.get(facing)[direction];

        this.position.replace("facing", newFacing);
        System.out.println(this.position);
    }


}


