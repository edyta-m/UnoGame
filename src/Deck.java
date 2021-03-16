//Edith Molda

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        createCards();
    }

    public void createCards() {
        //for all colours
        for (Card.Colour c : Card.Colour.values()) {
            //all colours except black
            if (!c.getColourName().equals("Wild")) {
                //all face values
                for (Card.Face f : Card.Face.values()) {
                    //all face values except wild draw 4 and wild *
                    if (!(f.getFaceSymbol().equals("*") || f.getFaceSymbol().equals("+4"))) {
                        //just zero
                        if (f.getFaceSymbol().equals("0")) {
                            cards.add(new Card(c,f));
                        }
                        //all other face values
                        else {
                            cards.add(new Card(c,f));
                            cards.add(new Card(c,f));
                        }
                    }
                }
            }
            //just black (wild)
            else {
                //all face values
                for (Card.Face f : Card.Face.values()) {
                    //just wild draw 4 and wild
                    if (f.getFaceSymbol().equals("*") || f.getFaceSymbol().equals("+4")) {
                        cards.add(new Card(c,f));
                        cards.add(new Card(c,f));
                        cards.add(new Card(c,f));
                        cards.add(new Card(c,f));
                    }
                }
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        return cards.remove(0);
    }
}
