package ca.sheridancollege.SYST17796_ProjectStarterCode;

import ca.sheridancollege.SYST17796_ProjectStarterCode.model.PasswordValidator;
import ca.sheridancollege.SYST17796_ProjectStarterCode.model.GoFishPlayer;
import ca.sheridancollege.SYST17796_ProjectStarterCode.model.GoFishGame;
import java.util.ArrayList;
import ca.sheridancollege.SYST17796_ProjectStarterCode.view.UserView;

/**
 * This is the main class of Go-Fish Game.
 *
 * @author Moeez Yasir April 18, 2021
 * @author Jinling Cai April 17, 2021
 */
public class PlayGoFishGame {

    public static void main(String[] args) {
        UserView view = new UserView();

        GoFishPlayer machinePlayer = new GoFishPlayer("machine", "machine_password");
        view.display("One side of the player will be machine!");
        GoFishPlayer secondPlayer = getSecondPlayer();

        ArrayList<GoFishPlayer> players = new ArrayList<GoFishPlayer>();
        players.add(machinePlayer);
        players.add(secondPlayer);

        GoFishGame goFishGame = new GoFishGame(players);
        
        view.display("we are all set! Let's play the game!");
        view.display("********************\nGame Rules:\n"
                + "1. Game will end once one player runs out of cards.\n"
                + "2. All cards are supposed to match before game ends.\n"
                + "3. Picks a new card if the other player doesn't have the card you have asked.\n"
                + ""
                + "********************\nSystem Does:\n"
                + "1. Matches the similar cards and removes from the system\n"
                + "2. Keeps track of the cards in hands, and in remaining deck.\n"
                + "3. Assumption : Game continues even if deck is empty.\n"
                + "********************");
        goFishGame.play();
    }

    /**
     *
     * @param answerQuestion
     * @return
     */
    public static GoFishPlayer getSecondPlayer() {
        UserView view = new UserView();

        String playerName = view.userSelection("Please enter your name:");
        view.display("********************\n"
                        + "Password Criteria: \n>>>Length should be atleast 8 characters"
                        + "\n>>>Add 1 special characters\n"
                        + "********************");
        
        String playerPwd = view.userSelection("Please enter your password:");
        boolean pwdValid;
        
        pwdValid = PasswordValidator.validatePassword(playerPwd);
        
        while (!pwdValid) {
            view.display("The password you have entered is invalid");
            
            playerPwd = view.userSelection("Please enter your password that matches the given criteria.");
            pwdValid = PasswordValidator.validatePassword(playerPwd);

        }

        GoFishPlayer secondPlayer = new GoFishPlayer(playerName, playerPwd);
        return secondPlayer;
    }
}
