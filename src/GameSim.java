//Edith Molda

public class GameSim {

    private CircularDoublyLinkedList<Player> players;
    private Deck cards;
    private Card topCard;
    private int deckCounter;
    private Card declareWild;
    private boolean won;


    public GameSim () {
        playGame();
    }

    //set up default players using circulardoublylinkedlist
    public void createPlayers() {
        players = new CircularDoublyLinkedList<Player>();
        players.addFirst(new Player("Woody"));
        players.addFirst(new Player("Buzz"));
        players.addFirst(new Player("Rex"));
        players.addFirst(new Player("Hamm"));
    }

    //create a new deck of cards and shuffle it
    public void createDeck() {
        cards = new Deck();
        deckCounter = 108;
        cards.shuffle();
    }

    //deal cards to each player
    public void dealHand() {
        //default starting hand size
        int startCards = 7;
        //till 7 cards are handed out
        for (int i = 0; i < startCards; i++) {
            //for each player
            for (int j = 0; j < players.size(); j++) {
                //give a card from the top of the deck to each player to make hand
                players.first().addCard(cards.deal());
                //reduce count of cards in deck
                deckCounter--;
                //then rotate to the next player
                players.rotate();
            }
        }
        //return back to starting position
        players.rotate();
    }

    //check if the deck has enough cards
    public void checkDeck() {
        if (deckCounter <= 1)
            createDeck();
    }

    public int matchingCard() {
        int index = 10000;
        //going through every card in hand
        for (int i=0; i<players.first().getCards().size(); i++) {
            //if colour or face value matches current top card in discard pile
            if (players.first().getCards().get(i).getColour().getColourName().equals(topCard.getColour().getColourName()) ||
                    players.first().getCards().get(i).getFace().getFaceSymbol().equals(topCard.getFace().getFaceSymbol())) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int checkWild() {
        int index = 10000;
        //check for wild cards
        for (int j=0; j<players.first().getCards().size(); j++) {
            if (players.first().getCards().get(j).getColour().getColourName().equals("Wild")) {
                index = j;
                break;
            }
        }
        return index;
    }

    public void playerTurn() {
        int check1 = matchingCard();
        int check2 = checkWild();
        //check if have matching card to play on top card
        if (check1 != 10000) {
            //remove the selected card and put in discard pile
            topCard = players.first().getCards().remove(check1);
            System.out.println(players.first().getPlayerName() + " plays " + topCard);
        }
        else if (check2 != 10000) {
            //remove the selected card and put in discard pile
            topCard = players.first().getCards().remove(check2);
            System.out.println(players.first().getPlayerName() + " plays " + topCard);
        }
        else
            pickUp();
    }

    public void pickUp() {
        //check if there are cards to pick up from deck
        checkDeck();
        //draw a card
        Card newCard = cards.deal();
        players.first().addCard(newCard);
        //reduce count of deck
        deckCounter--;
        System.out.println(players.first().getPlayerName() + " has no play, draws "
                + newCard.getColour().getColourName() + " " + newCard.getFace().getFaceSymbol());
        //last check through hand
        int check1 = matchingCard();
        int check2 = checkWild();
        //check if have matching card to play on top card
        if (check1 != 10000) {
            //remove the selected card and put in discard pile
            topCard = players.first().getCards().remove(check1);
            System.out.println(players.first().getPlayerName() + " plays " + topCard);
        }
        else if (check2 != 10000) {
            //remove the selected card and put in discard pile
            topCard = players.first().getCards().remove(check2);
            System.out.println(players.first().getPlayerName() + " plays " + topCard);
        }
        else
            System.out.println(players.first().getPlayerName() + " can't play it");
    }

    //simulate gameplay
    public void playGame() {
        //intro to game
        System.out.println("Let's play UNO!!!");
        //instantiate deck of cards
        createDeck();
        //set up players for game
        createPlayers();
        //deal out starting hand to each player
        dealHand();
        //lay out the top card on the discard pile
        topCard = cards.deal();
        declareWild = topCard;
        //reduce count of deck
        deckCounter--;
        System.out.println("\nFirst card:\n" + topCard + "\n");
        //card that players will pick to play
        Card pick = topCard;
        //if we have a winner
        won = false;

        //continue playing the game until a player gets rid of all of their cards
        while (!won) {
            //access the top card in the discard card and take any needed actions
            evaluate();
            //display the current player's hand
            System.out.println(players.first());
            //if there are no special effects, player finds a playable card
            playerTurn();
            //check sizing of hand to see if game is won or one card away
            callUno();
            System.out.println();
        }
    }

    public void evaluate() {
        switch (topCard.getFace().getFaceSymbol()) {
            //if top card is a skip
            case "⌀":
                System.out.println(players.first().getPlayerName() + " misses a turn\n");
                skipTurn();
                break;
            //if top card is a draw +2
            case "+2":
                System.out.println(players.first().getPlayerName() + " draws 2 cards and misses a turn\n");
                dealTwo();
                skipTurn();
                break;
            //if top card is a wild
            case "*":
                wildCard();
                System.out.println("Colour is now " + topCard.getColour().getColourName() + "\n");
                break;
            //if top card is a wild draw +4
            case "+4":
                wildCard();
                System.out.println("Colour is now " + topCard.getColour().getColourName() + "\n");
                System.out.println(players.first().getPlayerName() + " draws 4 cards and misses a turn\n");
                dealTwo();
                dealTwo();
                skipTurn();
                break;
            //if top card is a reverse
            case "⇄":
                System.out.println("Game reverses direction\n");
                reversePlay();
                break;
        }
    }

    public void skipTurn() {
        players.rotate();
    }

    public void reversePlay() {
        players.rotate();
        players.reverse();
    }

    public void dealTwo() {
        checkDeck();
        players.first().addCard(cards.deal());
        players.first().addCard(cards.deal());
        //reduce count of deck
        deckCounter--;
        deckCounter--;
    }

    public void wildCard() {
        for (int i = 0; i < players.first().getCards().size(); i++) {
            if (!players.first().getCards().get(i).getColour().getColourName().equals("Wild")) {
                declareWild.setColour(players.first().getCards().get(i).getColour());
            }
            else {
                declareWild.setColour(Card.Colour.BLUE);
            }
            declareWild.setFace(topCard.getFace());
            topCard = declareWild;
        }
    }

    public void callUno() {
        if (players.first().getCards().size() == 1) {
            System.out.println(players.first().getPlayerName() + " yells \"Uno!\"");
            players.rotate();
        }
        else if (players.first().getCards().size() == 0) {
            System.out.println(players.first().getPlayerName() + " wins!!!");
            won = true;
        }
        else
            players.rotate();
    }
}