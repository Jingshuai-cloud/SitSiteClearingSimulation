package TestGame;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import Game.Play;


public class TestPlay {

    @Test
    public void testPlayGame() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream("q".getBytes());
        System.setIn(in);
        Play.main(null);
    }
}

