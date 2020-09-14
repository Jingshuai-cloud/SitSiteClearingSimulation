import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {

    String pathName;
    ArrayList<ArrayList<Character>> map = new ArrayList<ArrayList<Character>>();
    int X, Y;
    Bulldozer bulldozer;
    int fuelUsage =0;
    int paintDamage = 0;
    int paintCost = 0;
    int protectdTree = 0;
    int protectedTreeCost = 0;

    public Map(String pathName){
        this.pathName = pathName;
    }

    public void placeBulldozer(Bulldozer bulldozer){
        this.bulldozer = bulldozer;
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

    public void caculateMapSize(){
        this.X = this.map.get(0).size();
        this.Y = this.map.size();
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


    public char getLand(int x, int y){
        return this.map.get(y).get(x);
    }

    public void clearMap(int x, int y){
        this.map.get(y).set(x, 'C');
    }


    public boolean isPositionValid(int newX,int newY){
        return newX >= 0 && newX < this.X && newY >= 0 && newY < this.Y;
    }



    public void caculatePaintDamage(char land){
        if(land == 't'){
            paintDamage++;
            paintCost = paintDamage * 2;
        }
    }

    public void caculateDestructionTree(char land){
        if(land == 'T'){
            protectdTree++;
            protectedTreeCost = protectdTree * 10;
        }
    }

    public boolean isProtectTree(int x, int y){
        try{
            char land = map.get(y).get(x);
            if(land == 'T'){
                return true;
            }
        }catch(IndexOutOfBoundsException e){
        System.out.println("Bulldozer is out of map!");
        }

        return false;
    }
}
