package OOSD.ass2;

import bagel.Image;

/** Is a character or object in the grid where the players rest */
public class Fence extends Actor {
    public static final String IDENTIFIER= "Fence";

    /** A fence is created at the particular position */
    public Fence(Point position){
        setType(Fence.IDENTIFIER);
        setPosition(new Point(position));
        setImage(new Image("res/images/fence.png"));
    }

    /** the pool just appears in the grid */
    @Override
    public void update(Tick tick) {
    }
}