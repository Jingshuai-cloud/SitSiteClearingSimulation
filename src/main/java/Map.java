import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    public static void main(String[] args) throws FileNotFoundException {
        File mapFile = new File("src/Map.txt");
        Scanner scanner = new Scanner(mapFile);
        ArrayList<String> map = new ArrayList<String>();

        while(scanner.hasNextLine()){
            map.add(scanner.nextLine());
        }

        System.out.println(map);
    }
}
