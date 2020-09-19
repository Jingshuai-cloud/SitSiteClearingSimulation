package Object;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Site {

    private String pathName;
    private ArrayList<ArrayList<Character>> site = new ArrayList<ArrayList<Character>>();

    public Site(String pathName){
        this.pathName = pathName;
    }

    public void readSite () throws FileNotFoundException {
        File siteFile = new File(pathName);
        Scanner scanner = new Scanner(siteFile);

        //Store the site in a ArrayLisit[ootooooooo,oooooooToo...]
        ArrayList<String> siteArray = new ArrayList<String>();
        while(scanner.hasNextLine()){
            siteArray.add(scanner.nextLine());
        }
        //change the site structure to [[o, o, t, o, o, o, o, o, o, o],[...]...]
        for(int i =0; i<siteArray.size(); i++){
            ArrayList<Character> siteItem = new ArrayList<>();
            for(int j = 0; j<siteArray.get(i).length(); j++){
                siteItem.add(siteArray.get(i).charAt(j));
            }
            site.add(siteItem);
        }
    }

    public void printSite(){
        for(int i = 0; i< this.site.size(); i++){
            for(int j = 0; j<this.site.get(i).size(); j++){
                System.out.print(this.site.get(i).get(j));
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    //get land o/r/T/t/C from index
    public char getLand(int x, int y){
        return site.get(y).get(x);
    }

    public void clearSite(int x, int y){
        this.site.get(y).set(x, 'C');
    }

    //return the position status valid/protected tree/out of border
    public String getPositionStatus(int x, int y){
        String status = "";
        char land;
        try{
             land = getLand(x,y);
        }catch(IndexOutOfBoundsException e){
            return status = "OUT_OF_BORDER";
        }
        if(land == 'T'){
            return  status = "PROTECTED_TREE";
        }
        return status = "VALID";
    }

    public ArrayList<ArrayList<Character>> getSite(){
        return site;
    }
}
