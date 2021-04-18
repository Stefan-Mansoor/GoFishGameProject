package ca.sheridancollege.SYST17796_ProjectStarterCode.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class models the processes of Go-Fish game.
 * @author Jinling Cai April 17, 2021
 */
public class GoFishRuleProcessor {

    /**
     *
     * @param numCards
     * @param cardsForGame
     * @return
     */
    public static ArrayList<GoFishCard> generateHand(int numCards, ArrayList<GoFishCard> cardsForGame) {

        // Create an array to hold the cards
        ArrayList<GoFishCard> hand = new ArrayList<GoFishCard>();
        int cardsForGameCount = cardsForGame.size();
        int cardsToPick= 0;
        if(numCards > cardsForGameCount){
            cardsToPick = cardsForGameCount;
        }else {
            cardsToPick = numCards;
        }
        if (cardsForGameCount != 0) {
            for (int index = 0; index < cardsToPick; index++) {

                GoFishCard card = cardsForGame.get(index);
                hand.add(card);
            }
        }
        return hand;
    }

    /**
     *
     * @param numCards
     * @param cardsForGame
     * @return
     */
    public static ArrayList<GoFishCard> refreshCardsForGame(int numCards, ArrayList<GoFishCard> cardsForGame) {
        int cardsForGameCount = cardsForGame.size();
        int cardsToPick= 0;
        if(numCards > cardsForGameCount){
            cardsToPick = cardsForGameCount;
        }else {
            cardsToPick = numCards;
        }
        if (cardsForGameCount != 0) {
            for (int index = 0; index < cardsToPick; index++) {
                cardsForGame.remove(0);
            }
        }
        return cardsForGame;
    }

    /**
     *
     * @param valueForCheck
     * @param cardsInHand
     * @return
     */
    public static ArrayList<GoFishCard> getCardsForPass(String valueForCheck, ArrayList<GoFishCard> cardsInHand) {
        ArrayList<GoFishCard> cardsToPass = new ArrayList<GoFishCard>();
        for (GoFishCard card : cardsInHand) {
            String cardInHandValue = card.getValue().name();
            if (valueForCheck.equals(cardInHandValue)) {
                cardsToPass.add(card);
            }
        }
        return cardsToPass;
    }

    /**
     *
     * @param cardsToPass
     * @param cardsInHand
     * @return
     */
    public static ArrayList<GoFishCard> removeCardsInHand(
            ArrayList<GoFishCard> cardsToPass,
            ArrayList<GoFishCard> cardsInHand) {
       // String valueForCheck = cardsToPass.get(0).getValue().name();
        for (GoFishCard card : cardsToPass) {           
                cardsInHand.remove(card);
            
        }
        return cardsInHand;
    }

    /**
     *
     * @param cardsToPass
     * @param cardsInHand
     * @return
     */
    public static ArrayList<GoFishCard> addCardsToHand(
            ArrayList<GoFishCard> cardsToPass,
            ArrayList<GoFishCard> cardsInHand) {

        for (GoFishCard card : cardsToPass) {
            cardsInHand.add(card);
        }

        return cardsInHand;
    }

    /**
     *
     * @param valueForCheck
     * @param cardsInHand
     * @return
     */
    public static int getScore(String valueForCheck, ArrayList<GoFishCard> cardsInHand) {

        int cardsCount = 0;
        for (GoFishCard card : cardsInHand) {
            String cardInHandValue = card.getValue().name();
            if (valueForCheck.equals(cardInHandValue)) {
                cardsCount = cardsCount + 1;
            }
        }
        int score = Math.floorDiv(cardsCount, 2);
        return score;
    }

    /**
     *
     * @param score
     * @param valueForCheck
     * @param cardsInHand
     * @return
     */
    public static ArrayList<GoFishCard> removeScoredCards(int score, String valueForCheck, ArrayList<GoFishCard> cardsInHand) {

        int cardsToRemove = score * 2;
        String cardInHandValue;
        ArrayList<GoFishCard> scordedCards = new ArrayList<GoFishCard>() ;
        for (GoFishCard card : cardsInHand) {
            cardInHandValue = card.getValue().name();
            if (cardsToRemove != 0 && valueForCheck.equals(cardInHandValue)) {
                scordedCards.add(card);
                cardsToRemove = cardsToRemove -1 ;
            }
        }
         for (GoFishCard card :scordedCards){
             cardsInHand.remove(card);
         }
        return cardsInHand;
    }
    /**
     * 
     * @param cardsInHand
     * @return 
     */
    public static  HashMap<String,Integer> getRemainingScores(ArrayList<GoFishCard> cardsInHand){
        
         HashMap<String,Integer> valueCountMap = new HashMap<String,Integer>();
         HashMap<String,Integer> scoreMap = new HashMap<String,Integer>(); 
         for(GoFishCard.Value value:GoFishCard.Value.values()){
             valueCountMap.put(value.name(), 0);
         }
         String cardInHandValue;
         Integer valueCount;
         Integer score;
         for (GoFishCard card : cardsInHand) {
             cardInHandValue = card.getValue().name();
             valueCount = valueCountMap.get(cardInHandValue) + 1;
            valueCountMap.put(cardInHandValue,valueCount);
        }
         for(String value: valueCountMap.keySet()){
             valueCount = valueCountMap.get(value);
             score = Math.floorDiv(valueCount, 2);
             if(score != 0) scoreMap.put(value,score);
         }
         return scoreMap;  
    }
    
    /**
     *
     * @return
     */
    public static ArrayList<GoFishCard> getCardsForGame() {
        ArrayList<GoFishCard> brandNewCards = GoFishRuleProcessor.initializeCards();
        GroupOfCards shuffledCards = new GroupOfCards(brandNewCards.size());
        shuffledCards.setCards(brandNewCards);
        shuffledCards.shuffle();
        ArrayList<GoFishCard> cardsForGame = shuffledCards.getCards();
        return cardsForGame;
    }
    /**
     *
     * @return
     */
    public static ArrayList<GoFishCard> initializeCards() {

        // Create an array to hold the cards
        ArrayList<GoFishCard> initialList = new ArrayList<GoFishCard>();
        Random random = new Random();

        for (GoFishCard.Suit suit : GoFishCard.Suit.values()) {
            for (GoFishCard.Value value : GoFishCard.Value.values()) {
                GoFishCard card = new GoFishCard(value, suit);
                initialList.add(card);
            }
        }
        return initialList;
    }
}
