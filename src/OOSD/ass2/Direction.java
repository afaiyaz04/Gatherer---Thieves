package OOSD.ass2;


/** Used from the assignment 1 solution
 * Each direction is given an integer value and name for better readability
 * Which can be used to make rotations
 * Direction of rotation is stored to pass as arguments
 */
public class Direction {
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int CLOCKWISE = 1;
    public static final int ANTICLOCKWISE = -1;

    /** Takes in direction as argument
     * returns the value associated opposite to that direction
     */
    public static int rotation180(int direction){
        int newDir = direction-2;
        if(newDir == -1){
            return Direction.LEFT;
        }
        else if(newDir==-2){
            return Direction.DOWN;
        }
        else {
            return newDir;
        }
    }

    /** Takes in direction as argument
     * With another argument as the direction of rotation
     * return the direction associated to the modification
     */
    public static int rotation90(int direction,int rotation){
        int newDir = direction + rotation;
        if(newDir==-1){
            return Direction.LEFT;
        }
        else if(newDir==4){
            return Direction.UP;
        }
        else{
            return newDir;
        }
    }

}

