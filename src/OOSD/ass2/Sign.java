package OOSD.ass2;

import bagel.Image;

/** Is a diff type of object/character in the grid which changes the direction of the players
 * If the players is standing on it
 */
public class Sign extends Actor{
    public int direction;
    public static final String UP= "SignUp";
    public static final String DOWN= "SignDown";
    public static final String RIGHT= "SignRight";
    public static final String LEFT= "SignLeft";

    public Sign(int direction,Point position){
        switch (direction) {
            case Direction.UP:
                this.direction = Direction.UP;
                setType(Sign.UP);
                setPosition(new Point(position));
                setImage(new Image("res/images/up.png"));
                break;
            case Direction.DOWN:
                this.direction = Direction.DOWN;
                setType(Sign.DOWN);
                setPosition(new Point(position));
                setImage(new Image("res/images/down.png"));
                break;
            case Direction.LEFT:
                this.direction = Direction.LEFT;
                setType(Sign.LEFT);
                setPosition(new Point(position));
                setImage(new Image("res/images/left.png"));
                break;
            case Direction.RIGHT:
                this.direction = Direction.RIGHT;
                setType(Sign.RIGHT);
                setPosition(new Point(position));
                setImage(new Image("res/images/right.png"));
                break;
        }
    }

    /** the sign appears in thee grid pointing at its particular direction */
    @Override
    public void update(Tick tick) {
    }
}
