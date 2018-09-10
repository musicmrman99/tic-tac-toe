package tictactoe;

/**
 *
 * @author s1307051
 */
public class Player {
    /**
     * The null Player. Should be used to indicate the absence of or lack of a player.
     */
    public static final Player NO_PLAYER = new Player("<NO_PLAYER>", null);
    
    private final String name;
    private final String repr;
    
    public Player(String name, String repr) {
        this.name = name;
        this.repr = repr;
    }
    
    /**
     * Return the human-readable name of this player.
     * @return The human-readable name of this player.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Return the in-game representation of the player.
     * @return The in-game representation of the player.
     */
    public String getRepr() {
        return repr;
    }
}
