import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;



public class TestPlay {

    @Test
    public void testPlayGame() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream("q".getBytes());
        System.setIn(in);
        Play.main(null);
    }
}

