package ca.sheridancollege.SYST17796_ProjectStarterCode;

import ca.sheridancollege.SYST17796_ProjectStarterCode.Card;

/**
 * This class models Go-Fish card
 * @Author Jinling Cai April 17, 2021
 */
public class GoFishCard extends Card {    

    public enum Suit {
        HEARTS, CLUBS, SPADES, DIAMONDS
    }

    public enum Value {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN,
        EIGHT, NINE, TEN, JACK, QUEEN, KING
    }
    
    private Value value;
    private Suit suit;
    
    /**
     * 
     * @param value
     * @param suit
     */
    public GoFishCard(Value value, Suit suit) {
        super();
        this.value = value;
        this.suit = suit;
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }
    
    @Override
    public String toString() {
       return "Suit:" + getSuit() + ", Value:"+ getValue(); //To change body of generated methods, choose Tools | Templates.
    }



}