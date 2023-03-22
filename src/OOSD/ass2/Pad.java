package OOSD.ass2;

import bagel.Image;

/** Is a kind of object in the game */
public class Pad extends Actor {
    public static final String IDENTIFIER= "Pad";

    /** A pad is created in the particular position */
    public Pad(Point position){
        setType(Pad.IDENTIFIER);
        setPosition(new Point(position));
        setImage(new Image("res/images/pad.png"));
    }

    /** the pad just appears in the grid */
    @Override
    public void update(Tick tick) {
    }
}
