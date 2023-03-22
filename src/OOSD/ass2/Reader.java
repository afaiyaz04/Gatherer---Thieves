package OOSD.ass2;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/** Class to read the file and create objects from the input file
 * According to the specification of the world and the character*/
public class Reader {
    /* The variable saves the file's name with the file path */
    private final String fileNameAddress;

    public Reader(String fileNameAddress) {
        this.fileNameAddress=fileNameAddress;
    }

    /** The method reads the world file and creates the objects found
     * Then added to World's character list and map
     * For a specific world there are predetermined characters
     * specified in an array [playerType]
     * @param characters
     */
    public void worldReader(ArrayList<Actor> characters){
        try (Scanner inputStream = new Scanner(new FileReader(this.fileNameAddress))) {
            int count = 0;
            String[] playerType = {FruitPile.TREE, FruitPile.GOLDTREE, FruitPile.STOCKPILE, FruitPile.HOARDS, Pad.IDENTIFIER,
                    Fence.IDENTIFIER, Sign.UP, Sign.DOWN, Sign.LEFT, Sign.RIGHT, MitosisPool.IDENTIFIER, Gatherer.IDENTIFIER, Thief.IDENTIFIER};
            while (inputStream.hasNextLine()) {
                count++;
                String data = inputStream.nextLine();
                String[] tokens = data.split(",");
                if (tokens.length != 3 || Integer.parseInt(tokens[1]) < 0 || Integer.parseInt(tokens[2]) < 0
                        || !Arrays.asList(playerType).contains(tokens[0])) {
                    System.out.printf("error: in file \"%s\" at line %d\n", this.fileNameAddress, count);
                    System.exit(-1);
                }
                // Separates the co-ordinate details of the object found with it's type
                String type = tokens[0];
                int x = Integer.parseInt(tokens[1]);
                int y = Integer.parseInt(tokens[2]);
                Point point = new Point(x, y);
                switch (type) {
                    case FruitPile.TREE:
                        Actor tree = new FruitPile(FruitPile.TREE, point);
                        characters.add(tree);
                        World.addToMap(point, tree);
                        break;
                    case FruitPile.GOLDTREE:
                        Actor goldTree = new FruitPile(FruitPile.GOLDTREE, point);
                        characters.add(goldTree);
                        World.addToMap(point, goldTree);
                        break;
                    case FruitPile.STOCKPILE:
                        Actor stockpile = new FruitPile(FruitPile.STOCKPILE, point);
                        characters.add(stockpile);
                        World.addToMap(point, stockpile);
                        break;
                    case FruitPile.HOARDS:
                        Actor hoard = new FruitPile(FruitPile.HOARDS, point);
                        characters.add(hoard);
                        World.addToMap(point, hoard);
                        break;
                    case Pad.IDENTIFIER:
                        Actor pad = new Pad(point);
                        characters.add(pad);
                        World.addToMap(point, pad);
                        break;
                    case Fence.IDENTIFIER:
                        Actor fence = new Fence(point);
                        characters.add(fence);
                        World.addToMap(point, fence);
                        break;
                    case Sign.UP:
                        Actor signUp = new Sign(Direction.UP, point);
                        characters.add(signUp);
                        World.addToMap(point, signUp);
                        break;
                    case Sign.DOWN:
                        Actor signDown = new Sign(Direction.DOWN, point);
                        characters.add(signDown);
                        World.addToMap(point, signDown);
                        break;
                    case Sign.LEFT:
                        Actor signLeft = new Sign(Direction.LEFT, point);
                        characters.add(signLeft);
                        World.addToMap(point, signLeft);
                        break;
                    case Sign.RIGHT:
                        Actor signRight = new Sign(Direction.RIGHT, point);
                        characters.add(signRight);
                        World.addToMap(point, signRight);
                        break;
                    case MitosisPool.IDENTIFIER:
                        Actor pool = new MitosisPool(point);
                        characters.add(pool);
                        World.addToMap(point, pool);
                        break;
                    case Gatherer.IDENTIFIER:
                        Actor gatherer = new Gatherer(point);
                        characters.add(gatherer);
                        World.addToMap(point, gatherer);
                        break;
                    case Thief.IDENTIFIER:
                        Actor thief = new Thief(point);
                        characters.add(thief);
                        World.addToMap(point, thief);
                        break;
                }
            }
        } catch (Exception e) {
            System.out.printf("error: file \"%s\" not found\n",this.fileNameAddress);
            System.exit(-1);
        }
    }
}
