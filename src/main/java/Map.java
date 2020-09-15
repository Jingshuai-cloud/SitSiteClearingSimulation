import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {

    String pathName;
    ArrayList<ArrayList<Character>> map = new ArrayList<ArrayList<Character>>();

    public Map(String pathName){
        this.pathName = pathName;
    }

    public void readMap() throws FileNotFoundException {
        File mapFile = new File(pathName);
        Scanner scanner = new Scanner(mapFile);
        //Store the map in a ArrayLisit[ootooooooo,oooooooToo...]
        ArrayList<String> mapArray = new ArrayList<String>();
        while(scanner.hasNextLine()){
            mapArray.add(scanner.nextLine());
        }
        //change the map structure to [[o, o, t, o, o, o, o, o, o, o],[...]...]
        for(int i =0; i<mapArray.size(); i++){
            ArrayList<Character> mapItem = new ArrayList<>();
            for(int j = 0; j<mapArray.get(i).length(); j++){
                mapItem.add(mapArray.get(i).charAt(j));
            }
            map.add(mapItem);
        }
    }

    public void printMap(){
        for(int i = 0; i< this.map.size(); i++){
            for(int j = 0; j<this.map.get(i).size(); j++){
                System.out.print(this.map.get(i).get(j));
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    //get land o/r/T/t/C from index
    public char getLand(int x, int y){
        return this.map.get(y).get(x);
    }

    public void clearMap(int x, int y){
        this.map.get(y).set(x, 'C');
    }

    //return the position status valid/protected tree/out of border
    public String getPositionStatus(int x, int y){
        String status = "";
        char land;
        try{
             land = map.get(y).get(x);
        }catch(IndexOutOfBoundsException e){
            return status = "OUT_OF_BORDER";
        }
        if(land == 'T'){
            return  status = "PROTECTED_TREE";
        }
        return status = "VALID";
    }
}
