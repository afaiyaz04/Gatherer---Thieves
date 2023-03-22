package OOSD.ass2;

import bagel.Image;

/** One of the character in the game
 *  Which can move and interact with other characters
 *  Its goal is to steal fruits from the Gatherer's pile and store in their hoards
 */
public class Thief extends Player {
    public static final String IDENTIFIER = "Thief";
    private boolean consuming;

    /** Initiates the Thief at its particular point
     * Who starts moving up and is not carrying any fruit
     * Or consuming
     */
    public Thief(Point point) {
        setType(Thief.IDENTIFIER);
        setPosition(new Point(point));
        setDir(Direction.UP);
        setCarrying(false);
        setActive(true);
        this.consuming = false;
        setStandingOn(World.charMap.get(World.tileNumCalculator(this.getPosition())));
        setImage( new Image("res/images/Thief.png"));
    }

    /** Initiates the Thief at its particular point
     * Who starts moving at the particular direction and is not carrying any fruit
     * Or consuming
     */
    public Thief(Point point, int direction) {
        setType(Thief.IDENTIFIER);
        setPosition(new Point(point));
        setDir(direction);
        setCarrying(false);
        setActive(true);
        this.consuming = false;
        setStandingOn(World.charMap.get(World.tileNumCalculator(this.getPosition())));
        setImage(new Image("res/images/Thief.png"));
    }

    /** The thief's interaction have common traits with other players
     *  Other than that they interacts differently with other characters/objects
     */
    @Override
    public void interact(Actor object) {
        super.interact(object);
        if(object.getType().equals(Pad.IDENTIFIER)){
            this.consuming = true;
        }

        // If they find a tree at their position they pick one fruit if not carrying any and starts moving
        if ((object.getType().equals(FruitPile.TREE) || object.getType().equals(FruitPile.GOLDTREE)) && !isCarrying()) {
            FruitPile tree = (FruitPile) object;
            if (tree.fruitCount != 0) {
                tree.fruitCount--;
                setCarrying(true);
            }
        }

        // If they get to a hoard while consuming
        // they store it at the hoard and go looking for more
        if(object.getType().equals(FruitPile.HOARDS)){
            FruitPile hoard = (FruitPile) object;
            if(consuming){
                consuming = false;
                if(!isCarrying()){
                    if(hoard.fruitCount != 0){
                        setCarrying(true);
                        hoard.fruitCount--;
                    }
                    else{
                        setDir(Direction.rotation90(this.getDir(),Direction.CLOCKWISE));
                    }
                }
            }
            else if(isCarrying()){
                setCarrying(false);
                hoard.fruitCount++;
                setDir(Direction.rotation90(this.getDir(),Direction.CLOCKWISE));
            }
        }
        // If they find any stockpile which contains fruits
        // They steal one and move away from it
        if(object.getType().equals(FruitPile.STOCKPILE)){
            FruitPile stockpile = (FruitPile) object;
            if(!isCarrying()){
                if(stockpile.fruitCount != 0){
                    setCarrying(true);
                    this.consuming = false;
                    stockpile.fruitCount--;
                    setDir(Direction.rotation90(this.getDir(),Direction.CLOCKWISE));
                }
            }
            else{
                setDir(Direction.rotation90(this.getDir(),Direction.CLOCKWISE));
            }
        }
        // If they face a Gatherer when stealing
        // they move away from them
        if(object.getType().equals(Gatherer.IDENTIFIER)){
            /* 270 degree clockwise is the same as 90 ANTI-CLOCKWISE */
            setDir(Direction.rotation90(this.getDir(),Direction.ANTICLOCKWISE));
        }
    }

}