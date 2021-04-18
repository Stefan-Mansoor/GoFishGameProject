package ca.sheridancollege.SYST17796_ProjectStarterCode;

import java.util.ArrayList;
import ca.sheridancollege.SYST17796_ProjectStarterCode.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * This is the main class of Go-Fish Game.
 * @author Jinling Cai April 17, 2021
 */
public class PlayGoFishGame {

    public static void main(String[] args) {
        
        Scanner interaction = new Scanner(System.in);
        GoFishPlayer machinePlayer = new GoFishPlayer("machine", "machine_password");
        System.out.println("One side of the player will be machine!");
        GoFishPlayer secondPlayer = getSecondPlayer(interaction);
        
        ArrayList<GoFishPlayer> players = new ArrayList<GoFishPlayer>();
        players.add(machinePlayer);
        players.add(secondPlayer);
        
        GoFishGame goFishGame = new GoFishGame(players);
        System.out.println("we are all set! Let's play the game!");
        goFishGame.play();
    }  

    /**
     *
     * @param answerQuestion
     * @return
     */
    public static GoFishPlayer getSecondPlayer(Scanner answerQuestion) {
        System.out.println("Second player, please input your name:");
        String playerName = answerQuestion.nextLine();
        boolean pwdValid;
        System.out.println("Second player, please input your password:");
        String playerPwd = answerQuestion.nextLine();
        pwdValid = PasswordValidator.validatePassword(playerPwd);
        while (!pwdValid) {
            System.out.println("Second player, the password you input is invalid, please re-input your password:");
            playerPwd = answerQuestion.nextLine();
            pwdValid = PasswordValidator.validatePassword(playerPwd);
        }

        GoFishPlayer secondPlayer = new GoFishPlayer(playerName, playerPwd);
        return secondPlayer;
    }
}
