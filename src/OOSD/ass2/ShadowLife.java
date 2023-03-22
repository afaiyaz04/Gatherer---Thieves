package OOSD.ass2;
import bagel.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

/** Game created by Ahbab Faiyaz
 * Student ID. 1045377
 * Last edited 23.10.2020
 */

public class ShadowLife extends AbstractGame {
    private final World world;

    /** To create the game, a file is read
     * A world is created reading in the characters and the details
     * from a args.txt file
     */
    public ShadowLife(String[] args) {
        super(1024,768,"ShadowLife");
        this.world = new World(Integer.parseInt(args[0]),Integer.parseInt(args[1]), args[2],
                "res/images/background.png");
    }
    /**
     * The entry point for the Game, ShadowLife
     */
    public static void main(String[] args) {
        String[] arguments = argsFromFile();
        if (arguments.length != 3 || Integer.parseInt(arguments[0])<0 || Integer.parseInt(arguments[1])<0) {
            System.out.printf("usage: ShadowLife %s %s %s\n",arguments[0],arguments[1],arguments[2]);
            System.exit(-1);
        }
        ShadowLife game = new ShadowLife(arguments);
        game.run();
    }


    /**
     * Performs a state update for the world created
     * The movement of the objects are added here
     */
    @Override
    public void update(Input input) {
        world.update();

    }

    private static String[] argsFromFile() {
        try {
            return Files.readString(Path.of("args.txt"), Charset.defaultCharset())
                    .split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


