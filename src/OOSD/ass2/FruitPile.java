package OOSD.ass2;

import bagel.*;
import bagel.Font;

/** The Class for character types which store/pile fruits
 *  The amount of fruit in each depends on thee type of pile
 *  They all have different appearances
 *  The amount of fruit in each is displayed on the game
 */
public class FruitPile extends Actor {
    public int fruitCount;
    public static final String TREE = "Tree";
    public static final String GOLDTREE = "GoldenTree";
    public static final String STOCKPILE = "Stockpile";
    public static final String HOARDS = "Hoard";
    private final Font font;


    public FruitPile(String type, Point position){
        this.font = new Font("res/VeraMono.ttf",24);
        switch (type) {
            //Trees initially have three fruits
            case TREE:
                this.fruitCount = 3;
                setType(TREE);
                setPosition(new Point(position));
                setImage(new Image("res/images/tree.png"));
                break;
            // Golden tree has unlimited amount of fruits
            case GOLDTREE:
                this.fruitCount = Integer.MAX_VALUE;
                setType(GOLDTREE);
                setPosition(new Point(position));
                setImage(new Image("res/images/gold-tree.png"));
                break;
            // Stockpile initially have zero fruits
            case STOCKPILE:
                this.fruitCount = 0;
                setType(STOCKPILE);
                setPosition(new Point(position));
                setImage(new Image("res/images/cherries.png"));
                break;
            // Hoards initially have zero fruits
            case HOARDS:
                this.fruitCount = 0;
                setType(HOARDS);
                setPosition(new Point(position));
                setImage(new Image("res/images/hoard.png"));
                break;
        }
    }

    /** The piles are printed in the grid with their fruit count */
    @Override
    public void update(Tick tick) {
        if(!this.getType().equals(FruitPile.GOLDTREE)){
            font.drawString(Integer.toString(this.fruitCount),getPosition().getX(),getPosition().getY());
        }

    }
}
