/**
 * TODO:
 * - add feature to detect draws 
 *   - this could be as simple as checking if the grid is filled
 * - add leaderboard feature
 *   - can use Highscores as sub-structure for this, but it will need additional code
 *   - the Highscores structure is:
 *       [{(DateTime)timestamp: (int)score}, ...]
 *     whereas we need the following mapping (where 'result' is the name of one
 *     of the players, or the string 'draw'):
 *       [{(DateTime)timestamp: [(String)player1, (String)player2, (String)result]}, ...]
 *     and a more complex sort - to be able to sort by player like so:
 *       primary sort (show this): count(winner) - count(looser)
 *       secondary sort: last played match
 */

package tictactoe;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 *
 * @author s1307051
 */
public class TicTacToe extends JFrame {
    // Constants
    // ----------
    private final int rows = 3;
    private final int cols = 3;
    private final int runLength = 3;
    
    // Changing this will mean changes to the graphical design - be warned!
    // This also has a maximum of 8.
    private final int numPlayers = 2;
    
    // Variables
    // ----------
    private final Player[] players;
    private int curPlayer;
    
    private final JLabel[] playerNames;
    private final JLabel[] playerActiveIndicators;
    private final ImageIcon playerActiveIcon;
    private final ImageIcon playerInactiveIcon;
    
    private final GraphicalCell[][] graphicalGrid;
    private Cell[][] grid;
    
    private OutcomeTable outcomeTable;
    
    /**
     * Creates new form TicTacToe and creates needed data structures.
     */
    public TicTacToe() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        // Grid: Create Graphical
        // --------------------------------------------------
        pnlGrid.setLayout(new GridLayout(rows, cols));
        graphicalGrid = new GraphicalCell[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Create the cell and set their action listener
                GraphicalCell gCell = new GraphicalCell(row, col);
                gCell.addActionListener((java.awt.event.ActionEvent cellEvt) -> {
                    btnCellActionPerformed(cellEvt);
                });
                
                // Add the cell to the internal and graphical grids
                graphicalGrid[row][col] = gCell;
                pnlGrid.add(gCell);
            }
        }
        
        // Players: Create
        // --------------------------------------------------
        players = new Player[numPlayers];
        
        playerNames = new JLabel[] {
            lblPlayer1, lblPlayer2};
        playerActiveIndicators = new JLabel[] {
            lblPlayer1Active, lblPlayer2Active};
        
        // Set the text of the plater-related labels to nothing. They only have
        // text to make it easier to see and manipulate them in design view.
        for (JLabel lbl : playerNames) {
            lbl.setText("");
        }
        for (JLabel lbl : playerActiveIndicators) {
            lbl.setText("");
        }
        
        // Initialise the icons for the player active indicators
        playerActiveIcon = new ImageIcon(
            getResource("resources/player-active.png").getPath());
        playerInactiveIcon = new ImageIcon(
            getResource("resources/player-inactive.png").getPath());
        
        // Score Table: Create/Initailise
        // --------------------------------------------------
        
        try {
            outcomeTable = new OutcomeTable(getResource("appdata/outcome-table.txt"));
            outcomeTable.load();
            
        } catch (java.io.IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Error:\n"+e.getMessage()+"\nYour results may not be saved.", "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
        
        // Assersions
        // --------------------------------------------------
        // players, playerNames and playerActiveIndicators should all be equal length
        assert ((Integer)players.length).equals((Integer)playerNames.length);
        assert ((Integer)players.length).equals((Integer)playerActiveIndicators.length);
        
        // Also, just in case someone does something stupid:
        assert numPlayers > 1 && numPlayers <= 8;
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRoot = new javax.swing.JPanel();
        pnlMainMenu = new javax.swing.JPanel();
        btnPlay = new javax.swing.JButton();
        btnHelp = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();
        btnLeaderboard = new javax.swing.JButton();
        pnlGame = new javax.swing.JPanel();
        lblPlayer1 = new javax.swing.JLabel();
        lblPlayer2 = new javax.swing.JLabel();
        lblPlayer1Active = new javax.swing.JLabel();
        lblPlayer2Active = new javax.swing.JLabel();
        pnlGrid = new javax.swing.JPanel();
        btnGameMainMenu = new javax.swing.JButton();
        pnlHelp = new javax.swing.JPanel();
        pnlHelpMsg = new javax.swing.JPanel();
        lblHelpMsg = new javax.swing.JLabel();
        btnHelpBack = new javax.swing.JButton();
        pnlResults = new javax.swing.JPanel();
        pnlResultsMsg = new javax.swing.JPanel();
        lblResultsMsg = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JButton();
        pnlLeaderboard = new javax.swing.JPanel();
        pnlLeaderboardMsg = new javax.swing.JPanel();
        btnLeaderboardBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlRoot.setLayout(new java.awt.CardLayout());

        btnPlay.setText("Play");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        btnHelp.setText("Help");
        btnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpActionPerformed(evt);
            }
        });

        btnQuit.setText("Quit");
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });

        btnLeaderboard.setText("Leaderboard");
        btnLeaderboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeaderboardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainMenuLayout = new javax.swing.GroupLayout(pnlMainMenu);
        pnlMainMenu.setLayout(pnlMainMenuLayout);
        pnlMainMenuLayout.setHorizontalGroup(
            pnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainMenuLayout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addGroup(pnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLeaderboard, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(173, 173, 173))
        );

        pnlMainMenuLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHelp, btnLeaderboard, btnPlay, btnQuit});

        pnlMainMenuLayout.setVerticalGroup(
            pnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainMenuLayout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(btnPlay)
                .addGap(18, 18, 18)
                .addComponent(btnLeaderboard, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(173, Short.MAX_VALUE))
        );

        pnlMainMenuLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHelp, btnLeaderboard, btnPlay, btnQuit});

        pnlRoot.add(pnlMainMenu, "MainMenu");

        lblPlayer1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblPlayer1.setText("lblPlayer1");

        lblPlayer2.setText("lblPlayer2");

        lblPlayer1Active.setText("lblPlayer1Active");
        lblPlayer1Active.setToolTipText("");

        lblPlayer2Active.setText("lblPlayer2Active");
        lblPlayer2Active.setToolTipText("");

        javax.swing.GroupLayout pnlGridLayout = new javax.swing.GroupLayout(pnlGrid);
        pnlGrid.setLayout(pnlGridLayout);
        pnlGridLayout.setHorizontalGroup(
            pnlGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlGridLayout.setVerticalGroup(
            pnlGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );

        btnGameMainMenu.setText("Main Menu");
        btnGameMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGameMainMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlGameLayout = new javax.swing.GroupLayout(pnlGame);
        pnlGame.setLayout(pnlGameLayout);
        pnlGameLayout.setHorizontalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPlayer1Active)
                .addGap(18, 18, 18)
                .addComponent(lblPlayer1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 320, Short.MAX_VALUE)
                .addComponent(lblPlayer2)
                .addGap(18, 18, 18)
                .addComponent(lblPlayer2Active)
                .addContainerGap())
            .addComponent(pnlGrid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGameLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGameMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(163, 163, 163))
        );
        pnlGameLayout.setVerticalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlayer2)
                    .addComponent(lblPlayer1)
                    .addComponent(lblPlayer2Active)
                    .addComponent(lblPlayer1Active))
                .addGap(18, 18, 18)
                .addComponent(pnlGrid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGameMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlRoot.add(pnlGame, "Game");

        pnlHelpMsg.setBorder(javax.swing.BorderFactory.createTitledBorder(" Help "));

        lblHelpMsg.setText("<html>Tic-Tac-Toe is a game for two players, X and O, who take turns marking the spaces in a 3×3 grid. The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row wins the game.</html>");
        lblHelpMsg.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pnlHelpMsgLayout = new javax.swing.GroupLayout(pnlHelpMsg);
        pnlHelpMsg.setLayout(pnlHelpMsgLayout);
        pnlHelpMsgLayout.setHorizontalGroup(
            pnlHelpMsgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHelpMsgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHelpMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlHelpMsgLayout.setVerticalGroup(
            pnlHelpMsgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHelpMsgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHelpMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnHelpBack.setText("Back");
        btnHelpBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHelpLayout = new javax.swing.GroupLayout(pnlHelp);
        pnlHelp.setLayout(pnlHelpLayout);
        pnlHelpLayout.setHorizontalGroup(
            pnlHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHelpMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHelpLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHelpBack, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
        );
        pnlHelpLayout.setVerticalGroup(
            pnlHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHelpLayout.createSequentialGroup()
                .addComponent(pnlHelpMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHelpBack, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlRoot.add(pnlHelp, "Help");

        pnlResultsMsg.setBorder(javax.swing.BorderFactory.createTitledBorder(" Results "));

        lblResultsMsg.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblResultsMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResultsMsg.setText("[name] ([repr]) Wins!");

        javax.swing.GroupLayout pnlResultsMsgLayout = new javax.swing.GroupLayout(pnlResultsMsg);
        pnlResultsMsg.setLayout(pnlResultsMsgLayout);
        pnlResultsMsgLayout.setHorizontalGroup(
            pnlResultsMsgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultsMsgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblResultsMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlResultsMsgLayout.setVerticalGroup(
            pnlResultsMsgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultsMsgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblResultsMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnMainMenu.setText("Main Menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlResultsLayout = new javax.swing.GroupLayout(pnlResults);
        pnlResults.setLayout(pnlResultsLayout);
        pnlResultsLayout.setHorizontalGroup(
            pnlResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlResultsMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlResultsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(163, 163, 163))
        );
        pnlResultsLayout.setVerticalGroup(
            pnlResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultsLayout.createSequentialGroup()
                .addComponent(pnlResultsMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlRoot.add(pnlResults, "Results");

        pnlLeaderboardMsg.setBorder(javax.swing.BorderFactory.createTitledBorder(" Leaderboard "));
        pnlLeaderboardMsg.setLayout(new java.awt.GridBagLayout());

        btnLeaderboardBack.setText("Back");
        btnLeaderboardBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeaderboardBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLeaderboardLayout = new javax.swing.GroupLayout(pnlLeaderboard);
        pnlLeaderboard.setLayout(pnlLeaderboardLayout);
        pnlLeaderboardLayout.setHorizontalGroup(
            pnlLeaderboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLeaderboardMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlLeaderboardLayout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(btnLeaderboardBack, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
        );
        pnlLeaderboardLayout.setVerticalGroup(
            pnlLeaderboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeaderboardLayout.createSequentialGroup()
                .addComponent(pnlLeaderboardMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLeaderboardBack, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlRoot.add(pnlLeaderboard, "Leaderboard");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Return the File (path) of the given resource.
     * @param path The path to the resource, relative to the resources directory.
     * @return The File (path) to the given resource.
     */
    private File getResource(String path) {
        File file = null;
        
        try {
            Path packagePath = Paths.get(getClass().getResource("").toURI());
            file = packagePath.resolve(path).toFile();
        } catch (java.net.URISyntaxException e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Error:\n"+e.getMessage(), "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
        
        return file;
    }
    
    /**
     * Event handler for the 'Play' button on the Main Menu.
     * @param evt not used.
     */
    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        // Players: Initialise
        // --------------------------------------------------
        // If you have more than 8 players, then you're out of your mind.
        String[] repr = new String[] {"X", "O", "I", "U", "M", "N", "P", "Q"};
        
        for (int i = 0; i < players.length; i++) {
            String name = "";
            while (name.equals("")) {
                name = JOptionPane.showInputDialog("Player "+(i+1)+" Name ("+repr[i]+"):");
                
                // If the user has pressed 'cancel', return to the main menu.
                if (name == null) {
                    return;
                }
            }
            players[i] = new Player(name, repr[i]);
            
            // Set their name
            playerNames[i].setText(players[i].getName());
            
            // Initialise their active status
            playerActiveIndicators[i].setIcon(playerInactiveIcon);
        }
        
        // Set the first player
        curPlayer = ThreadLocalRandom.current().nextInt(0, 2);
        nextPlayer();
        
        // Grid: Create/Initialise Logical, Initialise Graphical
        // --------------------------------------------------
        grid = new Cell[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[col][row] = new Cell();
            }
        }
        
        updateGraphicalGrid();
                
        showScreen("Game");
    }//GEN-LAST:event_btnPlayActionPerformed

    /**
     * Event handler for the 'Help' button on the Main Menu.
     * @param evt not used.
     */
    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionPerformed
        showScreen("Help");
    }//GEN-LAST:event_btnHelpActionPerformed

    /**
     * Event handler for the 'Quit' button on the Main Menu.
     * @param evt not used.
     */
    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        quit();
    }//GEN-LAST:event_btnQuitActionPerformed

    /**
     * Event handler for the 'Back' button on the Help screen.
     * @param evt not used.
     */
    private void btnHelpBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpBackActionPerformed
        showScreen("MainMenu");
    }//GEN-LAST:event_btnHelpBackActionPerformed

    /**
     * Event handler for the 'Main Menu' button on the Results screen.
     * @param evt not used.
     */
    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        showScreen("MainMenu");
    }//GEN-LAST:event_btnMainMenuActionPerformed

    /**
     * Event handler for the 'Main Menu' button on the Leaderboard screen.
     * @param evt not used.
     */
    private void btnLeaderboardBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeaderboardBackActionPerformed
        showScreen("MainMenu");
    }//GEN-LAST:event_btnLeaderboardBackActionPerformed

    /**
     * Event handler for the 'Leaderboard' button on the Main Menu.
     * @param evt not used.
     */
    private void btnLeaderboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeaderboardActionPerformed
        // Initially show a loading message
        pnlLeaderboardMsg.removeAll();
        pnlLeaderboardMsg.add(new JLabel("Generating ..."));
        showScreen("Leaderboard");
        
        // Generate leaderboard
        // --------------------------------------------------
        // WARNING: This will become VERY slow if the numer of outcomes becomes large!
        
        // {playerName: [wins, losses]}
        // {String:     [int,  int]}
        Map<String, int[]> playerStats = new HashMap<>();
        
        // Iterate through the 'table' (a list of Timestamped<> instances) in
        // reverse chronological order, gathering a list of all players.
        for (Timestamped<Outcome> tsOutcome : outcomeTable.sorted()) {
            String[] playerNamesStr = tsOutcome.get().players;
            
            // For each player who participated inn the match, if they aren't
            // yet in the set, add them and their latest played match to the set.
            for (String playerName : playerNamesStr) {
                if (!playerStats.containsKey(playerName)) {
                    playerStats.put(playerName, new int[] {0, 0});
                }
            }
        }
        
        int[] values; // [wins, losses]
        
        // For each match ...
        for (Timestamped<Outcome> tsOutcome : outcomeTable.sorted()) {
            Outcome outcome = tsOutcome.get();
            
            // If won by someone ...
            if (!(outcome.winner.equals("<NO_PLAYER>"))) {
                // The winner: +1 win
                values = playerStats.get(outcome.winner);
                values[0] += 1;
                
                // The looser(s): +1 loss
                for (String playerNameStr : outcome.players) {
                    if (!(playerNameStr.equals(outcome.winner))) {
                        values = playerStats.get(playerNameStr);
                        values[1] += 1;
                    }
                }
            }
        }
        
        // Show leaderboard
        // --------------------------------------------------
        pnlLeaderboardMsg.removeAll();

        String[] entriesDescending = playerStats
                // Convert: -> sortable object
            .entrySet().stream()
                // Sort: descending (highest to lowest) by score
            .sorted((Map.Entry<String, int[]> e1, Map.Entry<String, int[]> e2) -> {
                int e1score = e1.getValue()[0] - e1.getValue()[1];
                int e2score = e2.getValue()[0] - e2.getValue()[1];
                return e2score - e1score;
            })
                // Map: {name: [wins, losses]} -> "name: [wins - losses]" ...
            .map(e -> String.format("%s: %d", e.getKey(), e.getValue()[0] - e.getValue()[1]))
                // Convert: -> indexable object
            .toArray(String[]::new);
        
        // Create and add label of each entry on the leaderboard
        for (int i = 0; i < entriesDescending.length; i++) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridy = i; // Place on separate rows
            pnlLeaderboardMsg.add(new JLabel(entriesDescending[i]), gbc);
        }
    }//GEN-LAST:event_btnLeaderboardActionPerformed

    /**
     * Event handler for the 'Main Menu' button on the Game screen.
     * @param evt not used.
     */
    private void btnGameMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGameMainMenuActionPerformed
        showScreen("MainMenu");
    }//GEN-LAST:event_btnGameMainMenuActionPerformed

    /**
     * Event handler for all of the GraphicalCells in the grid on the Game screen.
     * @param evt not used.
     */
    private void btnCellActionPerformed(java.awt.event.ActionEvent evt) {
        // Get graphical/logical cell
        GraphicalCell gCell = (GraphicalCell)evt.getSource();
        Cell cell = grid[gCell.row][gCell.col];
        
        // Make sure this cell has NOT already been activated
        if (!cell.isActive()) {
            cell.activate(players[curPlayer]);
            updateGraphicalGrid();
            
            // After this move, check if any possible runs would result in
            // a win, and if so, display it.
            Player winningPlayer = checkRuns(runLength);
            if (winningPlayer != Player.NO_PLAYER) {
                // !!! VICTORY !!!
                saveOutcome(winningPlayer);
                
                lblResultsMsg.setText(String.format("%s (%s) Wins!",
                    winningPlayer.getName(), winningPlayer.getRepr()
                ));
                showScreen("Results");
            }
            
            // If every single cell has been activated, then this game is a draw.
            boolean isDraw = checkDraw();
            if (isDraw) {
                // !!! DRAW !!!
                saveOutcome(Player.NO_PLAYER);
                
                lblResultsMsg.setText("The game is a draw!");
                showScreen("Results");
            }
            
            nextPlayer();
        }
    }
    
    /**
     * Show the given screen of the program.
     * @param screen 
     */
    private void showScreen(String screen) {
        java.awt.CardLayout layoutMgr = (java.awt.CardLayout)pnlRoot.getLayout();
        layoutMgr.show(pnlRoot, screen);
    }
    
    /**
     * Set the active player to the player at the given index.
     * @param playerIndex the index of the player to set to active status.
     */
    private void setActivePlayer(int playerIndex) {
        // I know this is somewhat inefficient
        for (JLabel indicator : playerActiveIndicators) {
            indicator.setIcon(playerInactiveIcon);
        }
        playerActiveIndicators[playerIndex].setIcon(playerActiveIcon);
    }
    
    /**
     * Cycle to the next player in the series, wrapping around to the first
     * player if needed.
     */
    private void nextPlayer() {
        curPlayer = (curPlayer + 1) % players.length;
        setActivePlayer(curPlayer);
    }
    
    /**
     * Add and save the outcome of this game.
     * @param winner The player that won the game (or no player for a draw).
     */
    private void saveOutcome(Player winner) {
        String[] playerNamesStr = new String[players.length];
        for (int i = 0; i < players.length; i++) {
            playerNamesStr[i] = players[i].getName();
        }
        outcomeTable.add(new Outcome(
            winner.getName(), playerNamesStr 
        ));

        try {
            outcomeTable.save();
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Error:\n"+e.getMessage()+"\nYour results will NOT be saved.", "Error",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
    /**
     * Checks all horizontal, vertical and diagonal runs.
     * @param n the number of cells in a row to check.
     */
    private Player checkRuns(int n) {
        List<String> orients = new LinkedList<>();
        orients.add("horizontal");
        orients.add("vertical");
        orients.add("diagonal");
        return checkRuns(n, orients);
    }
    
    /**
     * Check all of the possible lists of n cells in a row that were placed by
     * the same player.
     * 
     * @param n the number of cells in a row to check.
     * @param orients a list containing one or more of the strings "horizontal",
     * "vertical" and "diagonal", which specify the orientation of runs to
     * check.
     * @return Player.NO_PLAYER if no-one has won, or the winning player if
     * someone has won.
     */
    private Player checkRuns(int n, List<String> orients) {
        /*
        E.g. From this grid:
            1 2 3
          +-------
        1 | 1 2 3
        2 | 4 5 6
        3 | 7 8 9

        checkRuns(2) would result in this set of lists:
        {
        [1,2], [2,3], [4,5], [5,6], [7,8], [8,9],
        [1,4], [4,7], [2,5], [5,8], [3,6], [6,9],
        [1,5], [2,6], [4,8], [5,9],
        [2,4], [3,5], [5,7], [6,8]
        }

        checkRuns(3) would result in this set of lists:
        {
        [1,2,3], [4,5,6], [7,8,9],
        [1,4,7], [2,5,8], [3,6,9],
        [1,5,9], [3,5,7]
        }
        */
        
        List<List<Cell>> runs = new LinkedList<>();
        List<Cell> tmp;

        // Horizontal (-)
        if (orients.contains("horizontal")) {
            for (int row = 0; row < rows; row++) {
                for (int off = 0; off < (cols -n +1); off++) {
                    tmp = new LinkedList<>();
                    for (int i = 0; i < n; i++) {
                        tmp.add(grid[row][off+i]);
                    }
                    runs.add(tmp);
                }
            }
        }

        // Vertical (|)
        if (orients.contains("vertical")) {
            for (int col = 0; col < cols; col++) {
                for (int off = 0; off < (rows -n +1); off++) {
                    tmp = new LinkedList<>();
                    for (int i = 0; i < n; i++) {
                        tmp.add(grid[off+i][col]);
                    }
                    runs.add(tmp);
                }
            }
        }

        // Both Diagonals (\ and /)
        if (orients.contains("diagonal")) {
            for (int x_off = 0; x_off < (cols -n +1); x_off++) {
                for (int y_off = 0; y_off < (rows -n +1); y_off++) {
                    tmp = new LinkedList<>();
                    for (int i = 0; i < n; i++) {
                        tmp.add(grid[y_off+i][x_off+i]);
                    }
                    runs.add(tmp);
                }
            }
            
            for (int x_off = 0; x_off < (cols -n +1); x_off++) {
                for (int y_off = 0; y_off < (rows -n +1); y_off++) {
                    tmp = new LinkedList<>();
                    for (int i = 0; i < n; i++) {
                        tmp.add(grid[rows -1 -(y_off+i)][x_off+i]);
                    }
                    runs.add(tmp);
                }
            }
        }
        
        Cell initialCell;
        boolean winningRun;
        
        for (List<Cell> run : runs) {
            initialCell = run.get(0);
            
            // The player has won ...
            winningRun = true;
            
            // ... unless any cell in the run has not been activated, or has
            // been activated by someone other than the player that activated
            // the first cell in the run.
            for (int cell = 1; cell < run.size(); cell++) {
                Cell curCell = run.get(cell);
                if (curCell.isActive() == false || curCell.getActivator() != initialCell.getActivator()) {
                    winningRun = false;
                    break; // short-circuit
                }
            }
            
            if (winningRun) {
                return initialCell.getActivator();
            }
        }
        
        return Player.NO_PLAYER;
    }
    
    /**
     * Check whether the grid is in a draw state.
     * @return true if the grid is in a draw state, false otherwise.
     */
    private boolean checkDraw() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!grid[col][row].isActive()) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Update the graphical grid (graphicalGrid) to display the current state
     * of the logical grid (grid).
     */
    private void updateGraphicalGrid() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Cell cell = grid[row][col];
                GraphicalCell gCell = graphicalGrid[row][col];
                
                if (cell.isActive()) {
                    gCell.setText(cell.getActivator().getRepr());
                } else {
                    gCell.setText("");
                }
            }
        }
    }
    
    /**
     * Quit the program gracefully.
     */
    private void quit() {
        this.dispose();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | 
                InstantiationException | 
                IllegalAccessException | 
                javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TicTacToe().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGameMainMenu;
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnHelpBack;
    private javax.swing.JButton btnLeaderboard;
    private javax.swing.JButton btnLeaderboardBack;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnQuit;
    private javax.swing.JLabel lblHelpMsg;
    private javax.swing.JLabel lblPlayer1;
    private javax.swing.JLabel lblPlayer1Active;
    private javax.swing.JLabel lblPlayer2;
    private javax.swing.JLabel lblPlayer2Active;
    private javax.swing.JLabel lblResultsMsg;
    private javax.swing.JPanel pnlGame;
    private javax.swing.JPanel pnlGrid;
    private javax.swing.JPanel pnlHelp;
    private javax.swing.JPanel pnlHelpMsg;
    private javax.swing.JPanel pnlLeaderboard;
    private javax.swing.JPanel pnlLeaderboardMsg;
    private javax.swing.JPanel pnlMainMenu;
    private javax.swing.JPanel pnlResults;
    private javax.swing.JPanel pnlResultsMsg;
    private javax.swing.JPanel pnlRoot;
    // End of variables declaration//GEN-END:variables
}
