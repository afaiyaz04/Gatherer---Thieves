package OOSD.ass2;
import bagel.Image;

/** The abstract class with the basic attributes of a character
 * An image of a character
 * Position of the character in the game grid
 * The type of character
 */
public abstract class Actor {
    private Image image;
    private Point position;
    private String type;


    /** This method prints out the character in the game grid
     */
    public void render(){
        this.image.drawFromTopLeft(this.position.getX(),this.position.getY());
    }

    /** Each character is an observer of the tick
     *  Each character responses differently to the tick
     */
    public abstract void update(Tick tick);

    /** Method to initiate the image of each character*/
    public void setImage(Image image) {
        this.image = image;
    }

    /** Method to get the position of the character*/
    public Point getPosition() {
        return position;
    }

    /** Method to set the position of a character to a particular point */
    public void setPosition(Point position) {
        this.position = position;
    }

    /** Method to get the type of the character */
    public String getType() {
        return type;
    }

    /** Method to initiate the type of the character */
    public void setType(String type) {
        this.type = type;
    }
}
