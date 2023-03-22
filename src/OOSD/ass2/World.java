package OOSD.ass2;

import bagel.Image;
import bagel.Window;
import java.util.ArrayList;
import java.util.HashMap;


/** Class to make a model of a world with
 *  One grid texture and particular size of tile size
 *  Which contains characters and a map of their positions
 *  The time of the world can be set to a particular unit and for how long it will run
 */
public class World {
    public static ArrayList<Actor> characters;
    public static HashMap<Integer,ArrayList<Actor>> charMap;
    public static final int TILE_SIZE = 64;
    private boolean status;
    private final Image background;
    private final Tick tick;

    /** The constructor initiates the world with
     * A particular unit of time which is considered as a second in that world
     * Reads from a world file which contains the characters detail
     * Creates a map of all the characters
     */
    public World( int tickValue, int maxTick, String worldFile, String backgroundFile){
        this.background = new Image(backgroundFile);
        this.tick = new Tick(tickValue, maxTick);
        characters = new ArrayList<>();
        charMap = new HashMap<>();
        this.status =true;
        Reader inputFile = new Reader(worldFile);
        inputFile.worldReader(characters);

    }

    /** Method to determine the particular tile no in the grid associated to a point */
    public static Integer tileNumCalculator(Point point){
        int x = point.getX()/64;
        int y = point.getY()/64;
        return x+(16*y);
    }

    /** Method to insert a character in the grid map tile associated to the point */
    public static void addToMap(Point point, Actor character) {
        Integer tileNum = World.tileNumCalculator(point);
        ArrayList<Actor> charList = charMap.get(tileNum);

        // if list does not exist create it
        if(charList == null) {
            charList = new ArrayList<>();
            charList.add(character);
            charMap.put(tileNum, charList);
        } else {
            // add if Character is not already in list
            if(!charList.contains(character)) {
                charList.add(character);
            }
        }
    }

    /** Method which updates the state of every character in the world
     * checks and updates the state of the world
     * The game runs until the max tick is reached
     */
    public void update() {
        background.draw(Window.getWidth() / 2.0, Window.getHeight() / 2.0);
        tick.run();
        if(Tick.count <= tick.maxTick){
            for (int i = 0; i < World.characters.size(); i++) {
                World.characters.get(i).update(this.tick);
                World.characters.get(i).render();
            }
            status = statusCheck();
            if(!status){
                printOutputDetails();
                System.exit(0);
            }

        }
        else{
            System.out.println("Timed out");
            System.exit(0);

        }

    }

    private void printOutputDetails(){
        System.out.println(Tick.count);
        for (Actor character : characters) {
            switch (character.getType()) {
                case FruitPile.STOCKPILE:
                case FruitPile.HOARDS:
                    FruitPile pile = (FruitPile) character;
                    System.out.println(pile.fruitCount);
                    break;
            }
        }
    }

    private boolean statusCheck() {
        boolean status = false;
        for (Actor character : characters) {
            switch (character.getType()) {
                case Gatherer.IDENTIFIER:
                    Gatherer gatherer = (Gatherer) character;
                    if (gatherer.isActive()) {
                        status = true;
                    }
                    break;
                case Thief.IDENTIFIER:
                    Thief thief = (Thief) character;
                    if (thief.isActive()) {
                        status = true;
                    }
                    break;
            }
        }
        return status;
    }
}

