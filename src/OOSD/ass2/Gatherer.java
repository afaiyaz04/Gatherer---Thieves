package OOSD.ass2;
import bagel.Image;


/** One of the character in the game
 *  Which can move and interact with other characters
 *  Its goal is to collect fruits from trees and store in their pile
 */
public class Gatherer extends Player {
    public static final String IDENTIFIER = "Gatherer";

    /** Initiates the Gatherer at its particular point
     * Who starts moving left and is not carrying any fruit
     */
    public Gatherer(Point position) {
        setType(Gatherer.IDENTIFIER);
        setPosition(new Point(position));
        setDir(Direction.LEFT);
        setCarrying(false);
        setActive(true);
        setStandingOn(World.charMap.get(World.tileNumCalculator(this.getPosition())));
        setImage(new Image("res/images/Gatherer.png"));

    }

    /** Initiates the Gatherer at its particular point
     * Who starts moving at a particular direction and is not carrying any fruit
     */
    public Gatherer(Point position, int direction) {
        setType(Gatherer.IDENTIFIER);
        setPosition(new Point(position));
        setDir(direction);
        setCarrying(false);
        setActive(true);
        setStandingOn(World.charMap.get(World.tileNumCalculator(this.getPosition())));
        setImage(new Image("res/images/Gatherer.png"));

    }

    /** The gatherer's interaction have common traits with other players
     *  Other than that they interacts differently with other characters
     */
    @Override
    public void interact(Actor object) {
        super.interact(object);
        // When the gatherer reaches any tree and it contains fruits
        // It picks the fruit and start moving towards their pile
        if ((object.getType().equals(FruitPile.TREE) || object.getType().equals(FruitPile.GOLDTREE)) && !isCarrying()) {
            FruitPile tree = (FruitPile) object;
            if (tree.fruitCount != 0) {
                tree.fruitCount--;
                setCarrying(true);
                setDir(Direction.rotation180(this.getDir()));
            }
        }
        // When a gatherer reaches a pile it stores its fruit if carrying any
        if ((object.getType().equals(FruitPile.STOCKPILE) || object.getType().equals(FruitPile.HOARDS))) {
            FruitPile pile = (FruitPile) object;
            if (isCarrying()) {
                setCarrying(false);
                pile.fruitCount++;
            }
            setDir(Direction.rotation180(this.getDir()));
        }


    }




}
