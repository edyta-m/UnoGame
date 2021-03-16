//Edith Molda

import java.util.ArrayList;

public class Player {

    private String playerName;
    private ArrayList<Card> hand;

    public Player (String playerName) {
        this.playerName = playerName;
        hand = new ArrayList<Card>();
    }

    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<Card> getCards() {
        return hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(" + playerName + "'s hand: ");
        for (int i=0; i<hand.size(); i++) {
            if (i < hand.size()-1)
                sb.append(hand.get(i).toString() + ", ");
            else
                sb.append(hand.get(i).toString() + ")");
        }
        return sb.toString();
    }
}
