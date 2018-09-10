package tictactoe;

/**
 * A cell on a grid of cells.
 * 
 * Can be clicked like a JButton.
 * @author William Taylor <musicmrman99@gmail.com>
 */
public class Cell {
    private boolean active;
    private Player activator;
    
    public Cell() {
        this.active = false;
        this.activator = Player.NO_PLAYER;
    }
    
    /**
     * Activate the cell, setting this cell's activator to player.
     * @param player The player that activated this cell.
     */
    public void activate(Player player) {
        active = true;
        activator = player;
    }
    
    /**
     * Return true if this cell has been activated, false otherwise.
     * @return true if this cell has been activated, false otherwise.
     */
    public boolean isActive() {
        return active;
    }
    /**
     * Return the Player that activated this cell.
     * 
     * If the cell has not been activated, return Player.NO_PLAYER.
     * @return The Player that activated this cell.
     */
    public Player getActivator() {
        return activator;
    }
}
