package OOSD.ass2;

import bagel.Image;

/** It is a kind of character in the game which is magical
 * It just appears in the grid and other characters interact accordingly
 */
public class MitosisPool extends Actor {
    public static final String IDENTIFIER= "Pool";

    /** A pool is created at the particular point in thee grid */
    public  MitosisPool(Point position){
        setType(MitosisPool.IDENTIFIER);
        setPosition(new Point(position));
        setImage(new Image("res/images/pool.png"));
    }

    /** th pool doesnt change it just stands there */
    @Override
    public void update(Tick tick) {
    }
}
