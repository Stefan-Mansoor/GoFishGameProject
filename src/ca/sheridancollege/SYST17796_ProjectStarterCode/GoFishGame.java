package ca.sheridancollege.SYST17796_ProjectStarterCode;

import ca.sheridancollege.SYST17796_ProjectStarterCode.Game;
import ca.sheridancollege.SYST17796_ProjectStarterCode.GroupOfCards;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * This class models Go-Fish Game.
 * @Author Jinling Cai April 17, 2021
 */
public class GoFishGame extends Game {

    private ArrayList<GoFishPlayer> players;
    private GoFishCard card;

    final private int initialSize = 7;
    private boolean yourTurn = true;
    private boolean machineTurn = false;
    private String yourQuestion;
    private String machineQuestion;

    private int yourScore = 0;
    private int machineScore = 0;
    private int scoreToAdd = 0;

    private ArrayList<GoFishCard> cardsInMachine;
    private ArrayList<GoFishCard> cardsInYourHand;

    private int cardsForGameCount;
    private int cardsInYourHandCount;
    private int cardsInMachineCount;
    private String cardsInHandString;

    private GroupOfCards machineHand = new GroupOfCards(initialSize);
    private GroupOfCards yourHand = new GroupOfCards(initialSize);
    private ArrayList<GoFishCard> cardsForGame;

    /**
     *
     * @param players
     */
    public GoFishGame(ArrayList<GoFishPlayer> players) {
        super("GoFish");
        this.players = players;
    }

    public ArrayList<GoFishPlayer> getGoFishPlayers() {
        return this.players;
    }

    /**
     *
     * @param GoFishPlayer
     */
    public void setGoFishPlayers(ArrayList<GoFishPlayer> players) {
        this.players = players;
    }

    /**
     *
     * @param cardsForGame
     * @return
     */
    private ArrayList<GoFishCard> distributeCards(GroupOfCards cardsInOneHand, int cardsNumToDistribute) {

        cardsInOneHand.setCards(GoFishRuleProcessor.generateHand(cardsNumToDistribute, cardsForGame));
        cardsForGame = GoFishRuleProcessor.refreshCardsForGame(cardsNumToDistribute, cardsForGame);
        ArrayList<GoFishCard> cardsInOneSide = machineHand.getCards();
        return cardsInOneSide;
    }

    /**
     *
     * @param interaction
     */
    private void processYourTurn(Scanner interaction) {
        ArrayList<GoFishCard> cardsPassToYou;
        cardsInHandString = yourHand.toString();
        System.out.println("you got cards:" + cardsInHandString);

        cardsInYourHand = yourHand.getCards();
        cardsInMachine = machineHand.getCards();
        yourQuestion = getQuestion(interaction, cardsInYourHand);
        cardsPassToYou = GoFishRuleProcessor.getCardsForPass(yourQuestion, cardsInMachine);

        if (cardsPassToYou.size() > 0) {
            cardsInYourHand = GoFishRuleProcessor.addCardsToHand(cardsPassToYou, cardsInYourHand);
            cardsInMachine = GoFishRuleProcessor.removeCardsInHand(cardsPassToYou, cardsInMachine);
            scoreToAdd = GoFishRuleProcessor.getScore(yourQuestion, cardsInYourHand);
            cardsInYourHand = GoFishRuleProcessor.removeScoredCards(scoreToAdd, yourQuestion, cardsInYourHand);

            yourHand.setSize(cardsInYourHand.size());
            yourHand.setCards(cardsInYourHand);
            yourScore = yourScore + scoreToAdd;
            System.out.println("Now your score:" + yourScore);
            /**
             * if machine does not cards in hand it need to pick 7 cards from
             * the cards for game if remaining cards less than 7 then pick all
             *
             */
            cardsInMachineCount = cardsInMachine.size();
            if (cardsInMachineCount == 0) {
                machineHand.setCards(GoFishRuleProcessor.generateHand(initialSize, cardsForGame));
                cardsForGame = GoFishRuleProcessor.refreshCardsForGame(initialSize, cardsForGame);
                cardsInMachine = machineHand.getCards();
            }

        } else {
            System.out.println("machine said : GO Fish!");

            cardsPassToYou = GoFishRuleProcessor.generateHand(1, cardsForGame);
            cardsForGame = GoFishRuleProcessor.refreshCardsForGame(1, cardsForGame);
            cardsInYourHand = GoFishRuleProcessor.addCardsToHand(cardsPassToYou, cardsInYourHand);

            yourHand.setSize(cardsInYourHand.size());
            yourHand.setCards(cardsInYourHand);

            yourTurn = false;
            machineTurn = true;
        }
    }

    /**
     *
     */
    private void processMachineTurn() {
        ArrayList<GoFishCard> cardsPassToMachine;
        cardsInHandString = machineHand.toString();
        System.out.println("machine got cards:" + cardsInHandString);

        cardsInMachine = machineHand.getCards();
        cardsInYourHand = yourHand.getCards();
        machineQuestion = getMchineQuestion(cardsInMachine);
        cardsPassToMachine = GoFishRuleProcessor.getCardsForPass(machineQuestion, cardsInYourHand);

        if (cardsPassToMachine.size() > 0) {
            cardsInMachine = GoFishRuleProcessor.addCardsToHand(cardsPassToMachine, cardsInMachine);
            cardsInYourHand = GoFishRuleProcessor.removeCardsInHand(cardsPassToMachine, cardsInYourHand);
            scoreToAdd = GoFishRuleProcessor.getScore(machineQuestion, cardsInMachine);
            cardsInMachine = GoFishRuleProcessor.removeScoredCards(scoreToAdd, machineQuestion, cardsInMachine);

            machineHand.setSize(cardsInMachine.size());
            machineHand.setCards(cardsInMachine);
            machineScore = machineScore + scoreToAdd;
            System.out.println("Now machine score:" + machineScore);
            /**
             * if you do not cards in hand it need to pick 7 cards from the
             * cards for game if remaining cards less than 7 then pick all
             *
             */
            cardsInYourHandCount = cardsInYourHand.size();
            if (cardsInYourHandCount == 0) {
                yourHand.setCards(GoFishRuleProcessor.generateHand(initialSize, cardsForGame));
                cardsForGame = GoFishRuleProcessor.refreshCardsForGame(initialSize, cardsForGame);
                cardsInYourHand = yourHand.getCards();
            }
        } else {
            System.out.println("you said: GO Fish!");

            cardsPassToMachine = GoFishRuleProcessor.generateHand(1, cardsForGame);
            cardsForGame = GoFishRuleProcessor.refreshCardsForGame(1, cardsForGame);
            cardsInMachine = GoFishRuleProcessor.addCardsToHand(cardsPassToMachine, cardsInMachine);

            machineHand.setSize(cardsInMachine.size());
            machineHand.setCards(cardsInMachine);

            yourTurn = true;
            machineTurn = false;
        }
    }

    /**
     *
     */
    private void processingRemainingCards() {

        HashMap<String, Integer> yourRemainingScore = GoFishRuleProcessor.getRemainingScores(cardsInYourHand);
        HashMap<String, Integer> machineRemainingScore = GoFishRuleProcessor.getRemainingScores(cardsInMachine);

        for (String value : yourRemainingScore.keySet()) {
            scoreToAdd = yourRemainingScore.get(value);
            cardsInYourHand = GoFishRuleProcessor.removeScoredCards(scoreToAdd, value, cardsInYourHand);

            yourHand.setSize(cardsInYourHand.size());
            yourHand.setCards(cardsInYourHand);
            yourScore = yourScore + scoreToAdd;
            System.out.println("Now your score:" + yourScore);
        }

        for (String value : machineRemainingScore.keySet()) {
            scoreToAdd = machineRemainingScore.get(value);
            cardsInMachine = GoFishRuleProcessor.removeScoredCards(scoreToAdd, value, cardsInMachine);

            machineHand.setSize(cardsInMachine.size());
            machineHand.setCards(cardsInMachine);
            machineScore = machineScore + scoreToAdd;
            System.out.println("Now machine score:" + machineScore);
        }
    }

    /**
     *
     */
    public void play() {
        Scanner interaction = new Scanner(System.in);
        /**
         * get cards for Go Fish Game totally 52 cards at the beginning
         */
        cardsForGame = GoFishRuleProcessor.getCardsForGame();
        /**
         * Distribute cards to both sides remove the already picked cards from
         * initial list
         */

        cardsInMachine = distributeCards(machineHand, initialSize);

        cardsInHandString = machineHand.toString();
        System.out.println("machine got cards:" + cardsInHandString);

        cardsInYourHand = distributeCards(yourHand, initialSize);
        /**
         * assume you start first define variables for the Game
         */
        cardsForGameCount = cardsForGame.size();
        cardsInYourHandCount = cardsInYourHand.size();
        cardsInMachineCount = cardsInMachine.size();

        /**
         * start game until the cards for Game is empty and one side does not
         * have cards in hand
         *
         */
        while (cardsForGameCount != 0 || (cardsInYourHandCount != 0 && cardsInMachineCount != 0)) {
            //It is your turn to ask question
            if (yourTurn) {
                processYourTurn(interaction);
            }
            //It is opportunity for machine to ask question
            if (machineTurn) {
                processMachineTurn();
            }
            cardsForGameCount = cardsForGame.size();
            /**
             * to accelerate the speed of Game when there is no remaining in the
             * cards for game directly calculate the scores in both sides if
             * they match the criteria
             */
            if (cardsForGameCount == 0) {
                processingRemainingCards();
            }
            cardsInYourHandCount = cardsInYourHand.size();
            cardsInMachineCount = cardsInMachine.size();
            System.out.println("Now you have " + cardsInYourHandCount + " cards!");
            System.out.println("Now machine have " + cardsInMachineCount + " cards!");
            System.out.println("Now total remaining " + cardsForGameCount + " cards!");
        }       
        declareWinner();
    }

    /**
     *
     * @param askQuestion
     * @param cardsInHand
     * @return
     */
    private String getQuestion(Scanner askQuestion, ArrayList<GoFishCard> cardsInHand) {
        boolean askValid = false;
        String yourQuestion = "";
        while (!askValid) {
            System.out.println("please pickup the value you own:");
            yourQuestion = askQuestion.nextLine().toUpperCase();
            for (GoFishCard card : cardsInHand) {
                String cardInHandValue = card.getValue().name();
                if (yourQuestion.equals(cardInHandValue)) {
                    askValid = true;
                }
            }
        }
        return yourQuestion;
    }

    /**
     *
     * @param cardsInHand
     * @return
     */
    private String getMchineQuestion(ArrayList<GoFishCard> cardsInHand) {
        Random rnd = new Random();
        int cardsCount = cardsInHand.size();
        GoFishCard card = cardsInHand.get(rnd.nextInt(cardsCount));
        String cardInHandValue = card.getValue().name();
        System.out.println("Do you have:" + cardInHandValue);
        return cardInHandValue;
    }

    public void declareWinner() {
        System.out.println("Your got :" + yourScore);
        System.out.println("Machine got :" + machineScore);
        if(yourScore > machineScore){
            System.out.println("Congrulations!You are the WINNER!!!");
        }else if(yourScore < machineScore){
              System.out.println("Sorry! You lose the game! Try next time.");
        }else{
              System.out.println("It is a tie game!");
        }        
    }
}
