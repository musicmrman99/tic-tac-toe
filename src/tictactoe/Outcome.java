/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author William Taylor <musicmrman99@gmail.com>
 */
public class Outcome {
    public final String winner;
    public final String[] players;
    
    public Outcome(String winner, String... players) {
        this.winner = winner;
        this.players = players;
    }
}
