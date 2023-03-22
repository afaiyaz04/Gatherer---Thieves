package OOSD.ass2;

import java.util.ArrayList;

/** The abstract class with extended capabilities of characters
 * The characters which can move around the grid
 * Carry fruits from one point to another
 * The active attribute determines if the character is on the move
 * The carrying attribute determines if the character is carrying the fruit
 * The standingOn attribute determines what other characters is in the same position with the player
 */
public abstract class Player extends Actor{
    private int direction;
    private boolean carrying;
    private boolean active;
    private ArrayList<Actor> standingOn;

    /** Method to get the direction of the character on move */
    public int getDir() {
        return direction;
    }

    /** Method to set th direction of the character on move */
    public void setDir(int direction) {
        this.direction = direction;
    }

    /** Method to check if the character is carrying a fruit or not */
    public boolean isCarrying() {
        return carrying;
    }

    /** Method to set if the character is carrying a fruit or not */
    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }

    /** Method to check if the character is moving or not */
    public boolean isActive() {
        return active;
    }

    /** Method to set if the character is on the move or not */
    public void setActive(boolean active) {
        this.active = active;
    }

    /** Method to set the current points characters */
    public void setStandingOn(ArrayList<Actor> standingOn) {
        this.standingOn = standingOn;
    }

    /** Method to interact with the characters in the same tile or point
     * The characters which moves have similar interaction with other particular characters
     * The players interact according to the type of the character
     */
    public void interact(Actor object){
        if (object.getType().equals(Fence.IDENTIFIER)) {
            this.direction = Direction.rotation180(this.direction);
            move(this.direction, World.TILE_SIZE);
            standingOn = World.charMap.get(World.tileNumCalculator(this.getPosition()));
            this.active = false;
        }

        /* When the players steps on a direction tile, it starts moving at that particular direction */
        if (object.getType().equals(Sign.UP)) {
            Sign sign = (Sign) object;
            this.direction = sign.direction;
        } else if (object.getType().equals(Sign.DOWN)) {
            Sign sign = (Sign) object;
            this.direction = sign.direction;
        } else if (object.getType().equals(Sign.RIGHT)) {
            Sign sign = (Sign) object;
            this.direction = sign.direction;
        } else if (object.getType().equals(Sign.LEFT)) {
            Sign sign = (Sign) object;
            this.direction = sign.direction;
        }

        /* When the player is standing on a pool
           Two new player is created and that one is destroyed
         */
        if (object.getType().equals(MitosisPool.IDENTIFIER)) {
            Point point = this.getPosition();
            int dir = this.direction;
            Player first;
            Player second;
            /* The direction of the new players are clockwise and anticlockwise of the particular one*/
            if(this.getType().equals(Gatherer.IDENTIFIER)){
                first = new Gatherer(new Point(point),
                        Direction.rotation90(dir, Direction.CLOCKWISE));
                second = new Gatherer(new Point(point),
                        Direction.rotation90(dir, Direction.ANTICLOCKWISE));
            }
            else{
                first = new Thief(new Point(point),
                        Direction.rotation90(dir, Direction.CLOCKWISE));
                second = new Thief(new Point(point),
                        Direction.rotation90(dir, Direction.ANTICLOCKWISE));
            }
            World.characters.add(first);
            World.characters.add(second);

            this.destroy();
        }
    }

    /**The players moves at their particular direction if they are active
     * Then check what other players are standing at that particular tile
     * Then interact with them
     * It overrides the whole method of the actor class
     */
    @Override
    public void update(Tick tick) {
        if (tick.tickComplete) {
            if (active) {
                move(this.direction, World.TILE_SIZE);
                standingOn = World.charMap.get(World.tileNumCalculator(this.getPosition()));
            }

            if (standingOn != null) {
                for (Actor things : standingOn) {
                    interact(things);
                }
            }
        }
    }

    /** The player moves at its direction for a particular amount of distance */
    public void move(int direction, int distance) {
        // When the player is not active it doesnt move
        if(!this.active){
            return;
        }
        // if the direction is a valid one the player moves and the map is updated accordingly
        if(direction>=0 && direction<4){
            ArrayList<Actor> players = World.charMap.get(World.tileNumCalculator(this.getPosition()));
            for(int i=0; i<players.size();i++){
                if(players.contains(this)){
                    players.remove(this);
                }
            }
            if(players==null){
                World.charMap.remove(World.tileNumCalculator(this.getPosition()));
            }
            switch (direction) {
                case Direction.LEFT:
                    this.getPosition().setX((this.getPosition().getX()) - (distance));
                    break;
                case Direction.RIGHT:
                    this.getPosition().setX((this.getPosition().getX()) + (distance));
                    break;
                case Direction.UP:
                    this.getPosition().setY(this.getPosition().getY() - (distance));
                    break;
                case Direction.DOWN:
                    this.getPosition().setY(this.getPosition().getY() + (distance));
                    break;
            }
            World.addToMap(this.getPosition(),this);
            this.standingOn =World.charMap.get(World.tileNumCalculator(this.getPosition()));
        }
        else{
            System.out.println("Wrong direction entered!");
        }

    }

    /** In this game destroy means the player
     * Stops moving and not active anymore
     * Is out of the game screen
     */
    public void destroy(){
        int offScreenRow = 17;
        this.move(this.direction,World.TILE_SIZE*offScreenRow);
        this.active = false;
    }
}
