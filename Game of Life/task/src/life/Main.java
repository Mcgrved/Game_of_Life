package life;

import java.util.*;
import javax.swing.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        Runnable gameFrame = new Runnable() {
            @Override
            public void run() {
                new GameOfLife();
            }
        };
        SwingUtilities.invokeAndWait(gameFrame);
    }
}
