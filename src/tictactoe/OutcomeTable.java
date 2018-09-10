/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author William Taylor <musicmrman99@gmail.com>
 */
public class OutcomeTable {
    private List<Timestamped<Outcome>> outcomeList;
    private File boundFile;
    
    /**
     * Initialise an empty table.
     */
    public OutcomeTable() {
        outcomeList = new LinkedList<>();
        boundFile = null;
    }
    
    /**
     * Initialise an empty table.
     * @param file
     */
    public OutcomeTable(File file) {
        outcomeList = new LinkedList<>();
        boundFile = file;
    }
    
    /**
     * Add a highscore to the highscores data. If there are more than sizeLimit
     * scores currently there, then check if this score is better than the worst
     * score (or oldest score if there are multiple equal entries). If so,
     * replace the lowest score with the new score.
     * @param outcome The outcome to add to the table.
     */
    public void add(Outcome outcome) {
        Timestamped<Outcome> tsOutcome = new Timestamped<>(outcome);
        outcomeList.add(tsOutcome);
    }
    
    /**
     * Return a shallow copy of the highscores table, sorted in descending order.
     * @return A shallow copy of the highscores table, sorted in descending order.
     */
    public List<Timestamped<Outcome>> sorted() {
        List<Timestamped<Outcome>> sorted = new LinkedList<>(outcomeList);
        Collections.sort(sorted, Collections.reverseOrder());
        return sorted;
    }
    
    /**
     * Synchronise the highscores in the properties object to the primary
     * highscores data (after reading from disk).
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public void load() throws FileNotFoundException, IOException {
        // Load file
        Properties props = new Properties();
        try (FileReader file = new FileReader(boundFile)) {
            props.load(file);
        }
        
        outcomeList = new LinkedList<>();
        for (Map.Entry<Object, Object> entry : props.entrySet()) {
            String timestamp = (String)entry.getKey();
            String[] outcomeArr = ((String)entry.getValue()).split(";");
            
            outcomeList.add(new Timestamped<Outcome>(
                timestamp,
                new Outcome(outcomeArr[0], outcomeArr[1], outcomeArr[2])
            ));
        }
    }
    
    /**
     * Synchronise the highscores data to the corresponding properties object,
     * in preparation for writing to disk.
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public void save() throws FileNotFoundException, IOException {
        Properties props = new Properties();
        
        for (Timestamped<Outcome> timestampedOutcome : outcomeList) {
            String timestamp = timestampedOutcome.getTimestampAsString();
            Outcome outcome = timestampedOutcome.get();
            
            props.setProperty(
                timestamp,
                outcome.winner+";" + String.join(";", outcome.players)
            );
        }
        
        try (FileWriter file = new FileWriter(boundFile)) {
            props.store(file, "The highscores for the SwingPractice program.");
        }
    }
}
