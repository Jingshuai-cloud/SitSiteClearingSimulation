public class Play {
    public static void main(String[] args) {
        System.out.println("Play the game");
        Bulldozer bulldozer = new Bulldozer();
        bulldozer.advance(3);
        bulldozer.turn("L");
    }
}
