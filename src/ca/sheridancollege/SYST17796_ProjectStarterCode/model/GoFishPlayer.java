package ca.sheridancollege.SYST17796_ProjectStarterCode.model;
/**
 * This class models Go-Fish player
 * @author Jinling Cai April 17, 2021
 */
public class GoFishPlayer extends Player {

    private String password;
    private String name;

    /**
     * 
     * @param name
     * @param password
     */
    public GoFishPlayer(String name, String password) {
        // TODO - implement GoFishPlayer.GoFishPlayer
      super(name);
      this.name = name;
      this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    /**
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public void play() {
        // TODO - implement GoFishPlayer.play
        throw new UnsupportedOperationException();
    }

}