/**
 * The Card class contains 2 enum types (Colour and Face) which
 * are used to create an Uno card and then print out its card value.
 *
 * @author Edith Molda
 */

public class Card {
    /**
     * This enum contains all the colours used in an Uno deck
     * @return colourName This is the value to be displayed for each colour entity
     */
    public enum Colour {
        BLUE("Blue"), RED("Red"), GREEN("Green"), YELLOW("Yellow"), BLACK("Wild");

        private String colourName;

        public String getColourName() {
            return colourName;
        }

        private Colour(String colourName) {
            this.colourName = colourName;
        }
    }

    /**
     * This enum contains all the faces used in an Uno deck
     * @return faceSymbol This is the value to be displayed for each face entity
     */
    public enum Face {
        ZERO("0"), ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), DRAW2("+2"), REVERSE("⇄"), SKIP("⌀"), WILD("*"), DRAW4("+4");

        private String faceSymbol;

        public String getFaceSymbol() {
            return faceSymbol;
        }

        Face(String faceSymbol) {
           this.faceSymbol = faceSymbol;
        }
    }


    private Colour colour;
    private Face face;

    /**
     * Constructs a new uno card instance.
     * @param c the colour value of the card (eg. "BLUE")
     * @param f the number or symbol value of the face portion of the card (eg. "ONE")
     */
    public Card (Colour c, Face f) {
        colour = c;
        face = f;
    }

    public Colour getColour() {
        return colour;
    }
    public Face getFace() {
        return face;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }
    public void setFace(Face face) {
        this.face = face;
    }

    public String toString() {
        return colour.getColourName() + " " + face.getFaceSymbol();
    }
}
